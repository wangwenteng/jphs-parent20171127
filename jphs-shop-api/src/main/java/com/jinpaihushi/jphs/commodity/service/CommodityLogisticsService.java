package com.jinpaihushi.jphs.commodity.service;

import com.jinpaihushi.jphs.commodity.model.CommodityLogistics;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-09-02 09:23:34
 * @version 1.0
 */
public interface CommodityLogisticsService extends BaseService<CommodityLogistics> {

	CommodityLogistics getInfo(String coId);

}