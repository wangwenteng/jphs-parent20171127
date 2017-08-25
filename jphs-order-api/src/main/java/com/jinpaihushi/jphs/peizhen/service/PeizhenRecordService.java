package com.jinpaihushi.jphs.peizhen.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.peizhen.model.PeizhenRecord;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @version 1.0
 */
public interface PeizhenRecordService extends BaseService<PeizhenRecord> {

	List<Map<String, Object>> getHealthLog(Map<String, Object> query);

	

}