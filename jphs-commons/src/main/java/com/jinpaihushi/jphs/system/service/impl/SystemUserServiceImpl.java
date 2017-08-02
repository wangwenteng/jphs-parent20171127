package com.jinpaihushi.jphs.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.dao.SystemRoleDao;
import com.jinpaihushi.jphs.system.dao.SystemUserDao;
import com.jinpaihushi.jphs.system.dao.SystemUserRoleDao;
import com.jinpaihushi.jphs.system.model.SystemModule;
import com.jinpaihushi.jphs.system.model.SystemRole;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.model.SystemUserRole;
import com.jinpaihushi.jphs.system.service.SystemUserService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Service("systemUserService")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser> implements SystemUserService{

	@Autowired
	private SystemUserDao systemUserDao;
	
	@Override
	protected BaseDao<SystemUser> getDao(){
		return systemUserDao;
	}
	@Autowired
	private SystemRoleDao systemRoleDao;
	@Autowired
	private SystemUserRoleDao systemUserRoleDao;
	@Override
	public SystemUser getSystemUser(String userName) {
		SystemUser user=systemUserDao.getSystemUser(userName);
		//获取用户的角色
		//user=systemUserDao.getUserRoleModule(user);
		return user;
	}

	@Override
	public List<SystemRole> getUserRoles(String userId) {
		//获取所有的角色
		SystemRole systemRole =new SystemRole();
		systemRole.setStatus(0);
		List<SystemRole> allRole = systemRoleDao.list(systemRole);
		//获取用户所有的角色
		SystemUserRole query =new SystemUserRole();
		query.setSystemUserId(userId);
		List<SystemUserRole> userHasRole = systemUserRoleDao.list(query);
		for (SystemRole allrole : allRole) {
			for (SystemUserRole systemUserRole : userHasRole) {
				if(allrole.getId().equals(systemUserRole.getSystemRoleId())){
					allrole.setChecked(true);
					break;
				}
			}
		}
		return allRole;
	}

	@Override
	@Transactional
	public int saveRoles(String userId, String roleIds) {
		int j=0;
		//按条件分割出角色id
		//根据用户id获取用户有的角色
		//获取用户所有的角色
		SystemUserRole query =new SystemUserRole();
		query.setSystemUserId(userId);
		List<SystemUserRole> userHasRole = systemUserRoleDao.list(query);
		for (SystemUserRole systemUserRole : userHasRole) {
			systemUserRoleDao.deleteById(systemUserRole.getId());
		}
		if(roleIds!=null){
			String[] roleId=roleIds.split(",");
			SystemUserRole insert =null;
			List<SystemUserRole> list =new ArrayList<>();
			for (int i = 0; i < roleId.length; i++) {
				insert =new SystemUserRole();
				insert.setSystemUserId(userId);
				insert.setSystemRoleId(roleId[i]);
				insert.setId(UUIDUtils.getId());
				list.add(insert);
			}
			j=systemUserRoleDao.inserts(list);
		}
		return j;
	}

	@Override
	public List<String> getUserModule(String userId) {
		List<String> list = new ArrayList<>();
		SystemUser systemUser =new SystemUser();
		systemUser.setId(userId);
		SystemUser user = systemUserDao.getUserRoleModule(systemUser);
		if(user!=null){
			for (SystemRole role : user.getRoleList()) {
				for (SystemModule module : role.getModuleList()) {
					if(!list.contains(module.getUrl())){
						list.add(module.getUrl());
					}
				}
			}
		}
		return list;
	}

	/**
	 * {初始化系统用户}
	 * 
	 * @param systemUser
	 * @return
	 * @author: wangwt
	 */
	@Override
	public String initSystemUser() {
		SystemUser systemUser =new SystemUser();
		systemUser.setId(UUIDUtils.getId());
		systemUser.setName("超级管理员");
		systemUser.setEmail("admin@goldnurse.com");
		systemUser.setPassword(MD5.md5crypt(MD5.md5crypt("88888888")));
		systemUser.setPhone("");
		systemUser.setCreateTime(new Date());
		systemUser.setCreatorId(systemUser.getId());
		systemUser.setCreatorName(systemUser.getName());
		systemUser.setStatus(0);
		int i = systemUserDao.insert(systemUser);
		if(i>0) return systemUser.getId();
		return "";
	}

	@Override
	public int chackUser(String chackValue, String userId) {
		int count=systemUserDao.chackUser(chackValue,userId);
		return count;
	}
		
}