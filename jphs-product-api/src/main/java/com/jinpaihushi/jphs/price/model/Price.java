package com.jinpaihushi.jphs.price.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PRICE 
 * 继承自父类的字段:
 * id : id	
 * createTime : 	
 * status : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-07-08 09:34:35
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Price extends BaseModel implements Predicate<Price>,
		Updator<Price> {


    /** 商品id */
	@Length(max = 50, message = "{price.goodsId.illegal.length}")
	private String goodsId;

	 /** priceID */
	private String priceId;

    /** 标题 */
	@Length(max = 50, message = "{price.title.illegal.length}")
	private String title;

    /** 等级 */
	private Integer grade;
	
	/** 等级名 */
	@Length(max = 50, message = "{price.gradeName.illegal.length}")
	private String gradeName;

    /** 服务次数 */
	private Integer serviceNumber;

    /** 服务时长 */
	private Integer serviceTime;
	
	 /** 站点ID */
	private String siteId;
	/** 销售价 */
	private Double price;

    /** 原价 */
	private Double oldPrice;

	/** 成本价 */
	private Double costPrice;
	
	/** 护士参考最高价格 */
    private Double maxPrice;

    /** 发布服务超出比例 */
    private Double outRatio;
	
    /** 利润 */
	private Double profit;

	private String prId;
	
	/**
	 * 获取职称列表
	 * */
	List<Jobtitle> jobtitleList;
	
	public List<Jobtitle> getJobtitle() {
    	return jobtitleList;
    }
  	
	public void setJobtitle(List<Jobtitle> jobtitleList) {
    	this.jobtitleList = jobtitleList;
    }
	
    /** 单位 */
	@Length(max = 50, message = "{price.unit.illegal.length}")
	private String unit;

    /** 排序 */
	private Integer sort;

    /** 备注 */
	@Length(max = 500, message = "{price.remark.illegal.length}")
	private String remark;
	
	/**	价格明细	*/
	private PricePart pricePart;
	
	/**	价格明细id	*/
	private String pricePartId;
	
	 /** 授权数组 */
	@Length(max = 500, message = "{pricePart.aptitudeIdArr.illegal.length}")
	private String aptitudeIdArr;
	private List<Price> children;
	public Price(){}

	public Price(String id){
		this.id = id;
	}

	/**
     * 获取护士参考最高价格
     */
    public Double getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置护士参考最高价格
     */
    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 获取发布服务超出比例
     */
    public Double getOutRatio() {
        return outRatio;
    }

    /**
     * 设置发布服务超出比例
     */
    public void setOutRatio(Double outRatio) {
        this.outRatio = outRatio;
    }
	
	/**
	 * 获取prId
	 */
	public String getprId() {
    	return prId;
    }
  	
	/**
	 * 设置priceID
	 */
	public void setprId(String prId) {
    	this.prId = prId;
    }

	
	/**
	 * 获取priceID
	 */
	public String getPriceId() {
    	return priceId;
    }
  	
	/**
	 * 设置priceID
	 */
	public void setPriceId(String priceId) {
    	this.priceId = priceId;
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
	
	public PricePart getPricePart(){
		return pricePart;
	}
	
	public void setPricePart(PricePart pricePart){
		this.pricePart = pricePart;
	}
	
	public String getPricePartId(){
		return pricePartId;
	}
	
	public void setPricePartId(String pricePartId){
		this.pricePartId = pricePartId;
	}
	
	/**
	 * 获取站点ID
	 */
	public String getSiteId() {
    	return siteId;
    }
  	
	/**
	 * 设置站点ID
	 */
	public void setSiteId(String siteId) {
    	this.siteId = siteId;
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
	 * 获取利润
	 */
	public Double getProfit() {
    	return profit;
    }
  	
	/**
	 * 设置利润
	 */
	public void setProfit(Double profit) {
    	this.profit = profit;
    }

	
	/**
	 * 获取商品id
	 */
	public String getGoodsId() {
    	return goodsId;
    }
  	
	/**
	 * 设置商品id
	 */
	public void setGoodsId(String goodsId) {
    	this.goodsId = goodsId;
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
	 * 获取等级名
	 */
	public String getGradeName() {
    	return gradeName;
    }
  	
	/**
	 * 设置等级名
	 */
	public void setGradeName(String gradeName) {
    	this.gradeName = gradeName;
    }

	/**
	 * 获取服务次数
	 */
	public Integer getServiceNumber() {
    	return serviceNumber;
    }
  	
	/**
	 * 设置服务次数
	 */
	public void setServiceNumber(Integer serviceNumber) {
    	this.serviceNumber = serviceNumber;
    }

	/**
	 * 获取服务时长
	 */
	public Integer getServiceTime() {
    	return serviceTime;
    }
  	
	/**
	 * 设置服务时长
	 */
	public void setServiceTime(Integer serviceTime) {
    	this.serviceTime = serviceTime;
    }

	/**
	 * 获取授权数组
	 */
	public String getAptitudeIdArr() {
    	return aptitudeIdArr;
    }
  	
	/**
	 * 设置授权数组
	 */
	public void setAptitudeIdArr(String aptitudeIdArr) {
    	this.aptitudeIdArr = aptitudeIdArr;
    }
	
	/**
	 * 获取单位
	 */
	public String getUnit() {
    	return unit;
    }
  	
	/**
	 * 设置单位
	 */
	public void setUnit(String unit) {
    	this.unit = unit;
    }

	/**
	 * 获取排序
	 */
	public Integer getSort() {
    	return sort;
    }
  	
	/**
	 * 设置排序
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
	
    public List<Price> getChildren() {
		return children;
	}

	public void setChildren(List<Price> children) {
		this.children = children;
	}

	public String toString() {
		return new StringBuilder().append("Price{").
			append("id=").append(id).
			append(",goodsId=").append(goodsId).
			append(",title=").append(title).
			append(",grade=").append(grade).
			append(",serviceNumber=").append(serviceNumber).
			append(",serviceTime=").append(serviceTime).
			append(",unit=").append(unit).
			append(",price=").append(price).
			append(",oldPrice=").append(oldPrice).
			append(",profit=").append(profit).
			append(",sort=").append(sort).
			append(",remark=").append(remark).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, goodsId, title, grade, 
	 * serviceNumber, serviceTime, unit, sort, 
	 * remark, createTime, status, creatorId, 
	 * creatorName
	 */
	public Price copy(){
		Price price = new Price();
     	price.id = this.id;
     	price.goodsId = this.goodsId;
     	price.title = this.title;
     	price.grade = this.grade;
     	price.serviceNumber = this.serviceNumber;
     	price.serviceTime = this.serviceTime;
     	price.unit = this.unit;
     	price.price = this.price;
     	price.oldPrice = this.oldPrice;
     	price.profit = this.profit;
     	price.sort = this.sort;
     	price.remark = this.remark;
     	price.createTime = this.createTime;
     	price.status = this.status;
     	price.creatorId = this.creatorId;
     	price.creatorName = this.creatorName;
		return price;
	}
	
	/**
	 * 比较字段：
	 * id, goodsId, title, grade, 
	 * serviceNumber, serviceTime, unit, sort, 
	 * remark, createTime, status, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(Price t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.goodsId == null || this.goodsId.equals(t.goodsId))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.grade == null || this.grade.equals(t.grade))
				&& (this.serviceNumber == null || this.serviceNumber.equals(t.serviceNumber))
				&& (this.serviceTime == null || this.serviceTime.equals(t.serviceTime))
				&& (this.unit == null || this.unit.equals(t.unit))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.oldPrice == null || this.oldPrice.equals(t.oldPrice))
				&& (this.profit == null || this.profit.equals(t.profit))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Price element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.goodsId != null && !this.goodsId.isEmpty()) {
			element.goodsId = this.goodsId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.grade != null) {
			element.grade = this.grade;
		}
		if (this.serviceNumber != null) {
			element.serviceNumber = this.serviceNumber;
		}
		if (this.serviceTime != null) {
			element.serviceTime = this.serviceTime;
		}
		if (this.unit != null && !this.unit.isEmpty()) {
			element.unit = this.unit;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.oldPrice != null) {
			element.oldPrice = this.oldPrice;
		}
		if (this.profit != null) {
			element.profit = this.profit;
		}
		if (this.sort != null) {
			element.sort = this.sort;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
	}
}
