package com.jinpaihushi.jphs.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(path = "/getSessionUser.json", name = "获取session中用户的信息")
	@ResponseBody
	public byte[] getSessionUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.getSessionUser.json");
			}
			User user = (User) hs.getAttribute("user");
			if (user == null) {
				return JSONUtil.toJSONResult(0, "您还没有登录！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", user);
		} catch (Exception e) {
			Util.failLog.error("user.getSessionUser.json", e);
		}
		return null;
	}

	@RequestMapping(path = "/getUserDetail.json", name = "用户个人资料")
	@ResponseBody
	public byte[] getUserDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.getSessionUser.json");
			}
			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			User user = userService.loadById(userId);

			return JSONUtil.toJSONResult(1, "操作成功！", user);
		} catch (Exception e) {
			Util.failLog.error("user.getSessionUser.json", e);
		}
		return null;
	}
	@RequestMapping(path = "/updateUserInfo.json", name = "用户修改个人资料" ,method= RequestMethod.POST)
	@ResponseBody
	public byte[] updateUserInfo(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, User user) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.getSessionUser.json");
			}
			Boolean flag= userService.update(user);
			if(!flag){
				
				return JSONUtil.toJSONResult(0, "操作失败", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", user);
		} catch (Exception e) {
			Util.failLog.error("user.getSessionUser.json", e);
		}
		return null;
	}

}
