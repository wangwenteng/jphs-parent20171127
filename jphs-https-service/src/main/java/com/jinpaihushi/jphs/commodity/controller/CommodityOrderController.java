package com.jinpaihushi.jphs.commodity.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.car.service.CarService;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.jphs.nurse.service.NurseCommodityService;
import com.jinpaihushi.logistics.KdniaoTrackQueryAPI;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
	@Autowired
	private NurseCommodityService nurseCommodityService;
	@Autowired
	private CommodityService commodityService;

	@Override
	protected BaseService<CommodityOrder> getService() {
		return commodityOrderService;
	}

	@RequestMapping(name = "创建商品订单", path = "/createShopOrder.json")
	@ResponseBody
	public byte[] createShopOrder(
			String carIds ,String userId,String commodityIds,String userAddressId,String cpIds,String guideId,Integer number,String remark,double payPrice,String code,Integer device,String platformId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.createShopOrder.json,userId =" + userId + ",commodityId = "+commodityIds + ",userAddressId = "+userAddressId +",cpId = " + cpIds);
			} 
		 

System.out.println("===================================");
								System.out.println(device);

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
			
			 
			String result = commodityOrderService.createCommodityOrder(userId,commodityIds,userAddressId,cpIds,guideId,number,remark,payPrice,code,device,platformId);
			
 
			
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
			
			Set<String> set = new LinkedHashSet<String>();
			for (int i = 0;i<list.size();i++ ){
				double money = 0.0;
					List<CommodityOrderInfo> coiList = commodityOrderInfoService.getListByCoId(list.get(i).getId());
					for (int j = 0;j<coiList.size();j++ ){
						set.add(coiList.get(j).getTitle());
						money += coiList.get(j).getNumber() * coiList.get(j).getPrice();
					}
				 commodityOrderService.toUpdatePayPrice(list.get(i).getId(),money);
			}
			 for (String str : set) {  
				  goodsName += ","+str;
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
				result = commodityOrderInfoService.confimOrder(commodityOrder.getId());
				
			if (result < 0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
				
				CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
				commodityOrderInfo.setCommodityId(commodityOrder.getId());
				List<CommodityOrderInfo> list = commodityOrderInfoService.getList(commodityOrder.getId());

				for (int i = 0; i<list.size();i++ ){
					
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("commodityId",list.get(i).getCommodityId());
						map.put("number",list.get(i).getNumber());
						
						commodityService.updateCount(map);

					/*if(list.get(i).getUserId()!=null){
						map.put("creatorId",list.get(i).getUserId());
						nurseCommodityService.updateCount(map);
					}*/
                    
					
					
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
			String result = api.getOrderTracesByJson(expCode, expNo);
			/*String replaceAll = result.replaceAll("\"", "");
			// System.out.println(replaceAll);
			String[] split = replaceAll.split("Traces");

			String substring = split[1].substring(3);
			String substring2 = substring.substring(0, substring.length() - 2).trim();
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				if(substring2.length() >0){
			String substring3 = substring2.substring(0, substring2.length() - 1).trim();
			String[] split2 = substring3.split("},");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			 
			
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
				}*/
		JSONArray arry = JSONObject.fromObject(result).getJSONArray("Traces");
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < arry.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String AcceptStation = arry.getJSONObject(i).getString("AcceptStation") ;
				String AcceptTime = arry.getJSONObject(i).getString("AcceptTime") ;
				map.put("AcceptTime", AcceptTime);
				map.put("AcceptStation", AcceptStation);
				list.add(map);
			}

			if (list.size() == 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("AcceptTime", new Date());
				map.put("AcceptStation", "暂无快递信息");
				return JSONUtil.toJSONResult(0, "操作成功！", map);
			}
			 
			return JSONUtil.toJSONResult(1, "操作成功！", list );
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.getLogistics.json,expCode =" + expCode + ",expNo="+expNo,e);
		}
		return null;
	}

	@RequestMapping(name = "根据订单号查询当前状态", path = "/getStatusByOrderNo.json")
	@ResponseBody
	public byte[] getStatusByOrderNo(String orderNo) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.getStatusByOrderNo.json,orderNo =" + orderNo);
			}

			if (StringUtils.isEmpty(orderNo)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		  	 
			Integer result = 0 ;
			List<CommodityOrder> list = commodityOrderService.getStatusByOrderNo(orderNo);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.getStatusByOrderNo.json,list =" + list);
			}
			if (list == null || list.size() ==0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			boolean flag = true;
			if(list.size() > 0){
				for (int i = 0; i<list.size();i++ ){
					if(list.get(i).getSchedule() != 1){
						flag = false ;
						break;
					}
				}
			}
			
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.getStatusByOrderNo.json,flag =" + flag);
			}
			if (flag){
				result = 1;
			}
			
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.getStatusByOrderNo.json,orderNo =" + orderNo ,e);
		}
		return null;
	}
}
