package com.jinpaihushi.jphs.column.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COLUMN_SERVICE 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-08-07 14:05:57
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ColumnService extends BaseModel implements Predicate<ColumnService>,
		Updator<ColumnService> {


    /** columnID */
	@Length(max = 50, message = "{columnService.columnId.illegal.length}")
	private String columnId;

    /** 标图 */
	@Length(max = 255, message = "{columnService.image.illegal.length}")
	private String image;

    /** 名称 */
	@Length(max = 255, message = "{columnService.name.illegal.length}")
	private String name;

    /** 接链 */
	@Length(max = 255, message = "{columnService.link.illegal.length}")
	private String link;

    /** 介简 */
	@Length(max = 65535, message = "{columnService.brief.illegal.length}")
	private String brief;

	public ColumnService(){}

	public ColumnService(String id){
		this.id = id;
	}

	/**
	 * 获取columnID
	 */
	public String getColumnId() {
    	return columnId;
    }
  	
	/**
	 * 设置columnID
	 */
	public void setColumnId(String columnId) {
    	this.columnId = columnId;
    }

	/**
	 * 获取标图
	 */
	public String getImage() {
    	return image;
    }
  	
	/**
	 * 设置标图
	 */
	public void setImage(String image) {
    	this.image = image;
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

	/**
	 * 获取接链
	 */
	public String getLink() {
    	return link;
    }
  	
	/**
	 * 设置接链
	 */
	public void setLink(String link) {
    	this.link = link;
    }

	/**
	 * 获取介简
	 */
	public String getBrief() {
    	return brief;
    }
  	
	/**
	 * 设置介简
	 */
	public void setBrief(String brief) {
    	this.brief = brief;
    }

    public String toString() {
		return new StringBuilder().append("ColumnService{").
			append("id=").append(id).
			append(",columnId=").append(columnId).
			append(",image=").append(image).
			append(",name=").append(name).
			append(",link=").append(link).
			append(",brief=").append(brief).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, columnId, image, name, 
	 * link, brief, status, createTime, 
	 * creatorId, creatorName
	 */
	public ColumnService copy(){
		ColumnService columnService = new ColumnService();
     	columnService.id = this.id;
     	columnService.columnId = this.columnId;
     	columnService.image = this.image;
     	columnService.name = this.name;
     	columnService.link = this.link;
     	columnService.brief = this.brief;
     	columnService.status = this.status;
     	columnService.createTime = this.createTime;
     	columnService.creatorId = this.creatorId;
     	columnService.creatorName = this.creatorName;
		return columnService;
	}
	
	/**
	 * 比较字段：
	 * id, columnId, image, name, 
	 * link, brief, status, createTime, 
	 * creatorId, creatorName
	 */
	@Override
	public boolean test(ColumnService t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.columnId == null || this.columnId.equals(t.columnId))
				&& (this.image == null || this.image.equals(t.image))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.link == null || this.link.equals(t.link))
				&& (this.brief == null || this.brief.equals(t.brief))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(ColumnService element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.columnId != null && !this.columnId.isEmpty()) {
			element.columnId = this.columnId;
		}
		if (this.image != null && !this.image.isEmpty()) {
			element.image = this.image;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.link != null && !this.link.isEmpty()) {
			element.link = this.link;
		}
		if (this.brief != null && !this.brief.isEmpty()) {
			element.brief = this.brief;
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
