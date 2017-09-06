package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_ORDER_INFO 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityOrderInfo extends BaseModel implements Predicate<CommodityOrderInfo>,
		Updator<CommodityOrderInfo> {


    /** 主订单id */
	@Length(max = 50, message = "{commodityOrderInfo.commodityOrderId.illegal.length}")
	private String commodityOrderId;

    /** 商品id */
	@Length(max = 50, message = "{commodityOrderInfo.commodityId.illegal.length}")
	private String commodityId;

    /** 导购者id(未来可能是用户或者护士) */
	@Length(max = 50, message = "{commodityOrderInfo.userId.illegal.length}")
	private String userId;

    /** 导购者收益 */
	private Double profit;

    /** 商品名称 */
	@Length(max = 255, message = "{commodityOrderInfo.title.illegal.length}")
	private String title;

    /** 原价 */
	private Double oldPrice;

    /** 销售价 */
	private Double price;
	
	@Length(max = 65535, message = "{commodityOrderInfo.remark.illegal.length}")
	private String code;

    /** 商品sku */
	@Length(max = 255, message = "{commodityOrderInfo.commodityPriceName.illegal.length}")
	private String commodityPriceName;

    /** 规格 */
	@Length(max = 255, message = "{commodityOrderInfo.commodityModel.illegal.length}")
	private String commodityModel;

    /** 数量 */
	private Integer number;

    /** 备注 */
	@Length(max = 65535, message = "{commodityOrderInfo.remark.illegal.length}")
	private String remark;
	
	private String url;
	
	private String name;

	public CommodityOrderInfo(){}

	public CommodityOrderInfo(String id){
		this.id = id;
	}
	
	private String cpId;
	
	

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	/**
	 * 获取主订单id
	 */
	public String getCommodityOrderId() {
    	return commodityOrderId;
    }
  	
	/**
	 * 设置主订单id
	 */
	public void setCommodityOrderId(String commodityOrderId) {
    	this.commodityOrderId = commodityOrderId;
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
	 * 获取导购者id(未来可能是用户或者护士)
	 */
	public String getUserId() {
    	return userId;
    }
  	
	/**
	 * 设置导购者id(未来可能是用户或者护士)
	 */
	public void setUserId(String userId) {
    	this.userId = userId;
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
	 * 获取商品名称
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置商品名称
	 */
	public void setTitle(String title) {
    	this.title = title;
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
	 * 获取商品sku
	 */
	public String getCommodityPriceName() {
    	return commodityPriceName;
    }
  	
	/**
	 * 设置商品sku
	 */
	public void setCommodityPriceName(String commodityPriceName) {
    	this.commodityPriceName = commodityPriceName;
    }

	/**
	 * 获取规格
	 */
	public String getCommodityModel() {
    	return commodityModel;
    }
  	
	/**
	 * 设置规格
	 */
	public void setCommodityModel(String commodityModel) {
    	this.commodityModel = commodityModel;
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
	
	

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toString() {
		return new StringBuilder().append("CommodityOrderInfo{").
			append("id=").append(id).
			append(",commodityOrderId=").append(commodityOrderId).
			append(",commodityId=").append(commodityId).
			append(",userId=").append(userId).
			append(",profit=").append(profit).
			append(",title=").append(title).
			append(",oldPrice=").append(oldPrice).
			append(",price=").append(price).
			append(",commodityPriceName=").append(commodityPriceName).
			append(",commodityModel=").append(commodityModel).
			append(",number=").append(number).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, commodityOrderId, commodityId, userId, 
	 * profit, title, oldPrice, price, 
	 * commodityPriceName, commodityModel, number, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	public CommodityOrderInfo copy(){
		CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
     	commodityOrderInfo.id = this.id;
     	commodityOrderInfo.commodityOrderId = this.commodityOrderId;
     	commodityOrderInfo.commodityId = this.commodityId;
     	commodityOrderInfo.userId = this.userId;
     	commodityOrderInfo.profit = this.profit;
     	commodityOrderInfo.title = this.title;
     	commodityOrderInfo.oldPrice = this.oldPrice;
     	commodityOrderInfo.price = this.price;
     	commodityOrderInfo.commodityPriceName = this.commodityPriceName;
     	commodityOrderInfo.commodityModel = this.commodityModel;
     	commodityOrderInfo.number = this.number;
     	commodityOrderInfo.remark = this.remark;
     	commodityOrderInfo.status = this.status;
     	commodityOrderInfo.createTime = this.createTime;
     	commodityOrderInfo.creatorId = this.creatorId;
     	commodityOrderInfo.creatorName = this.creatorName;
		return commodityOrderInfo;
	}
	
	/**
	 * 比较字段：
	 * id, commodityOrderId, commodityId, userId, 
	 * profit, title, oldPrice, price, 
	 * commodityPriceName, commodityModel, number, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(CommodityOrderInfo t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.commodityOrderId == null || this.commodityOrderId.equals(t.commodityOrderId))
				&& (this.commodityId == null || this.commodityId.equals(t.commodityId))
				&& (this.userId == null || this.userId.equals(t.userId))
				&& (this.profit == null || this.profit.equals(t.profit))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.oldPrice == null || this.oldPrice.equals(t.oldPrice))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.commodityPriceName == null || this.commodityPriceName.equals(t.commodityPriceName))
				&& (this.commodityModel == null || this.commodityModel.equals(t.commodityModel))
				&& (this.number == null || this.number.equals(t.number))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityOrderInfo element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.commodityOrderId != null && !this.commodityOrderId.isEmpty()) {
			element.commodityOrderId = this.commodityOrderId;
		}
		if (this.commodityId != null && !this.commodityId.isEmpty()) {
			element.commodityId = this.commodityId;
		}
		if (this.userId != null && !this.userId.isEmpty()) {
			element.userId = this.userId;
		}
		if (this.profit != null) {
			element.profit = this.profit;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.oldPrice != null) {
			element.oldPrice = this.oldPrice;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.commodityPriceName != null && !this.commodityPriceName.isEmpty()) {
			element.commodityPriceName = this.commodityPriceName;
		}
		if (this.commodityModel != null && !this.commodityModel.isEmpty()) {
			element.commodityModel = this.commodityModel;
		}
		if (this.number != null) {
			element.number = this.number;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
