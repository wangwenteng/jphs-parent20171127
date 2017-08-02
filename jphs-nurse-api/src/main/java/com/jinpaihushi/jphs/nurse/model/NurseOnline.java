package com.jinpaihushi.jphs.nurse.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_ONLINE 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 创建人ID	
 * creatorName : 创建人姓名	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseOnline extends BaseModel implements Predicate<NurseOnline>,
		Updator<NurseOnline> {


    /** 护士ID */
	@Length(max = 50, message = "{nurseOnline.nurseId.illegal.length}")
	private String nurseId;

    /** 在线标识 */
	private Integer online;

    /** 上线时间 */
	private Date onlineTime;

    /** 下线时间 */
	private Date offlineTime;

    /** 在线时长 */
	@Length(max = 50, message = "{nurseOnline.timeLong.illegal.length}")
	private String timeLong;

	public NurseOnline(){}

	public NurseOnline(String id){
		this.id = id;
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
	 * 获取在线标识
	 */
	public Integer getOnline() {
    	return online;
    }
  	
	/**
	 * 设置在线标识
	 */
	public void setOnline(Integer online) {
    	this.online = online;
    }

	/**
	 * 获取上线时间
	 */
	public Date getOnlineTime() {
    	return onlineTime;
    }
  	
	/**
	 * 设置上线时间
	 */
	public void setOnlineTime(Date onlineTime) {
    	this.onlineTime = onlineTime;
    }

	/**
	 * 获取下线时间
	 */
	public Date getOfflineTime() {
    	return offlineTime;
    }
  	
	/**
	 * 设置下线时间
	 */
	public void setOfflineTime(Date offlineTime) {
    	this.offlineTime = offlineTime;
    }

	/**
	 * 获取在线时长
	 */
	public String getTimeLong() {
    	return timeLong;
    }
  	
	/**
	 * 设置在线时长
	 */
	public void setTimeLong(String timeLong) {
    	this.timeLong = timeLong;
    }

    public String toString() {
		return new StringBuilder().append("NurseOnline{").
			append("id=").append(id).
			append(",nurseId=").append(nurseId).
			append(",online=").append(online).
			append(",onlineTime=").append(onlineTime).
			append(",offlineTime=").append(offlineTime).
			append(",timeLong=").append(timeLong).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, nurseId, online, onlineTime, 
	 * offlineTime, timeLong, creatorId, creatorName, 
	 * createTime, status
	 */
	public NurseOnline copy(){
		NurseOnline nurseOnline = new NurseOnline();
     	nurseOnline.id = this.id;
     	nurseOnline.nurseId = this.nurseId;
     	nurseOnline.online = this.online;
     	nurseOnline.onlineTime = this.onlineTime;
     	nurseOnline.offlineTime = this.offlineTime;
     	nurseOnline.timeLong = this.timeLong;
     	nurseOnline.creatorId = this.creatorId;
     	nurseOnline.creatorName = this.creatorName;
     	nurseOnline.createTime = this.createTime;
     	nurseOnline.status = this.status;
		return nurseOnline;
	}
	
	/**
	 * 比较字段：
	 * id, nurseId, online, onlineTime, 
	 * offlineTime, timeLong, creatorId, creatorName, 
	 * createTime, status
	 */
	@Override
	public boolean test(NurseOnline t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.nurseId == null || this.nurseId.equals(t.nurseId))
				&& (this.online == null || this.online.equals(t.online))
				&& (this.onlineTime == null || this.onlineTime.equals(t.onlineTime))
				&& (this.offlineTime == null || this.offlineTime.equals(t.offlineTime))
				&& (this.timeLong == null || this.timeLong.equals(t.timeLong))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(NurseOnline element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.nurseId != null && !this.nurseId.isEmpty()) {
			element.nurseId = this.nurseId;
		}
		if (this.online != null) {
			element.online = this.online;
		}
		if (this.onlineTime != null) {
			element.onlineTime = this.onlineTime;
		}
		if (this.offlineTime != null) {
			element.offlineTime = this.offlineTime;
		}
		if (this.timeLong != null && !this.timeLong.isEmpty()) {
			element.timeLong = this.timeLong;
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
