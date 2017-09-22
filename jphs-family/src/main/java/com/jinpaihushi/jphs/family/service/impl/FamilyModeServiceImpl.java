package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyModeDao;
import com.jinpaihushi.jphs.family.model.FamilyMode;
import com.jinpaihushi.jphs.family.service.FamilyModeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyModeService")
public class FamilyModeServiceImpl extends BaseServiceImpl<FamilyMode> implements FamilyModeService{

	@Autowired
	private FamilyModeDao familyModeDao;
	
	@Override
	protected BaseDao<FamilyMode> getDao(){
		return familyModeDao;
	}

}