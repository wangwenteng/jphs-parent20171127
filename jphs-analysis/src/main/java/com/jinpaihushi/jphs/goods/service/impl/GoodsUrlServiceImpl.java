package com.jinpaihushi.jphs.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.dao.GoodsUrlDao;
import com.jinpaihushi.jphs.goods.model.GoodsUrl;
import com.jinpaihushi.jphs.goods.service.GoodsUrlService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:42
 * @version 1.0
 */
@Service("goodsUrlService")
public class GoodsUrlServiceImpl extends BaseServiceImpl<GoodsUrl> implements GoodsUrlService{

	@Autowired
	private GoodsUrlDao goodsUrlDao;
	
	@Override
	protected BaseDao<GoodsUrl> getDao(){
		return goodsUrlDao;
	}

}