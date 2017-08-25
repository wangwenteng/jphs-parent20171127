package com.jinpaihushi.jphs.site.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.site.model.ProductList;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-06-21 14:43:29
 * @version 1.0
 */
public interface SiteService extends BaseService<Site> {

	boolean insert(Site site,ProductList productList);
	
	boolean update(Site site,ProductList productList);

	Site getSiteDetail(String id);
	/**
	 * {获取站点列表}
	 * 
	 * @return
	 * @author: wangwt
	 */
	List<Map<String, Object>> getSiteList();
}