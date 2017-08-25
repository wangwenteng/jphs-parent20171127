package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_IMAGES 
 * 继承自父类的字段:
 * id : 	
 * type : 图片类型	
 * creatorId : 创建人ID-此表有系统图片，创建人是登录人的ID，用户创建时和user_ID一样，当是系统管理员上传图片时user_id是默认一个值	
 * creatorName : 创建人name	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author fengrz
 * @date 2017-06-09 14:36:57
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseImages extends BaseModel implements Predicate<NurseImages>,
		Updator<NurseImages> {


    /** 图片地址 */
	@Length(max = 200, message = "{nurseImages.url.illegal.length}")
	private String url;

    /** 服务器物理地址 */
	@Length(max = 200, message = "{nurseImages.path.illegal.length}")
	private String path;

    /** 来源 */
	@Length(max = 50, message = "{nurseImages.sourceId.illegal.length}")
	private String sourceId;

	public NurseImages(){}

	public NurseImages(String id){
		this.id = id;
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
	 * 获取服务器物理地址
	 */
	public String getPath() {
    	return path;
    }
  	
	/**
	 * 设置服务器物理地址
	 */
	public void setPath(String path) {
    	this.path = path;
    }

	/**
	 * 获取来源
	 */
	public String getSourceId() {
    	return sourceId;
    }
  	
	/**
	 * 设置来源
	 */
	public void setSourceId(String sourceId) {
    	this.sourceId = sourceId;
    }

    public String toString() {
		return new StringBuilder().append("NurseImages{").
			append("id=").append(id).
			append(",url=").append(url).
			append(",path=").append(path).
			append(",sourceId=").append(sourceId).
			append(",type=").append(type).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, url, path, sourceId, 
	 * type, creatorId, creatorName, createTime, 
	 * status
	 */
	public NurseImages copy(){
		NurseImages nurseImages = new NurseImages();
     	nurseImages.id = this.id;
     	nurseImages.url = this.url;
     	nurseImages.path = this.path;
     	nurseImages.sourceId = this.sourceId;
     	nurseImages.type = this.type;
     	nurseImages.creatorId = this.creatorId;
     	nurseImages.creatorName = this.creatorName;
     	nurseImages.createTime = this.createTime;
     	nurseImages.status = this.status;
		return nurseImages;
	}
	
	/**
	 * 比较字段：
	 * id, url, path, sourceId, 
	 * type, creatorId, creatorName, createTime, 
	 * status
	 */
	@Override
	public boolean test(NurseImages t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.url == null || this.url.equals(t.url))
				&& (this.path == null || this.path.equals(t.path))
				&& (this.sourceId == null || this.sourceId.equals(t.sourceId))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(NurseImages element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.url != null && !this.url.isEmpty()) {
			element.url = this.url;
		}
		if (this.path != null && !this.path.isEmpty()) {
			element.path = this.path;
		}
		if (this.sourceId != null && !this.sourceId.isEmpty()) {
			element.sourceId = this.sourceId;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
