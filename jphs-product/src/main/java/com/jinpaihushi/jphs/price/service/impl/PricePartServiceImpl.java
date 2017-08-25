package com.jinpaihushi.jphs.price.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.price.service.PricePartService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-08 09:34:35
 * @version 1.0
 */
@Service("pricePartService")
public class PricePartServiceImpl extends BaseServiceImpl<PricePart> implements PricePartService{

	@Autowired
	private PricePartDao pricePartDao;
	
	@Override
	protected BaseDao<PricePart> getDao(){
		return pricePartDao;
	}

}