package com.webapp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webapp.bean.SysRole;
import com.webapp.common.bean.PageBean;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:frame-config.xml"})
public class SysRoleMapperTest{

	@Autowired
	private SysRoleMapper sysRoleMapper;

	/**
	 * 按主键删除
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void deleteByPrimaryKeyTest(){
		Integer flag=sysRoleMapper.deleteByPrimaryKey(2);
		System.out.println(flag);
	}

	/**
	 * 插入一条记录
	 * 
	 * @param t
	 * @return
	 */
	@Test
	public void insertTest(){
		SysRole sysRole=new SysRole();
		sysRole.setRoleName("员工");
		sysRole.setRoleDesc("苦B啊");
		sysRole.setSupRoleId(1);
		sysRole.setDomainId(50);
		sysRole.setDataState(new Byte("1"));
		sysRole.setCreator(1);
		sysRole.setCreateDate(new Date());
		sysRole.setModifier(1);
		sysRole.setModifyDate(new Date());
		Integer flag=sysRoleMapper.insert(sysRole);
		System.out.println(flag);
	}

	/**
	 * 插入不为空的字段
	 * 
	 * @param t
	 * @return
	 */
	@Test
	public void insertSelectiveTest(){
		SysRole sysRole=new SysRole();
		sysRole.setRoleName("总理");
		sysRole.setSupRoleId(1);
		sysRole.setDomainId(50);
		sysRole.setDataState(new Byte("1"));
		sysRole.setCreator(1);
		sysRole.setCreateDate(new Date());
		Integer flag=sysRoleMapper.insertSelective(sysRole);
		System.out.println(flag);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void selectByPrimaryKeyTest(){
		SysRole sysRole=sysRoleMapper.selectByPrimaryKey(10000);
		System.out.println(sysRole.getRoleName());
		System.out.println(sysRole.getRoleDesc());
	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	@Test
	public void getListTest(){
		List<SysRole> list=sysRoleMapper.getList();
		System.out.println(list.size());
	}

	/**
	 * 查询全部通过条件
	 * 
	 * @param param
	 * @return
	 */
	@Test
	public void getList_Test(){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put(SysRole.ROLE_DESC,"苦B啊");
		param.put(SysRole.ORDER_BY,"ROLE_ID DESC");
		List<SysRole> list=sysRoleMapper.getList_(param);
		System.out.println(list.size());
		System.out.println(list.get(0).getRoleId());
		System.out.println(list.get(1).getRoleId());
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	@Test
	public void getListPageTest(){
		PageBean page=new PageBean(2,1);
		List<SysRole> list=sysRoleMapper.getListPage(page);
		for(SysRole sysRole:list){
			System.out.println(sysRole.getRoleId());
		}
	}

	/**
	 * 分页查询通过条件
	 * 
	 * @param page
	 * @param param
	 * @return
	 */
	@Test
	public void getListPage_Test(){
		PageBean page=new PageBean(2,1);
		Map<String,Object> param=new HashMap<String,Object>();
		param.put(SysRole.ROLE_DESC,"苦B啊");
		param.put(SysRole.ORDER_BY,"ROLE_ID DESC");
		List<SysRole> list=sysRoleMapper.getListPage_(page,param);
		for(SysRole sysRole:list){
			System.out.println(sysRole.getRoleId());
		}
	}

	/**
	 * 更新不为空的字段
	 * 
	 * @param t
	 * @return
	 */
	@Test
	public void updateByPrimaryKeySelectiveTest(){
		SysRole sysRole=new SysRole();
		sysRole.setRoleId(10000);
		sysRole.setRoleName("服务员");
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);
	}

	/**
	 * 更新所有字段属性
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void updateByPrimaryKeyTest(){
		SysRole sysRole=new SysRole();
		sysRole.setRoleId(10000);
		sysRole.setRoleName("总经理");
		sysRole.setRoleDesc("没事总是喝茶");
		sysRole.setSupRoleId(10000);
		sysRole.setDomainId(10086);
		sysRole.setDataState(new Byte("0"));
		sysRole.setCreator(10010);
		sysRole.setCreateDate(new Date());
		sysRole.setModifier(10000);
		sysRole.setModifyDate(new Date());
		sysRoleMapper.updateByPrimaryKey(sysRole);
	}
}
