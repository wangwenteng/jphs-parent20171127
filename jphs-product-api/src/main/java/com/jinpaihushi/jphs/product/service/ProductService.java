package com.jinpaihushi.jphs.product.service;

import java.util.HashMap;
import java.util.List;

import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-06-27 09:38:05
 * @version 1.0
 */
public interface ProductService extends BaseService<Product> {
	
	Product getProductDetail(String id);
	
	@SuppressWarnings("rawtypes")
	HashMap getProductDetails(String id);

	boolean update(Product product,ImageType imageType);
	
	boolean insert(Product product,ImageType imageType);
	
	List<Product> getProductGoodsDetail(String id);
	
}