package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityImagesDao;
import com.jinpaihushi.jphs.commodity.model.CommodityImages;
import com.jinpaihushi.jphs.commodity.service.CommodityImagesService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:24:03
 * @version 1.0
 */
@Service("commodityImagesService")
public class CommodityImagesServiceImpl extends BaseServiceImpl<CommodityImages> implements CommodityImagesService{

	@Autowired
	private CommodityImagesDao commodityImagesDao;
	
	@Override
	protected BaseDao<CommodityImages> getDao(){
		return commodityImagesDao;
	}

}