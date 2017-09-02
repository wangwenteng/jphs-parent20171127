package com.jinpaihushi.jphs.person.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PERSON_MANAGER 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0默认，待审核，1同意，-1拒绝）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-09-01 15:49:09
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PersonManager extends BaseModel implements Predicate<PersonManager>, Updator<PersonManager> {
    private String name;

    /**  */
    @Length(max = 50, message = "{personManager.personGroupId.illegal.length}")
    private String personGroupId;

    /**  */
    @Length(max = 50, message = "{personManager.userId.illegal.length}")
    private String userId;

    /** 是否是领导者 0 默认，不是 1 是 */
    private Integer isLeader;

    /** 是否查看 0 默认未读  1 已读 */
    private Integer isRead;

    private String url;

    private Integer sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonManager() {
    }

    public PersonManager(String id) {
        this.id = id;
    }

    /**
     * 获取
     */
    public String getPersonGroupId() {
        return personGroupId;
    }

    /**
     * 设置
     */
    public void setPersonGroupId(String personGroupId) {
        this.personGroupId = personGroupId;
    }

    /**
     * 获取
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取是否是领导者 0 默认，不是 1 是
     */
    public Integer getIsLeader() {
        return isLeader;
    }

    /**
     * 设置是否是领导者 0 默认，不是 1 是
     */
    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    /**
     * 获取是否查看 0 默认未读  1 已读
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * 设置是否查看 0 默认未读  1 已读
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String toString() {
        return new StringBuilder().append("PersonManager{").append("id=").append(id).append(",personGroupId=")
                .append(personGroupId).append(",userId=").append(userId).append(",isLeader=").append(isLeader)
                .append(",isRead=").append(isRead).append(",status=").append(status).append(",createTime=")
                .append(createTime).append(",creatorId=").append(creatorId).append(",creatorName=").append(creatorName)
                .append('}').toString();
    }

    /**
     * 复制字段：
     * id, personGroupId, userId, isLeader, 
     * isRead, status, createTime, creatorId, 
     * creatorName
     */
    public PersonManager copy() {
        PersonManager personManager = new PersonManager();
        personManager.id = this.id;
        personManager.personGroupId = this.personGroupId;
        personManager.userId = this.userId;
        personManager.isLeader = this.isLeader;
        personManager.isRead = this.isRead;
        personManager.status = this.status;
        personManager.createTime = this.createTime;
        personManager.creatorId = this.creatorId;
        personManager.creatorName = this.creatorName;
        return personManager;
    }

    /**
     * 比较字段：
     * id, personGroupId, userId, isLeader, 
     * isRead, status, createTime, creatorId, 
     * creatorName
     */
    @Override
    public boolean test(PersonManager t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id))
                && (this.personGroupId == null || this.personGroupId.equals(t.personGroupId))
                && (this.userId == null || this.userId.equals(t.userId))
                && (this.isLeader == null || this.isLeader.equals(t.isLeader))
                && (this.isRead == null || this.isRead.equals(t.isRead))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName));
    }

    @Override
    public void update(PersonManager element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.personGroupId != null && !this.personGroupId.isEmpty()) {
            element.personGroupId = this.personGroupId;
        }
        if (this.userId != null && !this.userId.isEmpty()) {
            element.userId = this.userId;
        }
        if (this.isLeader != null) {
            element.isLeader = this.isLeader;
        }
        if (this.isRead != null) {
            element.isRead = this.isRead;
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
