package com.jinpaihushi.jphs.recovered.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.recovered.dao.RecoveredRecordDao;
import com.jinpaihushi.jphs.recovered.model.RecoveredRecord;
import com.jinpaihushi.jphs.recovered.service.RecoveredRecordService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @version 1.0
 */
@Service("recoveredRecordService")
public class RecoveredRecordServiceImpl extends BaseServiceImpl<RecoveredRecord> implements RecoveredRecordService{

	@Autowired
	private RecoveredRecordDao recoveredRecordDao;
	
	@Override
	protected BaseDao<RecoveredRecord> getDao(){
		return recoveredRecordDao;
	}

	@Override
	public List<Map<String, Object>> getHealthLog(Map<String, Object> query) {
		
		return recoveredRecordDao.getHealthLog(query);
	}
	
}