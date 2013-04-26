package com.webapp.common.tree;

import java.util.Map;

public class DefaultTreeNode implements ITreeNode{

	private String id;
	private String state;
	private String pid;
	private String text;
	private boolean leaf;
	private Map<String,Object> attributes;
	private String[] attributeNames;

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state=state;
	}

	public void setId(String id){
		this.id=id;
	}

	public void setPid(String pid){
		this.pid=pid;
	}

	public void setText(String text){
		this.text=text;
	}

	public void setLeaf(boolean leaf){
		this.leaf=leaf;
	}

	public void setAttributes(Map<String,Object> attributes){
		this.attributes=attributes;
	}

	public void setAttributeNames(String[] attributeNames){
		this.attributeNames=attributeNames;
	}

	@Override
	public String getId(){
		return this.id;
	}

	@Override
	public String getPid(){
		return this.pid;
	}

	@Override
	public String getText(){
		return this.text;
	}

	@Override
	public boolean isLeaf(){
		return this.leaf;
	}

	@Override
	public Map<String,Object> getAttributes(){
		return this.attributes;
	}

	@Override
	public String[] getAttributeNames(){
		return this.attributeNames;
	}

}
