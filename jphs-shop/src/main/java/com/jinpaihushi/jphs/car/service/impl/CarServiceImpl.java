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

<<<<<<< HEAD
	@Override
	public Car lookup(Car car) {
		 
		return carDao.lookup(car);
	}
	
	@Override
	public boolean updateNumber(Car car) {
		// TODO Auto-generated method stub
		return carDao.updateNumber(car);
	}

	@Override
	public boolean successOrder(String Ids) {
		
		boolean result = false;
		
		String[] idArr = Ids.split(",");
		
		for (int i = 0; i < idArr.length; i++) {
			if(!("".equals(idArr[i]))){
				result =  carDao.successOrder(idArr[i]);
			}
		}
		
		return result;
	}
	
	
	 

=======
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}