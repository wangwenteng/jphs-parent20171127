package com.jinpaihushi.jphs.jobtitle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jobtitle.dao.JobtitleTypeDao;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleTypeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:01
 * @version 1.0
 */
@Service("jobtitleTypeService")
public class JobtitleTypeServiceImpl extends BaseServiceImpl<JobtitleType> implements JobtitleTypeService{

	@Autowired
	private JobtitleTypeDao jobtitleTypeDao;
	
	@Override
	protected BaseDao<JobtitleType> getDao(){
		return jobtitleTypeDao;
	}

	@Override
	public List<JobtitleType> queryDetail(Jobtitle jobtitle) {
		List<JobtitleType> list = jobtitleTypeDao.getJobtitleDetail(jobtitle);
		return list;
	}

	@Override
	public int checkName(JobtitleType jobtitleType) {
		return jobtitleTypeDao.checkName(jobtitleType);
	}
	
}