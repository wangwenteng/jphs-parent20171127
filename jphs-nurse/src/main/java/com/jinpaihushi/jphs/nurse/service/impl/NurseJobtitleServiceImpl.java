package com.jinpaihushi.jphs.nurse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseJobtitleDao;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:01
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

}