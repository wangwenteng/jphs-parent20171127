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
 * @date 2017-09-20 13:49:07
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Business extends BaseModel implements Predicate<Business>,
		Updator<Business> {


    /** 家商名称 */
	@Length(max = 255, message = "{business.name.illegal.length}")
	private String name;

    /** 官网地址 */
	@Length(max = 255, message = "{business.officialWebsiteUrl.illegal.length}")
	private String officialWebsiteUrl;

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

    /** 银行 */
	@Length(max = 255, message = "{business.bank.illegal.length}")
	private String bank;

    /** 开户行 */
	@Length(max = 255, message = "{business.openBankAddress.illegal.length}")
	private String openBankAddress;

    /** 卡号 */
	@Length(max = 255, message = "{business.cardNumber.illegal.length}")
	private String cardNumber;

    /** 开卡人 */
	@Length(max = 50, message = "{business.cardName.illegal.length}")
	private String cardName;

    /** 备注 */
	@Length(max = 500, message = "{business.remark.illegal.length}")
	private String remark;

    /** 结算周期 */
	@Length(max = 50, message = "{business.unit.illegal.length}")
	private String unit;

    /** 结算方式 */
	@Length(max = 50, message = "{business.payType.illegal.length}")
	private String payType;

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
	 * 获取官网地址
	 */
	public String getOfficialWebsiteUrl() {
    	return officialWebsiteUrl;
    }
  	
	/**
	 * 设置官网地址
	 */
	public void setOfficialWebsiteUrl(String officialWebsiteUrl) {
    	this.officialWebsiteUrl = officialWebsiteUrl;
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
	 * 获取银行
	 */
	public String getBank() {
    	return bank;
    }
  	
	/**
	 * 设置银行
	 */
	public void setBank(String bank) {
    	this.bank = bank;
    }

	/**
	 * 获取开户行
	 */
	public String getOpenBankAddress() {
    	return openBankAddress;
    }
  	
	/**
	 * 设置开户行
	 */
	public void setOpenBankAddress(String openBankAddress) {
    	this.openBankAddress = openBankAddress;
    }

	/**
	 * 获取卡号
	 */
	public String getCardNumber() {
    	return cardNumber;
    }
  	
	/**
	 * 设置卡号
	 */
	public void setCardNumber(String cardNumber) {
    	this.cardNumber = cardNumber;
    }

	/**
	 * 获取开卡人
	 */
	public String getCardName() {
    	return cardName;
    }
  	
	/**
	 * 设置开卡人
	 */
	public void setCardName(String cardName) {
    	this.cardName = cardName;
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

	/**
	 * 获取结算周期
	 */
	public String getUnit() {
    	return unit;
    }
  	
	/**
	 * 设置结算周期
	 */
	public void setUnit(String unit) {
    	this.unit = unit;
    }

	/**
	 * 获取结算方式
	 */
	public String getPayType() {
    	return payType;
    }
  	
	/**
	 * 设置结算方式
	 */
	public void setPayType(String payType) {
    	this.payType = payType;
    }

    public String toString() {
		return new StringBuilder().append("Business{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",officialWebsiteUrl=").append(officialWebsiteUrl).
			append(",address=").append(address).
			append(",contactsName=").append(contactsName).
			append(",contactsPhone=").append(contactsPhone).
			append(",settlementCycle=").append(settlementCycle).
			append(",bank=").append(bank).
			append(",openBankAddress=").append(openBankAddress).
			append(",cardNumber=").append(cardNumber).
			append(",cardName=").append(cardName).
			append(",status=").append(status).
			append(",remark=").append(remark).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",unit=").append(unit).
			append(",payType=").append(payType).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, officialWebsiteUrl, address, 
	 * contactsName, contactsPhone, settlementCycle, bank, 
	 * openBankAddress, cardNumber, cardName, status, 
	 * remark, createTime, creatorId, creatorName, 
	 * unit, payType
	 */
	public Business copy(){
		Business business = new Business();
     	business.id = this.id;
     	business.name = this.name;
     	business.officialWebsiteUrl = this.officialWebsiteUrl;
     	business.address = this.address;
     	business.contactsName = this.contactsName;
     	business.contactsPhone = this.contactsPhone;
     	business.settlementCycle = this.settlementCycle;
     	business.bank = this.bank;
     	business.openBankAddress = this.openBankAddress;
     	business.cardNumber = this.cardNumber;
     	business.cardName = this.cardName;
     	business.status = this.status;
     	business.remark = this.remark;
     	business.createTime = this.createTime;
     	business.creatorId = this.creatorId;
     	business.creatorName = this.creatorName;
     	business.unit = this.unit;
     	business.payType = this.payType;
		return business;
	}
	
	/**
	 * 比较字段：
	 * id, name, officialWebsiteUrl, address, 
	 * contactsName, contactsPhone, settlementCycle, bank, 
	 * openBankAddress, cardNumber, cardName, status, 
	 * remark, createTime, creatorId, creatorName, 
	 * unit, payType
	 */
	@Override
	public boolean test(Business t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.officialWebsiteUrl == null || this.officialWebsiteUrl.equals(t.officialWebsiteUrl))
				&& (this.address == null || this.address.equals(t.address))
				&& (this.contactsName == null || this.contactsName.equals(t.contactsName))
				&& (this.contactsPhone == null || this.contactsPhone.equals(t.contactsPhone))
				&& (this.settlementCycle == null || this.settlementCycle.equals(t.settlementCycle))
				&& (this.bank == null || this.bank.equals(t.bank))
				&& (this.openBankAddress == null || this.openBankAddress.equals(t.openBankAddress))
				&& (this.cardNumber == null || this.cardNumber.equals(t.cardNumber))
				&& (this.cardName == null || this.cardName.equals(t.cardName))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.unit == null || this.unit.equals(t.unit))
				&& (this.payType == null || this.payType.equals(t.payType))
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
		if (this.officialWebsiteUrl != null && !this.officialWebsiteUrl.isEmpty()) {
			element.officialWebsiteUrl = this.officialWebsiteUrl;
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
		if (this.openBankAddress != null && !this.openBankAddress.isEmpty()) {
			element.openBankAddress = this.openBankAddress;
		}
		if (this.cardNumber != null && !this.cardNumber.isEmpty()) {
			element.cardNumber = this.cardNumber;
		}
		if (this.cardName != null && !this.cardName.isEmpty()) {
			element.cardName = this.cardName;
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
		if (this.unit != null && !this.unit.isEmpty()) {
			element.unit = this.unit;
		}
		if (this.payType != null && !this.payType.isEmpty()) {
			element.payType = this.payType;
		}
	}
}
