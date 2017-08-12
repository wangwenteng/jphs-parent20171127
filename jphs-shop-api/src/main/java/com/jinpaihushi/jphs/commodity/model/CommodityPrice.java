package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_PRICE 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-09 09:20:37
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityPrice extends BaseModel implements Predicate<CommodityPrice>,
		Updator<CommodityPrice> {


    /** 商品id */
	@Length(max = 50, message = "{commodityPrice.commodityId.illegal.length}")
	private String commodityId;

    /** 商品SKU */
	@Length(max = 255, message = "{commodityPrice.name.illegal.length}")
	private String name;

    /** 数量 */
	private Integer number;

    /** 成本价 */
	private Double costPrice;

    /** 原价 */
	private Double oldPrice;

    /** 销售价 */
	private Double price;

    /** 导购者收益 */
	private Double profit;

    /**  */
	private Integer sort;

	public CommodityPrice(){}

	public CommodityPrice(String id){
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
	 * 获取商品SKU
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置商品SKU
	 */
	public void setName(String name) {
    	this.name = name;
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
	 * 获取成本价
	 */
	public Double getCostPrice() {
    	return costPrice;
    }
  	
	/**
	 * 设置成本价
	 */
	public void setCostPrice(Double costPrice) {
    	this.costPrice = costPrice;
    }

	/**
	 * 获取原价
	 */
	public Double getOldPrice() {
    	return oldPrice;
    }
  	
	/**
	 * 设置原价
	 */
	public void setOldPrice(Double oldPrice) {
    	this.oldPrice = oldPrice;
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

	/**
	 * 获取导购者收益
	 */
	public Double getProfit() {
    	return profit;
    }
  	
	/**
	 * 设置导购者收益
	 */
	public void setProfit(Double profit) {
    	this.profit = profit;
    }

	/**
	 * 获取
	 */
	public Integer getSort() {
    	return sort;
    }
  	
	/**
	 * 设置
	 */
	public void setSort(Integer sort) {
    	this.sort = sort;
    }

    public String toString() {
		return new StringBuilder().append("CommodityPrice{").
			append("id=").append(id).
			append(",commodityId=").append(commodityId).
			append(",name=").append(name).
			append(",number=").append(number).
			append(",costPrice=").append(costPrice).
			append(",oldPrice=").append(oldPrice).
			append(",price=").append(price).
			append(",profit=").append(profit).
			append(",sort=").append(sort).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, commodityId, name, number, 
	 * costPrice, oldPrice, price, profit, 
	 * sort, status, createTime, creatorId, 
	 * creatorName
	 */
	public CommodityPrice copy(){
		CommodityPrice commodityPrice = new CommodityPrice();
     	commodityPrice.id = this.id;
     	commodityPrice.commodityId = this.commodityId;
     	commodityPrice.name = this.name;
     	commodityPrice.number = this.number;
     	commodityPrice.costPrice = this.costPrice;
     	commodityPrice.oldPrice = this.oldPrice;
     	commodityPrice.price = this.price;
     	commodityPrice.profit = this.profit;
     	commodityPrice.sort = this.sort;
     	commodityPrice.status = this.status;
     	commodityPrice.createTime = this.createTime;
     	commodityPrice.creatorId = this.creatorId;
     	commodityPrice.creatorName = this.creatorName;
		return commodityPrice;
	}
	
	/**
	 * 比较字段：
	 * id, commodityId, name, number, 
	 * costPrice, oldPrice, price, profit, 
	 * sort, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(CommodityPrice t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.commodityId == null || this.commodityId.equals(t.commodityId))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.number == null || this.number.equals(t.number))
				&& (this.costPrice == null || this.costPrice.equals(t.costPrice))
				&& (this.oldPrice == null || this.oldPrice.equals(t.oldPrice))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.profit == null || this.profit.equals(t.profit))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityPrice element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.commodityId != null && !this.commodityId.isEmpty()) {
			element.commodityId = this.commodityId;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.number != null) {
			element.number = this.number;
		}
		if (this.costPrice != null) {
			element.costPrice = this.costPrice;
		}
		if (this.oldPrice != null) {
			element.oldPrice = this.oldPrice;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.profit != null) {
			element.profit = this.profit;
		}
		if (this.sort != null) {
			element.sort = this.sort;
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
