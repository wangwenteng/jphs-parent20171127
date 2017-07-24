package com.jinpaihushi.jphs.person.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.verification.model.Verification;
import com.jinpaihushi.jphs.verification.service.VerificationService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(path = "/login")
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private VerificationService verificationService;
	@Autowired
	private  AccountService accountService;
	
	@ResponseBody
	@RequestMapping(name = "登录", path = "/login.json")
	public byte[] login(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone,
			String password, String type) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("login.login.json,phone=" + phone + " password=" + password + " type=" + type);
			}

			// 查空
			if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(type)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			int tid = 0; // type
			try {
				tid = Integer.valueOf(type);
			} catch (NumberFormatException e) {
				return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
			}
			User user = new User();
			user.setPhone(phone);
			user.setPassword(password);
			user.setType(tid);
			// 1.根据 name，password,type查询完整信息
			user = userService.findUser(user);

			// 2.错误N种情况判断及返回前端
			if (user == null) {
				return JSONUtil.toJSONResult(0, "用户名或密码不正确！", null);
			}
			// 设置token
			String token = "";
			token = Common.getToken(phone, password);// 生成token
			user.setToken(token);
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "登录成功！", user);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("login.login.json,phone=" + phone + " password=" + password + " type=" + type, e);
		}
		return null;
	}

	/**
	 * {登录注册合并}
	 * 
	 * @param hs
	 * @param req
	 * @param resp
	 * @param phone 手机号
	 * @param validateCode 图片验证码
	 * @param smsCode 短信验证码
	 * @param type 类型 1、用户 2 护士
	 * @return
	 * @author: wangwt
	 */
	@ResponseBody
	@RequestMapping(name = "快捷登录", path = "/quickLogin.json")
	public byte[] quickLogin(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String validateCode,String smsCode, String phone, String type) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("login.quickLogin.json,phone=" + phone + " type=" + type+ " smsCode=" + smsCode+ " verifyCode=" + validateCode);
			}
			//如果session中没有图片验证码并且请求的验证码数据存在 ---非法请求
			if((null == req.getSession().getAttribute("validateCode") || StringUtils.isEmpty(req.getSession().getAttribute("validateCode").toString()))&&StringUtils.isNotEmpty(validateCode)){
				return JSONUtil.toJSONResult(0, "非法请求", null);
			}
			else if(StringUtils.isNotEmpty(validateCode)&&!validateCode.equalsIgnoreCase(req.getSession().getAttribute("validateCode").toString())) {
				return JSONUtil.toJSONResult(0, "图片验证码错误", null);
			}
			// 查空
			if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(type)||StringUtils.isEmpty(smsCode)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			int tid = 0; // type
			try {
				tid = Integer.valueOf(type);
			} catch (NumberFormatException e) {
				return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
			}
			// 获取该手机号最新一条短信
			Verification vc = verificationService.getLastRecordByPhone(phone);
			if(vc==null){
				return JSONUtil.toJSONResult(0, "非法请求", null);
				
			}else{
				if (!smsCode.equals(vc.getCode())) {
					// 验证码不对
					return JSONUtil.toJSONResult(0, "短信验证码不正确", null);
				}
			}
			int t = Common.beforeNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vc.getValidTime()));
			if (t == 0) {
				// 无效
				return JSONUtil.toJSONResult(0, "验证码已失效", null);
			} else if (t == -1) {
				// 失败
				return JSONUtil.toJSONResult(0, "判断验证码有效时间失败", null);
			}
			User user = new User();
			user.setPhone(phone);
			user.setType(tid);
			// 1.根据 name，password,type查询完整信息
			user = userService.findUser(user);
			
			// 2.错误N种情况判断及返回前端
			if (user == null) {
				//如果不存在给用户注册信息
				user = new User();
				user.setPhone(phone);
				user.setType(tid);
				//设置默认密码为手机号
				user.setPassword(MD5.md5crypt(MD5.md5crypt(phone)));
				// 设置用户的注册端
				user.setDevice(5);
				// 设置当前注册类型为用户
				if (StringUtils.isEmpty(user.getName())) {
					user.setName(user.getPhone());
				}
				user.setStatus(0);
				
				//写入数据
				String userId = userService.insertUser(user);
				if(userId.length()<0){
					return JSONUtil.toJSONResult(0, "注册失败", null);
				}
				//给用户创建相应的account账户
				Account account = new Account();
				account.setId(UUIDUtils.getId());
				account.setBalance(0.0);
				account.setScore(0);
				account.setCreateTime(new Date());
				account.setCreatorId(userId);
				account.setStatus(0);
				accountService.insert(account);
			}
			// 设置token
			String token = "";
			token = Common.getToken(phone, smsCode);// 生成token
			user.setToken(token);
			// 3.信息无误，封装信息以及生成token，返回前端
			//将用户信息放到session中
			req.getSession().setAttribute("user", user);
			return JSONUtil.toJSONResult(1, "登录成功！", user);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("login.quickLogin.json,phone=" + phone + " type=" + type+ " smsCode=" + smsCode+ " verifyCode=" + validateCode,e);
		}
		return null;
	}

}