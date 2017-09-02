package com.jinpaihushi.jphs.commodity.controller;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
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
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.logistics.KdniaoTrackQueryAPI;
import com.jinpaihushi.jphs.car.service.CarService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import java.text.SimpleDateFormat;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
import java.util.Date;
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
	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;
	@Autowired
	private CarService carService;

	@Override
	protected BaseService<CommodityOrder> getService() {
		return commodityOrderService;
	}

	@RequestMapping(name = "创建商品订单", path = "/createShopOrder.json")
	@ResponseBody
	public byte[] createShopOrder(
			String carIds ,String userId,String commodityIds,String userAddressId,String cpIds,String guideId,Integer number,String remark,double payPrice) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.createShopOrder.json,userId =" + userId + ",commodityId = "+commodityIds + ",userAddressId = "+userAddressId +",cpId = " + cpIds);
			} 
		 

			if (StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(commodityIds)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(userAddressId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(cpIds)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			 
			String result = commodityOrderService.createCommodityOrder(userId,commodityIds,userAddressId,cpIds,guideId,number,remark,payPrice);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
System.out.println(result);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			if (result.length() <= 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}else{
				if(!(StringUtils.isEmpty(carIds))){
					boolean b = carService.successOrder(carIds);	
					if(!b){
						return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
					}
				}
			}
			
			
			List<CommodityOrder> list = commodityOrderService.getListByOrderNo(result);
			String goodsName = "";
			Set<String> set = new HashSet<String>();
			for (int i = 0;i<list.size();i++ ){
					List<CommodityOrderInfo> coiList = commodityOrderInfoService.getListByCoId(list.get(i).getId());
					for (int j = 0;j<coiList.size();j++ ){
							set.add(coiList.get(j).getTitle());
					}
			}
			 for (String str : set) {  
				  goodsName += "，"+str;
			} 



			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNo",result);
			map.put("goodsName",goodsName.substring(1));
			map.put("payParice",payPrice);
			
			
			return JSONUtil.toJSONResult(1, "操作成功！", map);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.createShopOrder.json,userId =" + userId + ",commodityId = "+commodityIds + ",userAddressId = "+userAddressId +",cpId = " + cpIds , e);
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


	@RequestMapping(name = "提醒发货", path = "/updateRemindTime.json")
	@ResponseBody
	public byte[] updateRemindTime(CommodityOrder commodityOrder) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.updateShopOrderSchedule.json,id =" + commodityOrder.getId());
			}

			if (StringUtils.isEmpty(commodityOrder.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 
			Integer result = commodityOrderService.updateRemindTime(commodityOrder);
			
			if (result < 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.updateRemindTime.json,id =" + commodityOrder.getId() ,e);
		}
		return null;
	}



	@RequestMapping(name = "确认收货", path = "/confimOrder.json")
	@ResponseBody
	public byte[] confimOrder(CommodityOrder commodityOrder) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.confimOrder.json,id =" + commodityOrder.getId());
			}

			if (StringUtils.isEmpty(commodityOrder.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 
			Integer result = commodityOrderService.confimOrder(commodityOrder);
			
			if (result < 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.confimOrder.json,id =" + commodityOrder.getId() ,e);
		}
		return null;
	}



	@RequestMapping(name = "删除订单", path = "/deleteOrder.json")
	@ResponseBody
	public byte[] deleteOrder(CommodityOrder commodityOrder) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.deleteOrder.json,id =" + commodityOrder.getId());
			}

			if (StringUtils.isEmpty(commodityOrder.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 
			Integer result = commodityOrderService.deleteOrder(commodityOrder);
			
			if (result < 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.deleteOrder.json,id =" + commodityOrder.getId() ,e);
		}
		return null;
	}

	@RequestMapping(name = "获取物流信息", path = "/getLogistics.json")
	@ResponseBody
	public byte[] getLogistics(String expCode, String expNo) {

		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.getLogistics.json,expCode =" + expCode + ",expNo="+expNo);
			}

			if (StringUtils.isEmpty(expCode)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(expNo)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 
			String result = api.getOrderTracesByJson("ZTO", "447893110782");

			String replaceAll = result.replaceAll("\"", "");
			// System.out.println(replaceAll);
			String[] split = replaceAll.split("Traces");

			String substring = split[1].substring(3);
			String substring2 = substring.substring(0, substring.length() - 2).trim();
			String substring3 = substring2.substring(0, substring2.length() - 1).trim();
			String[] split2 = substring3.split("},");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			for (int i = 0; i < split2.length; i++) {

				String[] split3 = split2[i].split("AcceptTime:");
				for (int j = 0; j < split3.length; j++) {
					j++;

					String[] split4 = split3[j].split("AcceptStation:");
					for (int k = 0; k < split4.length; k++) {
						Map<String, Object> map = new HashMap<String, Object>();
						date = sdf.parse(split4[0].substring(0, split4[0].trim().length()));
						map.put("AcceptTime", date);
						map.put("AcceptStation", split4[1]);
						list.add(map);
						break;
					}
				}
			}
			if (list.size() == 0) {
				return JSONUtil.toJSONResult(0, "暂无快递信息", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", list );
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.getLogistics.json,expCode =" + expCode + ",expNo="+expNo,e);
		}
		return null;
	}
}
