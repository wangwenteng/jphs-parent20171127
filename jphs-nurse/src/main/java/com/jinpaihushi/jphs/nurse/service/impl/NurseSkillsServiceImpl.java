package com.jinpaihushi.jphs.nurse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseSkillsDao;
import com.jinpaihushi.jphs.nurse.model.NurseSkills;
import com.jinpaihushi.jphs.nurse.service.NurseSkillsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-16 09:44:28
 * @version 1.0
 */
@Service("nurseSkillsService")
public class NurseSkillsServiceImpl extends BaseServiceImpl<NurseSkills> implements NurseSkillsService{

	@Autowired
	private NurseSkillsDao nurseSkillsDao;
	
	@Override
	protected BaseDao<NurseSkills> getDao(){
		return nurseSkillsDao;
	}
	
	public List<NurseSkills> getNurseSkillsAc(NurseSkills nurseSkills){
		return nurseSkillsDao.getNurseSkillsAc(nurseSkills);
	}

}