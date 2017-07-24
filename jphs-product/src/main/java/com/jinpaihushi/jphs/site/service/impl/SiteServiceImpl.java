package com.jinpaihushi.jphs.site.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.area.dao.AreaDao;
import com.jinpaihushi.jphs.area.model.Area;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.site.dao.SiteDao;
import com.jinpaihushi.jphs.site.model.ProductList;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.jphs.site.service.SiteService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author scj
 * @date 2017-06-21 14:43:29
 * @version 1.0
 */
@Service("siteService")
public class SiteServiceImpl extends BaseServiceImpl<Site> implements SiteService{

	@Autowired
	private SiteDao siteDao;
	
	@Autowired
	private PricePartDao pricePartDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Override
	protected BaseDao<Site> getDao(){
		return siteDao;
	}

	@Transactional
	public boolean update(Site site,ProductList productList){
		boolean falg = true;
		
		String siteID = site.getId();
		int s = siteDao.update(site);
		if(s > 0){
			// 先删除原有的服务区域信息
			Area area = null;
			area = new Area();
			area.setSourceId(siteID);
			area.setStatus(-1);
			List<Area> list = areaDao.list(area);
			for (Area area2 : list) {
				areaDao.deleteById(area2.getId());
			}
			// 插入服务区域
			String areas[] = site.getAreas().split(",");
			for (int k = 0; k < areas.length; k++) {
				area = new Area();
				area.setId(UUIDUtils.getId());
//				area.setCreatorId(site.getId());
//				area.setCreatorName(user.getName());
				area.setCreateTime(new Date());
				area.setType(1);
				area.setSourceId(siteID);
				area.setLocation(areas[k]);
				area.setStatus(0);
				areaDao.insert(area);
			}
			
			if(productList != null && !"".equals(productList)){
				// 品类list
				List<Product> product = productList.getProductList();
				if(product != null && !"".equals(product)){
					for(int a = 0;a < product.size(); a++){
						// 商品list
						List<Goods> goods = product.get(a).getGoodsList();
						if(goods != null && !"".equals(goods)){
							for(int b=0;b < goods.size();b++){
								// 价格list
								List<Price> priceList = goods.get(b).getPriceList();
								if(priceList != null && !"".equals(priceList)){
									for(int c=0;c<priceList.size();c++){
										// 价格明细
										PricePart pricePart = priceList.get(c).getPricePart();
										if(pricePart.getId() != null && !"".equals(pricePart.getId())){
											// siteID位0 则是新添加商品价格明细，需新添加到自己站点内
											if(pricePart.getSiteId().equals("0") && pricePart.getPrice() != null 
													&& !"".equals(pricePart) && pricePart.getOldPrice() != null
													&& !"".equals(pricePart.getOldPrice())){
												pricePart.setId(UUID.randomUUID().toString());
												pricePart.setSiteId(siteID);
												pricePart.setStatus(0);
												pricePartDao.insert(pricePart);
											}else{
												pricePartDao.update(pricePart);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}else{
			falg = false;
		}
		return falg;
	}
	
	/**
	 * 添加站点
	 * 站点信息录入
	 * 站点产品-商品录入
	 */
	@Override
	public boolean insert(Site site, ProductList productList) {
		boolean falg = true;
		
		site.setId(UUID.randomUUID().toString());
		int s = siteDao.insert(site);
		if(s > 0){
			String siteId =site.getId();
			// 插入服务区域
			if(site.getAreas() != null && !"".equals(site.getAreas())){
				Area area = null;
				String areas[] = site.getAreas().split(",");
				for (int k = 0; k < areas.length; k++) {
					area = new Area();
					area.setId(UUIDUtils.getId());
					try {
						area.setCreatorId(site.getCreatorId());
						area.setCreatorName(site.getCreatorName());
					} catch (Exception e) {
					}
					area.setCreateTime(new Date());
					area.setType(0);
					area.setSourceId(siteId);
					area.setLocation(areas[k]);
					area.setStatus(0);
					areaDao.insert(area);
				}
			}
			
			if(productList != null && !"".equals(productList)){
				// 品类list
				List<Product> product = productList.getProductList();
				if(product != null && !"".equals(product)){
					for(int a = 0;a < product.size(); a++){
						// 商品list
						List<Goods> goods = product.get(a).getGoodsList();
						if(goods != null && !"".equals(goods)){
							for(int b=0;b < goods.size();b++){
								// 价格list
								List<Price> priceList = goods.get(b).getPriceList();
								if(priceList != null && !"".equals(priceList)){
									for(int c=0;c<priceList.size();c++){
										// 价格明细
										PricePart pricePart = priceList.get(c).getPricePart();
										pricePart.setId(UUID.randomUUID().toString());
										pricePart.setSiteId(siteId);
										try {
											pricePart.setCreatorId(site.getCreatorId());
											pricePart.setCreatorName(site.getCreatorName());
										} catch (Exception e) {
										}
										int pp=pricePartDao.insert(pricePart);
										if(pp <= 0){
											falg = false;
											return false;
										}
									}
								}
							}
						}
					}
				}
			}
		}else{
			falg = false;
		}
		return falg;
	}

	@Override
	public Site getSiteDetail(String id) {
		Site query =new Site();
		query.setId(id);
		query.setStatus(0);
		Site s =siteDao.getSiteDetail(id);
		return s;
	}

	/**
	 * {获取站点列表}
	 * 
	 * @return
	 * @author: wangwt
	 */
	@Override
	public List<Map<String, Object>> getSiteList() {
		
		List<Map<String,Object>> list = siteDao.getSiteList();
		return list;
	}
	
}