package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityReturnDao;
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.jphs.commodity.service.CommodityReturnService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-15 13:57:49
 * @version 1.0
 */
@Service("commodityReturnService")
public class CommodityReturnServiceImpl extends BaseServiceImpl<CommodityReturn> implements CommodityReturnService{

	@Autowired
	private CommodityReturnDao commodityReturnDao;
	
	@Override
	protected BaseDao<CommodityReturn> getDao(){
		return commodityReturnDao;
	}

}