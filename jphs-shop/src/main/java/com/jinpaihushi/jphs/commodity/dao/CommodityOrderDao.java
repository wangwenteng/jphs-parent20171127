package com.jinpaihushi.jphs.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
@Repository("commodityOrderDao")
public interface CommodityOrderDao extends BaseDao<CommodityOrder> {

	CommodityOrder getObjectByOrder(Map<String, Object> map);
	
	List<CommodityOrder> getOrderList(Map<String, Object> map);

	CommodityOrder getOrderDetail(String orderId);
	
}
