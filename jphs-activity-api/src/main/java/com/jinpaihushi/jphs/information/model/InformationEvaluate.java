package com.jinpaihushi.jphs.information.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * INFORMATION_EVALUATE 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-07-19 22:47:31
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InformationEvaluate extends BaseModel implements Predicate<InformationEvaluate>,
		Updator<InformationEvaluate> {


    /** 讯资id */
	@Length(max = 50, message = "{informationEvaluate.informationId.illegal.length}")
	private String informationId;

    /** 头像 */
	@Length(max = 255, message = "{informationEvaluate.headPicture.illegal.length}")
	private String headPicture="";

    /** 评价内容 */
	@Length(max = 65535, message = "{informationEvaluate.content.illegal.length}")
	private String content;

    /** 创建设备 */
	@Length(max = 50, message = "{informationEvaluate.device.illegal.length}")
	private String device;

    /** 精华 */
	private Integer essence;
	
	/** 资讯 */
	private Information information;
	
	
	public InformationEvaluate(){}

	public InformationEvaluate(String id){
		this.id = id;
	}
	
	/**
	 * 获取讯资id
	 */
	public Information getInformation() {
    	return information;
    }
  	
	/**
	 * 设置讯资id
	 */
	public void setInformation(Information information) {
    	this.information = information;
    }

	/**
	 * 获取讯资id
	 */
	public String getInformationId() {
    	return informationId;
    }
  	
	/**
	 * 设置讯资id
	 */
	public void setInformationId(String informationId) {
    	this.informationId = informationId;
    }

	/**
	 * 获取头像
	 */
	public String getHeadPicture() {
    	return headPicture;
    }
  	
	/**
	 * 设置头像
	 */
	public void setHeadPicture(String headPicture) {
    	this.headPicture = headPicture;
    }

	/**
	 * 获取评价内容
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置评价内容
	 */
	public void setContent(String content) {
    	this.content = content;
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

	/**
	 * 获取精华
	 */
	public Integer getEssence() {
    	return essence;
    }
  	
	/**
	 * 设置精华
	 */
	public void setEssence(Integer essence) {
    	this.essence = essence;
    }

    public String toString() {
		return new StringBuilder().append("InformationEvaluate{").
			append("id=").append(id).
			append(",informationId=").append(informationId).
			append(",headPicture=").append(headPicture).
			append(",content=").append(content).
			append(",device=").append(device).
			append(",essence=").append(essence).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, informationId, headPicture, content, 
	 * device, essence, status, createTime, 
	 * creatorId, creatorName
	 */
	public InformationEvaluate copy(){
		InformationEvaluate informationEvaluate = new InformationEvaluate();
     	informationEvaluate.id = this.id;
     	informationEvaluate.informationId = this.informationId;
     	informationEvaluate.headPicture = this.headPicture;
     	informationEvaluate.content = this.content;
     	informationEvaluate.device = this.device;
     	informationEvaluate.essence = this.essence;
     	informationEvaluate.status = this.status;
     	informationEvaluate.createTime = this.createTime;
     	informationEvaluate.creatorId = this.creatorId;
     	informationEvaluate.creatorName = this.creatorName;
		return informationEvaluate;
	}
	
	/**
	 * 比较字段：
	 * id, informationId, headPicture, content, 
	 * device, essence, status, createTime, 
	 * creatorId, creatorName
	 */
	@Override
	public boolean test(InformationEvaluate t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.informationId == null || this.informationId.equals(t.informationId))
				&& (this.headPicture == null || this.headPicture.equals(t.headPicture))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.device == null || this.device.equals(t.device))
				&& (this.essence == null || this.essence.equals(t.essence))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(InformationEvaluate element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.informationId != null && !this.informationId.isEmpty()) {
			element.informationId = this.informationId;
		}
		if (this.headPicture != null && !this.headPicture.isEmpty()) {
			element.headPicture = this.headPicture;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
		}
		if (this.device != null && !this.device.isEmpty()) {
			element.device = this.device;
		}
		if (this.essence != null) {
			element.essence = this.essence;
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
