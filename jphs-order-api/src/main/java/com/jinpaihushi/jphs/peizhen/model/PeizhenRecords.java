package com.jinpaihushi.jphs.peizhen.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

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
public class PeizhenRecords implements Serializable {


    /** 是否复诊 */
	private Integer returnExamine;

    /** 复诊时间 */
	private String examineTimes;

    /** 是否转诊 */
	private Integer rotateExamine;

    /** 转诊时间 */
	private String rotateTimes;

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

	public PeizhenRecords(){}

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
	public String getExamineTimes() {
    	return examineTimes;
    }
  	
	/**
	 * 设置复诊时间
	 */
	public void setExamineTime(String examineTimes) {
    	this.examineTimes = examineTimes;
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
	public String getRotateTimes() {
    	return rotateTimes;
    }
  	
	/**
	 * 设置转诊时间
	 */
	public void setRotateTimes(String rotateTimes) {
    	this.rotateTimes = rotateTimes;
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

}
