package com.jinpaihushi.jphs.activity.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * VOUCHER_USE 
 * 继承自父类的字段:
 * id : id	
 * creatorName : 创建人	
 * creatorId : 会员ID	
 * createTime : 兑换时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author zhangzd
 * @date 2017-06-26 14:48:27
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class VoucherUse extends BaseModel implements Predicate<VoucherUse>,
		Updator<VoucherUse> {


    /** 优惠券仓库id */
	@Length(max = 50, message = "{voucherUse.voucherRepertoryId.illegal.length}")
	private String voucherRepertoryId;

    /** 领取人手机号 */
	@Length(max = 50, message = "{voucherUse.phone.illegal.length}")
	private String phone;

    /** 优惠券金额 */
	private Double amount;

    /** 有效期开始时间 */
	private Date startTime;

    /** 有效期结束时间 */
	private Date endTime;

    /** 使用时间 */
	private Date useTime;
	
	private Date beginTime;
	
	private Date stopTime;
	
	private Integer type;
	
	private String userName;
	
	private String repertoryId;
	
	private String grantName;
	
	private String no;
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public VoucherUse(){}

	public VoucherUse(String id){
		this.id = id;
	}

	/**
	 * 获取优惠券仓库id
	 */
	public String getVoucherRepertoryId() {
    	return voucherRepertoryId;
    }
  	
	/**
	 * 设置优惠券仓库id
	 */
	public void setVoucherRepertoryId(String voucherRepertoryId) {
    	this.voucherRepertoryId = voucherRepertoryId;
    }

	/**
	 * 获取领取人手机号
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置领取人手机号
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * 获取优惠券金额
	 */
	public Double getAmount() {
    	return amount;
    }
  	
	/**
	 * 设置优惠券金额
	 */
	public void setAmount(Double amount) {
    	this.amount = amount;
    }

	/**
	 * 获取有效期开始时间
	 */
	public Date getStartTime() {
    	return startTime;
    }
  	
	/**
	 * 设置有效期开始时间
	 */
	public void setStartTime(Date startTime) {
    	this.startTime = startTime;
    }

	/**
	 * 获取有效期结束时间
	 */
	public Date getEndTime() {
    	return endTime;
    }
  	
	/**
	 * 设置有效期结束时间
	 */
	public void setEndTime(Date endTime) {
    	this.endTime = endTime;
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

    public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	 
	
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(String repertoryId) {
		this.repertoryId = repertoryId;
	}

	 

	public String getGrantName() {
		return grantName;
	}

	public void setGrantName(String grantName) {
		this.grantName = grantName;
	}

	/**
	 * 复制字段：
	 * id, voucherRepertoryId, phone, amount, 
	 * startTime, endTime, useTime, creatorName, 
	 * creatorId, createTime, status
	 */
	public VoucherUse copy(){
		VoucherUse voucherUse = new VoucherUse();
     	voucherUse.id = this.id;
     	voucherUse.voucherRepertoryId = this.voucherRepertoryId;
     	voucherUse.phone = this.phone;
     	voucherUse.amount = this.amount;
     	voucherUse.startTime = this.startTime;
     	voucherUse.endTime = this.endTime;
     	voucherUse.useTime = this.useTime;
     	voucherUse.creatorName = this.creatorName;
     	voucherUse.creatorId = this.creatorId;
     	voucherUse.createTime = this.createTime;
     	voucherUse.status = this.status;
		return voucherUse;
	}
	
	@Override
	public String toString() {
		return "VoucherUse [voucherRepertoryId=" + voucherRepertoryId + ", phone=" + phone + ", amount=" + amount
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", useTime=" + useTime + ", beginTime="
				+ beginTime + ", stopTime=" + stopTime + ", type=" + type + ", userName=" + userName + ", repertoryId="
				+ repertoryId + "]";
	}

	/**
	 * 比较字段：
	 * id, voucherRepertoryId, phone, amount, 
	 * startTime, endTime, useTime, creatorName, 
	 * creatorId, createTime, status
	 */
	@Override
	public boolean test(VoucherUse t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.voucherRepertoryId == null || this.voucherRepertoryId.equals(t.voucherRepertoryId))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.amount == null || this.amount.equals(t.amount))
				&& (this.startTime == null || this.startTime.equals(t.startTime))
				&& (this.endTime == null || this.endTime.equals(t.endTime))
				&& (this.useTime == null || this.useTime.equals(t.useTime))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(VoucherUse element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.voucherRepertoryId != null && !this.voucherRepertoryId.isEmpty()) {
			element.voucherRepertoryId = this.voucherRepertoryId;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.amount != null) {
			element.amount = this.amount;
		}
		if (this.startTime != null) {
			element.startTime = this.startTime;
		}
		if (this.endTime != null) {
			element.endTime = this.endTime;
		}
		if (this.useTime != null) {
			element.useTime = this.useTime;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}

	 
}
