package com.jinpaihushi.jphs.jobtitle.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jobtitle.dao.JobtitleGoodsDao;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleGoods;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleGoodsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-09 17:06:12
 * @version 1.0
 */
@Service("jobtitleGoodsService")
public class JobtitleGoodsServiceImpl extends BaseServiceImpl<JobtitleGoods> implements JobtitleGoodsService{

	@Autowired
	private JobtitleGoodsDao jobtitleGoodsDao;
	
	@Override
	protected BaseDao<JobtitleGoods> getDao(){
		return jobtitleGoodsDao;
	}
	
	public Integer getJobtitleCount(Map<String ,Object> jobtitleGoods){
		return jobtitleGoodsDao.getJobtitleCount(jobtitleGoods);
	}

}