package com.jinpaihushi.jphs.activity.controller;

import java.util.Date;

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
import com.jinpaihushi.jphs.activity.model.Advertising;
import com.jinpaihushi.jphs.activity.service.AdvertisingService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author zhangzd
 * @date 2017-06-27 10:35:22
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Advertising", path = "/advertising")
public class AdvertisingController extends BaseController<Advertising> {

	@Autowired
	private AdvertisingService advertisingService;

	@Override
	protected BaseService<Advertising> getService() {
		return advertisingService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Advertising advertising, Integer p, Integer n) {
		startPage(p, n);
		Page<Advertising> list = advertisingService.query(advertising);
		PageInfos<Advertising> pageInfo = new PageInfos<Advertising>(list, req);
		System.out.println("-------------list:"+list);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		System.out.println("========pageInfo:"+pageInfo);
		return "activity/advertising/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Advertising advertising = advertisingService.loadById(id);
		modelMap.put("advertising", advertising);
		return "activity/advertising/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "activity/advertising/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Advertising advertising = advertisingService.loadById(id);
		modelMap.put("advertising", advertising);
		return "activity/advertising/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Advertising advertising) {
		SystemUser session_user = (SystemUser) req.getSession().getAttribute("session_user");
		if (advertising.getId() != null && !advertising.getId().equals("")) {
			String result= advertisingService.updateAdvertising(advertising);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/advertising/err.jhtml";
			}
		} else {
			advertising.setId(UUIDUtils.getId());
			advertising.setCreateTime(new Date());
			advertising.setCreatorId(session_user.getId());
			advertising.setCreatorName(session_user.getName());
			advertising.setStatus(0);
			String result = advertisingService.insertAdvertising(advertising);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/advertising/err.jhtml";
			}
		}
		return "redirect:/advertising/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = advertisingService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/advertising/err.jhtml";
		}

		return "redirect:/advertising/index.jhtml";
	}
	
	

}
