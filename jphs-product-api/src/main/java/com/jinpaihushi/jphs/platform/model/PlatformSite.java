package com.jinpaihushi.jphs.platform.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM_SITE  平台站点关系中间表
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwt
 * @date 2017-06-21 15:30:05
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlatformSite extends BaseModel implements Predicate<PlatformSite>, Updator<PlatformSite> {

    /** 平台id  */
    @Length(max = 50, message = "{platformSite.platformId.illegal.length}")
    private String platformId;

    /** 站点id  */
    @Length(max = 50, message = "{platformSite.siteId.illegal.length}")
    private String siteId;

    /** 置配平台入口地址 */
    @Length(max = 500, message = "{platformSite.url.illegal.length}")
    private String url;

    public PlatformSite() {
    }

    public PlatformSite(String id) {
        this.id = id;
    }

    /**
     * 获取
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * 设置
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    /**
     * 获取
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * 设置
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取置配平台入口地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置置配平台入口地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return new StringBuilder().append("PlatformSite{").append("id=").append(id).append(",platformId=")
                .append(platformId).append(",siteId=").append(siteId).append(",url=").append(url).append(",status=")
                .append(status).append(",createTime=").append(createTime).append(",creatorId=").append(creatorId)
                .append(",creatorName=").append(creatorName).append('}').toString();
    }

    /**
     * 复制字段：
     * id, platformId, siteId, url, 
     * status, createTime, creatorId, creatorName
     */
    public PlatformSite copy() {
        PlatformSite platformSite = new PlatformSite();
        platformSite.id = this.id;
        platformSite.platformId = this.platformId;
        platformSite.siteId = this.siteId;
        platformSite.url = this.url;
        platformSite.status = this.status;
        platformSite.createTime = this.createTime;
        platformSite.creatorId = this.creatorId;
        platformSite.creatorName = this.creatorName;
        return platformSite;
    }

    /**
     * 比较字段：
     * id, platformId, siteId, url, 
     * status, createTime, creatorId, creatorName
     */
    @Override
    public boolean test(PlatformSite t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id))
                && (this.platformId == null || this.platformId.equals(t.platformId))
                && (this.siteId == null || this.siteId.equals(t.siteId)) && (this.url == null || this.url.equals(t.url))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName));
    }

    @Override
    public void update(PlatformSite element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.platformId != null && !this.platformId.isEmpty()) {
            element.platformId = this.platformId;
        }
        if (this.siteId != null && !this.siteId.isEmpty()) {
            element.siteId = this.siteId;
        }
        if (this.url != null && !this.url.isEmpty()) {
            element.url = this.url;
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
