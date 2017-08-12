package com.jinpaihushi.jphs.location.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.area.dao.AreaDao;
import com.jinpaihushi.jphs.area.model.Area;
import com.jinpaihushi.jphs.location.dao.LocationDao;
import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.jphs.location.service.LocationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.MyPredicate;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 11:52:34
 * @version 1.0
 */
@Service("locationService")
public class LocationServiceImpl extends BaseServiceImpl<Location> implements LocationService {

	@Autowired
	private LocationDao locationDao;
	@Autowired
	private AreaDao areaDao;

	@Override
	protected BaseDao<Location> getDao() {
		return locationDao;
	}

	/**
	 * {获取所有省的信息}
	 * 
	 * @param locationList
	 *            省市区的集合
	 * @param sourceId
	 *            被查询的对象id 护士id或者站点id
	 * @return
	 * @author: wangwt
	 */
	@Override
	@Transactional
	public List<Location> getEasyTreeData(List<Location> locationList, String sourceId) {

		// 获取已选择的区域
		List<Area> nurseArea = new ArrayList<>();
		if (StringUtils.isNotEmpty(sourceId)) {
			// 获取护士服务的所有区域
			Area area = new Area();
			area.setSourceId(sourceId);
			area.setStatus(0);
			nurseArea = areaDao.list(area);
		}
		// 获取一级的省集合
		Predicate provincePredicate = new MyPredicate("parentId", "0");
		List<Location> result = (List<Location>) CollectionUtils.select(locationList, provincePredicate);
		// 根据省获取省下边的市
		for (Location location : result) {
			Predicate cityPredicate = new MyPredicate("parentId", location.getId());
			List<Location> cityList = (List<Location>) CollectionUtils.select(locationList, cityPredicate);
			for (Location location2 : cityList) {
				Boolean flag = false;
				Predicate districtPredicate = new MyPredicate("parentId", location2.getId());
				List<Location> districtList = (List<Location>) CollectionUtils.select(locationList, districtPredicate);
				if (CollectionUtils.isNotEmpty(nurseArea)) {
					for (Location location3 : districtList) {
						for (Area area1 : nurseArea) {
							if (area1.getLocation().equals(location3.getId())) {
								location3.setChecked(true);
								flag = true;
							}
						}
					}
					if (flag) {

						location2.setState("open");
						location.setState("open");
					}
				}
				location2.setChildren(districtList);
			}
			location.setChildren(cityList);

		}

		return result;
	}

}