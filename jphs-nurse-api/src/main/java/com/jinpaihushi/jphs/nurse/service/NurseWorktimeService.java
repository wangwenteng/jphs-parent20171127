package com.jinpaihushi.jphs.nurse.service;

import java.util.List;

import com.jinpaihushi.jphs.nurse.model.NurseWorktime;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 16:32:52
 * @version 1.0
 */
public interface NurseWorktimeService extends BaseService<NurseWorktime> {

	List<NurseWorktime> findWorkTime(String userId, Boolean isNextWeek);

	int updateNurseWorkTime();
	
	int insertNurseWorkTime(String userId);
	
	void updateAllNurseWorkTime();
}