package com.jinpaihushi.jphs.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.model.SystemModule;

/**
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:05
 * @version 1.0
 */
@Repository("systemModuleDao")
public interface SystemModuleDao extends BaseDao<SystemModule> {
	
	List<SystemModule> queryList(SystemModule systemModule);
}
