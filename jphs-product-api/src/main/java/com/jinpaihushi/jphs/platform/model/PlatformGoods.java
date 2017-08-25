package com.jinpaihushi.jphs.platform.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM_GOODS 平台上线的商品
 * 继承自父类的字段:
 * id : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * status : 	
 * 
 * @author wangwt
 * @date 2017-07-03 16:58:14
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlatformGoods extends BaseModel implements Predicate<PlatformGoods>, Updator<PlatformGoods> {

    /** 平台id */
    @Length(max = 50, message = "{platformGoods.platformId.illegal.length}")
    private String platformId;

    /** 商品id */
    @Length(max = 50, message = "{platformGoods.goodsId.illegal.length}")
    private String goodsId;

    public PlatformGoods() {
    }

    public PlatformGoods(String id) {
        this.id = id;
    }

    /**
     * 获取平台id
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * 设置平台id
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
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

    public String toString() {
        return new StringBuilder().append("PlatformGoods{").append("id=").append(id).append(",platformId=")
                .append(platformId).append(",goodsId=").append(goodsId).append(",createTime=").append(createTime)
                .append(",creatorId=").append(creatorId).append(",creatorName=").append(creatorName).append(",status=")
                .append(status).append('}').toString();
    }

    /**
     * 复制字段：
     * id, platformId, goodsId, createTime, 
     * creatorId, creatorName, status
     */
    public PlatformGoods copy() {
        PlatformGoods platformGoods = new PlatformGoods();
        platformGoods.id = this.id;
        platformGoods.platformId = this.platformId;
        platformGoods.goodsId = this.goodsId;
        platformGoods.createTime = this.createTime;
        platformGoods.creatorId = this.creatorId;
        platformGoods.creatorName = this.creatorName;
        platformGoods.status = this.status;
        return platformGoods;
    }

    /**
     * 比较字段：
     * id, platformId, goodsId, createTime, 
     * creatorId, creatorName, status
     */
    @Override
    public boolean test(PlatformGoods t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id))
                && (this.platformId == null || this.platformId.equals(t.platformId))
                && (this.goodsId == null || this.goodsId.equals(t.goodsId))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName))
                && (this.status == null || this.status.equals(t.status));
    }

    @Override
    public void update(PlatformGoods element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.platformId != null && !this.platformId.isEmpty()) {
            element.platformId = this.platformId;
        }
        if (this.goodsId != null && !this.goodsId.isEmpty()) {
            element.goodsId = this.goodsId;
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
        if (this.status != null) {
            element.status = this.status;
        }
    }
}
