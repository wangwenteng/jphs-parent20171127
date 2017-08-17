package com.jinpaihushi.jphs.skills.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.skills.model.Skills;

/**
 * 
 * @author scj
 * @date 2017-08-16 09:44:28
 * @version 1.0
 */
@Repository("skillsDao")
public interface SkillsDao extends BaseDao<Skills> {
	
	List<Map<String,Object>> getNurseSkills(Map<String,Object> map);
	
}
