package com.jinpaihushi.jphs.user.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.user.model.Feedback;

/**
 * 
 * @author zhangzd
 * @date 2017-06-27 11:22:41
 * @version 1.0
 */
@Repository("feedbackDao")
public interface FeedbackDao extends BaseDao<Feedback> {
	
	
	
}
