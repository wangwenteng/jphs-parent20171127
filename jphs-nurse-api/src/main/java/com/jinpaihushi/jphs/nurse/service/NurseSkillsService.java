package com.jinpaihushi.jphs.nurse.service;

import java.util.List;

import com.jinpaihushi.jphs.nurse.model.NurseSkills;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-16 09:44:28
 * @version 1.0
 */
public interface NurseSkillsService extends BaseService<NurseSkills> {

	List<NurseSkills> getNurseSkillsAc(NurseSkills nurseSkills);

}