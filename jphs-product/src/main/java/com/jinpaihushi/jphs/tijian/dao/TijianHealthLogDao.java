package com.jinpaihushi.jphs.tijian.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.tijian.model.TijianHealthLog;

/**
 * 
 * @author wangwt
 * @date 2017-08-15 10:36:45
 * @version 1.0
 */
@Repository("tijianHealthLogDao")
public interface TijianHealthLogDao extends BaseDao<TijianHealthLog> {
	
	/**
	 * @param map
	 * userId 必传 
	 * tijian_health_log id 不为空时 查询的是详情
	 * 						为空是 查询的列表
	 * @return
	 */
	List<Map<String, Object>> getUserHealthLog(Map<String, Object> map); } 