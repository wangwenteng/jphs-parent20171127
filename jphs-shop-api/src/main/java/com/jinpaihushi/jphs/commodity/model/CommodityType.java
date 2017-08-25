package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_TYPE 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-09 09:22:31
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityType extends BaseModel implements Predicate<CommodityType>,
		Updator<CommodityType> {


    /** 商品类品名称 */
	@Length(max = 255, message = "{commodityType.name.illegal.length}")
	private String name;

    /** 排序 */
	private Integer sort;

    /**  */
	@Length(max = 255, message = "{commodityType.parentid.illegal.length}")
	private String parentid;

    /**  */
	@Length(max = 65535, message = "{commodityType.remark.illegal.length}")
	private String remark;

	public CommodityType(){}

	public CommodityType(String id){
		this.id = id;
	}

	/**
	 * 获取商品类品名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置商品类品名称
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

	/**
	 * 获取
	 */
	public String getParentid() {
    	return parentid;
    }
  	
	/**
	 * 设置
	 */
	public void setParentid(String parentid) {
    	this.parentid = parentid;
    }

	/**
	 * 获取
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

    public String toString() {
		return new StringBuilder().append("CommodityType{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",sort=").append(sort).
			append(",parentid=").append(parentid).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, sort, parentid, 
	 * remark, status, createTime, creatorId, 
	 * creatorName
	 */
	public CommodityType copy(){
		CommodityType commodityType = new CommodityType();
     	commodityType.id = this.id;
     	commodityType.name = this.name;
     	commodityType.sort = this.sort;
     	commodityType.parentid = this.parentid;
     	commodityType.remark = this.remark;
     	commodityType.status = this.status;
     	commodityType.createTime = this.createTime;
     	commodityType.creatorId = this.creatorId;
     	commodityType.creatorName = this.creatorName;
		return commodityType;
	}
	
	/**
	 * 比较字段：
	 * id, name, sort, parentid, 
	 * remark, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(CommodityType t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.parentid == null || this.parentid.equals(t.parentid))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityType element) {
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
		if (this.parentid != null && !this.parentid.isEmpty()) {
			element.parentid = this.parentid;
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
