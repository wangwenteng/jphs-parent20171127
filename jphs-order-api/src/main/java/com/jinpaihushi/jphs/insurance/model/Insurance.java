package com.jinpaihushi.jphs.insurance.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * INSURANCE 
 * 继承自父类的字段:
 * id : 	
 * createTime : 	
 * status : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Insurance extends BaseModel implements Predicate<Insurance>,
		Updator<Insurance> {


    /** 主订单id */
	@Length(max = 50, message = "{insurance.orderId.illegal.length}")
	private String orderId;

    /** 受保人 */
	@Length(max = 50, message = "{insurance.name.illegal.length}")
	private String name;

    /** 受保人身份证 */
	@Length(max = 50, message = "{insurance.sfz.illegal.length}")
	private String sfz;

    /** 备注 */
	@Length(max = 255, message = "{insurance.remark.illegal.length}")
	private String remark;

	public Insurance(){}

	public Insurance(String id){
		this.id = id;
	}

	/**
	 * 获取主订单id
	 */
	public String getOrderId() {
    	return orderId;
    }
  	
	/**
	 * 设置主订单id
	 */
	public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

	/**
	 * 获取受保人
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置受保人
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取受保人身份证
	 */
	public String getSfz() {
    	return sfz;
    }
  	
	/**
	 * 设置受保人身份证
	 */
	public void setSfz(String sfz) {
    	this.sfz = sfz;
    }

	/**
	 * 获取备注
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("Insurance{").
			append("id=").append(id).
			append(",orderId=").append(orderId).
			append(",name=").append(name).
			append(",sfz=").append(sfz).
			append(",remark=").append(remark).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, orderId, name, sfz, 
	 * remark, createTime, status, creatorId, 
	 * creatorName
	 */
	public Insurance copy(){
		Insurance insurance = new Insurance();
     	insurance.id = this.id;
     	insurance.orderId = this.orderId;
     	insurance.name = this.name;
     	insurance.sfz = this.sfz;
     	insurance.remark = this.remark;
     	insurance.createTime = this.createTime;
     	insurance.status = this.status;
     	insurance.creatorId = this.creatorId;
     	insurance.creatorName = this.creatorName;
		return insurance;
	}
	
	/**
	 * 比较字段：
	 * id, orderId, name, sfz, 
	 * remark, createTime, status, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(Insurance t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.orderId == null || this.orderId.equals(t.orderId))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sfz == null || this.sfz.equals(t.sfz))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Insurance element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.orderId != null && !this.orderId.isEmpty()) {
			element.orderId = this.orderId;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.sfz != null && !this.sfz.isEmpty()) {
			element.sfz = this.sfz;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
	}
}
