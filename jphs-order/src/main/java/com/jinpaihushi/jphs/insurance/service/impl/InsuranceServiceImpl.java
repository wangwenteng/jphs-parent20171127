package com.jinpaihushi.jphs.insurance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.insurance.dao.InsuranceDao;
import com.jinpaihushi.jphs.insurance.model.Insurance;
import com.jinpaihushi.jphs.insurance.service.InsuranceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Service("insuranceService")
public class InsuranceServiceImpl extends BaseServiceImpl<Insurance> implements InsuranceService{

	@Autowired
	private InsuranceDao insuranceDao;
	
	@Override
	protected BaseDao<Insurance> getDao(){
		return insuranceDao;
	}

}