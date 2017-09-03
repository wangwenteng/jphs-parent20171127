package com.jinpaihushi.jphs.wecth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.wecth.dao.WecthDao;
import com.jinpaihushi.jphs.wecth.model.Wecth;
import com.jinpaihushi.jphs.wecth.service.WecthService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author scj
 * @date 2017-09-03 09:50:11
 * @version 1.0
 */
@Service("wecthService")
@Configurable
@EnableScheduling
public class WecthServiceImpl extends BaseServiceImpl<Wecth> implements WecthService{

	@Autowired
	private WecthDao wecthDao;
	
	@Override
	protected BaseDao<Wecth> getDao(){
		return wecthDao;
	}

	@Scheduled(cron = "${GET_WECTH_TOKEN}")
	public void getToken(){
		if (Util.debugLog.isDebugEnabled()) {
            Util.debugLog.debug("定时任务");
        }
	}
	
}