package com.jinpaihushi.jphs.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.car.dao.CarDao;
import com.jinpaihushi.jphs.car.model.Car;
import com.jinpaihushi.jphs.car.service.CarService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 17:39:23
 * @version 1.0
 */
@Service("carService")
public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService{

	@Autowired
	private CarDao carDao;
	
	@Override
	protected BaseDao<Car> getDao(){
		return carDao;
	}

	@Override
	public List<Car> getList(String creatorId) {
		 
		return carDao.getList(creatorId);
	}

}