package com.asiainfo.appframe.bean;

import java.util.Date;

import com.asiainfo.appframe.common.bean.BaseBean;

public class SysRole extends BaseBean{

	private static final long serialVersionUID=1L;

	public static final String ROLE_ID="roleId";
	public static final String ROLE_NAME="roleName";
	public static final String ROLE_DESC="roleDesc";
	public static final String SUP_ROLE_ID="supRoleId";
	public static final String DOMAIN_ID="domainId";
	public static final String DATA_STATE="dataState";
	public static final String CREATOR="creator";
	public static final String CREATE_DATE="createDate";
	public static final String MODIFIER="modifier";
	public static final String MODIFY_DATE="modifyDate";

	public SysRole(){
		super();
	}

	private Integer roleId;

	private String roleName;

	private String roleDesc;

	private Integer supRoleId;

	private Integer domainId;

	private Byte dataState;

	private Integer creator;

	private Date createDate;

	private Integer modifier;

	private Date modifyDate;

	public Integer getRoleId(){
		return roleId;
	}

	public void setRoleId(Integer roleId){
		this.roleId=roleId;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName=roleName==null?null:roleName.trim();
	}

	public String getRoleDesc(){
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc){
		this.roleDesc=roleDesc==null?null:roleDesc.trim();
	}

	public Integer getSupRoleId(){
		return supRoleId;
	}

	public void setSupRoleId(Integer supRoleId){
		this.supRoleId=supRoleId;
	}

	public Integer getDomainId(){
		return domainId;
	}

	public void setDomainId(Integer domainId){
		this.domainId=domainId;
	}

	public Byte getDataState(){
		return dataState;
	}

	public void setDataState(Byte dataState){
		this.dataState=dataState;
	}

	public Integer getCreator(){
		return creator;
	}

	public void setCreator(Integer creator){
		this.creator=creator;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate=createDate;
	}

	public Integer getModifier(){
		return modifier;
	}

	public void setModifier(Integer modifier){
		this.modifier=modifier;
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate){
		this.modifyDate=modifyDate;
	}
}