package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_SKILLS 
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
public class NurseSkills extends BaseModel implements Predicate<NurseSkills>,
		Updator<NurseSkills> {

    /**  */
	@Length(max = 50, message = "{nurseSkills.skillsId.illegal.length}")
	private String skillsId;
	
	private Integer nurseType;

	public NurseSkills(){}

	public NurseSkills(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getSkillsId() {
    	return skillsId;
    }
  	
	/**
	 * 设置
	 */
	public void setSkillsId(String skillsId) {
    	this.skillsId = skillsId;
    }
	
	public Integer getNurseType(){
		return nurseType;
	}
	
	public void setNurseType(Integer nurseType){
		this.nurseType = nurseType;
	}

    public String toString() {
		return new StringBuilder().append("NurseSkills{").
			append("id=").append(id).
			append(",skillsId=").append(skillsId).
			append(",creatorName=").append(creatorName).
			append(",creatorId=").append(creatorId).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, skillsId, creatorName, creatorId, 
	 * createTime, status
	 */
	public NurseSkills copy(){
		NurseSkills nurseSkills = new NurseSkills();
     	nurseSkills.id = this.id;
     	nurseSkills.skillsId = this.skillsId;
     	nurseSkills.creatorName = this.creatorName;
     	nurseSkills.creatorId = this.creatorId;
     	nurseSkills.createTime = this.createTime;
     	nurseSkills.status = this.status;
		return nurseSkills;
	}
	
	/**
	 * 比较字段：
	 * id, skillsId, creatorName, creatorId, 
	 * createTime, status
	 */
	@Override
	public boolean test(NurseSkills t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.skillsId == null || this.skillsId.equals(t.skillsId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(NurseSkills element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.skillsId != null && !this.skillsId.isEmpty()) {
			element.skillsId = this.skillsId;
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
