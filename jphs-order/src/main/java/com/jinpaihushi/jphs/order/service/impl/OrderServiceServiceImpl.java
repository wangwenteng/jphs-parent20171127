package com.jinpaihushi.jphs.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.dao.OrderServiceDao;
import com.jinpaihushi.jphs.order.model.OrderService;
import com.jinpaihushi.jphs.order.service.OrderServiceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:50:13
 * @version 1.0
 */
@Service("orderServiceService")
public class OrderServiceServiceImpl extends BaseServiceImpl<OrderService> implements OrderServiceService{

	@Autowired
	private OrderServiceDao orderServiceDao;
	
	@Override
	protected BaseDao<OrderService> getDao(){
		return orderServiceDao;
	}

	@Override
	public Page<OrderService> getInfo(OrderService orderService) {
		// TODO Auto-generated method stub
		return orderServiceDao.getInfo(orderService);
	}

	@Override
	public boolean updatePatientPhone(OrderService orderServiceInfo) {
		// TODO Auto-generated method stub
		return orderServiceDao.updatePatientPhone(orderServiceInfo);
	}

	 

}