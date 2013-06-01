package com.asiainfo.appframe.common.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.asiainfo.appframe.common.bean.BaseBean;
import com.asiainfo.appframe.common.bean.PageBean;

/**
 * Mapper基类
 * 
 * @author luhf
 * @date 2013-4-14 下午4:30:07
 * @param <T>
 */
public interface BaseMapper<T extends BaseBean>{

	/**
	 * 按主键删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Serializable id);

	/**
	 * 插入一条记录
	 * 
	 * @param t
	 * @return
	 */
	int insert(T t);

	/**
	 * 插入不为空的字段
	 * 
	 * @param t
	 * @return
	 */
	int insertSelective(T t);

	/**
	 * 按主键查询
	 * 
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(Serializable id);

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	List<T> getList();

	/**
	 * 查询全部通过条件
	 * 
	 * @param param
	 * @return
	 */
	List<T> getList_(@Param("param")Map<String,Object> param);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	List<T> getListPage(@Param("page") PageBean pageBean);

	/**
	 * 分页查询通过条件
	 * 
	 * @param page
	 * @param param
	 * @return
	 */
	List<T> getListPage_(@Param("page") PageBean pageBean,@Param("param")Map<String,Object> param);

	/**
	 * 更新不为空的字段
	 * 
	 * @param t
	 * @return
	 */
	int updateByPrimaryKeySelective(T t);

	/**
	 * 更新所有字段属性
	 * 
	 * @param id
	 * @return
	 */
	int updateByPrimaryKey(T t);
}
