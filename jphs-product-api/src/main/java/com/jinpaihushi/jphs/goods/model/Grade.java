package com.jinpaihushi.jphs.goods.model;

import java.util.List;

import com.jinpaihushi.model.BaseModel;

/**
 * 服务详情中的服务等级对象
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class Grade extends BaseModel {
    private String name;

    private String goodsName;

    private Integer grade;

    private String siUrl;

    private String productId;

    private List<GoodsPrice> goodsPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<GoodsPrice> getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(List<GoodsPrice> goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getSiUrl() {
        return siUrl;
    }

    public void setSiUrl(String siUrl) {
        this.siUrl = siUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
