package com.jinpaihushi.jphs.tijian.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.tijian.dao.TijianHealthLogDao;
import com.jinpaihushi.jphs.tijian.model.TijianHealthLog;
import com.jinpaihushi.jphs.tijian.service.TijianHealthLogService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-08-15 10:36:45
 * @version 1.0
 */
@Service("tijianHealthLogService")
public class TijianHealthLogServiceImpl extends BaseServiceImpl<TijianHealthLog> implements TijianHealthLogService{

	@Autowired
	private TijianHealthLogDao tijianHealthLogDao;
	
	@Override
	protected BaseDao<TijianHealthLog> getDao(){
		return tijianHealthLogDao;
	}

	@Override
	public List<Map<String, Object>> getHealthLog(Map<String, Object> query) {
		return tijianHealthLogDao.getUserHealthLog(query);
	}
	
}