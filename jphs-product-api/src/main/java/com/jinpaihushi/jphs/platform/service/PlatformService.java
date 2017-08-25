package com.jinpaihushi.jphs.platform.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.platform.model.Platform;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author fengrz
 * @date 2017-06-21 15:26:22
 * @version 1.0
 */
public interface PlatformService extends BaseService<Platform> {

    PageInfo<Platform> queryByPage(Integer p, Integer n, Platform platform);

    List<TreeNode> getGoodsList(Platform platform);

    String insertPlatform(Platform platform);

    List<Site> getSelectSite(Platform platform);

    boolean updatePlatform(Platform platform);

    /**
     * 获取平台的二级导航
     * @param platformId 平台id
     * @return
     */
    List<Product> getNavigation(String platformId, Integer deviceType);

    /**
     * 获取品类信息
     * @param platformId
     * @param deviceType
     * @return
     */
    List<Map<String, Object>> getProductList(String platformId, Integer deviceType);

    List<Map<String, Object>> getAllGoodsList(Map<String, Object> query);
}