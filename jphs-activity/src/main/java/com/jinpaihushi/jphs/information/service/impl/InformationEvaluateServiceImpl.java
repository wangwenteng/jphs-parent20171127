package com.jinpaihushi.jphs.information.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.information.dao.InformationEvaluateDao;
import com.jinpaihushi.jphs.information.model.InformationEvaluate;
import com.jinpaihushi.jphs.information.service.InformationEvaluateService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Service("informationEvaluateService")
public class InformationEvaluateServiceImpl extends BaseServiceImpl<InformationEvaluate> implements InformationEvaluateService{

	@Autowired
	private InformationEvaluateDao informationEvaluateDao;
	
	@Override
	protected BaseDao<InformationEvaluate> getDao(){
		return informationEvaluateDao;
	}

	@Override
	public List<InformationEvaluate> listInfo(InformationEvaluate query,String userId) {
		return informationEvaluateDao.listInfo(query,userId);
	}
	
}