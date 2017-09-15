package com.jinpaihushi.jphs.car.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * CAR 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-08 17:39:23
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Car extends BaseModel implements Predicate<Car>,
		Updator<Car> {


    /** 商品id */
	@Length(max = 50, message = "{car.commodityId.illegal.length}")
	private String commodityId;

    /** 商品价格id */
	@Length(max = 50, message = "{car.commodityPriceId.illegal.length}")
	private String commodityPriceId;

    /** 数量 */
	private Integer number;
	
	private String userId;
	
	private String code;

	public Car(){}

	public Car(String id){
		this.id = id;
	}

	/**
	 * 获取商品id
	 */
	public String getCommodityId() {
    	return commodityId;
    }
  	
	/**
	 * 设置商品id
	 */
	public void setCommodityId(String commodityId) {
    	this.commodityId = commodityId;
    }

	/**
	 * 获取商品价格id
	 */
	public String getCommodityPriceId() {
    	return commodityPriceId;
    }
  	
	/**
	 * 设置商品价格id
	 */
	public void setCommodityPriceId(String commodityPriceId) {
    	this.commodityPriceId = commodityPriceId;
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
	
	

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toString() {
		return new StringBuilder().append("Car{").
			append("id=").append(id).
			append(",commodityId=").append(commodityId).
			append(",commodityPriceId=").append(commodityPriceId).
			append(",number=").append(number).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, commodityId, commodityPriceId, number, 
	 * status, createTime, creatorId, creatorName
	 */
	public Car copy(){
		Car car = new Car();
     	car.id = this.id;
     	car.commodityId = this.commodityId;
     	car.commodityPriceId = this.commodityPriceId;
     	car.number = this.number;
     	car.status = this.status;
     	car.createTime = this.createTime;
     	car.creatorId = this.creatorId;
     	car.creatorName = this.creatorName;
		return car;
	}
	
	/**
	 * 比较字段：
	 * id, commodityId, commodityPriceId, number, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(Car t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.commodityId == null || this.commodityId.equals(t.commodityId))
				&& (this.commodityPriceId == null || this.commodityPriceId.equals(t.commodityPriceId))
				&& (this.number == null || this.number.equals(t.number))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Car element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.commodityId != null && !this.commodityId.isEmpty()) {
			element.commodityId = this.commodityId;
		}
		if (this.commodityPriceId != null && !this.commodityPriceId.isEmpty()) {
			element.commodityPriceId = this.commodityPriceId;
		}
		if (this.number != null) {
			element.number = this.number;
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
