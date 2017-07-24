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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.system.model.SystemModule;
import com.jinpaihushi.jphs.system.model.SystemRole;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.SystemRoleService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Controller
@RequestMapping(name = "角色管理", path = "/system/role")
public class SystemRoleController extends BaseController<SystemRole> {

	@Autowired
	private SystemRoleService systemRoleService;

	@Override
	protected BaseService<SystemRole> getService() {
		return systemRoleService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			SystemRole systemRole, Integer p, Integer n) {
		startPage(p, n);
		Page<SystemRole> list = systemRoleService.query(systemRole);
		PageInfos<SystemRole> pageInfo = new PageInfos<SystemRole>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "system/system/role/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SystemRole systemRole = systemRoleService.loadById(id);
		modelMap.put("systemRole", systemRole);
		return "system/system/role/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "system/system/role/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SystemRole systemRole = systemRoleService.loadById(id);
		modelMap.put("systemRole", systemRole);
		return "system/system/role/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, SystemRole systemRole) {
		SystemUser systemUser =(SystemUser) req.getSession().getAttribute("session_user");
		if (systemRole.getId() != null && !systemRole.getId().equals("")) {
			boolean b = systemRoleService.update(systemRole);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/system/role/err.jhtml";
			}
		} else {
			systemRole.setId(UUIDUtils.getId());
			systemRole.setCreateTime(new Date());
			systemRole.setStatus(0);
			systemRole.setType(1);
			systemRole.setCreatorId(systemUser.getId());
			systemRole.setCreatorName(systemUser.getName());
			String result = systemRoleService.insert(systemRole);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/system/role/err.jhtml";
			}
		}
		return "redirect:/system/role/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {
		SystemRole role = systemRoleService.loadById(id);
		role.setStatus(-1);
		boolean b = systemRoleService.update(role);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/system/role/err.jhtml";
		}

		return "redirect:/system/role/index.jhtml";
	}
	@RequestMapping(name = "获取角色模块", path = "/getRoleModule.jhtml")
	public String getRoleModule(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String roleId) {
		JSONObject message = new JSONObject();
		List<SystemModule> roleModule= systemRoleService.getRoleModule(roleId);
		message.put("treeData", roleModule);
		modelMap.put("data", message);
		modelMap.put("roleId", roleId);
		return "system/system/role/module/edit";
	}
	@RequestMapping(name = "分配角色模块", path = "/saveRoleModule.jhtml")
	public String saveRoleModule(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String roleId,String moduleIds) {
		int i = systemRoleService.saveRoleModule(roleId,moduleIds);
		if (i<0) {
			// 跳转到错误页
			return "redirect:/system/role/err.jhtml";
		}

		return "redirect:/system/role/index.jhtml";
	}
	

}
