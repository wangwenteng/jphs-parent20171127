package com.jinpaihushi.jphs.logistics.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.logistics.model.Logistics;

/**
 * 
 * @author yangsong
 * @date 2017-09-02 09:26:27
 * @version 1.0
 */
@Repository("logisticsDao")
public interface LogisticsDao extends BaseDao<Logistics> {

	Map<String, Object> getLogisticsInfo(String coId);
	
	
	
}
