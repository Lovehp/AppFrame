package com.asiainfo.appframe.common.db.dialect;

/**
 * Mysql方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class MysqlDialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		sql=sql.trim();
		StringBuffer pagingSelect=new StringBuffer(sql.length()+20);
		pagingSelect.append(sql);
		pagingSelect.append(" limit "+offset+","+limit);
		return pagingSelect.toString();
	}

}
