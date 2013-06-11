package com.asiainfo.appframe.common.db.dialect;

/**
 * Oracle方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class OracleDialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		sql=sql.trim();
		StringBuffer pagingSelect=new StringBuffer(sql.length()+100);
		pagingSelect.append("select * from ( select row_.*, rownumber() over() rn_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) where rn_ > ").append(offset).append(" and rn_ <= ").append(offset+limit);
		return pagingSelect.toString();
	}
}
