package com.jinpaihushi.jphs.tijian.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * TIJIAN_HEALTH_LOG 
 * 继承自父类的字段:
 * id : 体检记录id	
 * creatorName : 创建人	
 * creatorId : 创建人ID	
 * createTime : 创建时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author wangwt
 * @date 2017-08-15 10:36:45
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TijianHealthLog extends BaseModel implements Predicate<TijianHealthLog>, Updator<TijianHealthLog> {

    /** 会员id */
    @Length(max = 50, message = "{tijianHealthLog.userId.illegal.length}")
    private String userId;

    /** 订单id */
    @Length(max = 50, message = "{tijianHealthLog.orderId.illegal.length}")
    private String orderId;

    /** 会员姓名 */
    @Length(max = 50, message = "{tijianHealthLog.name.illegal.length}")
    private String name;

    /** 会员电话 */
    @Length(max = 50, message = "{tijianHealthLog.phone.illegal.length}")
    private String phone;

    /** 体检类型id */
    @Length(max = 50, message = "{tijianHealthLog.tijianTypeId.illegal.length}")
    private String tijianTypeId;

    /** 头部 */
    @Length(max = 255, message = "{tijianHealthLog.head.illegal.length}")
    private String head;

    /** 颈部 */
    @Length(max = 255, message = "{tijianHealthLog.neck.illegal.length}")
    private String neck;

    /** 手臂 */
    @Length(max = 255, message = "{tijianHealthLog.arm.illegal.length}")
    private String arm;

    /** 腿部 */
    @Length(max = 255, message = "{tijianHealthLog.leg.illegal.length}")
    private String leg;

    /** 手 */
    @Length(max = 255, message = "{tijianHealthLog.hand.illegal.length}")
    private String hand;

    /** 脚 */
    @Length(max = 255, message = "{tijianHealthLog.foot.illegal.length}")
    private String foot;

    /** 心脏 */
    @Length(max = 255, message = "{tijianHealthLog.heart.illegal.length}")
    private String heart;

    /** 肝脏 */
    @Length(max = 255, message = "{tijianHealthLog.liver.illegal.length}")
    private String liver;

    /** 肺部 */
    @Length(max = 255, message = "{tijianHealthLog.lungs.illegal.length}")
    private String lungs;

    /** 胃 */
    @Length(max = 255, message = "{tijianHealthLog.stomach.illegal.length}")
    private String stomach;

    /** 胆 */
    @Length(max = 255, message = "{tijianHealthLog.gallbladder.illegal.length}")
    private String gallbladder;

    /** 肾 */
    @Length(max = 255, message = "{tijianHealthLog.kidney.illegal.length}")
    private String kidney;

    /** 男性生殖系统 */
    @Length(max = 255, message = "{tijianHealthLog.reproductivesystemMan.illegal.length}")
    private String reproductivesystemMan;

    /** 女性生殖系统 */
    @Length(max = 255, message = "{tijianHealthLog.reproductivesystemWoman.illegal.length}")
    private String reproductivesystemWoman;

    /** 图片 */
    @Length(max = 255, message = "{tijianHealthLog.image.illegal.length}")
    private String image;

    /** 建议 */
    @Length(max = 255, message = "{tijianHealthLog.proposal.illegal.length}")
    private String proposal;

    /** 体检时间 */
    private Date healthTime;

    /** 注意事项 */
    @Length(max = 255, message = "{tijianHealthLog.notes.illegal.length}")
    private String notes;

    public TijianHealthLog() {
    }

    public TijianHealthLog(String id) {
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
     * 获取会员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置会员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取会员电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置会员电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取体检类型id
     */
    public String getTijianTypeId() {
        return tijianTypeId;
    }

    /**
     * 设置体检类型id
     */
    public void setTijianTypeId(String tijianTypeId) {
        this.tijianTypeId = tijianTypeId;
    }

    /**
     * 获取头部
     */
    public String getHead() {
        return head;
    }

    /**
     * 设置头部
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 获取颈部
     */
    public String getNeck() {
        return neck;
    }

    /**
     * 设置颈部
     */
    public void setNeck(String neck) {
        this.neck = neck;
    }

    /**
     * 获取手臂
     */
    public String getArm() {
        return arm;
    }

    /**
     * 设置手臂
     */
    public void setArm(String arm) {
        this.arm = arm;
    }

    /**
     * 获取腿部
     */
    public String getLeg() {
        return leg;
    }

    /**
     * 设置腿部
     */
    public void setLeg(String leg) {
        this.leg = leg;
    }

    /**
     * 获取手
     */
    public String getHand() {
        return hand;
    }

    /**
     * 设置手
     */
    public void setHand(String hand) {
        this.hand = hand;
    }

    /**
     * 获取脚
     */
    public String getFoot() {
        return foot;
    }

    /**
     * 设置脚
     */
    public void setFoot(String foot) {
        this.foot = foot;
    }

    /**
     * 获取心脏
     */
    public String getHeart() {
        return heart;
    }

    /**
     * 设置心脏
     */
    public void setHeart(String heart) {
        this.heart = heart;
    }

    /**
     * 获取肝脏
     */
    public String getLiver() {
        return liver;
    }

    /**
     * 设置肝脏
     */
    public void setLiver(String liver) {
        this.liver = liver;
    }

    /**
     * 获取肺部
     */
    public String getLungs() {
        return lungs;
    }

    /**
     * 设置肺部
     */
    public void setLungs(String lungs) {
        this.lungs = lungs;
    }

    /**
     * 获取胃
     */
    public String getStomach() {
        return stomach;
    }

    /**
     * 设置胃
     */
    public void setStomach(String stomach) {
        this.stomach = stomach;
    }

    /**
     * 获取胆
     */
    public String getGallbladder() {
        return gallbladder;
    }

    /**
     * 设置胆
     */
    public void setGallbladder(String gallbladder) {
        this.gallbladder = gallbladder;
    }

    /**
     * 获取肾
     */
    public String getKidney() {
        return kidney;
    }

    /**
     * 设置肾
     */
    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    /**
     * 获取男性生殖系统
     */
    public String getReproductivesystemMan() {
        return reproductivesystemMan;
    }

    /**
     * 设置男性生殖系统
     */
    public void setReproductivesystemMan(String reproductivesystemMan) {
        this.reproductivesystemMan = reproductivesystemMan;
    }

    /**
     * 获取女性生殖系统
     */
    public String getReproductivesystemWoman() {
        return reproductivesystemWoman;
    }

    /**
     * 设置女性生殖系统
     */
    public void setReproductivesystemWoman(String reproductivesystemWoman) {
        this.reproductivesystemWoman = reproductivesystemWoman;
    }

    /**
     * 获取图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取建议
     */
    public String getProposal() {
        return proposal;
    }

    /**
     * 设置建议
     */
    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    /**
     * 获取体检时间
     */
    public Date getHealthTime() {
        return healthTime;
    }

    /**
     * 设置体检时间
     */
    public void setHealthTime(Date healthTime) {
        this.healthTime = healthTime;
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
        return new StringBuilder().append("TijianHealthLog{").append("id=").append(id).append(",userId=").append(userId)
                .append(",orderId=").append(orderId).append(",name=").append(name).append(",phone=").append(phone)
                .append(",tijianTypeId=").append(tijianTypeId).append(",head=").append(head).append(",neck=")
                .append(neck).append(",arm=").append(arm).append(",leg=").append(leg).append(",hand=").append(hand)
                .append(",foot=").append(foot).append(",heart=").append(heart).append(",liver=").append(liver)
                .append(",lungs=").append(lungs).append(",stomach=").append(stomach).append(",gallbladder=")
                .append(gallbladder).append(",kidney=").append(kidney).append(",reproductivesystemMan=")
                .append(reproductivesystemMan).append(",reproductivesystemWoman=").append(reproductivesystemWoman)
                .append(",image=").append(image).append(",proposal=").append(proposal).append(",healthTime=")
                .append(healthTime).append(",notes=").append(notes).append(",creatorName=").append(creatorName)
                .append(",creatorId=").append(creatorId).append(",createTime=").append(createTime).append(",status=")
                .append(status).append('}').toString();
    }

    /**
     * 复制字段：
     * id, userId, orderId, name, 
     * phone, tijianTypeId, head, neck, 
     * arm, leg, hand, foot, 
     * heart, liver, lungs, stomach, 
     * gallbladder, kidney, reproductivesystemMan, reproductivesystemWoman, 
     * image, proposal, healthTime, notes, 
     * creatorName, creatorId, createTime, status
     */
    public TijianHealthLog copy() {
        TijianHealthLog tijianHealthLog = new TijianHealthLog();
        tijianHealthLog.id = this.id;
        tijianHealthLog.userId = this.userId;
        tijianHealthLog.orderId = this.orderId;
        tijianHealthLog.name = this.name;
        tijianHealthLog.phone = this.phone;
        tijianHealthLog.tijianTypeId = this.tijianTypeId;
        tijianHealthLog.head = this.head;
        tijianHealthLog.neck = this.neck;
        tijianHealthLog.arm = this.arm;
        tijianHealthLog.leg = this.leg;
        tijianHealthLog.hand = this.hand;
        tijianHealthLog.foot = this.foot;
        tijianHealthLog.heart = this.heart;
        tijianHealthLog.liver = this.liver;
        tijianHealthLog.lungs = this.lungs;
        tijianHealthLog.stomach = this.stomach;
        tijianHealthLog.gallbladder = this.gallbladder;
        tijianHealthLog.kidney = this.kidney;
        tijianHealthLog.reproductivesystemMan = this.reproductivesystemMan;
        tijianHealthLog.reproductivesystemWoman = this.reproductivesystemWoman;
        tijianHealthLog.image = this.image;
        tijianHealthLog.proposal = this.proposal;
        tijianHealthLog.healthTime = this.healthTime;
        tijianHealthLog.notes = this.notes;
        tijianHealthLog.creatorName = this.creatorName;
        tijianHealthLog.creatorId = this.creatorId;
        tijianHealthLog.createTime = this.createTime;
        tijianHealthLog.status = this.status;
        return tijianHealthLog;
    }

    /**
     * 比较字段：
     * id, userId, orderId, name, 
     * phone, tijianTypeId, head, neck, 
     * arm, leg, hand, foot, 
     * heart, liver, lungs, stomach, 
     * gallbladder, kidney, reproductivesystemMan, reproductivesystemWoman, 
     * image, proposal, healthTime, notes, 
     * creatorName, creatorId, createTime, status
     */
    @Override
    public boolean test(TijianHealthLog t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.userId == null || this.userId.equals(t.userId))
                && (this.orderId == null || this.orderId.equals(t.orderId))
                && (this.name == null || this.name.equals(t.name)) && (this.phone == null || this.phone.equals(t.phone))
                && (this.tijianTypeId == null || this.tijianTypeId.equals(t.tijianTypeId))
                && (this.head == null || this.head.equals(t.head)) && (this.neck == null || this.neck.equals(t.neck))
                && (this.arm == null || this.arm.equals(t.arm)) && (this.leg == null || this.leg.equals(t.leg))
                && (this.hand == null || this.hand.equals(t.hand)) && (this.foot == null || this.foot.equals(t.foot))
                && (this.heart == null || this.heart.equals(t.heart))
                && (this.liver == null || this.liver.equals(t.liver))
                && (this.lungs == null || this.lungs.equals(t.lungs))
                && (this.stomach == null || this.stomach.equals(t.stomach))
                && (this.gallbladder == null || this.gallbladder.equals(t.gallbladder))
                && (this.kidney == null || this.kidney.equals(t.kidney))
                && (this.reproductivesystemMan == null || this.reproductivesystemMan.equals(t.reproductivesystemMan))
                && (this.reproductivesystemWoman == null
                        || this.reproductivesystemWoman.equals(t.reproductivesystemWoman))
                && (this.image == null || this.image.equals(t.image))
                && (this.proposal == null || this.proposal.equals(t.proposal))
                && (this.healthTime == null || this.healthTime.equals(t.healthTime))
                && (this.notes == null || this.notes.equals(t.notes))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.status == null || this.status.equals(t.status));
    }

    @Override
    public void update(TijianHealthLog element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.userId != null && !this.userId.isEmpty()) {
            element.userId = this.userId;
        }
        if (this.orderId != null && !this.orderId.isEmpty()) {
            element.orderId = this.orderId;
        }
        if (this.name != null && !this.name.isEmpty()) {
            element.name = this.name;
        }
        if (this.phone != null && !this.phone.isEmpty()) {
            element.phone = this.phone;
        }
        if (this.tijianTypeId != null && !this.tijianTypeId.isEmpty()) {
            element.tijianTypeId = this.tijianTypeId;
        }
        if (this.head != null && !this.head.isEmpty()) {
            element.head = this.head;
        }
        if (this.neck != null && !this.neck.isEmpty()) {
            element.neck = this.neck;
        }
        if (this.arm != null && !this.arm.isEmpty()) {
            element.arm = this.arm;
        }
        if (this.leg != null && !this.leg.isEmpty()) {
            element.leg = this.leg;
        }
        if (this.hand != null && !this.hand.isEmpty()) {
            element.hand = this.hand;
        }
        if (this.foot != null && !this.foot.isEmpty()) {
            element.foot = this.foot;
        }
        if (this.heart != null && !this.heart.isEmpty()) {
            element.heart = this.heart;
        }
        if (this.liver != null && !this.liver.isEmpty()) {
            element.liver = this.liver;
        }
        if (this.lungs != null && !this.lungs.isEmpty()) {
            element.lungs = this.lungs;
        }
        if (this.stomach != null && !this.stomach.isEmpty()) {
            element.stomach = this.stomach;
        }
        if (this.gallbladder != null && !this.gallbladder.isEmpty()) {
            element.gallbladder = this.gallbladder;
        }
        if (this.kidney != null && !this.kidney.isEmpty()) {
            element.kidney = this.kidney;
        }
        if (this.reproductivesystemMan != null && !this.reproductivesystemMan.isEmpty()) {
            element.reproductivesystemMan = this.reproductivesystemMan;
        }
        if (this.reproductivesystemWoman != null && !this.reproductivesystemWoman.isEmpty()) {
            element.reproductivesystemWoman = this.reproductivesystemWoman;
        }
        if (this.image != null && !this.image.isEmpty()) {
            element.image = this.image;
        }
        if (this.proposal != null && !this.proposal.isEmpty()) {
            element.proposal = this.proposal;
        }
        if (this.healthTime != null) {
            element.healthTime = this.healthTime;
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
