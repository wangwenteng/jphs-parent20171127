package com.jinpaihushi.jphs.service.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SERVICE_IMAGES 
 * 继承自父类的字段:
 * id : 	
 * type : 功能类型	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-06-27 14:35:39
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ServiceImages extends BaseModel implements Predicate<ServiceImages>,
		Updator<ServiceImages> {


    /** 图片来源 */
	@Length(max = 50, message = "{serviceImages.sourceId.illegal.length}")
	private String sourceId;

    /** 图片地址 */
	@Length(max = 500, message = "{serviceImages.url.illegal.length}")
	private String url;

    /** 物理地址 */
	@Length(max = 10, message = "{serviceImages.path.illegal.length}")
	private String path;

    /** 排序 */
	private Integer sort;
	
	/** duan*/
	private Integer device_type;

	/** 说明 */
	@Length(max = 255, message = "{serviceImages.remarks.illegal.length}")
	private String remarks;

	public ServiceImages(){}

	public ServiceImages(String id){
		this.id = id;
	}
	
	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
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
		return new StringBuilder().append("ServiceImages{").
			append("id=").append(id).
			append(",sourceId=").append(sourceId).
			append(",url=").append(url).
			append(",path=").append(path).
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
	 * type, sort, remarks, status, 
	 * createTime, creatorId, creatorName
	 */
	public ServiceImages copy(){
		ServiceImages serviceImages = new ServiceImages();
     	serviceImages.id = this.id;
     	serviceImages.sourceId = this.sourceId;
     	serviceImages.url = this.url;
     	serviceImages.path = this.path;
     	serviceImages.type = this.type;
     	serviceImages.sort = this.sort;
     	serviceImages.remarks = this.remarks;
     	serviceImages.status = this.status;
     	serviceImages.createTime = this.createTime;
     	serviceImages.creatorId = this.creatorId;
     	serviceImages.creatorName = this.creatorName;
		return serviceImages;
	}
	
	/**
	 * 比较字段：
	 * id, sourceId, url, path, 
	 * type, sort, remarks, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(ServiceImages t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.sourceId == null || this.sourceId.equals(t.sourceId))
				&& (this.url == null || this.url.equals(t.url))
				&& (this.path == null || this.path.equals(t.path))
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
	public void update(ServiceImages element) {
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
