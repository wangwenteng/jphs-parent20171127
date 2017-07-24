package com.jinpaihushi.jphs.goods.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.model.Goods;

/**
 * 
 * @author scj
 * @date 2017-06-27 10:02:37
 * @version 1.0
 */
@Repository("goodsDao")
public interface GoodsDao extends BaseDao<Goods> {
	
	Goods getGoodsDetail(@Param("id")String id);
	
	Goods getGoodsImgDetail(@Param("id")String id);
	
}
