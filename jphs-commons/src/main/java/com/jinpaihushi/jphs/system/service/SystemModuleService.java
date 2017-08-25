package com.jinpaihushi.jphs.system.service;

import java.util.List;

import com.jinpaihushi.jphs.system.model.SystemModule;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @version 1.0
 */
public interface SystemModuleService extends BaseService<SystemModule> {
	void initUrlHandler(List<String> urlList);
	
	List<SystemModule> getModuleTree(SystemModule systemModule);
}