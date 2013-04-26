package com.webapp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.webapp.bean.SysRole;
import com.webapp.common.mapper.BaseMapper;
import com.webapp.common.service.BaseService;
import com.webapp.dao.SysRoleMapper;
import com.webapp.service.SysRoleService;

@Service("impl.sysRoleServiceImpl")
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService{

	@SuppressWarnings("rawtypes")
	private BaseMapper mapper;

	@Autowired
	@Qualifier("mapper.sysRoleMapper")
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public Map<String,Object> getSysRoleList(int rows,int page){
		return getListByPage(rows,page);
	}

	@Override
	public Map<String,Object> getSysRoleList(int rows,int page,Map<String,String> param){
		return getListByPage(rows,page,param);
	}

	@Override
	public Map<String,Object> addSysRole(SysRole sysRole){
		Map<String,Object> map=new HashMap<String,Object>(1);
		try{
			sysRoleMapper.insert(sysRole);
			map.put("success","true");
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseMapper getMapper(){
		if(mapper==null)
			mapper=sysRoleMapper;// 默认mapper
		return mapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setMapper(BaseMapper mapper){
		this.mapper=mapper;
	}

}
