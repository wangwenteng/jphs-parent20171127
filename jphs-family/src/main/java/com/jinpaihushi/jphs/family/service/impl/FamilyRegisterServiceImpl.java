package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyRegisterDao;
import com.jinpaihushi.jphs.family.model.FamilyRegister;
import com.jinpaihushi.jphs.family.service.FamilyRegisterService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyRegisterService")
public class FamilyRegisterServiceImpl extends BaseServiceImpl<FamilyRegister> implements FamilyRegisterService{

	@Autowired
	private FamilyRegisterDao familyRegisterDao;
	
	@Override
	protected BaseDao<FamilyRegister> getDao(){
		return familyRegisterDao;
	}

}