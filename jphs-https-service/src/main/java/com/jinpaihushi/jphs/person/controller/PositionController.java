package com.jinpaihushi.jphs.person.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.order.model.OrderInfo;
import com.jinpaihushi.jphs.position.model.Position;
import com.jinpaihushi.jphs.position.service.PositionService;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.ObjectVerification;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/position")
public class PositionController {
	@Autowired
	private PositionService positionService;
	@RequestMapping(path = "/updatePosition.json", name = "用户下单", method = RequestMethod.POST)
	@ResponseBody
	public byte[] updatePosition(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,Position position ) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("order.createOrder.json");
			}
			
			if(!ObjectVerification.verification(position)){
				return JSONUtil.toJSONResult(0, "请核对参数信息", null);
			}
			/*String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User user = (User) req.getSession().getAttribute("user");
			if (user == null)
				user = userService.loadById(orderInfo.getCreatorId());
			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			}*/
			boolean b = positionService.update(position);
			return JSONUtil.toJSONResult(1, "操作成功！", null);
		} catch (Exception e) {
			Util.failLog.error("order.createOrder.json", e);
		}
		return null;
	}
	@RequestMapping(path = "/insertPosition.json", name = "用户下单", method = RequestMethod.POST)
	@ResponseBody
	public byte[] insertPosition(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,Position position ) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("order.createOrder.json");
			}
			
			if(!ObjectVerification.verification(position)){
				return JSONUtil.toJSONResult(0, "请核对参数信息", null);
			}
			/*String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User user = (User) req.getSession().getAttribute("user");
			if (user == null)
				user = userService.loadById(orderInfo.getCreatorId());
			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			}*/
			String b = positionService.insert(position);
			return JSONUtil.toJSONResult(1, "操作成功！", b);
		} catch (Exception e) {
			Util.failLog.error("order.createOrder.json", e);
		}
		return null;
	}
}
