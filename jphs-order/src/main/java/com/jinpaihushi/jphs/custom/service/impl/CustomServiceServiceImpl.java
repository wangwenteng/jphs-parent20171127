package com.jinpaihushi.jphs.custom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.custom.dao.CustomServiceDao;
import com.jinpaihushi.jphs.custom.model.CustomService;
import com.jinpaihushi.jphs.custom.service.CustomServiceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-13 14:40:35
 * @version 1.0
 */
@Service("customServiceService")
public class CustomServiceServiceImpl extends BaseServiceImpl<CustomService> implements CustomServiceService{

	@Autowired
	private CustomServiceDao customServiceDao;
	
	@Override
	protected BaseDao<CustomService> getDao(){
		return customServiceDao;
	}

	@Override
	public Page<CustomService> getList(CustomService customService) {
		// TODO Auto-generated method stub
		return customServiceDao.getList(customService);
	}

}