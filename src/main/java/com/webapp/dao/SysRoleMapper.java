package com.webapp.dao;

import org.springframework.stereotype.Repository;

import com.webapp.bean.SysRole;
import com.webapp.common.mapper.BaseMapper;

@Repository("mapper.sysRoleMapper")
public interface SysRoleMapper extends BaseMapper<SysRole>{}