package com.jinpaihushi.jphs.information.service;

import java.util.List;

import com.jinpaihushi.jphs.information.model.InformationEvaluate;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
public interface InformationEvaluateService extends BaseService<InformationEvaluate> {

	List<InformationEvaluate> listInfo(InformationEvaluate query,String userId);

	

}