package com.jinpaihushi.jphs.family.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_VOUCHER 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1删除）	
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
public class FamilyVoucher extends BaseModel implements Predicate<FamilyVoucher>,
		Updator<FamilyVoucher> {


    /**  */
	@Length(max = 50, message = "{familyVoucher.voucherId.illegal.length}")
	private String voucherId;

    /** 备注说明 */
	@Length(max = 500, message = "{familyVoucher.remark.illegal.length}")
	private String remark;

	public FamilyVoucher(){}

	public FamilyVoucher(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getVoucherId() {
    	return voucherId;
    }
  	
	/**
	 * 设置
	 */
	public void setVoucherId(String voucherId) {
    	this.voucherId = voucherId;
    }

	/**
	 * 获取备注说明
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置备注说明
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("FamilyVoucher{").
			append("id=").append(id).
			append(",voucherId=").append(voucherId).
			append(",status=").append(status).
			append(",remark=").append(remark).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, voucherId, status, remark, 
	 * createTime, creatorId, creatorName
	 */
	public FamilyVoucher copy(){
		FamilyVoucher familyVoucher = new FamilyVoucher();
     	familyVoucher.id = this.id;
     	familyVoucher.voucherId = this.voucherId;
     	familyVoucher.status = this.status;
     	familyVoucher.remark = this.remark;
     	familyVoucher.createTime = this.createTime;
     	familyVoucher.creatorId = this.creatorId;
     	familyVoucher.creatorName = this.creatorName;
		return familyVoucher;
	}
	
	/**
	 * 比较字段：
	 * id, voucherId, status, remark, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(FamilyVoucher t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.voucherId == null || this.voucherId.equals(t.voucherId))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyVoucher element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.voucherId != null && !this.voucherId.isEmpty()) {
			element.voucherId = this.voucherId;
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
