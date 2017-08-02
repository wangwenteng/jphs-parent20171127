package com.jinpaihushi.jphs.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.dao.SystemModuleDao;
import com.jinpaihushi.jphs.system.dao.SystemRoleDao;
import com.jinpaihushi.jphs.system.dao.SystemRoleModuleDao;
import com.jinpaihushi.jphs.system.model.SystemModule;
import com.jinpaihushi.jphs.system.model.SystemRole;
import com.jinpaihushi.jphs.system.model.SystemRoleModule;
import com.jinpaihushi.jphs.system.service.SystemRoleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.MyPredicate;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Service("systemRoleService")
@SuppressWarnings("unchecked")
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRole> implements SystemRoleService {

	@Autowired
	private SystemRoleDao systemRoleDao;

	@Override
	protected BaseDao<SystemRole> getDao() {
		return systemRoleDao;
	}

	@Autowired
	private SystemModuleDao systemModuleDao;
	@Autowired
	private SystemRoleModuleDao systemRoleModuleDao;

	@Override
	public List<SystemModule> getRoleModule(String roleId) {
		// 获取所有的模块
		List<SystemModule> allModule = systemModuleDao.queryList(null);

		// 获取角色已分配的模块
		SystemRoleModule query = new SystemRoleModule();
		query.setSystemRoleId(roleId);
		List<SystemRoleModule> roleHasModule = systemRoleModuleDao.list(query);
		if (roleHasModule.size() != 0) {
			for (SystemModule systemModule : allModule) {
				for (SystemRoleModule systemRoleModule : roleHasModule) {
					if (systemModule.getId().equals(systemRoleModule.getSystemModuleId())) {
						systemModule.setChecked(true);
						if (systemModule.getParentId().equals("0")) {
							systemModule.setState("open");
						}
					}
				}
			}
		}
		Predicate predicate = new MyPredicate("parentId", "0");
		List<SystemModule> parentList = (List<SystemModule>) CollectionUtils.select(allModule, predicate);
		for (SystemModule parentModule : parentList) {
			// 根据一级菜单的id获取到二级菜单
			Predicate childrenPredicate = new MyPredicate("parentId", parentModule.getId());
			List<SystemModule> childrenList = (List<SystemModule>) CollectionUtils.select(allModule, childrenPredicate);
			parentModule.setState("open");
			parentModule.setChildren(childrenList);
		}
		return parentList;
	}

	@Override
	public int saveRoleModule(String roleId, String moduleIds) {
		String[] moduleId = moduleIds.split(",");
		// 根据角色id获取已经分配的模块
		systemRoleModuleDao.deleteByRole(roleId);
		SystemRoleModule insert = null;
		List<SystemRoleModule> list = new ArrayList<>();
		for (int i = 0; i < moduleId.length; i++) {
			insert = new SystemRoleModule();
			insert.setSystemRoleId(roleId);
			insert.setSystemModuleId(moduleId[i]);
			list.add(insert);
		}
		return systemRoleModuleDao.inserts(list);
	}

	@Override
	public String initSystemRole(String userId) {
		SystemRole systemRole = new SystemRole();
		systemRole.setId(UUIDUtils.getId());
		systemRole.setName("超级管理员");
		systemRole.setDescribe("系统初始化角色");
		systemRole.setType(0);
		systemRole.setCreateTime(new Date());
		systemRole.setCreatorId(userId);
		systemRole.setCreatorName("超级管理员");
		systemRole.setStatus(0);
		int i = systemRoleDao.insert(systemRole);
		if(i>0) return systemRole.getId();
		return "";
	}
	
}