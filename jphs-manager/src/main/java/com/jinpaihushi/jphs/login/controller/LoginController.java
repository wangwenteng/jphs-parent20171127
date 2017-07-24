package com.jinpaihushi.jphs.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.jphs.location.service.LocationService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.SystemUserService;
import com.jinpaihushi.utils.MD5;

@Controller
public class LoginController {
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private LocationService locationService;
	@RequestMapping(name = "登录", path = "/login.jhtml")
	public String login(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String userName, String password) {
		try {
			if (StringUtils.isNotEmpty(userName) || StringUtils.isNotEmpty(password)) {
				SystemUser user = systemUserService.getSystemUser(userName);
				if(user!=null){
					//将用户输入的密码进行加密
					password=MD5.md5crypt(MD5.md5crypt(password));
					if(user.getPassword().equals(password)){
						//modelMap.put("message", "登录成功");
						//将用户的信息放到session中
						List<Location> list = locationService.list(null);
						req.getSession().setAttribute("location", list);
						req.getSession().setAttribute("session_user", user);
						//根据用户id获取用户的相关角色以及分配的模块
						List<String> urlList = systemUserService.getUserModule(user.getId());
						//将用户相关的模块放入到session中
						req.getSession().setAttribute("session_url", urlList);
					}else{
						modelMap.put("message", "密码有误，请核对后再操作");
						return "toLogin";
					}
				}else{
					modelMap.put("message", "用户名不存在");
					return "toLogin";
				}
				
			}
		} catch (Exception e) {
			modelMap.put("message", "用户名或密码不正确");
			return "toLogin";
		}
		return "redirect:/welcome/index.jhtml";
	}
	@RequestMapping(name = "退出", path = "/loginOut.jhtml")
	public String loginOut(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap
			) {
		req.getSession().removeAttribute("session_user");
		return "toLogin";
	}
	@RequestMapping(name = "跳转到登录", path = "/toLogin.jhtml")
	public String toLogin(HttpSession hs, HttpServletRequest req, HttpServletResponse resp
			) {
		//纯跳转后登录页面
		return "toLogin";
	}
}
