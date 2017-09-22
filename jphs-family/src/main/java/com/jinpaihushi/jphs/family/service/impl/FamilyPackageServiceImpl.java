package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyPackageDao;
import com.jinpaihushi.jphs.family.model.FamilyPackage;
import com.jinpaihushi.jphs.family.service.FamilyPackageService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyPackageService")
public class FamilyPackageServiceImpl extends BaseServiceImpl<FamilyPackage> implements FamilyPackageService{

	@Autowired
	private FamilyPackageDao familyPackageDao;
	
	@Override
	protected BaseDao<FamilyPackage> getDao(){
		return familyPackageDao;
	}

}