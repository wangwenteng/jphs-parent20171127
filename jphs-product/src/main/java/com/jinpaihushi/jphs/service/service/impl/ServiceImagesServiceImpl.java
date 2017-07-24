package com.jinpaihushi.jphs.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.service.dao.ServiceImagesDao;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-06-27 14:35:39
 * @version 1.0
 */
@Service("serviceImagesService")
public class ServiceImagesServiceImpl extends BaseServiceImpl<ServiceImages> implements ServiceImagesService{

	@Autowired
	private ServiceImagesDao serviceImagesDao;
	
	@Override
	protected BaseDao<ServiceImages> getDao(){
		return serviceImagesDao;
	}

}