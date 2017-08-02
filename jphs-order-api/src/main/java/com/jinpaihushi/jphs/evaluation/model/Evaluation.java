package com.jinpaihushi.jphs.evaluation.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * EVALUATION 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:42
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Evaluation extends BaseModel implements Predicate<Evaluation>,
		Updator<Evaluation> {


    /** 主订单id */
	@Length(max = 50, message = "{evaluation.orderId.illegal.length}")
	private String orderId;

    /** 商品id */
	@Length(max = 50, message = "{evaluation.goodsId.illegal.length}")
	private String goodsId;

    /** 护士id */
	@Length(max = 50, message = "{evaluation.nurseId.illegal.length}")
	private String nurseId;

    /** 星级 */
	private Integer level;
	
	private String nurseName;
	
	private String userName;
	
	private String goodsName;
	
	private String userPhone;

	private Date beginTime;
	
	private Date stopTime; 
	
	private String title;
	
	private String nursePhone;
	private String headPortrait;
    /** 内容 */
	@Length(max = 65535, message = "{evaluation.content.illegal.length}")
	private String content;
	

	public Evaluation(){}

	public Evaluation(String id){
		this.id = id;
	}

	/**
	 * 获取主订单id
	 */
	public String getOrderId() {
    	return orderId;
    }
  	
	/**
	 * 设置主订单id
	 */
	public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

	/**
	 * 获取商品id
	 */
	public String getGoodsId() {
    	return goodsId;
    }
  	
	/**
	 * 设置商品id
	 */
	public void setGoodsId(String goodsId) {
    	this.goodsId = goodsId;
    }

	/**
	 * 获取护士id
	 */
	public String getNurseId() {
    	return nurseId;
    }
  	
	/**
	 * 设置护士id
	 */
	public void setNurseId(String nurseId) {
    	this.nurseId = nurseId;
    }

	/**
	 * 获取星级
	 */
	public Integer getLevel() {
    	return level;
    }
  	
	/**
	 * 设置星级
	 */
	public void setLevel(Integer level) {
    	this.level = level;
    }

	/**
	 * 获取内容
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置内容
	 */
	public void setContent(String content) {
    	this.content = content;
    }
	
	

    public String getNurseName() {
		return nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	
    public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	@Override
	public String toString() {
		return "Evaluation [orderId=" + orderId + ", goodsId=" + goodsId + ", nurseId=" + nurseId + ", level=" + level
				+ ", nurseName=" + nurseName + ", userName=" + userName + ", goodsName=" + goodsName + ", content="
				+ content + "]";
	}

	
	
	public String getNursePhone() {
		return nursePhone;
	}

	public void setNursePhone(String nursePhone) {
		this.nursePhone = nursePhone;
	}

	/**
	 * 复制字段：
	 * id, orderId, goodsId, nurseId, 
	 * level, content, status, createTime, 
	 * creatorId, creatorName
	 */
	public Evaluation copy(){
		Evaluation evaluation = new Evaluation();
     	evaluation.id = this.id;
     	evaluation.orderId = this.orderId;
     	evaluation.goodsId = this.goodsId;
     	evaluation.nurseId = this.nurseId;
     	evaluation.level = this.level;
     	evaluation.content = this.content;
     	evaluation.status = this.status;
     	evaluation.createTime = this.createTime;
     	evaluation.creatorId = this.creatorId;
     	evaluation.creatorName = this.creatorName;
		return evaluation;
	}
	
	/**
	 * 比较字段：
	 * id, orderId, goodsId, nurseId, 
	 * level, content, status, createTime, 
	 * creatorId, creatorName
	 */
	@Override
	public boolean test(Evaluation t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.orderId == null || this.orderId.equals(t.orderId))
				&& (this.goodsId == null || this.goodsId.equals(t.goodsId))
				&& (this.nurseId == null || this.nurseId.equals(t.nurseId))
				&& (this.level == null || this.level.equals(t.level))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Evaluation element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.orderId != null && !this.orderId.isEmpty()) {
			element.orderId = this.orderId;
		}
		if (this.goodsId != null && !this.goodsId.isEmpty()) {
			element.goodsId = this.goodsId;
		}
		if (this.nurseId != null && !this.nurseId.isEmpty()) {
			element.nurseId = this.nurseId;
		}
		if (this.level != null) {
			element.level = this.level;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
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
