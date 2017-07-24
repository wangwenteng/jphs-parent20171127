package com.jinpaihushi.jphs.price.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.price.model.Price;

/**
 * 
 * @author yangsong
 * @date 2017-07-08 09:34:35
 * @version 1.0
 */
@Repository("priceDao")
public interface PriceDao extends BaseDao<Price> {
	
	List<Price>  getGoodsPriceDetail(Price p);
	
	List<Price> getGoodsPriceGradeDetail(@Param("id")String id);
	
	List<Price> getGoodsGrade(Map<String, Object> map);
}
