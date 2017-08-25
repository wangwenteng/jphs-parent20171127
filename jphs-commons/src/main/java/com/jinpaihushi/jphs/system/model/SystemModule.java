package com.jinpaihushi.jphs.system.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SYSTEM_MODULE 系统权限表
 * 继承自父类的字段:
 * id : 	
 * status : 状态	
 * createTime : 	
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SystemModule extends BaseModel implements Predicate<SystemModule>,
		Updator<SystemModule> {


    /** 名称 */
	@NotEmpty(message = "{systemModule.name.empty}")
	@Length(max = 50, message = "{systemModule.name.illegal.length}")
	private String name;

    /** 链接地址 */
	@NotEmpty(message = "{systemModule.url.empty}")
	@Length(max = 200, message = "{systemModule.url.illegal.length}")
	private String url;

    /** 上级菜单ID */
	@NotEmpty(message = "{systemModule.parentId.empty}")
	@Length(max = 50, message = "{systemModule.parentId.illegal.length}")
	private String parentId;
	private String text;
	private String state ="open"; 
	private Boolean checked =false;
	private List<SystemModule> children;
	public SystemModule(){}

	public SystemModule(String id){
		this.id = id;
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
	 * 获取链接地址
	 */
	public String getUrl() {
    	return url;
    }
  	
	/**
	 * 设置链接地址
	 */
	public void setUrl(String url) {
    	this.url = url;
    }

	/**
	 * 获取上级菜单名称
	 */
	public String getParentId() {
    	return parentId;
    }
  	
	/**
	 * 设置上级菜单名称
	 */
	public void setParentId(String parentId) {
    	this.parentId = parentId;
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<SystemModule> getChildren() {
		return children;
	}

	public void setChildren(List<SystemModule> children) {
		this.children = children;
	}

	public String toString() {
		return new StringBuilder().append("SystemModule{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",status=").append(status).
			append(",url=").append(url).
			append(",parentId=").append(parentId).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, status, url, 
	 * parentId, createTime
	 */
	public SystemModule copy(){
		SystemModule systemModule = new SystemModule();
     	systemModule.id = this.id;
     	systemModule.name = this.name;
     	systemModule.status = this.status;
     	systemModule.url = this.url;
     	systemModule.parentId = this.parentId;
     	systemModule.createTime = this.createTime;
		return systemModule;
	}
	
	/**
	 * 比较字段：
	 * id, name, status, url, 
	 * parentId, createTime
	 */
	@Override
	public boolean test(SystemModule t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.url == null || this.url.equals(t.url))
				&& (this.parentId == null || this.parentId.equals(t.parentId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(SystemModule element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.url != null && !this.url.isEmpty()) {
			element.url = this.url;
		}
		if (this.parentId != null && !this.parentId.isEmpty()) {
			element.parentId = this.parentId;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
	}
}
