package com.jinpaihushi.jphs.order.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.jinpaihushi.model.BaseModel;

public class OrderPojo extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 优惠券id */
    @Length(max = 50, message = "{order.voucherUseId.illegal.length}")
    private String voucherUseId = "";

    /** 平台id */
    @Length(max = 50, message = "{order.platformId.illegal.length}")
    private String platformId;

    /** 预约时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentTime;

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
    private String addressId;

    /** 备注 */
    @Length(max = 500, message = "{order.remarks.illegal.length}")
    private String remarks;

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

    private String goodsId;

    // 受保人
    private String name;

    // 身份证号
    private String sfz;

    private String pricePartId;

    /** 图片地址*/
    private String images = "";

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

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getExpectorId() {
        return expectorId;
    }

    public void setExpectorId(String expectorId) {
        this.expectorId = expectorId;
    }

    public String getExpectorDoctor() {
        return expectorDoctor;
    }

    public void setExpectorDoctor(String expectorDoctor) {
        this.expectorDoctor = expectorDoctor;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public String getPricePartId() {
        return pricePartId;
    }

    public void setPricePartId(String pricePartId) {
        this.pricePartId = pricePartId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

}
