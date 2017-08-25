package com.jinpaihushi.jphs.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.dao.SystemUserRoleDao;
import com.jinpaihushi.jphs.system.model.SystemUserRole;
import com.jinpaihushi.jphs.system.service.SystemUserRoleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Service("systemUserRoleService")
public class SystemUserRoleServiceImpl extends BaseServiceImpl<SystemUserRole> implements SystemUserRoleService{

	@Autowired
	private SystemUserRoleDao systemUserRoleDao;
	
	@Override
	protected BaseDao<SystemUserRole> getDao(){
		return systemUserRoleDao;
	}

}