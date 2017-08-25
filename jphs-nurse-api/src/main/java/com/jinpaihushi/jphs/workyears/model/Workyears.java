package com.jinpaihushi.jphs.workyears.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * WORKYEARS 
 * 继承自父类的字段:
 * id : 	
 * status : 状态	
 * createTime : 创建时间	
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:31
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Workyears extends BaseModel implements Predicate<Workyears>,
		Updator<Workyears> {


    /** 工作年限 */
	@Length(max = 50, message = "{workyears.content.illegal.length}")
	private String content;

	public Workyears(){}

	public Workyears(String id){
		this.id = id;
	}

	/**
	 * 获取工作年限
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置工作年限
	 */
	public void setContent(String content) {
    	this.content = content;
    }

    public String toString() {
		return new StringBuilder().append("Workyears{").
			append("id=").append(id).
			append(",content=").append(content).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, content, status, createTime
	 */
	public Workyears copy(){
		Workyears workyears = new Workyears();
     	workyears.id = this.id;
     	workyears.content = this.content;
     	workyears.status = this.status;
     	workyears.createTime = this.createTime;
		return workyears;
	}
	
	/**
	 * 比较字段：
	 * id, content, status, createTime
	 */
	@Override
	public boolean test(Workyears t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(Workyears element) {
		if (element == null)
			return;
		if (this.id != null) {
			element.id = this.id;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
	}
}
