package com.jinpaihushi.jphs.information.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * INFORMATION 
 * 继承自父类的字段:
 * id : 	
 * type : 类型	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Information extends BaseModel implements Predicate<Information>,
		Updator<Information> {


    /** 频道id，多个以逗号隔开 */
	@Length(max = 500, message = "{information.informationChannelId.illegal.length}")
	private String informationChannelId;
	
    /** 题标 */
	@Length(max = 255, message = "{information.title.illegal.length}")
	private String title;
	
	/** 简介 */
	@Length(max = 255, message = "{information.brief.illegal.length}")
	private String brief;

    /** 标题图片，多个以逗号隔开 */
	@Length(max = 255, message = "{information.image.illegal.length}")
	private String image;

    /** 内容，编辑器编辑 */
	@Length(max = 65535, message = "{information.content.illegal.length}")
	private String content;

    /** 预览数量 */
	private Integer previewNumber;

    /** 置顶 */
	private Integer top;

    /** 出处 */
	@Length(max = 50, message = "{information.source.illegal.length}")
	private String source;

    /** 作者 */
	@Length(max = 50, message = "{information.author.illegal.length}")
	private String author;

    /** 置顶时间 */
	private Date topTime;
	/**
	 * 资讯评价
	 */
	private List<InformationEvaluate> informationEvaluate;
	private Boolean collection =false;
	public Information(){}

	public Information(String id){
		this.id = id;
	}

	/**
	 * 获取频道id，多个以逗号隔开
	 */
	public String getInformationChannelId() {
    	return informationChannelId;
    }
  	
	/**
	 * 设置频道id，多个以逗号隔开
	 */
	public void setInformationChannelId(String informationChannelId) {
    	this.informationChannelId = informationChannelId;
    }

	/**
	 * 获取题标
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置题标
	 */
	public void setTitle(String title) {
    	this.title = title;
    }
	
	/**
	 * 获取简介
	 */
	public String getBrief() {
    	return brief;
    }
  	
	/**
	 * 设置简介
	 */
	public void setBrief(String brief) {
    	this.brief = brief;
    }

	/**
	 * 获取标题图片，多个以逗号隔开
	 */
	public String getImage() {
    	return image;
    }
  	
	/**
	 * 设置标题图片，多个以逗号隔开
	 */
	public void setImage(String image) {
    	this.image = image;
    }

	/**
	 * 获取内容，编辑器编辑
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置内容，编辑器编辑
	 */
	public void setContent(String content) {
    	this.content = content;
    }

	/**
	 * 获取预览数量
	 */
	public Integer getPreviewNumber() {
    	return previewNumber;
    }
  	
	/**
	 * 设置预览数量
	 */
	public void setPreviewNumber(Integer previewNumber) {
    	this.previewNumber = previewNumber;
    }

	/**
	 * 获取置顶
	 */
	public Integer getTop() {
    	return top;
    }
  	
	/**
	 * 设置置顶
	 */
	public void setTop(Integer top) {
    	this.top = top;
    }

	/**
	 * 获取出处
	 */
	public String getSource() {
    	return source;
    }
  	
	/**
	 * 设置出处
	 */
	public void setSource(String source) {
    	this.source = source;
    }

	/**
	 * 获取作者
	 */
	public String getAuthor() {
    	return author;
    }
  	
	/**
	 * 设置作者
	 */
	public void setAuthor(String author) {
    	this.author = author;
    }

	/**
	 * 获取置顶时间
	 */
	public Date getTopTime() {
    	return topTime;
    }
  	
	/**
	 * 设置置顶时间
	 */
	public void setTopTime(Date topTime) {
    	this.topTime = topTime;
    }
	
    public List<InformationEvaluate> getInformationEvaluate() {
		return informationEvaluate;
	}

	public void setInformationEvaluate(List<InformationEvaluate> informationEvaluate) {
		this.informationEvaluate = informationEvaluate;
	}
	
	public Boolean getCollection() {
		return collection;
	}

	public void setCollection(Boolean collection) {
		this.collection = collection;
	}

	public String toString() {
		return new StringBuilder().append("Information{").
			append("id=").append(id).
			append(",informationChannelId=").append(informationChannelId).
			append(",title=").append(title).
			append(",image=").append(image).
			append(",content=").append(content).
			append(",previewNumber=").append(previewNumber).
			append(",top=").append(top).
			append(",type=").append(type).
			append(",status=").append(status).
			append(",source=").append(source).
			append(",author=").append(author).
			append(",topTime=").append(topTime).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, informationChannelId, title, image, 
	 * content, previewNumber, top, type, 
	 * status, source, author, topTime, 
	 * createTime, creatorId, creatorName
	 */
	public Information copy(){
		Information information = new Information();
     	information.id = this.id;
     	information.informationChannelId = this.informationChannelId;
     	information.title = this.title;
     	information.image = this.image;
     	information.content = this.content;
     	information.previewNumber = this.previewNumber;
     	information.top = this.top;
     	information.type = this.type;
     	information.status = this.status;
     	information.source = this.source;
     	information.author = this.author;
     	information.topTime = this.topTime;
     	information.createTime = this.createTime;
     	information.creatorId = this.creatorId;
     	information.creatorName = this.creatorName;
		return information;
	}
	
	/**
	 * 比较字段：
	 * id, informationChannelId, title, image, 
	 * content, previewNumber, top, type, 
	 * status, source, author, topTime, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(Information t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.informationChannelId == null || this.informationChannelId.equals(t.informationChannelId))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.image == null || this.image.equals(t.image))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.previewNumber == null || this.previewNumber.equals(t.previewNumber))
				&& (this.top == null || this.top.equals(t.top))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.source == null || this.source.equals(t.source))
				&& (this.author == null || this.author.equals(t.author))
				&& (this.topTime == null || this.topTime.equals(t.topTime))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Information element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.informationChannelId != null && !this.informationChannelId.isEmpty()) {
			element.informationChannelId = this.informationChannelId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.image != null && !this.image.isEmpty()) {
			element.image = this.image;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
		}
		if (this.previewNumber != null) {
			element.previewNumber = this.previewNumber;
		}
		if (this.top != null) {
			element.top = this.top;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.source != null && !this.source.isEmpty()) {
			element.source = this.source;
		}
		if (this.author != null && !this.author.isEmpty()) {
			element.author = this.author;
		}
		if (this.topTime != null) {
			element.topTime = this.topTime;
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
