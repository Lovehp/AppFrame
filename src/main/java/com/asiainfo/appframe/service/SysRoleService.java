package com.asiainfo.appframe.service;

import java.util.Map;

import com.asiainfo.appframe.bean.SysRole;

/**
 * 角色服务接口
 * 
 * @author luhf
 * @date 2013-4-14 下午11:31:52
 */
public interface SysRoleService{

	/**
	 * 查找所有角色
	 * 
	 * @param rows
	 *            展示行数
	 * @param page
	 *            页数
	 * @return
	 */
	Map<String,Object> getSysRoleList(int rows,int page);

	/**
	 * 查找所有角色
	 * 
	 * @param rows
	 *            展示行数
	 * @param page
	 *            页数
	 * @param param
	 *            条件
	 * @return
	 */
	Map<String,Object> getSysRoleList(int rows,int page,Map<String,String> param);

	/**
	 * 增加角色
	 * 
	 * @param userBean
	 *            用户bean
	 * @return
	 */
	Map<String,Object> addSysRole(SysRole sysRole);
}
