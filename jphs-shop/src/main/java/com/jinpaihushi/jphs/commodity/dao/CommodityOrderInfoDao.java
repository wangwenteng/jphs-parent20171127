package com.jinpaihushi.jphs.commodity.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
@Repository("commodityOrderInfoDao")
public interface CommodityOrderInfoDao extends BaseDao<CommodityOrderInfo> {

	Integer updateByOrderNo(CommodityOrderInfo commodityOrderInfo);
	
	Integer getAllNumber(String id);
	
	Integer getAllNumberByCommoditById(CommodityOrderInfo commodityOrderInfo);
	
	Double getMoneyByNurse(Map<String, Object> map);

	 
}
