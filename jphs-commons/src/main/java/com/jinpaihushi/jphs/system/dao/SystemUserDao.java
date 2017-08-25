package com.jinpaihushi.jphs.system.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.model.SystemUser;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Repository("systemUserDao")
public interface SystemUserDao extends BaseDao<SystemUser> {

	SystemUser getSystemUser(@Param("userName")String userName);
	
	SystemUser getUserRoleModule(SystemUser systemUser);

	int chackUser(@Param("chackValue")String chackValue, @Param("userId")String userId);
	
}
