package com.jinpaihushi.jphs.location.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.location.model.Location;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 11:52:33
 * @version 1.0
 */
@Repository("locationDao")
public interface LocationDao extends BaseDao<Location> {
	
	
	
}
