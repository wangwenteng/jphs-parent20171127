package com.jinpaihushi.jphs.access.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * ACCESS_LOG 平台日志数据
 * 继承自父类的字段:
 * id : 	
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AccessLog extends BaseModel implements Predicate<AccessLog>, Updator<AccessLog> {

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

    public AccessLog() {
    }

    public AccessLog(String id) {
        this.id = id;
    }

    /**
     * 获取商品请求地址
     */
    public String getGoodsUrlId() {
        return goodsUrlId;
    }

    /**
     * 设置商品请求地址
     */
    public void setGoodsUrlId(String goodsUrlId) {
        this.goodsUrlId = goodsUrlId;
    }

    /**
     * 获取
     */
    public Integer getUv() {
        return uv;
    }

    /**
     * 设置
     */
    public void setUv(Integer uv) {
        this.uv = uv;
    }

    /**
     * 获取
     */
    public Integer getPv() {
        return pv;
    }

    /**
     * 设置
     */
    public void setPv(Integer pv) {
        this.pv = pv;
    }

    /**
     * 获取访问日期
     */
    public Date getAccesstime() {
        return accesstime;
    }

    /**
     * 设置访问日期
     */
    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    /**
     * 获取开始时间
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * 设置开始时间
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 获取结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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

    public String toString() {
        return new StringBuilder().append("AccessLog{").append("id=").append(id).append(",goodsUrlId=")
                .append(goodsUrlId).append(",uv=").append(uv).append(",pv=").append(pv).append(",accesstime=")
                .append(accesstime).append(",starttime=").append(starttime).append(",endtime=").append(endtime)
                .append(",platformId=").append(platformId).append('}').toString();
    }

    /**
     * 复制字段：
     * id, goodsUrlId, uv, pv, 
     * accesstime, starttime, endtime, platformId
     */
    public AccessLog copy() {
        AccessLog accessLog = new AccessLog();
        accessLog.id = this.id;
        accessLog.goodsUrlId = this.goodsUrlId;
        accessLog.uv = this.uv;
        accessLog.pv = this.pv;
        accessLog.accesstime = this.accesstime;
        accessLog.starttime = this.starttime;
        accessLog.endtime = this.endtime;
        accessLog.platformId = this.platformId;
        return accessLog;
    }

    /**
     * 比较字段：
     * id, goodsUrlId, uv, pv, 
     * accesstime, starttime, endtime, platformId
     */
    @Override
    public boolean test(AccessLog t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id))
                && (this.goodsUrlId == null || this.goodsUrlId.equals(t.goodsUrlId))
                && (this.uv == null || this.uv.equals(t.uv)) && (this.pv == null || this.pv.equals(t.pv))
                && (this.accesstime == null || this.accesstime.equals(t.accesstime))
                && (this.starttime == null || this.starttime.equals(t.starttime))
                && (this.endtime == null || this.endtime.equals(t.endtime))
                && (this.platformId == null || this.platformId.equals(t.platformId));
    }

    @Override
    public void update(AccessLog element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.goodsUrlId != null && !this.goodsUrlId.isEmpty()) {
            element.goodsUrlId = this.goodsUrlId;
        }
        if (this.uv != null) {
            element.uv = this.uv;
        }
        if (this.pv != null) {
            element.pv = this.pv;
        }
        if (this.accesstime != null) {
            element.accesstime = this.accesstime;
        }
        if (this.starttime != null) {
            element.starttime = this.starttime;
        }
        if (this.endtime != null) {
            element.endtime = this.endtime;
        }
        if (this.platformId != null && !this.platformId.isEmpty()) {
            element.platformId = this.platformId;
        }
    }
}
