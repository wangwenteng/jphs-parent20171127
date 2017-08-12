package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityPriceDao;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.commodity.service.CommodityPriceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:20:37
 * @version 1.0
 */
@Service("commodityPriceService")
public class CommodityPriceServiceImpl extends BaseServiceImpl<CommodityPrice> implements CommodityPriceService{

	@Autowired
	private CommodityPriceDao commodityPriceDao;
	
	@Override
	protected BaseDao<CommodityPrice> getDao(){
		return commodityPriceDao;
	}

}