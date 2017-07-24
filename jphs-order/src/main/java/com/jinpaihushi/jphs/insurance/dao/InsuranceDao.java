package com.jinpaihushi.jphs.insurance.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.insurance.model.Insurance;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Repository("insuranceDao")
public interface InsuranceDao extends BaseDao<Insurance> {
	
	
	
}
