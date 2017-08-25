package com.jinpaihushi.jphs.aptitude.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * APTITUDE_NURSE 
 * 继承自父类的字段:
 * id : 	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AptitudeNurse extends BaseModel implements Predicate<AptitudeNurse>,
		Updator<AptitudeNurse> {


    /** 资质ID */
	@Length(max = 50, message = "{aptitudeNurse.aptitudeId.illegal.length}")
	private String aptitudeId;

    /** 护士ID */
	@Length(max = 50, message = "{aptitudeNurse.nurseId.illegal.length}")
	private String nurseId;

    /** 审核标识 */
	@Length(max = 50, message = "{aptitudeNurse.auditId.illegal.length}")
	private String auditId;

    /** 资质商品ID */
	@Length(max = 500, message = "{aptitudeNurse.aptitudeGoodsIds.illegal.length}")
	private String aptitudeGoodsIds;

	public AptitudeNurse(){}

	public AptitudeNurse(String id){
		this.id = id;
	}

	/**
	 * 获取资质ID
	 */
	public String getAptitudeId() {
    	return aptitudeId;
    }
  	
	/**
	 * 设置资质ID
	 */
	public void setAptitudeId(String aptitudeId) {
    	this.aptitudeId = aptitudeId;
    }

	/**
	 * 获取护士ID
	 */
	public String getNurseId() {
    	return nurseId;
    }
  	
	/**
	 * 设置护士ID
	 */
	public void setNurseId(String nurseId) {
    	this.nurseId = nurseId;
    }

	/**
	 * 获取审核标识
	 */
	public String getAuditId() {
    	return auditId;
    }
  	
	/**
	 * 设置审核标识
	 */
	public void setAuditId(String auditId) {
    	this.auditId = auditId;
    }

	/**
	 * 获取资质商品ID
	 */
	public String getAptitudeGoodsIds() {
    	return aptitudeGoodsIds;
    }
  	
	/**
	 * 设置资质商品ID
	 */
	public void setAptitudeGoodsIds(String aptitudeGoodsIds) {
    	this.aptitudeGoodsIds = aptitudeGoodsIds;
    }

    public String toString() {
		return new StringBuilder().append("AptitudeNurse{").
			append("id=").append(id).
			append(",aptitudeId=").append(aptitudeId).
			append(",nurseId=").append(nurseId).
			append(",auditId=").append(auditId).
			append(",aptitudeGoodsIds=").append(aptitudeGoodsIds).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, aptitudeId, nurseId, auditId, 
	 * aptitudeGoodsIds, createTime, status
	 */
	public AptitudeNurse copy(){
		AptitudeNurse aptitudeNurse = new AptitudeNurse();
     	aptitudeNurse.id = this.id;
     	aptitudeNurse.aptitudeId = this.aptitudeId;
     	aptitudeNurse.nurseId = this.nurseId;
     	aptitudeNurse.auditId = this.auditId;
     	aptitudeNurse.aptitudeGoodsIds = this.aptitudeGoodsIds;
     	aptitudeNurse.createTime = this.createTime;
     	aptitudeNurse.status = this.status;
		return aptitudeNurse;
	}
	
	/**
	 * 比较字段：
	 * id, aptitudeId, nurseId, auditId, 
	 * aptitudeGoodsIds, createTime, status
	 */
	@Override
	public boolean test(AptitudeNurse t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.aptitudeId == null || this.aptitudeId.equals(t.aptitudeId))
				&& (this.nurseId == null || this.nurseId.equals(t.nurseId))
				&& (this.auditId == null || this.auditId.equals(t.auditId))
				&& (this.aptitudeGoodsIds == null || this.aptitudeGoodsIds.equals(t.aptitudeGoodsIds))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(AptitudeNurse element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.aptitudeId != null && !this.aptitudeId.isEmpty()) {
			element.aptitudeId = this.aptitudeId;
		}
		if (this.nurseId != null && !this.nurseId.isEmpty()) {
			element.nurseId = this.nurseId;
		}
		if (this.auditId != null && !this.auditId.isEmpty()) {
			element.auditId = this.auditId;
		}
		if (this.aptitudeGoodsIds != null && !this.aptitudeGoodsIds.isEmpty()) {
			element.aptitudeGoodsIds = this.aptitudeGoodsIds;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
