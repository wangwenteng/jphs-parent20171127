package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderInfoDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
@Service("commodityOrderInfoService")
public class CommodityOrderInfoServiceImpl extends BaseServiceImpl<CommodityOrderInfo> implements CommodityOrderInfoService{

	@Autowired
	private CommodityOrderInfoDao commodityOrderInfoDao;
	
	@Override
	protected BaseDao<CommodityOrderInfo> getDao(){
		return commodityOrderInfoDao;
	}

}