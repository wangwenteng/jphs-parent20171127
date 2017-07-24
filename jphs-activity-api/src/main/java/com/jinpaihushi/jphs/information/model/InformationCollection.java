package com.jinpaihushi.jphs.information.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * INFORMATION_COLLECTION 
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
public class InformationCollection extends BaseModel implements Predicate<InformationCollection>,
		Updator<InformationCollection> {


    /** 资讯id */
	@Length(max = 50, message = "{informationCollection.informationId.illegal.length}")
	private String informationId;

    /** 创建设备 */
	@Length(max = 50, message = "{informationCollection.device.illegal.length}")
	private String device;

	public InformationCollection(){}

	public InformationCollection(String id){
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
		return new StringBuilder().append("InformationCollection{").
			append("id=").append(id).
			append(",informationId=").append(informationId).
			append(",device=").append(device).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, informationId, device, status, 
	 * createTime, creatorId, creatorName
	 */
	public InformationCollection copy(){
		InformationCollection informationCollection = new InformationCollection();
     	informationCollection.id = this.id;
     	informationCollection.informationId = this.informationId;
     	informationCollection.device = this.device;
     	informationCollection.status = this.status;
     	informationCollection.createTime = this.createTime;
     	informationCollection.creatorId = this.creatorId;
     	informationCollection.creatorName = this.creatorName;
		return informationCollection;
	}
	
	/**
	 * 比较字段：
	 * id, informationId, device, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(InformationCollection t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.informationId == null || this.informationId.equals(t.informationId))
				&& (this.device == null || this.device.equals(t.device))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(InformationCollection element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.informationId != null && !this.informationId.isEmpty()) {
			element.informationId = this.informationId;
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
