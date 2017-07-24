package com.jinpaihushi.jphs.aptitude.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.aptitude.dao.AptitudeDao;
import com.jinpaihushi.jphs.aptitude.model.Aptitude;
import com.jinpaihushi.jphs.aptitude.service.AptitudeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("aptitudeService")
public class AptitudeServiceImpl extends BaseServiceImpl<Aptitude> implements AptitudeService{

	@Autowired
	private AptitudeDao aptitudeDao;
	
	@Override
	protected BaseDao<Aptitude> getDao(){
		return aptitudeDao;
	}

}