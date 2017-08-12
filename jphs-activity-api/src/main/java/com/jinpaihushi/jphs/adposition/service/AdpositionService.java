package com.jinpaihushi.jphs.adposition.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.adposition.model.Adposition;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-07 11:47:31
 * @version 1.0
 */
public interface AdpositionService extends BaseService<Adposition> {

	List<Map<String,Object>> getAppindex(Integer type);

}