package com.jinpaihushi.jphs.site.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.site.model.Site;

/**
 * 
 * @author scj
 * @date 2017-06-21 14:43:29
 * @version 1.0
 */
@Repository("siteDao")
public interface SiteDao extends BaseDao<Site> {
	
	Site getSiteDetail(@Param("id")String id);
	List<Map<String, Object>> getSiteList();
}
