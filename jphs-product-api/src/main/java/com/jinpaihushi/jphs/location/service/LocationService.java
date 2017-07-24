package com.jinpaihushi.jphs.location.service;

import java.util.List;

import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 11:52:34
 * @version 1.0
 */
public interface LocationService extends BaseService<Location> {

	List<Location> getEasyTreeData(List<Location> locationList,String nurseId);

}