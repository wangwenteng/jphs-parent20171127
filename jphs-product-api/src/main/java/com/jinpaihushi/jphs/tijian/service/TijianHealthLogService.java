package com.jinpaihushi.jphs.tijian.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.tijian.model.TijianHealthLog;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-08-15 10:36:45
 * @version 1.0
 */
public interface TijianHealthLogService extends BaseService<TijianHealthLog> {

	/**
	 * @param query
	 * 		  query 中的参数
	 * 		 	userId 必须 
	 * 			id 体检记录id 非必传 （为空是查询列表	不为空时查询详情）
	 * @return
	 */
	List<Map<String, Object>> getHealthLog(Map<String, Object> query);

	

}