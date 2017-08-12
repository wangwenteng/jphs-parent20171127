package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_EVALUATION 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-08 20:09:47
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityEvaluation extends BaseModel implements Predicate<CommodityEvaluation>,
		Updator<CommodityEvaluation> {


    /** 商品订单id */
	@Length(max = 50, message = "{commodityEvaluation.commodityOrderId.illegal.length}")
	private String commodityOrderId;

    /** 商品id */
	@Length(max = 50, message = "{commodityEvaluation.commodityId.illegal.length}")
	private String commodityId;

    /** 匿名（0否，1是） */
	private Integer anonymous;

    /** 1差评，2中评，3好评 */
	private Integer grade;

    /** 述描相符-评星 */
	private Integer descriptionLevel;

    /** 物流服务-评星 */
	private Integer logisticsLevel;

    /** 内容 */
	@Length(max = 65535, message = "{commodityEvaluation.content.illegal.length}")
	private String content;

	public CommodityEvaluation(){}

	public CommodityEvaluation(String id){
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
	 * 获取匿名（0否，1是）
	 */
	public Integer getAnonymous() {
    	return anonymous;
    }
  	
	/**
	 * 设置匿名（0否，1是）
	 */
	public void setAnonymous(Integer anonymous) {
    	this.anonymous = anonymous;
    }

	/**
	 * 获取1差评，2中评，3好评
	 */
	public Integer getGrade() {
    	return grade;
    }
  	
	/**
	 * 设置1差评，2中评，3好评
	 */
	public void setGrade(Integer grade) {
    	this.grade = grade;
    }

	/**
	 * 获取述描相符-评星
	 */
	public Integer getDescriptionLevel() {
    	return descriptionLevel;
    }
  	
	/**
	 * 设置述描相符-评星
	 */
	public void setDescriptionLevel(Integer descriptionLevel) {
    	this.descriptionLevel = descriptionLevel;
    }

	/**
	 * 获取物流服务-评星
	 */
	public Integer getLogisticsLevel() {
    	return logisticsLevel;
    }
  	
	/**
	 * 设置物流服务-评星
	 */
	public void setLogisticsLevel(Integer logisticsLevel) {
    	this.logisticsLevel = logisticsLevel;
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

    public String toString() {
		return new StringBuilder().append("CommodityEvaluation{").
			append("id=").append(id).
			append(",commodityOrderId=").append(commodityOrderId).
			append(",commodityId=").append(commodityId).
			append(",anonymous=").append(anonymous).
			append(",grade=").append(grade).
			append(",descriptionLevel=").append(descriptionLevel).
			append(",logisticsLevel=").append(logisticsLevel).
			append(",content=").append(content).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, commodityOrderId, commodityId, anonymous, 
	 * grade, descriptionLevel, logisticsLevel, content, 
	 * status, createTime, creatorId, creatorName
	 */
	public CommodityEvaluation copy(){
		CommodityEvaluation commodityEvaluation = new CommodityEvaluation();
     	commodityEvaluation.id = this.id;
     	commodityEvaluation.commodityOrderId = this.commodityOrderId;
     	commodityEvaluation.commodityId = this.commodityId;
     	commodityEvaluation.anonymous = this.anonymous;
     	commodityEvaluation.grade = this.grade;
     	commodityEvaluation.descriptionLevel = this.descriptionLevel;
     	commodityEvaluation.logisticsLevel = this.logisticsLevel;
     	commodityEvaluation.content = this.content;
     	commodityEvaluation.status = this.status;
     	commodityEvaluation.createTime = this.createTime;
     	commodityEvaluation.creatorId = this.creatorId;
     	commodityEvaluation.creatorName = this.creatorName;
		return commodityEvaluation;
	}
	
	/**
	 * 比较字段：
	 * id, commodityOrderId, commodityId, anonymous, 
	 * grade, descriptionLevel, logisticsLevel, content, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(CommodityEvaluation t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.commodityOrderId == null || this.commodityOrderId.equals(t.commodityOrderId))
				&& (this.commodityId == null || this.commodityId.equals(t.commodityId))
				&& (this.anonymous == null || this.anonymous.equals(t.anonymous))
				&& (this.grade == null || this.grade.equals(t.grade))
				&& (this.descriptionLevel == null || this.descriptionLevel.equals(t.descriptionLevel))
				&& (this.logisticsLevel == null || this.logisticsLevel.equals(t.logisticsLevel))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityEvaluation element) {
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
		if (this.anonymous != null) {
			element.anonymous = this.anonymous;
		}
		if (this.grade != null) {
			element.grade = this.grade;
		}
		if (this.descriptionLevel != null) {
			element.descriptionLevel = this.descriptionLevel;
		}
		if (this.logisticsLevel != null) {
			element.logisticsLevel = this.logisticsLevel;
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
