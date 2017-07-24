package com.jinpaihushi.jphs.goods.dao;

import java.util.Map;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.model.GoodsDetail;

public interface GoodsDetailDao extends BaseDao<GoodsDetail> {
	GoodsDetail getGoodsDetail(Map<String, Object> map);
}
