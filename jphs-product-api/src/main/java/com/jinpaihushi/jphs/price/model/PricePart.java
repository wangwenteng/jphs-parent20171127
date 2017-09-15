package com.jinpaihushi.jphs.price.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PRICE_PART 
 * 继承自父类的字段:
 * id : 	
 * status : 状态	
 * creatorId : 创建人ID	
 * creatorName : 创建人姓名	
 * createTime : 创建时间	
 * 
 * @author scj
 * @date 2017-07-14 15:08:37
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PricePart extends BaseModel implements Predicate<PricePart>, Updator<PricePart> {

    /** priceID */
    @Length(max = 50, message = "{pricePart.priceId.illegal.length}")
    private String priceId;

    /** 站点ID */
    @Length(max = 50, message = "{pricePart.siteId.illegal.length}")
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

    /** 授权数组 */
    @Length(max = 500, message = "{pricePart.aptitudeIdArr.illegal.length}")
    private String aptitudeIdArr;

    public PricePart() {
    }

    public PricePart(String id) {
        this.id = id;
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

    public String toString() {
        return new StringBuilder().append("PricePart{").append("id=").append(id).append(",priceId=").append(priceId)
                .append(",siteId=").append(siteId).append(",price=").append(price).append(",oldPrice=").append(oldPrice)
                .append(",costPrice=").append(costPrice).append(",maxPrice=").append(maxPrice).append(",outRatio=")
                .append(outRatio).append(",profit=").append(profit).append(",status=").append(status)
                .append(",creatorId=").append(creatorId).append(",creatorName=").append(creatorName)
                .append(",createTime=").append(createTime).append('}').toString();
    }

    /**
     * 复制字段：
     * id, priceId, siteId, price, 
     * oldPrice, costPrice, maxPrice, outRatio, 
     * profit, status, creatorId, creatorName, 
     * createTime
     */
    public PricePart copy() {
        PricePart pricePart = new PricePart();
        pricePart.id = this.id;
        pricePart.priceId = this.priceId;
        pricePart.siteId = this.siteId;
        pricePart.price = this.price;
        pricePart.oldPrice = this.oldPrice;
        pricePart.costPrice = this.costPrice;
        pricePart.maxPrice = this.maxPrice;
        pricePart.outRatio = this.outRatio;
        pricePart.profit = this.profit;
        pricePart.status = this.status;
        pricePart.creatorId = this.creatorId;
        pricePart.creatorName = this.creatorName;
        pricePart.createTime = this.createTime;
        return pricePart;
    }

    /**
     * 比较字段：
     * id, priceId, siteId, price, 
     * oldPrice, costPrice, maxPrice, outRatio, 
     * profit, status, creatorId, creatorName, 
     * createTime
     */
    @Override
    public boolean test(PricePart t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.priceId == null || this.priceId.equals(t.priceId))
                && (this.siteId == null || this.siteId.equals(t.siteId))
                && (this.price == null || this.price.equals(t.price))
                && (this.oldPrice == null || this.oldPrice.equals(t.oldPrice))
                && (this.costPrice == null || this.costPrice.equals(t.costPrice))
                && (this.maxPrice == null || this.maxPrice.equals(t.maxPrice))
                && (this.outRatio == null || this.outRatio.equals(t.outRatio))
                && (this.profit == null || this.profit.equals(t.profit))
                && (this.status == null || this.status.equals(t.status))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName))
                && (this.createTime == null || this.createTime.equals(t.createTime));
    }

    @Override
    public void update(PricePart element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.priceId != null && !this.priceId.isEmpty()) {
            element.priceId = this.priceId;
        }
        if (this.siteId != null && !this.siteId.isEmpty()) {
            element.siteId = this.siteId;
        }
        if (this.price != null) {
            element.price = this.price;
        }
        if (this.oldPrice != null) {
            element.oldPrice = this.oldPrice;
        }
        if (this.costPrice != null) {
            element.costPrice = this.costPrice;
        }
        if (this.maxPrice != null) {
            element.maxPrice = this.maxPrice;
        }
        if (this.outRatio != null) {
            element.outRatio = this.outRatio;
        }
        if (this.profit != null) {
            element.profit = this.profit;
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
        if (this.createTime != null) {
            element.createTime = this.createTime;
        }
    }
}
