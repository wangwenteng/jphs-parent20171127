package com.jinpaihushi.jphs.department.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * DEPARTMENT 
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
public class Department extends BaseModel implements Predicate<Department>,
		Updator<Department> {


    /** 科室名 */
	@Length(max = 50, message = "{department.name.illegal.length}")
	private String name;

    /** 排序 */
	private Integer sort;

	public Department(){}

	public Department(String id){
		this.id = id;
	}

	/**
	 * 获取科室名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置科室名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取排序
	 */
	public Integer getSort() {
    	return sort;
    }
  	
	/**
	 * 设置排序
	 */
	public void setSort(Integer sort) {
    	this.sort = sort;
    }

    public String toString() {
		return new StringBuilder().append("Department{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",sort=").append(sort).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, sort, creatorId, 
	 * creatorName, createTime, status
	 */
	public Department copy(){
		Department department = new Department();
     	department.id = this.id;
     	department.name = this.name;
     	department.sort = this.sort;
     	department.creatorId = this.creatorId;
     	department.creatorName = this.creatorName;
     	department.createTime = this.createTime;
     	department.status = this.status;
		return department;
	}
	
	/**
	 * 比较字段：
	 * id, name, sort, creatorId, 
	 * creatorName, createTime, status
	 */
	@Override
	public boolean test(Department t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Department element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.sort != null) {
			element.sort = this.sort;
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
