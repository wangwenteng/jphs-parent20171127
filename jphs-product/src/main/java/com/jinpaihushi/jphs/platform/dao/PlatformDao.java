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

<<<<<<< HEAD
    List<Map<String, Object>> getProductList(Map<String, Object> query);

    /**
     * @param query
     *  platformId 平台id
        productId 品类id 有品类id查询列表 如果没有有goodsId 查询单条
        deviceType 终端类型
        siteId 站点id
     * @return
     */
    List<Map<String, Object>> getAllGoods(Map<String, Object> query);
=======
	List<Map<String, Object>> getProductList(Map<String, Object> query);

	List<Map<String, Object>> getAllGoods(Map<String, Object> query);
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}
