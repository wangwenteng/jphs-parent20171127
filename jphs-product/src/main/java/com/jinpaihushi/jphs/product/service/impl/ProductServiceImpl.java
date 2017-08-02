package com.jinpaihushi.jphs.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.product.dao.ProductDao;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.sequence.dao.SequenceDao;
import com.jinpaihushi.jphs.service.dao.ServiceImagesDao;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-06-27 09:38:05
 * @version 1.0
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private ServiceImagesDao serviceImagesDao;

	@Override
	protected BaseDao<Product> getDao() {
		return productDao;
	}

	@Override
	public Product getProductDetail(String id) {
		Product product = productDao.getProductDetail(id);
		return product;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public HashMap getProductDetails(String id) {
		HashMap map = productDao.getProductDetailMap(id);
		return map;
	}

	@Override
	@Transactional
	public boolean update(Product product, ImageType imageType) {
		boolean flag = true;
		int result = productDao.update(product);
		if (result > 0) {
			if (!"".equals(imageType.getPcid()) && imageType.getPcid() != null) {
				ServiceImages serviceImages = new ServiceImages();
				serviceImages.setSourceId(product.getId());
				serviceImages.setSort(product.getSort());
				serviceImages.setStatus(product.getStatus());
				serviceImages.setType(1);
				/* pc图片信息 */
				serviceImages.setId(imageType.getPcid());
				serviceImages.setUrl(imageType.getPcurl());
				serviceImages.setDevice_type(1);
				serviceImages.setRemarks("服务-" + product.getTitle() + "-pc图片");
				// 修改
				int pc = serviceImagesDao.update(serviceImages);
				if (pc > 0) {
					if (!"".equals(imageType.getMoveid()) && imageType.getMoveid() != null) {
						/* 移动端图片信息 */
						serviceImages.setId(imageType.getMoveid());
						serviceImages.setUrl(imageType.getMoveurl());
						serviceImages.setDevice_type(2);
						serviceImages.setRemarks("服务-" + product.getTitle() + "-移动图片");
						// 修改
						int wap = serviceImagesDao.update(serviceImages);
						if (wap <= 0) {
							flag = false;
						}
					}
				} else {
					flag = false;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean insert(Product product, ImageType imageType) {
		boolean flag = true;

		String ida = sequenceDao.getCurrentVal("product");
		product.setId(ida);
		int result = productDao.insert(product);
		if (result > 0) {
			ServiceImages serviceImages = new ServiceImages();
			serviceImages.setSourceId(ida);
			serviceImages.setSort(product.getSort());
			serviceImages.setStatus(1);
			serviceImages.setType(1);
			try {
				serviceImages.setCreatorId(product.getCreatorId());
				serviceImages.setCreatorName(product.getCreatorName());
			} catch (Exception e) {
			}
			/* pc图片信息 */
			serviceImages.setId(UUID.randomUUID().toString());
			serviceImages.setUrl(imageType.getPcurl());
			serviceImages.setDevice_type(1);
			serviceImages.setRemarks("服务-" + product.getTitle() + "-pc图片");
			// 添加
			int pc = serviceImagesDao.insert(serviceImages);
			/* 移动端图片信息 */
			serviceImages.setId(UUID.randomUUID().toString());
			serviceImages.setUrl(imageType.getMoveurl());
			serviceImages.setDevice_type(2);
			serviceImages.setRemarks("服务-" + product.getTitle() + "-移动图片");
			// 添加
			int move = serviceImagesDao.insert(serviceImages);
			if (pc <= 0 && move <= 0) {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Product> getProductGoodsDetail(String id) {
		List<Product> p= productDao.getProductGoodsDetail(id);
		return p;
	}

}