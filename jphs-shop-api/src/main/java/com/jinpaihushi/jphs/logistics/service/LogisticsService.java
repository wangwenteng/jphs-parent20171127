package com.jinpaihushi.jphs.logistics.service;

import java.util.Map;

import com.jinpaihushi.jphs.logistics.model.Logistics;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-09-02 09:26:27
 * @version 1.0
 */
public interface LogisticsService extends BaseService<Logistics> {

	Map<String, Object> getLogisticsInfo(String coId);

}