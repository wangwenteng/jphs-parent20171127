package com.jinpaihushi.jphs.product.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.product.model.Product;

/**
 * 
 * @author scj
 * @date 2017-06-27 09:38:05
 * @version 1.0
 */
@Repository("productDao")
public interface ProductDao extends BaseDao<Product> {
	
	Product getProductDetail(@Param("id")String id);
	
	@SuppressWarnings("rawtypes")
	HashMap getProductDetailMap(@Param("id")String id);
	
	List<Product> getProductGoodsDetail(@Param("id")String id);
	Page<Product> getProductGoodsListDetail(Goods goods);
}