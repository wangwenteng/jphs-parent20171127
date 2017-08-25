package com.jinpaihushi.jphs.sysversion.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SYSVERSION 
 * 继承自父类的字段:
 * id : 	
 * type : 1苹果，2安卓	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-08-08 10:46:13
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Sysversion extends BaseModel implements Predicate<Sysversion>,
		Updator<Sysversion> {


    /** 版本号 */
	@Length(max = 50, message = "{sysversion.version.illegal.length}")
	private String version;

    /** app下载地址 */
	@Length(max = 255, message = "{sysversion.downloadUrl.illegal.length}")
	private String downloadUrl;

    /** 0不强制更新，1强制更新 */
	private Integer forceUpdating;

    /** 更新内容 */
	@Length(max = 65535, message = "{sysversion.content.illegal.length}")
	private String content;

    /** 0不隐藏，1隐藏 */
	private Integer hide;

	public Sysversion(){}

	public Sysversion(String id){
		this.id = id;
	}

	/**
	 * 获取版本号
	 */
	public String getVersion() {
    	return version;
    }
  	
	/**
	 * 设置版本号
	 */
	public void setVersion(String version) {
    	this.version = version;
    }

	/**
	 * 获取app下载地址
	 */
	public String getDownloadUrl() {
    	return downloadUrl;
    }
  	
	/**
	 * 设置app下载地址
	 */
	public void setDownloadUrl(String downloadUrl) {
    	this.downloadUrl = downloadUrl;
    }

	/**
	 * 获取0不强制更新，1强制更新
	 */
	public Integer getForceUpdating() {
    	return forceUpdating;
    }
  	
	/**
	 * 设置0不强制更新，1强制更新
	 */
	public void setForceUpdating(Integer forceUpdating) {
    	this.forceUpdating = forceUpdating;
    }

	/**
	 * 获取更新内容
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置更新内容
	 */
	public void setContent(String content) {
    	this.content = content;
    }

	/**
	 * 获取0不隐藏，1隐藏
	 */
	public Integer getHide() {
    	return hide;
    }
  	
	/**
	 * 设置0不隐藏，1隐藏
	 */
	public void setHide(Integer hide) {
    	this.hide = hide;
    }

    public String toString() {
		return new StringBuilder().append("Sysversion{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",version=").append(version).
			append(",downloadUrl=").append(downloadUrl).
			append(",forceUpdating=").append(forceUpdating).
			append(",content=").append(content).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",hide=").append(hide).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, version, downloadUrl, 
	 * forceUpdating, content, status, createTime, 
	 * creatorId, creatorName, hide
	 */
	public Sysversion copy(){
		Sysversion sysversion = new Sysversion();
     	sysversion.id = this.id;
     	sysversion.type = this.type;
     	sysversion.version = this.version;
     	sysversion.downloadUrl = this.downloadUrl;
     	sysversion.forceUpdating = this.forceUpdating;
     	sysversion.content = this.content;
     	sysversion.status = this.status;
     	sysversion.createTime = this.createTime;
     	sysversion.creatorId = this.creatorId;
     	sysversion.creatorName = this.creatorName;
     	sysversion.hide = this.hide;
		return sysversion;
	}
	
	/**
	 * 比较字段：
	 * id, type, version, downloadUrl, 
	 * forceUpdating, content, status, createTime, 
	 * creatorId, creatorName, hide
	 */
	@Override
	public boolean test(Sysversion t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.version == null || this.version.equals(t.version))
				&& (this.downloadUrl == null || this.downloadUrl.equals(t.downloadUrl))
				&& (this.forceUpdating == null || this.forceUpdating.equals(t.forceUpdating))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.hide == null || this.hide.equals(t.hide))
		;
	}
	
	@Override
	public void update(Sysversion element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.version != null && !this.version.isEmpty()) {
			element.version = this.version;
		}
		if (this.downloadUrl != null && !this.downloadUrl.isEmpty()) {
			element.downloadUrl = this.downloadUrl;
		}
		if (this.forceUpdating != null) {
			element.forceUpdating = this.forceUpdating;
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
		if (this.hide != null) {
			element.hide = this.hide;
		}
	}
}
