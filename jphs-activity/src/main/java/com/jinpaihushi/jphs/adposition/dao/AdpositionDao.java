package com.jinpaihushi.jphs.adposition.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.adposition.model.Adposition;

/**
 * 
 * @author scj
 * @date 2017-08-07 11:47:31
 * @version 1.0
 */
@Repository("adpositionDao")
public interface AdpositionDao extends BaseDao<Adposition> {
	
	List<Map<String,Object>> getAppindex(Integer type);
	
}
