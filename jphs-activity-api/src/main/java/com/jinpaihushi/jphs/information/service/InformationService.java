package com.jinpaihushi.jphs.information.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.information.model.Information;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
public interface InformationService extends BaseService<Information> {

	/**
	 * 获取资讯列表
	 * @param channelId 频道id
	 * @param page 页数
	 * @return
	 */
	Map<String, Object> getInformationList(String channelId,Integer page);

	/**
	 * 获取置顶的资讯
	 * @param information
	 * @return
	 */
	Map<String, Object> getHomeInformation(String channelId);

	/**
	 * 资讯详情 
	 * @param id 资讯id
	 * @return
	 */
	Information getInformationDetail(String id);

	

}