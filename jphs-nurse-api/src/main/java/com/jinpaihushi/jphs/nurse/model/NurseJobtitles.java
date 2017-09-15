package com.jinpaihushi.jphs.nurse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

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
public class NurseJobtitles implements Serializable {

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.name.illegal.length}")
	private String nurseName;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.sfz.illegal.length}")
	private String sfz;

    /**  */
	private Integer sex;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.hospital.illegal.length}")
	private String hospital;
	
	private String studyInstitution;

    /** 机构ID */
	@Length(max = 50, message = "{nurseJobtitle.nurseInstitutionsId.illegal.length}")
	private String nurseInstitutionsId;
	//			   nurseInstitutionsId

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.departmentId.illegal.length}")
	private String departmentId;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.jobtitleId.illegal.length}")
	private String jobtitleId;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.education.illegal.length}")
	private String education;

    /**  */
	@Length(max = 50, message = "{nurseJobtitle.address.illegal.length}")
	private String address;
	
	private List<NurseImages> nurseImages;
	
	private String sculpture;
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
	
	public List<NurseImages> getNurseImages() {
		return nurseImages;
	}
	public void setNurseImages(List<NurseImages> nurseImages) {
		this.nurseImages = nurseImages;
	}
	
	public String getSculpture() {
		return sculpture;
	}
	public void setSculpture(String sculpture) {
		this.sculpture = sculpture;
	}
	
	public String getStudyInstitution() {
		return studyInstitution;
	}
	public void setStudyInstitution(String studyInstitution) {
		this.studyInstitution = studyInstitution;
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
	public String getNurseName() {
    	return nurseName;
    }
  	
	/**
	 * 设置
	 */
	public void setNurseName(String nurseName) {
    	this.nurseName = nurseName;
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
	 * 获取机构ID
	 */
	public String getNurseInstitutionsId() {
    	return nurseInstitutionsId;
    }
  	
	/**
	 * 设置机构ID
	 */
	public void setNurseInstitutionsId(String nurseInstitutionsId) {
    	this.nurseInstitutionsId = nurseInstitutionsId;
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
}