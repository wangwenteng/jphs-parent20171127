package com.jinpaihushi.jphs.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.dao.PlatformGoodsDao;
import com.jinpaihushi.jphs.platform.model.PlatformGoods;
import com.jinpaihushi.jphs.platform.service.PlatformGoodsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-07-03 16:58:14
 * @version 1.0
 */
@Service("platformGoodsService")
public class PlatformGoodsServiceImpl extends BaseServiceImpl<PlatformGoods> implements PlatformGoodsService{

	@Autowired
	private PlatformGoodsDao platformGoodsDao;
	
	@Override
	protected BaseDao<PlatformGoods> getDao(){
		return platformGoodsDao;
	}

}