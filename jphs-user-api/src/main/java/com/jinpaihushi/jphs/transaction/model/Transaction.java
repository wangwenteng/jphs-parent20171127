package com.jinpaihushi.jphs.transaction.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * TRANSACTION 
 * 继承自父类的字段:
 * id : 交易记录id	
 * creatorId : 创建人ID	
 * creatorName : 创建人	
 * createTime : 创建时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author yangsong
 * @date 2017-06-29 18:40:45
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Transaction extends BaseModel implements Predicate<Transaction>,
		Updator<Transaction> {


    /** 订单id */
	@Length(max = 50, message = "{transaction.orderId.illegal.length}")
	private String orderId;

    /** 金额 */
	private Double amount;

    /** 积分 */
	private Integer score;

    /** 操作(1.提现, 2.充值, 3.消费 , 4.收入, 5.系统调整) */
	private Integer operate;

    /** 备注 */
	@Length(max = 65535, message = "{transaction.remark.illegal.length}")
	private String remark;

    /** 是否已提现 1=是 0=否 */
	private Integer withdraw;

    /** 支付方式 */
	private Integer payType;

    /** 交易号 */
	@Length(max = 100, message = "{transaction.outTradeNo.illegal.length}")
	private String outTradeNo;
	
	private String name;
	
	private String phone;
	
	private Integer operate_source;
	
	private Date beginTime;
	
	private Date stopTime; 

	public Transaction(){}

	public Transaction(String id){
		this.id = id;
	}

	/**
	 * 获取操作来源
	 */
	public Integer getOperateSource() {
    	return operate_source;
    }
  	
	/**
	 * 设置操作来源
	 */
	public void setOperateSource(Integer operate_source) {
    	this.operate_source = operate_source;
    }
	
	/**
	 * 获取订单id
	 */
	public String getOrderId() {
    	return orderId;
    }
  	
	/**
	 * 设置订单id
	 */
	public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

	/**
	 * 获取金额
	 */
	public Double getAmount() {
    	return amount;
    }
  	
	/**
	 * 设置金额
	 */
	public void setAmount(Double amount) {
    	this.amount = amount;
    }

	/**
	 * 获取积分
	 */
	public Integer getScore() {
    	return score;
    }
  	
	/**
	 * 设置积分
	 */
	public void setScore(Integer score) {
    	this.score = score;
    }

	/**
	 * 获取操作(1.提现, 2.充值, 3.消费 , 4.收入, 5.系统调整)
	 */
	public Integer getOperate() {
    	return operate;
    }
  	
	/**
	 * 设置操作(1.提现, 2.充值, 3.消费 , 4.收入, 5.系统调整)
	 */
	public void setOperate(Integer operate) {
    	this.operate = operate;
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
	 * 获取是否已提现 1=是 0=否
	 */
	public Integer getWithdraw() {
    	return withdraw;
    }
  	
	/**
	 * 设置是否已提现 1=是 0=否
	 */
	public void setWithdraw(Integer withdraw) {
    	this.withdraw = withdraw;
    }

	/**
	 * 获取支付方式
	 */
	public Integer getPayType() {
    	return payType;
    }
  	
	/**
	 * 设置支付方式
	 */
	public void setPayType(Integer payType) {
    	this.payType = payType;
    }

	/**
	 * 获取交易号
	 */
	public String getOutTradeNo() {
    	return outTradeNo;
    }
  	
	/**
	 * 设置交易号
	 */
	public void setOutTradeNo(String outTradeNo) {
    	this.outTradeNo = outTradeNo;
    }

	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String toString() {
		return new StringBuilder().append("Transaction{").
			append("id=").append(id).
			append(",orderId=").append(orderId).
			append(",amount=").append(amount).
			append(",score=").append(score).
			append(",operate=").append(operate).
			append(",remark=").append(remark).
			append(",withdraw=").append(withdraw).
			append(",payType=").append(payType).
			append(",outTradeNo=").append(outTradeNo).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, orderId, amount, score, 
	 * operate, remark, withdraw, payType, 
	 * outTradeNo, creatorId, creatorName, createTime, 
	 * status
	 */
	public Transaction copy(){
		Transaction transaction = new Transaction();
     	transaction.id = this.id;
     	transaction.orderId = this.orderId;
     	transaction.amount = this.amount;
     	transaction.score = this.score;
     	transaction.operate = this.operate;
     	transaction.remark = this.remark;
     	transaction.withdraw = this.withdraw;
     	transaction.payType = this.payType;
     	transaction.outTradeNo = this.outTradeNo;
     	transaction.creatorId = this.creatorId;
     	transaction.creatorName = this.creatorName;
     	transaction.createTime = this.createTime;
     	transaction.status = this.status;
		return transaction;
	}
	
	/**
	 * 比较字段：
	 * id, orderId, amount, score, 
	 * operate, remark, withdraw, payType, 
	 * outTradeNo, creatorId, creatorName, createTime, 
	 * status
	 */
	@Override
	public boolean test(Transaction t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.orderId == null || this.orderId.equals(t.orderId))
				&& (this.amount == null || this.amount.equals(t.amount))
				&& (this.score == null || this.score.equals(t.score))
				&& (this.operate == null || this.operate.equals(t.operate))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.withdraw == null || this.withdraw.equals(t.withdraw))
				&& (this.payType == null || this.payType.equals(t.payType))
				&& (this.outTradeNo == null || this.outTradeNo.equals(t.outTradeNo))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Transaction element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.orderId != null && !this.orderId.isEmpty()) {
			element.orderId = this.orderId;
		}
		if (this.amount != null) {
			element.amount = this.amount;
		}
		if (this.score != null) {
			element.score = this.score;
		}
		if (this.operate != null) {
			element.operate = this.operate;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.withdraw != null) {
			element.withdraw = this.withdraw;
		}
		if (this.payType != null) {
			element.payType = this.payType;
		}
		if (this.outTradeNo != null && !this.outTradeNo.isEmpty()) {
			element.outTradeNo = this.outTradeNo;
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
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
