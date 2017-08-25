package com.jinpaihushi.jphs.nurse.service;

import java.util.List;

import com.jinpaihushi.jphs.nurse.model.NurseSkill;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
public interface NurseSkillService extends BaseService<NurseSkill> {

	List<NurseSkill> queryDetail(NurseSkill nurseSkill);

	boolean updateNurseSkill(NurseSkill nurseSkill);

}