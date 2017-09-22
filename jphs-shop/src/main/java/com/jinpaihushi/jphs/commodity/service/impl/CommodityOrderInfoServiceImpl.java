package com.jinpaihushi.jphs.commodity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderInfoDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
@Service("commodityOrderInfoService")
public class CommodityOrderInfoServiceImpl extends BaseServiceImpl<CommodityOrderInfo> implements CommodityOrderInfoService{

	@Autowired
	private CommodityOrderInfoDao commodityOrderInfoDao;
	
	@Override
	protected BaseDao<CommodityOrderInfo> getDao(){
		return commodityOrderInfoDao;
	}

	@Override
	public Integer updateByOrderNo(CommodityOrderInfo commodityOrderInfo) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.updateByOrderNo(commodityOrderInfo);
	}

	@Override
	public List<CommodityOrderInfo> getListByCoId(String coId) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.getListByCoId(coId);
	}

	@Override
	public List<CommodityOrderInfo> judgeProfit(CommodityOrderInfo commodityOrderInfo) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.judgeProfit(commodityOrderInfo);
	}

	@Override
	public Integer confimOrder(String comId) {
		 
		Integer result = 0;
		
		List<CommodityOrderInfo> confirmList = commodityOrderInfoDao.getConfirmList(comId);
		
		for (int i = 0; i < confirmList.size(); i++) {
			 
			if(confirmList.get(i).getStatus() == 2){
				result = commodityOrderInfoDao.confimOrder(confirmList.get(i).getId());
			}
		}
		
		
		return result;
	}

	@Override
	public List<CommodityOrderInfo> getList(String coId) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.getList(coId);
	}

	
}