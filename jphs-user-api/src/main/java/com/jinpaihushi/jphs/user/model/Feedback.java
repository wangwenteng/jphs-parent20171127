package com.jinpaihushi.jphs.user.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FEEDBACK 
 * 继承自父类的字段:
 * id : 问题反馈id	
 * creatorName : 创建人	
 * creatorId : 创建人ID	
 * createTime : 创建时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author zhangzd
 * @date 2017-06-27 11:22:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Feedback extends BaseModel implements Predicate<Feedback>,
		Updator<Feedback> {


    /** 内容 */
	@Length(max = 65535, message = "{feedback.content.illegal.length}")
	private String content;

	public Feedback(){}

	public Feedback(String id){
		this.id = id;
	}

	/**
	 * 获取内容
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置内容
	 */
	public void setContent(String content) {
    	this.content = content;
    }

    public String toString() {
		return new StringBuilder().append("Feedback{").
			append("id=").append(id).
			append(",content=").append(content).
			append(",creatorName=").append(creatorName).
			append(",creatorId=").append(creatorId).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, content, creatorName, creatorId, 
	 * createTime, status
	 */
	public Feedback copy(){
		Feedback feedback = new Feedback();
     	feedback.id = this.id;
     	feedback.content = this.content;
     	feedback.creatorName = this.creatorName;
     	feedback.creatorId = this.creatorId;
     	feedback.createTime = this.createTime;
     	feedback.status = this.status;
		return feedback;
	}
	
	/**
	 * 比较字段：
	 * id, content, creatorName, creatorId, 
	 * createTime, status
	 */
	@Override
	public boolean test(Feedback t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Feedback element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
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
