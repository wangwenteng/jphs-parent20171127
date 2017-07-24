package com.jinpaihushi.jphs.area.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.area.model.Area;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 08:54:36
 * @version 1.0
 */
@Repository("areaDao")
public interface AreaDao extends BaseDao<Area> {
	
	int updateQuery(Area area);
}
