package com.jinpaihushi.jphs.commodity.dao;

<<<<<<< HEAD
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
	
	Integer getAllNumber(String commodityOrderId);
	
	Integer getAllNumberByCommoditById(CommodityOrderInfo commodityOrderInfo);
	
	Double getMoneyByNurse(Map<String, Object> map);
=======
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
	
	
	
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}
