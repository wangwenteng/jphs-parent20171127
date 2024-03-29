package com.jinpaihushi.jphs.car.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.car.model.Car;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 17:39:23
 * @version 1.0
 */
@Repository("carDao")
public interface CarDao extends BaseDao<Car> {

	List<Car> getList(String creatorId);

	Car lookup(Car car);

	boolean updateNumber(Car car);

	boolean successOrder(String id);
	
	
	
}
