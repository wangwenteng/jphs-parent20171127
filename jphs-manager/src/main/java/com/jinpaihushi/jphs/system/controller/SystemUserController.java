package com.jinpaihushi.jphs.system.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.system.model.SystemRole;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.SystemUserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Controller
@RequestMapping(name = "系统用户管理", path = "/system/user")
public class SystemUserController extends BaseController<SystemUser> {

	@Autowired
	private SystemUserService systemUserService;

	@Override
	protected BaseService<SystemUser> getService() {
		return systemUserService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			SystemUser systemUser, Integer p, Integer n) {
		startPage(p, n);
		Page<SystemUser> list = systemUserService.query(systemUser);
		PageInfos<SystemUser> pageInfo = new PageInfos<SystemUser>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "system/system/user/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SystemUser systemUser = systemUserService.loadById(id);
		modelMap.put("systemUser", systemUser);
		return "system/system/user/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "system/system/user/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SystemUser systemUser = systemUserService.loadById(id);
		modelMap.put("systemUser", systemUser);
		return "system/system/user/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, SystemUser systemUser) {
		SystemUser seesion_user =(SystemUser) req.getSession().getAttribute("session_user");
		if (systemUser.getId() != null && !systemUser.getId().equals("")) {
			boolean b = systemUserService.update(systemUser);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/system/user/err.jhtml";
			}
		} else {
			String newPassword=MD5.md5crypt(MD5.md5crypt(systemUser.getPassword()));
			systemUser.setPassword(newPassword);
			systemUser.setId(UUIDUtils.getId());
			systemUser.setCreateTime(new Date());
			systemUser.setStatus(0);
			systemUser.setCreatorId(seesion_user.getId());
			systemUser.setCreatorName(seesion_user.getName());
			String result = systemUserService.insert(systemUser);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/system/user/err.jhtml";
			}
		}
		return "redirect:/system/user/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		SystemUser user = systemUserService.loadById(id);
		user.setStatus(-1);
		boolean b = systemUserService.update(user);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/system/user/err.jhtml";
		}

		return "redirect:/system/user/index.jhtml";
	}
	@RequestMapping(name = "用户分配角色", path = "/saveRoles.jhtml")
	public String saveRoles(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String userId,String roleIds) {

		int i = systemUserService.saveRoles(userId,roleIds);
		if (i<0) {
			// 跳转到错误页
			return "redirect:/system/user/err.jhtml";
		}

		return "redirect:/system/user/index.jhtml";
	}
	@RequestMapping(name = "查看用户角色", path = "/getUserRoles.jhtml")
	public String getUserRoles(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String userId) {

		List<SystemRole> userRole = systemUserService.getUserRoles(userId);
		modelMap.put("userRole", userRole);
		modelMap.put("userId", userId);
		return "system/system/user/role/edit";
	}

}
