package com.jinpaihushi.jphs.evaluation.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:42
 * @version 1.0
 */
public interface EvaluationService extends BaseService<Evaluation> {

	Page<Evaluation> getInfo(Evaluation evaluation);

	Page<Evaluation> getList(Evaluation evaluation);

	Integer getGoodLevel(String goodsId);

	List<Evaluation> listInfo(Evaluation evaluation);

}