package com.jinpaihushi.jphs.verification.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * VERIFICATION 
 * 继承自父类的字段:
 * status : 	
 * 
 * @author wangwt
 * @date 2017-07-15 10:23:09
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Verification extends BaseModel implements Predicate<Verification>,
		Updator<Verification> {


    /**  */
	@NotEmpty(message = "{verification.id.empty}")
	@Length(max = 50, message = "{verification.id.illegal.length}")
	private String id;

    /**  */
	@Length(max = 128, message = "{verification.phone.illegal.length}")
	private String phone;

    /** 证码验 */
	@Length(max = 128, message = "{verification.code.illegal.length}")
	private String code;

    /**  */
	private Date validTime;

	public Verification(){}

	public Verification(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getId() {
    	return id;
    }
  	
	/**
	 * 设置
	 */
	public void setId(String id) {
    	this.id = id;
    }

	/**
	 * 获取
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * 获取证码验
	 */
	public String getCode() {
    	return code;
    }
  	
	/**
	 * 设置证码验
	 */
	public void setCode(String code) {
    	this.code = code;
    }

	/**
	 * 获取
	 */
	public Date getValidTime() {
    	return validTime;
    }
  	
	/**
	 * 设置
	 */
	public void setValidTime(Date validTime) {
    	this.validTime = validTime;
    }

    public String toString() {
		return new StringBuilder().append("Verification{").
			append("id=").append(id).
			append(",phone=").append(phone).
			append(",code=").append(code).
			append(",validTime=").append(validTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, phone, code, validTime, 
	 * status
	 */
	public Verification copy(){
		Verification verification = new Verification();
     	verification.id = this.id;
     	verification.phone = this.phone;
     	verification.code = this.code;
     	verification.validTime = this.validTime;
     	verification.status = this.status;
		return verification;
	}
	
	/**
	 * 比较字段：
	 * id, phone, code, validTime, 
	 * status
	 */
	@Override
	public boolean test(Verification t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.code == null || this.code.equals(t.code))
				&& (this.validTime == null || this.validTime.equals(t.validTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Verification element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.code != null && !this.code.isEmpty()) {
			element.code = this.code;
		}
		if (this.validTime != null) {
			element.validTime = this.validTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
