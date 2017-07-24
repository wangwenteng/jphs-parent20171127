package com.jinpaihushi.jphs.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.dao.PlatformSiteDao;
import com.jinpaihushi.jphs.platform.model.PlatformSite;
import com.jinpaihushi.jphs.platform.service.PlatformSiteService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-21 15:30:05
 * @version 1.0
 */
@Service("platformSiteService")
public class PlatformSiteServiceImpl extends BaseServiceImpl<PlatformSite> implements PlatformSiteService{

	@Autowired
	private PlatformSiteDao platformSiteDao;
	
	@Override
	protected BaseDao<PlatformSite> getDao(){
		return platformSiteDao;
	}

}