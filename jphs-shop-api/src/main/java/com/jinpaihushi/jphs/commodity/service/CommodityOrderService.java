package com.jinpaihushi.jphs.commodity.service;

<<<<<<< HEAD
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

	String createCommodityOrder(String userId, String commodityId, String userAddressId, String cpId, String guideId,Integer number,String remark);

	String cancelShopOrder(String orderNo);
	
	List<CommodityOrder> getOrderList(String userId, String schedule);

	CommodityOrder getOrderDetail(String orderId);
	
	Integer updateShopOrderSchedule(CommodityOrder commodityOrder);
	
=======
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
public interface CommodityOrderService extends BaseService<CommodityOrder> {

	

>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}