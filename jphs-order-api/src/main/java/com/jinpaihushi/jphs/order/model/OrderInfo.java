package com.jinpaihushi.jphs.order.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.jinpaihushi.model.BaseModel;

@SuppressWarnings("serial")
public class OrderInfo extends BaseModel {
	/** 优惠券价格 */
	private Double voucherPrice = 0.0;

	/** 优惠券id */
	@Length(max = 50, message = "{order.voucherUseId.illegal.length}")
	private String voucherUseId = "";

	/** 平台id */
	@Length(max = 50, message = "{order.platformId.illegal.length}")
	private String platformId;

	/** 预约时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date appointmentTime;

	/** 进度 */
	private Integer schedule = 0;

	/** 下单设备/来源 */
	private Integer device;

	/** 科室id */
	@Length(max = 500, message = "{orderOther.departmentId.illegal.length}")
	private String departmentId = "";

	/** 医院 */
	@Length(max = 255, message = "{orderOther.hospital.illegal.length}")
	private String hospital = "";

	/** 地址-省 */
	@Length(max = 50, message = "{orderOther.address.illegal.length}")
	private String address;

	/** 详细地址 */
	@Length(max = 500, message = "{orderOther.detailAddress.illegal.length}")
	private String detailAddress;

	/** 是否有药品 */
	@Length(max = 50, message = "{orderOther.drug.illegal.length}")
	private String drug = "";

	/** 是否有工具 */
	@Length(max = 50, message = "{orderOther.tool.illegal.length}")
	private String tool = "";

	/** 备注 */
	@Length(max = 500, message = "{order.remarks.illegal.length}")
	private String remarks;

	/** 单次价格 */
	private Double onePrice;

	/** 销售价价格 */
	private Double price;

	/** 患者姓名 */
	@Length(max = 50, message = "{orderService.patientName.illegal.length}")
	private String patientName;

	/** 患者电话 */
	@Length(max = 50, message = "{orderService.patientPhone.illegal.length}")
	private String patientPhone;

	/** 指定人 */
	@Length(max = 50, message = "{order.expectorId.illegal.length}")
	private String expectorId = "";

	/** 指定医生 */
	@Length(max = 50, message = "{orderService.expectorDoctor.illegal.length}")
	private String expectorDoctor = "";

	/** 实付金额 */
	private Double payPrice;

	/** 利润 */
	private Double profit;

	private String title;

	private String goodsId;

	private String productId;

	private String order_img;

	private Integer serviceNumber;

	// 受保人
	private String name;

	// 身份证号
	private String sfz;

	private String pricePartId;

	/** 是否需要上保险 */
	private Integer insurance;

	@Length(max = 500, message = "{order.remarks.illegal.length}")
	private String remark;

	/** 推荐人id */
	@Length(max = 50, message = "{user.recommendId.illegal.length}")
	private String recommendId = "";
	/** 是否需要上传证明 1三张就医证明 2一张 3不需要 */
	private Integer isProve;
	/** 图片地址*/
	private String images="";

	public Double getVoucherPrice() {
		return voucherPrice;
	}

	public void setVoucherPrice(Double voucherPrice) {
		this.voucherPrice = voucherPrice;
	}

	public String getVoucherUseId() {
		return voucherUseId;
	}

	public void setVoucherUseId(String voucherUseId) {
		this.voucherUseId = voucherUseId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getExpectorId() {
		return expectorId;
	}

	public void setExpectorId(String expectorId) {
		this.expectorId = expectorId;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Integer getSchedule() {
		return schedule;
	}

	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}

	public Integer getDevice() {
		return device;
	}

	public void setDevice(Integer device) {
		this.device = device;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getOnePrice() {
		return onePrice;
	}

	public void setOnePrice(Double onePrice) {
		this.onePrice = onePrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getExpectorDoctor() {
		return expectorDoctor;
	}

	public void setExpectorDoctor(String expectorDoctor) {
		this.expectorDoctor = expectorDoctor;
	}

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrder_img() {
		return order_img;
	}

	public void setOrder_img(String order_img) {
		this.order_img = order_img;
	}

	public Integer getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(Integer serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPricePartId() {
		return pricePartId;
	}

	public void setPricePartId(String pricePartId) {
		this.pricePartId = pricePartId;
	}

	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public Integer getIsProve() {
		return isProve;
	}

	public void setIsProve(Integer isProve) {
		this.isProve = isProve;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
}
