package com.jinpaihushi.jphs.commodity.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityMap;
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

	Commodity getInfo(Map<String, Object> map);

	Commodity getOneDetail(Map<String, Object> map);
	
	List<CommodityMap> getList(Map<String, Object> map);

	List<CommodityMap> getNurseShareList(Map<String, Object> map);

	boolean updateBrowser(String commodityId);

	boolean updateShareNumber(String commodityId);

	boolean updateCount(Map<String, Object> map);

	Page<Commodity> getPageList(Commodity commodity);

}
