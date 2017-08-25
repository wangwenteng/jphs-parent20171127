package com.jinpaihushi.jphs.recovered.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * RECOVERED_RECORD 
 * 继承自父类的字段:
 * id : 	
 * createTime : 创建时间	
 * creatorId : 创建人	
 * creatorName : 创建人姓名	
 * status : 	
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RecoveredRecord extends BaseModel implements Predicate<RecoveredRecord>,
		Updator<RecoveredRecord> {


    /** 患者姓名 */
	@Length(max = 50, message = "{recoveredRecord.name.illegal.length}")
	private String name;

    /** 患者性别 */
	private Integer sex;

    /** 患者年龄 */
	private Integer age;

    /** 评估 */
	@Length(max = 65535, message = "{recoveredRecord.estimate.illegal.length}")
	private String estimate;

    /** 建议 */
	@Length(max = 65535, message = "{recoveredRecord.propose.illegal.length}")
	private String propose;

    /** 对应的订单ID */
	@Length(max = 50, message = "{recoveredRecord.orderId.illegal.length}")
	private String orderId;

    /** 该订单的下单人ID */
	@Length(max = 50, message = "{recoveredRecord.userId.illegal.length}")
	private String userId;

	public RecoveredRecord(){}

	public RecoveredRecord(String id){
		this.id = id;
	}

	/**
	 * 获取患者姓名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置患者姓名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取患者性别
	 */
	public Integer getSex() {
    	return sex;
    }
  	
	/**
	 * 设置患者性别
	 */
	public void setSex(Integer sex) {
    	this.sex = sex;
    }

	/**
	 * 获取患者年龄
	 */
	public Integer getAge() {
    	return age;
    }
  	
	/**
	 * 设置患者年龄
	 */
	public void setAge(Integer age) {
    	this.age = age;
    }

	/**
	 * 获取评估
	 */
	public String getEstimate() {
    	return estimate;
    }
  	
	/**
	 * 设置评估
	 */
	public void setEstimate(String estimate) {
    	this.estimate = estimate;
    }

	/**
	 * 获取建议
	 */
	public String getPropose() {
    	return propose;
    }
  	
	/**
	 * 设置建议
	 */
	public void setPropose(String propose) {
    	this.propose = propose;
    }

	/**
	 * 获取对应的订单ID
	 */
	public String getOrderId() {
    	return orderId;
    }
  	
	/**
	 * 设置对应的订单ID
	 */
	public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

	/**
	 * 获取该订单的下单人ID
	 */
	public String getUserId() {
    	return userId;
    }
  	
	/**
	 * 设置该订单的下单人ID
	 */
	public void setUserId(String userId) {
    	this.userId = userId;
    }

    public String toString() {
		return new StringBuilder().append("RecoveredRecord{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",sex=").append(sex).
			append(",age=").append(age).
			append(",estimate=").append(estimate).
			append(",propose=").append(propose).
			append(",orderId=").append(orderId).
			append(",userId=").append(userId).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, sex, age, 
	 * estimate, propose, orderId, userId, 
	 * createTime, creatorId, creatorName, status
	 */
	public RecoveredRecord copy(){
		RecoveredRecord recoveredRecord = new RecoveredRecord();
     	recoveredRecord.id = this.id;
     	recoveredRecord.name = this.name;
     	recoveredRecord.sex = this.sex;
     	recoveredRecord.age = this.age;
     	recoveredRecord.estimate = this.estimate;
     	recoveredRecord.propose = this.propose;
     	recoveredRecord.orderId = this.orderId;
     	recoveredRecord.userId = this.userId;
     	recoveredRecord.createTime = this.createTime;
     	recoveredRecord.creatorId = this.creatorId;
     	recoveredRecord.creatorName = this.creatorName;
     	recoveredRecord.status = this.status;
		return recoveredRecord;
	}
	
	/**
	 * 比较字段：
	 * id, name, sex, age, 
	 * estimate, propose, orderId, userId, 
	 * createTime, creatorId, creatorName, status
	 */
	@Override
	public boolean test(RecoveredRecord t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sex == null || this.sex.equals(t.sex))
				&& (this.age == null || this.age.equals(t.age))
				&& (this.estimate == null || this.estimate.equals(t.estimate))
				&& (this.propose == null || this.propose.equals(t.propose))
				&& (this.orderId == null || this.orderId.equals(t.orderId))
				&& (this.userId == null || this.userId.equals(t.userId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(RecoveredRecord element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.sex != null) {
			element.sex = this.sex;
		}
		if (this.age != null) {
			element.age = this.age;
		}
		if (this.estimate != null && !this.estimate.isEmpty()) {
			element.estimate = this.estimate;
		}
		if (this.propose != null && !this.propose.isEmpty()) {
			element.propose = this.propose;
		}
		if (this.orderId != null && !this.orderId.isEmpty()) {
			element.orderId = this.orderId;
		}
		if (this.userId != null && !this.userId.isEmpty()) {
			element.userId = this.userId;
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
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
