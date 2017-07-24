package com.jinpaihushi.jphs.platform.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.model.Platform;

/**
 * 
 * @author wangwt
 * @date 2017-06-21 15:30:04
 * @version 1.0
 */
@Repository("platformDao")
public interface PlatformDao extends BaseDao<Platform> {

	List<Map<String, Object>> getProductList(Map<String, Object> query);

	List<Map<String, Object>> getAllGoods(Map<String, Object> query);
}
