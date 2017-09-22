package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyCardDao;
import com.jinpaihushi.jphs.family.model.FamilyCard;
import com.jinpaihushi.jphs.family.service.FamilyCardService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:53
 * @version 1.0
 */
@Service("familyCardService")
public class FamilyCardServiceImpl extends BaseServiceImpl<FamilyCard> implements FamilyCardService{

	@Autowired
	private FamilyCardDao familyCardDao;
	
	@Override
	protected BaseDao<FamilyCard> getDao(){
		return familyCardDao;
	}

}