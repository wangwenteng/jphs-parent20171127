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
    
    /** 是否需要服务工具 */
    private Integer dzTool;

    /** 是否需要服务工具 */
    private Integer hlTool;
    
    /** 是否需要上保险 */
    private Integer insurance;
    
    /** 是否需要上传证明 1三张就医证明 2一张 3不需要 */
    private Integer isProve;

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
    
    /**
     * 获取是否需要服务工具
     */
    public Integer getDzTool() {
        return dzTool;
    }
    
    /**
     * 设置是否需要服务工具
     */
    public void setDzTool(Integer dzTool) {
        this.dzTool = dzTool;
    }

    /**
     * 获取是否需要服务工具
     */
    public Integer getHlTool() {
        return hlTool;
    }

    /**
     * 设置是否需要服务工具
     */
    public void setHlTool(Integer hlTool) {
        this.hlTool = hlTool;
    }

    /**
     * 获取是否需要上保险
     */
    public Integer getInsurance() {
        return insurance;
    }

    /**
     * 设置是否需要上保险
     */
    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }
    
    /**
     * 获取是否需要上传证明 1三张就医证明 2一张 3不需要
     */
    public Integer getIsProve() {
        return isProve;
    }

    /**
     * 设置是否需要上传证明 1三张就医证明 2一张 3不需要
     */
    public void setIsProve(Integer isProve) {
        this.isProve = isProve;
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
