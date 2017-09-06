package com.jinpaihushi.jphs.person.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PERSON_GROUP 
 * 继承自父类的字段:
 * id : 	
 * status : 状态(0开启默认，-1删除)	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-09-01 14:02:33
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PersonGroup extends BaseModel implements Predicate<PersonGroup>, Updator<PersonGroup> {

    /** 组名 */
    @Length(max = 50, message = "{personGroup.name.illegal.length}")
    private String name;

    /** 排序 */
    private Integer sort = 0;

    /** 默认分组(0否，1是) */
    private Integer isDefault;

    private List<PersonManager> manager;

    public PersonGroup() {
    }

    public PersonGroup(String id) {
        this.id = id;
    }

    /**
     * 获取组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取默认分组(0否，1是)
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 设置默认分组(0否，1是)
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public List<PersonManager> getManager() {
        return manager;
    }

    public void setManager(List<PersonManager> manager) {
        this.manager = manager;
    }

    public String toString() {
        return new StringBuilder().append("PersonGroup{").append("id=").append(id).append(",name=").append(name)
                .append(",sort=").append(sort).append(",isDefault=").append(isDefault).append(",status=").append(status)
                .append(",createTime=").append(createTime).append(",creatorId=").append(creatorId)
                .append(",creatorName=").append(creatorName).append('}').toString();
    }

    /**
     * 复制字段：
     * id, name, sort, isDefault, 
     * status, createTime, creatorId, creatorName
     */
    public PersonGroup copy() {
        PersonGroup personGroup = new PersonGroup();
        personGroup.id = this.id;
        personGroup.name = this.name;
        personGroup.sort = this.sort;
        personGroup.isDefault = this.isDefault;
        personGroup.status = this.status;
        personGroup.createTime = this.createTime;
        personGroup.creatorId = this.creatorId;
        personGroup.creatorName = this.creatorName;
        return personGroup;
    }

    /**
     * 比较字段：
     * id, name, sort, isDefault, 
     * status, createTime, creatorId, creatorName
     */
    @Override
    public boolean test(PersonGroup t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.name == null || this.name.equals(t.name))
                && (this.sort == null || this.sort.equals(t.sort))
                && (this.isDefault == null || this.isDefault.equals(t.isDefault))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName));
    }

    @Override
    public void update(PersonGroup element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.name != null && !this.name.isEmpty()) {
            element.name = this.name;
        }
        if (this.sort != null) {
            element.sort = this.sort;
        }
        if (this.isDefault != null) {
            element.isDefault = this.isDefault;
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
