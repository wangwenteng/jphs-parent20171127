package com.jinpaihushi.jphs.price.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.price.dao.PriceNurseDao;
import com.jinpaihushi.jphs.price.model.PriceNurse;
import com.jinpaihushi.jphs.price.service.PriceNurseService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-16 15:07:51
 * @version 1.0
 */
@Service("priceNurseService")
public class PriceNurseServiceImpl extends BaseServiceImpl<PriceNurse> implements PriceNurseService{

	@Autowired
	private PriceNurseDao priceNurseDao;
	
	@Override
	protected BaseDao<PriceNurse> getDao(){
		return priceNurseDao;
	}

	@Override
	public List<Map<String, Object>> getServiceItems(String userId) {
		return priceNurseDao.getServiceItems(userId);
	}
	
}