package com.jinpaihushi.jphs.access.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.model.BaseModel;

/**
 * ACCESS_LOG_SPREAD 平台日志数据扩展类
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AccessLogSpread extends BaseModel {

    /** 商品请求地址 */
    @Length(max = 50, message = "{accessLog.goodsUrlId.illegal.length}")
    private String goodsUrlId;

    /**  */
    private Integer uv;

    /**  */
    private Integer pv;

    /** 访问日期 */
    private Date accesstime;

    /** 开始时间 */
    private Date starttime;

    /** 结束时间 */
    private Date endtime;

    /** 平台id */
    @Length(max = 50, message = "{accessLog.platformId.illegal.length}")
    private String platformId;

    private String goodsUrl;

    private String platformName;

    private String hour;

    private String accessMonth;

    public String getAccessMonth() {
        return accessMonth;
    }

    public void setAccessMonth(String accessMonth) {
        this.accessMonth = accessMonth;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsUrlId() {
        return goodsUrlId;
    }

    public void setGoodsUrlId(String goodsUrlId) {
        this.goodsUrlId = goodsUrlId;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String toString() {
        return new StringBuilder().append("AccessLog{").append("id=").append(id).append(",goodsUrlId=")
                .append(goodsUrlId).append(",uv=").append(uv).append(",pv=").append(pv).append(",accesstime=")
                .append(accesstime).append(",starttime=").append(starttime).append(",endtime=").append(endtime)
                .append(",platformId=").append(platformId).append('}').toString();
    }
}
