package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_INSTITUTIONS 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-09-03 13:35:33
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseInstitutions extends BaseModel implements Predicate<NurseInstitutions>,
		Updator<NurseInstitutions> {


    /** 机构名称 */
	@Length(max = 255, message = "{nurseInstitutions.name.illegal.length}")
	private String name;

    /** 负责人id,机构在平台负责派单的id */
	@Length(max = 50, message = "{nurseInstitutions.chargeId.illegal.length}")
	private String chargeId;

    /** 机构联系人 */
	@Length(max = 50, message = "{nurseInstitutions.contactsName.illegal.length}")
	private String contactsName;

    /** 机构联系电话 */
	@Length(max = 50, message = "{nurseInstitutions.contactsPhone.illegal.length}")
	private String contactsPhone;

    /** 构机地址 */
	@Length(max = 255, message = "{nurseInstitutions.address.illegal.length}")
	private String address;

    /**  */
	@Length(max = 500, message = "{nurseInstitutions.remark.illegal.length}")
	private String remark;

	public NurseInstitutions(){}

	public NurseInstitutions(String id){
		this.id = id;
	}

	/**
	 * 获取机构名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置机构名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取负责人id,机构在平台负责派单的id
	 */
	public String getChargeId() {
    	return chargeId;
    }
  	
	/**
	 * 设置负责人id,机构在平台负责派单的id
	 */
	public void setChargeId(String chargeId) {
    	this.chargeId = chargeId;
    }

	/**
	 * 获取机构联系人
	 */
	public String getContactsName() {
    	return contactsName;
    }
  	
	/**
	 * 设置机构联系人
	 */
	public void setContactsName(String contactsName) {
    	this.contactsName = contactsName;
    }

	/**
	 * 获取机构联系电话
	 */
	public String getContactsPhone() {
    	return contactsPhone;
    }
  	
	/**
	 * 设置机构联系电话
	 */
	public void setContactsPhone(String contactsPhone) {
    	this.contactsPhone = contactsPhone;
    }

	/**
	 * 获取构机地址
	 */
	public String getAddress() {
    	return address;
    }
  	
	/**
	 * 设置构机地址
	 */
	public void setAddress(String address) {
    	this.address = address;
    }

	/**
	 * 获取
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("NurseInstitutions{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",chargeId=").append(chargeId).
			append(",contactsName=").append(contactsName).
			append(",contactsPhone=").append(contactsPhone).
			append(",address=").append(address).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, chargeId, contactsName, 
	 * contactsPhone, address, remark, status, 
	 * createTime, creatorId, creatorName
	 */
	public NurseInstitutions copy(){
		NurseInstitutions nurseInstitutions = new NurseInstitutions();
     	nurseInstitutions.id = this.id;
     	nurseInstitutions.name = this.name;
     	nurseInstitutions.chargeId = this.chargeId;
     	nurseInstitutions.contactsName = this.contactsName;
     	nurseInstitutions.contactsPhone = this.contactsPhone;
     	nurseInstitutions.address = this.address;
     	nurseInstitutions.remark = this.remark;
     	nurseInstitutions.status = this.status;
     	nurseInstitutions.createTime = this.createTime;
     	nurseInstitutions.creatorId = this.creatorId;
     	nurseInstitutions.creatorName = this.creatorName;
		return nurseInstitutions;
	}
	
	/**
	 * 比较字段：
	 * id, name, chargeId, contactsName, 
	 * contactsPhone, address, remark, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(NurseInstitutions t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.chargeId == null || this.chargeId.equals(t.chargeId))
				&& (this.contactsName == null || this.contactsName.equals(t.contactsName))
				&& (this.contactsPhone == null || this.contactsPhone.equals(t.contactsPhone))
				&& (this.address == null || this.address.equals(t.address))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(NurseInstitutions element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.chargeId != null && !this.chargeId.isEmpty()) {
			element.chargeId = this.chargeId;
		}
		if (this.contactsName != null && !this.contactsName.isEmpty()) {
			element.contactsName = this.contactsName;
		}
		if (this.contactsPhone != null && !this.contactsPhone.isEmpty()) {
			element.contactsPhone = this.contactsPhone;
		}
		if (this.address != null && !this.address.isEmpty()) {
			element.address = this.address;
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
