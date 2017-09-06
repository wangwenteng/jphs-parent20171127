package com.jinpaihushi.jphs.nurse.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;

/**
 * 
 * @author scj
 * @date 2017-08-16 14:56:07
 * @version 1.0
 */
@Repository("nurseJobtitleDao")
public interface NurseJobtitleDao extends BaseDao<NurseJobtitle> {
	/**
	 * goodsId 服务id 默认不传
	 * type 排序规则 1、离我最近 2、服务次数最多 3、护龄最长 默认不传
	 * lon 经度
	 * lat 纬度
	 * nurseName 护士姓名
	 * @param query
	 * @return
	 */
	List<Map<String, Object>> getNurseList(Map<String, Object> query);
	
	List<NurseJobtitle> getNurseAuditing(Map<String,Object> map);
	
}
