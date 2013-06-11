package com.asiainfo.appframe.common.db.dialect;

/**
 * HSQL方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class HSQLDialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		sql=sql.trim();
		StringBuffer pagingSelect=new StringBuffer(sql.length()+10);
		pagingSelect.append(sql);
		pagingSelect.insert(sql.toLowerCase().indexOf("select")+6,(offset>0)?" limit "+offset+" "+limit:" top "+limit);
		return pagingSelect.toString();
	}

}
