package com.jinpaihushi.jphs.system.service;

import java.util.List;

import com.jinpaihushi.jphs.system.model.SystemModule;
import com.jinpaihushi.jphs.system.model.SystemRole;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
public interface SystemRoleService extends BaseService<SystemRole> {

	List<SystemModule> getRoleModule(String roleId);

	int saveRoleModule(String roleId, String moduleIds);

	String initSystemRole(String userId);

	int checkName(SystemRole role);

}