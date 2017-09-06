package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_IMAGES 
 * 继承自父类的字段:
 * id : 	
 * type : 功能类型	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-08 20:24:03
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityImages extends BaseModel implements Predicate<CommodityImages>,
		Updator<CommodityImages> {


    /** 图片来源 */
	@Length(max = 50, message = "{commodityImages.sourceId.illegal.length}")
	private String sourceId;

    /** 图片地址 */
	@Length(max = 65535, message = "{commodityImages.url.illegal.length}")
	private String url;

    /** 物理地址 */
	@Length(max = 100, message = "{commodityImages.path.illegal.length}")
	private String path;

    /** 渠道-端1-pc;2-移动 */
	private Integer deviceType;

    /** 排序 */
	private Integer sort;

    /** 说明 */
	@Length(max = 255, message = "{commodityImages.remarks.illegal.length}")
	private String remarks;

	public CommodityImages(){}

	public CommodityImages(String id){
		this.id = id;
	}

	/**
	 * 获取图片来源
	 */
	public String getSourceId() {
    	return sourceId;
    }
  	
	/**
	 * 设置图片来源
	 */
	public void setSourceId(String sourceId) {
    	this.sourceId = sourceId;
    }

	/**
	 * 获取图片地址
	 */
	public String getUrl() {
    	return url;
    }
  	
	/**
	 * 设置图片地址
	 */
	public void setUrl(String url) {
    	this.url = url;
    }

	/**
	 * 获取物理地址
	 */
	public String getPath() {
    	return path;
    }
  	
	/**
	 * 设置物理地址
	 */
	public void setPath(String path) {
    	this.path = path;
    }

	/**
	 * 获取渠道-端1-pc;2-移动
	 */
	public Integer getDeviceType() {
    	return deviceType;
    }
  	
	/**
	 * 设置渠道-端1-pc;2-移动
	 */
	public void setDeviceType(Integer deviceType) {
    	this.deviceType = deviceType;
    }

	/**
	 * 获取排序
	 */
	public Integer getSort() {
    	return sort;
    }
  	
	/**
	 * 设置排序
	 */
	public void setSort(Integer sort) {
    	this.sort = sort;
    }

	/**
	 * 获取说明
	 */
	public String getRemarks() {
    	return remarks;
    }
  	
	/**
	 * 设置说明
	 */
	public void setRemarks(String remarks) {
    	this.remarks = remarks;
    }

    public String toString() {
		return new StringBuilder().append("CommodityImages{").
			append("id=").append(id).
			append(",sourceId=").append(sourceId).
			append(",url=").append(url).
			append(",path=").append(path).
			append(",deviceType=").append(deviceType).
			append(",type=").append(type).
			append(",sort=").append(sort).
			append(",remarks=").append(remarks).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, sourceId, url, path, 
	 * deviceType, type, sort, remarks, 
	 * status, createTime, creatorId, creatorName
	 */
	public CommodityImages copy(){
		CommodityImages commodityImages = new CommodityImages();
     	commodityImages.id = this.id;
     	commodityImages.sourceId = this.sourceId;
     	commodityImages.url = this.url;
     	commodityImages.path = this.path;
     	commodityImages.deviceType = this.deviceType;
     	commodityImages.type = this.type;
     	commodityImages.sort = this.sort;
     	commodityImages.remarks = this.remarks;
     	commodityImages.status = this.status;
     	commodityImages.createTime = this.createTime;
     	commodityImages.creatorId = this.creatorId;
     	commodityImages.creatorName = this.creatorName;
		return commodityImages;
	}
	
	/**
	 * 比较字段：
	 * id, sourceId, url, path, 
	 * deviceType, type, sort, remarks, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(CommodityImages t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.sourceId == null || this.sourceId.equals(t.sourceId))
				&& (this.url == null || this.url.equals(t.url))
				&& (this.path == null || this.path.equals(t.path))
				&& (this.deviceType == null || this.deviceType.equals(t.deviceType))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.remarks == null || this.remarks.equals(t.remarks))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityImages element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.sourceId != null && !this.sourceId.isEmpty()) {
			element.sourceId = this.sourceId;
		}
		if (this.url != null && !this.url.isEmpty()) {
			element.url = this.url;
		}
		if (this.path != null && !this.path.isEmpty()) {
			element.path = this.path;
		}
		if (this.deviceType != null) {
			element.deviceType = this.deviceType;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.sort != null) {
			element.sort = this.sort;
		}
		if (this.remarks != null && !this.remarks.isEmpty()) {
			element.remarks = this.remarks;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
	}
}
