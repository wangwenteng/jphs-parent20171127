package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_ACTIVE 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 创建人ID	
 * creatorName : 创建人姓名	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseActive extends BaseModel implements Predicate<NurseActive>,
		Updator<NurseActive> {


    /** 被封护士ID */
	@Length(max = 50, message = "{nurseActive.nurseId.illegal.length}")
	private String nurseId;

    /** 封号标识 */
	private Integer active;

    /** 备注 */
	@Length(max = 65535, message = "{nurseActive.remark.illegal.length}")
	private String remark;

	public NurseActive(){}

	public NurseActive(String id){
		this.id = id;
	}

	/**
	 * 获取被封护士ID
	 */
	public String getNurseId() {
    	return nurseId;
    }
  	
	/**
	 * 设置被封护士ID
	 */
	public void setNurseId(String nurseId) {
    	this.nurseId = nurseId;
    }

	/**
	 * 获取封号标识
	 */
	public Integer getActive() {
    	return active;
    }
  	
	/**
	 * 设置封号标识
	 */
	public void setActive(Integer active) {
    	this.active = active;
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
		return new StringBuilder().append("NurseActive{").
			append("id=").append(id).
			append(",nurseId=").append(nurseId).
			append(",active=").append(active).
			append(",remark=").append(remark).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, nurseId, active, remark, 
	 * creatorId, creatorName, createTime, status
	 */
	public NurseActive copy(){
		NurseActive nurseActive = new NurseActive();
     	nurseActive.id = this.id;
     	nurseActive.nurseId = this.nurseId;
     	nurseActive.active = this.active;
     	nurseActive.remark = this.remark;
     	nurseActive.creatorId = this.creatorId;
     	nurseActive.creatorName = this.creatorName;
     	nurseActive.createTime = this.createTime;
     	nurseActive.status = this.status;
		return nurseActive;
	}
	
	/**
	 * 比较字段：
	 * id, nurseId, active, remark, 
	 * creatorId, creatorName, createTime, status
	 */
	@Override
	public boolean test(NurseActive t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.nurseId == null || this.nurseId.equals(t.nurseId))
				&& (this.active == null || this.active.equals(t.active))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(NurseActive element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.nurseId != null && !this.nurseId.isEmpty()) {
			element.nurseId = this.nurseId;
		}
		if (this.active != null) {
			element.active = this.active;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
