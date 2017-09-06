package com.jinpaihushi.jphs.recovered.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.recovered.model.RecoveredRecord;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @version 1.0
 */
public interface RecoveredRecordService extends BaseService<RecoveredRecord> {

	List<Map<String, Object>> getHealthLog(Map<String, Object> query);

	

}