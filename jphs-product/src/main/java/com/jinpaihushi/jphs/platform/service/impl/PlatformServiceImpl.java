package com.jinpaihushi.jphs.platform.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.platform.dao.PlatformDao;
import com.jinpaihushi.jphs.platform.dao.PlatformGoodsDao;
import com.jinpaihushi.jphs.platform.dao.PlatformSiteDao;
import com.jinpaihushi.jphs.platform.dao.TreeNodeDao;
import com.jinpaihushi.jphs.platform.model.Platform;
import com.jinpaihushi.jphs.platform.model.PlatformGoods;
import com.jinpaihushi.jphs.platform.model.PlatformSite;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.site.dao.SiteDao;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.MyPredicate;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-06-21 15:30:05
 * @version 1.0
 */
@Service("platformService")
@SuppressWarnings("unchecked")
public class PlatformServiceImpl extends BaseServiceImpl<Platform> implements PlatformService {

<<<<<<< HEAD
    @Autowired
    private PlatformDao platformDao;

    @Override
    protected BaseDao<Platform> getDao() {
        return platformDao;
    }

    @Autowired
    private TreeNodeDao treeNodeDao;

    @Autowired
    private PlatformGoodsDao platformGoodsDao;

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private PlatformSiteDao platformSiteDao;

