package com.jinpaihushi.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.Page;

public interface BaseService<T> {

	/**
	 * 插入数据
	 * 
	 * @param model
	 *            要插入的数据
	 * @return 对象ID
	 */
	String insert(T model);

	/**
	 * 批量添加 注意：如果集合为空将会报错
	 * 
	 * @param models
	 *            需要添加的元素
	 * @return 添加的数据条数
	 */
	int inserts(List<T> models);

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

	/**
	 * 更新一条信息
	 * 
	 * @param model
	 *            ID是必要的, 并且至少有一个需要更新的字段
	 * @return 是否更新成功
	 */
	boolean update(T model);

	/**
	 * 根据ID物理删除一条信息
	 * 
	 * @param id
	 *            对象ID
	 * @return 是否删除成功
	 */
	boolean deleteById(String id);

	/**
	 * 根据ID逻辑删除一条信息
	 * 
	 * @param id
	 *            受影响的数据条数
	 */
	boolean disableById(String id);

	/**
	 * 保存 如果有ID执行更新，没ID执行插入
	 * 
	 * @param model
	 *            对象
	 * @return 是否更新成功
	 */
	boolean save(T model);
}
