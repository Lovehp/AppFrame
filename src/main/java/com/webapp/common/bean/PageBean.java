package com.webapp.common.bean;

import java.io.Serializable;

/**
 * 分页基类
 * @author luhf
 * @date 2013-1-13 下午9:56:54
 */
public class PageBean implements Serializable{
	private static final long serialVersionUID=347630882856280040L;
	// pagesize ，每一页显示多少
	private int showCount;
	// 总页数
	private int totalPage;
	// 总记录数
	private int totalResult;
	// 当前页数
	private int currentPage;
	// 当前显示到的ID, 在mysql limit 中就是第一个参数.
	private int currentResult;
	private String sortField;
	private String order;

	public PageBean(){}

	/**
	 * @param rows
	 *            展示行数
	 * @param page
	 *            页数
	 */
	public PageBean(int rows,int page){
		if(page<=0){
			page=1;
		}
		int currentResult=(page-1)*rows;
		this.setShowCount(rows);
		this.setCurrentResult(currentResult);
	}

	public int getShowCount(){
		return showCount;
	}

	public void setShowCount(int showCount){
		this.showCount=showCount;
	}

	public int getTotalPage(){
		return totalPage;
	}

	public void setTotalPage(int totalPage){
		this.totalPage=totalPage;
	}

	public int getTotalResult(){
		return totalResult;
	}

	public void setTotalResult(int totalResult){
		this.totalResult=totalResult;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage=currentPage;
	}

	public int getCurrentResult(){
		return currentResult;
	}

	public void setCurrentResult(int currentResult){
		this.currentResult=currentResult;
	}

	public String getSortField(){
		return sortField;
	}

	public void setSortField(String sortField){
		this.sortField=sortField;
	}

	public String getOrder(){
		return order;
	}

	public void setOrder(String order){
		this.order=order;
	}
}
