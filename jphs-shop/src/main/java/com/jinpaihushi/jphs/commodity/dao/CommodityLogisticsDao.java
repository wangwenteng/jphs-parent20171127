package com.jinpaihushi.jphs.commodity.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.CommodityLogistics;

/**
 * 
 * @author yangsong
 * @date 2017-09-02 09:23:33
 * @version 1.0
 */
@Repository("commodityLogisticsDao")
public interface CommodityLogisticsDao extends BaseDao<CommodityLogistics> {

	CommodityLogistics getInfo(String coId);
	
	
	
}
