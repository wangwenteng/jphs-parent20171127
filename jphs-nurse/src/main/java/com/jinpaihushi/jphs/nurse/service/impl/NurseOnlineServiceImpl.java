package com.jinpaihushi.jphs.nurse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseOnlineDao;
import com.jinpaihushi.jphs.nurse.model.NurseOnline;
import com.jinpaihushi.jphs.nurse.service.NurseOnlineService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("nurseOnlineService")
public class NurseOnlineServiceImpl extends BaseServiceImpl<NurseOnline> implements NurseOnlineService{

	@Autowired
	private NurseOnlineDao nurseOnlineDao;
	
	@Override
	protected BaseDao<NurseOnline> getDao(){
		return nurseOnlineDao;
	}

}