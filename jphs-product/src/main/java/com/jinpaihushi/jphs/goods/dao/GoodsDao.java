package com.jinpaihushi.jphs.goods.dao;

import java.util.List;
import java.util.Map;

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
	
	Goods getGoodsByPricePart(@Param("pricePartId")String pricePartId);
	
	List<Map<String,Object>> getColumnGoods(Map<String,Object> map);
	
	List<Map<String,Object>> getHospitalGoods(Map<String,Object> map);
	
}
