package com.jinpaihushi.jphs.skills.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.skills.dao.SkillsDao;
import com.jinpaihushi.jphs.skills.model.Skills;
import com.jinpaihushi.jphs.skills.service.SkillsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-16 09:44:28
 * @version 1.0
 */
@Service("skillsService")
public class SkillsServiceImpl extends BaseServiceImpl<Skills> implements SkillsService{

	@Autowired
	private SkillsDao skillsDao;
	
	@Override
	protected BaseDao<Skills> getDao(){
		return skillsDao;
	}

	public List<Map<String,Object>> getNurseSkills(Map<String,Object> map){
		return skillsDao.getNurseSkills(map);
	}
	
}