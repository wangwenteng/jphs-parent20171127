package com.jinpaihushi.jphs.jobtitle.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JOBTITLE_TYPE 职称类型
 * 继承自父类的字段:
 * id : 	
 * creatorId : 	
 * creatorName : 	
 * createTime : 	
 * status : 	
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:01
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JobtitleType extends BaseModel implements Predicate<JobtitleType>,
		Updator<JobtitleType> {


    /** 职称类型名称 */
	@Length(max = 50, message = "{jobtitleType.name.illegal.length}")
	private String name;
	private List<Jobtitle> jobtitle;
	
	public List<Jobtitle> getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(List<Jobtitle> jobtitle) {
		this.jobtitle = jobtitle;
	}

	public JobtitleType(){}

	public JobtitleType(String id){
		this.id = id;
	}

	/**
	 * 获取职称类型名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置职称类型名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

    public String toString() {
		return new StringBuilder().append("JobtitleType{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, creatorId, creatorName, 
	 * createTime, status
	 */
	public JobtitleType copy(){
		JobtitleType jobtitleType = new JobtitleType();
     	jobtitleType.id = this.id;
     	jobtitleType.name = this.name;
     	jobtitleType.creatorId = this.creatorId;
     	jobtitleType.creatorName = this.creatorName;
     	jobtitleType.createTime = this.createTime;
     	jobtitleType.status = this.status;
		return jobtitleType;
	}
	
	/**
	 * 比较字段：
	 * id, name, creatorId, creatorName, 
	 * createTime, status
	 */
	@Override
	public boolean test(JobtitleType t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(JobtitleType element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
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
