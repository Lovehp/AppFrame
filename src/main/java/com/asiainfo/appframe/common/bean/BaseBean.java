package com.asiainfo.appframe.common.bean;

import java.io.Serializable;

/**
 * 实体类的基类
 * 
 * @author luhf
 * @date Jan 8, 2013 12:25:36 PM
 */
public class BaseBean implements Serializable{

	/*
	 * 序列化 
	 */
	private static final long serialVersionUID=-6676124739888373172L;

	/*
	 * 分隔符
	 */
	public static final String SPLIT_CHARACTER=" ";

	/*
	 * 排序
	 */
	public static final String ORDER_BY="orderBy";
}