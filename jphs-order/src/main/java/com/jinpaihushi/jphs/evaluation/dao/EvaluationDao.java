package com.jinpaihushi.jphs.evaluation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:42
 * @version 1.0
 */
@Repository("evaluationDao")
public interface EvaluationDao extends BaseDao<Evaluation> {

	Page<Evaluation> getInfo(Evaluation evaluation);

	Page<Evaluation> getList(Evaluation evaluation);

	int getGoodsLevel(String goodsId);
	
	List<Evaluation> listInfo(Evaluation evaluation);
	
}
