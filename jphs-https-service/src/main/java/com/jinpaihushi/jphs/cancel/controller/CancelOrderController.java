package com.jinpaihushi.jphs.cancel.controller;

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
import com.jinpaihushi.jphs.cancel.model.CancelOrder;
import com.jinpaihushi.jphs.cancel.service.CancelOrderService;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;


import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
import java.util.Date;

/**
 * 
 * @author yangsong
 * @date 2017-08-25 16:52:03
 * @version 1.0
 */
@Controller
@RequestMapping(name = "CancelOrder", path = "/cancel/order")
public class CancelOrderController extends BaseController<CancelOrder> {

	@Autowired
	private CancelOrderService cancelOrderService;
	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;

	@Override
	protected BaseService<CancelOrder> getService() {
		return cancelOrderService;
	}

	 
	@RequestMapping(name = "提交退货商品原因", path = "/tkReason.json")
	@ResponseBody
	public byte[] tkReason(CancelOrder co,String coId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.tkReason.json,coId="+coId );
			}

			if (StringUtils.isEmpty(coId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			co.setId(UUID.randomUUID().toString());
			co.setCancelTime(new Date());
			String result = cancelOrderService.insert(co);
			if (result.length() <= 0) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			CommodityOrderInfo coi = new CommodityOrderInfo();

			coi.setId(coId);
			coi.setStatus(-3);
			boolean b = commodityOrderInfoService.update(coi);
			if (!b) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.tkReason.json,coId="+coId,e);
		}
		return null;
	}
	

}
