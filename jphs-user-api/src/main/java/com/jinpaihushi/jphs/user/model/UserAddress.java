package com.jinpaihushi.jphs.user.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * USER_ADDRESS 地址管理
 * 继承自父类的字段:
 * id : 	
 * createTime : 建创时间	
 * creatorId : 创建人id	
 * creatorName : 创建人姓名	
 * status : 状态 ( 0否（默认），-1删除)	
 * 
 * @author wangwenteng
 * @date 2017-09-06 10:30:36
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class UserAddress extends BaseModel implements Predicate<UserAddress>,
		Updator<UserAddress> {


    /** 姓名 */
	@Length(max = 50, message = "{userAddress.name.illegal.length}")
	private String name;

    /** 电话 */
	@Length(max = 50, message = "{userAddress.phone.illegal.length}")
	private String phone;

    /** 默认地址 */
	private Integer defaultAddress;

    /** 标题地址 */
	@Length(max = 500, message = "{userAddress.title.illegal.length}")
	private String title;

    /** 省 */
	@Length(max = 100, message = "{userAddress.province.illegal.length}")
	private String province;

    /** 市 */
	@Length(max = 100, message = "{userAddress.city.illegal.length}")
	private String city;

    /** 区（县） */
	@Length(max = 100, message = "{userAddress.area.illegal.length}")
	private String area;

    /** 详细地址 */
	@Length(max = 500, message = "{userAddress.detailaddress.illegal.length}")
	private String detailaddress;

    /** 经度 */
	private Double lon;

    /** 纬度 */
	private Double lat;

	public UserAddress(){}

	public UserAddress(String id){
		this.id = id;
	}

	/**
	 * 获取姓名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置姓名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取电话
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置电话
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * 获取默认地址
	 */
	public Integer getDefaultAddress() {
    	return defaultAddress;
    }
  	
	/**
	 * 设置默认地址
	 */
	public void setDefaultAddress(Integer defaultAddress) {
    	this.defaultAddress = defaultAddress;
    }

	/**
	 * 获取标题地址
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置标题地址
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取省
	 */
	public String getProvince() {
    	return province;
    }
  	
	/**
	 * 设置省
	 */
	public void setProvince(String province) {
    	this.province = province;
    }

	/**
	 * 获取市
	 */
	public String getCity() {
    	return city;
    }
  	
	/**
	 * 设置市
	 */
	public void setCity(String city) {
    	this.city = city;
    }

	/**
	 * 获取区（县）
	 */
	public String getArea() {
    	return area;
    }
  	
	/**
	 * 设置区（县）
	 */
	public void setArea(String area) {
    	this.area = area;
    }

	/**
	 * 获取详细地址
	 */
	public String getDetailaddress() {
    	return detailaddress;
    }
  	
	/**
	 * 设置详细地址
	 */
	public void setDetailaddress(String detailaddress) {
    	this.detailaddress = detailaddress;
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
		return new StringBuilder().append("UserAddress{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",phone=").append(phone).
			append(",defaultAddress=").append(defaultAddress).
			append(",title=").append(title).
			append(",province=").append(province).
			append(",city=").append(city).
			append(",area=").append(area).
			append(",detailaddress=").append(detailaddress).
			append(",lon=").append(lon).
			append(",lat=").append(lat).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, phone, defaultAddress, 
	 * title, province, city, area, 
	 * detailaddress, lon, lat, createTime, 
	 * creatorId, creatorName, status
	 */
	public UserAddress copy(){
		UserAddress userAddress = new UserAddress();
     	userAddress.id = this.id;
     	userAddress.name = this.name;
     	userAddress.phone = this.phone;
     	userAddress.defaultAddress = this.defaultAddress;
     	userAddress.title = this.title;
     	userAddress.province = this.province;
     	userAddress.city = this.city;
     	userAddress.area = this.area;
     	userAddress.detailaddress = this.detailaddress;
     	userAddress.lon = this.lon;
     	userAddress.lat = this.lat;
     	userAddress.createTime = this.createTime;
     	userAddress.creatorId = this.creatorId;
     	userAddress.creatorName = this.creatorName;
     	userAddress.status = this.status;
		return userAddress;
	}
	
	/**
	 * 比较字段：
	 * id, name, phone, defaultAddress, 
	 * title, province, city, area, 
	 * detailaddress, lon, lat, createTime, 
	 * creatorId, creatorName, status
	 */
	@Override
	public boolean test(UserAddress t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.defaultAddress == null || this.defaultAddress.equals(t.defaultAddress))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.province == null || this.province.equals(t.province))
				&& (this.city == null || this.city.equals(t.city))
				&& (this.area == null || this.area.equals(t.area))
				&& (this.detailaddress == null || this.detailaddress.equals(t.detailaddress))
				&& (this.lon == null || this.lon.equals(t.lon))
				&& (this.lat == null || this.lat.equals(t.lat))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(UserAddress element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.defaultAddress != null) {
			element.defaultAddress = this.defaultAddress;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.province != null && !this.province.isEmpty()) {
			element.province = this.province;
		}
		if (this.city != null && !this.city.isEmpty()) {
			element.city = this.city;
		}
		if (this.area != null && !this.area.isEmpty()) {
			element.area = this.area;
		}
		if (this.detailaddress != null && !this.detailaddress.isEmpty()) {
			element.detailaddress = this.detailaddress;
		}
		if (this.lon != null) {
			element.lon = this.lon;
		}
		if (this.lat != null) {
			element.lat = this.lat;
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
