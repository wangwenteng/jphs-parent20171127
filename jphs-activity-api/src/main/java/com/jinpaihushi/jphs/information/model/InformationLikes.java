package com.jinpaihushi.jphs.information.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * INFORMATION_LIKES 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InformationLikes extends BaseModel implements Predicate<InformationLikes>,
		Updator<InformationLikes> {


    /** 资讯id */
	@Length(max = 50, message = "{informationLikes.informationId.illegal.length}")
	private String informationId;

    /** 评价id */
	@Length(max = 50, message = "{informationLikes.informationEvaluateId.illegal.length}")
	private String informationEvaluateId;

    /** 1喜欢，2不喜欢 */
	private Integer likes;

    /** 创建设备 */
	@Length(max = 50, message = "{informationLikes.device.illegal.length}")
	private String device;

	public InformationLikes(){}

	public InformationLikes(String id){
		this.id = id;
	}

	/**
	 * 获取资讯id
	 */
	public String getInformationId() {
    	return informationId;
    }
  	
	/**
	 * 设置资讯id
	 */
	public void setInformationId(String informationId) {
    	this.informationId = informationId;
    }

	/**
	 * 获取评价id
	 */
	public String getInformationEvaluateId() {
    	return informationEvaluateId;
    }
  	
	/**
	 * 设置评价id
	 */
	public void setInformationEvaluateId(String informationEvaluateId) {
    	this.informationEvaluateId = informationEvaluateId;
    }

	/**
	 * 获取1喜欢，2不喜欢
	 */
	public Integer getLikes() {
    	return likes;
    }
  	
	/**
	 * 设置1喜欢，2不喜欢
	 */
	public void setLikes(Integer likes) {
    	this.likes = likes;
    }

	/**
	 * 获取创建设备
	 */
	public String getDevice() {
    	return device;
    }
  	
	/**
	 * 设置创建设备
	 */
	public void setDevice(String device) {
    	this.device = device;
    }

    public String toString() {
		return new StringBuilder().append("InformationLikes{").
			append("id=").append(id).
			append(",informationId=").append(informationId).
			append(",informationEvaluateId=").append(informationEvaluateId).
			append(",likes=").append(likes).
			append(",device=").append(device).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, informationId, informationEvaluateId, likes, 
	 * device, status, createTime, creatorId, 
	 * creatorName
	 */
	public InformationLikes copy(){
		InformationLikes informationLikes = new InformationLikes();
     	informationLikes.id = this.id;
     	informationLikes.informationId = this.informationId;
     	informationLikes.informationEvaluateId = this.informationEvaluateId;
     	informationLikes.likes = this.likes;
     	informationLikes.device = this.device;
     	informationLikes.status = this.status;
     	informationLikes.createTime = this.createTime;
     	informationLikes.creatorId = this.creatorId;
     	informationLikes.creatorName = this.creatorName;
		return informationLikes;
	}
	
	/**
	 * 比较字段：
	 * id, informationId, informationEvaluateId, likes, 
	 * device, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(InformationLikes t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.informationId == null || this.informationId.equals(t.informationId))
				&& (this.informationEvaluateId == null || this.informationEvaluateId.equals(t.informationEvaluateId))
				&& (this.likes == null || this.likes.equals(t.likes))
				&& (this.device == null || this.device.equals(t.device))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(InformationLikes element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.informationId != null && !this.informationId.isEmpty()) {
			element.informationId = this.informationId;
		}
		if (this.informationEvaluateId != null && !this.informationEvaluateId.isEmpty()) {
			element.informationEvaluateId = this.informationEvaluateId;
		}
		if (this.likes != null) {
			element.likes = this.likes;
		}
		if (this.device != null && !this.device.isEmpty()) {
			element.device = this.device;
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
