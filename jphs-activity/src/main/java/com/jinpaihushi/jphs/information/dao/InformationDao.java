package com.jinpaihushi.jphs.information.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.information.model.Information;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Repository("informationDao")
public interface InformationDao extends BaseDao<Information> {
	
	/**
	 * 查询置顶的列表
	 * @return
	 */
	List<Information> queryOrderBy(Map<String, Object> map);
	
}