    /**
     * {带分页查询}
     * 
     * @param p
     *            查询页数
     * @param n
     *            每页显示记录数
     * @param platform
     *            查询条件
     * @return 带分页的结果集
     * @author: wangwt
     */
    @Override
    public PageInfo<Platform> queryByPage(Integer page, Integer rows, Platform platform) {
        PageHelper.startPage(page, rows);
        List<Platform> list = platformDao.query(platform);
        PageInfo<Platform> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * {获取商品的列表}
     * 
     * @param platform
     * @return
     * @author: wangwt
     */
    @Override
    public List<TreeNode> getGoodsList(Platform platform) {
        // 获取所有的产品列表
        List<TreeNode> product = treeNodeDao.getProduct();
        // 获取所有商品列表
        List<TreeNode> goods = treeNodeDao.getGoods();
        // 获取平台已上架的信息
        List<PlatformGoods> list = new ArrayList<>();
        if (platform != null) {
            PlatformGoods platformGoods = new PlatformGoods();
            platformGoods.setPlatformId(platform.getId());
            list = platformGoodsDao.list(platformGoods);
        }
        for (TreeNode treeNode : product) {
            Predicate predicate = new MyPredicate("parentId", treeNode.getId());
            List<TreeNode> select = (List<TreeNode>) CollectionUtils.select(goods, predicate);
            int i = 0;
            for (TreeNode selectNode : select) {
                for (PlatformGoods platformGood : list) {
                    if (platformGood.getGoodsId().equals(selectNode.getId())) {
                        selectNode.setChecked(true);
                        i++;
                    }
                }
            }
            if (i >= 1) {
                treeNode.setState("open");
            }
            treeNode.setChildren(select);
        }
        return product;
    }

    @Override
    public List<Site> getSelectSite(Platform platform) {
        // 获取所有的站点
        Site site = new Site();
        site.setStatus(0);
        List<Site> siteList = siteDao.list(site);
        // 获取改平台已关联的站点
        if (platform != null) {
            PlatformSite platformSite = new PlatformSite();
            platformSite.setPlatformId(platform.getId());
            List<PlatformSite> list = platformSiteDao.list(platformSite);
            for (Site site1 : siteList) {
                for (PlatformSite platformSite2 : list) {
                    if (site1.getId().equals(platformSite2.getSiteId())) {
                        site1.setChecked(true);
                    }
                }
            }
        }

        return siteList;
    }

    @Override
    @Transactional
    public String insertPlatform(Platform platform) {
        platform.setId(UUIDUtils.getId());
        platform.setCreateTime(new Date());
        platform.setStatus(0);
        int i = platformDao.insert(platform);
        if (i > 0) {
            PlatformGoods platformGoods = null;
            String[] goods = platform.getGoodsIds().split(",");
            for (int j = 0; j < goods.length; j++) {
                platformGoods = new PlatformGoods();
                platformGoods.setId(UUIDUtils.getId());
                platformGoods.setPlatformId(platform.getId());
                platformGoods.setGoodsId(goods[j]);
                platformGoods.setCreateTime(new Date());
                platformGoods.setCreatorId(platform.getCreatorId());
                platformGoods.setCreatorName(platform.getCreatorName());
                platformGoods.setStatus(0);
                platformGoodsDao.insert(platformGoods);
            }
            PlatformSite platformSite = null;
            String[] sites = platform.getSites().split(",");
            for (int j = 0; j < sites.length; j++) {
                platformSite = new PlatformSite();
                platformSite.setId(UUIDUtils.getId());
                platformSite.setPlatformId(platform.getId());
                platformSite.setSiteId(sites[j]);
                platformSite.setCreateTime(new Date());
                platformSite.setCreatorId(platform.getCreatorId());
                platformSite.setCreatorName(platform.getCreatorName());
                platformSite.setStatus(0);
                platformSiteDao.insert(platformSite);
            }
        }
        return platform.getId();
    }

    @Override
    @Transactional
    public boolean updatePlatform(Platform platform) {
        int i = platformDao.update(platform);
        if (i > 0) {
            // 根据平台id将已关联的商品
            PlatformGoods goods2 = new PlatformGoods();
            goods2.setPlatformId(platform.getId());
            List<PlatformGoods> oldGoodsList = platformGoodsDao.list(goods2);
            i = 0;
            for (PlatformGoods platformGoods : oldGoodsList) {
                i = platformGoodsDao.deleteById(platformGoods.getId());
            }
            if (oldGoodsList.size() == 0)
                i = 1;
            if (i > 0) {
                PlatformGoods platformGoods = null;
                String[] goods = platform.getGoodsIds().split(",");
                for (int j = 0; j < goods.length; j++) {
                    platformGoods = new PlatformGoods();
                    platformGoods.setId(UUIDUtils.getId());
                    platformGoods.setPlatformId(platform.getId());
                    platformGoods.setGoodsId(goods[j]);
                    platformGoods.setCreateTime(new Date());
                    platformGoods.setCreatorId(platform.getCreatorId());
                    platformGoods.setCreatorName(platform.getCreatorName());
                    platformGoods.setStatus(0);
                    platformGoodsDao.insert(platformGoods);
                }
            }
            PlatformSite site2 = new PlatformSite();
            site2.setPlatformId(platform.getId());
            List<PlatformSite> oldSiteList = platformSiteDao.list(site2);
            i = 0;
            for (PlatformSite platformSite : oldSiteList) {
                i = platformSiteDao.deleteById(platformSite.getId());
            }
            if (oldSiteList.size() == 0)
                i = 1;
            if (i > 0) {
                PlatformSite platformSite = null;
                String[] sites = platform.getSites().split(",");
                for (int j = 0; j < sites.length; j++) {
                    platformSite = new PlatformSite();
                    platformSite.setId(UUIDUtils.getId());
                    platformSite.setPlatformId(platform.getId());
                    platformSite.setSiteId(sites[j]);
                    platformSite.setCreateTime(new Date());
                    platformSite.setCreatorId(platform.getCreatorId());
                    platformSite.setCreatorName(platform.getCreatorName());
                    platformSite.setStatus(0);
                    platformSiteDao.insert(platformSite);
                }
            }
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getProductList(String platformId, Integer deviceType) {
        Map<String, Object> query = new HashMap<>();
        query.put("platformId", platformId);
        query.put("deviceType", deviceType);
        List<Map<String, Object>> producntList = platformDao.getProductList(query);
        return producntList;
    }

    @Override
    public List<Map<String, Object>> getAllGoodsList(Map<String, Object> query) {

        List<Map<String, Object>> goodsList = platformDao.getAllGoods(query);
        return goodsList;
    }

    /**
     * 获取平台的二级导航
     * 
     * @param platformId
     *            平台id
     * @return
     */
    @Override
    public List<Product> getNavigation(String platformId, Integer deviceType) {
        List<Product> result = new ArrayList<>();
        try {

            Map<String, Object> query = new HashMap<>();
            query.put("platformId", platformId);
            query.put("deviceType", deviceType);
            List<Map<String, Object>> productList = platformDao.getProductList(query);
            for (Map<String, Object> map : productList) {
                Product product = new Product();
                BeanUtils.populate(product, map);
                result.add(product);
            }
            List<Goods> goods = new ArrayList<>();
            // 查询平台所有的服务
            List<Map<String, Object>> goodsList = platformDao.getAllGoods(query);
            for (Map<String, Object> map : goodsList) {
                Goods good = new Goods();
                BeanUtils.populate(good, map);
                good.setProductId((String) map.get("product_id"));
                goods.add(good);
            }
            for (Product product : result) {
                Predicate predicate = new MyPredicate("productId", product.getId());
                List<Goods> select = (List<Goods>) CollectionUtils.select(goods, predicate);
                product.setGoodsList(select);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
=======
	@Autowired
	private PlatformDao platformDao;

	@Override
	protected BaseDao<Platform> getDao() {
		return platformDao;
	}

	@Autowired
	private TreeNodeDao treeNodeDao;
	@Autowired
	private PlatformGoodsDao platformGoodsDao;
	@Autowired
	private SiteDao siteDao;
	@Autowired
	private PlatformSiteDao platformSiteDao;

	/**
	 * {带分页查询}
	 * 
	 * @param p
	 *            查询页数
	 * @param n
	 *            每页显示记录数
	 * @param platform
	 *            查询条件
	 * @return 带分页的结果集
	 * @author: wangwt
	 */
	@Override
	public PageInfo<Platform> queryByPage(Integer page, Integer rows, Platform platform) {
		PageHelper.startPage(page, rows);
		List<Platform> list = platformDao.query(platform);
		PageInfo<Platform> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * {获取商品的列表}
	 * 
	 * @param platform
	 * @return
	 * @author: wangwt
	 */
	@Override
	public List<TreeNode> getGoodsList(Platform platform) {
		// 获取所有的产品列表
		List<TreeNode> product = treeNodeDao.getProduct();
		// 获取所有商品列表
		List<TreeNode> goods = treeNodeDao.getGoods();
		// 获取平台已上架的信息
		List<PlatformGoods> list = new ArrayList<>();
		if (platform != null) {
			PlatformGoods platformGoods = new PlatformGoods();
			platformGoods.setPlatformId(platform.getId());
			list = platformGoodsDao.list(platformGoods);
		}
		for (TreeNode treeNode : product) {
			Predicate predicate = new MyPredicate("parentId", treeNode.getId());
			List<TreeNode> select = (List<TreeNode>) CollectionUtils.select(goods, predicate);
			int i = 0;
			for (TreeNode selectNode : select) {
				for (PlatformGoods platformGood : list) {
					if (platformGood.getGoodsId().equals(selectNode.getId())) {
						selectNode.setChecked(true);
						i++;
					}
				}
			}
			if (i >= 1) {
				treeNode.setState("open");
			}
			treeNode.setChildren(select);
		}
		return product;
	}

	@Override
	public List<Site> getSelectSite(Platform platform) {
		// 获取所有的站点
		Site site = new Site();
		site.setStatus(0);
		List<Site> siteList = siteDao.list(site);
		// 获取改平台已关联的站点
		if (platform != null) {
			PlatformSite platformSite = new PlatformSite();
			platformSite.setPlatformId(platform.getId());
			List<PlatformSite> list = platformSiteDao.list(platformSite);
			for (Site site1 : siteList) {
				for (PlatformSite platformSite2 : list) {
					if (site1.getId().equals(platformSite2.getSiteId())) {
						site1.setChecked(true);
					}
				}
			}
		}

		return siteList;
	}

	@Override
	@Transactional
	public String insertPlatform(Platform platform) {
		platform.setId(UUIDUtils.getId());
		platform.setCreateTime(new Date());
		platform.setStatus(0);
		int i = platformDao.insert(platform);
		if (i > 0) {
			PlatformGoods platformGoods = null;
			String[] goods = platform.getGoodsIds().split(",");
			for (int j = 0; j < goods.length; j++) {
				platformGoods = new PlatformGoods();
				platformGoods.setId(UUIDUtils.getId());
				platformGoods.setPlatformId(platform.getId());
				platformGoods.setGoodsId(goods[j]);
				platformGoods.setCreateTime(new Date());
				platformGoods.setCreatorId(platform.getCreatorId());
				platformGoods.setCreatorName(platform.getCreatorName());
				platformGoods.setStatus(0);
				platformGoodsDao.insert(platformGoods);
			}
			PlatformSite platformSite = null;
			String[] sites = platform.getSites().split(",");
			for (int j = 0; j < sites.length; j++) {
				platformSite = new PlatformSite();
				platformSite.setId(UUIDUtils.getId());
				platformSite.setPlatformId(platform.getId());
				platformSite.setSiteId(sites[j]);
				platformSite.setCreateTime(new Date());
				platformSite.setCreatorId(platform.getCreatorId());
				platformSite.setCreatorName(platform.getCreatorName());
				platformSite.setStatus(0);
				platformSiteDao.insert(platformSite);
			}
		}
		return platform.getId();
	}

	@Override
	@Transactional
	public boolean updatePlatform(Platform platform) {
		int i = platformDao.update(platform);
		if (i > 0) {
			// 根据平台id将已关联的商品
			PlatformGoods goods2 = new PlatformGoods();
			goods2.setPlatformId(platform.getId());
			List<PlatformGoods> oldGoodsList = platformGoodsDao.list(goods2);
			i = 0;
			for (PlatformGoods platformGoods : oldGoodsList) {
				i = platformGoodsDao.deleteById(platformGoods.getId());
			}
			if (oldGoodsList.size() == 0)
				i = 1;
			if (i > 0) {
				PlatformGoods platformGoods = null;
				String[] goods = platform.getGoodsIds().split(",");
				for (int j = 0; j < goods.length; j++) {
					platformGoods = new PlatformGoods();
					platformGoods.setId(UUIDUtils.getId());
					platformGoods.setPlatformId(platform.getId());
					platformGoods.setGoodsId(goods[j]);
					platformGoods.setCreateTime(new Date());
					platformGoods.setCreatorId(platform.getCreatorId());
					platformGoods.setCreatorName(platform.getCreatorName());
					platformGoods.setStatus(0);
					platformGoodsDao.insert(platformGoods);
				}
			}
			PlatformSite site2 = new PlatformSite();
			site2.setPlatformId(platform.getId());
			List<PlatformSite> oldSiteList = platformSiteDao.list(site2);
			i = 0;
			for (PlatformSite platformSite : oldSiteList) {
				i = platformSiteDao.deleteById(platformSite.getId());
			}
			if (oldSiteList.size() == 0)
				i = 1;
			if (i > 0) {
				PlatformSite platformSite = null;
				String[] sites = platform.getSites().split(",");
				for (int j = 0; j < sites.length; j++) {
					platformSite = new PlatformSite();
					platformSite.setId(UUIDUtils.getId());
					platformSite.setPlatformId(platform.getId());
					platformSite.setSiteId(sites[j]);
					platformSite.setCreateTime(new Date());
					platformSite.setCreatorId(platform.getCreatorId());
					platformSite.setCreatorName(platform.getCreatorName());
					platformSite.setStatus(0);
					platformSiteDao.insert(platformSite);
				}
			}
		}
		return true;
	}


	@Override
	public List<Map<String, Object>> getProductList(String platformId, Integer deviceType) {
		Map<String, Object> query = new HashMap<>();
		query.put("platformId", platformId);
		query.put("deviceType", deviceType);
		List<Map<String, Object>> producntList = platformDao.getProductList(query);
		return producntList;
	}
	
	@Override
	public List<Map<String, Object>> getGoodsList(String platformId, String productId, Integer deviceType,String siteId) {
		Map<String, Object> query = new HashMap<>();
		query.put("platformId", platformId);
		query.put("productId", productId);
		query.put("deviceType", deviceType);
		query.put("siteId", siteId);
		List<Map<String, Object>> goodsList = platformDao.getAllGoods(query);
		return goodsList;
	}

	/**
	 * 获取平台的二级导航
	 * 
	 * @param platformId
	 *            平台id
	 * @return
	 */
	@Override
	public List<Product> getNavigation(String platformId,Integer deviceType) {
		List<Product> result = new ArrayList<>();
		try {

			Map<String, Object> query = new HashMap<>();
			query.put("platformId", platformId);
			query.put("deviceType", deviceType);
			List<Map<String, Object>> productList = platformDao.getProductList(query);
			for (Map<String, Object> map : productList) {
				Product product = new Product();
				BeanUtils.populate(product, map);
				result.add(product);
			}
			List<Goods> goods = new ArrayList<>();
			// 查询平台所有的服务
			List<Map<String, Object>> goodsList = platformDao.getAllGoods(query);
			for (Map<String, Object> map : goodsList) {
				Goods good = new Goods();
				BeanUtils.populate(good, map);
				good.setProductId((String) map.get("product_id"));
				goods.add(good);
			}
			for (Product product : result) {
				Predicate predicate = new MyPredicate("productId", product.getId());
				List<Goods> select = (List<Goods>) CollectionUtils.select(goods, predicate);
				product.setGoodsList(select);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git

}