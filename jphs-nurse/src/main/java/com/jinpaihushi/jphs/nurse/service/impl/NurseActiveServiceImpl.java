package com.jinpaihushi.jphs.nurse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseActiveDao;
import com.jinpaihushi.jphs.nurse.model.NurseActive;
import com.jinpaihushi.jphs.nurse.service.NurseActiveService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("nurseActiveService")
public class NurseActiveServiceImpl extends BaseServiceImpl<NurseActive> implements NurseActiveService{

	@Autowired
	private NurseActiveDao nurseActiveDao;
	
	@Override
	protected BaseDao<NurseActive> getDao(){
		return nurseActiveDao;
	}

}