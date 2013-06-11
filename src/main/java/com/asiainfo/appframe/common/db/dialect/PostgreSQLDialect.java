package com.asiainfo.appframe.common.db.dialect;

/**
 * PostgreSQL方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class PostgreSQLDialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		sql=sql.trim();
		StringBuffer pagingSelect=new StringBuffer(sql.length()+20);
		pagingSelect.append(sql);
		pagingSelect.append(offset>0?" limit "+limit+" offset " + offset : " limit " + limit);
		return pagingSelect.toString();
	}

}
