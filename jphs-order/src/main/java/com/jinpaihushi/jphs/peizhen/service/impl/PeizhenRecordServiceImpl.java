package com.jinpaihushi.jphs.peizhen.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.peizhen.dao.PeizhenRecordDao;
import com.jinpaihushi.jphs.peizhen.model.PeizhenRecord;
import com.jinpaihushi.jphs.peizhen.service.PeizhenRecordService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @version 1.0
 */
@Service("peizhenRecordService")
public class PeizhenRecordServiceImpl extends BaseServiceImpl<PeizhenRecord> implements PeizhenRecordService{

	@Autowired
	private PeizhenRecordDao peizhenRecordDao;
	
	@Override
	protected BaseDao<PeizhenRecord> getDao(){
		return peizhenRecordDao;
	}

	@Override
	public List<Map<String, Object>> getHealthLog(Map<String, Object> query) {
		return peizhenRecordDao.getHealthLog(query);
	}
	
}