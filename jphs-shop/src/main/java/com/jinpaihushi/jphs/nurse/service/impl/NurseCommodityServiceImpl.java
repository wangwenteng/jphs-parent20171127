package com.jinpaihushi.jphs.nurse.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseCommodityDao;
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.jphs.nurse.service.NurseCommodityService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-14 10:02:02
 * @version 1.0
 */
@Service("nurseCommodityService")
public class NurseCommodityServiceImpl extends BaseServiceImpl<NurseCommodity> implements NurseCommodityService{

	@Autowired
	private NurseCommodityDao nurseCommodityDao;
	
	@Override
	protected BaseDao<NurseCommodity> getDao(){
		return nurseCommodityDao;
	}

	@Override
	public Integer getBrowser(NurseCommodity nurseCommodity) {
		// TODO Auto-generated method stub
		return nurseCommodityDao.getBrowser(nurseCommodity);
	}

	@Override
	public Integer getShareNumber(NurseCommodity nurseCommodity) {
		// TODO Auto-generated method stub
		return nurseCommodityDao.getShareNumber(nurseCommodity);
	}

	@Override
	public boolean updateBrowser(NurseCommodity nurseCommodity) {
		// TODO Auto-generated method stub
		return nurseCommodityDao.updateBrowser(nurseCommodity);
	}

	@Override
	public boolean updateShareNumber(NurseCommodity nurseCommodity) {
		// TODO Auto-generated method stub
		return nurseCommodityDao.updateShareNumber(nurseCommodity);
	}

	@Override
	public boolean updateCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return nurseCommodityDao.updateCount(map);
	}

	 

	 

}