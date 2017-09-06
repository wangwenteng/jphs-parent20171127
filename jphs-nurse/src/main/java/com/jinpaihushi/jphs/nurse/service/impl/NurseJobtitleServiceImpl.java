package com.jinpaihushi.jphs.nurse.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseJobtitleDao;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-16 14:56:07
 * @version 1.0
 */
@Service("nurseJobtitleService")
public class NurseJobtitleServiceImpl extends BaseServiceImpl<NurseJobtitle> implements NurseJobtitleService{

	@Autowired
	private NurseJobtitleDao nurseJobtitleDao;
	
	@Override
	protected BaseDao<NurseJobtitle> getDao(){
		return nurseJobtitleDao;
	}

	@Override
	public List<Map<String, Object>> getNurseList(Map<String, Object> query) {
		return nurseJobtitleDao.getNurseList(query);
	}
	
	public List<NurseJobtitle> getNurseAuditing(Map<String,Object> map){
		return nurseJobtitleDao.getNurseAuditing(map);
	}
	
}