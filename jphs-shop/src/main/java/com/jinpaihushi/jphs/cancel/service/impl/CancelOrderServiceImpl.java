package com.jinpaihushi.jphs.cancel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.cancel.dao.CancelOrderDao;
import com.jinpaihushi.jphs.cancel.model.CancelOrder;
import com.jinpaihushi.jphs.cancel.service.CancelOrderService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-25 16:52:03
 * @version 1.0
 */
@Service("cancelOrderService")
public class CancelOrderServiceImpl extends BaseServiceImpl<CancelOrder> implements CancelOrderService{

	@Autowired
	private CancelOrderDao cancelOrderDao;
	
	@Override
	protected BaseDao<CancelOrder> getDao(){
		return cancelOrderDao;
	}

}