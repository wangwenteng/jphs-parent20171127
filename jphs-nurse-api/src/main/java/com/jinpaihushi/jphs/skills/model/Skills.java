package com.jinpaihushi.jphs.skills.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SKILLS 
 * 继承自父类的字段:
 * id : 	
 * creatorName : 	
 * creatorId : 	
 * createTime : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-08-16 09:44:28
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Skills extends BaseModel implements Predicate<Skills>,
		Updator<Skills> {


    /**  */
	@Length(max = 50, message = "{skills.name.illegal.length}")
	private String name;
	
	private Integer skillsType;

	public Skills(){}

	public Skills(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public Integer getSkillsType() {
    	return skillsType;
    }
  	
	/**
	 * 设置
	 */
	public void setSkillsType(Integer skillsType) {
    	this.skillsType = skillsType;
    }
	
	/**
	 * 获取
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置
	 */
	public void setName(String name) {
    	this.name = name;
    }

    public String toString() {
		return new StringBuilder().append("Skills{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",creatorName=").append(creatorName).
			append(",creatorId=").append(creatorId).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, creatorName, creatorId, 
	 * createTime, status
	 */
	public Skills copy(){
		Skills skills = new Skills();
     	skills.id = this.id;
     	skills.name = this.name;
     	skills.creatorName = this.creatorName;
     	skills.creatorId = this.creatorId;
     	skills.createTime = this.createTime;
     	skills.status = this.status;
		return skills;
	}
	
	/**
	 * 比较字段：
	 * id, name, creatorName, creatorId, 
	 * createTime, status
	 */
	@Override
	public boolean test(Skills t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Skills element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
