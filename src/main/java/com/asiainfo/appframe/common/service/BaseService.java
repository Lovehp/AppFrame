package com.asiainfo.appframe.common.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.appframe.common.bean.BaseBean;
import com.asiainfo.appframe.common.bean.PageBean;
import com.asiainfo.appframe.common.mapper.BaseMapper;

/**
 * 业务层基类
 * @author luhf
 * @date 2013-4-14 下午11:25:19
 * @param <T>
 */
public abstract class BaseService<T extends BaseBean>{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	/*
	 * 查询分页集合
	 */
	public Map<String,Object> getListByPage(Integer rows,Integer page){
		Map<String,Object> map=new HashMap<String,Object>();
		PageBean pageBean=new PageBean(rows,page);
		map.put("rows",getMapper().getListPage(pageBean));
		map.put("total",pageBean.getTotalResult());
		return map;
	}
	
	/*
	 * 查询分页集合通过条件
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getListByPage(Integer rows,Integer page,Map<String,String> param){
		Map<String,Object> map=new HashMap<String,Object>();
		PageBean pageBean=new PageBean(rows,page);
		map.put("rows",getMapper().getListPage_(pageBean,param));
		map.put("total",pageBean.getTotalResult());
		return map;
	}

	/*
	 * 查询集合
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListAll(){
		return getMapper().getList();
	}
	
	/*
	 * 查询集合通过条件 
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListAll(Map<String,String> param){
		return getMapper().getList_(param);
	}

	/*
	 * 按主键删除
	 */
	public int deleteByPrimaryKey(Serializable id){
		return getMapper().deleteByPrimaryKey(id);
	}

	/*
	 * 插入一条记录
	 */
	@SuppressWarnings("unchecked")
	public int insert(T entity){
		return getMapper().insert(entity);
	}

	/*
	 * 插入不为空的字段
	 */
	@SuppressWarnings("unchecked")
	public int insertSelective(T entity){
		return getMapper().insertSelective(entity);
	}

	/*
	 * 按主键查询
	 */
	@SuppressWarnings("unchecked")
	public T selectByPrimaryKey(Integer id){
		return (T)getMapper().selectByPrimaryKey(id);
	}

	/*
	 * 更新不为空的字段
	 */
	@SuppressWarnings("unchecked")
	public int updateByPrimaryKeySelective(T entity){
		return getMapper().updateByPrimaryKeySelective(entity);
	}

	/*
	 * 更新所有字段属性
	 */
	@SuppressWarnings("unchecked")
	public int updateByPrimaryKey(T entity){
		return getMapper().updateByPrimaryKey(entity);
	}

	/*
	 * 抽象类获取Mapper
	 */
	@SuppressWarnings("rawtypes")
	public abstract BaseMapper getMapper();

	/*
	 * 抽象类设置Mapper
	 */
	@SuppressWarnings("rawtypes")
	public abstract void setMapper(BaseMapper mapper);
}
