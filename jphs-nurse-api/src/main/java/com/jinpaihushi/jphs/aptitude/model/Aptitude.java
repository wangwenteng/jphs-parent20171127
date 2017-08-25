package com.jinpaihushi.jphs.aptitude.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * APTITUDE 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 创建人-生成记录人的ID	
 * creatorName : 创建人-生成记录人的name	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Aptitude extends BaseModel implements Predicate<Aptitude>,
		Updator<Aptitude> {


    /** 资质名称or技能名称 */
	@Length(max = 50, message = "{aptitude.name.illegal.length}")
	private String name;

    /** 资质证明 */
	@Length(max = 50, message = "{aptitude.prove.illegal.length}")
	private String prove;

    /** 备注 */
	@Length(max = 65535, message = "{aptitude.remark.illegal.length}")
	private String remark;

	public Aptitude(){}

	public Aptitude(String id){
		this.id = id;
	}

	/**
	 * 获取资质名称or技能名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置资质名称or技能名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取资质证明
	 */
	public String getProve() {
    	return prove;
    }
  	
	/**
	 * 设置资质证明
	 */
	public void setProve(String prove) {
    	this.prove = prove;
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
		return new StringBuilder().append("Aptitude{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",prove=").append(prove).
			append(",remark=").append(remark).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, prove, remark, 
	 * creatorId, creatorName, createTime, status
	 */
	public Aptitude copy(){
		Aptitude aptitude = new Aptitude();
     	aptitude.id = this.id;
     	aptitude.name = this.name;
     	aptitude.prove = this.prove;
     	aptitude.remark = this.remark;
     	aptitude.creatorId = this.creatorId;
     	aptitude.creatorName = this.creatorName;
     	aptitude.createTime = this.createTime;
     	aptitude.status = this.status;
		return aptitude;
	}
	
	/**
	 * 比较字段：
	 * id, name, prove, remark, 
	 * creatorId, creatorName, createTime, status
	 */
	@Override
	public boolean test(Aptitude t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.prove == null || this.prove.equals(t.prove))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Aptitude element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.prove != null && !this.prove.isEmpty()) {
			element.prove = this.prove;
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
