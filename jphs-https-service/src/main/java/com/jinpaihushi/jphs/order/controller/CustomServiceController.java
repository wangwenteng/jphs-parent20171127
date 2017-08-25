package com.jinpaihushi.jphs.order.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.custom.model.CustomService;
import com.jinpaihushi.jphs.custom.service.CustomServiceService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.ObjectVerification;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/customService")
public class CustomServiceController {
	@Autowired
	private CustomServiceService customServiceService;
	@Autowired
	private UserService userService;
	@RequestMapping(path = "/createService.json", name = "定制服务",method=RequestMethod.POST)
	@ResponseBody
	public byte[] createService(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,CustomService customService) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("customService.createService.json");
			}
			if(!ObjectVerification.verification(customService)){
				return JSONUtil.toJSONResult(0, "请核对信息后再提交", null);
			}
			String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User user = (User) req.getSession().getAttribute("user");
			if (user == null)
				user = userService.loadById(customService.getCreatorId());
			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			}
			customService.setId(UUIDUtils.getId());
			customService.setCreateTime(new Date());
			customService.setStatus(0);
			String insert = customServiceService.insert(customService);
			if(insert.length()<0){
				return JSONUtil.toJSONResult(0, "添加失败", null);
			}
			return JSONUtil.toJSONResult(1, "提交成功，我们金牌护士会马上与你联系，请保持手机畅通。", customService);
		} catch (Exception e) {
			Util.failLog.error("customService.createService.json", e);
		}
		return null;
	}
}
