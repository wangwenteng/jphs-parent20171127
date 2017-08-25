package com.jinpaihushi.jphs.business.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * BUSINESS 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-08 17:28:55
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Business extends BaseModel implements Predicate<Business>,
		Updator<Business> {


    /** 家商名称 */
	@Length(max = 255, message = "{business.name.illegal.length}")
	private String name;

    /** 商家地址 */
	@Length(max = 255, message = "{business.address.illegal.length}")
	private String address;

    /** 联系人 */
	@Length(max = 50, message = "{business.contactsName.illegal.length}")
	private String contactsName;

    /** 联系电话 */
	@Length(max = 50, message = "{business.contactsPhone.illegal.length}")
	private String contactsPhone;

    /** 算结周期 */
	@Length(max = 50, message = "{business.settlementCycle.illegal.length}")
	private String settlementCycle;

    /**  */
	@Length(max = 255, message = "{business.bank.illegal.length}")
	private String bank;

    /** 开户行 */
	@Length(max = 255, message = "{business.openingbank.illegal.length}")
	private String openingbank;

    /**  */
	@Length(max = 255, message = "{business.cardnumber.illegal.length}")
	private String cardnumber;

    /** 备注 */
	@Length(max = 500, message = "{business.remark.illegal.length}")
	private String remark;

	public Business(){}

	public Business(String id){
		this.id = id;
	}

	/**
	 * 获取家商名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置家商名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取商家地址
	 */
	public String getAddress() {
    	return address;
    }
  	
	/**
	 * 设置商家地址
	 */
	public void setAddress(String address) {
    	this.address = address;
    }

	/**
	 * 获取联系人
	 */
	public String getContactsName() {
    	return contactsName;
    }
  	
	/**
	 * 设置联系人
	 */
	public void setContactsName(String contactsName) {
    	this.contactsName = contactsName;
    }

	/**
	 * 获取联系电话
	 */
	public String getContactsPhone() {
    	return contactsPhone;
    }
  	
	/**
	 * 设置联系电话
	 */
	public void setContactsPhone(String contactsPhone) {
    	this.contactsPhone = contactsPhone;
    }

	/**
	 * 获取算结周期
	 */
	public String getSettlementCycle() {
    	return settlementCycle;
    }
  	
	/**
	 * 设置算结周期
	 */
	public void setSettlementCycle(String settlementCycle) {
    	this.settlementCycle = settlementCycle;
    }

	/**
	 * 获取
	 */
	public String getBank() {
    	return bank;
    }
  	
	/**
	 * 设置
	 */
	public void setBank(String bank) {
    	this.bank = bank;
    }

	/**
	 * 获取开户行
	 */
	public String getOpeningbank() {
    	return openingbank;
    }
  	
	/**
	 * 设置开户行
	 */
	public void setOpeningbank(String openingbank) {
    	this.openingbank = openingbank;
    }

	/**
	 * 获取
	 */
	public String getCardnumber() {
    	return cardnumber;
    }
  	
	/**
	 * 设置
	 */
	public void setCardnumber(String cardnumber) {
    	this.cardnumber = cardnumber;
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
		return new StringBuilder().append("Business{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",address=").append(address).
			append(",contactsName=").append(contactsName).
			append(",contactsPhone=").append(contactsPhone).
			append(",settlementCycle=").append(settlementCycle).
			append(",bank=").append(bank).
			append(",openingbank=").append(openingbank).
			append(",cardnumber=").append(cardnumber).
			append(",status=").append(status).
			append(",remark=").append(remark).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, address, contactsName, 
	 * contactsPhone, settlementCycle, bank, openingbank, 
	 * cardnumber, status, remark, createTime, 
	 * creatorId, creatorName
	 */
	public Business copy(){
		Business business = new Business();
     	business.id = this.id;
     	business.name = this.name;
     	business.address = this.address;
     	business.contactsName = this.contactsName;
     	business.contactsPhone = this.contactsPhone;
     	business.settlementCycle = this.settlementCycle;
     	business.bank = this.bank;
     	business.openingbank = this.openingbank;
     	business.cardnumber = this.cardnumber;
     	business.status = this.status;
     	business.remark = this.remark;
     	business.createTime = this.createTime;
     	business.creatorId = this.creatorId;
     	business.creatorName = this.creatorName;
		return business;
	}
	
	/**
	 * 比较字段：
	 * id, name, address, contactsName, 
	 * contactsPhone, settlementCycle, bank, openingbank, 
	 * cardnumber, status, remark, createTime, 
	 * creatorId, creatorName
	 */
	@Override
	public boolean test(Business t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.address == null || this.address.equals(t.address))
				&& (this.contactsName == null || this.contactsName.equals(t.contactsName))
				&& (this.contactsPhone == null || this.contactsPhone.equals(t.contactsPhone))
				&& (this.settlementCycle == null || this.settlementCycle.equals(t.settlementCycle))
				&& (this.bank == null || this.bank.equals(t.bank))
				&& (this.openingbank == null || this.openingbank.equals(t.openingbank))
				&& (this.cardnumber == null || this.cardnumber.equals(t.cardnumber))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Business element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.address != null && !this.address.isEmpty()) {
			element.address = this.address;
		}
		if (this.contactsName != null && !this.contactsName.isEmpty()) {
			element.contactsName = this.contactsName;
		}
		if (this.contactsPhone != null && !this.contactsPhone.isEmpty()) {
			element.contactsPhone = this.contactsPhone;
		}
		if (this.settlementCycle != null && !this.settlementCycle.isEmpty()) {
			element.settlementCycle = this.settlementCycle;
		}
		if (this.bank != null && !this.bank.isEmpty()) {
			element.bank = this.bank;
		}
		if (this.openingbank != null && !this.openingbank.isEmpty()) {
			element.openingbank = this.openingbank;
		}
		if (this.cardnumber != null && !this.cardnumber.isEmpty()) {
			element.cardnumber = this.cardnumber;
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
