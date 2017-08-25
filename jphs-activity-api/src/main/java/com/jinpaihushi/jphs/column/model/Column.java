package com.jinpaihushi.jphs.column.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COLUMN 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-08-07 14:05:57
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Column extends BaseModel implements Predicate<Column>,
		Updator<Column> {


    /** 目栏名称 */
	@Length(max = 255, message = "{column.name.illegal.length}")
	private String name;

    /** 注备 */
	@Length(max = 65535, message = "{column.remark.illegal.length}")
	private String remark;

	public Column(){}

	public Column(String id){
		this.id = id;
	}

	/**
	 * 获取目栏名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置目栏名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取注备
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置注备
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("Column{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, remark, status, 
	 * createTime, creatorId, creatorName
	 */
	public Column copy(){
		Column column = new Column();
     	column.id = this.id;
     	column.name = this.name;
     	column.remark = this.remark;
     	column.status = this.status;
     	column.createTime = this.createTime;
     	column.creatorId = this.creatorId;
     	column.creatorName = this.creatorName;
		return column;
	}
	
	/**
	 * 比较字段：
	 * id, name, remark, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(Column t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Column element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
