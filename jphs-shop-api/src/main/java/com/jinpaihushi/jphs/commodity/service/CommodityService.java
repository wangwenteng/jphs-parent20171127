package com.jinpaihushi.jphs.commodity.service;

import java.util.List;

import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:04:04
 * @version 1.0
 */
public interface CommodityService extends BaseService<Commodity> {

<<<<<<< HEAD
	List<Commodity> getCommodityList(String columnId,String nurseId,String sort);
	
	Commodity getCommodityDetail(String columnId,String commodityId);
	
	List<Commodity> getSaleByNurse(String nurseId);

	List<Commodity> getNurseSale(String nurseId,String commodityId,String schedule);
	
=======
	List<Commodity> getCommodityList(String columnId);
	
	Commodity getCommodityDetail(String columnId,String commodityId);
	
	List<Commodity> getSaleByNurse(String nurseId);

>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}