package com.jinpaihushi.jphs.logistics.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.logistics.dao.LogisticsDao;
import com.jinpaihushi.jphs.logistics.model.Logistics;
import com.jinpaihushi.jphs.logistics.service.LogisticsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-09-02 09:26:27
 * @version 1.0
 */
@Service("logisticsService")
public class LogisticsServiceImpl extends BaseServiceImpl<Logistics> implements LogisticsService{

	@Autowired
	private LogisticsDao logisticsDao;
	
	@Override
	protected BaseDao<Logistics> getDao(){
		return logisticsDao;
	}

	@Override
	public Map<String, Object> getLogisticsInfo(String coId) {
		// TODO Auto-generated method stub
		return logisticsDao.getLogisticsInfo(coId);
	}

}