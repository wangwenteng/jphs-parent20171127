package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyConsultationDao;
import com.jinpaihushi.jphs.family.model.FamilyConsultation;
import com.jinpaihushi.jphs.family.service.FamilyConsultationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyConsultationService")
public class FamilyConsultationServiceImpl extends BaseServiceImpl<FamilyConsultation> implements FamilyConsultationService{

	@Autowired
	private FamilyConsultationDao familyConsultationDao;
	
	@Override
	protected BaseDao<FamilyConsultation> getDao(){
		return familyConsultationDao;
	}

}