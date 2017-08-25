package com.jinpaihushi.jphs.tijian.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * TIJIAN_HOSPITAL 
 * 继承自父类的字段:
 * id : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-08-08 15:34:53
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TijianHospital extends BaseModel implements Predicate<TijianHospital>,
		Updator<TijianHospital> {


    /** 医院名字 */
	@Length(max = 255, message = "{tijianHospital.name.illegal.length}")
	private String name;

    /** 最低价位 */
	private Double price;

    /** 图片 */
	@Length(max = 255, message = "{tijianHospital.imageurl.illegal.length}")
	private String imageurl;

    /** 介绍图片 */
	@Length(max = 255, message = "{tijianHospital.detailImageurl.illegal.length}")
	private String detailImageurl;

    /** 医院等级名称 */
	@Length(max = 255, message = "{tijianHospital.gradeName.illegal.length}")
	private String gradeName;

    /** 医院地址 */
	@Length(max = 255, message = "{tijianHospital.address.illegal.length}")
	private String address;

	public TijianHospital(){}

	public TijianHospital(String id){
		this.id = id;
	}

	/**
	 * 获取医院名字
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置医院名字
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取最低价位
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置最低价位
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取图片
	 */
	public String getImageurl() {
    	return imageurl;
    }
  	
	/**
	 * 设置图片
	 */
	public void setImageurl(String imageurl) {
    	this.imageurl = imageurl;
    }

	/**
	 * 获取介绍图片
	 */
	public String getDetailImageurl() {
    	return detailImageurl;
    }
  	
	/**
	 * 设置介绍图片
	 */
	public void setDetailImageurl(String detailImageurl) {
    	this.detailImageurl = detailImageurl;
    }

	/**
	 * 获取医院等级名称
	 */
	public String getGradeName() {
    	return gradeName;
    }
  	
	/**
	 * 设置医院等级名称
	 */
	public void setGradeName(String gradeName) {
    	this.gradeName = gradeName;
    }

	/**
	 * 获取医院地址
	 */
	public String getAddress() {
    	return address;
    }
  	
	/**
	 * 设置医院地址
	 */
	public void setAddress(String address) {
    	this.address = address;
    }

    public String toString() {
		return new StringBuilder().append("TijianHospital{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",price=").append(price).
			append(",imageurl=").append(imageurl).
			append(",detailImageurl=").append(detailImageurl).
			append(",gradeName=").append(gradeName).
			append(",address=").append(address).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, price, imageurl, 
	 * detailImageurl, gradeName, address, createTime, 
	 * creatorId, creatorName, status
	 */
	public TijianHospital copy(){
		TijianHospital tijianHospital = new TijianHospital();
     	tijianHospital.id = this.id;
     	tijianHospital.name = this.name;
     	tijianHospital.price = this.price;
     	tijianHospital.imageurl = this.imageurl;
     	tijianHospital.detailImageurl = this.detailImageurl;
     	tijianHospital.gradeName = this.gradeName;
     	tijianHospital.address = this.address;
     	tijianHospital.createTime = this.createTime;
     	tijianHospital.creatorId = this.creatorId;
     	tijianHospital.creatorName = this.creatorName;
     	tijianHospital.status = this.status;
		return tijianHospital;
	}
	
	/**
	 * 比较字段：
	 * id, name, price, imageurl, 
	 * detailImageurl, gradeName, address, createTime, 
	 * creatorId, creatorName, status
	 */
	@Override
	public boolean test(TijianHospital t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.imageurl == null || this.imageurl.equals(t.imageurl))
				&& (this.detailImageurl == null || this.detailImageurl.equals(t.detailImageurl))
				&& (this.gradeName == null || this.gradeName.equals(t.gradeName))
				&& (this.address == null || this.address.equals(t.address))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(TijianHospital element) {
		if (element == null)
			return;
		if (this.id != null) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.imageurl != null && !this.imageurl.isEmpty()) {
			element.imageurl = this.imageurl;
		}
		if (this.detailImageurl != null && !this.detailImageurl.isEmpty()) {
			element.detailImageurl = this.detailImageurl;
		}
		if (this.gradeName != null && !this.gradeName.isEmpty()) {
			element.gradeName = this.gradeName;
		}
		if (this.address != null && !this.address.isEmpty()) {
			element.address = this.address;
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
