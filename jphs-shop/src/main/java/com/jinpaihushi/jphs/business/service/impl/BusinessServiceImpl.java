package com.jinpaihushi.jphs.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.business.dao.BusinessDao;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.business.service.BusinessService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 17:28:55
 * @version 1.0
 */
@Service("businessService")
public class BusinessServiceImpl extends BaseServiceImpl<Business> implements BusinessService{

	@Autowired
	private BusinessDao businessDao;
	
	@Override
	protected BaseDao<Business> getDao(){
		return businessDao;
	}

}