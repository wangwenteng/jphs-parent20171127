package com.jinpaihushi.jphs.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
	@Autowired
	private PlatformService platformService;
	@ResponseBody
	@RequestMapping(name = "品类列表" ,path = "/getProductList.json")
	public byte[] getProductList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
			String platformId,String authCode,Integer deviceType){
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("product.getProductList.json,platformId"+platformId+" deviceType="+deviceType+" authCode="+authCode);
			}
			// 查空
			if(StringUtils.isEmpty(platformId)||deviceType==null){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Map<String, Object>> list=platformService.getProductList(platformId,deviceType);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("product.getProductList.json,platformId"+platformId+" deviceType="+deviceType+" authCode="+authCode, e);
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(name = "服务列表" ,path = "/getGoodsList.json")
	public byte[] getGoodsList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
			String platformId,String productId,String authCode,Integer deviceType){
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("product.getGoodsList.json,platformId="+platformId+" productId="+productId+" deviceType="+deviceType+" authCode="+authCode);
			}
			// 查空
			if(StringUtils.isEmpty(platformId)||StringUtils.isEmpty(productId)||deviceType==null){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Map<String, Object>> list=platformService.getGoodsList(platformId,productId,deviceType);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("product.getGoodsList.json,platformId="+platformId+" productId="+productId+" deviceType="+deviceType+" authCode="+authCode, e);
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(name = "二级导航列表" ,path = "/getNavigation.json")
	public byte[] getNavigation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
			String platformId,String authCode,Integer deviceType){
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("product.getNavigation.json,platformId="+platformId+" deviceType="+deviceType+" authCode="+authCode);
			}
			// 查空
			if(StringUtils.isEmpty(platformId)||deviceType==null){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Product> list=platformService.getNavigation(platformId,deviceType);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("product.getNavigation.json,platformId="+platformId+" deviceType="+deviceType+" authCode="+authCode, e);
		}
		return null;
	}
}
