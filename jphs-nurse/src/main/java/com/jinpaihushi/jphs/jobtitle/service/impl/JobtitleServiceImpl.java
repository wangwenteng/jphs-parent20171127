package com.jinpaihushi.jphs.jobtitle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jobtitle.dao.JobtitleDao;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:00
 * @version 1.0
 */
@Service("jobtitleService")
public class JobtitleServiceImpl extends BaseServiceImpl<Jobtitle> implements JobtitleService{

	@Autowired
	private JobtitleDao jobtitleDao;
	
	@Override
	protected BaseDao<Jobtitle> getDao(){
		return jobtitleDao;
	}

	@Override
	public int checkName(Jobtitle jobtitle) {
		return jobtitleDao.checkName(jobtitle);
	}
	
	public List<Jobtitle> jobtitleSelectList(String type){
		return jobtitleDao.jobtitleSelectList(type);
	}
	
}