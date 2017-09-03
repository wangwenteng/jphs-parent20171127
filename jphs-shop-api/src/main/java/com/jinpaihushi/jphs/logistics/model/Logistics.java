package com.jinpaihushi.jphs.logistics.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * LOGISTICS 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-09-02 09:26:27
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Logistics extends BaseModel implements Predicate<Logistics>,
		Updator<Logistics> {


    /** 物流公司名称 */
	@Length(max = 255, message = "{logistics.name.illegal.length}")
	private String name;

    /** 流物代号 */
	@Length(max = 255, message = "{logistics.code.illegal.length}")
	private String code;

    /** 物流logo */
	@Length(max = 255, message = "{logistics.logo.illegal.length}")
	private String logo;

    /** 备注 */
	@Length(max = 500, message = "{logistics.remark.illegal.length}")
	private String remark;

	public Logistics(){}

	public Logistics(String id){
		this.id = id;
	}

	/**
	 * 获取物流公司名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置物流公司名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取流物代号
	 */
	public String getCode() {
    	return code;
    }
  	
	/**
	 * 设置流物代号
	 */
	public void setCode(String code) {
    	this.code = code;
    }

	/**
	 * 获取物流logo
	 */
	public String getLogo() {
    	return logo;
    }
  	
	/**
	 * 设置物流logo
	 */
	public void setLogo(String logo) {
    	this.logo = logo;
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
		return new StringBuilder().append("Logistics{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",code=").append(code).
			append(",logo=").append(logo).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, code, logo, 
	 * remark, status, createTime, creatorId, 
	 * creatorName
	 */
	public Logistics copy(){
		Logistics logistics = new Logistics();
     	logistics.id = this.id;
     	logistics.name = this.name;
     	logistics.code = this.code;
     	logistics.logo = this.logo;
     	logistics.remark = this.remark;
     	logistics.status = this.status;
     	logistics.createTime = this.createTime;
     	logistics.creatorId = this.creatorId;
     	logistics.creatorName = this.creatorName;
		return logistics;
	}
	
	/**
	 * 比较字段：
	 * id, name, code, logo, 
	 * remark, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(Logistics t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.code == null || this.code.equals(t.code))
				&& (this.logo == null || this.logo.equals(t.logo))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Logistics element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.code != null && !this.code.isEmpty()) {
			element.code = this.code;
		}
		if (this.logo != null && !this.logo.isEmpty()) {
			element.logo = this.logo;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
