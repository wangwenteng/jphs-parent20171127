package com.jinpaihushi.jphs.order.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.insurance.model.Insurance;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderInfo;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
public interface OrderService extends BaseService<Order> {

	Page<Order> getList(Order order);

	/**
	 * 创建订单
	 * @param order 订单及相关信息
	 * @param insurance 保险的信息
	 * @return
	 */
	Map<String, Object> createOrder( OrderInfo orderInfo);

	/**
	 * 获取用的个人中心的订单列表
	 * @param user 用户对象
	 * @return
	 */
	List<Map<String,Object>> getUserOrder(Map<String, Object> query);
	
	/**
	 * 查询订单详情
	 * @param userId 用户id
	 * @param deviceType 
	 * @return
	 */
	Order getUserOrderDetail (String orderId, Integer deviceType);

	/**
	 * 验证支付金额
	 * @param orderNo
	 * @param payParice
	 * @return
	 */
	boolean checkPrice(String orderNo, Double payParice);

}