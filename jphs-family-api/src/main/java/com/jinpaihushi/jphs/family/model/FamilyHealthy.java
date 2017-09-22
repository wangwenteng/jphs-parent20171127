package com.jinpaihushi.jphs.family.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_HEALTHY 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FamilyHealthy extends BaseModel implements Predicate<FamilyHealthy>,
		Updator<FamilyHealthy> {


    /**  */
	@Length(max = 50, message = "{familyHealthy.familyMemberId.illegal.length}")
	private String familyMemberId;

    /** 健康计划标题 */
	@Length(max = 50, message = "{familyHealthy.title.illegal.length}")
	private String title;

    /** 总周期 */
	@Length(max = 50, message = "{familyHealthy.cycle.illegal.length}")
	private String cycle;

    /** 相关病史 */
	@Length(max = 50, message = "{familyHealthy.history.illegal.length}")
	private String history;

    /** 病史内容，编辑器 */
	@Length(max = 65535, message = "{familyHealthy.historyContent.illegal.length}")
	private String historyContent;

    /** 病史周期 */
	@Length(max = 50, message = "{familyHealthy.historyCycle.illegal.length}")
	private String historyCycle;

    /** 治疗计划 */
	@Length(max = 50, message = "{familyHealthy.plan.illegal.length}")
	private String plan;

    /** 计划内容，编辑器 */
	@Length(max = 65535, message = "{familyHealthy.planContent.illegal.length}")
	private String planContent;

    /** 计划周期 */
	@Length(max = 50, message = "{familyHealthy.planCycle.illegal.length}")
	private String planCycle;

    /** 饮食安排 */
	@Length(max = 50, message = "{familyHealthy.diet.illegal.length}")
	private String diet;

    /** 饮食内容，编辑器 */
	@Length(max = 65535, message = "{familyHealthy.dietContent.illegal.length}")
	private String dietContent;

    /** 饮食周期 */
	@Length(max = 50, message = "{familyHealthy.dietCycle.illegal.length}")
	private String dietCycle;

	public FamilyHealthy(){}

	public FamilyHealthy(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getFamilyMemberId() {
    	return familyMemberId;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyMemberId(String familyMemberId) {
    	this.familyMemberId = familyMemberId;
    }

	/**
	 * 获取健康计划标题
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置健康计划标题
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取总周期
	 */
	public String getCycle() {
    	return cycle;
    }
  	
	/**
	 * 设置总周期
	 */
	public void setCycle(String cycle) {
    	this.cycle = cycle;
    }

	/**
	 * 获取相关病史
	 */
	public String getHistory() {
    	return history;
    }
  	
	/**
	 * 设置相关病史
	 */
	public void setHistory(String history) {
    	this.history = history;
    }

	/**
	 * 获取病史内容，编辑器
	 */
	public String getHistoryContent() {
    	return historyContent;
    }
  	
	/**
	 * 设置病史内容，编辑器
	 */
	public void setHistoryContent(String historyContent) {
    	this.historyContent = historyContent;
    }

	/**
	 * 获取病史周期
	 */
	public String getHistoryCycle() {
    	return historyCycle;
    }
  	
	/**
	 * 设置病史周期
	 */
	public void setHistoryCycle(String historyCycle) {
    	this.historyCycle = historyCycle;
    }

	/**
	 * 获取治疗计划
	 */
	public String getPlan() {
    	return plan;
    }
  	
	/**
	 * 设置治疗计划
	 */
	public void setPlan(String plan) {
    	this.plan = plan;
    }

	/**
	 * 获取计划内容，编辑器
	 */
	public String getPlanContent() {
    	return planContent;
    }
  	
	/**
	 * 设置计划内容，编辑器
	 */
	public void setPlanContent(String planContent) {
    	this.planContent = planContent;
    }

	/**
	 * 获取计划周期
	 */
	public String getPlanCycle() {
    	return planCycle;
    }
  	
	/**
	 * 设置计划周期
	 */
	public void setPlanCycle(String planCycle) {
    	this.planCycle = planCycle;
    }

	/**
	 * 获取饮食安排
	 */
	public String getDiet() {
    	return diet;
    }
  	
	/**
	 * 设置饮食安排
	 */
	public void setDiet(String diet) {
    	this.diet = diet;
    }

	/**
	 * 获取饮食内容，编辑器
	 */
	public String getDietContent() {
    	return dietContent;
    }
  	
	/**
	 * 设置饮食内容，编辑器
	 */
	public void setDietContent(String dietContent) {
    	this.dietContent = dietContent;
    }

	/**
	 * 获取饮食周期
	 */
	public String getDietCycle() {
    	return dietCycle;
    }
  	
	/**
	 * 设置饮食周期
	 */
	public void setDietCycle(String dietCycle) {
    	this.dietCycle = dietCycle;
    }

    public String toString() {
		return new StringBuilder().append("FamilyHealthy{").
			append("id=").append(id).
			append(",familyMemberId=").append(familyMemberId).
			append(",title=").append(title).
			append(",cycle=").append(cycle).
			append(",history=").append(history).
			append(",historyContent=").append(historyContent).
			append(",historyCycle=").append(historyCycle).
			append(",plan=").append(plan).
			append(",planContent=").append(planContent).
			append(",planCycle=").append(planCycle).
			append(",diet=").append(diet).
			append(",dietContent=").append(dietContent).
			append(",dietCycle=").append(dietCycle).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, familyMemberId, title, cycle, 
	 * history, historyContent, historyCycle, plan, 
	 * planContent, planCycle, diet, dietContent, 
	 * dietCycle, status, createTime, creatorId, 
	 * creatorName
	 */
	public FamilyHealthy copy(){
		FamilyHealthy familyHealthy = new FamilyHealthy();
     	familyHealthy.id = this.id;
     	familyHealthy.familyMemberId = this.familyMemberId;
     	familyHealthy.title = this.title;
     	familyHealthy.cycle = this.cycle;
     	familyHealthy.history = this.history;
     	familyHealthy.historyContent = this.historyContent;
     	familyHealthy.historyCycle = this.historyCycle;
     	familyHealthy.plan = this.plan;
     	familyHealthy.planContent = this.planContent;
     	familyHealthy.planCycle = this.planCycle;
     	familyHealthy.diet = this.diet;
     	familyHealthy.dietContent = this.dietContent;
     	familyHealthy.dietCycle = this.dietCycle;
     	familyHealthy.status = this.status;
     	familyHealthy.createTime = this.createTime;
     	familyHealthy.creatorId = this.creatorId;
     	familyHealthy.creatorName = this.creatorName;
		return familyHealthy;
	}
	
	/**
	 * 比较字段：
	 * id, familyMemberId, title, cycle, 
	 * history, historyContent, historyCycle, plan, 
	 * planContent, planCycle, diet, dietContent, 
	 * dietCycle, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(FamilyHealthy t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.familyMemberId == null || this.familyMemberId.equals(t.familyMemberId))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.cycle == null || this.cycle.equals(t.cycle))
				&& (this.history == null || this.history.equals(t.history))
				&& (this.historyContent == null || this.historyContent.equals(t.historyContent))
				&& (this.historyCycle == null || this.historyCycle.equals(t.historyCycle))
				&& (this.plan == null || this.plan.equals(t.plan))
				&& (this.planContent == null || this.planContent.equals(t.planContent))
				&& (this.planCycle == null || this.planCycle.equals(t.planCycle))
				&& (this.diet == null || this.diet.equals(t.diet))
				&& (this.dietContent == null || this.dietContent.equals(t.dietContent))
				&& (this.dietCycle == null || this.dietCycle.equals(t.dietCycle))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyHealthy element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.familyMemberId != null && !this.familyMemberId.isEmpty()) {
			element.familyMemberId = this.familyMemberId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.cycle != null && !this.cycle.isEmpty()) {
			element.cycle = this.cycle;
		}
		if (this.history != null && !this.history.isEmpty()) {
			element.history = this.history;
		}
		if (this.historyContent != null && !this.historyContent.isEmpty()) {
			element.historyContent = this.historyContent;
		}
		if (this.historyCycle != null && !this.historyCycle.isEmpty()) {
			element.historyCycle = this.historyCycle;
		}
		if (this.plan != null && !this.plan.isEmpty()) {
			element.plan = this.plan;
		}
		if (this.planContent != null && !this.planContent.isEmpty()) {
			element.planContent = this.planContent;
		}
		if (this.planCycle != null && !this.planCycle.isEmpty()) {
			element.planCycle = this.planCycle;
		}
		if (this.diet != null && !this.diet.isEmpty()) {
			element.diet = this.diet;
		}
		if (this.dietContent != null && !this.dietContent.isEmpty()) {
			element.dietContent = this.dietContent;
		}
		if (this.dietCycle != null && !this.dietCycle.isEmpty()) {
			element.dietCycle = this.dietCycle;
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
