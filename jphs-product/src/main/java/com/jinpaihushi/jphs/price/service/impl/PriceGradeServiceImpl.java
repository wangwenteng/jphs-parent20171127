package com.jinpaihushi.jphs.price.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.price.dao.PriceGradeDao;
import com.jinpaihushi.jphs.price.model.PriceGrade;
import com.jinpaihushi.jphs.price.service.PriceGradeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-15 19:05:47
 * @version 1.0
 */
@Service("priceGradeService")
public class PriceGradeServiceImpl extends BaseServiceImpl<PriceGrade> implements PriceGradeService{

	@Autowired
	private PriceGradeDao priceGradeDao;
	
	@Override
	protected BaseDao<PriceGrade> getDao(){
		return priceGradeDao;
	}

}