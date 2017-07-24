package com.jinpaihushi.jphs.price.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PRICE_GRADE 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 创建时间	
 * creatorId : 创建人ID	
 * creatorName : 创建人姓名	
 * 
 * @author yangsong
 * @date 2017-07-15 19:05:47
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PriceGrade extends BaseModel implements Predicate<PriceGrade>,
		Updator<PriceGrade> {


    /** 商品ID */
	@Length(max = 50, message = "{priceGrade.goodsId.illegal.length}")
	private String goodsId;

    /** 等级 */
	private Integer grade;

    /** 等级名称 */
	@Length(max = 50, message = "{priceGrade.gradeName.illegal.length}")
	private String gradeName;
	
	private List<Price> price;

	public PriceGrade(){}

	public PriceGrade(String id){
		this.id = id;
	}

	/**
	 * 获取商品ID
	 */
	public List<Price> getPrice() {
    	return price;
    }
  	
	/**
	 * 设置商品ID
	 */
	public void setPrice(List<Price> price) {
    	this.price = price;
    }
	
	/**
	 * 获取商品ID
	 */
	public String getGoodsId() {
    	return goodsId;
    }
  	
	/**
	 * 设置商品ID
	 */
	public void setGoodsId(String goodsId) {
    	this.goodsId = goodsId;
    }

	/**
	 * 获取等级
	 */
	public Integer getGrade() {
    	return grade;
    }
  	
	/**
	 * 设置等级
	 */
	public void setGrade(Integer grade) {
    	this.grade = grade;
    }

	/**
	 * 获取等级名称
	 */
	public String getGradeName() {
    	return gradeName;
    }
  	
	/**
	 * 设置等级名称
	 */
	public void setGradeName(String gradeName) {
    	this.gradeName = gradeName;
    }

    public String toString() {
		return new StringBuilder().append("PriceGrade{").
			append("id=").append(id).
			append(",goodsId=").append(goodsId).
			append(",grade=").append(grade).
			append(",gradeName=").append(gradeName).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, goodsId, grade, gradeName, 
	 * status, createTime, creatorId, creatorName
	 */
	public PriceGrade copy(){
		PriceGrade priceGrade = new PriceGrade();
     	priceGrade.id = this.id;
     	priceGrade.goodsId = this.goodsId;
     	priceGrade.grade = this.grade;
     	priceGrade.gradeName = this.gradeName;
     	priceGrade.status = this.status;
     	priceGrade.createTime = this.createTime;
     	priceGrade.creatorId = this.creatorId;
     	priceGrade.creatorName = this.creatorName;
		return priceGrade;
	}
	
	/**
	 * 比较字段：
	 * id, goodsId, grade, gradeName, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(PriceGrade t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.goodsId == null || this.goodsId.equals(t.goodsId))
				&& (this.grade == null || this.grade.equals(t.grade))
				&& (this.gradeName == null || this.gradeName.equals(t.gradeName))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(PriceGrade element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.goodsId != null && !this.goodsId.isEmpty()) {
			element.goodsId = this.goodsId;
		}
		if (this.grade != null) {
			element.grade = this.grade;
		}
		if (this.gradeName != null && !this.gradeName.isEmpty()) {
			element.gradeName = this.gradeName;
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
