package com.jinpaihushi.jphs.voucher.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.model.BaseModel;

/**
 * VOUCHER 继承自父类的字段: id : type : 类型（现金卷、满减卷、折扣卷） creatorId : creatorName :
 * createTime : status :
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Voucher extends BaseModel implements Predicate<Voucher>, Updator<Voucher> {

	/** 批次号 */
	@Length(max = 50, message = "{voucher.batchNo.illegal.length}")
	private String batchNo;

	/** 产品id */
	@Length(max = 50, message = "{voucher.productId.illegal.length}")
	private String productId;

	/** 商品id */
	@Length(max = 50, message = "{voucher.goodsId.illegal.length}")
	private String goodsId;

	/** 兑换开始时间 */
	private Date startTime;

	/** 兑换结束时间 */
	private Date endTime;

	/** 兑换开始时间 */
	private Date activationStartTime;

	/** 兑换结束时间 */
	private Date activationEndTime;
	
	
	/** 激活后有效时长 */
	private Integer days;

	private String productName;

	/** 金额 */
	private Double amount;

	private Date beginTime;

	private Date stopTime;

	/** 满xx减 */
	private Double conditionAmount;

	/** 满xx折 */
	private Double discountAmount;
	
	private String goodsName;
	
	private VoucherRepertory voucherRepertory;
	private VoucherUse voucherUse;
	public Voucher() {
	}

	public Voucher(String id) {
		this.id = id;
	}

	/**
	 * 获取批次号
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * 设置批次号
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * 获取产品id
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置产品id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取商品id
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置商品id
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 获取兑换开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置兑换开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取兑换结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置兑换结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取激活后有效时长
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * 设置激活后有效时长
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Double getConditionAmount() {
		return conditionAmount;
	}

	public void setConditionAmount(Double conditionAmount) {
		this.conditionAmount = conditionAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	
	public VoucherRepertory getVoucherRepertory() {
		return voucherRepertory;
	}

	public void setVoucherRepertory(VoucherRepertory voucherRepertory) {
		this.voucherRepertory = voucherRepertory;
	}

	public VoucherUse getVoucherUse() {
		return voucherUse;
	}

	public void setVoucherUse(VoucherUse voucherUse) {
		this.voucherUse = voucherUse;
	}

	public Date getActivationStartTime() {
		return activationStartTime;
	}

	public void setActivationStartTime(Date activationStartTime) {
		this.activationStartTime = activationStartTime;
	}

	public Date getActivationEndTime() {
		return activationEndTime;
	}

	public void setActivationEndTime(Date activationEndTime) {
		this.activationEndTime = activationEndTime;
	}

	public String toString() {
		return new StringBuilder().append("Voucher{").append("id=").append(id).append(",type=").append(type)
				.append(",batchNo=").append(batchNo).append(",productId=").append(productId).append(",goodsId=")
				.append(goodsId).append(",startTime=").append(startTime).append(",endTime=").append(endTime)
				.append(",days=").append(days).append(",creatorId=").append(creatorId).append(",creatorName=")
				.append(creatorName).append(",createTime=").append(createTime).append(",status=").append(status)
				.append('}').toString();
	}

	/**
	 * 复制字段： id, type, batchNo, productId, goodsId, startTime, endTime, days,
	 * creatorId, creatorName, createTime, status
	 */
	public Voucher copy() {
		Voucher voucher = new Voucher();
		voucher.id = this.id;
		voucher.type = this.type;
		voucher.batchNo = this.batchNo;
		voucher.productId = this.productId;
		voucher.goodsId = this.goodsId;
		voucher.startTime = this.startTime;
		voucher.endTime = this.endTime;
		voucher.days = this.days;
		voucher.creatorId = this.creatorId;
		voucher.creatorName = this.creatorName;
		voucher.createTime = this.createTime;
		voucher.status = this.status;
		return voucher;
	}

	/**
	 * 比较字段： id, type, batchNo, productId, goodsId, startTime, endTime, days,
	 * creatorId, creatorName, createTime, status
	 */
	@Override
	public boolean test(Voucher t) {
		if (t == null)
			return false;
		return (this.id == null || this.id.equals(t.id)) && (this.type == null || this.type.equals(t.type))
				&& (this.batchNo == null || this.batchNo.equals(t.batchNo))
				&& (this.productId == null || this.productId.equals(t.productId))
				&& (this.goodsId == null || this.goodsId.equals(t.goodsId))
				&& (this.startTime == null || this.startTime.equals(t.startTime))
				&& (this.endTime == null || this.endTime.equals(t.endTime))
				&& (this.days == null || this.days.equals(t.days))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status));
	}

	@Override
	public void update(Voucher element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.batchNo != null && !this.batchNo.isEmpty()) {
			element.batchNo = this.batchNo;
		}
		if (this.productId != null && !this.productId.isEmpty()) {
			element.productId = this.productId;
		}
		if (this.goodsId != null && !this.goodsId.isEmpty()) {
			element.goodsId = this.goodsId;
		}
		if (this.startTime != null) {
			element.startTime = this.startTime;
		}
		if (this.endTime != null) {
			element.endTime = this.endTime;
		}
		if (this.days != null) {
			element.days = this.days;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
