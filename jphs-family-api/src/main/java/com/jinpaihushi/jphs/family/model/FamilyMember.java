package com.jinpaihushi.jphs.family.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_MEMBER 
 * 继承自父类的字段:
 * id : 	
 * status : 态状（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FamilyMember extends BaseModel implements Predicate<FamilyMember>,
		Updator<FamilyMember> {


    /** 姓名 */
	@Length(max = 50, message = "{familyMember.name.illegal.length}")
	private String name;

    /** 手机号 */
	@Length(max = 50, message = "{familyMember.phone.illegal.length}")
	private String phone;

    /** 身份证 */
	@Length(max = 50, message = "{familyMember.sfz.illegal.length}")
	private String sfz;

    /** 关系 */
	@Length(max = 50, message = "{familyMember.relation.illegal.length}")
	private String relation;

	public FamilyMember(){}

	public FamilyMember(String id){
		this.id = id;
	}

	/**
	 * 获取姓名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置姓名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取手机号
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置手机号
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * 获取身份证
	 */
	public String getSfz() {
    	return sfz;
    }
  	
	/**
	 * 设置身份证
	 */
	public void setSfz(String sfz) {
    	this.sfz = sfz;
    }

	/**
	 * 获取关系
	 */
	public String getRelation() {
    	return relation;
    }
  	
	/**
	 * 设置关系
	 */
	public void setRelation(String relation) {
    	this.relation = relation;
    }

    public String toString() {
		return new StringBuilder().append("FamilyMember{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",phone=").append(phone).
			append(",sfz=").append(sfz).
			append(",relation=").append(relation).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, phone, sfz, 
	 * relation, status, createTime, creatorId, 
	 * creatorName
	 */
	public FamilyMember copy(){
		FamilyMember familyMember = new FamilyMember();
     	familyMember.id = this.id;
     	familyMember.name = this.name;
     	familyMember.phone = this.phone;
     	familyMember.sfz = this.sfz;
     	familyMember.relation = this.relation;
     	familyMember.status = this.status;
     	familyMember.createTime = this.createTime;
     	familyMember.creatorId = this.creatorId;
     	familyMember.creatorName = this.creatorName;
		return familyMember;
	}
	
	/**
	 * 比较字段：
	 * id, name, phone, sfz, 
	 * relation, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(FamilyMember t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.sfz == null || this.sfz.equals(t.sfz))
				&& (this.relation == null || this.relation.equals(t.relation))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyMember element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.sfz != null && !this.sfz.isEmpty()) {
			element.sfz = this.sfz;
		}
		if (this.relation != null && !this.relation.isEmpty()) {
			element.relation = this.relation;
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
