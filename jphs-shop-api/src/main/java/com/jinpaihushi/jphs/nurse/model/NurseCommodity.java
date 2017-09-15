package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_COMMODITY 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * creatorId : 	
 * creatorName : 	
 * createTime : 	
 * 
 * @author yangsong
 * @date 2017-08-14 10:02:02
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseCommodity extends BaseModel implements Predicate<NurseCommodity>,
		Updator<NurseCommodity> {


    /** 商品id */
	@Length(max = 50, message = "{nurseCommodity.commodityId.illegal.length}")
	private String commodityId;

    /** 浏览量 */
	private Integer browser;

    /** 分享量 */
	private Integer sharenumber;
	
	private Integer count;

	public NurseCommodity(){}

	public NurseCommodity(String id){
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
	 * 获取浏览量
	 */
	public Integer getBrowser() {
    	return browser;
    }
  	
	/**
	 * 设置浏览量
	 */
	public void setBrowser(Integer browser) {
    	this.browser = browser;
    }

	/**
	 * 获取分享量
	 */
	public Integer getSharenumber() {
    	return sharenumber;
    }
  	
	/**
	 * 设置分享量
	 */
	public void setSharenumber(Integer sharenumber) {
    	this.sharenumber = sharenumber;
    }

	
	
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String toString() {
		return new StringBuilder().append("NurseCommodity{").
			append("id=").append(id).
			append(",commodityId=").append(commodityId).
			append(",status=").append(status).
			append(",browser=").append(browser).
			append(",sharenumber=").append(sharenumber).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, commodityId, status, browser, 
	 * sharenumber, creatorId, creatorName, createTime
	 */
	public NurseCommodity copy(){
		NurseCommodity nurseCommodity = new NurseCommodity();
     	nurseCommodity.id = this.id;
     	nurseCommodity.commodityId = this.commodityId;
     	nurseCommodity.status = this.status;
     	nurseCommodity.browser = this.browser;
     	nurseCommodity.sharenumber = this.sharenumber;
     	nurseCommodity.creatorId = this.creatorId;
     	nurseCommodity.creatorName = this.creatorName;
     	nurseCommodity.createTime = this.createTime;
		return nurseCommodity;
	}
	
	/**
	 * 比较字段：
	 * id, commodityId, status, browser, 
	 * sharenumber, creatorId, creatorName, createTime
	 */
	@Override
	public boolean test(NurseCommodity t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.commodityId == null || this.commodityId.equals(t.commodityId))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.browser == null || this.browser.equals(t.browser))
				&& (this.sharenumber == null || this.sharenumber.equals(t.sharenumber))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(NurseCommodity element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.commodityId != null && !this.commodityId.isEmpty()) {
			element.commodityId = this.commodityId;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.browser != null) {
			element.browser = this.browser;
		}
		if (this.sharenumber != null) {
			element.sharenumber = this.sharenumber;
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
