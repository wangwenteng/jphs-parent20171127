package com.jinpaihushi.jphs.activity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.model.Advertising;

/**
 * 
 * @author zhangzd
 * @date 2017-06-27 10:35:22
 * @version 1.0
 */
@Repository("advertisingDao")
public interface AdvertisingDao extends BaseDao<Advertising> {

	List<Advertising> getCarouselFigure(Map<String ,Object> map);
	
}
