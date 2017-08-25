package com.jinpaihushi.jphs.jobtitle.service;

import java.util.Map;

import com.jinpaihushi.jphs.jobtitle.model.JobtitleGoods;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-09 17:06:12
 * @version 1.0
 */
public interface JobtitleGoodsService extends BaseService<JobtitleGoods> {

	Integer getJobtitleCount(Map<String ,Object> jobtitleGoods);

}