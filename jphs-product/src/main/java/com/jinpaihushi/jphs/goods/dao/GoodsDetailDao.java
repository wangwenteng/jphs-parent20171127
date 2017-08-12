package com.jinpaihushi.jphs.goods.dao;

import java.util.Map;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.model.GoodsDetail;

public interface GoodsDetailDao extends BaseDao<GoodsDetail> {
	/**
	 * 获取服务的基本信息以及价格（商品详情）
	 * @param map（siteId 站点id， goodsId 服务id，deviceType 端）
	 * @return
	 */
	GoodsDetail getGoodsDetail(Map<String, Object> map);
}
