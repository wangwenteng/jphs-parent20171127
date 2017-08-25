package com.jinpaihushi.jphs.price.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.price.dao.PriceDao;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.service.PriceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-08 09:34:35
 * @version 1.0
 */
@Service("priceService")
public class PriceServiceImpl extends BaseServiceImpl<Price> implements PriceService{

	@Autowired
	private PriceDao priceDao;
	
	@Override
	protected BaseDao<Price> getDao(){
		return priceDao;
	}
	
	public List<Price>  getGoodsPriceDetail(Price p){
		return priceDao.getGoodsPriceDetail(p);
	}

}