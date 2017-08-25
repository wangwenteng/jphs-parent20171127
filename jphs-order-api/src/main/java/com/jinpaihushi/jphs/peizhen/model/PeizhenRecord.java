package com.jinpaihushi.jphs.peizhen.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PEIZHEN_RECORD 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 创建人ID	
 * creatorName : 创建人姓名	
 * createTime : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PeizhenRecord extends BaseModel implements Predicate<PeizhenRecord>,
		Updator<PeizhenRecord> {


    /** 是否复诊 */
	private Integer returnExamine;

    /** 复诊时间 */
	private Date examineTime;

    /** 是否转诊 */
	private Integer rotateExamine;

    /** 转诊时间 */
	private Date rotateTime;

    /** 推荐科室 */
	@Length(max = 50, message = "{peizhenRecord.recommendDepartment.illegal.length}")
	private String recommendDepartment;

    /** 注意事项 */
	@Length(max = 65535, message = "{peizhenRecord.remarks.illegal.length}")
	private String remarks;

    /** 订单ID */
	@Length(max = 50, message = "{peizhenRecord.orderId.illegal.length}")
	private String orderId;

    /** 下单人ID */
	@Length(max = 50, message = "{peizhenRecord.userId.illegal.length}")
	private String userId;

	public PeizhenRecord(){}

	public PeizhenRecord(String id){
		this.id = id;
	}

	/**
	 * 获取是否复诊
	 */
	public Integer getReturnExamine() {
    	return returnExamine;
    }
  	
	/**
	 * 设置是否复诊
	 */
	public void setReturnExamine(Integer returnExamine) {
    	this.returnExamine = returnExamine;
    }

	/**
	 * 获取复诊时间
	 */
	public Date getExamineTime() {
    	return examineTime;
    }
  	
	/**
	 * 设置复诊时间
	 */
	public void setExamineTime(Date examineTime) {
    	this.examineTime = examineTime;
    }

	/**
	 * 获取是否转诊
	 */
	public Integer getRotateExamine() {
    	return rotateExamine;
    }
  	
	/**
	 * 设置是否转诊
	 */
	public void setRotateExamine(Integer rotateExamine) {
    	this.rotateExamine = rotateExamine;
    }

	/**
	 * 获取转诊时间
	 */
	public Date getRotateTime() {
    	return rotateTime;
    }
  	
	/**
	 * 设置转诊时间
	 */
	public void setRotateTime(Date rotateTime) {
    	this.rotateTime = rotateTime;
    }

	/**
	 * 获取推荐科室
	 */
	public String getRecommendDepartment() {
    	return recommendDepartment;
    }
  	
	/**
	 * 设置推荐科室
	 */
	public void setRecommendDepartment(String recommendDepartment) {
    	this.recommendDepartment = recommendDepartment;
    }

	/**
	 * 获取注意事项
	 */
	public String getRemarks() {
    	return remarks;
    }
  	
	/**
	 * 设置注意事项
	 */
	public void setRemarks(String remarks) {
    	this.remarks = remarks;
    }

	/**
	 * 获取订单ID
	 */
	public String getOrderId() {
    	return orderId;
    }
  	
	/**
	 * 设置订单ID
	 */
	public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

	/**
	 * 获取下单人ID
	 */
	public String getUserId() {
    	return userId;
    }
  	
	/**
	 * 设置下单人ID
	 */
	public void setUserId(String userId) {
    	this.userId = userId;
    }

    public String toString() {
		return new StringBuilder().append("PeizhenRecord{").
			append("id=").append(id).
			append(",returnExamine=").append(returnExamine).
			append(",examineTime=").append(examineTime).
			append(",rotateExamine=").append(rotateExamine).
			append(",rotateTime=").append(rotateTime).
			append(",recommendDepartment=").append(recommendDepartment).
			append(",remarks=").append(remarks).
			append(",orderId=").append(orderId).
			append(",userId=").append(userId).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, returnExamine, examineTime, rotateExamine, 
	 * rotateTime, recommendDepartment, remarks, orderId, 
	 * userId, creatorId, creatorName, createTime, 
	 * status
	 */
	public PeizhenRecord copy(){
		PeizhenRecord peizhenRecord = new PeizhenRecord();
     	peizhenRecord.id = this.id;
     	peizhenRecord.returnExamine = this.returnExamine;
     	peizhenRecord.examineTime = this.examineTime;
     	peizhenRecord.rotateExamine = this.rotateExamine;
     	peizhenRecord.rotateTime = this.rotateTime;
     	peizhenRecord.recommendDepartment = this.recommendDepartment;
     	peizhenRecord.remarks = this.remarks;
     	peizhenRecord.orderId = this.orderId;
     	peizhenRecord.userId = this.userId;
     	peizhenRecord.creatorId = this.creatorId;
     	peizhenRecord.creatorName = this.creatorName;
     	peizhenRecord.createTime = this.createTime;
     	peizhenRecord.status = this.status;
		return peizhenRecord;
	}
	
	/**
	 * 比较字段：
	 * id, returnExamine, examineTime, rotateExamine, 
	 * rotateTime, recommendDepartment, remarks, orderId, 
	 * userId, creatorId, creatorName, createTime, 
	 * status
	 */
	@Override
	public boolean test(PeizhenRecord t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.returnExamine == null || this.returnExamine.equals(t.returnExamine))
				&& (this.examineTime == null || this.examineTime.equals(t.examineTime))
				&& (this.rotateExamine == null || this.rotateExamine.equals(t.rotateExamine))
				&& (this.rotateTime == null || this.rotateTime.equals(t.rotateTime))
				&& (this.recommendDepartment == null || this.recommendDepartment.equals(t.recommendDepartment))
				&& (this.remarks == null || this.remarks.equals(t.remarks))
				&& (this.orderId == null || this.orderId.equals(t.orderId))
				&& (this.userId == null || this.userId.equals(t.userId))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(PeizhenRecord element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.returnExamine != null) {
			element.returnExamine = this.returnExamine;
		}
		if (this.examineTime != null) {
			element.examineTime = this.examineTime;
		}
		if (this.rotateExamine != null) {
			element.rotateExamine = this.rotateExamine;
		}
		if (this.rotateTime != null) {
			element.rotateTime = this.rotateTime;
		}
		if (this.recommendDepartment != null && !this.recommendDepartment.isEmpty()) {
			element.recommendDepartment = this.recommendDepartment;
		}
		if (this.remarks != null && !this.remarks.isEmpty()) {
			element.remarks = this.remarks;
		}
		if (this.orderId != null && !this.orderId.isEmpty()) {
			element.orderId = this.orderId;
		}
		if (this.userId != null && !this.userId.isEmpty()) {
			element.userId = this.userId;
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
