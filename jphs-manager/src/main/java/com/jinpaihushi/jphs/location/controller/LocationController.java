package com.jinpaihushi.jphs.location.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.jphs.location.service.LocationService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 11:52:33
 * @version 1.0
 */
@Controller
@RequestMapping(name = "区域", path = "/location")
public class LocationController extends BaseController<Location> {

	@Autowired
	private LocationService locationService;

	@Override
	protected BaseService<Location> getService() {
		return locationService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Location location, Integer p, Integer n) {
		startPage(p, n);
		Page<Location> list = locationService.query(location);
		PageInfos<Location> pageInfo = new PageInfos<Location>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "product/location/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Location location = locationService.loadById(id);
		modelMap.put("location", location);
		return "product/location/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "product/location/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Location location = locationService.loadById(id);
		modelMap.put("location", location);
		return "product/location/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Location location) {

		if (location.getId() != null && !location.getId().equals("")) {
			boolean b = locationService.update(location);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/location/err.jhtml";
			}
		} else {
			location.setId(UUID.randomUUID().toString());
			String result = locationService.insert(location);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/location/err.jhtml";
			}
		}
		return "redirect:/location/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = locationService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/location/err.jhtml";
		}

		return "redirect:/location/index.jhtml";
	}
	
	/**
	 * 
	 * @Title: getAllProvince @Description: 获取所有省份列表 @param @return 设定文件 @return
	 * JSONObject 返回类型 @throws
	 */
	@RequestMapping(name = "获取省市区树", path = "/getAllLocation.jhtml")
	@ResponseBody
	public JSONObject getAllLocation(HttpServletRequest req,String nurseId) {
		JSONObject message = new JSONObject();
		try {
			//获取所有省市区的信息
			@SuppressWarnings("unchecked")
			List<Location> location=(List<Location>)req.getSession().getAttribute("location");
			List<Location> list = locationService.getEasyTreeData(location,nurseId);
			message.put("data", list);
			message.put("result", 1);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			message.put("result", 0);
		}
		message.put("result", 0);
		return message;
	}
}
