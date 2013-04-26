package com.webapp.web.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.common.bean.PageBean;
import com.webapp.common.db.dialect.Dialect;
import com.webapp.common.db.dialect.MysqlDialect;
import com.webapp.util.Reflections;

/**
 * mybatis分页拦截器
 * 
 * @author luhf
 * @date 2013-4-17 下午10:00:08
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	private static String dialect;// 数据库方言
	private static String pageSqlId;// 包含分页mapper方法名称

	/**
	 * 执行操作
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable{
		RoutingStatementHandler statement=(RoutingStatementHandler)invocation.getTarget();
		PreparedStatementHandler handler=(PreparedStatementHandler)Reflections.getFieldValue(statement,"delegate");
		MappedStatement mappedStatement=(MappedStatement)Reflections.getFieldValue(handler,"mappedStatement");
		// 是否匹配分页mapper方法名称[正则表达式]
		if(mappedStatement.getId().matches(pageSqlId)){
			BoundSql boundSql=handler.getBoundSql();
			Object parameterObject=boundSql.getParameterObject();
			if(parameterObject==null){
				throw new NullPointerException("parameterObject error");
			}else{
				Connection connection=(Connection)invocation.getArgs()[0];
				String sql=boundSql.getSql();
				String countSql="select count(0) from ("+sql+") myCount";
				logger.debug("count sql :"+countSql);
				PreparedStatement countStmt=connection.prepareStatement(countSql);
				BoundSql countBS=new BoundSql(mappedStatement.getConfiguration(),countSql,boundSql.getParameterMappings(),parameterObject);
				setParameters(countStmt,mappedStatement,countBS,parameterObject);
				ResultSet rs=countStmt.executeQuery();
				int count=0;
				if(rs.next()){
					count=rs.getInt(1);
				}
				rs.close();
				countStmt.close();

				PageBean page=null;
				if(parameterObject instanceof PageBean){
					page=(PageBean)parameterObject;
					page.setTotalResult(count);
				}else if(parameterObject instanceof Map){
					Map<String,Object> map=(Map<String,Object>)parameterObject;
					page=(PageBean)map.get("page");
					if(page==null){
						page=new PageBean();
					}
					page.setTotalResult(count);
				}else{
					Field pageField=(Field)Reflections.getFieldValue(parameterObject,"page");
					if(pageField!=null){
						page=(PageBean)Reflections.getFieldValue(parameterObject,"page");
						if(page==null){
							page=new PageBean();
						}
						page.setTotalResult(count);
						Reflections.setFieldValue(parameterObject,"page",page);
					}else{
						throw new NoSuchFieldException(parameterObject.getClass().getName());
					}
				}
				String pageSql=generatePageSql(sql,page);
				logger.debug("limit sql :"+pageSql);
				Reflections.setFieldValue(boundSql,"sql",pageSql);
			}
		}
		return invocation.proceed();
	}

	/**
	 * 插件操作
	 */
	@Override
	public Object plugin(Object target){
		logger.debug("plugin(Object target) :"+target.toString());
		return Plugin.wrap(target,this);
	}

	/**
	 * 配置设置
	 */
	@Override
	public void setProperties(Properties properties){
		logger.debug("setProperties(Properties properties) :"+properties.getProperty("dialect"));
		dialect=properties.getProperty("dialect");
		if(dialect==null||dialect.equals("")){
			try{
				throw new PropertyException("dialect property is not found!");
			}catch(PropertyException e){
				e.printStackTrace();
			}
		}
		pageSqlId=properties.getProperty("pageSqlId");
		if(dialect==null||dialect.equals("")){
			try{
				throw new PropertyException("pageSqlId property is not found!");
			}catch(PropertyException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成不同方言的sql语句
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePageSql(String sql,PageBean page){
		if(null!=page&&(null!=dialect||!"".equals(dialect))){
			String pageSql=null;
			if(Dialect.Type.MYSQL.toString().equalsIgnoreCase(dialect)){
				pageSql=new MysqlDialect().getLimitString(sql,page.getCurrentResult(),page.getShowCount());
			}
			return pageSql;
		}else{
			return sql;
		}
	}

	/**
	 * 设置参数
	 * 
	 * @param countStmt
	 * @param mappedStatement
	 * @param countBS
	 * @param parameterObject
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException{
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings=boundSql.getParameterMappings();
		if(parameterMappings!=null){
			Configuration configuration=mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry=configuration.getTypeHandlerRegistry();
			MetaObject metaObject=parameterObject==null?null:configuration.newMetaObject(parameterObject);
			for(int i=0;i<parameterMappings.size();i++){
				ParameterMapping parameterMapping=parameterMappings.get(i);
				if(parameterMapping.getMode()!=ParameterMode.OUT){
					Object value;
					String propertyName=parameterMapping.getProperty();
					PropertyTokenizer prop=new PropertyTokenizer(propertyName);
					if(parameterObject==null){
						value=null;
					}else if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())){
						value=parameterObject;
					}else if(boundSql.hasAdditionalParameter(propertyName)){
						value=boundSql.getAdditionalParameter(propertyName);
					}else if(propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&&boundSql.hasAdditionalParameter(prop.getName())){
						value=boundSql.getAdditionalParameter(prop.getName());
						if(value!=null){
							value=configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					}else{
						value=metaObject==null?null:metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler=parameterMapping.getTypeHandler();
					if(typeHandler==null){
						throw new ExecutorException("There was no TypeHandler found for parameter "+propertyName+" of statement "+mappedStatement.getId());
					}
					typeHandler.setParameter(ps,i+1,value,parameterMapping.getJdbcType());
				}
			}
		}
	}
}