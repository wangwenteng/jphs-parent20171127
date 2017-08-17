package com.jinpaihushi.jphs.skills.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.skills.model.Skills;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-16 09:44:28
 * @version 1.0
 */
public interface SkillsService extends BaseService<Skills> {

	List<Map<String,Object>> getNurseSkills(Map<String,Object> map);

}