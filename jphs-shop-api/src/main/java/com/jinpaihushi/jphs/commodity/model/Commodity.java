package com.jinpaihushi.jphs.commodity.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

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
@SuppressWarnings("serial")
public class Commodity extends BaseModel implements Predicate<Commodity>,
		Updator<Commodity> {


    /** 商家id */
	@Length(max = 50, message = "{commodity.businessId.illegal.length}")
	private String businessId;

    /** 品类id */
	@Length(max = 50, message = "{commodity.commodityTypeId.illegal.length}")
	private String commodityTypeId;

    /** 标题 */
	@Length(max = 255, message = "{commodity.title.illegal.length}")
	private String title;

    /** 副标题，简介 */
	@Length(max = 500, message = "{commodity.subTitle.illegal.length}")
	private String subTitle;

    /** 型号/规格 */
	@Length(max = 255, message = "{commodity.model.illegal.length}")
	private String model;

    /** 内容 */
	@Length(max = 65535, message = "{commodity.content.illegal.length}")
	private String content;

    /** 正品认证（0否，1是） */
	private Integer quality;

    /** 隐私配送（0否，1是） */
	private Integer privacy;

    /** 药检认证（0否，1是） */
	private Integer security;

    /** 参数 */
	@Length(max = 65535, message = "{commodity.parameter.illegal.length}")
	private String parameter;

    /** 是否支持使用优惠券（0不支持，1支持） */
	private Integer supportVoucher;

    /** 限购数量 */
	private Integer limitNumber;

    /** 保护期(天） */
	private Integer protectDay;

    /**  */
	private Integer sort;
	
	private Integer number;
	
	private double price;
	
	private double oldPrice;
	private double onePrice;
	
	private String cpId;
	
	private String commodityId;
    /** 备注 */
	@Length(max = 500, message = "{commodity.remark.illegal.length}")
	private String remark;
	
	private List<CommodityPrice> commodityPrice;
	
	private CommodityImages commodityImages;
	
	private String DetailUrl;
	
	private Integer count;
	
	private Integer browser;
	
	private Integer shareNumber;
	
	private Double money;
	
	private String url;
	
	private String name;
	
	private double profit;
	
	public Commodity(){}

	public Commodity(String id){
		this.id = id;
	}

	
	
	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	/**
	 * 获取商家id
	 */
	public String getBusinessId() {
    	return businessId;
    }
  	
	/**
	 * 设置商家id
	 */
	public void setBusinessId(String businessId) {
    	this.businessId = businessId;
    }

	/**
	 * 获取品类id
	 */
	public String getCommodityTypeId() {
    	return commodityTypeId;
    }
  	
	/**
	 * 设置品类id
	 */
	public void setCommodityTypeId(String commodityTypeId) {
    	this.commodityTypeId = commodityTypeId;
    }

	/**
	 * 获取标题
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取副标题，简介
	 */
	public String getSubTitle() {
    	return subTitle;
    }
  	
	/**
	 * 设置副标题，简介
	 */
	public void setSubTitle(String subTitle) {
    	this.subTitle = subTitle;
    }

	/**
	 * 获取型号/规格
	 */
	public String getModel() {
    	return model;
    }
  	
	/**
	 * 设置型号/规格
	 */
	public void setModel(String model) {
    	this.model = model;
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

	/**
	 * 获取正品认证（0否，1是）
	 */
	public Integer getQuality() {
    	return quality;
    }
  	
	/**
	 * 设置正品认证（0否，1是）
	 */
	public void setQuality(Integer quality) {
    	this.quality = quality;
    }

	/**
	 * 获取隐私配送（0否，1是）
	 */
	public Integer getPrivacy() {
    	return privacy;
    }
  	
	/**
	 * 设置隐私配送（0否，1是）
	 */
	public void setPrivacy(Integer privacy) {
    	this.privacy = privacy;
    }

	/**
	 * 获取药检认证（0否，1是）
	 */
	public Integer getSecurity() {
    	return security;
    }
  	
	/**
	 * 设置药检认证（0否，1是）
	 */
	public void setSecurity(Integer security) {
    	this.security = security;
    }

	/**
	 * 获取参数
	 */
	public String getParameter() {
    	return parameter;
    }
  	
	/**
	 * 设置参数
	 */
	public void setParameter(String parameter) {
    	this.parameter = parameter;
    }

	/**
	 * 获取是否支持使用优惠券（0不支持，1支持）
	 */
	public Integer getSupportVoucher() {
    	return supportVoucher;
    }
  	
	/**
	 * 设置是否支持使用优惠券（0不支持，1支持）
	 */
	public void setSupportVoucher(Integer supportVoucher) {
    	this.supportVoucher = supportVoucher;
    }

	/**
	 * 获取限购数量
	 */
	public Integer getLimitNumber() {
    	return limitNumber;
    }
  	
	/**
	 * 设置限购数量
	 */
	public void setLimitNumber(Integer limitNumber) {
    	this.limitNumber = limitNumber;
    }

	/**
	 * 获取保护期(天）
	 */
	public Integer getProtectDay() {
    	return protectDay;
    }
  	
	/**
	 * 设置保护期(天）
	 */
	public void setProtectDay(Integer protectDay) {
    	this.protectDay = protectDay;
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

	
	
	public List<CommodityPrice> getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(List<CommodityPrice> commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public CommodityImages getCommodityImages() {
		return commodityImages;
	}

	public void setCommodityImages(CommodityImages commodityImages) {
		this.commodityImages = commodityImages;
	}

	
	
	public String getDetailUrl() {
		return DetailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		DetailUrl = detailUrl;
	}

	
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	

	public Integer getBrowser() {
		return browser;
	}

	public void setBrowser(Integer browser) {
		this.browser = browser;
	}

	public Integer getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(Integer shareNumber) {
		this.shareNumber = shareNumber;
	}

	
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
	 

	public double getOnePrice() {
		return onePrice;
	}

	public void setOnePrice(double onePrice) {
		this.onePrice = onePrice;
	}

	public double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}

	
	
	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String toString() {
		return new StringBuilder().append("Commodity{").
			append("id=").append(id).
			append(",businessId=").append(businessId).
			append(",commodityTypeId=").append(commodityTypeId).
			append(",title=").append(title).
			append(",subTitle=").append(subTitle).
			append(",model=").append(model).
			append(",content=").append(content).
			append(",quality=").append(quality).
			append(",privacy=").append(privacy).
			append(",security=").append(security).
			append(",parameter=").append(parameter).
			append(",supportVoucher=").append(supportVoucher).
			append(",limitNumber=").append(limitNumber).
			append(",protectDay=").append(protectDay).
			append(",sort=").append(sort).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, businessId, commodityTypeId, title, 
	 * subTitle, model, content, quality, 
	 * privacy, security, parameter, supportVoucher, 
	 * limitNumber, protectDay, sort, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	public Commodity copy(){
		Commodity commodity = new Commodity();
     	commodity.id = this.id;
     	commodity.businessId = this.businessId;
     	commodity.commodityTypeId = this.commodityTypeId;
     	commodity.title = this.title;
     	commodity.subTitle = this.subTitle;
     	commodity.model = this.model;
     	commodity.content = this.content;
     	commodity.quality = this.quality;
     	commodity.privacy = this.privacy;
     	commodity.security = this.security;
     	commodity.parameter = this.parameter;
     	commodity.supportVoucher = this.supportVoucher;
     	commodity.limitNumber = this.limitNumber;
     	commodity.protectDay = this.protectDay;
     	commodity.sort = this.sort;
     	commodity.remark = this.remark;
     	commodity.status = this.status;
     	commodity.createTime = this.createTime;
     	commodity.creatorId = this.creatorId;
     	commodity.creatorName = this.creatorName;
		return commodity;
	}
	
	/**
	 * 比较字段：
	 * id, businessId, commodityTypeId, title, 
	 * subTitle, model, content, quality, 
	 * privacy, security, parameter, supportVoucher, 
	 * limitNumber, protectDay, sort, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(Commodity t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.businessId == null || this.businessId.equals(t.businessId))
				&& (this.commodityTypeId == null || this.commodityTypeId.equals(t.commodityTypeId))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.subTitle == null || this.subTitle.equals(t.subTitle))
				&& (this.model == null || this.model.equals(t.model))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.quality == null || this.quality.equals(t.quality))
				&& (this.privacy == null || this.privacy.equals(t.privacy))
				&& (this.security == null || this.security.equals(t.security))
				&& (this.parameter == null || this.parameter.equals(t.parameter))
				&& (this.supportVoucher == null || this.supportVoucher.equals(t.supportVoucher))
				&& (this.limitNumber == null || this.limitNumber.equals(t.limitNumber))
				&& (this.protectDay == null || this.protectDay.equals(t.protectDay))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Commodity element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.businessId != null && !this.businessId.isEmpty()) {
			element.businessId = this.businessId;
		}
		if (this.commodityTypeId != null && !this.commodityTypeId.isEmpty()) {
			element.commodityTypeId = this.commodityTypeId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.subTitle != null && !this.subTitle.isEmpty()) {
			element.subTitle = this.subTitle;
		}
		if (this.model != null && !this.model.isEmpty()) {
			element.model = this.model;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
		}
		if (this.quality != null) {
			element.quality = this.quality;
		}
		if (this.privacy != null) {
			element.privacy = this.privacy;
		}
		if (this.security != null) {
			element.security = this.security;
		}
		if (this.parameter != null && !this.parameter.isEmpty()) {
			element.parameter = this.parameter;
		}
		if (this.supportVoucher != null) {
			element.supportVoucher = this.supportVoucher;
		}
		if (this.limitNumber != null) {
			element.limitNumber = this.limitNumber;
		}
		if (this.protectDay != null) {
			element.protectDay = this.protectDay;
		}
		if (this.sort != null) {
			element.sort = this.sort;
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
