package com.jinpaihushi.jphs.order.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * ORDER_OTHER 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * 
 * @author yangsong
 * @date 2017-07-04 10:39:09
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class OrderOther extends BaseModel implements Predicate<OrderOther>,
		Updator<OrderOther> {


    /** 订单id */
	private String orderId;

    /** 科室id */
	@Length(max = 500, message = "{orderOther.departmentId.illegal.length}")
	private String departmentId;

    /** 医院 */
	@Length(max = 255, message = "{orderOther.hospital.illegal.length}")
	private String hospital;

    /** 地址-省 */
	@Length(max = 50, message = "{orderOther.address.illegal.length}")
	private String address;

    /** 详细地址 */
	@Length(max = 500, message = "{orderOther.detailAddress.illegal.length}")
	private String detailAddress;

    /** 是否有药品 */
	@Length(max = 50, message = "{orderOther.drug.illegal.length}")
	private String drug;

    /** 是否有工具 */
	@Length(max = 50, message = "{orderOther.tool.illegal.length}")
	private String tool;

    /** 通用字段 */
	@Length(max = 255, message = "{orderOther.publicName.illegal.length}")
	private String publicName;

    /** 通用字段 */
	@Length(max = 255, message = "{orderOther.publicContent.illegal.length}")
	private String publicContent;

    /**  */
	@Length(max = 255, message = "{orderOther.remarks.illegal.length}")
	private String remarks;

	public OrderOther(){}

	public OrderOther(String id){
		this.id = id;
	}

	/**
	 * 获取订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 设置订单id
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 获取科室id
	 */
	public String getDepartmentId() {
    	return departmentId;
    }
  	
	



	/**
	 * 设置科室id
	 */
	public void setDepartmentId(String departmentId) {
    	this.departmentId = departmentId;
    }

	/**
	 * 获取医院
	 */
	public String getHospital() {
    	return hospital;
    }
  	
	/**
	 * 设置医院
	 */
	public void setHospital(String hospital) {
    	this.hospital = hospital;
    }

	/**
	 * 获取地址-省
	 */
	public String getAddress() {
    	return address;
    }
  	
	/**
	 * 设置地址-省
	 */
	public void setAddress(String address) {
    	this.address = address;
    }

	/**
	 * 获取详细地址
	 */
	public String getDetailAddress() {
    	return detailAddress;
    }
  	
	/**
	 * 设置详细地址
	 */
	public void setDetailAddress(String detailAddress) {
    	this.detailAddress = detailAddress;
    }

	/**
	 * 获取是否有药品
	 */
	public String getDrug() {
    	return drug;
    }
  	
	/**
	 * 设置是否有药品
	 */
	public void setDrug(String drug) {
    	this.drug = drug;
    }

	/**
	 * 获取是否有工具
	 */
	public String getTool() {
    	return tool;
    }
  	
	/**
	 * 设置是否有工具
	 */
	public void setTool(String tool) {
    	this.tool = tool;
    }

	/**
	 * 获取通用字段
	 */
	public String getPublicName() {
    	return publicName;
    }
  	
	/**
	 * 设置通用字段
	 */
	public void setPublicName(String publicName) {
    	this.publicName = publicName;
    }

	/**
	 * 获取通用字段
	 */
	public String getPublicContent() {
    	return publicContent;
    }
  	
	/**
	 * 设置通用字段
	 */
	public void setPublicContent(String publicContent) {
    	this.publicContent = publicContent;
    }

	/**
	 * 获取
	 */
	public String getRemarks() {
    	return remarks;
    }
  	
	/**
	 * 设置
	 */
	public void setRemarks(String remarks) {
    	this.remarks = remarks;
    }

    public String toString() {
		return new StringBuilder().append("OrderOther{").
			append("id=").append(id).
			append(",orderId=").append(orderId).
			append(",departmentId=").append(departmentId).
			append(",hospital=").append(hospital).
			append(",address=").append(address).
			append(",detailAddress=").append(detailAddress).
			append(",drug=").append(drug).
			append(",tool=").append(tool).
			append(",publicName=").append(publicName).
			append(",publicContent=").append(publicContent).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",remarks=").append(remarks).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, orderId, departmentId, hospital, 
	 * address, detailAddress, drug, tool, 
	 * publicName, publicContent, status, createTime, 
	 * creatorId, remarks
	 */
	public OrderOther copy(){
		OrderOther orderOther = new OrderOther();
     	orderOther.id = this.id;
     	orderOther.orderId = this.orderId;
     	orderOther.departmentId = this.departmentId;
     	orderOther.hospital = this.hospital;
     	orderOther.address = this.address;
     	orderOther.detailAddress = this.detailAddress;
     	orderOther.drug = this.drug;
     	orderOther.tool = this.tool;
     	orderOther.publicName = this.publicName;
     	orderOther.publicContent = this.publicContent;
     	orderOther.status = this.status;
     	orderOther.createTime = this.createTime;
     	orderOther.creatorId = this.creatorId;
     	orderOther.remarks = this.remarks;
		return orderOther;
	}
	
	/**
	 * 比较字段：
	 * id, orderId, departmentId, hospital, 
	 * address, detailAddress, drug, tool, 
	 * publicName, publicContent, status, createTime, 
	 * creatorId, remarks
	 */
	@Override
	public boolean test(OrderOther t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.orderId == null || this.orderId.equals(t.orderId))
				&& (this.departmentId == null || this.departmentId.equals(t.departmentId))
				&& (this.hospital == null || this.hospital.equals(t.hospital))
				&& (this.address == null || this.address.equals(t.address))
				&& (this.detailAddress == null || this.detailAddress.equals(t.detailAddress))
				&& (this.drug == null || this.drug.equals(t.drug))
				&& (this.tool == null || this.tool.equals(t.tool))
				&& (this.publicName == null || this.publicName.equals(t.publicName))
				&& (this.publicContent == null || this.publicContent.equals(t.publicContent))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.remarks == null || this.remarks.equals(t.remarks))
		;
	}
	
	@Override
	public void update(OrderOther element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.orderId != null) {
			element.orderId = this.orderId;
		}
		if (this.departmentId != null && !this.departmentId.isEmpty()) {
			element.departmentId = this.departmentId;
		}
		if (this.hospital != null && !this.hospital.isEmpty()) {
			element.hospital = this.hospital;
		}
		if (this.address != null && !this.address.isEmpty()) {
			element.address = this.address;
		}
		if (this.detailAddress != null && !this.detailAddress.isEmpty()) {
			element.detailAddress = this.detailAddress;
		}
		if (this.drug != null && !this.drug.isEmpty()) {
			element.drug = this.drug;
		}
		if (this.tool != null && !this.tool.isEmpty()) {
			element.tool = this.tool;
		}
		if (this.publicName != null && !this.publicName.isEmpty()) {
			element.publicName = this.publicName;
		}
		if (this.publicContent != null && !this.publicContent.isEmpty()) {
			element.publicContent = this.publicContent;
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
		if (this.remarks != null && !this.remarks.isEmpty()) {
			element.remarks = this.remarks;
		}
	}
}
