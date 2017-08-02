package com.jinpaihushi.jphs.system.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SYSTEM_USER_ROLE 系统用户角色表
 * 继承自父类的字段:
 * id : 	
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SystemUserRole extends BaseModel implements Predicate<SystemUserRole>,
		Updator<SystemUserRole> {


    /** 用户id */
	@NotEmpty(message = "{systemUserRole.systemUserId.empty}")
	@Length(max = 50, message = "{systemUserRole.systemUserId.illegal.length}")
	private String systemUserId;

    /** 角色id */
	@NotEmpty(message = "{systemUserRole.systemRoleId.empty}")
	@Length(max = 50, message = "{systemUserRole.systemRoleId.illegal.length}")
	private String systemRoleId;

	public SystemUserRole(){}

	public SystemUserRole(String id){
		this.id = id;
	}

	/**
	 * 获取用户id
	 */
	public String getSystemUserId() {
    	return systemUserId;
    }
  	
	/**
	 * 设置用户id
	 */
	public void setSystemUserId(String systemUserId) {
    	this.systemUserId = systemUserId;
    }

	/**
	 * 获取角色id
	 */
	public String getSystemRoleId() {
    	return systemRoleId;
    }
  	
	/**
	 * 设置角色id
	 */
	public void setSystemRoleId(String systemRoleId) {
    	this.systemRoleId = systemRoleId;
    }

    public String toString() {
		return new StringBuilder().append("SystemUserRole{").
			append("id=").append(id).
			append(",systemUserId=").append(systemUserId).
			append(",systemRoleId=").append(systemRoleId).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, systemUserId, systemRoleId
	 */
	public SystemUserRole copy(){
		SystemUserRole systemUserRole = new SystemUserRole();
     	systemUserRole.id = this.id;
     	systemUserRole.systemUserId = this.systemUserId;
     	systemUserRole.systemRoleId = this.systemRoleId;
		return systemUserRole;
	}
	
	/**
	 * 比较字段：
	 * id, systemUserId, systemRoleId
	 */
	@Override
	public boolean test(SystemUserRole t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.systemUserId == null || this.systemUserId.equals(t.systemUserId))
				&& (this.systemRoleId == null || this.systemRoleId.equals(t.systemRoleId))
		;
	}
	
	@Override
	public void update(SystemUserRole element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.systemUserId != null && !this.systemUserId.isEmpty()) {
			element.systemUserId = this.systemUserId;
		}
		if (this.systemRoleId != null && !this.systemRoleId.isEmpty()) {
			element.systemRoleId = this.systemRoleId;
		}
	}
}
