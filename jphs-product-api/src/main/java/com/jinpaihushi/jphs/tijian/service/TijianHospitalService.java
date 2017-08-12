package com.jinpaihushi.jphs.tijian.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.tijian.model.TijianHospital;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-08 15:34:53
 * @version 1.0
 */
public interface TijianHospitalService extends BaseService<TijianHospital> {

	public List<Map<String,Object>> getAllHospital(String platformId);

}