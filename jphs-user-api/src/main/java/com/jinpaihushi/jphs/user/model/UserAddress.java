package com.jinpaihushi.jphs.user.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * USER_ADDRESS 
 * 继承自父类的字段:
 * id : 	
 * createTime : 建创时间	
 * creatorId : 创建人id	
 * creatorName : 创建人姓名	
 * status : 状态 ( 0否（默认），-1删除)	
 * 
 * @author yangsong
 * @date 2017-07-03 15:09:14
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class UserAddress extends BaseModel implements Predicate<UserAddress>,
		Updator<UserAddress> {


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
	
	private Integer defaultAddress;
	
	private String phone;
	
	private String name;

	public UserAddress(){}

	public UserAddress(String id){
		this.id = id;
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
	
	

    public Integer getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(Integer defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return new StringBuilder().append("UserAddress{").
			append("id=").append(id).
			append(",title=").append(title).
			append(",province=").append(province).
			append(",city=").append(city).
			append(",area=").append(area).
			append(",detailaddress=").append(detailaddress).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, title, province, city, 
	 * area, detailaddress, createTime, creatorId, 
	 * creatorName, status
	 */
	public UserAddress copy(){
		UserAddress userAddress = new UserAddress();
     	userAddress.id = this.id;
     	userAddress.title = this.title;
     	userAddress.province = this.province;
     	userAddress.city = this.city;
     	userAddress.area = this.area;
     	userAddress.detailaddress = this.detailaddress;
     	userAddress.createTime = this.createTime;
     	userAddress.creatorId = this.creatorId;
     	userAddress.creatorName = this.creatorName;
     	userAddress.status = this.status;
		return userAddress;
	}
	
	/**
	 * 比较字段：
	 * id, title, province, city, 
	 * area, detailaddress, createTime, creatorId, 
	 * creatorName, status
	 */
	@Override
	public boolean test(UserAddress t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.province == null || this.province.equals(t.province))
				&& (this.city == null || this.city.equals(t.city))
				&& (this.area == null || this.area.equals(t.area))
				&& (this.detailaddress == null || this.detailaddress.equals(t.detailaddress))
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
