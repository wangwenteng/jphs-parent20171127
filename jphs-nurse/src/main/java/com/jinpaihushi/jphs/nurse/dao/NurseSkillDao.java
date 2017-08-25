package com.jinpaihushi.jphs.nurse.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.model.NurseSkill;

/**
 * 
 * @author wangwt
 * @date 2017-06-30 09:46:28
 * @version 1.0
 */
@Repository("nurseSkillDao")
public interface NurseSkillDao extends BaseDao<NurseSkill> {
	
	List<NurseSkill> queryDetail(NurseSkill nurseSkill);
	
}
