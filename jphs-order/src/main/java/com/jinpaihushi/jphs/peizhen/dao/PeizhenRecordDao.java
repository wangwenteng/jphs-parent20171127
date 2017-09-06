package com.jinpaihushi.jphs.peizhen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.peizhen.model.PeizhenRecord;

/**
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @version 1.0
 */
@Repository("peizhenRecordDao")
public interface PeizhenRecordDao extends BaseDao<PeizhenRecord> {

	List<Map<String, Object>> getHealthLog(Map<String, Object> query);
	
	
	
}
