package com.asiainfo.appframe.dao;

import org.springframework.stereotype.Repository;

import com.asiainfo.appframe.bean.SysRole;
import com.asiainfo.appframe.common.mapper.BaseMapper;

@Repository("mapper.sysRoleMapper")
public interface SysRoleMapper extends BaseMapper<SysRole>{}