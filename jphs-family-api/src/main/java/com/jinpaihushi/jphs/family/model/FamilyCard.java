package com.jinpaihushi.jphs.family.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_CARD 
 * 继承自父类的字段:
 * id : 	
 * type : 类型（0虚拟卡，1实体卡）	
 * status : -1删除，0未使用，1已使用	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-09-22 15:56:53
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FamilyCard extends BaseModel implements Predicate<FamilyCard>,
		Updator<FamilyCard> {


    /** 卡号 */
	@Length(max = 50, message = "{familyCard.no.illegal.length}")
	private String no;

    /** 兑换码 */
	@Length(max = 50, message = "{familyCard.code.illegal.length}")
	private String code;

    /** 绑定人 */
	@Length(max = 50, message = "{familyCard.remmond.illegal.length}")
	private String remmond;

    /** 批次号 */
	@Length(max = 50, message = "{familyCard.batchNo.illegal.length}")
	private String batchNo;

    /** 使用时间 */
	private Date useTime;

    /** 备注 */
	@Length(max = 255, message = "{familyCard.remark.illegal.length}")
	private String remark;

	public FamilyCard(){}

	public FamilyCard(String id){
		this.id = id;
	}

	/**
	 * 获取卡号
	 */
	public String getNo() {
    	return no;
    }
  	
	/**
	 * 设置卡号
	 */
	public void setNo(String no) {
    	this.no = no;
    }

	/**
	 * 获取兑换码
	 */
	public String getCode() {
    	return code;
    }
  	
	/**
	 * 设置兑换码
	 */
	public void setCode(String code) {
    	this.code = code;
    }

	/**
	 * 获取绑定人
	 */
	public String getRemmond() {
    	return remmond;
    }
  	
	/**
	 * 设置绑定人
	 */
	public void setRemmond(String remmond) {
    	this.remmond = remmond;
    }

	/**
	 * 获取批次号
	 */
	public String getBatchNo() {
    	return batchNo;
    }
  	
	/**
	 * 设置批次号
	 */
	public void setBatchNo(String batchNo) {
    	this.batchNo = batchNo;
    }

	/**
	 * 获取使用时间
	 */
	public Date getUseTime() {
    	return useTime;
    }
  	
	/**
	 * 设置使用时间
	 */
	public void setUseTime(Date useTime) {
    	this.useTime = useTime;
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
		return new StringBuilder().append("FamilyCard{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",no=").append(no).
			append(",code=").append(code).
			append(",remmond=").append(remmond).
			append(",batchNo=").append(batchNo).
			append(",useTime=").append(useTime).
			append(",status=").append(status).
			append(",remark=").append(remark).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, no, code, 
	 * remmond, batchNo, useTime, status, 
	 * remark, createTime, creatorId, creatorName
	 */
	public FamilyCard copy(){
		FamilyCard familyCard = new FamilyCard();
     	familyCard.id = this.id;
     	familyCard.type = this.type;
     	familyCard.no = this.no;
     	familyCard.code = this.code;
     	familyCard.remmond = this.remmond;
     	familyCard.batchNo = this.batchNo;
     	familyCard.useTime = this.useTime;
     	familyCard.status = this.status;
     	familyCard.remark = this.remark;
     	familyCard.createTime = this.createTime;
     	familyCard.creatorId = this.creatorId;
     	familyCard.creatorName = this.creatorName;
		return familyCard;
	}
	
	/**
	 * 比较字段：
	 * id, type, no, code, 
	 * remmond, batchNo, useTime, status, 
	 * remark, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(FamilyCard t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.no == null || this.no.equals(t.no))
				&& (this.code == null || this.code.equals(t.code))
				&& (this.remmond == null || this.remmond.equals(t.remmond))
				&& (this.batchNo == null || this.batchNo.equals(t.batchNo))
				&& (this.useTime == null || this.useTime.equals(t.useTime))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyCard element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.no != null && !this.no.isEmpty()) {
			element.no = this.no;
		}
		if (this.code != null && !this.code.isEmpty()) {
			element.code = this.code;
		}
		if (this.remmond != null && !this.remmond.isEmpty()) {
			element.remmond = this.remmond;
		}
		if (this.batchNo != null && !this.batchNo.isEmpty()) {
			element.batchNo = this.batchNo;
		}
		if (this.useTime != null) {
			element.useTime = this.useTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
