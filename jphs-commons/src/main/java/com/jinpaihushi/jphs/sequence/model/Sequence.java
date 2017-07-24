package com.jinpaihushi.jphs.sequence.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SEQUENCE 
 * 继承自父类的字段:
 * 
 * @author wangwt
 * @date 2017-06-30 13:56:21
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Sequence extends BaseModel implements Predicate<Sequence>,
		Updator<Sequence> {


    /** 列序名 */
	@NotEmpty(message = "{sequence.seqName.empty}")
	@Length(max = 50, message = "{sequence.seqName.illegal.length}")
	private String seqName;

    /** 当前值 */
	@NotNull(message = "{sequence.currentVal.null}")
	private Integer currentVal;

    /** 增长值 */
	@NotNull(message = "{sequence.incrementVal.null}")
	private Integer incrementVal;

	public Sequence(){}


	/**
	 * 获取列序名
	 */
	public String getSeqName() {
    	return seqName;
    }
  	
	/**
	 * 设置列序名
	 */
	public void setSeqName(String seqName) {
    	this.seqName = seqName;
    }

	/**
	 * 获取当前值
	 */
	public Integer getCurrentVal() {
    	return currentVal;
    }
  	
	/**
	 * 设置当前值
	 */
	public void setCurrentVal(Integer currentVal) {
    	this.currentVal = currentVal;
    }

	/**
	 * 获取增长值
	 */
	public Integer getIncrementVal() {
    	return incrementVal;
    }
  	
	/**
	 * 设置增长值
	 */
	public void setIncrementVal(Integer incrementVal) {
    	this.incrementVal = incrementVal;
    }

    public String toString() {
		return new StringBuilder().append("Sequence{").
			append("seqName=").append(seqName).
			append(",currentVal=").append(currentVal).
			append(",incrementVal=").append(incrementVal).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * seqName, currentVal, incrementVal
	 */
	public Sequence copy(){
		Sequence sequence = new Sequence();
     	sequence.seqName = this.seqName;
     	sequence.currentVal = this.currentVal;
     	sequence.incrementVal = this.incrementVal;
		return sequence;
	}
	
	/**
	 * 比较字段：
	 * seqName, currentVal, incrementVal
	 */
	@Override
	public boolean test(Sequence t) {
		if(t == null) return false;
		return (this.seqName == null || this.seqName.equals(t.seqName))
				&& (this.currentVal == null || this.currentVal.equals(t.currentVal))
				&& (this.incrementVal == null || this.incrementVal.equals(t.incrementVal))
		;
	}
	
	@Override
	public void update(Sequence element) {
		if (element == null)
			return;
		if (this.seqName != null && !this.seqName.isEmpty()) {
			element.seqName = this.seqName;
		}
		if (this.currentVal != null) {
			element.currentVal = this.currentVal;
		}
		if (this.incrementVal != null) {
			element.incrementVal = this.incrementVal;
		}
	}
}
