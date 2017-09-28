package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityLogisticsDao;
import com.jinpaihushi.jphs.commodity.model.CommodityLogistics;
import com.jinpaihushi.jphs.commodity.service.CommodityLogisticsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-09-02 09:23:34
 * @version 1.0
 */
@Service("commodityLogisticsService")
public class CommodityLogisticsServiceImpl extends BaseServiceImpl<CommodityLogistics> implements CommodityLogisticsService{

	@Autowired
	private CommodityLogisticsDao commodityLogisticsDao;
	
	@Override
	protected BaseDao<CommodityLogistics> getDao(){
		return commodityLogisticsDao;
	}

	@Override
	public CommodityLogistics getInfo(String coId) {
		// TODO Auto-generated method stub
		return commodityLogisticsDao.getInfo(coId);
	}

}