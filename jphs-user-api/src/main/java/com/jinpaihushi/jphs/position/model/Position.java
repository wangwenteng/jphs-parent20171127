package com.jinpaihushi.jphs.position.model;

import java.util.function.Predicate;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * POSITION 
 * 继承自父类的字段:
 * id : 定位id	
 * creatorId : 创建人id	
 * creatorName : 创建人名字	
 * createTime : 创建时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author wangwt
 * @date 2017-08-17 18:15:16
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Position extends BaseModel implements Predicate<Position>, Updator<Position> {

    /** 经度 */
    private Double lon;

    /** 纬度 */
    private Double lat;

    public Position() {
    }

    public Position(String id) {
        this.id = id;
    }

    /**
     * 获取经度
     */
    public Double getLon() {
        return lon;
    }

    /**
     * 设置经度
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * 获取纬度
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 设置纬度
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String toString() {
        return new StringBuilder().append("Position{").append("id=").append(id).append(",lon=").append(lon)
                .append(",lat=").append(lat).append(",creatorId=").append(creatorId).append(",creatorName=")
                .append(creatorName).append(",createTime=").append(createTime).append(",status=").append(status)
                .append('}').toString();
    }

    /**
     * 复制字段：
     * id, lon, lat, creatorId, 
     * creatorName, createTime, status
     */
    public Position copy() {
        Position position = new Position();
        position.id = this.id;
        position.lon = this.lon;
        position.lat = this.lat;
        position.creatorId = this.creatorId;
        position.creatorName = this.creatorName;
        position.createTime = this.createTime;
        position.status = this.status;
        return position;
    }

    /**
     * 比较字段：
     * id, lon, lat, creatorId, 
     * creatorName, createTime, status
     */
    @Override
    public boolean test(Position t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.lon == null || this.lon.equals(t.lon))
                && (this.lat == null || this.lat.equals(t.lat))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.status == null || this.status.equals(t.status));
    }

    @Override
    public void update(Position element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.lon != null) {
            element.lon = this.lon;
        }
        if (this.lat != null) {
            element.lat = this.lat;
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
        if (this.status != null) {
            element.status = this.status;
        }
    }
}
