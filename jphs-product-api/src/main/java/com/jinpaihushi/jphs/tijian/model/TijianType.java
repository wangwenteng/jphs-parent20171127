package com.jinpaihushi.jphs.tijian.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * TIJIAN_TYPE 
 * 继承自父类的字段:
 * id : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-08-08 15:34:54
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TijianType extends BaseModel implements Predicate<TijianType>,
		Updator<TijianType> {


    /**  */
	@Length(max = 255, message = "{tijianType.name.illegal.length}")
	private String name;

	public TijianType(){}

	public TijianType(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置
	 */
	public void setName(String name) {
    	this.name = name;
    }

    public String toString() {
		return new StringBuilder().append("TijianType{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, createTime, creatorId, 
	 * creatorName, status
	 */
	public TijianType copy(){
		TijianType tijianType = new TijianType();
     	tijianType.id = this.id;
     	tijianType.name = this.name;
     	tijianType.createTime = this.createTime;
     	tijianType.creatorId = this.creatorId;
     	tijianType.creatorName = this.creatorName;
     	tijianType.status = this.status;
		return tijianType;
	}
	
	/**
	 * 比较字段：
	 * id, name, createTime, creatorId, 
	 * creatorName, status
	 */
	@Override
	public boolean test(TijianType t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(TijianType element) {
		if (element == null)
			return;
		if (this.id != null) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
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
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
