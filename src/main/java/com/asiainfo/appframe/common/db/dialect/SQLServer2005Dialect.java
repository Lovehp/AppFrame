package com.asiainfo.appframe.common.db.dialect;

/**
 * SQLServer2005方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class SQLServer2005Dialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		sql=sql.trim();
		StringBuffer pagingSelect=new StringBuffer();
		String orderby=getOrderByPart(sql);
		String distinctStr="";
		String loweredString=sql.toLowerCase();
		String sqlPartString=sql;
		if(loweredString.trim().startsWith("select")){
			int index=6;
			if(loweredString.startsWith("select distinct")){
				distinctStr="DISTINCT ";
				index=15;
			}
			sqlPartString=sqlPartString.substring(index);
		}
		pagingSelect.append(sqlPartString);

		// if no ORDER BY is specified use fake ORDER BY field to avoid errors
		if(orderby==null||orderby.length()==0){
			orderby="ORDER BY CURRENT_TIMESTAMP";
		}

		StringBuffer result=new StringBuffer();
		result.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ").append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingSelect).append(") SELECT * FROM query WHERE __row_number__ BETWEEN ").append(offset).append(" AND ").append(offset+limit).append(" ORDER BY __row_number__");
		return pagingSelect.toString();
	}

	private String getOrderByPart(String sql){
		String loweredString=sql.toLowerCase();
		int orderByIndex=loweredString.indexOf("order by");
		if(orderByIndex!=-1){
			// if we find a new "order by" then we need to ignore
			// the previous one since it was probably used for a subquery
			return sql.substring(orderByIndex);
		}else{
			return "";
		}
	}

}
