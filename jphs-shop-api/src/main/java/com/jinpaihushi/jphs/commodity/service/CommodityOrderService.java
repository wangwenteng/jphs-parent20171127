package com.jinpaihushi.jphs.commodity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
public interface CommodityOrderService extends BaseService<CommodityOrder> {

	String createCommodityOrder(String userId, String commodityId, String userAddressId, String cpId, String guideId,Integer number,String remark,double payPrice,String code,Integer device,String platformId);

	String cancelShopOrder(String orderNo);
	
	List<CommodityOrder> getOrderList(String userId, String schedule);

	CommodityOrder getOrderDetail(String orderId);
	
	Integer updateShopOrderSchedule(CommodityOrder commodityOrder);
	
	Integer updateRemindTime(CommodityOrder commodityOrder);
	
	Integer confimOrder(CommodityOrder commodityOrder);
	
	Integer deleteOrder(CommodityOrder commodityOrder);
	
 
	List<CommodityOrder> getListByOrderNo(String OrderNo);
	
 
	List<HashMap<String,Object>> loadS(CommodityOrder commodityOrder);
	
	Integer toUpdatePayPrice(String id,double payPrice);
}