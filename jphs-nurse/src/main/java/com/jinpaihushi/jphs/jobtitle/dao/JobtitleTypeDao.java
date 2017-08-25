package com.jinpaihushi.jphs.jobtitle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:01
 * @version 1.0
 */
@Repository("jobtitleTypeDao")
public interface JobtitleTypeDao extends BaseDao<JobtitleType> {
	
	/**
	 * 获取职称以及职称类型
	 * @param jobtitle 
	 * @return
	 */
	List<JobtitleType> getJobtitleDetail(@Param("jobtitle")Jobtitle jobtitle);
	int checkName(JobtitleType jobtitleType);
}
