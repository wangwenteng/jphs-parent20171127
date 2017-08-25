package com.jinpaihushi.jphs.custom.dao;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.custom.model.CustomService;

/**
 * 
 * @author yangsong
 * @date 2017-07-13 14:40:35
 * @version 1.0
 */
@Repository("customServiceDao")
public interface CustomServiceDao extends BaseDao<CustomService> {
	
	Page<CustomService> getList(CustomService customService);
	
}
