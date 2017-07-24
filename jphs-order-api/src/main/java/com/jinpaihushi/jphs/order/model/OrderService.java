package com.jinpaihushi.jphs.order.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * ORDER_SERVICE 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-07-04 10:50:13
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class OrderService extends BaseModel implements Predicate<OrderService>,
		Updator<OrderService> {


    /** 主订单id */
	@Length(max = 50, message = "{orderService.orderId.illegal.length}")
	private String orderId;

    /** 单次价格 */
	private Double price;

    /** 执行人 */
	@Length(max = 50, message = "{orderService.nurseId.illegal.length}")
	private String nurseId;

    /** 患者姓名 */
	@Length(max = 50, message = "{orderService.patientName.illegal.length}")
	private String patientName;

    /** 患者电话 */
	@Length(max = 50, message = "{orderService.patientPhone.illegal.length}")
	private String patientPhone;

    /** 指定医生 */
	@Length(max = 50, message = "{orderService.expectorDoctor.illegal.length}")
	private String expectorDoctor;
	
	private String nurseName;

    /** 进度 */
	private Integer schedule;

    /** 预约时间 */
	private Date appointmentTime;

    /** 出发时间 */
	private Date setoutTime;

    /** 开始服务时间 */
	private Date startServiceTime;

    /** 结束服务时间 */
	private Date endServiceTime;

    /** 提醒时间 */
	private Date remindTime;

    /** 完成订单时间 */
	private Date confirmTime;

	public OrderService(){}

	public OrderService(String id){
		this.id = id;
	}

	/**
	 * 获取主订单id
	 */
	public String getOrderId() {
    	return orderId;
    }
  	
	/**
	 * 设置主订单id
	 */
	public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

	/**
	 * 获取单次价格
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置单次价格
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取执行人
	 */
	public String getNurseId() {
    	return nurseId;
    }
  	
	/**
	 * 设置执行人
	 */
	public void setNurseId(String nurseId) {
    	this.nurseId = nurseId;
    }

	/**
	 * 获取患者姓名
	 */
	public String getPatientName() {
    	return patientName;
    }
  	
	/**
	 * 设置患者姓名
	 */
	public void setPatientName(String patientName) {
    	this.patientName = patientName;
    }

	/**
	 * 获取患者电话
	 */
	public String getPatientPhone() {
    	return patientPhone;
    }
  	
	/**
	 * 设置患者电话
	 */
	public void setPatientPhone(String patientPhone) {
    	this.patientPhone = patientPhone;
    }

	/**
	 * 获取指定医生
	 */
	public String getExpectorDoctor() {
    	return expectorDoctor;
    }
  	
	/**
	 * 设置指定医生
	 */
	public void setExpectorDoctor(String expectorDoctor) {
    	this.expectorDoctor = expectorDoctor;
    }

	/**
	 * 获取进度
	 */
	public Integer getSchedule() {
    	return schedule;
    }
  	
	/**
	 * 设置进度
	 */
	public void setSchedule(Integer schedule) {
    	this.schedule = schedule;
    }

	/**
	 * 获取预约时间
	 */
	public Date getAppointmentTime() {
    	return appointmentTime;
    }
  	
	/**
	 * 设置预约时间
	 */
	public void setAppointmentTime(Date appointmentTime) {
    	this.appointmentTime = appointmentTime;
    }

	/**
	 * 获取出发时间
	 */
	public Date getSetoutTime() {
    	return setoutTime;
    }
  	
	/**
	 * 设置出发时间
	 */
	public void setSetoutTime(Date setoutTime) {
    	this.setoutTime = setoutTime;
    }

	/**
	 * 获取开始服务时间
	 */
	public Date getStartServiceTime() {
    	return startServiceTime;
    }
  	
	/**
	 * 设置开始服务时间
	 */
	public void setStartServiceTime(Date startServiceTime) {
    	this.startServiceTime = startServiceTime;
    }

	/**
	 * 获取结束服务时间
	 */
	public Date getEndServiceTime() {
    	return endServiceTime;
    }
  	
	/**
	 * 设置结束服务时间
	 */
	public void setEndServiceTime(Date endServiceTime) {
    	this.endServiceTime = endServiceTime;
    }

	/**
	 * 获取提醒时间
	 */
	public Date getRemindTime() {
    	return remindTime;
    }
  	
	/**
	 * 设置提醒时间
	 */
	public void setRemindTime(Date remindTime) {
    	this.remindTime = remindTime;
    }

	/**
	 * 获取完成订单时间
	 */
	public Date getConfirmTime() {
    	return confirmTime;
    }
  	
	/**
	 * 设置完成订单时间
	 */
	public void setConfirmTime(Date confirmTime) {
    	this.confirmTime = confirmTime;
    }
	
	

    public String getNurseName() {
		return nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	public String toString() {
		return new StringBuilder().append("OrderService{").
			append("id=").append(id).
			append(",orderId=").append(orderId).
			append(",price=").append(price).
			append(",nurseId=").append(nurseId).
			append(",patientName=").append(patientName).
			append(",patientPhone=").append(patientPhone).
			append(",expectorDoctor=").append(expectorDoctor).
			append(",schedule=").append(schedule).
			append(",appointmentTime=").append(appointmentTime).
			append(",setoutTime=").append(setoutTime).
			append(",startServiceTime=").append(startServiceTime).
			append(",endServiceTime=").append(endServiceTime).
			append(",remindTime=").append(remindTime).
			append(",confirmTime=").append(confirmTime).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, orderId, price, nurseId, 
	 * patientName, patientPhone, expectorDoctor, schedule, 
	 * appointmentTime, setoutTime, startServiceTime, endServiceTime, 
	 * remindTime, confirmTime, status, createTime, 
	 * creatorId, creatorName
	 */
	public OrderService copy(){
		OrderService orderService = new OrderService();
     	orderService.id = this.id;
     	orderService.orderId = this.orderId;
     	orderService.price = this.price;
     	orderService.nurseId = this.nurseId;
     	orderService.patientName = this.patientName;
     	orderService.patientPhone = this.patientPhone;
     	orderService.expectorDoctor = this.expectorDoctor;
     	orderService.schedule = this.schedule;
     	orderService.appointmentTime = this.appointmentTime;
     	orderService.setoutTime = this.setoutTime;
     	orderService.startServiceTime = this.startServiceTime;
     	orderService.endServiceTime = this.endServiceTime;
     	orderService.remindTime = this.remindTime;
     	orderService.confirmTime = this.confirmTime;
     	orderService.status = this.status;
     	orderService.createTime = this.createTime;
     	orderService.creatorId = this.creatorId;
     	orderService.creatorName = this.creatorName;
		return orderService;
	}
	
	/**
	 * 比较字段：
	 * id, orderId, price, nurseId, 
	 * patientName, patientPhone, expectorDoctor, schedule, 
	 * appointmentTime, setoutTime, startServiceTime, endServiceTime, 
	 * remindTime, confirmTime, status, createTime, 
	 * creatorId, creatorName
	 */
	@Override
	public boolean test(OrderService t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.orderId == null || this.orderId.equals(t.orderId))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.nurseId == null || this.nurseId.equals(t.nurseId))
				&& (this.patientName == null || this.patientName.equals(t.patientName))
				&& (this.patientPhone == null || this.patientPhone.equals(t.patientPhone))
				&& (this.expectorDoctor == null || this.expectorDoctor.equals(t.expectorDoctor))
				&& (this.schedule == null || this.schedule.equals(t.schedule))
				&& (this.appointmentTime == null || this.appointmentTime.equals(t.appointmentTime))
				&& (this.setoutTime == null || this.setoutTime.equals(t.setoutTime))
				&& (this.startServiceTime == null || this.startServiceTime.equals(t.startServiceTime))
				&& (this.endServiceTime == null || this.endServiceTime.equals(t.endServiceTime))
				&& (this.remindTime == null || this.remindTime.equals(t.remindTime))
				&& (this.confirmTime == null || this.confirmTime.equals(t.confirmTime))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(OrderService element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.orderId != null && !this.orderId.isEmpty()) {
			element.orderId = this.orderId;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.nurseId != null && !this.nurseId.isEmpty()) {
			element.nurseId = this.nurseId;
		}
		if (this.patientName != null && !this.patientName.isEmpty()) {
			element.patientName = this.patientName;
		}
		if (this.patientPhone != null && !this.patientPhone.isEmpty()) {
			element.patientPhone = this.patientPhone;
		}
		if (this.expectorDoctor != null && !this.expectorDoctor.isEmpty()) {
			element.expectorDoctor = this.expectorDoctor;
		}
		if (this.schedule != null) {
			element.schedule = this.schedule;
		}
		if (this.appointmentTime != null) {
			element.appointmentTime = this.appointmentTime;
		}
		if (this.setoutTime != null) {
			element.setoutTime = this.setoutTime;
		}
		if (this.startServiceTime != null) {
			element.startServiceTime = this.startServiceTime;
		}
		if (this.endServiceTime != null) {
			element.endServiceTime = this.endServiceTime;
		}
		if (this.remindTime != null) {
			element.remindTime = this.remindTime;
		}
		if (this.confirmTime != null) {
			element.confirmTime = this.confirmTime;
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
