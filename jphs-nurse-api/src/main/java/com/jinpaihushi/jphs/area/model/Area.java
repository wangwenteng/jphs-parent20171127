package com.jinpaihushi.jphs.area.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * AREA 区域表
 * 继承自父类的字段:
 * id : 	
 * type : 0、站点 1、护士	
 * creatorId : 创建人姓名	
 * creatorName : 创建人ID	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-06-28 08:54:36
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Area extends BaseModel implements Predicate<Area>,
		Updator<Area> {


    /** 护士ID */
	@Length(max = 50, message = "{area.sourceId.illegal.length}")
	private String sourceId;

    /** 区域ID */
	@NotEmpty(message = "{area.location.empty}")
	@Length(max = 100, message = "{area.location.illegal.length}")
	private String location;

	public Area(){}

	public Area(String id){
		this.id = id;
	}

	/**
	 * 获取护士ID
	 */
	public String getSourceId() {
    	return sourceId;
    }
  	
	/**
	 * 设置护士ID
	 */
	public void setSourceId(String sourceId) {
    	this.sourceId = sourceId;
    }

	/**
	 * 获取区域ID
	 */
	public String getLocation() {
    	return location;
    }
  	
	/**
	 * 设置区域ID
	 */
	public void setLocation(String location) {
    	this.location = location;
    }

    public String toString() {
		return new StringBuilder().append("Area{").
			append("id=").append(id).
			append(",sourceId=").append(sourceId).
			append(",location=").append(location).
			append(",type=").append(type).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, sourceId, location, type, 
	 * creatorId, creatorName, createTime, status
	 */
	public Area copy(){
		Area area = new Area();
     	area.id = this.id;
     	area.sourceId = this.sourceId;
     	area.location = this.location;
     	area.type = this.type;
     	area.creatorId = this.creatorId;
     	area.creatorName = this.creatorName;
     	area.createTime = this.createTime;
     	area.status = this.status;
		return area;
	}
	
	/**
	 * 比较字段：
	 * id, sourceId, location, type, 
	 * creatorId, creatorName, createTime, status
	 */
	@Override
	public boolean test(Area t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.sourceId == null || this.sourceId.equals(t.sourceId))
				&& (this.location == null || this.location.equals(t.location))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Area element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.sourceId != null && !this.sourceId.isEmpty()) {
			element.sourceId = this.sourceId;
		}
		if (this.location != null && !this.location.isEmpty()) {
			element.location = this.location;
		}
		if (this.type != null) {
			element.type = this.type;
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
