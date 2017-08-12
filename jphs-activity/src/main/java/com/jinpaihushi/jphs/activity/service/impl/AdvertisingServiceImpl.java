package com.jinpaihushi.jphs.activity.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.AdvertisingDao;
import com.jinpaihushi.jphs.activity.model.Advertising;
import com.jinpaihushi.jphs.activity.service.AdvertisingService;
import com.jinpaihushi.jphs.service.dao.ServiceImagesDao;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author zhangzd
 * @date 2017-06-27 10:35:22
 * @version 1.0
 */
@Service("advertisingService")
public class AdvertisingServiceImpl extends BaseServiceImpl<Advertising> implements AdvertisingService{

	@Autowired
	private AdvertisingDao advertisingDao;
	
	@Autowired
	private ServiceImagesDao serviceImagesDao;
	
	@Override
	protected BaseDao<Advertising> getDao(){
		return advertisingDao;
	}

	@Override
	public String insertAdvertising(Advertising advertising) {
		int i = advertisingDao.insert(advertising);
		if(i>0){
			ServiceImages  serviceImages = new ServiceImages();
			serviceImages.setSourceId(advertising.getId());
			serviceImages.setStatus(0);
			serviceImages.setCreateTime(new Date());
			serviceImages.setCreatorId(advertising.getCreatorId());
			serviceImages.setCreatorName(advertising.getCreatorName());
			//添加轮播图图片
			serviceImages.setId(UUIDUtils.getId());
			serviceImages.setUrl(advertising.getImage());
			serviceImages.setType(1);
			serviceImages.setRemarks("轮播图图片");
			serviceImagesDao.insert(serviceImages);
			//添加轮播图背景图片
			serviceImages.setId(UUIDUtils.getId());
			serviceImages.setUrl(advertising.getBgImage());
			serviceImages.setType(2);
			serviceImages.setRemarks("轮播图背景图片");
			serviceImagesDao.insert(serviceImages);
		}
		return advertising.getId();
	}

	@Override
	public String updateAdvertising(Advertising advertising) {
		ServiceImages  serviceImages = null;
		int i = advertisingDao.update(advertising);
		if(i>0){
			serviceImages = new ServiceImages();
			serviceImages.setSourceId(advertising.getId());
			//图片
			serviceImages.setType(1);
			serviceImages = serviceImagesDao.load(serviceImages);
			if(serviceImages!=null){
				serviceImages.setUrl(advertising.getImage());
				serviceImagesDao.update(serviceImages);
			}else{
				serviceImages = new ServiceImages();
				serviceImages.setSourceId(advertising.getId());
				serviceImages.setStatus(0);
				serviceImages.setCreateTime(new Date());
				serviceImages.setCreatorId(advertising.getCreatorId());
				serviceImages.setCreatorName(advertising.getCreatorName());
				//添加轮播图图片
				serviceImages.setId(UUIDUtils.getId());
				serviceImages.setUrl(advertising.getImage());
				serviceImages.setType(1);
				serviceImages.setRemarks("轮播图图片");
				serviceImagesDao.insert(serviceImages);
			}
			
			serviceImages = new ServiceImages();
			serviceImages.setSourceId(advertising.getId());
			//背景图片
			serviceImages.setType(2);
			serviceImages = serviceImagesDao.load(serviceImages);
			if(serviceImages!=null){
				serviceImages.setUrl(advertising.getBgImage());
				serviceImagesDao.update(serviceImages);
			}else{
				serviceImages = new ServiceImages();
				serviceImages.setSourceId(advertising.getId());
				serviceImages.setStatus(0);
				serviceImages.setCreateTime(new Date());
				serviceImages.setCreatorId(advertising.getCreatorId());
				serviceImages.setCreatorName(advertising.getCreatorName());
				//添加轮播图背景图片
				serviceImages.setId(UUIDUtils.getId());
				serviceImages.setUrl(advertising.getBgImage());
				serviceImages.setType(2);
				serviceImages.setRemarks("轮播图图片");
				serviceImagesDao.insert(serviceImages);
			}
		}
		return advertising.getId();
	}

	/**
	 * {获取轮播图}
	 * 
	 * @param map type 轮播的类型
	 * @return
	 * @author: wangwt
	 */
	@Override
	public List<Advertising> getCarouselFigure(Map<String, Object> map) {
		List<Advertising> list = advertisingDao.getCarouselFigure(map);
		
		return list;
	}
	
}