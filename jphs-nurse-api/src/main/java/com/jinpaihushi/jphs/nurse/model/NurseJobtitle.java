package com.jinpaihushi.jphs.nurse.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_JOBTITLE 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 	
 * creatorName : 	
 * createTime : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-08-16 14:56:07
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseJobtitle extends BaseModel implements Predicate<NurseJobtitle>,
		Updator<NurseJobtitle> {


    /**  */
	@Length(max = 50, message = "{nurseJobtitle.name.illegal.length}")
	private String name;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.sfz.illegal.length}")
	private String sfz;

    /**  */
	private Integer sex;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.hospital.illegal.length}")
	private String hospital;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.departmentId.illegal.length}")
	private String departmentId;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.jobtitleId.illegal.length}")
	private String jobtitleId;

    /**  */
	private Date workYears;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.education.illegal.length}")
	private String education;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.address.illegal.length}")
	private String address;
	
	/**	身份证-正面	*/
	private String sfzz;
	/**	身份证-反面	*/
	private String sfzf;
	/**	执业证	*/
	private String charteredProve;
	/**	资格证	*/
	private String seniorityProve;
	/**	医院聘书	*/
	private String hospitalContract;
	/**	工牌	*/
	private String workCard;
	/**	康复师资格证正面	*/
	private String therapistZ;
	/**	康复师资格证反面	*/
	private String therapistF;
	/**	母婴师	*/
	private String fransnanaCard;
	
	public NurseJobtitle(){}

	public NurseJobtitle(String id){
		this.id = id;
	}
	
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	public String getSfzf() {
		return sfzf;
	}
	public void setSfzf(String sfzf) {
		this.sfzf = sfzf;
	}
	public String getCharteredProve() {
		return charteredProve;
	}
	public void setCharteredProve(String charteredProve) {
		this.charteredProve = charteredProve;
	}
	public String getSeniorityProve() {
		return seniorityProve;
	}
	public void setSeniorityProve(String seniorityProve) {
		this.seniorityProve = seniorityProve;
	}
	public String getHospitalContract() {
		return hospitalContract;
	}
	public void setHospitalContract(String hospitalContract) {
		this.hospitalContract = hospitalContract;
	}
	public String getWorkCard() {
		return workCard;
	}
	public void setWorkCard(String workCard) {
		this.workCard = workCard;
	}
	public String getTherapistZ() {
		return therapistZ;
	}
	public void setTherapistZ(String therapistZ) {
		this.therapistZ = therapistZ;
	}
	public String getTherapistF() {
		return therapistF;
	}
	public void setTherapistF(String therapistF) {
		this.therapistF = therapistF;
	}
	public String getFransnanaCard() {
		return fransnanaCard;
	}
	public void setFransnanaCard(String fransnanaCard) {
		this.fransnanaCard = fransnanaCard;
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

	/**
	 * 获取
	 */
	public String getSfz() {
    	return sfz;
    }
  	
	/**
	 * 设置
	 */
	public void setSfz(String sfz) {
    	this.sfz = sfz;
    }

	/**
	 * 获取
	 */
	public Integer getSex() {
    	return sex;
    }
  	
	/**
	 * 设置
	 */
	public void setSex(Integer sex) {
    	this.sex = sex;
    }

	/**
	 * 获取
	 */
	public String getHospital() {
    	return hospital;
    }
  	
	/**
	 * 设置
	 */
	public void setHospital(String hospital) {
    	this.hospital = hospital;
    }

	/**
	 * 获取
	 */
	public String getDepartmentId() {
    	return departmentId;
    }
  	
	/**
	 * 设置
	 */
	public void setDepartmentId(String departmentId) {
    	this.departmentId = departmentId;
    }

	/**
	 * 获取
	 */
	public String getJobtitleId() {
    	return jobtitleId;
    }
  	
	/**
	 * 设置
	 */
	public void setJobtitleId(String jobtitleId) {
    	this.jobtitleId = jobtitleId;
    }

	/**
	 * 获取
	 */
	public Date getWorkYears() {
    	return workYears;
    }
  	
	/**
	 * 设置
	 */
	public void setWorkYears(Date workYears) {
    	this.workYears = workYears;
    }

	/**
	 * 获取
	 */
	public String getEducation() {
    	return education;
    }
  	
	/**
	 * 设置
	 */
	public void setEducation(String education) {
    	this.education = education;
    }

	/**
	 * 获取
	 */
	public String getAddress() {
    	return address;
    }
  	
	/**
	 * 设置
	 */
	public void setAddress(String address) {
    	this.address = address;
    }

    public String toString() {
		return new StringBuilder().append("NurseJobtitle{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",sfz=").append(sfz).
			append(",sex=").append(sex).
			append(",hospital=").append(hospital).
			append(",departmentId=").append(departmentId).
			append(",jobtitleId=").append(jobtitleId).
			append(",workYears=").append(workYears).
			append(",education=").append(education).
			append(",address=").append(address).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, sfz, sex, 
	 * hospital, departmentId, jobtitleId, workYears, 
	 * education, address, creatorId, creatorName, 
	 * createTime, status
	 */
	public NurseJobtitle copy(){
		NurseJobtitle nurseJobtitle = new NurseJobtitle();
     	nurseJobtitle.id = this.id;
     	nurseJobtitle.name = this.name;
     	nurseJobtitle.sfz = this.sfz;
     	nurseJobtitle.sex = this.sex;
     	nurseJobtitle.hospital = this.hospital;
     	nurseJobtitle.departmentId = this.departmentId;
     	nurseJobtitle.jobtitleId = this.jobtitleId;
     	nurseJobtitle.workYears = this.workYears;
     	nurseJobtitle.education = this.education;
     	nurseJobtitle.address = this.address;
     	nurseJobtitle.creatorId = this.creatorId;
     	nurseJobtitle.creatorName = this.creatorName;
     	nurseJobtitle.createTime = this.createTime;
     	nurseJobtitle.status = this.status;
		return nurseJobtitle;
	}
	
	/**
	 * 比较字段：
	 * id, name, sfz, sex, 
	 * hospital, departmentId, jobtitleId, workYears, 
	 * education, address, creatorId, creatorName, 
	 * createTime, status
	 */
	@Override
	public boolean test(NurseJobtitle t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sfz == null || this.sfz.equals(t.sfz))
				&& (this.sex == null || this.sex.equals(t.sex))
				&& (this.hospital == null || this.hospital.equals(t.hospital))
				&& (this.departmentId == null || this.departmentId.equals(t.departmentId))
				&& (this.jobtitleId == null || this.jobtitleId.equals(t.jobtitleId))
				&& (this.workYears == null || this.workYears.equals(t.workYears))
				&& (this.education == null || this.education.equals(t.education))
				&& (this.address == null || this.address.equals(t.address))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(NurseJobtitle element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.sfz != null && !this.sfz.isEmpty()) {
			element.sfz = this.sfz;
		}
		if (this.sex != null) {
			element.sex = this.sex;
		}
		if (this.hospital != null && !this.hospital.isEmpty()) {
			element.hospital = this.hospital;
		}
		if (this.departmentId != null && !this.departmentId.isEmpty()) {
			element.departmentId = this.departmentId;
		}
		if (this.jobtitleId != null && !this.jobtitleId.isEmpty()) {
			element.jobtitleId = this.jobtitleId;
		}
		if (this.workYears != null) {
			element.workYears = this.workYears;
		}
		if (this.education != null && !this.education.isEmpty()) {
			element.education = this.education;
		}
		if (this.address != null && !this.address.isEmpty()) {
			element.address = this.address;
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
