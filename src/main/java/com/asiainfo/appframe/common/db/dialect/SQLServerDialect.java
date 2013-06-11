package com.asiainfo.appframe.common.db.dialect;

/**
 * SQLServer方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class SQLServerDialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		sql=sql.trim();
		if (offset > 0) {
			throw new UnsupportedOperationException("sql server has no offset");
		}
		StringBuffer pagingSelect=new StringBuffer(sql.length()+8);
		pagingSelect.append(sql);
		pagingSelect.insert(getAfterSelectInsertPoint(sql), " top " + limit);
		return pagingSelect.toString();
	}

	private int getAfterSelectInsertPoint(String sql){
		int selectIndex = sql.toLowerCase().indexOf("select");
		final int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
	}

}
