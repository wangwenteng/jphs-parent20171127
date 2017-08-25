package com.jinpaihushi.jphs.activity.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.activity.model.Advertising;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author zhangzd
 * @date 2017-06-27 10:35:22
 * @version 1.0
 */
public interface AdvertisingService extends BaseService<Advertising> {

	/**
	 * 添加轮播图
	 * 
	 * @param advertising
	 * @return
	 */
	public String insertAdvertising(Advertising advertising);

	/**
	 * 编辑轮播图
	 * 
	 * @param advertising
	 * @return
	 */
	public String updateAdvertising(Advertising advertising);

	/**
	 * {获取轮播图}
	 * 
	 * @param map
	 * @return
	 * @author: wangwt
	 */
	List<Advertising> getCarouselFigure(Map<String, Object> map); 

}