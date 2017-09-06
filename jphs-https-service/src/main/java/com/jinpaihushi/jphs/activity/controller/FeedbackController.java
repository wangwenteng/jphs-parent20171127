package com.jinpaihushi.jphs.activity.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.user.model.Feedback;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.FeedbackService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	@Autowired
	NurseImagesService nurseImagesService;
	
	/*	
	 * http://192.168.7.66:8060/feedback/setFeedback.json?id=20140
		&phone=13341181592&password=220ecdad95054356ba9461c84414b179
		&type=0&authCode=123&content=反馈内容&imgarr=反馈图片字符串数组
		
		token存Header*/
	
	@ResponseBody
	@RequestMapping(name = "/反馈", path = "/setFeedback.json")
	@Transactional
	public byte[] setFeedback(HttpServletRequest req, HttpServletResponse resp,User user,String content,String imgarr){

		try {
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("feedback.setFeedback.json");
			}
			// 查空
			if(StringUtils.isEmpty(content)
						||StringUtils.isEmpty(user.getId())
							||StringUtils.isEmpty(user.getPassword())
								||StringUtils.isEmpty(user.getPhone())/*
									||StringUtils.isEmpty(token)*/){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}

			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}
			Feedback feedBack = new Feedback();
			feedBack.setContent(content);
			feedBack.setStatus(1);
			feedBack.setCreatorId(user.getId());
			feedBack.setCreatorName(user.getName());
			feedBack.setCreateTime(new Date());
			feedBack.setId(UUID.randomUUID().toString());
			String fee_id = feedbackService.insert(feedBack);
			
			if(!StringUtils.isEmpty(imgarr)){
				try {
					String [] imgarr_s = imgarr.split(",");
					for(int a=0;a<imgarr_s.length;a++){
						if(!StringUtils.isEmpty(imgarr_s[a])){
							NurseImages ni = new NurseImages();
							ni.setCreateTime(new Date());
							ni.setType(12);
							ni.setCreatorId(user.getId());
							ni.setCreatorName(user.getName());
							ni.setStatus(1);
							ni.setSourceId(fee_id);
							ni.setUrl(imgarr_s[0]);
							nurseImagesService.insert(ni);
						}
					}
				} catch (Exception e) {
				}
			}
			
			return JSONUtil.toJSONResult(1, "操作成功！", null);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("column.getColumnService.json", e);
		}
		
		return null;
	}
	
}
