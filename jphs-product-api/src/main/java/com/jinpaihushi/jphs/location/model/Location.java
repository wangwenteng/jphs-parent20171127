package com.jinpaihushi.jphs.location.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * LOCATION 
 * 继承自父类的字段:
 * id : 地址id	
 * type : 等级	
 * 
 * @author wangwt
 * @date 2017-06-28 11:52:34
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Location extends BaseModel implements Predicate<Location>,
		Updator<Location> {


    /** 地址的名称 */
	@Length(max = 50, message = "{location.content.illegal.length}")
	private String content;

    /** 父id(上一级) */
	@Length(max = 50, message = "{location.parentId.illegal.length}")
	/**
	 * ztree 使用
	 */
	private String parentId;
	private String text;
	private String state ="closed"; 
	private Boolean checked =false;
	private List<Location> children;
	public Location(){}
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	private String pid;
	
	public String getPid() {
		return pid;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Location> getChildren() {
		return children;
	}

	public void setChildren(List<Location> children) {
		this.children = children;
	}

	public void setPid(String pid) {
		this.pid = this.parentId;
	}

	public Location(String id){
		this.id = id;
	}

	/**
	 * 获取地址的名称
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置地址的名称
	 */
	public void setContent(String content) {
    	this.content = content;
    }

	/**
	 * 获取父id(上一级)
	 */
	public String getParentId() {
    	return parentId;
    }
  	
	/**
	 * 设置父id(上一级)
	 */
	public void setParentId(String parentId) {
    	this.parentId = parentId;
    }

    public String toString() {
		return new StringBuilder().append("Location{").
			append("id=").append(id).
			append(",content=").append(content).
			append(",parentId=").append(parentId).
			append(",type=").append(type).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, content, parentId, type
	 */
	public Location copy(){
		Location location = new Location();
     	location.id = this.id;
     	location.content = this.content;
     	location.parentId = this.parentId;
     	location.type = this.type;
		return location;
	}
	
	/**
	 * 比较字段：
	 * id, content, parentId, type
	 */
	@Override
	public boolean test(Location t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.parentId == null || this.parentId.equals(t.parentId))
				&& (this.type == null || this.type.equals(t.type))
		;
	}
	
	@Override
	public void update(Location element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
		}
		if (this.parentId != null && !this.parentId.isEmpty()) {
			element.parentId = this.parentId;
		}
		if (this.type != null) {
			element.type = this.type;
		}
	}
}
