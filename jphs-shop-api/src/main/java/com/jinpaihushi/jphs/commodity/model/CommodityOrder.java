package com.jinpaihushi.jphs.commodity.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_ORDER 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-10 18:58:20
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityOrder extends BaseModel implements Predicate<CommodityOrder>,
		Updator<CommodityOrder> {


    /**  */
	@Length(max = 255, message = "{commodityOrder.orderNo.illegal.length}")
	private String orderNo;

    /**  */
	private Double payPrice;

    /**  */
	private Date payTime;

    /** 发货时间 */
	private Date sendTime;

    /** 收货时间 */
	private Date takeTime;

    /** 完成时间 */
	private Date confirmTime;

    /** 提醒发货时间 */
	private Date remindTime;

    /**  */
	private Integer protectDay;

    /**  */
	private Date protectTime;

    /**  */
	@Length(max = 50, message = "{commodityOrder.voucherUseId.illegal.length}")
	private String voucherUseId;

    /** 优惠券金额 */
	private Double voucherPrice;

    /** 进度(0:待支付,1待发货,2运输中,3待评价,4已完成) */
	private Integer schedule;

    /** 省 */
	@Length(max = 255, message = "{commodityOrder.province.illegal.length}")
	private String address;

    /** 详细地址 */
	@Length(max = 255, message = "{commodityOrder.detailAddress.illegal.length}")
	private String detailAddress;

	private List<CommodityOrderInfo> coiList;
	
	private Integer count;
	
	private Double payment;
	
	private String phone;
	
	private String receiveName;
	
	private Integer device;
	
	private String platformId;
	
	private CommodityImages commodityImages;
	
	private String logisticsName;
	
	private String no;
	
	private String title;
	
	private Date beginTime;
	
	private Date stopTime;
	
	private Integer flag;
	
	private Integer crStatus;
	
	public CommodityOrder(){}

	public CommodityOrder(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getOrderNo() {
    	return orderNo;
    }
  	
	/**
	 * 设置
	 */
	public void setOrderNo(String orderNo) {
    	this.orderNo = orderNo;
    }

	/**
	 * 获取
	 */
	public Double getPayPrice() {
    	return payPrice;
    }
  	
	/**
	 * 设置
	 */
	public void setPayPrice(Double payPrice) {
    	this.payPrice = payPrice;
    }

	/**
	 * 获取
	 */
	public Date getPayTime() {
    	return payTime;
    }
  	
	/**
	 * 设置
	 */
	public void setPayTime(Date payTime) {
    	this.payTime = payTime;
    }

	/**
	 * 获取发货时间
	 */
	public Date getSendTime() {
    	return sendTime;
    }
  	
	/**
	 * 设置发货时间
	 */
	public void setSendTime(Date sendTime) {
    	this.sendTime = sendTime;
    }

	/**
	 * 获取收货时间
	 */
	public Date getTakeTime() {
    	return takeTime;
    }
  	
	/**
	 * 设置收货时间
	 */
	public void setTakeTime(Date takeTime) {
    	this.takeTime = takeTime;
    }

	/**
	 * 获取完成时间
	 */
	public Date getConfirmTime() {
    	return confirmTime;
    }
  	
	/**
	 * 设置完成时间
	 */
	public void setConfirmTime(Date confirmTime) {
    	this.confirmTime = confirmTime;
    }

	/**
	 * 获取提醒发货时间
	 */
	public Date getRemindTime() {
    	return remindTime;
    }
  	
	/**
	 * 设置提醒发货时间
	 */
	public void setRemindTime(Date remindTime) {
    	this.remindTime = remindTime;
    }

	/**
	 * 获取
	 */
	public Integer getProtectDay() {
    	return protectDay;
    }
  	
	/**
	 * 设置
	 */
	public void setProtectDay(Integer protectDay) {
    	this.protectDay = protectDay;
    }

	/**
	 * 获取
	 */
	public Date getProtectTime() {
    	return protectTime;
    }
  	
	/**
	 * 设置
	 */
	public void setProtectTime(Date protectTime) {
    	this.protectTime = protectTime;
    }

	/**
	 * 获取
	 */
	public String getVoucherUseId() {
    	return voucherUseId;
    }
  	
	/**
	 * 设置
	 */
	public void setVoucherUseId(String voucherUseId) {
    	this.voucherUseId = voucherUseId;
    }

	/**
	 * 获取优惠券金额
	 */
	public Double getVoucherPrice() {
    	return voucherPrice;
    }
  	
	/**
	 * 设置优惠券金额
	 */
	public void setVoucherPrice(Double voucherPrice) {
    	this.voucherPrice = voucherPrice;
    }

	/**
	 * 获取进度(0:待支付,1待发货,2运输中,3待评价,4已完成)
	 */
	public Integer getSchedule() {
    	return schedule;
    }
  	
	/**
	 * 设置进度(0:待支付,1待发货,2运输中,3待评价,4已完成)
	 */
	public void setSchedule(Integer schedule) {
    	this.schedule = schedule;
    }

	/**
	 * 获取省
	 */
	public String getAddress() {
		return address;
	}
  	
	/**
	 * 设置省
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

	
	
    public List<CommodityOrderInfo> getCoiList() {
		return coiList;
	}

	public void setCoiList(List<CommodityOrderInfo> coiList) {
		this.coiList = coiList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	

/*	public String getReceivename() {
		return receivename;
	}

	public void setReceivename(String receivename) {
		this.receivename = receivename;
	}*/

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	
	
	public CommodityImages getCommodityImages() {
		return commodityImages;
	}

	public void setCommodityImages(CommodityImages commodityImages) {
		this.commodityImages = commodityImages;
	}

	 
	
    public Integer getDevice() {
		return device;
	}

	public void setDevice(Integer device) {
		this.device = device;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	
	
	public Integer getCrStatus() {
		return crStatus;
	}

	public void setCrStatus(Integer crStatus) {
		this.crStatus = crStatus;
	}

	@Override
	public String toString() {
		return "CommodityOrder [orderNo=" + orderNo + ", payPrice=" + payPrice + ", payTime=" + payTime + ", sendTime="
				+ sendTime + ", takeTime=" + takeTime + ", confirmTime=" + confirmTime + ", remindTime=" + remindTime
				+ ", protectDay=" + protectDay + ", protectTime=" + protectTime + ", voucherUseId=" + voucherUseId
				+ ", voucherPrice=" + voucherPrice + ", schedule=" + schedule + ", address=" + address
				+ ", detailAddress=" + detailAddress + ", coiList=" + coiList + ", count=" + count + ", payment="
				+ payment + ", phone=" + phone + ", receiveName=" + receiveName + ", commodityImages=" + commodityImages
				+ "]";
	}

	/**
	 * 复制字段：
	 * id, orderNo, payPrice, payTime, 
	 * sendTime, takeTime, confirmTime, remindTime, 
	 * protectDay, protectTime, voucherUseId, voucherPrice, 
	 * schedule, status, createTime, creatorId, 
	 * creatorName, province, detailAddress
	 */
	public CommodityOrder copy(){
		CommodityOrder commodityOrder = new CommodityOrder();
     	commodityOrder.id = this.id;
     	commodityOrder.orderNo = this.orderNo;
     	commodityOrder.payPrice = this.payPrice;
     	commodityOrder.payTime = this.payTime;
     	commodityOrder.sendTime = this.sendTime;
     	commodityOrder.takeTime = this.takeTime;
     	commodityOrder.confirmTime = this.confirmTime;
     	commodityOrder.remindTime = this.remindTime;
     	commodityOrder.protectDay = this.protectDay;
     	commodityOrder.protectTime = this.protectTime;
     	commodityOrder.voucherUseId = this.voucherUseId;
     	commodityOrder.voucherPrice = this.voucherPrice;
     	commodityOrder.schedule = this.schedule;
     	commodityOrder.status = this.status;
     	commodityOrder.createTime = this.createTime;
     	commodityOrder.creatorId = this.creatorId;
     	commodityOrder.creatorName = this.creatorName;
     	commodityOrder.address = this.address;
     	commodityOrder.detailAddress = this.detailAddress;
		return commodityOrder;
	}
	
	/**
	 * 比较字段：
	 * id, orderNo, payPrice, payTime, 
	 * sendTime, takeTime, confirmTime, remindTime, 
	 * protectDay, protectTime, voucherUseId, voucherPrice, 
	 * schedule, status, createTime, creatorId, 
	 * creatorName, province, detailAddress
	 */
	@Override
	public boolean test(CommodityOrder t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.orderNo == null || this.orderNo.equals(t.orderNo))
				&& (this.payPrice == null || this.payPrice.equals(t.payPrice))
				&& (this.payTime == null || this.payTime.equals(t.payTime))
				&& (this.sendTime == null || this.sendTime.equals(t.sendTime))
				&& (this.takeTime == null || this.takeTime.equals(t.takeTime))
				&& (this.confirmTime == null || this.confirmTime.equals(t.confirmTime))
				&& (this.remindTime == null || this.remindTime.equals(t.remindTime))
				&& (this.protectDay == null || this.protectDay.equals(t.protectDay))
				&& (this.protectTime == null || this.protectTime.equals(t.protectTime))
				&& (this.voucherUseId == null || this.voucherUseId.equals(t.voucherUseId))
				&& (this.voucherPrice == null || this.voucherPrice.equals(t.voucherPrice))
				&& (this.schedule == null || this.schedule.equals(t.schedule))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.address == null || this.address.equals(t.address))
				&& (this.detailAddress == null || this.detailAddress.equals(t.detailAddress))
		;
	}
	
	@Override
	public void update(CommodityOrder element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.orderNo != null && !this.orderNo.isEmpty()) {
			element.orderNo = this.orderNo;
		}
		if (this.payPrice != null) {
			element.payPrice = this.payPrice;
		}
		if (this.payTime != null) {
			element.payTime = this.payTime;
		}
		if (this.sendTime != null) {
			element.sendTime = this.sendTime;
		}
		if (this.takeTime != null) {
			element.takeTime = this.takeTime;
		}
		if (this.confirmTime != null) {
			element.confirmTime = this.confirmTime;
		}
		if (this.remindTime != null) {
			element.remindTime = this.remindTime;
		}
		if (this.protectDay != null) {
			element.protectDay = this.protectDay;
		}
		if (this.protectTime != null) {
			element.protectTime = this.protectTime;
		}
		if (this.voucherUseId != null && !this.voucherUseId.isEmpty()) {
			element.voucherUseId = this.voucherUseId;
		}
		if (this.voucherPrice != null) {
			element.voucherPrice = this.voucherPrice;
		}
		if (this.schedule != null) {
			element.schedule = this.schedule;
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
		if (this.address != null && !this.address.isEmpty()) {
			element.address = this.address;
		}
		if (this.detailAddress != null && !this.detailAddress.isEmpty()) {
			element.detailAddress = this.detailAddress;
		}
	}
}
