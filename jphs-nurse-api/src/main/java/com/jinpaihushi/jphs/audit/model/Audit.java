package com.jinpaihushi.jphs.audit.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * AUDIT 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 申请人ID	
 * creatorName : 申请人姓名	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-07-11 10:38:09
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Audit extends BaseModel implements Predicate<Audit>,
		Updator<Audit> {


    /** 审核结果（0、未通过1、通过） */
	private Integer audit;

    /** 审核时间 */
	private Date auditTime;

    /** 审核人ID */
	@NotEmpty(message = "{audit.auditUserId.empty}")
	@Length(max = 50, message = "{audit.auditUserId.illegal.length}")
	private String auditUserId;

    /**  */
	@NotEmpty(message = "{audit.auditUserName.empty}")
	@Length(max = 50, message = "{audit.auditUserName.illegal.length}")
	private String auditUserName;

    /** 审核备注 */
	@Length(max = 65535, message = "{audit.remark.illegal.length}")
	private String remark;

	public Audit(){}

	public Audit(String id){
		this.id = id;
	}

	/**
	 * 获取审核结果（0、未通过1、通过）
	 */
	public Integer getAudit() {
    	return audit;
    }
  	
	/**
	 * 设置审核结果（0、未通过1、通过）
	 */
	public void setAudit(Integer audit) {
    	this.audit = audit;
    }

	/**
	 * 获取审核时间
	 */
	public Date getAuditTime() {
    	return auditTime;
    }
  	
	/**
	 * 设置审核时间
	 */
	public void setAuditTime(Date auditTime) {
    	this.auditTime = auditTime;
    }

	/**
	 * 获取审核人ID
	 */
	public String getAuditUserId() {
    	return auditUserId;
    }
  	
	/**
	 * 设置审核人ID
	 */
	public void setAuditUserId(String auditUserId) {
    	this.auditUserId = auditUserId;
    }

	/**
	 * 获取
	 */
	public String getAuditUserName() {
    	return auditUserName;
    }
  	
	/**
	 * 设置
	 */
	public void setAuditUserName(String auditUserName) {
    	this.auditUserName = auditUserName;
    }

	/**
	 * 获取审核备注
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置审核备注
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("Audit{").
			append("id=").append(id).
			append(",audit=").append(audit).
			append(",auditTime=").append(auditTime).
			append(",auditUserId=").append(auditUserId).
			append(",auditUserName=").append(auditUserName).
			append(",remark=").append(remark).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, audit, auditTime, auditUserId, 
	 * auditUserName, remark, creatorId, creatorName, 
	 * createTime, status
	 */
	public Audit copy(){
		Audit audit = new Audit();
     	audit.id = this.id;
     	audit.audit = this.audit;
     	audit.auditTime = this.auditTime;
     	audit.auditUserId = this.auditUserId;
     	audit.auditUserName = this.auditUserName;
     	audit.remark = this.remark;
     	audit.creatorId = this.creatorId;
     	audit.creatorName = this.creatorName;
     	audit.createTime = this.createTime;
     	audit.status = this.status;
		return audit;
	}
	
	/**
	 * 比较字段：
	 * id, audit, auditTime, auditUserId, 
	 * auditUserName, remark, creatorId, creatorName, 
	 * createTime, status
	 */
	@Override
	public boolean test(Audit t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.audit == null || this.audit.equals(t.audit))
				&& (this.auditTime == null || this.auditTime.equals(t.auditTime))
				&& (this.auditUserId == null || this.auditUserId.equals(t.auditUserId))
				&& (this.auditUserName == null || this.auditUserName.equals(t.auditUserName))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Audit element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.audit != null) {
			element.audit = this.audit;
		}
		if (this.auditTime != null) {
			element.auditTime = this.auditTime;
		}
		if (this.auditUserId != null && !this.auditUserId.isEmpty()) {
			element.auditUserId = this.auditUserId;
		}
		if (this.auditUserName != null && !this.auditUserName.isEmpty()) {
			element.auditUserName = this.auditUserName;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
