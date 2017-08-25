package com.jinpaihushi.jphs.order.service;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.order.model.OrderService;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:50:13
 * @version 1.0
 */
public interface OrderServiceService extends BaseService<OrderService> {

	Page<OrderService> getInfo(OrderService orderService);

	boolean updatePatientPhone(OrderService orderServiceInfo);


}