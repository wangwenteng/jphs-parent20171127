package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyHealthyDao;
import com.jinpaihushi.jphs.family.model.FamilyHealthy;
import com.jinpaihushi.jphs.family.service.FamilyHealthyService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyHealthyService")
public class FamilyHealthyServiceImpl extends BaseServiceImpl<FamilyHealthy> implements FamilyHealthyService{

	@Autowired
	private FamilyHealthyDao familyHealthyDao;
	
	@Override
	protected BaseDao<FamilyHealthy> getDao(){
		return familyHealthyDao;
	}

}