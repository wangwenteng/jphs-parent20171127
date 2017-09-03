package com.jinpaihushi.jphs.nurse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseInstitutionsDao;
import com.jinpaihushi.jphs.nurse.model.NurseInstitutions;
import com.jinpaihushi.jphs.nurse.service.NurseInstitutionsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-03 13:35:33
 * @version 1.0
 */
@Service("nurseInstitutionsService")
public class NurseInstitutionsServiceImpl extends BaseServiceImpl<NurseInstitutions> implements NurseInstitutionsService{

	@Autowired
	private NurseInstitutionsDao nurseInstitutionsDao;
	
	@Override
	protected BaseDao<NurseInstitutions> getDao(){
		return nurseInstitutionsDao;
	}

}