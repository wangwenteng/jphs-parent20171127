package com.jinpaihushi.jphs.peizhen.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PEIZHEN_PHARMACY_REMIND 
 * 继承自父类的字段:
 * id : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PeizhenPharmacyRemind extends BaseModel implements Predicate<PeizhenPharmacyRemind>,
		Updator<PeizhenPharmacyRemind> {


    /** 陪诊主表ID */
	@Length(max = 50, message = "{peizhenPharmacyRemind.peizhenRecordId.illegal.length}")
	private String peizhenRecordId;

    /** 药物 */
	@Length(max = 255, message = "{peizhenPharmacyRemind.drug.illegal.length}")
	private String drug;

    /** 用量说明 */
	@Length(max = 65535, message = "{peizhenPharmacyRemind.content.illegal.length}")
	private String content;

	public PeizhenPharmacyRemind(){}

	public PeizhenPharmacyRemind(String id){
		this.id = id;
	}

	/**
	 * 获取陪诊主表ID
	 */
	public String getPeizhenRecordId() {
    	return peizhenRecordId;
    }
  	
	/**
	 * 设置陪诊主表ID
	 */
	public void setPeizhenRecordId(String peizhenRecordId) {
    	this.peizhenRecordId = peizhenRecordId;
    }

	/**
	 * 获取药物
	 */
	public String getDrug() {
    	return drug;
    }
  	
	/**
	 * 设置药物
	 */
	public void setDrug(String drug) {
    	this.drug = drug;
    }

	/**
	 * 获取用量说明
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置用量说明
	 */
	public void setContent(String content) {
    	this.content = content;
    }

    public String toString() {
		return new StringBuilder().append("PeizhenPharmacyRemind{").
			append("id=").append(id).
			append(",peizhenRecordId=").append(peizhenRecordId).
			append(",drug=").append(drug).
			append(",content=").append(content).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, peizhenRecordId, drug, content, 
	 * createTime, creatorId, creatorName, status
	 */
	public PeizhenPharmacyRemind copy(){
		PeizhenPharmacyRemind peizhenPharmacyRemind = new PeizhenPharmacyRemind();
     	peizhenPharmacyRemind.id = this.id;
     	peizhenPharmacyRemind.peizhenRecordId = this.peizhenRecordId;
     	peizhenPharmacyRemind.drug = this.drug;
     	peizhenPharmacyRemind.content = this.content;
     	peizhenPharmacyRemind.createTime = this.createTime;
     	peizhenPharmacyRemind.creatorId = this.creatorId;
     	peizhenPharmacyRemind.creatorName = this.creatorName;
     	peizhenPharmacyRemind.status = this.status;
		return peizhenPharmacyRemind;
	}
	
	/**
	 * 比较字段：
	 * id, peizhenRecordId, drug, content, 
	 * createTime, creatorId, creatorName, status
	 */
	@Override
	public boolean test(PeizhenPharmacyRemind t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.peizhenRecordId == null || this.peizhenRecordId.equals(t.peizhenRecordId))
				&& (this.drug == null || this.drug.equals(t.drug))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(PeizhenPharmacyRemind element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.peizhenRecordId != null && !this.peizhenRecordId.isEmpty()) {
			element.peizhenRecordId = this.peizhenRecordId;
		}
		if (this.drug != null && !this.drug.isEmpty()) {
			element.drug = this.drug;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
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
