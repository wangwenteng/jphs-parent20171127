package com.jinpaihushi.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.Page;

public interface ReadonlyService<T> {

	/**
	 * 根据查询条件统计数据个数
	 * 
	 * @param model
	 *            匹配条件
	 * @return 返回匹配条件对象的个数
	 */
	int count(T model);

	/**
	 * 根据条件查询匹配的数据, 分页查询
	 * 
	 * @param model
	 *            查询条件
	 * @param row
	 *            分页边界
	 * @return 匹配的对象列表
	 */
	Page<T> query(T model, RowBounds row);

	/**
	 * 根据条件查询匹配的数据, 分页查询
	 * 
	 * @param model
	 *            查询条件
	 * @return 匹配的对象列表
	 */
	Page<T> query(T model);

	/**
	 * 根据条件查询匹配的数据
	 * 
	 * @param model
	 *            查询条件
	 * @return 所有匹配的对象列表
	 */
	List<T> list(T model);

	/**
	 * 根据条件加载一条信息
	 * 
	 * @param model
	 *            查询条件
	 * @return 加载的对象
	 */
	T load(T model);

	/**
	 * 根据ID查询信息
	 * 
	 * @param id
	 *            数据ID
	 * @return 加载的对象
	 */
	T loadById(String id);
}
