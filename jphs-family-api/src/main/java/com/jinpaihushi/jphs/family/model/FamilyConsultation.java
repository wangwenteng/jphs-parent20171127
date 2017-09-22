package com.jinpaihushi.jphs.family.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_CONSULTATION 
 * 继承自父类的字段:
 * id : 	
 * status : 状态(0正常，-1删除)	
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
public class FamilyConsultation extends BaseModel implements Predicate<FamilyConsultation>,
		Updator<FamilyConsultation> {


    /** 问题 */
	@Length(max = 500, message = "{familyConsultation.quiz.illegal.length}")
	private String quiz;

    /** 答案 */
	@Length(max = 65535, message = "{familyConsultation.answer.illegal.length}")
	private String answer;

	public FamilyConsultation(){}

	public FamilyConsultation(String id){
		this.id = id;
	}

	/**
	 * 获取问题
	 */
	public String getQuiz() {
    	return quiz;
    }
  	
	/**
	 * 设置问题
	 */
	public void setQuiz(String quiz) {
    	this.quiz = quiz;
    }

	/**
	 * 获取答案
	 */
	public String getAnswer() {
    	return answer;
    }
  	
	/**
	 * 设置答案
	 */
	public void setAnswer(String answer) {
    	this.answer = answer;
    }

    public String toString() {
		return new StringBuilder().append("FamilyConsultation{").
			append("id=").append(id).
			append(",quiz=").append(quiz).
			append(",answer=").append(answer).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, quiz, answer, status, 
	 * createTime, creatorId, creatorName
	 */
	public FamilyConsultation copy(){
		FamilyConsultation familyConsultation = new FamilyConsultation();
     	familyConsultation.id = this.id;
     	familyConsultation.quiz = this.quiz;
     	familyConsultation.answer = this.answer;
     	familyConsultation.status = this.status;
     	familyConsultation.createTime = this.createTime;
     	familyConsultation.creatorId = this.creatorId;
     	familyConsultation.creatorName = this.creatorName;
		return familyConsultation;
	}
	
	/**
	 * 比较字段：
	 * id, quiz, answer, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(FamilyConsultation t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.quiz == null || this.quiz.equals(t.quiz))
				&& (this.answer == null || this.answer.equals(t.answer))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyConsultation element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.quiz != null && !this.quiz.isEmpty()) {
			element.quiz = this.quiz;
		}
		if (this.answer != null && !this.answer.isEmpty()) {
			element.answer = this.answer;
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
