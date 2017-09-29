package com.jinpaihushi.jphs.withdraw.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.model.BaseModel;

/**
 * WITHDRAW_CASH 提现申请记录

 * 继承自父类的字段:
 * id : 	
 * status : 状态 0 待审核 1 审核通过  -1 驳回	
 * creatorId : 	
 * creatorName : 	
 * createTime : 	
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WithdrawCash extends BaseModel implements Predicate<WithdrawCash>,
		Updator<WithdrawCash> {


    /** 提现金额 */
	private Double amount;

    /** 账户姓名 */
	@Length(max = 50, message = "{withdrawCash.accountName.illegal.length}")
	private String accountName;

    /** 支付宝账号 */
	@Length(max = 50, message = "{withdrawCash.alipayAccount.illegal.length}")
	private String alipayAccount;

    /** 审核时间 */
	private Date auditTime;
	
	private String withdrawCashId;
	
	private List<Order> orderList;
	
	/** 备注 驳回时间 */
	@Length(max = 500, message = "{withdrawCash.remark.illegal.length}")
	private String remark;

	public WithdrawCash(){}

	public WithdrawCash(String id){
		this.id = id;
	}

	/**
	 * 获取提现金额
	 */
	public List<Order> getOrderList() {
    	return orderList;
    }
  	
	/**
	 * 设置提现金额
	 */
	public void setOrderList( List<Order> orderList) {
    	this.orderList = orderList;
    }
	
	
	/**
	 * 获取提现金额
	 */
	public String getWithdrawCashId() {
    	return withdrawCashId;
    }
  	
	/**
	 * 设置提现金额
	 */
	public void setWithdrawCashId(String withdrawCashId) {
    	this.withdrawCashId = withdrawCashId;
    }
	
	/**
	 * 获取提现金额
	 */
	public Double getAmount() {
    	return amount;
    }
  	
	/**
	 * 设置提现金额
	 */
	public void setAmount(Double amount) {
    	this.amount = amount;
    }

	/**
	 * 获取账户姓名
	 */
	public String getAccountName() {
    	return accountName;
    }
  	
	/**
	 * 设置账户姓名
	 */
	public void setAccountName(String accountName) {
    	this.accountName = accountName;
    }

	/**
	 * 获取支付宝账号
	 */
	public String getAlipayAccount() {
    	return alipayAccount;
    }
  	
	/**
	 * 设置支付宝账号
	 */
	public void setAlipayAccount(String alipayAccount) {
    	this.alipayAccount = alipayAccount;
    }

	/**
	 * 获取审核时间
	 */
	public Date getAuditTime() {
    	return auditTime;
    }
  	
	/**
	 * 设置审核时间
	 */
	public void setAuditTime(Date auditTime) {
    	this.auditTime = auditTime;
    }

	/**
	 * 获取备注 驳回时间
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置备注 驳回时间
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("WithdrawCash{").
			append("id=").append(id).
			append(",amount=").append(amount).
			append(",accountName=").append(accountName).
			append(",alipayAccount=").append(alipayAccount).
			append(",auditTime=").append(auditTime).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, amount, accountName, alipayAccount, 
	 * auditTime, remark, status, creatorId, 
	 * creatorName, createTime
	 */
	public WithdrawCash copy(){
		WithdrawCash withdrawCash = new WithdrawCash();
     	withdrawCash.id = this.id;
     	withdrawCash.amount = this.amount;
     	withdrawCash.accountName = this.accountName;
     	withdrawCash.alipayAccount = this.alipayAccount;
     	withdrawCash.auditTime = this.auditTime;
     	withdrawCash.remark = this.remark;
     	withdrawCash.status = this.status;
     	withdrawCash.creatorId = this.creatorId;
     	withdrawCash.creatorName = this.creatorName;
     	withdrawCash.createTime = this.createTime;
		return withdrawCash;
	}
	
	/**
	 * 比较字段：
	 * id, amount, accountName, alipayAccount, 
	 * auditTime, remark, status, creatorId, 
	 * creatorName, createTime
	 */
	@Override
	public boolean test(WithdrawCash t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.amount == null || this.amount.equals(t.amount))
				&& (this.accountName == null || this.accountName.equals(t.accountName))
				&& (this.alipayAccount == null || this.alipayAccount.equals(t.alipayAccount))
				&& (this.auditTime == null || this.auditTime.equals(t.auditTime))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(WithdrawCash element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.amount != null) {
			element.amount = this.amount;
		}
		if (this.accountName != null && !this.accountName.isEmpty()) {
			element.accountName = this.accountName;
		}
		if (this.alipayAccount != null && !this.alipayAccount.isEmpty()) {
			element.alipayAccount = this.alipayAccount;
		}
		if (this.auditTime != null) {
			element.auditTime = this.auditTime;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.status != null) {
			element.status = this.status;
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
	}
}
