package com.jinpaihushi.jphs.family.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_REGISTER 
 * 继承自父类的字段:
 * id : 	
 * type : 问诊类型（1普通门诊，2专家问诊，3加急问诊）	
 * status : 状态（0正常，-1删除）	
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
public class FamilyRegister extends BaseModel implements Predicate<FamilyRegister>,
		Updator<FamilyRegister> {


    /** 医院 */
	@Length(max = 255, message = "{familyRegister.hospital.illegal.length}")
	private String hospital;

    /** 科室 */
	@Length(max = 50, message = "{familyRegister.department.illegal.length}")
	private String department;

    /** 行程名称 */
	@Length(max = 255, message = "{familyRegister.title.illegal.length}")
	private String title;

    /** 拨打电话 */
	@Length(max = 50, message = "{familyRegister.phone.illegal.length}")
	private String phone;

    /** 预约时间 */
	private Date appointmentTime;

    /** 星期 */
	@Length(max = 50, message = "{familyRegister.week.illegal.length}")
	private String week;

	public FamilyRegister(){}

	public FamilyRegister(String id){
		this.id = id;
	}

	/**
	 * 获取医院
	 */
	public String getHospital() {
    	return hospital;
    }
  	
	/**
	 * 设置医院
	 */
	public void setHospital(String hospital) {
    	this.hospital = hospital;
    }

	/**
	 * 获取科室
	 */
	public String getDepartment() {
    	return department;
    }
  	
	/**
	 * 设置科室
	 */
	public void setDepartment(String department) {
    	this.department = department;
    }

	/**
	 * 获取行程名称
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置行程名称
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取拨打电话
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置拨打电话
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * 获取预约时间
	 */
	public Date getAppointmentTime() {
    	return appointmentTime;
    }
  	
	/**
	 * 设置预约时间
	 */
	public void setAppointmentTime(Date appointmentTime) {
    	this.appointmentTime = appointmentTime;
    }

	/**
	 * 获取星期
	 */
	public String getWeek() {
    	return week;
    }
  	
	/**
	 * 设置星期
	 */
	public void setWeek(String week) {
    	this.week = week;
    }

    public String toString() {
		return new StringBuilder().append("FamilyRegister{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",hospital=").append(hospital).
			append(",department=").append(department).
			append(",title=").append(title).
			append(",phone=").append(phone).
			append(",appointmentTime=").append(appointmentTime).
			append(",week=").append(week).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, hospital, department, 
	 * title, phone, appointmentTime, week, 
	 * status, createTime, creatorId, creatorName
	 */
	public FamilyRegister copy(){
		FamilyRegister familyRegister = new FamilyRegister();
     	familyRegister.id = this.id;
     	familyRegister.type = this.type;
     	familyRegister.hospital = this.hospital;
     	familyRegister.department = this.department;
     	familyRegister.title = this.title;
     	familyRegister.phone = this.phone;
     	familyRegister.appointmentTime = this.appointmentTime;
     	familyRegister.week = this.week;
     	familyRegister.status = this.status;
     	familyRegister.createTime = this.createTime;
     	familyRegister.creatorId = this.creatorId;
     	familyRegister.creatorName = this.creatorName;
		return familyRegister;
	}
	
	/**
	 * 比较字段：
	 * id, type, hospital, department, 
	 * title, phone, appointmentTime, week, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(FamilyRegister t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.hospital == null || this.hospital.equals(t.hospital))
				&& (this.department == null || this.department.equals(t.department))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.appointmentTime == null || this.appointmentTime.equals(t.appointmentTime))
				&& (this.week == null || this.week.equals(t.week))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyRegister element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.hospital != null && !this.hospital.isEmpty()) {
			element.hospital = this.hospital;
		}
		if (this.department != null && !this.department.isEmpty()) {
			element.department = this.department;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.appointmentTime != null) {
			element.appointmentTime = this.appointmentTime;
		}
		if (this.week != null && !this.week.isEmpty()) {
			element.week = this.week;
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
