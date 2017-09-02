package com.jinpaihushi.jphs.order.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * ORDER_GOODS 
 * 继承自父类的字段:
 * id : id	
 * status : 状态	
 * createTime : 创建时间	
 * 
 * @author yangsong
 * @date 2017-07-04 10:29:44
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class OrderGoods extends BaseModel implements Predicate<OrderGoods>, Updator<OrderGoods> {

    /** 订单id */
    @Length(max = 50, message = "{orderGoods.orderId.illegal.length}")
    private String orderId;

    /** 商品id */
    @Length(max = 50, message = "{orderGoods.goodsId.illegal.length}")
    private String goodsId;

    /** 规格id */
    @Length(max = 50, message = "{orderGoods.pricePartId.illegal.length}")
    private String pricePartId;

    /** 商品名称 */
    @Length(max = 255, message = "{orderGoods.title.illegal.length}")
    private String title;

    /** 销售价 */
    private Double price;

    /** 实付金额 */
    private Double payPrice;

    /** 利润 */
    private Double profit;

    /** 指定人 */
    @Length(max = 50, message = "{orderGoods.expectorId.illegal.length}")
    private String expectorId;

    /** 执行人 */
    @Length(max = 50, message = "{orderGoods.executorId.illegal.length}")
    private String executorId;

    /** 备注 */
    @Length(max = 500, message = "{orderGoods.remark.illegal.length}")
    private String remark;

    private String gradeName;

    private String priceTitle;

    public OrderGoods() {
    }

    public OrderGoods(String id) {
        this.id = id;
    }

    /**
     * 获取订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
     * 获取规格id
     */
    public String getPricePartId() {
        return pricePartId;
    }

    /**
     * 设置规格id
     */
    public void setPricePartId(String pricePartId) {
        this.pricePartId = pricePartId;
    }

    /**
     * 获取商品名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品名称
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取实付金额
     */
    public Double getPayPrice() {
        return payPrice;
    }

    /**
     * 设置实付金额
     */
    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
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
     * 获取指定人
     */
    public String getExpectorId() {
        return expectorId;
    }

    /**
     * 设置指定人
     */
    public void setExpectorId(String expectorId) {
        this.expectorId = expectorId;
    }

    /**
     * 获取执行人
     */
    public String getExecutorId() {
        return executorId;
    }

    /**
     * 设置执行人
     */
    public void setExecutorId(String executorId) {
        this.executorId = executorId;
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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getPriceTitle() {
        return priceTitle;
    }

    public void setPriceTitle(String priceTitle) {
        this.priceTitle = priceTitle;
    }

    public String toString() {
        return new StringBuilder().append("OrderGoods{").append("id=").append(id).append(",orderId=").append(orderId)
                .append(",goodsId=").append(goodsId).append(",pricePartId=").append(pricePartId).append(",title=")
                .append(title).append(",price=").append(price).append(",payPrice=").append(payPrice).append(",profit=")
                .append(profit).append(",expectorId=").append(expectorId).append(",executorId=").append(executorId)
                .append(",status=").append(status).append(",createTime=").append(createTime).append(",remark=")
                .append(remark).append('}').toString();
    }

    /**
     * 复制字段：
     * id, orderId, goodsId, pricePartId, 
     * title, price, payPrice, profit, 
     * expectorId, executorId, status, createTime, 
     * remark
     */
    public OrderGoods copy() {
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.id = this.id;
        orderGoods.orderId = this.orderId;
        orderGoods.goodsId = this.goodsId;
        orderGoods.pricePartId = this.pricePartId;
        orderGoods.title = this.title;
        orderGoods.price = this.price;
        orderGoods.payPrice = this.payPrice;
        orderGoods.profit = this.profit;
        orderGoods.expectorId = this.expectorId;
        orderGoods.executorId = this.executorId;
        orderGoods.status = this.status;
        orderGoods.createTime = this.createTime;
        orderGoods.remark = this.remark;
        return orderGoods;
    }

    /**
     * 比较字段：
     * id, orderId, goodsId, pricePartId, 
     * title, price, payPrice, profit, 
     * expectorId, executorId, status, createTime, 
     * remark
     */
    @Override
    public boolean test(OrderGoods t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.orderId == null || this.orderId.equals(t.orderId))
                && (this.goodsId == null || this.goodsId.equals(t.goodsId))
                && (this.pricePartId == null || this.pricePartId.equals(t.pricePartId))
                && (this.title == null || this.title.equals(t.title))
                && (this.price == null || this.price.equals(t.price))
                && (this.payPrice == null || this.payPrice.equals(t.payPrice))
                && (this.profit == null || this.profit.equals(t.profit))
                && (this.expectorId == null || this.expectorId.equals(t.expectorId))
                && (this.executorId == null || this.executorId.equals(t.executorId))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.remark == null || this.remark.equals(t.remark));
    }

    @Override
    public void update(OrderGoods element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.orderId != null && !this.orderId.isEmpty()) {
            element.orderId = this.orderId;
        }
        if (this.goodsId != null && !this.goodsId.isEmpty()) {
            element.goodsId = this.goodsId;
        }
        if (this.pricePartId != null && !this.pricePartId.isEmpty()) {
            element.pricePartId = this.pricePartId;
        }
        if (this.title != null && !this.title.isEmpty()) {
            element.title = this.title;
        }
        if (this.price != null) {
            element.price = this.price;
        }
        if (this.payPrice != null) {
            element.payPrice = this.payPrice;
        }
        if (this.profit != null) {
            element.profit = this.profit;
        }
        if (this.expectorId != null && !this.expectorId.isEmpty()) {
            element.expectorId = this.expectorId;
        }
        if (this.executorId != null && !this.executorId.isEmpty()) {
            element.executorId = this.executorId;
        }
        if (this.status != null) {
            element.status = this.status;
        }
        if (this.createTime != null) {
            element.createTime = this.createTime;
        }
        if (this.remark != null && !this.remark.isEmpty()) {
            element.remark = this.remark;
        }
    }
}
