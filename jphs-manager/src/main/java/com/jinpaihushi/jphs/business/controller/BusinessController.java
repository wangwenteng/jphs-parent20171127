package com.jinpaihushi.jphs.business.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.business.service.BusinessService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

/**
 * 
 * @author yangsong
 * @date 2017-09-20 09:15:22
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Business", path = "/business")
public class BusinessController extends BaseController<Business> {

	@Autowired
	private BusinessService businessService;

	@Override
	protected BaseService<Business> getService() {
		return businessService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Business business, Integer p, Integer n) {
		startPage(p, n);
		Page<Business> list = businessService.query(business);
		PageInfos<Business> pageInfo = new PageInfos<Business>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "shop/business/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Business business = businessService.loadById(id);
		modelMap.put("business", business);
		return "shop/business/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "shop/business/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Business business = businessService.loadById(id);
		modelMap.put("business", business);
		return "shop/business/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Business business) {

		 if (business.getId() != null && !business.getId().equals("")) {
			boolean b = businessService.update(business);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/business/err.jhtml";
			}
		} else {
			business.setId(UUID.randomUUID().toString());
			String result = businessService.insert(business);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/business/err.jhtml";
			}
		} 
		 
		return "redirect:/business/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = businessService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/business/err.jhtml";
		}

		return "redirect:/business/index.jhtml";
	}
	
	

}
