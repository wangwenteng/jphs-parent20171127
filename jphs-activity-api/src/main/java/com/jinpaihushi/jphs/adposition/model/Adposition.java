package com.jinpaihushi.jphs.adposition.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * ADPOSITION 广告位
 * 继承自父类的字段:
 * id : 	
 * status : 状态（1、启用 0 待开启 -1删除） 默认0	
 * creatorId : 	
 * creatorName : 	
 * createTime : 	
 * 
 * @author scj
 * @date 2017-08-07 11:47:31
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Adposition extends BaseModel implements Predicate<Adposition>,
		Updator<Adposition> {


    /** 广告位名称 */
	@Length(max = 255, message = "{adposition.name.illegal.length}")
	private String name;

    /** 链接地址 */
	@Length(max = 255, message = "{adposition.link.illegal.length}")
	private String link;

    /** 排序 */
	private Integer sort;

	public Adposition(){}

	public Adposition(String id){
		this.id = id;
	}

	/**
	 * 获取广告位名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置广告位名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取链接地址
	 */
	public String getLink() {
    	return link;
    }
  	
	/**
	 * 设置链接地址
	 */
	public void setLink(String link) {
    	this.link = link;
    }

	/**
	 * 获取排序
	 */
	public Integer getSort() {
    	return sort;
    }
  	
	/**
	 * 设置排序
	 */
	public void setSort(Integer sort) {
    	this.sort = sort;
    }

    public String toString() {
		return new StringBuilder().append("Adposition{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",link=").append(link).
			append(",sort=").append(sort).
			append(",status=").append(status).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, link, sort, 
	 * status, creatorId, creatorName, createTime
	 */
	public Adposition copy(){
		Adposition adposition = new Adposition();
     	adposition.id = this.id;
     	adposition.name = this.name;
     	adposition.link = this.link;
     	adposition.sort = this.sort;
     	adposition.status = this.status;
     	adposition.creatorId = this.creatorId;
     	adposition.creatorName = this.creatorName;
     	adposition.createTime = this.createTime;
		return adposition;
	}
	
	/**
	 * 比较字段：
	 * id, name, link, sort, 
	 * status, creatorId, creatorName, createTime
	 */
	@Override
	public boolean test(Adposition t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.link == null || this.link.equals(t.link))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(Adposition element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.link != null && !this.link.isEmpty()) {
			element.link = this.link;
		}
		if (this.sort != null) {
			element.sort = this.sort;
		}
		if (this.status != null) {
			element.status = this.status;
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
	}
}
