package com.jinpaihushi.jphs.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.dao.OrderGoodsDao;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.service.OrderGoodsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:29:44
 * @version 1.0
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl extends BaseServiceImpl<OrderGoods> implements OrderGoodsService{

	@Autowired
	private OrderGoodsDao orderGoodsDao;
	
	@Override
	protected BaseDao<OrderGoods> getDao(){
		return orderGoodsDao;
	}

	@Override
	public Integer getOrderCount(OrderGoods orderGoods) {
		// TODO Auto-generated method stub
		return orderGoodsDao.getOrderCount(orderGoods);
	}

}