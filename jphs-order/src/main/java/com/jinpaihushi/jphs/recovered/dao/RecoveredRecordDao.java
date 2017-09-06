package com.jinpaihushi.jphs.recovered.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.recovered.model.RecoveredRecord;

/**
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @version 1.0
 */
@Repository("recoveredRecordDao")
public interface RecoveredRecordDao extends BaseDao<RecoveredRecord> {

	List<Map<String, Object>> getHealthLog(Map<String, Object> query);
	
	
	
}
