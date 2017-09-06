package com.jinpaihushi.jphs.cancel.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;
import java.util.Date;

/**
 * CANCEL_ORDER 
 * 继承自父类的字段:
 * id : 	
 * type : 1.服务订单，2商品订单，3商品info_id	
 * status : 默认是0 待审核 1 审核通过	
 * creatorId : 	
 * creatorName : 	
 * createTime : 	
 * 
 * @author yangsong
 * @date 2017-08-25 16:52:03
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CancelOrder extends BaseModel implements Predicate<CancelOrder>,
		Updator<CancelOrder> {


    /** 商品订单id,服务订单id,订单info商品id */
	@Length(max = 50, message = "{cancelOrder.sourceId.illegal.length}")
	private String sourceId;

    /**  */
	private Double price;

    /** 数量 */
	private Integer number;

    /** 取消时间 */
	@NotNull(message = "{cancelOrder.cancelTime.null}")
	private Date cancelTime;

    /** 取消理由 */
	@NotEmpty(message = "{cancelOrder.cancelReason.empty}")
	@Length(max = 500, message = "{cancelOrder.cancelReason.illegal.length}")
	private String cancelReason;

	public CancelOrder(){}

	public CancelOrder(String id){
		this.id = id;
	}

	/**
	 * 获取商品订单id,服务订单id,订单info商品id
	 */
	public String getSourceId() {
    	return sourceId;
    }
  	
	/**
	 * 设置商品订单id,服务订单id,订单info商品id
	 */
	public void setSourceId(String sourceId) {
    	this.sourceId = sourceId;
    }

	/**
	 * 获取
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取数量
	 */
	public Integer getNumber() {
    	return number;
    }
  	
	/**
	 * 设置数量
	 */
	public void setNumber(Integer number) {
    	this.number = number;
    }

	/**
	 * 获取取消时间
	 */
	public Date getCancelTime() {
    	return cancelTime;
    }
  	
	/**
	 * 设置取消时间
	 */
	public void setCancelTime(Date cancelTime) {
    	this.cancelTime = cancelTime;
    }

	/**
	 * 获取取消理由
	 */
	public String getCancelReason() {
    	return cancelReason;
    }
  	
	/**
	 * 设置取消理由
	 */
	public void setCancelReason(String cancelReason) {
    	this.cancelReason = cancelReason;
    }

    public String toString() {
		return new StringBuilder().append("CancelOrder{").
			append("id=").append(id).
			append(",sourceId=").append(sourceId).
			append(",type=").append(type).
			append(",price=").append(price).
			append(",number=").append(number).
			append(",cancelTime=").append(cancelTime).
			append(",cancelReason=").append(cancelReason).
			append(",status=").append(status).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, sourceId, type, price, 
	 * number, cancelTime, cancelReason, status, 
	 * creatorId, creatorName, createTime
	 */
	public CancelOrder copy(){
		CancelOrder cancelOrder = new CancelOrder();
     	cancelOrder.id = this.id;
     	cancelOrder.sourceId = this.sourceId;
     	cancelOrder.type = this.type;
     	cancelOrder.price = this.price;
     	cancelOrder.number = this.number;
     	cancelOrder.cancelTime = this.cancelTime;
     	cancelOrder.cancelReason = this.cancelReason;
     	cancelOrder.status = this.status;
     	cancelOrder.creatorId = this.creatorId;
     	cancelOrder.creatorName = this.creatorName;
     	cancelOrder.createTime = this.createTime;
		return cancelOrder;
	}
	
	/**
	 * 比较字段：
	 * id, sourceId, type, price, 
	 * number, cancelTime, cancelReason, status, 
	 * creatorId, creatorName, createTime
	 */
	@Override
	public boolean test(CancelOrder t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.sourceId == null || this.sourceId.equals(t.sourceId))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.number == null || this.number.equals(t.number))
				&& (this.cancelTime == null || this.cancelTime.equals(t.cancelTime))
				&& (this.cancelReason == null || this.cancelReason.equals(t.cancelReason))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(CancelOrder element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.sourceId != null && !this.sourceId.isEmpty()) {
			element.sourceId = this.sourceId;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.number != null) {
			element.number = this.number;
		}
		if (this.cancelTime != null) {
			element.cancelTime = this.cancelTime;
		}
		if (this.cancelReason != null && !this.cancelReason.isEmpty()) {
			element.cancelReason = this.cancelReason;
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
