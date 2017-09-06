package com.jinpaihushi.jphs.position.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.position.dao.PositionDao;
import com.jinpaihushi.jphs.position.model.Position;
import com.jinpaihushi.jphs.position.service.PositionService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-08-17 18:15:16
 * @version 1.0
 */
@Service("positionService")
public class PositionServiceImpl extends BaseServiceImpl<Position> implements PositionService{

	@Autowired
	private PositionDao positionDao;
	
	@Override
	protected BaseDao<Position> getDao(){
		return positionDao;
	}

}