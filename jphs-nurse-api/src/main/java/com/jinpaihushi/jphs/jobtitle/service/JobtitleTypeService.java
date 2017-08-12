package com.jinpaihushi.jphs.jobtitle.service;

import java.util.List;

import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:01
 * @version 1.0
 */
public interface JobtitleTypeService extends BaseService<JobtitleType> {

	List<JobtitleType> queryDetail(Jobtitle jobtitle);

	int checkName(JobtitleType jobtitleType);
	

}