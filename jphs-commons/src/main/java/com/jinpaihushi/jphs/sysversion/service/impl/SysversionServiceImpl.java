package com.jinpaihushi.jphs.sysversion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sysversion.dao.SysversionDao;
import com.jinpaihushi.jphs.sysversion.model.Sysversion;
import com.jinpaihushi.jphs.sysversion.service.SysversionService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-08 10:46:13
 * @version 1.0
 */
@Service("sysversionService")
public class SysversionServiceImpl extends BaseServiceImpl<Sysversion> implements SysversionService{

	@Autowired
	private SysversionDao sysversionDao;
	
	@Override
	protected BaseDao<Sysversion> getDao(){
		return sysversionDao;
	}
	
	public Sysversion appVersion(Integer type){
		return sysversionDao.appVersion(type);
	}

}