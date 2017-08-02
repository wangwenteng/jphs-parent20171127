package com.jinpaihushi.jphs.evaluation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.evaluation.dao.EvaluationDao;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;
import com.jinpaihushi.jphs.evaluation.service.EvaluationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:42
 * @version 1.0
 */
@Service("evaluationService")
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation> implements EvaluationService{

	@Autowired
	private EvaluationDao evaluationDao;
	
	@Override
	protected BaseDao<Evaluation> getDao(){
		return evaluationDao;
	}

	@Override
	public Page<Evaluation> getInfo(Evaluation evaluation) {
		// TODO Auto-generated method stub
		Page<Evaluation> list = evaluationDao.getInfo(evaluation);
		return list;
	}

	@Override
	public Page<Evaluation> getList(Evaluation evaluation) {
		// TODO Auto-generated method stub
		return evaluationDao.getList(evaluation);
	}

	@Override
	public Integer getGoodLevel(String goodsId) {
		int i=evaluationDao.getGoodsLevel(goodsId);
		return i;
	}

	@Override
	public List<Evaluation> listInfo(Evaluation evaluation) {
		return evaluationDao.listInfo(evaluation);
	}
	
}