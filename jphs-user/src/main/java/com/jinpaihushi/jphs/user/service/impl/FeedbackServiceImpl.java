package com.jinpaihushi.jphs.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.user.dao.FeedbackDao;
import com.jinpaihushi.jphs.user.model.Feedback;
import com.jinpaihushi.jphs.user.service.FeedbackService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author zhangzd
 * @date 2017-06-27 11:22:41
 * @version 1.0
 */
@Service("feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback> implements FeedbackService{

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Override
	protected BaseDao<Feedback> getDao(){
		return feedbackDao;
	}

}