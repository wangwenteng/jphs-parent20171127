package com.jinpaihushi.jphs.family.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_PACKAGE 
 * 继承自父类的字段:
 * id : 	
 * type : 类型（1=单人版，2=三人版）	
 * status : 状态（默认0=正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FamilyPackage extends BaseModel implements Predicate<FamilyPackage>,
		Updator<FamilyPackage> {


    /** 套餐名称 */
	@Length(max = 50, message = "{familyPackage.title.illegal.length}")
	private String title;

    /** 介绍 */
	@Length(max = 255, message = "{familyPackage.subTitle.illegal.length}")
	private String subTitle;

    /** 价格 */
	private Double price;

    /** 头图 */
	@Length(max = 500, message = "{familyPackage.image.illegal.length}")
	private String image;

    /** 内容，文本编辑器 */
	@Length(max = 65535, message = "{familyPackage.content.illegal.length}")
	private String content;

	public FamilyPackage(){}

	public FamilyPackage(String id){
		this.id = id;
	}

	/**
	 * 获取套餐名称
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置套餐名称
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取介绍
	 */
	public String getSubTitle() {
    	return subTitle;
    }
  	
	/**
	 * 设置介绍
	 */
	public void setSubTitle(String subTitle) {
    	this.subTitle = subTitle;
    }

	/**
	 * 获取价格
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置价格
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取头图
	 */
	public String getImage() {
    	return image;
    }
  	
	/**
	 * 设置头图
	 */
	public void setImage(String image) {
    	this.image = image;
    }

	/**
	 * 获取内容，文本编辑器
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置内容，文本编辑器
	 */
	public void setContent(String content) {
    	this.content = content;
    }

    public String toString() {
		return new StringBuilder().append("FamilyPackage{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",title=").append(title).
			append(",subTitle=").append(subTitle).
			append(",price=").append(price).
			append(",image=").append(image).
			append(",content=").append(content).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, title, subTitle, 
	 * price, image, content, status, 
	 * createTime, creatorId, creatorName
	 */
	public FamilyPackage copy(){
		FamilyPackage familyPackage = new FamilyPackage();
     	familyPackage.id = this.id;
     	familyPackage.type = this.type;
     	familyPackage.title = this.title;
     	familyPackage.subTitle = this.subTitle;
     	familyPackage.price = this.price;
     	familyPackage.image = this.image;
     	familyPackage.content = this.content;
     	familyPackage.status = this.status;
     	familyPackage.createTime = this.createTime;
     	familyPackage.creatorId = this.creatorId;
     	familyPackage.creatorName = this.creatorName;
		return familyPackage;
	}
	
	/**
	 * 比较字段：
	 * id, type, title, subTitle, 
	 * price, image, content, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(FamilyPackage t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.subTitle == null || this.subTitle.equals(t.subTitle))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.image == null || this.image.equals(t.image))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyPackage element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.subTitle != null && !this.subTitle.isEmpty()) {
			element.subTitle = this.subTitle;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.image != null && !this.image.isEmpty()) {
			element.image = this.image;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
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
