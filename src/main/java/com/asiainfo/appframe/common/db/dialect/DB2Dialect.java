package com.asiainfo.appframe.common.db.dialect;

/**
 * DB2方言分页
 * 
 * @author luhf
 * @date 2013-1-13 下午2:57:12
 */
public class DB2Dialect extends Dialect{

	@Override
	public String getLimitString(String sql,Integer offset,Integer limit){
		sql=sql.trim();
		int startOfSelect=sql.toLowerCase().indexOf("select");
		StringBuffer pagingSelect=new StringBuffer(sql.length()+100)
				.append(sql.substring(0,startOfSelect)) // add the comment
				.append("select * from ( select ") // nest the main query in an outer select
				.append(getRowNumber(sql)); // add the rownnumber bit into the outer query select list
		if(hasDistinct(sql)){
			pagingSelect.append(" row_.* from ( ") // add another (inner) nested select
						.append(sql.substring(startOfSelect)) // add the main query
						.append(" ) as row_"); // close off the inner nested select
		}else{
			pagingSelect.append(sql.substring(startOfSelect+6)); // add the main query
		}
		pagingSelect.append(" ) as temp_ where rownumber_ ");
		// add the restriction to the outer select
		if(offset>0){ // 如果开始有开始索引有数据时
			// pagingSelect.append("between ").append(offset).append(" and ").append(limit);
			pagingSelect.append(">").append(offset).append(" and rownumber_ <= ").append(limit);
		}else{
			pagingSelect.append("<= ").append(limit);
		}
		return pagingSelect.toString();
	}
	
	private String getRowNumber(String sql) {
		StringBuffer rownumber = new StringBuffer(50)
				.append("rownumber() over(");
		int orderByIndex = sql.toLowerCase().indexOf("order by");
		if (orderByIndex > 0 && !hasDistinct(sql)) {
			rownumber.append(sql.substring(orderByIndex));
		}
		rownumber.append(") as rownumber_,");
		return rownumber.toString();
	}

	private boolean hasDistinct(String sql) {
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}
}