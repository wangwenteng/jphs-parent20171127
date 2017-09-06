package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY 
 * 继承自父类的字段:
 * id : 	
 * status : -1下架，0待上架，1已上架	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-09 11:50:37
 * @company jinpaihushi
 * @version 1.0
 */
public class CommodityMap  {

	private Integer shareNumber;
	
	private String subTitle;
	
	private String title;

	private String cpId;

	
	private double price;
	
	private double oldPrice;
	 
	private Integer counts;
	
	private String commodityId;
	
	private String id;
	
	private Integer browser;
	
	private Double money;
	
	private Integer status;
	
	private String url;
	
	private String name;
	
	private double profit;
	
	private Integer count;
	
	public CommodityMap(){}

	
	
	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	public Integer getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(Integer shareNumber) {
		this.shareNumber = shareNumber;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBrowser() {
		return browser;
	}

	public void setBrowser(Integer browser) {
		this.browser = browser;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

    
}
