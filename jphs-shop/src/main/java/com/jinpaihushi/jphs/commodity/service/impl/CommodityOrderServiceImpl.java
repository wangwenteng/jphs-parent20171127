package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
@Service("commodityOrderService")
public class CommodityOrderServiceImpl extends BaseServiceImpl<CommodityOrder> implements CommodityOrderService{

	@Autowired
	private CommodityOrderDao commodityOrderDao;
	
	@Override
	protected BaseDao<CommodityOrder> getDao(){
		return commodityOrderDao;
	}

}