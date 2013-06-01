package com.asiainfo.appframe.common.db.dialect;

/**
 * 实现分页的方言
 * 
 * @author luhf
 * @date 2013-1-13 下午2:53:24
 */
public abstract class Dialect{

	public static enum Type{
		MYSQL,ORACLE,DB2,DERBY,H2,HSQL,POSTGRESQL,SQLSERVER2005,SQLSERVER,SYBASE;
	}

	/**
	 * 获取分页sql
	 * @param sql 查询sql
	 * @param offset 首行
	 * @param limit 结束行
	 * @return
	 */
	public abstract String getLimitString(String sql,Integer offset,Integer limit);
}
