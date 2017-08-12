package com.jinpaihushi.jphs.system.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.model.SystemRole;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Repository("systemRoleDao")
public interface SystemRoleDao extends BaseDao<SystemRole> {

	int checkName(SystemRole role);
	
	
	
}
