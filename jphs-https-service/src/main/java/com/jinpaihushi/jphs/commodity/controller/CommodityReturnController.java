package com.jinpaihushi.jphs.commodity.controller;

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
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityReturnService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
/**
 * 
 * @author yangsong
 * @date 2017-08-15 13:57:49
 * @version 1.0
 */
@Controller
@RequestMapping(name = "CommodityReturn", path = "/commodity/return")
public class CommodityReturnController extends BaseController<CommodityReturn> {

	@Autowired
	private CommodityReturnService commodityReturnService;
	@Autowired
	private CommodityOrderService commodityOrderService;
	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;

	@Override
	protected BaseService<CommodityReturn> getService() {
		return commodityReturnService;
	}

	
	@RequestMapping(name = "退货", path = "/returnGoods.json")
	@ResponseBody
	public byte[] returnGoods(CommodityReturn commodityReturn,Integer schedule) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.return.returnGoods.json,orderNoId =" + commodityReturn.getCommodityOrderInfoId()+",schedule = "+schedule);
			}

			if (StringUtils.isEmpty(commodityReturn.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 

			commodityReturn.setId(UUID.randomUUID().toString());
			commodityReturn.setStatus(0);
			commodityReturnService.insert(commodityReturn);
			
			//修改订单表的状态
			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setId(commodityReturn.getCommodityOrderInfoId());
			commodityOrder.setSchedule(schedule);
			commodityOrderService.update(commodityOrder);
			
				
			//修改订单表中商品的状态
			CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
			commodityOrderInfo.setCommodityOrderId(commodityReturn.getCommodityOrderInfoId());	
			commodityOrderInfo.setStatus(-1);
			Integer result = commodityOrderInfoService.updateByOrderNo(commodityOrderInfo);


			if (result < 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
				Util.failLog.error("commodity.return.returnGoods.json,orderNoId =" + commodityReturn.getCommodityOrderInfoId()+",schedule = "+schedule,e);
		}
		return null;
	}
	
	
	@RequestMapping(name = "退货信息", path = "/getInfo.json")
	@ResponseBody
	public byte[] getInfo(String id) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.return.getInfo.json,id =" +id);
			}

			if (StringUtils.isEmpty(id)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		 	 

			 
			CommodityReturn commodityReturn = commodityReturnService.loadById(id);

			if (commodityReturn == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", "");
			}
			return JSONUtil.toJSONResult(1, "操作成功！", commodityReturn);
		} catch (Exception e) {
				Util.failLog.error("commodity.return.getInfo.json,id =" +id,e);
		}
		return null;
	}

	@RequestMapping(name = "退货信息", path = "/updateInfo.json")
	@ResponseBody
	public byte[] updateInfo(CommodityReturn cr) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.return.updateInfo.json,id =" +cr.getId());
			}

			if (StringUtils.isEmpty(cr.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		 	 

			 
			boolean b = commodityReturnService.update(cr);

			if (!b) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", "");
			}
			return JSONUtil.toJSONResult(1, "操作成功！", 1);
		} catch (Exception e) {
				Util.failLog.error("commodity.return.updateInfo.json,id =" +cr.getId(),e);
		}
		return null;
	}

	@RequestMapping(name = "撤销退货信息", path = "/cancelReturn.json")
	@ResponseBody
	public byte[] cancelReturn(String crId,String coiId,Integer status,Integer schedule) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.return.cancelReturn.json,id =" +crId);
			}

			if (StringUtils.isEmpty(crId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		 	 
			CommodityReturn cr = new CommodityReturn();
			cr.setId(crId);
			cr.setStatus(-1);
			
			CommodityOrderInfo coi = new CommodityOrderInfo();
			
			coi.setId(coiId);
			coi.setStatus(status);
			if(schedule == 4){
			coi.setStatus(3);
			}
			boolean b = commodityReturnService.update(cr);
					b = commodityOrderInfoService.update(coi);
			if (!b) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", "");
			}
			return JSONUtil.toJSONResult(1, "操作成功！", 1);
		} catch (Exception e) {
				Util.failLog.error("commodity.return.cancelReturn.json,id =" +crId,e);
		}
		return null;
	}
	
}
