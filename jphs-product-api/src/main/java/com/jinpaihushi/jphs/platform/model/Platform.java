package com.jinpaihushi.jphs.platform.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM  平台
 * 继承自父类的字段:
 * id : id	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwt
 * @date 2017-06-21 15:30:05
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Platform extends BaseModel implements Predicate<Platform>, Updator<Platform> {

    /** 平台名称 */
    @Length(max = 50, message = "{platform.name.illegal.length}")
    private String name;

    /** 公司 */
    @Length(max = 50, message = "{platform.company.illegal.length}")
    private String company;

    /** 渠道	 1.公众号，2.微信，3.app，4.网站 */
    @Length(max = 50, message = "{platform.channel.illegal.length}")
    private String channel;

    /** 联系人姓名 */
    @Length(max = 50, message = "{platform.contactsName.illegal.length}")
    private String contactsName;

    /** 联系人手机号 */
    @Length(max = 50, message = "{platform.contactsPhone.illegal.length}")
    private String contactsPhone;

    /** 公司地址 */
    @Length(max = 255, message = "{platform.companyAddress.illegal.length}")
    private String companyAddress;

    /** 平台分成比例 */
    private Double splitRatio;

    /** 授权码 */
    @Length(max = 50, message = "{platform.authCode.illegal.length}")
    private String authCode;

    /** 备注 */
    @Length(max = 500, message = "{platform.remark.illegal.length}")
    private String remark;

    private String goodsIds;

    private String sites;

    public Platform() {
    }

    public Platform(String id) {
        this.id = id;
    }

    /**
     * 获取平台名称
     */
    public String getName() {
        return name;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    /**
     * 设置平台名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取1.公众号，2.微信，3.app，4.网站
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置1.公众号，2.微信，3.app，4.网站
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 获取联系人姓名
     */
    public String getContactsName() {
        return contactsName;
    }

    /**
     * 设置联系人姓名
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    /**
     * 获取联系人手机号
     */
    public String getContactsPhone() {
        return contactsPhone;
    }

    /**
     * 设置联系人手机号
     */
    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    /**
     * 获取公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取平台分成比例
     */
    public Double getSplitRatio() {
        return splitRatio;
    }

    /**
     * 设置平台分成比例
     */
    public void setSplitRatio(Double splitRatio) {
        this.splitRatio = splitRatio;
    }

    /**
     * 获取授权码
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * 设置授权码
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String toString() {
        return new StringBuilder().append("Platform{").append("id=").append(id).append(",name=").append(name)
                .append(",company=").append(company).append(",channel=").append(channel).append(",contactsName=")
                .append(contactsName).append(",contactsPhone=").append(contactsPhone).append(",companyAddress=")
                .append(companyAddress).append(",remark=").append(remark).append(",status=").append(status)
                .append(",createTime=").append(createTime).append(",creatorId=").append(creatorId)
                .append(",creatorName=").append(creatorName).append('}').toString();
    }

    /**
     * 复制字段： id, name, company, channel, contactsName, contactsPhone,
     * companyAddress, remark, status, createTime, creatorId, creatorName
     */
    public Platform copy() {
        Platform platform = new Platform();
        platform.id = this.id;
        platform.name = this.name;
        platform.company = this.company;
        platform.channel = this.channel;
        platform.contactsName = this.contactsName;
        platform.contactsPhone = this.contactsPhone;
        platform.companyAddress = this.companyAddress;
        platform.remark = this.remark;
        platform.status = this.status;
        platform.createTime = this.createTime;
        platform.creatorId = this.creatorId;
        platform.creatorName = this.creatorName;
        return platform;
    }

    /**
     * 比较字段： id, name, company, channel, contactsName, contactsPhone,
     * companyAddress, remark, status, createTime, creatorId, creatorName
     */
    @Override
    public boolean test(Platform t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.name == null || this.name.equals(t.name))
                && (this.company == null || this.company.equals(t.company))
                && (this.channel == null || this.channel.equals(t.channel))
                && (this.contactsName == null || this.contactsName.equals(t.contactsName))
                && (this.contactsPhone == null || this.contactsPhone.equals(t.contactsPhone))
                && (this.companyAddress == null || this.companyAddress.equals(t.companyAddress))
                && (this.remark == null || this.remark.equals(t.remark))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName));
    }

    @Override
    public void update(Platform element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.name != null && !this.name.isEmpty()) {
            element.name = this.name;
        }
        if (this.company != null && !this.company.isEmpty()) {
            element.company = this.company;
        }
        if (this.channel != null && !this.channel.isEmpty()) {
            element.channel = this.channel;
        }
        if (this.contactsName != null && !this.contactsName.isEmpty()) {
            element.contactsName = this.contactsName;
        }
        if (this.contactsPhone != null && !this.contactsPhone.isEmpty()) {
            element.contactsPhone = this.contactsPhone;
        }
        if (this.companyAddress != null && !this.companyAddress.isEmpty()) {
            element.companyAddress = this.companyAddress;
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
