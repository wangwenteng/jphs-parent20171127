package com.jinpaihushi.jphs.nurse.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.model.NurseWorktime;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 16:32:51
 * @version 1.0
 */
@Repository("nurseWorktimeDao")
public interface NurseWorktimeDao extends BaseDao<NurseWorktime> {

	List<String> getAllNurse();
	
	int deleteExpired();

	List<String> getTrialNurseId();
	
}
