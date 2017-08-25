package com.jinpaihushi.jphs.activity.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * ADVERTISING 
 * 继承自父类的字段:
 * id : 轮播图id	
 * type : 类型  1=护士，2=用户，3=官网首页，4=资讯	
 * createTime : 创建时间	
 * creatorId : 创建人id	
 * creatorName : 创建人姓名	
 * status:状态（默认0  -1删除）
 * 
 * @author zhangzd
 * @date 2017-06-27 10:35:22
 * @company jinpaihushi
 * @version 1.0
 */

@SuppressWarnings("serial")
public class Advertising extends BaseModel implements Predicate<Advertising>,
		Updator<Advertising> {


    /** 链接地址 */
	@Length(max = 255, message = "{advertising.link.illegal.length}")
	private String link;

    /** 排序 */
	@NotNull(message = "{advertising.sort.null}")
	private Integer sort;

	/** 轮播图相关图片 */
	private String image;
	private String bgImage;
	public Advertising(){}

	public Advertising(String id){
		this.id = id;
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
	
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public String toString() {
		return new StringBuilder().append("Advertising{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",link=").append(link).
			append(",sort=").append(sort).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, link, sort, 
	 * createTime, creatorId, creatorName, status
	 */
	public Advertising copy(){
		Advertising advertising = new Advertising();
     	advertising.id = this.id;
     	advertising.type = this.type;
     	advertising.link = this.link;
     	advertising.sort = this.sort;
     	advertising.createTime = this.createTime;
     	advertising.creatorId = this.creatorId;
     	advertising.creatorName = this.creatorName;
     	advertising.status = this.status;
		return advertising;
	}
	
	/**
	 * 比较字段：
	 * id, type, link, sort, 
	 * createTime, creatorId, creatorName, status
	 */
	@Override
	public boolean test(Advertising t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.link == null || this.link.equals(t.link))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Advertising element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.link != null && !this.link.isEmpty()) {
			element.link = this.link;
		}
		if (this.sort != null) {
			element.sort = this.sort;
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
