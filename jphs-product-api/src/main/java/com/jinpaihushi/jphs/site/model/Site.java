package com.jinpaihushi.jphs.site.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.model.BaseModel;

/**
 * SITE 
 * 继承自父类的字段:
 * id : id	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-06-21 14:43:29
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Site extends BaseModel implements Predicate<Site>,
		Updator<Site> {


    /** 名称 */
	@Length(max = 50, message = "{site.name.illegal.length}")
	private String name;

    /** 域名 */
	@Length(max = 100, message = "{site.url.illegal.length}")
	private String url;

    /** 服务区域 */
	@Length(max = 50, message = "{site.locationId.illegal.length}")
	private String locationId;
	private List<Location> location;	
	/**
	 * 服务区域
	 */
	private String areas;
	
    /** 备注 */
	@Length(max = 500, message = "{site.remark.illegal.length}")
	private String remark;
	private Boolean checked =false;
	public Site(){}

	public Site(String id){
		this.id = id;
	}
	
	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}

	/**
	 * 获取名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置名称
	 */
	public void setName(String name) {
    	this.name = name;
    }
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * 获取域名
	 */
	public String getUrl() {
    	return url;
    }
  	
	/**
	 * 设置域名
	 */
	public void setUrl(String url) {
    	this.url = url;
    }

	/**
	 * 获取服务区域
	 */
	public String getLocationId() {
    	return locationId;
    }
  	
	/**
	 * 设置服务区域
	 */
	public void setLocationId(String locationId) {
    	this.locationId = locationId;
    }


	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
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

    public String toString() {
		return new StringBuilder().append("Site{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",url=").append(url).
			append(",locationId=").append(locationId).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, url, locationId, 
	 * remark, status, createTime, creatorId, 
	 * creatorName
	 */
	public Site copy(){
		Site site = new Site();
     	site.id = this.id;
     	site.name = this.name;
     	site.url = this.url;
     	site.locationId = this.locationId;
     	site.remark = this.remark;
     	site.status = this.status;
     	site.createTime = this.createTime;
     	site.creatorId = this.creatorId;
     	site.creatorName = this.creatorName;
		return site;
	}
	
	/**
	 * 比较字段：
	 * id, name, url, locationId, 
	 * remark, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(Site t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.url == null || this.url.equals(t.url))
				&& (this.locationId == null || this.locationId.equals(t.locationId))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Site element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.url != null && !this.url.isEmpty()) {
			element.url = this.url;
		}
		if (this.locationId != null && !this.locationId.isEmpty()) {
			element.locationId = this.locationId;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
