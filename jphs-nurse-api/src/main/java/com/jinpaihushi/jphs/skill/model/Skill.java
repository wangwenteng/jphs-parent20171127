package com.jinpaihushi.jphs.skill.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SKILL 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 创建人姓名	
 * creatorName : 创建人ID	
 * createTime : 创建时间	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-06-28 09:43:42
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Skill extends BaseModel implements Predicate<Skill>,
		Updator<Skill> {


    /** 技能名称 */
	@Length(max = 50, message = "{skill.name.illegal.length}")
	private String name;

    /** 平台此商品最低价格 */
	private Double amount;

	public Skill(){}

	public Skill(String id){
		this.id = id;
	}

	/**
	 * 获取技能名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置技能名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取平台此商品最低价格
	 */
	public Double getAmount() {
    	return amount;
    }
  	
	/**
	 * 设置平台此商品最低价格
	 */
	public void setAmount(Double amount) {
    	this.amount = amount;
    }

    public String toString() {
		return new StringBuilder().append("Skill{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",amount=").append(amount).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, amount, creatorId, 
	 * creatorName, createTime, status
	 */
	public Skill copy(){
		Skill skill = new Skill();
     	skill.id = this.id;
     	skill.name = this.name;
     	skill.amount = this.amount;
     	skill.creatorId = this.creatorId;
     	skill.creatorName = this.creatorName;
     	skill.createTime = this.createTime;
     	skill.status = this.status;
		return skill;
	}
	
	/**
	 * 比较字段：
	 * id, name, amount, creatorId, 
	 * creatorName, createTime, status
	 */
	@Override
	public boolean test(Skill t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.amount == null || this.amount.equals(t.amount))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Skill element) {
		if (element == null)
			return;
		if (this.id != null) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.amount != null) {
			element.amount = this.amount;
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
