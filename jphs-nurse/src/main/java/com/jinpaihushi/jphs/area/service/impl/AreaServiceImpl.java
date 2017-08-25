package com.jinpaihushi.jphs.area.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.area.dao.AreaDao;
import com.jinpaihushi.jphs.area.model.Area;
import com.jinpaihushi.jphs.area.service.AreaService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 08:54:36
 * @version 1.0
 */
@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService{

	@Autowired
	private AreaDao areaDao;
	
	@Override
	protected BaseDao<Area> getDao(){
		return areaDao;
	}

}