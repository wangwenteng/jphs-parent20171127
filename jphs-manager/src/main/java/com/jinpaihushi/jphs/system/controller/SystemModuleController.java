package com.jinpaihushi.jphs.system.controller;

import java.util.List;
import java.util.UUID;

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
import com.jinpaihushi.jphs.system.service.SystemModuleService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:05
 * @version 1.0
 */
@Controller
@RequestMapping(name = "模块管理", path = "/system/module")
public class SystemModuleController extends BaseController<SystemModule> {

	@Autowired
	private SystemModuleService systemModuleService;

	@Override
	protected BaseService<SystemModule> getService() {
		return systemModuleService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			SystemModule systemModule, Integer p, Integer n) {
		startPage(p, n);
		Page<SystemModule> list = systemModuleService.query(systemModule);
		PageInfos<SystemModule> pageInfo = new PageInfos<SystemModule>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "system/system/module/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SystemModule systemModule = systemModuleService.loadById(id);
		modelMap.put("systemModule", systemModule);
		return "system/system/module/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "system/system/module/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SystemModule systemModule = systemModuleService.loadById(id);
		modelMap.put("systemModule", systemModule);
		return "system/system/module/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, SystemModule systemModule) {

		if (systemModule.getId() != null && !systemModule.getId().equals("")) {
			boolean b = systemModuleService.update(systemModule);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/system/module/err.jhtml";
			}
		} else {
			systemModule.setId(UUID.randomUUID().toString());
			String result = systemModuleService.insert(systemModule);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/system/module/err.jhtml";
			}
		}
		return "redirect:/system/module/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = systemModuleService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/system/module/err.jhtml";
		}

		return "redirect:/system/module/index.jhtml";
	}
	
	
	@RequestMapping(name = "模块树", path = "/moduleTree.jhtml")
	public String moduleTree(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, SystemModule systemModule) {

		JSONObject message = new JSONObject();
		List<SystemModule> moduleTree = systemModuleService.getModuleTree(systemModule);
		message.put("treeData", moduleTree);
		modelMap.put("data", message);
		return "system/system/module/list";
	}
}
