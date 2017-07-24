package com.jinpaihushi.jphs.system.service;

import java.util.List;

import com.jinpaihushi.jphs.system.model.SystemRole;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
public interface SystemUserService extends BaseService<SystemUser> {

	SystemUser getSystemUser(String userName);

	List<SystemRole> getUserRoles(String userId);

	int saveRoles(String userId, String roleIds);
	
	List<String> getUserModule(String userId);
	
	String initSystemUser();
}