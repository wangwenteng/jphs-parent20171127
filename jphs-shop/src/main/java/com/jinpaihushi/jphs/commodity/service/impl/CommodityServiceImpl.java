package com.jinpaihushi.jphs.commodity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.car.dao.CarDao;
import com.jinpaihushi.jphs.car.model.Car;
import com.jinpaihushi.jphs.commodity.dao.CommodityDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityImagesDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderInfoDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityImages;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.jphs.nurse.dao.NurseCommodityDao;
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
	private CommodityOrderInfoDao commodityOrderInfoDao;
	@Autowired
	private CommodityImagesDao commodityImagesDao;
	@Autowired
	private CarDao carDao;
	
	@Override
	protected BaseDao<Commodity> getDao(){
		return commodityDao;
	}

	@Override
	public List<Commodity> getCommodityList(String columnId,String nurseId,String sort
		 ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("columnId", columnId);
		
		if(sort == ""){
			sort = "";
		}else if("1".equals(sort)){
			sort = " com.create_time";
		}else if("2".equals(sort)){
			sort = " counts";
		}else if("3".equals(sort)){
			sort = " price";
		}else if("4".equals(sort)){
			sort = " price desc";
		}else if("5".equals(sort)){
			sort = " shareNumber";
		} 
		
		if( sort != ""){
			map.put("sort"," order by " + sort);
		}
		
		 

		List<Commodity> list = commodityDao.getShopList(map);
		
		/*if(nurseId != null){
			NurseCommodity nurseCommodity = new NurseCommodity();
			CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
			nurseCommodity.setCreatorId(nurseId);
			for(int i = 0;i < list.size();i++ ){
				commodityOrderInfo.setCommodityId(list.get(i).getId());
				nurseCommodity.setCommodityId(list.get(i).getId());
				Integer browser = nurseCommodityDao.getBrowser(nurseCommodity);
				Integer shareNumber = nurseCommodityDao.getShareNumber(nurseCommodity);
				Integer count = commodityOrderInfoDao.getAllNumberByCommoditById(commodityOrderInfo);
				list.get(i).setBrowser(browser);
				list.get(i).setShareNumber(shareNumber);
				list.get(i).setCount(count);
			}
		}*/
		
		return list;
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
		commodityImages.setStatus(0);
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

		for (int i = 0; i < commodityList.size(); i++) {
			map.put("commodityId", commodityList.get(i).getId());
			Integer count = commodityDao.getSaleCount(map);
			Double money = commodityOrderInfoDao.getMoneyByNurse(map);
			
			commodityList.get(i).setCount(count);
			commodityList.get(i).setMoney(money);
		}
		return commodityList;
	}

	@Override
	public List<Commodity> getNurseSale(String nurseId, String commodityId,String schedule) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nurseId", nurseId);
		map.put("commodityId", commodityId);
		map.put("schedule", schedule);
		List<Commodity> list = commodityDao.getNurseSale(map);
		
		return list;
	}

	@Override
	public List<Commodity> getListByCar(String ids) {
		
		String[] idArr = ids.split(",");
		List<Commodity> list = new ArrayList<Commodity>();
		for (int i = 0; i < idArr.length; i++) {
			Car car = carDao.loadById(idArr[i]);
			String commodityId = car.getCommodityId();
			Commodity com = commodityDao.getInfo(commodityId);
			com.setCount(car.getNumber());
			com.setPrice(car.getNumber()*com.getOldPrice());
			list.add(com);
		}
		
		 
		return list;
	}

	@Override
	public Commodity getOneDetail(String commodityId, String cpId) {
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commodityId", commodityId);
		map.put("cpId", cpId);
		Commodity commodity = commodityDao.getOneDetail(map);
		
		CommodityImages commodityImages = new CommodityImages();
		commodityImages.setType(2);
		commodityImages.setSourceId(commodityId);
		commodityImages.setStatus(0);
		Page<CommodityImages> query = commodityImagesDao.query(commodityImages);
		if(query.size() >0){
			commodity.setDetailUrl(query.get(0).getUrl());
		}
		return commodity;
		
	}

}