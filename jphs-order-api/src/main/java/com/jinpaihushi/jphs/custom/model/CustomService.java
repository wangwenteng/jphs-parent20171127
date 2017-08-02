package com.jinpaihushi.jphs.custom.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * CUSTOM_SERVICE 
 * 继承自父类的字段:
 * id : 	
 * type : 类型1、企业 0 个人	
 * createTime : 创建时间	
 * status : 状态(0:待回访，1:已回访)	
 * 
 * @author wangwt
 * @date 2017-07-19 17:15:11
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomService extends BaseModel implements Predicate<CustomService>,
		Updator<CustomService> {


    /** 姓名 */
	@Length(max = 50, message = "{customService.name.illegal.length}")
	private String name;

    /** 公司名称 */
	@Length(max = 50, message = "{customService.companyName.illegal.length}")
	private String companyName;

    /** 联系方式 */
	@Length(max = 50, message = "{customService.phone.illegal.length}")
	private String phone;

    /** 信息来源地址 */
	@Length(max = 50, message = "{customService.source.illegal.length}")
	private String source;

    /** 回访时间 */
	private Date visitTime;

    /** 回访客服 */
	@Length(max = 50, message = "{customService.visitor.illegal.length}")
	private String visitor;

    /**  */
	@Length(max = 65535, message = "{customService.remark.illegal.length}")
	private String remark;
	private Date beginTime;
	private Date stopTime;
	public CustomService(){}

	public CustomService(String id){
		this.id = id;
	}

	/**
	 * 获取姓名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置姓名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取公司名称
	 */
	public String getCompanyName() {
    	return companyName;
    }
  	
	/**
	 * 设置公司名称
	 */
	public void setCompanyName(String companyName) {
    	this.companyName = companyName;
    }

	/**
	 * 获取联系方式
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置联系方式
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * 获取信息来源地址
	 */
	public String getSource() {
    	return source;
    }
  	
	/**
	 * 设置信息来源地址
	 */
	public void setSource(String source) {
    	this.source = source;
    }

	/**
	 * 获取回访时间
	 */
	public Date getVisitTime() {
    	return visitTime;
    }
  	
	/**
	 * 设置回访时间
	 */
	public void setVisitTime(Date visitTime) {
    	this.visitTime = visitTime;
    }

	/**
	 * 获取回访客服
	 */
	public String getVisitor() {
    	return visitor;
    }
  	
	/**
	 * 设置回访客服
	 */
	public void setVisitor(String visitor) {
    	this.visitor = visitor;
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
	
    public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String toString() {
		return new StringBuilder().append("CustomService{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",companyName=").append(companyName).
			append(",type=").append(type).
			append(",phone=").append(phone).
			append(",createTime=").append(createTime).
			append(",source=").append(source).
			append(",visitTime=").append(visitTime).
			append(",visitor=").append(visitor).
			append(",status=").append(status).
			append(",remark=").append(remark).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, companyName, type, 
	 * phone, createTime, source, visitTime, 
	 * visitor, status, remark
	 */
	public CustomService copy(){
		CustomService customService = new CustomService();
     	customService.id = this.id;
     	customService.name = this.name;
     	customService.companyName = this.companyName;
     	customService.type = this.type;
     	customService.phone = this.phone;
     	customService.createTime = this.createTime;
     	customService.source = this.source;
     	customService.visitTime = this.visitTime;
     	customService.visitor = this.visitor;
     	customService.status = this.status;
     	customService.remark = this.remark;
		return customService;
	}
	
	/**
	 * 比较字段：
	 * id, name, companyName, type, 
	 * phone, createTime, source, visitTime, 
	 * visitor, status, remark
	 */
	@Override
	public boolean test(CustomService t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.companyName == null || this.companyName.equals(t.companyName))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.source == null || this.source.equals(t.source))
				&& (this.visitTime == null || this.visitTime.equals(t.visitTime))
				&& (this.visitor == null || this.visitor.equals(t.visitor))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.remark == null || this.remark.equals(t.remark))
		;
	}
	
	@Override
	public void update(CustomService element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.companyName != null && !this.companyName.isEmpty()) {
			element.companyName = this.companyName;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.source != null && !this.source.isEmpty()) {
			element.source = this.source;
		}
		if (this.visitTime != null) {
			element.visitTime = this.visitTime;
		}
		if (this.visitor != null && !this.visitor.isEmpty()) {
			element.visitor = this.visitor;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
	}
}
