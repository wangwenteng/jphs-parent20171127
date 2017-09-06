package com.jinpaihushi.jphs.goods.model;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.model.BaseModel;

@SuppressWarnings("serial")
public class GoodsPrice extends BaseModel {
    /** 销售价 */
    private Double price;

    private String priceId;

    /** 成本价 */
    private Double costPrice;

    /** 原价 */
    private Double oldPrice;

    /** 利润 */
    private Double profit;

    /** 利润 */
    private Double maxPrice;

    /** 服务次数 */
    private Integer serviceNumber;

    private Double pnPrice;

    /** 服务时长 */
    private Integer serviceTime;

    /** 单位 */
    @Length(max = 50, message = "{price.unit.illegal.length}")
    private String unit;

    /** 标题 */
    @Length(max = 50, message = "{price.title.illegal.length}")
    private String title;

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(Integer serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getPnPrice() {
        return pnPrice;
    }

    public void setPnPrice(Double pnPrice) {
        this.pnPrice = pnPrice;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }
}
