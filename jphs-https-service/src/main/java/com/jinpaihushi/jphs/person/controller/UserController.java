package com.jinpaihushi.jphs.person.controller;



import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.model.UserWorktime;
import com.jinpaihushi.jphs.user.service.UserAddressService;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.user.service.UserWorktimeService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private NurseImagesService nurseImagesService;
	@Autowired
	private UserWorktimeService userWorktimeService;
	@Autowired
	private InformationService informationService;
	@Autowired
	private UserAddressService userAddressService;
	@RequestMapping(path = "/getSessionUser.json", name = "获取session中用户的信息")
	@ResponseBody
	public byte[] getSessionUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.getSessionUser.json");
			}
			User user = (User) req.getSession().getAttribute("user");
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
				Util.debugLog.debug("user.getUserDetail.json userId=" + userId);
			}
			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			// 验证token
			String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User user = userService.loadById(userId);
			NurseImages img = new NurseImages();
			img.setSourceId(user.getId());
			img.setType(1);
			img = nurseImagesService.load(img);
			if (img != null)
				user.setHeadPicture(img.getUrl());
			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", user);
		} catch (Exception e) {
			Util.failLog.error("user.getUserDetail.json userId=" + userId, e);
		}
		return null;
	}

	@RequestMapping(path = "/updateUserInfo.json", name = "用户修改个人资料", method = RequestMethod.POST)
	@ResponseBody
	public byte[] updateUserInfo(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, User user) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.updateUserInfo.json");
			}
			String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User seesion = (User) req.getSession().getAttribute("user");
			if (seesion == null)
				seesion = userService.loadById(user.getId());
			boolean flag = Common.CheckPerson(seesion.getPhone(), seesion.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			}
			//
			flag = userService.update(user);
			NurseImages nurseImages = new NurseImages();
			nurseImages.setSourceId(user.getId());
			nurseImages.setType(1);
			nurseImages= nurseImagesService.load(nurseImages);
			nurseImages.setUrl(user.getHeadPicture());
			flag = nurseImagesService.update(nurseImages);
			if (!flag) {

				return JSONUtil.toJSONResult(0, "操作失败", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", user);
		} catch (Exception e) {
			Util.failLog.error("user.updateUserInfo.json", e);
		}
		return null;
	}

	@RequestMapping(path = "/getUserAccount.json", name = "个人账户", method = RequestMethod.POST)
	@ResponseBody
	public byte[] getUserAccount(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.updateUserInfo.json userId=" + userId);
			}
			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User user = (User) req.getSession().getAttribute("user");
			if (user == null)
				user = userService.loadById(userId);
			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			}
			Account account = new Account();
			account.setCreatorId(userId);
			Account result = accountService.load(account);
			if (result == null) {
				return JSONUtil.toJSONResult(0, "操作失败", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("user.updateUserInfo.json userId=" + userId, e);
		}
		return null;
	}

	@RequestMapping(path = "/findWorkTime.json", name = "选择上门时间")
	@ResponseBody
	public byte[] findWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,String productId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.findWorkTime.json userId=" + userId+" productId="+productId);
			}
			if (StringUtils.isEmpty(productId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User user = (User) req.getSession().getAttribute("user");
			if (user == null)
				user = userService.loadById(userId);
			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			}
			List<UserWorktime> result = userWorktimeService.findWorkTime(productId);
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("user.findWorkTime.json userId=" + userId+" productId="+productId, e);
		}
		return null;
	}
	@RequestMapping(path = "/getCollection.json", name = "选择上门时间")
	@ResponseBody
	public byte[] getCollection(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.findWorkTime.json userId=" + userId);
			}
			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			String token = req.getHeader("token");
			if (StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(3, "非法请求", null);
			}
			User user = (User) req.getSession().getAttribute("user");
			if (user == null)
				user = userService.loadById(userId);
			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
			if (!flag) {
				// 身份认证失败,返回错误信息
				return JSONUtil.toJSONResult(2, "身份认证失败", null);
			} 
			List<Map<String,Object>> informationList = informationService.getCollection(userId);
			return JSONUtil.toJSONResult(1, "操作成功！", informationList);
		} catch (Exception e) {
			Util.failLog.error("user.findWorkTime.json userId=" + userId, e);
		}
		return null;
	}
	@RequestMapping(path = "/getReceiveAddress.json", name = "查询收货地址")
	@ResponseBody
	public byte[] getReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,Integer defaultAddress) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.getReceiveAddress.json userId=" + userId + ",defaultAddress = " + defaultAddress );
			}
			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			UserAddress userAddress = new UserAddress();
		
			 
			userAddress.setDefaultAddress(defaultAddress);
	 
			
			userAddress.setCreatorId(userId);
			List<UserAddress> UserAddressList = userAddressService.getReceiveAddress(userAddress);
			 
			if (UserAddressList.size() < 0) {
				return JSONUtil.toJSONResult(0, "操作失败！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", UserAddressList);
		} catch (Exception e) {
			Util.failLog.error("user.getReceiveAddress.json userId = " + userId + ",defaultAddress = " + defaultAddress , e);
		}
		return null;
	}


	@RequestMapping(path = "/insertReceiveAddress.json", name = "添加收货地址")
	@ResponseBody
	public byte[] insertReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,UserAddress userAddress, String userId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.insertReceiveAddress.json userId=" + userId);
			}
			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(userAddress.getDefaultAddress() == null){
				userAddress.setDefaultAddress(0);
			}
			userAddress.setId(UUID.randomUUID().toString());  
			userAddress.setCreatorId(userId);
			String result = userAddressService.insert(userAddress);
			  
			if (result == null ) {
				return JSONUtil.toJSONResult(0, "操作失败！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("user.insertReceiveAddress.json userId=" + userId, e);
		}
		return null;
	}


	@RequestMapping(path = "/deleteReceiveAddress.json", name = "删除收货地址")
	@ResponseBody
	public byte[] deleteReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, UserAddress userAddress) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.deleteReceiveAddress.json id=" + userAddress.getId());
			}
			if (StringUtils.isEmpty(userAddress.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}

			userAddress.setStatus(-1);
			boolean b =  userAddressService.update(userAddress);
			 
			  
			if (!b) {
				return JSONUtil.toJSONResult(0, "操作失败！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", b);
		} catch (Exception e) {
			Util.failLog.error("user.deleteReceiveAddress.json id = " + userAddress.getId(), e);
		}
		return null;
	}


	@RequestMapping(path = "/updateReceiveAddress.json", name = "编辑收货地址")
	@ResponseBody
	public byte[] updateReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,UserAddress userAddress) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.updateReceiveAddress.json " );
			}
			if (StringUtils.isEmpty(userAddress.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}

			
			boolean b =  userAddressService.update(userAddress);
			 
			  
			if (!b) {
				return JSONUtil.toJSONResult(0, "操作失败！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", b);
		} catch (Exception e) {
			Util.failLog.error("user.updateReceiveAddress.json userAddressId " , e);
		}
		return null;
	}

}
