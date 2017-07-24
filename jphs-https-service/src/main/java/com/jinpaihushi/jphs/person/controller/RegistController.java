package com.jinpaihushi.jphs.person.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.jinpaihushi.tools.DoPostSms;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/regist")
public class RegistController {
	@Autowired
	private UserService userService;
	@Autowired
	private VerificationService verificationService;
	@Autowired
	private  AccountService accountService;
	@ResponseBody
	@RequestMapping(name = "用户注册", path = "/user.json")
	public byte[] regist(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, User user,
			String verificattionCode, String type) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("regist.user.json,phone=" + user.getPhone() + " verificattionCode="
						+ verificattionCode + " type=" + type);
			}

			// 查空
			if (StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(verificattionCode)
					|| StringUtils.isEmpty(type)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			// 获取该手机号最新一条短信
			Verification vc = verificationService.getLastRecordByPhone(user.getPhone());
			if (!verificattionCode.equals(vc.getCode())) {
				// 验证码不对
				return JSONUtil.toJSONResult(0, "验证码不正确", null);
			}
			int t = Common.beforeNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vc.getValidTime()));
			if (t == 0) {
				// 无效
				return JSONUtil.toJSONResult(0, "验证码已失效", null);
			} else if (t == -1) {
				// 失败
				return JSONUtil.toJSONResult(0, "判断验证码有效时间失败", null);
			}
			// 设置用户的注册端
			user.setDevice(5);
			// 设置当前注册类型为用户
			user.setType(1);
			// 后期会用
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
			return JSONUtil.toJSONResult(1, "注册成功！", null);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("regist.user.json,phone=" + user.getPhone() + " verificattionCode=" + verificattionCode
					+ " type=" + type, e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(name = "发送验证码", path = "/sendMassage.json")
	public byte[] sendMassage(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("regist.sendMassage.json,phone=" + phone);
			}
			// 查空
			if (phone == null || "".equals(phone)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			String regExp = "^((13)|(14)|(15)|(16)|(17)|(18)|(19))\\d{9}$";
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(phone);
			if (m.equals(false)) {
				return JSONUtil.toJSONResult(0, "请输入有效的手机号！", null);
			}
			int s = (int) ((Math.random() * 9 + 1) * 1000);// 4位验证码
			// 查询该手机号今天发送验证码的次数
			int size = verificationService.countVerification(phone);
			if (size >= 5) {
				
				return JSONUtil.toJSONResult(0, "操作过于频繁，请稍后再试！", null);
			}
			// 发送验证码
			DoPostSms.sendSms(phone, "【金牌护士】您的短信验证码是：" + s + "，10分钟有效！如非本人操作，请及时登录并修改密码。", "SMS_69155344",
					"{\"s\":\"" + s + "\"}");
			Verification vc = new Verification();
			vc.setId(UUIDUtils.getId());
			vc.setPhone(phone);
			Date now = new Date();
			Date now_10 = new Date(now.getTime() + 600000);//加10分钟前的时间
			vc.setValidTime(now_10);
			vc.setCode(s + "");
			vc.setStatus(0);
			String insert = verificationService.insert(vc);
			if (insert.length() < 0) {
				return JSONUtil.toJSONResult(0, "操作失败", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", vc);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("regist.sendMassage.json,phone=" + phone);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(name = "验证手机号是否重复", path = "/distinctPhone.json")
	public byte[] distinctPhone(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("regist.distinctPhone.json,phone=" + phone);
			}

			// 查空
			if (phone == null || "".equals(phone)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			// 查询手机号是否注册
			User user = new User();
			user.setPhone(phone);
			user.setStatus(0);
			user = userService.load(user);
			if (user!=null) {
				return JSONUtil.toJSONResult(0, "手机号已经注册", null);
			}
			return JSONUtil.toJSONResult(1, "该手机号还未注册！", user);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("regist.distinctPhone.json,phone=" + phone, e);
		}
		return null;
	}

}
