package com.jinpaihushi.jphs.commodity.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityImagesDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityImages;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:04:04
 * @version 1.0
 */
@Service("commodityService")
public class CommodityServiceImpl extends BaseServiceImpl<Commodity> implements CommodityService{

	@Autowired
	private CommodityDao commodityDao;
	@Autowired
	private CommodityImagesDao commodityImagesDao;
	@Override
	protected BaseDao<Commodity> getDao(){
		return commodityDao;
	}

	@Override
	public List<Commodity> getCommodityList(String columnId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("columnId", columnId);
		List<Commodity> commodityList = commodityDao.getShopList(map);
		return commodityList;
	}

	@Override
	public Commodity getCommodityDetail(String columnId,String commodityId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commodityId", commodityId);
		map.put("columnId", columnId);
		Commodity commodity = commodityDao.getShopDetail(map);
		
		CommodityImages commodityImages = new CommodityImages();
		commodityImages.setType(2);
		commodityImages.setSourceId(commodityId);
		Page<CommodityImages> query = commodityImagesDao.query(commodityImages);
		if(query.size() >0){
			commodity.setDetailUrl(query.get(0).getUrl());
		}
		return commodity;
		
	}

	@Override
	public List<Commodity> getSaleByNurse(String nurseId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nurseId", nurseId);
		List<Commodity> commodityList = commodityDao.getSaleByNurse(map);
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM");
		Date date=new Date();
		map.put("currentMonth", dateFormater.format(date));
		for (int i = 0; i < commodityList.size(); i++) {
			map.put("commodityId", commodityList.get(i).getId());
			Integer count = commodityDao.getSaleCount(map);
			commodityList.get(i).setCount(count);
		}
		return commodityList;
	}

}