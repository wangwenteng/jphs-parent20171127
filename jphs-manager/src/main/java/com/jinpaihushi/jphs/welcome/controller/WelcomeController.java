package com.jinpaihushi.jphs.welcome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	@RequestMapping(name = "跳转到欢迎页", path = "/index.jhtml")
	public String toLogin(HttpSession hs, HttpServletRequest req, HttpServletResponse resp
			) {
		//纯跳转后登录页面
		return "/welcome/index";
	}
}
