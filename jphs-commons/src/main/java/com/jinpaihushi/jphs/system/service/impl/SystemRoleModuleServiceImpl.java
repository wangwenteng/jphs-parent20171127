package com.jinpaihushi.jphs.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.dao.SystemRoleModuleDao;
import com.jinpaihushi.jphs.system.model.SystemRoleModule;
import com.jinpaihushi.jphs.system.service.SystemRoleModuleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Service("systemRoleModuleService")
public class SystemRoleModuleServiceImpl extends BaseServiceImpl<SystemRoleModule> implements SystemRoleModuleService{

	@Autowired
	private SystemRoleModuleDao systemRoleModuleDao;
	
	@Override
	protected BaseDao<SystemRoleModule> getDao(){
		return systemRoleModuleDao;
	}

}