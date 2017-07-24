package com.jinpaihushi.jphs.aptitude.controller;

import java.util.UUID;

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
import com.jinpaihushi.jphs.aptitude.model.Aptitude;
import com.jinpaihushi.jphs.aptitude.service.AptitudeService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 08:54:36
 * @version 1.0
 */
@Controller
@RequestMapping(name = "资质管理", path = "/aptitude")
public class AptitudeController extends BaseController<Aptitude> {

	@Autowired
	private AptitudeService aptitudeService;

	@Override
	protected BaseService<Aptitude> getService() {
		return aptitudeService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Aptitude aptitude, Integer p, Integer n) {
		startPage(p, n);
		Page<Aptitude> list = aptitudeService.query(aptitude);
		PageInfos<Aptitude> pageInfo = new PageInfos<Aptitude>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "nurse/aptitude/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Aptitude aptitude = aptitudeService.loadById(id);
		modelMap.put("aptitude", aptitude);
		return "nurse/aptitude/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "nurse/aptitude/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Aptitude aptitude = aptitudeService.loadById(id);
		modelMap.put("aptitude", aptitude);
		return "nurse/aptitude/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Aptitude aptitude) {

		if (aptitude.getId() != null && !aptitude.getId().equals("")) {
			boolean b = aptitudeService.update(aptitude);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/aptitude/err.jhtml";
			}
		} else {
			aptitude.setId(UUID.randomUUID().toString());
			String result = aptitudeService.insert(aptitude);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/aptitude/err.jhtml";
			}
		}
		return "redirect:/aptitude/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = aptitudeService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/aptitude/err.jhtml";
		}

		return "redirect:/aptitude/index.jhtml";
	}
	
	

}
