package com.jinpaihushi.jphs.system.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.model.SystemRoleModule;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Repository("systemRoleModuleDao")
public interface SystemRoleModuleDao extends BaseDao<SystemRoleModule> {
	
	
	int deleteByRole(String roleId);
}
