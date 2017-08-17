package com.jinpaihushi.jphs.price.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PRICE_NURSE 
 * 继承自父类的字段:
 * id : 	
 * status : 状态	
 * createTime : 创建时间	
 * creatorId : 创建人ID	
 * creatorName : 创建人姓名	
 * 
 * @author yangsong
 * @date 2017-08-16 18:19:35
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PriceNurse extends BaseModel implements Predicate<PriceNurse>,
		Updator<PriceNurse> {


    /**  */
	@Length(max = 50, message = "{priceNurse.goodsId.illegal.length}")
	private String goodsId;

    /** priceID */
	@Length(max = 50, message = "{priceNurse.pricePartId.illegal.length}")
	private String pricePartId;

    /** 销售价 */
	private Double price;

	public PriceNurse(){}

	public PriceNurse(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getGoodsId() {
    	return goodsId;
    }
  	
	/**
	 * 设置
	 */
	public void setGoodsId(String goodsId) {
    	this.goodsId = goodsId;
    }

	/**
	 * 获取priceID
	 */
	public String getPricePartId() {
    	return pricePartId;
    }
  	
	/**
	 * 设置priceID
	 */
	public void setPricePartId(String pricePartId) {
    	this.pricePartId = pricePartId;
    }

	/**
	 * 获取销售价
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置销售价
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

    public String toString() {
		return new StringBuilder().append("PriceNurse{").
			append("id=").append(id).
			append(",goodsId=").append(goodsId).
			append(",pricePartId=").append(pricePartId).
			append(",price=").append(price).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, goodsId, pricePartId, price, 
	 * status, createTime, creatorId, creatorName
	 */
	public PriceNurse copy(){
		PriceNurse priceNurse = new PriceNurse();
     	priceNurse.id = this.id;
     	priceNurse.goodsId = this.goodsId;
     	priceNurse.pricePartId = this.pricePartId;
     	priceNurse.price = this.price;
     	priceNurse.status = this.status;
     	priceNurse.createTime = this.createTime;
     	priceNurse.creatorId = this.creatorId;
     	priceNurse.creatorName = this.creatorName;
		return priceNurse;
	}
	
	/**
	 * 比较字段：
	 * id, goodsId, pricePartId, price, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(PriceNurse t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.goodsId == null || this.goodsId.equals(t.goodsId))
				&& (this.pricePartId == null || this.pricePartId.equals(t.pricePartId))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(PriceNurse element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.goodsId != null && !this.goodsId.isEmpty()) {
			element.goodsId = this.goodsId;
		}
		if (this.pricePartId != null && !this.pricePartId.isEmpty()) {
			element.pricePartId = this.pricePartId;
		}
		if (this.price != null) {
			element.price = this.price;
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
