package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityEvaluationDao;
import com.jinpaihushi.jphs.commodity.model.CommodityEvaluation;
import com.jinpaihushi.jphs.commodity.service.CommodityEvaluationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:09:47
 * @version 1.0
 */
@Service("commodityEvaluationService")
public class CommodityEvaluationServiceImpl extends BaseServiceImpl<CommodityEvaluation> implements CommodityEvaluationService{

	@Autowired
	private CommodityEvaluationDao commodityEvaluationDao;
	
	@Override
	protected BaseDao<CommodityEvaluation> getDao(){
		return commodityEvaluationDao;
	}

}