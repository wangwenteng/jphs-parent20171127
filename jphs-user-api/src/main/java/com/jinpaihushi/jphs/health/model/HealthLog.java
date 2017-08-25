package com.jinpaihushi.jphs.health.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * HEALTH_LOG 
 * 继承自父类的字段:
 * id : id	
 * type : 1=陪诊，2=康复	
 * creatorName : 创建人	
 * creatorId : 创建人ID	
 * createTime : 创建时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author yangsong
 * @date 2017-07-03 11:18:42
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class HealthLog extends BaseModel implements Predicate<HealthLog>, Updator<HealthLog> {

    /** 会员id */
    @Length(max = 50, message = "{healthLog.userId.illegal.length}")
    private String userId;

    /** 订单id */
    @Length(max = 50, message = "{healthLog.orderId.illegal.length}")
    private String orderId;

    /** 康复建议 */
    @Length(max = 500, message = "{healthLog.advise.illegal.length}")
    private String advise;

    /** 评估内容 */
    @Length(max = 500, message = "{healthLog.evaluateContent.illegal.length}")
    private String evaluateContent;

    /** 疾病原因 */
    @Length(max = 255, message = "{healthLog.diseaseCauses.illegal.length}")
    private String diseaseCauses;

    /** 身体症状 */
    @Length(max = 255, message = "{healthLog.physicalSymptoms.illegal.length}")
    private String physicalSymptoms;

    /** 现病史 */
    @Length(max = 255, message = "{healthLog.presentIllness.illegal.length}")
    private String presentIllness;

    /** 疾病史 */
    @Length(max = 255, message = "{healthLog.diseasesHistory.illegal.length}")
    private String diseasesHistory;

    /** 用药提醒 */
    @Length(max = 255, message = "{healthLog.medicationRemind.illegal.length}")
    private String medicationRemind;

    /** 复诊时间 */
    private Date appointmentTime;

    /** 注意事项 */
    @Length(max = 500, message = "{healthLog.notes.illegal.length}")
    private String notes;

    public HealthLog() {
    }

    public HealthLog(String id) {
        this.id = id;
    }

    /**
     * 获取会员id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置会员id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取康复建议
     */
    public String getAdvise() {
        return advise;
    }

    /**
     * 设置康复建议
     */
    public void setAdvise(String advise) {
        this.advise = advise;
    }

    /**
     * 获取评估内容
     */
    public String getEvaluateContent() {
        return evaluateContent;
    }

    /**
     * 设置评估内容
     */
    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    /**
     * 获取疾病原因
     */
    public String getDiseaseCauses() {
        return diseaseCauses;
    }

    /**
     * 设置疾病原因
     */
    public void setDiseaseCauses(String diseaseCauses) {
        this.diseaseCauses = diseaseCauses;
    }

    /**
     * 获取身体症状
     */
    public String getPhysicalSymptoms() {
        return physicalSymptoms;
    }

    /**
     * 设置身体症状
     */
    public void setPhysicalSymptoms(String physicalSymptoms) {
        this.physicalSymptoms = physicalSymptoms;
    }

    /**
     * 获取现病史
     */
    public String getPresentIllness() {
        return presentIllness;
    }

    /**
     * 设置现病史
     */
    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

    /**
     * 获取疾病史
     */
    public String getDiseasesHistory() {
        return diseasesHistory;
    }

    /**
     * 设置疾病史
     */
    public void setDiseasesHistory(String diseasesHistory) {
        this.diseasesHistory = diseasesHistory;
    }

    /**
     * 获取用药提醒
     */
    public String getMedicationRemind() {
        return medicationRemind;
    }

    /**
     * 设置用药提醒
     */
    public void setMedicationRemind(String medicationRemind) {
        this.medicationRemind = medicationRemind;
    }

    /**
     * 获取复诊时间
     */
    public Date getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * 设置复诊时间
     */
    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * 获取注意事项
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 设置注意事项
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String toString() {
        return new StringBuilder().append("HealthLog{").append("id=").append(id).append(",type=").append(type)
                .append(",userId=").append(userId).append(",orderId=").append(orderId).append(",advise=").append(advise)
                .append(",evaluateContent=").append(evaluateContent).append(",diseaseCauses=").append(diseaseCauses)
                .append(",physicalSymptoms=").append(physicalSymptoms).append(",presentIllness=").append(presentIllness)
                .append(",diseasesHistory=").append(diseasesHistory).append(",medicationRemind=")
                .append(medicationRemind).append(",appointmentTime=").append(appointmentTime).append(",notes=")
                .append(notes).append(",creatorName=").append(creatorName).append(",creatorId=").append(creatorId)
                .append(",createTime=").append(createTime).append(",status=").append(status).append('}').toString();
    }

    /**
     * 复制字段：
     * id, type, userId, orderId, 
     * advise, evaluateContent, diseaseCauses, physicalSymptoms, 
     * presentIllness, diseasesHistory, medicationRemind, appointmentTime, 
     * notes, creatorName, creatorId, createTime, 
     * status
     */
    public HealthLog copy() {
        HealthLog healthLog = new HealthLog();
        healthLog.id = this.id;
        healthLog.type = this.type;
        healthLog.userId = this.userId;
        healthLog.orderId = this.orderId;
        healthLog.advise = this.advise;
        healthLog.evaluateContent = this.evaluateContent;
        healthLog.diseaseCauses = this.diseaseCauses;
        healthLog.physicalSymptoms = this.physicalSymptoms;
        healthLog.presentIllness = this.presentIllness;
        healthLog.diseasesHistory = this.diseasesHistory;
        healthLog.medicationRemind = this.medicationRemind;
        healthLog.appointmentTime = this.appointmentTime;
        healthLog.notes = this.notes;
        healthLog.creatorName = this.creatorName;
        healthLog.creatorId = this.creatorId;
        healthLog.createTime = this.createTime;
        healthLog.status = this.status;
        return healthLog;
    }

    /**
     * 比较字段：
     * id, type, userId, orderId, 
     * advise, evaluateContent, diseaseCauses, physicalSymptoms, 
     * presentIllness, diseasesHistory, medicationRemind, appointmentTime, 
     * notes, creatorName, creatorId, createTime, 
     * status
     */
    @Override
    public boolean test(HealthLog t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.type == null || this.type.equals(t.type))
                && (this.userId == null || this.userId.equals(t.userId))
                && (this.orderId == null || this.orderId.equals(t.orderId))
                && (this.advise == null || this.advise.equals(t.advise))
                && (this.evaluateContent == null || this.evaluateContent.equals(t.evaluateContent))
                && (this.diseaseCauses == null || this.diseaseCauses.equals(t.diseaseCauses))
                && (this.physicalSymptoms == null || this.physicalSymptoms.equals(t.physicalSymptoms))
                && (this.presentIllness == null || this.presentIllness.equals(t.presentIllness))
                && (this.diseasesHistory == null || this.diseasesHistory.equals(t.diseasesHistory))
                && (this.medicationRemind == null || this.medicationRemind.equals(t.medicationRemind))
                && (this.appointmentTime == null || this.appointmentTime.equals(t.appointmentTime))
                && (this.notes == null || this.notes.equals(t.notes))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.status == null || this.status.equals(t.status));
    }

    @Override
    public void update(HealthLog element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.type != null) {
            element.type = this.type;
        }
        if (this.userId != null && !this.userId.isEmpty()) {
            element.userId = this.userId;
        }
        if (this.orderId != null && !this.orderId.isEmpty()) {
            element.orderId = this.orderId;
        }
        if (this.advise != null && !this.advise.isEmpty()) {
            element.advise = this.advise;
        }
        if (this.evaluateContent != null && !this.evaluateContent.isEmpty()) {
            element.evaluateContent = this.evaluateContent;
        }
        if (this.diseaseCauses != null && !this.diseaseCauses.isEmpty()) {
            element.diseaseCauses = this.diseaseCauses;
        }
        if (this.physicalSymptoms != null && !this.physicalSymptoms.isEmpty()) {
            element.physicalSymptoms = this.physicalSymptoms;
        }
        if (this.presentIllness != null && !this.presentIllness.isEmpty()) {
            element.presentIllness = this.presentIllness;
        }
        if (this.diseasesHistory != null && !this.diseasesHistory.isEmpty()) {
            element.diseasesHistory = this.diseasesHistory;
        }
        if (this.medicationRemind != null && !this.medicationRemind.isEmpty()) {
            element.medicationRemind = this.medicationRemind;
        }
        if (this.appointmentTime != null) {
            element.appointmentTime = this.appointmentTime;
        }
        if (this.notes != null && !this.notes.isEmpty()) {
            element.notes = this.notes;
        }
        if (this.creatorName != null && !this.creatorName.isEmpty()) {
            element.creatorName = this.creatorName;
        }
        if (this.creatorId != null && !this.creatorId.isEmpty()) {
            element.creatorId = this.creatorId;
        }
        if (this.createTime != null) {
            element.createTime = this.createTime;
        }
        if (this.status != null) {
            element.status = this.status;
        }
    }
}
