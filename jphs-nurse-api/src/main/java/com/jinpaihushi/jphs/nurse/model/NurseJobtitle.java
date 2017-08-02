package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_JOBTITLE 
 * 继承自父类的字段:
 * id : 	
 * createTime : 创建时间	
 * status : 审核标识	
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:01
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseJobtitle extends BaseModel implements Predicate<NurseJobtitle>,
		Updator<NurseJobtitle> {


    /** 资质ID */
	@NotEmpty(message = "{nurseJobtitle.jobtitleId.empty}")
	@Length(max = 50, message = "{nurseJobtitle.jobtitleId.illegal.length}")
	private String jobtitleId;

    /** 护士ID */
	@NotEmpty(message = "{nurseJobtitle.nurseId.empty}")
	@Length(max = 50, message = "{nurseJobtitle.nurseId.illegal.length}")
	private String nurseId;

    /** 备注 */
	@Length(max = 255, message = "{nurseJobtitle.remark.illegal.length}")
	private String remark;

	public NurseJobtitle(){}

	public NurseJobtitle(String id){
		this.id = id;
	}

	/**
	 * 获取资质ID
	 */
	public String getJobtitleId() {
    	return jobtitleId;
    }
  	
	/**
	 * 设置资质ID
	 */
	public void setJobtitleId(String jobtitleId) {
    	this.jobtitleId = jobtitleId;
    }

	/**
	 * 获取护士ID
	 */
	public String getNurseId() {
    	return nurseId;
    }
  	
	/**
	 * 设置护士ID
	 */
	public void setNurseId(String nurseId) {
    	this.nurseId = nurseId;
    }

	/**
	 * 获取备注
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("NurseJobtitle{").
			append("id=").append(id).
			append(",jobtitleId=").append(jobtitleId).
			append(",nurseId=").append(nurseId).
			append(",remark=").append(remark).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, jobtitleId, nurseId, remark, 
	 * createTime, status
	 */
	public NurseJobtitle copy(){
		NurseJobtitle nurseJobtitle = new NurseJobtitle();
     	nurseJobtitle.id = this.id;
     	nurseJobtitle.jobtitleId = this.jobtitleId;
     	nurseJobtitle.nurseId = this.nurseId;
     	nurseJobtitle.remark = this.remark;
     	nurseJobtitle.createTime = this.createTime;
     	nurseJobtitle.status = this.status;
		return nurseJobtitle;
	}
	
	/**
	 * 比较字段：
	 * id, jobtitleId, nurseId, remark, 
	 * createTime, status
	 */
	@Override
	public boolean test(NurseJobtitle t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.jobtitleId == null || this.jobtitleId.equals(t.jobtitleId))
				&& (this.nurseId == null || this.nurseId.equals(t.nurseId))
				&& (this.remark == null || this.remark.equals(t.remark))
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
		if (this.jobtitleId != null && !this.jobtitleId.isEmpty()) {
			element.jobtitleId = this.jobtitleId;
		}
		if (this.nurseId != null && !this.nurseId.isEmpty()) {
			element.nurseId = this.nurseId;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
