package com.jinpaihushi.jphs.nurse.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.model.NurseSkills;

/**
 * 
 * @author scj
 * @date 2017-08-16 09:44:28
 * @version 1.0
 */
@Repository("nurseSkillsDao")
public interface NurseSkillsDao extends BaseDao<NurseSkills> {
	
	List<NurseSkills> getNurseSkillsAc(NurseSkills nurseSkills);
	
}
