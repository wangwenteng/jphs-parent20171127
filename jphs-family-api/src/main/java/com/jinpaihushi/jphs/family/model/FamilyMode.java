package com.jinpaihushi.jphs.family.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_MODE 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1关闭）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FamilyMode extends BaseModel implements Predicate<FamilyMode>,
		Updator<FamilyMode> {


    /**  */
	@Length(max = 50, message = "{familyMode.familyPackageId.illegal.length}")
	private String familyPackageId;

    /** 获取方式(1实付购买，2兑换码，3免费识别) */
	private Integer accessMode;

    /** 验证编号 */
	@Length(max = 255, message = "{familyMode.validateCode.illegal.length}")
	private String validateCode;

    /** 开始时间 */
	private Date beginTime;
	
	/** 价格 */
	private Double price;

    /** 结束时间 */
	private Date endTime;

    /** 有效天数，通过免费获取有效家庭护士天数 */
	private Integer day;

    /** 单位 */
	@Length(max = 50, message = "{familyMode.unit.illegal.length}")
	private String unit;
	
	private FamilyPackage familyPackage;

	public FamilyMode(){}

	public FamilyMode(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public FamilyPackage getFamilyPackage() {
    	return familyPackage;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyPackage(FamilyPackage familyPackage) {
    	this.familyPackage = familyPackage;
    }
	
	/**
	 * 获取
	 */
	public String getFamilyPackageId() {
    	return familyPackageId;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyPackageId(String familyPackageId) {
    	this.familyPackageId = familyPackageId;
    }
	
	/**
	 * 获取价格
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置价格
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取获取方式(1实付购买，2兑换码，3免费识别)
	 */
	public Integer getAccessMode() {
    	return accessMode;
    }
  	
	/**
	 * 设置获取方式(1实付购买，2兑换码，3免费识别)
	 */
	public void setAccessMode(Integer accessMode) {
    	this.accessMode = accessMode;
    }

	/**
	 * 获取验证编号
	 */
	public String getValidateCode() {
    	return validateCode;
    }
  	
	/**
	 * 设置验证编号
	 */
	public void setValidateCode(String validateCode) {
    	this.validateCode = validateCode;
    }

	/**
	 * 获取开始时间
	 */
	public Date getBeginTime() {
    	return beginTime;
    }
  	
	/**
	 * 设置开始时间
	 */
	public void setBeginTime(Date beginTime) {
    	this.beginTime = beginTime;
    }

	/**
	 * 获取结束时间
	 */
	public Date getEndTime() {
    	return endTime;
    }
  	
	/**
	 * 设置结束时间
	 */
	public void setEndTime(Date endTime) {
    	this.endTime = endTime;
    }

	/**
	 * 获取有效天数，通过免费获取有效家庭护士天数
	 */
	public Integer getDay() {
    	return day;
    }
  	
	/**
	 * 设置有效天数，通过免费获取有效家庭护士天数
	 */
	public void setDay(Integer day) {
    	this.day = day;
    }

	/**
	 * 获取单位
	 */
	public String getUnit() {
    	return unit;
    }
  	
	/**
	 * 设置单位
	 */
	public void setUnit(String unit) {
    	this.unit = unit;
    }

    public String toString() {
		return new StringBuilder().append("FamilyMode{").
			append("id=").append(id).
			append(",familyPackageId=").append(familyPackageId).
			append(",accessMode=").append(accessMode).
			append(",validateCode=").append(validateCode).
			append(",beginTime=").append(beginTime).
			append(",endTime=").append(endTime).
			append(",day=").append(day).
			append(",unit=").append(unit).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, familyPackageId, accessMode, validateCode, 
	 * beginTime, endTime, day, unit, 
	 * status, createTime, creatorId, creatorName
	 */
	public FamilyMode copy(){
		FamilyMode familyMode = new FamilyMode();
     	familyMode.id = this.id;
     	familyMode.familyPackageId = this.familyPackageId;
     	familyMode.accessMode = this.accessMode;
     	familyMode.validateCode = this.validateCode;
     	familyMode.beginTime = this.beginTime;
     	familyMode.endTime = this.endTime;
     	familyMode.day = this.day;
     	familyMode.unit = this.unit;
     	familyMode.status = this.status;
     	familyMode.createTime = this.createTime;
     	familyMode.creatorId = this.creatorId;
     	familyMode.creatorName = this.creatorName;
		return familyMode;
	}
	
	/**
	 * 比较字段：
	 * id, familyPackageId, accessMode, validateCode, 
	 * beginTime, endTime, day, unit, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(FamilyMode t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.familyPackageId == null || this.familyPackageId.equals(t.familyPackageId))
				&& (this.accessMode == null || this.accessMode.equals(t.accessMode))
				&& (this.validateCode == null || this.validateCode.equals(t.validateCode))
				&& (this.beginTime == null || this.beginTime.equals(t.beginTime))
				&& (this.endTime == null || this.endTime.equals(t.endTime))
				&& (this.day == null || this.day.equals(t.day))
				&& (this.unit == null || this.unit.equals(t.unit))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyMode element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.familyPackageId != null && !this.familyPackageId.isEmpty()) {
			element.familyPackageId = this.familyPackageId;
		}
		if (this.accessMode != null) {
			element.accessMode = this.accessMode;
		}
		if (this.validateCode != null && !this.validateCode.isEmpty()) {
			element.validateCode = this.validateCode;
		}
		if (this.beginTime != null) {
			element.beginTime = this.beginTime;
		}
		if (this.endTime != null) {
			element.endTime = this.endTime;
		}
		if (this.day != null) {
			element.day = this.day;
		}
		if (this.unit != null && !this.unit.isEmpty()) {
			element.unit = this.unit;
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
