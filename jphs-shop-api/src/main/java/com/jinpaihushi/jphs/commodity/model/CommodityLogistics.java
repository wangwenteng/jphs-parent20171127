package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_LOGISTICS 
 * 继承自父类的字段:
 * id : 	
 * type : 类型（1：发货，2退货）	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-09-02 09:23:34
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityLogistics extends BaseModel implements Predicate<CommodityLogistics>,
		Updator<CommodityLogistics> {


    /** 商品订单id */
	@Length(max = 50, message = "{commodityLogistics.commodityOrderId.illegal.length}")
	private String commodityOrderId;

    /** 物流公司id */
	@Length(max = 50, message = "{commodityLogistics.logisticsId.illegal.length}")
	private String logisticsId;

    /** 物流单号 */
	@Length(max = 50, message = "{commodityLogistics.no.illegal.length}")
	private String no;

    /** 物流费 */
	private Double price;

    /** 进度 */
	private Integer schedule;

    /** 备注 */
	@Length(max = 500, message = "{commodityLogistics.remark.illegal.length}")
	private String remark;
	
	private String name;

	private String code;
	
	public CommodityLogistics(){}

	public CommodityLogistics(String id){
		this.id = id;
	}

	/**
	 * 获取商品订单id
	 */
	public String getCommodityOrderId() {
    	return commodityOrderId;
    }
  	
	/**
	 * 设置商品订单id
	 */
	public void setCommodityOrderId(String commodityOrderId) {
    	this.commodityOrderId = commodityOrderId;
    }

	/**
	 * 获取物流公司id
	 */
	public String getLogisticsId() {
    	return logisticsId;
    }
  	
	/**
	 * 设置物流公司id
	 */
	public void setLogisticsId(String logisticsId) {
    	this.logisticsId = logisticsId;
    }

	/**
	 * 获取物流单号
	 */
	public String getNo() {
    	return no;
    }
  	
	/**
	 * 设置物流单号
	 */
	public void setNo(String no) {
    	this.no = no;
    }

	/**
	 * 获取物流费
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置物流费
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取进度
	 */
	public Integer getSchedule() {
    	return schedule;
    }
  	
	/**
	 * 设置进度
	 */
	public void setSchedule(Integer schedule) {
    	this.schedule = schedule;
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
		return new StringBuilder().append("CommodityLogistics{").
			append("id=").append(id).
			append(",commodityOrderId=").append(commodityOrderId).
			append(",logisticsId=").append(logisticsId).
			append(",no=").append(no).
			append(",price=").append(price).
			append(",schedule=").append(schedule).
			append(",remark=").append(remark).
			append(",type=").append(type).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, commodityOrderId, logisticsId, no, 
	 * price, schedule, remark, type, 
	 * status, createTime, creatorId, creatorName
	 */
	public CommodityLogistics copy(){
		CommodityLogistics commodityLogistics = new CommodityLogistics();
     	commodityLogistics.id = this.id;
     	commodityLogistics.commodityOrderId = this.commodityOrderId;
     	commodityLogistics.logisticsId = this.logisticsId;
     	commodityLogistics.no = this.no;
     	commodityLogistics.price = this.price;
     	commodityLogistics.schedule = this.schedule;
     	commodityLogistics.remark = this.remark;
     	commodityLogistics.type = this.type;
     	commodityLogistics.status = this.status;
     	commodityLogistics.createTime = this.createTime;
     	commodityLogistics.creatorId = this.creatorId;
     	commodityLogistics.creatorName = this.creatorName;
		return commodityLogistics;
	}
	
	/**
	 * 比较字段：
	 * id, commodityOrderId, logisticsId, no, 
	 * price, schedule, remark, type, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(CommodityLogistics t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.commodityOrderId == null || this.commodityOrderId.equals(t.commodityOrderId))
				&& (this.logisticsId == null || this.logisticsId.equals(t.logisticsId))
				&& (this.no == null || this.no.equals(t.no))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.schedule == null || this.schedule.equals(t.schedule))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityLogistics element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.commodityOrderId != null && !this.commodityOrderId.isEmpty()) {
			element.commodityOrderId = this.commodityOrderId;
		}
		if (this.logisticsId != null && !this.logisticsId.isEmpty()) {
			element.logisticsId = this.logisticsId;
		}
		if (this.no != null && !this.no.isEmpty()) {
			element.no = this.no;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.schedule != null) {
			element.schedule = this.schedule;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.type != null) {
			element.type = this.type;
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
