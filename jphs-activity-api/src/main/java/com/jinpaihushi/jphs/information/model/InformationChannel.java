package com.jinpaihushi.jphs.information.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * INFORMATION_CHANNEL 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InformationChannel extends BaseModel implements Predicate<InformationChannel>,
		Updator<InformationChannel> {


    /** 频道名称 */
	@Length(max = 50, message = "{informationChannel.name.illegal.length}")
	private String name;

    /** 序排 */
	private Integer sort;

	public InformationChannel(){}

	public InformationChannel(String id){
		this.id = id;
	}

	/**
	 * 获取频道名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置频道名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取序排
	 */
	public Integer getSort() {
    	return sort;
    }
  	
	/**
	 * 设置序排
	 */
	public void setSort(Integer sort) {
    	this.sort = sort;
    }

    public String toString() {
		return new StringBuilder().append("InformationChannel{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",sort=").append(sort).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, sort, status, 
	 * createTime, creatorId, creatorName
	 */
	public InformationChannel copy(){
		InformationChannel informationChannel = new InformationChannel();
     	informationChannel.id = this.id;
     	informationChannel.name = this.name;
     	informationChannel.sort = this.sort;
     	informationChannel.status = this.status;
     	informationChannel.createTime = this.createTime;
     	informationChannel.creatorId = this.creatorId;
     	informationChannel.creatorName = this.creatorName;
		return informationChannel;
	}
	
	/**
	 * 比较字段：
	 * id, name, sort, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(InformationChannel t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(InformationChannel element) {
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
