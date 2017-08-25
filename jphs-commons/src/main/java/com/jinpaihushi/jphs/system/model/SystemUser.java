package com.jinpaihushi.jphs.system.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SYSTEM_USER 系统用户表（用户可以通过邮箱和手机号登录）
 * 继承自父类的字段:
 * id : 	
 * createTime : 创建时间	
 * creatorId : 创建人id	
 * creatorName : 创建人姓名	
 * status : 状态	
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SystemUser extends BaseModel implements Predicate<SystemUser>,
		Updator<SystemUser> {


    /** 用户姓名 */
	@NotEmpty(message = "{systemUser.name.empty}")
	@Length(max = 50, message = "{systemUser.name.illegal.length}")
	private String name;

    /** 公司邮箱 */
	@NotEmpty(message = "{systemUser.email.empty}")
	@Length(max = 50, message = "{systemUser.email.illegal.length}")
	private String email;

    /** 密码 */
	@NotEmpty(message = "{systemUser.password.empty}")
	@Length(max = 50, message = "{systemUser.password.illegal.length}")
	private String password;

    /** 手机号 */
	@NotEmpty(message = "{systemUser.phone.empty}")
	@Length(max = 50, message = "{systemUser.phone.illegal.length}")
	private String phone;
	private List<SystemRole> roleList;
	/** 用户权限 */
	private List<String> privilegeList;
	public SystemUser(){}

	public SystemUser(String id){
		this.id = id;
	}

	/**
	 * 获取用户姓名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置用户姓名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取公司邮箱
	 */
	public String getEmail() {
    	return email;
    }
  	
	/**
	 * 设置公司邮箱
	 */
	public void setEmail(String email) {
    	this.email = email;
    }

	/**
	 * 获取密码
	 */
	public String getPassword() {
    	return password;
    }
  	
	/**
	 * 设置密码
	 */
	public void setPassword(String password) {
    	this.password = password;
    }

	/**
	 * 获取手机号
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置手机号
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }
	
	public List<SystemRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SystemRole> roleList) {
		this.roleList = roleList;
	}

	public List<String> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public String toString() {
		return new StringBuilder().append("SystemUser{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",email=").append(email).
			append(",password=").append(password).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",status=").append(status).
			append(",phone=").append(phone).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, email, password, 
	 * createTime, creatorId, creatorName, status, 
	 * phone
	 */
	public SystemUser copy(){
		SystemUser systemUser = new SystemUser();
     	systemUser.id = this.id;
     	systemUser.name = this.name;
     	systemUser.email = this.email;
     	systemUser.password = this.password;
     	systemUser.createTime = this.createTime;
     	systemUser.creatorId = this.creatorId;
     	systemUser.creatorName = this.creatorName;
     	systemUser.status = this.status;
     	systemUser.phone = this.phone;
		return systemUser;
	}
	
	/**
	 * 比较字段：
	 * id, name, email, password, 
	 * createTime, creatorId, creatorName, status, 
	 * phone
	 */
	@Override
	public boolean test(SystemUser t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.email == null || this.email.equals(t.email))
				&& (this.password == null || this.password.equals(t.password))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.phone == null || this.phone.equals(t.phone))
		;
	}
	
	@Override
	public void update(SystemUser element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.email != null && !this.email.isEmpty()) {
			element.email = this.email;
		}
		if (this.password != null && !this.password.isEmpty()) {
			element.password = this.password;
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
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
	}
}
