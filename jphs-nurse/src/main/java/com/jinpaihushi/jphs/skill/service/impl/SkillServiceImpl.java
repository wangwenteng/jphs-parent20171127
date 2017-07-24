package com.jinpaihushi.jphs.skill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.skill.dao.SkillDao;
import com.jinpaihushi.jphs.skill.model.Skill;
import com.jinpaihushi.jphs.skill.service.SkillService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 09:43:42
 * @version 1.0
 */
@Service("skillService")
public class SkillServiceImpl extends BaseServiceImpl<Skill> implements SkillService{

	@Autowired
	private SkillDao skillDao;
	
	@Override
	protected BaseDao<Skill> getDao(){
		return skillDao;
	}

}