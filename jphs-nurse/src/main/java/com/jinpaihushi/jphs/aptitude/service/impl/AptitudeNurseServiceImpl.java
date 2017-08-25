package com.jinpaihushi.jphs.aptitude.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.aptitude.dao.AptitudeNurseDao;
import com.jinpaihushi.jphs.aptitude.model.AptitudeNurse;
import com.jinpaihushi.jphs.aptitude.service.AptitudeNurseService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("aptitudeNurseService")
public class AptitudeNurseServiceImpl extends BaseServiceImpl<AptitudeNurse> implements AptitudeNurseService{

	@Autowired
	private AptitudeNurseDao aptitudeNurseDao;
	
	@Override
	protected BaseDao<AptitudeNurse> getDao(){
		return aptitudeNurseDao;
	}

}