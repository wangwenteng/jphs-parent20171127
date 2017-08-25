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

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;


import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
@Controller
@RequestMapping(name = "CommodityOrder", path = "/commodityOrder")
public class CommodityOrderController extends BaseController<CommodityOrder> {

	@Autowired
	private CommodityOrderService commodityOrderService;

	@Override
	protected BaseService<CommodityOrder> getService() {
		return commodityOrderService;
	}

	@RequestMapping(name = "创建商品订单", path = "/createShopOrder.json")
	@ResponseBody
	public byte[] createShopOrder(
			String userId,String commodityId,String userAddressId,String cpId,String guideId,Integer number,String remark) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.createShopOrder.json,userId =" + userId + ",commodityId = "+commodityId + ",userAddressId = "+userAddressId +",cpId = " + cpId);
			} 
		 

			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(commodityId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(userAddressId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(cpId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			 
			String result = commodityOrderService.createCommodityOrder(userId,commodityId,userAddressId,cpId,guideId,number,remark);
			
			if (result.length() <= 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.createShopOrder.json,userId =" + userId + ",commodityId = "+commodityId + ",userAddressId = "+userAddressId +",cpId = " + cpId , e);
		}
		return null;
	}

	

	@RequestMapping(name = "取消商品订单", path = "/cancelShopOrder.json")
	@ResponseBody
	public byte[] cancelShopOrder(String orderNoId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.cancelShopOrder.json,orderNoId =" + orderNoId);
			}

			if (StringUtils.isEmpty(orderNoId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
			String result = commodityOrderService.cancelShopOrder(orderNoId);
			
			if (result.length() <= 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.cancelShopOrder.json,orderNoId =" + orderNoId ,e);
		}
		return null;
	}

 
	@RequestMapping(name = "获取商品订单", path = "/getOrderList.json")
	@ResponseBody
	public byte[] getOrderList(String userId,String schedule) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.getOrderList.json,userId =" + userId);
			} 

			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			 
			List<CommodityOrder> list = commodityOrderService.getOrderList(userId,schedule);
			
			if (list.size() <= 0) {
				return JSONUtil.toJSONResult(0, "暂无数据", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.getOrderList.json,userId =" + userId ,e);
		}
		return null;
	}


	@RequestMapping(name = "查询商品订单详细", path = "/getOrderDetail.json")
	@ResponseBody
	public byte[] getOrderDetail(String orderId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.getOrderDetail.json,orderId =" + orderId);
			}

			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 

			if (StringUtils.isEmpty(orderId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			 
			CommodityOrder commodityOrder = commodityOrderService.getOrderDetail(orderId);
			
			if (commodityOrder == null) {
				return JSONUtil.toJSONResult(0, "暂无数据", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", commodityOrder);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.getOrderDetail.json,orderId =" + orderId ,e);
		}
		return null;
	}


	@RequestMapping(name = "取消商品订单", path = "/updateShopOrderSchedule.json")
	@ResponseBody
	public byte[] updateShopOrderSchedule(CommodityOrder commodityOrder) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.updateShopOrderSchedule.json,orderNoId =" + commodityOrder.getId()+",schedule = "+commodityOrder.getSchedule());
			}

			if (StringUtils.isEmpty(commodityOrder.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 
			Integer result = commodityOrderService.updateShopOrderSchedule(commodityOrder);
			
			if (result < 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.updateShopOrderSchedule.json,orderNoId =" + commodityOrder.getId()+",schedule = "+commodityOrder.getSchedule() ,e);
		}
		return null;
	}

}
