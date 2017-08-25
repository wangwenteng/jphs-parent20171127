package com.jinpaihushi.jphs.nurse.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.jphs.skill.model.Skill;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_SKILL 发布技能表
 * 继承自父类的字段:
 * id : uuid	
 * creatorId : 创建人	
 * creatorName : 创建人姓名	
 * createTime : 创建时间	
 * status : 是否删除	
 * 
 * @author wangwt
 * @date 2017-06-30 09:46:29
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseSkill extends BaseModel implements Predicate<NurseSkill>,
		Updator<NurseSkill> {


    /** 技能ID */
	@Length(max = 50, message = "{nurseSkill.skillId.illegal.length}")
	private String skillId;

    /** 价格 */
	private Double price;

    /** 服务区域 */
	@Length(max = 200, message = "{nurseSkill.locationId.illegal.length}")
	private String locationId;

    /** 空闲周期 */
	@Length(max = 500, message = "{nurseSkill.freeCycle.illegal.length}")
	private String freeCycle;

    /** 空闲时间 */
	@Length(max = 500, message = "{nurseSkill.leisureTime.illegal.length}")
	private String leisureTime;
	private Skill skill;
	private String imageUrl;
	private Location location;
	private List<NurseImages> nurseImage;
	public NurseSkill(){}

	public NurseSkill(String id){
		this.id = id;
	}

	/**
	 * 获取技能ID
	 */
	public String getSkillId() {
    	return skillId;
    }
  	
	/**
	 * 设置技能ID
	 */
	public void setSkillId(String skillId) {
    	this.skillId = skillId;
    }

	/**
	 * 获取价格
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置价格
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取服务区域
	 */
	public String getLocationId() {
    	return locationId;
    }
  	
	/**
	 * 设置服务区域
	 */
	public void setLocationId(String locationId) {
    	this.locationId = locationId;
    }

	/**
	 * 获取空闲周期
	 */
	public String getFreeCycle() {
    	return freeCycle;
    }
  	
	/**
	 * 设置空闲周期
	 */
	public void setFreeCycle(String freeCycle) {
    	this.freeCycle = freeCycle;
    }

	/**
	 * 获取空闲时间
	 */
	public String getLeisureTime() {
    	return leisureTime;
    }
  	
	/**
	 * 设置空闲时间
	 */
	public void setLeisureTime(String leisureTime) {
    	this.leisureTime = leisureTime;
    }
	
    public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	public List<NurseImages> getNurseImage() {
		return nurseImage;
	}

	public void setNurseImage(List<NurseImages> nurseImage) {
		this.nurseImage = nurseImage;
	}
	
	public String toString() {
		return new StringBuilder().append("NurseSkill{").
			append("id=").append(id).
			append(",skillId=").append(skillId).
			append(",price=").append(price).
			append(",locationId=").append(locationId).
			append(",freeCycle=").append(freeCycle).
			append(",leisureTime=").append(leisureTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
   /* public NurseImages getNurseImage() {
		return nurseImage;
	}

	public void setNurseImage(NurseImages nurseImage) {
		this.nurseImage = nurseImage;
	}*/

	/**
	 * 复制字段：
	 * id, skillId, price, locationId, 
	 * freeCycle, leisureTime, creatorId, creatorName, 
	 * createTime, status
	 */
	public NurseSkill copy(){
		NurseSkill nurseSkill = new NurseSkill();
     	nurseSkill.id = this.id;
     	nurseSkill.skillId = this.skillId;
     	nurseSkill.price = this.price;
     	nurseSkill.locationId = this.locationId;
     	nurseSkill.freeCycle = this.freeCycle;
     	nurseSkill.leisureTime = this.leisureTime;
     	nurseSkill.creatorId = this.creatorId;
     	nurseSkill.creatorName = this.creatorName;
     	nurseSkill.createTime = this.createTime;
     	nurseSkill.status = this.status;
		return nurseSkill;
	}
	
	/**
	 * 比较字段：
	 * id, skillId, price, locationId, 
	 * freeCycle, leisureTime, creatorId, creatorName, 
	 * createTime, status
	 */
	@Override
	public boolean test(NurseSkill t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.skillId == null || this.skillId.equals(t.skillId))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.locationId == null || this.locationId.equals(t.locationId))
				&& (this.freeCycle == null || this.freeCycle.equals(t.freeCycle))
				&& (this.leisureTime == null || this.leisureTime.equals(t.leisureTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(NurseSkill element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.skillId != null && !this.skillId.isEmpty()) {
			element.skillId = this.skillId;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.locationId != null && !this.locationId.isEmpty()) {
			element.locationId = this.locationId;
		}
		if (this.freeCycle != null && !this.freeCycle.isEmpty()) {
			element.freeCycle = this.freeCycle;
		}
		if (this.leisureTime != null && !this.leisureTime.isEmpty()) {
			element.leisureTime = this.leisureTime;
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
