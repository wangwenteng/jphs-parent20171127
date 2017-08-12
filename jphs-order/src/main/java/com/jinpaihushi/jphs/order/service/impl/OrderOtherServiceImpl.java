package com.jinpaihushi.jphs.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.dao.OrderOtherDao;
import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.jphs.order.service.OrderOtherService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:39:09
 * @version 1.0
 */
@Service("orderOtherService")
public class OrderOtherServiceImpl extends BaseServiceImpl<OrderOther> implements OrderOtherService{

	@Autowired
	private OrderOtherDao orderOtherDao;
	
	@Override
	protected BaseDao<OrderOther> getDao(){
		return orderOtherDao;
	}

	@Override
	public boolean updateDetailAddress(OrderOther orderOther) {
		// TODO Auto-generated method stub
		return orderOtherDao.updateDetailAddress(orderOther);
	}

	 

}