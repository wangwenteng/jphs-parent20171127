package com.jinpaihushi.jphs.order.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.dao.OrderDao;
import com.jinpaihushi.jphs.order.dao.OrderGoodsDao;
import com.jinpaihushi.jphs.order.dao.OrderOtherDao;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.model.OrderInfo;
import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.order.service.OrderServiceService;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	@Autowired
	private OrderOtherDao orderOtherDao;
	@Autowired
	private OrderServiceService orderServiceService;
	@Autowired
	private ServiceImagesService serviceImagesService;
	@Override
	protected BaseDao<Order> getDao() {
		return orderDao;
	}

	
	@Override
	public Page<Order> getList(Order order) {
		return orderDao.getList(order);
	}

	/**
	 * 创建订单
	 * 
	 * @param order
	 *            订单及相关信息
	 * @param insurance
	 *            保险的信息
	 * @return
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> createOrder(OrderInfo orderInfo) {
		Map<String, Object> result = new HashMap<>();
		try {
			// 插入订单的基础信息
			Order order = new Order();
			BeanUtils.copyProperties(order, orderInfo);
			order.setId(UUIDUtils.getId());
			order.setOrderNo(Common.getOrderNumber());
			order.setSchedule(0);
			order.setStatus(0);
			order.setCreateTime(new Date());
			int i = orderDao.insert(order);
			if (i > 0) {
				// 添加订单的商品信息
				OrderGoods orderGoods = new OrderGoods();
				BeanUtils.copyProperties(orderGoods, orderInfo);
				orderGoods.setId(UUIDUtils.getId());
				orderGoods.setCreateTime(new Date());
				orderGoods.setOrderId(order.getId());
				orderGoods.setStatus(0);
				i = orderGoodsDao.insert(orderGoods);
				if (i > 0) {
					// 添加订单的其他信息
					OrderOther orderOther = new OrderOther();
					BeanUtils.copyProperties(orderOther, orderInfo);
					orderOther.setId(UUIDUtils.getId());
					orderOther.setStatus(0);
					orderOther.setCreateTime(new Date());
					orderOther.setOrderId(order.getId());
					i = orderOtherDao.insert(orderOther);
					//插入图片
					ServiceImages serviceImages= new ServiceImages();
					serviceImages.setId(UUID.randomUUID().toString());
					serviceImages.setCreatorId(order.getCreatorId());
					serviceImages.setCreatorName(order.getCreatorName());
					serviceImages.setCreateTime(new Date());
					serviceImages.setSourceId(order.getId());
					serviceImagesService.insert(serviceImages);
					if (i > 0) {
						com.jinpaihushi.jphs.order.model.OrderService orderService = null;
						for (int j = 0; j < orderInfo.getServiceNumber(); j++) {
							orderService = new com.jinpaihushi.jphs.order.model.OrderService();
							BeanUtils.copyProperties(orderService, orderInfo);
							orderService.setAppointmentTime(orderInfo.getAppointmentTime());
							orderService.setPrice(orderInfo.getOnePrice());
							orderService.setOrderId(order.getId());
							orderService.setId(UUIDUtils.getId());
							orderService.setSchedule(0);
							orderService.setStatus(0);
							orderServiceService.insert(orderService);
						}
						result.put("orderId", order.getId());
						result.put("orderNo", order.getOrderNo());
						result.put("payParice", orderGoods.getPayPrice());
						result.put("goodsName", orderInfo.getTitle());
						return result;
					}
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取用的个人中心的订单列表
	 * 
	 * @param user
	 *            用户对象
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getUserOrder(Map<String, Object> query) {
		List<Map<String, Object>> userOrder = orderDao.getUserOrder(query);
		return userOrder;
	}

	/**
	 * 查询订单详情
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	@Override
	public Order getUserOrderDetail(String orderId) {
		// 获取订单的基本信息
		Order orderDetail = orderDao.getOrderDetail(orderId);
		// 查询订单的服务情况
		com.jinpaihushi.jphs.order.model.OrderService orderService = new com.jinpaihushi.jphs.order.model.OrderService();
		orderService.setOrderId(orderId);
		orderService.setStatus(0);
		List<com.jinpaihushi.jphs.order.model.OrderService> serviceList = orderServiceService.list(orderService);
		orderDetail.setOrderServiceList(serviceList);
		return orderDetail;
	}

}