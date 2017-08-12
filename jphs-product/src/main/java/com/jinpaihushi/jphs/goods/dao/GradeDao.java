package com.jinpaihushi.jphs.goods.dao;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.model.Grade;

public interface GradeDao extends BaseDao<Grade>{
	/**
	 * 获取服务所有价格
	 * @param map（siteId 站点id， goodsId 服务id）
	 * @return
	 */
	List<Grade> getGoodsPrice(Map<String, Object> map);
}
