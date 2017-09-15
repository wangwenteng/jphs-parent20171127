package com.jinpaihushi.jphs.account.model;

import java.util.function.Predicate;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * ACCOUNT 
 * 继承自父类的字段:
 * id : 账户id	
 * creatorName : 创建人	
 * creatorId : 创建人ID	
 * createTime : 创建时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author yangsong
 * @date 2017-06-29 15:40:16
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Account extends BaseModel implements Predicate<Account>, Updator<Account> {


    /** 余额 */
	private Double balance;

    /** 积累积分 */
	private Integer score;

    /** 剩余积分 */
	private Integer availableScore;

	public Account(){}

	public Account(String id){
		this.id = id;
	}

	/**
	 * 获取余额
	 */
	public Double getBalance() {
    	return balance;
    }
  	
	/**
	 * 设置余额
	 */
	public void setBalance(Double balance) {
    	this.balance = balance;
    }

	/**
	 * 获取积累积分
	 */
	public Integer getScore() {
    	return score;
    }
  	
	/**
	 * 设置积累积分
	 */
	public void setScore(Integer score) {
    	this.score = score;
    }

	/**
	 * 获取剩余积分
	 */
	public Integer getAvailableScore() {
    	return availableScore;
    }
  	
	/**
	 * 设置剩余积分
	 */
	public void setAvailableScore(Integer availableScore) {
    	this.availableScore = availableScore;
    }

    public String toString() {
		return new StringBuilder().append("Account{").
			append("id=").append(id).
			append(",balance=").append(balance).
			append(",score=").append(score).
			append(",availableScore=").append(availableScore).
			append(",creatorName=").append(creatorName).
			append(",creatorId=").append(creatorId).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, balance, score, availableScore, 
	 * creatorName, creatorId, createTime, status
	 */
	public Account copy(){
		Account account = new Account();
     	account.id = this.id;
     	account.balance = this.balance;
     	account.score = this.score;
     	account.availableScore = this.availableScore;
     	account.creatorName = this.creatorName;
     	account.creatorId = this.creatorId;
     	account.createTime = this.createTime;
     	account.status = this.status;
		return account;
	}
	
	/**
	 * 比较字段：
	 * id, balance, score, availableScore, 
	 * creatorName, creatorId, createTime, status
	 */
	@Override
	public boolean test(Account t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.balance == null || this.balance.equals(t.balance))
				&& (this.score == null || this.score.equals(t.score))
				&& (this.availableScore == null || this.availableScore.equals(t.availableScore))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Account element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.balance != null) {
			element.balance = this.balance;
		}
		if (this.score != null) {
			element.score = this.score;
		}
		if (this.availableScore != null) {
			element.availableScore = this.availableScore;
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
