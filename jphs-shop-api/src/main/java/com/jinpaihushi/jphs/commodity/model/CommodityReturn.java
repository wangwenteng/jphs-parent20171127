package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_RETURN 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-15 13:57:49
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityReturn extends BaseModel implements Predicate<CommodityReturn>,
		Updator<CommodityReturn> {


    /**  */
	@Length(max = 50, message = "{commodityReturn.commodityOrderId.illegal.length}")
	private String commodityOrderInfoId;

    /**  */
	@Length(max = 255, message = "{commodityReturn.reason.illegal.length}")
	private String reason;

    /**  */
	@Length(max = 65535, message = "{commodityReturn.remark.illegal.length}")
	private String remark;

	private Double price;
	
	private Integer sign;
	
	public CommodityReturn(){}

	public CommodityReturn(String id){
		this.id = id;
	}

	 

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCommodityOrderInfoId() {
		return commodityOrderInfoId;
	}

	public void setCommodityOrderInfoId(String commodityOrderInfoId) {
		this.commodityOrderInfoId = commodityOrderInfoId;
	}

	/**
	 * 获取
	 */
	public String getReason() {
    	return reason;
    }
  	
	/**
	 * 设置
	 */
	public void setReason(String reason) {
    	this.reason = reason;
    }

	/**
	 * 获取
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

	
	
    public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public String toString() {
		return new StringBuilder().append("CommodityReturn{").
			append("id=").append(id).
			append(",commodityOrderId=").append(commodityOrderInfoId).
			append(",reason=").append(reason).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, commodityOrderId, reason, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	public CommodityReturn copy(){
		CommodityReturn commodityReturn = new CommodityReturn();
     	commodityReturn.id = this.id;
     	commodityReturn.commodityOrderInfoId = this.commodityOrderInfoId;
     	commodityReturn.reason = this.reason;
     	commodityReturn.remark = this.remark;
     	commodityReturn.status = this.status;
     	commodityReturn.createTime = this.createTime;
     	commodityReturn.creatorId = this.creatorId;
     	commodityReturn.creatorName = this.creatorName;
		return commodityReturn;
	}
	
	/**
	 * 比较字段：
	 * id, commodityOrderId, reason, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(CommodityReturn t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.commodityOrderInfoId == null || this.commodityOrderInfoId.equals(t.commodityOrderInfoId))
				&& (this.reason == null || this.reason.equals(t.reason))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityReturn element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.commodityOrderInfoId != null && !this.commodityOrderInfoId.isEmpty()) {
			element.commodityOrderInfoId = this.commodityOrderInfoId;
		}
		if (this.reason != null && !this.reason.isEmpty()) {
			element.reason = this.reason;
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
