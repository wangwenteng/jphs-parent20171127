package com.jinpaihushi.jphs.commodity.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.commodity.service.CommodityReturnService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
@Controller
@RequestMapping(name = "CommodityOrderInfo", path = "/commodity/order/info")
public class CommodityOrderInfoController extends BaseController<CommodityOrderInfo> {

	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;
	@Autowired
	private CommodityOrderService commodityOrderService;
	@Autowired
	private CommodityReturnService commodityReturnService;
	@Override
	protected BaseService<CommodityOrderInfo> getService() {
		return commodityOrderInfoService;
	}

	
	@RequestMapping(name = "123", path = "/test1.json")
	@ResponseBody
	public byte[] test1() throws IOException{
		CommodityOrderInfo coi_up = new CommodityOrderInfo();
		coi_up.setCommodityOrderId("15b1f377-3730-41e0-a497-16b9a5270ce3");
		CommodityOrderInfo coi = commodityOrderInfoService.load(coi_up);
		return JSONUtil.toJSONResult(1, "操作成功！", coi);
	}
	 
	@RequestMapping(name = "获取退货商品的信息", path = "/tkDetail.json")
	@ResponseBody
	public byte[] tkDetail(String coiId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.tkDetail.json,coiId =" + coiId  );
			}

			if (StringUtils.isEmpty(coiId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 
			CommodityOrderInfo coi = commodityOrderInfoService.loadById(coiId);
			
			if (coi == null ) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", coi);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.tkDetail.json,coiId =" + coiId   ,e);
		}
		return null;
	}


	@RequestMapping(name = "提交退货商品原因", path = "/tkReason.json")
	@ResponseBody
	public byte[] tkReason(CommodityOrderInfo coi,String coId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.tkReason.json,coId="+coId );
			}

			if (StringUtils.isEmpty(coId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			coi.setId(UUID.randomUUID().toString());
			String result = commodityOrderInfoService.insert(coi);
			if (result.length() <= 0) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			 
			return JSONUtil.toJSONResult(1, "操作成功！", coi);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.tkReason.json,coId="+coId,e);
		}
		return null;
	}


	
	@RequestMapping(name = "提交退货商品原因", path = "/judgeProfit.json")
	@ResponseBody
	public byte[] judgeProfit(CommodityOrderInfo coi) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.judgeProfit.json,coId="+coi.getUserId() );
			}

			if (StringUtils.isEmpty(coi.getUserId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			
			List<CommodityOrderInfo> coiList = commodityOrderInfoService.judgeProfit(coi);
			if (coiList.size() <= 0) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(1, "请核对参数后访问", coiList);
			}
			 
			return JSONUtil.toJSONResult(1, "操作成功！", coiList);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.judgeProfit.json,coId="+coi.getUserId(),e);
		}
		return null;
	}


	@RequestMapping(name = "获取订单商品", path = "/getCom.json")
	@ResponseBody
	public byte[] getCom(CommodityOrderInfo coi) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.getCom.json,coId="+coi.getCommodityOrderId() );
			}

			if (StringUtils.isEmpty(coi.getCommodityOrderId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			
			List<CommodityOrderInfo> coiList = commodityOrderInfoService.getList(coi.getCommodityOrderId());
			if (coiList.size() <= 0) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			 
			return JSONUtil.toJSONResult(1, "操作成功！", coiList);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.getCom.json,coId="+coi.getCommodityOrderId(),e);
		}
		return null;
	}


	@RequestMapping(name = "单个商品退货原因", path = "/tkOneReason.json")
	@ResponseBody
	public byte[] tkOneReason(CommodityOrderInfo coi,String remark,String reason,String tkFlag,String coId,String schedule,Integer sign,double price) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.tkOneReason.json,coiId="+coi.getId() );
			}

			if (StringUtils.isEmpty(coi.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(coId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(coi.getCreatorId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			boolean b = false;
 
			if(tkFlag.equals("Y")){
				
			   CommodityOrder co = new CommodityOrder();
			   if(schedule.equals("1")){
				   co.setSchedule(-2);
				}else if(schedule.equals("2")){
				   co.setSchedule(-6);
				}
			   co.setId(coId);
			   
			   co.setStatus(0);
			   b = commodityOrderService.update(co);
			}
			  b = commodityOrderInfoService.update(coi);
			  if (!b) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			  CommodityReturn cr = new CommodityReturn();
			  cr.setCommodityOrderInfoId(coi.getId());
			  cr.setReason(reason);
			  cr.setId(UUID.randomUUID().toString());
			  cr.setStatus(1);
			  cr.setSign(sign);
			  cr.setCreateTime(new Date());
			  cr.setCreatorId(coi.getCreatorId());
			  cr.setPrice(price);
			   cr.setRemark(remark);
				String result = commodityReturnService.insert(cr);

			 
			 if (result.length() <= 0) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", 1);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.tkOneReason.json,coiId="+coi.getId(),e);
		}
		return null;
	}
}
