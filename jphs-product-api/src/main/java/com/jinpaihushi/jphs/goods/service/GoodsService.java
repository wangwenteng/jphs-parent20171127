package com.jinpaihushi.jphs.goods.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.model.GoodsDetail;
import com.jinpaihushi.jphs.goods.model.Grade;
import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.goods.model.ListPrice;
import com.jinpaihushi.jphs.price.model.Jobtitle;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-06-27 10:02:37
 * @version 1.0
 */
public interface GoodsService extends BaseService<Goods> {

	Goods getGoodsDetail(String id);
	
	Goods getGoodsImgDetail(String id);
	
	int insertGoodsAndImg(Goods goods,ImageType imageType,ListPrice listPrice);
	
	boolean updateGoods(Goods goods,ListPrice listPrice,ImageType imageType);
	
	Page<Product> getProductGoodsListDetail(Goods goods);
	Price getJobtitlePrice(String id);

	/**
	 * {获取服务的详情}
	 * 
	 * @param id 服务id
	 * @param siteId 站点id
	 * @return
	 * @author: wangwt
	 */
	GoodsDetail getOneGoodsDetail(String id,String siteId,Integer deviceType);
	
	public List<Jobtitle> getJobtitle();
	
	public List<Map<String,Object>> getColumnGoods(Map<String,Object> map);

	/**
	 * app端使用商品的所有价格
	 * @param goodsId
	 * @param siteId
	 * @return
	 */
	List<Grade> getGoodsAllPrice(String goodsId, String siteId);
	
	public List<Map<String,Object>> getHospitalGoods(Map<String,Object> map);
	
}