package com.jinpaihushi.jphs.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.model.Order;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Repository("orderDao")
public interface OrderDao extends BaseDao<Order> {

	Page<Order> getList(Order order);

	/**
	 * 获取用户的订单
	 * 
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> getUserOrder(Map<String, Object> query);

	/**
	 * 订单详情
	 * @param deviceType 
	 * @param id 订单id
	 * @return
	 */
	Order getUserOrderDetail(Order order);
}
