package com.jinpaihushi.jphs.health.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.health.dao.HealthLogDao;
import com.jinpaihushi.jphs.health.model.HealthLog;
import com.jinpaihushi.jphs.health.service.HealthLogService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 11:18:42
 * @version 1.0
 */
@Service("healthLogService")
public class HealthLogServiceImpl extends BaseServiceImpl<HealthLog> implements HealthLogService{

	@Autowired
	private HealthLogDao healthLogDao;
	
	@Override
	protected BaseDao<HealthLog> getDao(){
		return healthLogDao;
	}

	@Override
	public List<Map<String, Object>> getHealthLog(Map<String, Object> query) {
		return healthLogDao.getHealthLog(query);
	}
	
}