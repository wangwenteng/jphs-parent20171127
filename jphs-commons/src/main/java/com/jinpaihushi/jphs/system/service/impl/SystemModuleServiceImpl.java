package com.jinpaihushi.jphs.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.dao.SystemModuleDao;
import com.jinpaihushi.jphs.system.model.SystemModule;
import com.jinpaihushi.jphs.system.service.SystemModuleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.MyPredicate;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
@Service("systemModuleService")
@SuppressWarnings("unchecked")
public class SystemModuleServiceImpl extends BaseServiceImpl<SystemModule> implements SystemModuleService {

	@Autowired
	private SystemModuleDao systemModuleDao;

	@Override
	protected BaseDao<SystemModule> getDao() {
		return systemModuleDao;
	}

	@Override
	@Transactional
	public void initUrlHandler(List<String> urlList) {
		// 取字符串的前缀
		// String str ="/access/log/index.jhtmlTT访问量统计TT0";
		// System.out.println(str.indexOf("/"));
		// System.out.println(str.indexOf("index"));
		// System.out.println(str.substring(str.indexOf("/"),str.indexOf("index")-1));
		String str = urlList.get(0).split("TT")[0];
		urlList.remove(0);
		SystemModule systemModule = new SystemModule();
		systemModule.setUrl(str);
		// 判断该模块是否有
		List<SystemModule> list = systemModuleDao.list(systemModule);
		if (list.size() > 0) {
			for (SystemModule resultSystemModule : list) {
				systemModuleDao.deleteById(resultSystemModule.getId());
			}
		}
		String parentId = "0";
		for (String string : urlList) {
			systemModule = new SystemModule();
			systemModule.setId(UUIDUtils.getId());
			systemModule.setUrl(string.split("TT")[0]);
			systemModule.setName(string.split("TT")[1]);
			systemModule.setParentId(parentId);
			systemModule.setCreateTime(new Date());
			systemModule.setStatus(0);
			if (string.contains("/index.jhtml")) {
				parentId = systemModule.getId();
			}
			systemModuleDao.insert(systemModule);
		}
	}

	@Override
	public List<SystemModule> getModuleTree(SystemModule systemModule) {
		//获取所有的权限
		List<SystemModule> list = systemModuleDao.queryList(null);
		//从所有的数据中选出一级菜单
		Predicate predicate = new MyPredicate("parentId", "0");
		List<SystemModule> parentList = (List<SystemModule>) CollectionUtils.select(list, predicate);
		for (SystemModule parentModule : parentList) {
			//根据一级菜单的id获取到二级菜单
			Predicate childrenPredicate = new MyPredicate("parentId", parentModule.getId());
			List<SystemModule> childrenList = (List<SystemModule>) CollectionUtils.select(list, childrenPredicate);
			parentModule.setState("open");
			parentModule.setChildren(childrenList);
		}
		return parentList;
	}
	
}