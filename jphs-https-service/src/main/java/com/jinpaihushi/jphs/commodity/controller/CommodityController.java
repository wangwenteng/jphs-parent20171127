package com.jinpaihushi.jphs.commodity.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:04:04
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Commodity", path = "/commodity")
public class CommodityController extends BaseController<Commodity> {

	@Autowired
	private CommodityService commodityService;

	@Override
	protected BaseService<Commodity> getService() {
		return commodityService;
	}

	@RequestMapping(name = "获取商品列表", path = "/getShopList.json")
	@ResponseBody
	public byte[] getShopList(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			String columnId, Integer p) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getShopList.json,columnId =" + columnId);
			}
			if (StringUtils.isEmpty(columnId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			PageHelper.startPage(p, 2);
			List<Commodity> list = commodityService.getCommodityList(columnId);
			
			PageInfo<Commodity> page = new PageInfo<>(list);
			if (list == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", page);
		} catch (Exception e) {
			Util.failLog.error("commodity.getShopList.json,columnId =" + columnId, e);
		}
		return null;
	}

	
	@RequestMapping(name = "获取商品列表", path = "/getShopDetail.json")
	@ResponseBody
	public byte[] getShopDetail(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			String commodityId,String columnId, Integer p) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getShopDetail.json,commodityId =" + commodityId +",columnId =" + columnId);
			}
			if (StringUtils.isEmpty(commodityId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Commodity commodity = commodityService.getCommodityDetail(columnId,commodityId);
			if (commodity == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", commodity);
		} catch (Exception e) {
			Util.failLog.error("commodity.getShopDetail.json,commodityId =" + commodityId +",columnId = "+columnId, e);
		}
		return null;
	}


	@RequestMapping(name = "护士销售列表", path = "/getSaleByNurse.json")
	@ResponseBody
	public byte[] getSaleByNurse(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			String nurseId, Integer p) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getSaleByNurse.json,nurseId =" + nurseId);
			}
			if (StringUtils.isEmpty(nurseId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Commodity> list = commodityService.getSaleByNurse(nurseId);
			if (list == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("commodity.getSaleByNurse.json,nurseId =" + nurseId, e);
		}
		return null;
	}

}
