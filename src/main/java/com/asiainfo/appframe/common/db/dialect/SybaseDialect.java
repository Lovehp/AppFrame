package com.asiainfo.appframe.common.db.dialect;

/**
 * Sybase方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class SybaseDialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		throw new UnsupportedOperationException("paged queries not supported");
	}

}
