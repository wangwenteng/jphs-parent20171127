package com.jinpaihushi.jphs.order.dao;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.model.OrderService;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:50:13
 * @version 1.0
 */
@Repository("orderServiceDao")
public interface OrderServiceDao extends BaseDao<OrderService> {

	Page<OrderService> getInfo(OrderService orderService);

	boolean updatePatientPhone(OrderService orderServiceInfo);

	int deleteByOrderId(String orderId);

	int updateByOrderId(OrderService orderService);
}
