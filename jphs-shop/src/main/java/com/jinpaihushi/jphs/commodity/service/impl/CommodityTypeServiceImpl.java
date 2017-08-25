package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityTypeDao;
import com.jinpaihushi.jphs.commodity.model.CommodityType;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:22:31
 * @version 1.0
 */
@Service("commodityTypeService")
public class CommodityTypeServiceImpl extends BaseServiceImpl<CommodityType> implements CommodityTypeService{

	@Autowired
	private CommodityTypeDao commodityTypeDao;
	
	@Override
	protected BaseDao<CommodityType> getDao(){
		return commodityTypeDao;
	}

}