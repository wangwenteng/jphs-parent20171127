package com.jinpaihushi.jphs.commodity.service;

import java.util.List;

import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-15 13:57:49
 * @version 1.0
 */
public interface CommodityReturnService extends BaseService<CommodityReturn> {

	CommodityReturn getNotStatus(CommodityReturn commodityReturn);
	
	boolean updateReason(CommodityReturn commodityReturn);

	List<CommodityReturn> getListByCoId(String CoId);
	
}