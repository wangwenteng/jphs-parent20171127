package com.jinpaihushi.jphs.health.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.health.model.HealthLog;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 11:18:42
 * @version 1.0
 */
@Repository("healthLogDao")
public interface HealthLogDao extends BaseDao<HealthLog> {
	
	
	
}
