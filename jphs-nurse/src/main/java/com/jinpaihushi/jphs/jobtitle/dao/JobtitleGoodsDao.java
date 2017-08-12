package com.jinpaihushi.jphs.jobtitle.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleGoods;

/**
 * 
 * @author scj
 * @date 2017-08-09 17:06:12
 * @version 1.0
 */
@Repository("jobtitleGoodsDao")
public interface JobtitleGoodsDao extends BaseDao<JobtitleGoods> {
	
	Integer getJobtitleCount(Map<String ,Object> jobtitleGoods);
	
}
