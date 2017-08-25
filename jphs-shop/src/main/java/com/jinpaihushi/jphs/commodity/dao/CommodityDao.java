package com.jinpaihushi.jphs.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
<<<<<<< HEAD
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:04:04
 * @version 1.0
 */
@Repository("commodityDao")
public interface CommodityDao extends BaseDao<Commodity> {
	
	List<Commodity> getShopList(Map<String, Object> map);
	
	Commodity getShopDetail(Map<String, Object> map);

	List<Commodity> getSaleByNurse(Map<String, Object> map);

	Integer getSaleCount(Map<String, Object> map);

	List<Commodity> getNurseSale(Map<String, Object> map);

	Integer updateShopOrderSchedule(CommodityOrder commodityOrder);
=======

/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:04:04
 * @version 1.0
 */
@Repository("commodityDao")
public interface CommodityDao extends BaseDao<Commodity> {
	
	List<Commodity> getShopList(Map<String, Object> map);
	
	Commodity getShopDetail(Map<String, Object> map);

	List<Commodity> getSaleByNurse(Map<String, Object> map);

	Integer getSaleCount(Map<String, Object> map);
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}
