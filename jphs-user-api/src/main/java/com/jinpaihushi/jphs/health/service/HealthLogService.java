package com.jinpaihushi.jphs.health.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.health.model.HealthLog;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 11:18:42
 * @version 1.0
 */
public interface HealthLogService extends BaseService<HealthLog> {
	/**
	 * @param query	
	 * 			type  1=陪诊，2=康复
	 * 			userId  当前用户
	 * 			id health_log id 没有是查询列表 
	 * @return
	 */
	List<Map<String, Object>> getHealthLog(Map<String, Object> query);
}