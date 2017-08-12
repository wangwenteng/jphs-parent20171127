package com.jinpaihushi.jphs.nurse.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE 继承自父类的字段: id : uuid type : 护士类型：1、护士2、康复师3、母婴护理师 creatorId : 创建人ID，即护士
 * creatorName : 创建人姓名 createTime : 创建时间 status : 状态
 * 
 * @author wangwt
 * @date 2017-06-28 10:05:23
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Nurse extends BaseModel implements Predicate<Nurse>, Updator<Nurse> {

	/** 身份证 */
	@Length(max = 50, message = "{nurse.sfz.illegal.length}")
	private String sfz;

	/** 参加工作时间 */
	private Date workYears;

	/** 个人简介 */
	@Length(max = 65535, message = "{nurse.brief.illegal.length}")
	private String brief;

	/** 详情 */
	@Length(max = 65535, message = "{nurse.details.illegal.length}")
	private String details;

	/** 在职医院 */
	@Length(max = 50, message = "{nurse.hospital.illegal.length}")
	private String hospital;

	/** 所属科室 */
	@Length(max = 50, message = "{nurse.departmentId.illegal.length}")
	private String departmentId;

	/** 封号标识 */
	private Integer active;

	/**
	 * 护士相关图片字段开始
	 */
	private String head_portrait;//头像
	private String aptitude_positive;//资格证正面
	private String aptitude_negative;//资格证反面
	private String id_positive;//身份证反面
	private String id_negative;//身份证反面
	/**
	 * 护士相关图片字段结束
	 */
	/**
	 * 服务区域
	 */
	private String areas;
	private int age;
	/**
	 * 护士基本信息
	 */
	private User user;
	private List<Location> location;
	private String recommendName;
	private String workYear;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Nurse() {
	}

	public Nurse(String id) {
		this.id = id;
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
	 * 获取参加工作时间
	 */
	public Date getWorkYears() {
		return workYears;
	}

	/**
	 * 设置参加工作时间
	 */
	public void setWorkYears(Date workYears) {
		this.workYears = workYears;
	}

	/**
	 * 获取个人简介
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * 设置个人简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * 获取详情
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * 设置详情
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * 获取在职医院
	 */
	public String getHospital() {
		return hospital;
	}

	/**
	 * 设置在职医院
	 */
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	/**
	 * 获取所属科室
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 设置所属科室
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 获取封号标识
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * 设置封号标识
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

		
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHead_portrait() {
		return head_portrait;
	}

	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

	public String getAptitude_positive() {
		return aptitude_positive;
	}

	public void setAptitude_positive(String aptitude_positive) {
		this.aptitude_positive = aptitude_positive;
	}

	public String getAptitude_negative() {
		return aptitude_negative;
	}

	public void setAptitude_negative(String aptitude_negative) {
		this.aptitude_negative = aptitude_negative;
	}

	public String getId_positive() {
		return id_positive;
	}

	public void setId_positive(String id_positive) {
		this.id_positive = id_positive;
	}

	public String getId_negative() {
		return id_negative;
	}

	public void setId_negative(String id_negative) {
		this.id_negative = id_negative;
	}
	
	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}
	

	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}
	
	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}
	
	public String getWorkYear() {
		return workYear;
	}

	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}

	public String toString() {
		return new StringBuilder().append("Nurse{").append("id=").append(id).append(",type=").append(type)
				.append(",sfz=").append(sfz).append(",workYears=").append(workYears).append(",brief=").append(brief)
				.append(",details=").append(details).append(",hospital=").append(hospital).append(",departmentId=")
				.append(departmentId).append(",creatorId=").append(creatorId).append(",creatorName=")
				.append(creatorName).append(",createTime=").append(createTime).append(",active=").append(active)
				.append(",status=").append(status).append('}').toString();
	}

	/**
	 * 复制字段： id, type, sfz, workYears, brief, details, hospital, departmentId,
	 * creatorId, creatorName, createTime, active, address, status
	 */
	public Nurse copy() {
		Nurse nurse = new Nurse();
		nurse.id = this.id;
		nurse.type = this.type;
		nurse.sfz = this.sfz;
		nurse.workYears = this.workYears;
		nurse.brief = this.brief;
		nurse.details = this.details;
		nurse.hospital = this.hospital;
		nurse.departmentId = this.departmentId;
		nurse.creatorId = this.creatorId;
		nurse.creatorName = this.creatorName;
		nurse.createTime = this.createTime;
		nurse.active = this.active;
		nurse.status = this.status;
		return nurse;
	}

	/**
	 * 比较字段： id, type, sfz, workYears, brief, details, hospital, departmentId,
	 * creatorId, creatorName, createTime, active, address, status
	 */
	@Override
	public boolean test(Nurse t) {
		if (t == null)
			return false;
		return (this.id == null || this.id.equals(t.id)) && (this.type == null || this.type.equals(t.type))
				&& (this.sfz == null || this.sfz.equals(t.sfz))
				&& (this.workYears == null || this.workYears.equals(t.workYears))
				&& (this.brief == null || this.brief.equals(t.brief))
				&& (this.details == null || this.details.equals(t.details))
				&& (this.hospital == null || this.hospital.equals(t.hospital))
				&& (this.departmentId == null || this.departmentId.equals(t.departmentId))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.active == null || this.active.equals(t.active))
				&& (this.status == null || this.status.equals(t.status));
	}

	@Override
	public void update(Nurse element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.sfz != null && !this.sfz.isEmpty()) {
			element.sfz = this.sfz;
		}
		if (this.workYears != null) {
			element.workYears = this.workYears;
		}
		if (this.brief != null && !this.brief.isEmpty()) {
			element.brief = this.brief;
		}
		if (this.details != null && !this.details.isEmpty()) {
			element.details = this.details;
		}
		if (this.hospital != null && !this.hospital.isEmpty()) {
			element.hospital = this.hospital;
		}
		if (this.departmentId != null && !this.departmentId.isEmpty()) {
			element.departmentId = this.departmentId;
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
		if (this.active != null) {
			element.active = this.active;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
