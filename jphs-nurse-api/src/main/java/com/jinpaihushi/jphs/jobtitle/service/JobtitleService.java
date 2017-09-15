package com.jinpaihushi.jphs.jobtitle.service;

import java.util.List;

import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:00
 * @version 1.0
 */
public interface JobtitleService extends BaseService<Jobtitle> {

	int checkName(Jobtitle jobtitle);

	
	List<Jobtitle> jobtitleSelectList(String type);
}