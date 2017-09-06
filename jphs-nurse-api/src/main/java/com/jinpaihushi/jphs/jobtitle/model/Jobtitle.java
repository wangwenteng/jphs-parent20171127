package com.jinpaihushi.jphs.jobtitle.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JOBTITLE 职称
 * 继承自父类的字段:
 * id : 	
 * creatorId : 创建人id	
 * creatorName : 创建人姓名	
 * createTime : 创建时间	
 * status : 状态(0、正常，-1删除）	
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:00
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Jobtitle extends BaseModel implements Predicate<Jobtitle>,
		Updator<Jobtitle> {


    /** 职称名称 */
	@Length(max = 128, message = "{jobtitle.name.illegal.length}")
	private String name;
	
	private String jtName;

    /** 职称类型id */
	@Length(max = 50, message = "{jobtitle.jobtitleTypeId.illegal.length}")
	private String jobtitleTypeId;
	private Boolean checked =false;
	public Jobtitle(){}

	public Jobtitle(String id){
		this.id = id;
	}
	
	/** 等级 */
	private Integer grade;
	
	public String getJtName() {
    	return jtName;
    }
	public void setJtName(String jtName) {
    	this.jtName = jtName;
    }
	
	/**
	 * 获取等级
	 */
	public Integer getGrade() {
    	return grade;
    }
  	
	/**
	 * 设置等级
	 */
	public void setGrade(Integer grade) {
    	this.grade = grade;
    }

	/**
	 * 获取职称名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置职称名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取护士类型id
	 */
	public String getJobtitleTypeId() {
    	return jobtitleTypeId;
    }
  	
	/**
	 * 设置护士类型id
	 */
	public void setJobtitleTypeId(String jobtitleTypeId) {
    	this.jobtitleTypeId = jobtitleTypeId;
    }
	
    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String toString() {
		return new StringBuilder().append("Jobtitle{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",jobtitleTypeId=").append(jobtitleTypeId).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, jobtitleTypeId, creatorId, 
	 * creatorName, createTime, status
	 */
	public Jobtitle copy(){
		Jobtitle jobtitle = new Jobtitle();
     	jobtitle.id = this.id;
     	jobtitle.name = this.name;
     	jobtitle.jobtitleTypeId = this.jobtitleTypeId;
     	jobtitle.creatorId = this.creatorId;
     	jobtitle.creatorName = this.creatorName;
     	jobtitle.createTime = this.createTime;
     	jobtitle.status = this.status;
		return jobtitle;
	}
	
	/**
	 * 比较字段：
	 * id, name, jobtitleTypeId, creatorId, 
	 * creatorName, createTime, status
	 */
	@Override
	public boolean test(Jobtitle t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.jobtitleTypeId == null || this.jobtitleTypeId.equals(t.jobtitleTypeId))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Jobtitle element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.jobtitleTypeId != null && !this.jobtitleTypeId.isEmpty()) {
			element.jobtitleTypeId = this.jobtitleTypeId;
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
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
