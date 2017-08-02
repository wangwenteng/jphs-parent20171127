package com.jinpaihushi.jphs.system.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SYSTEM_ROLE_MODULE 角色权限中间表
 * 继承自父类的字段:
 * 
 * @author wangwt
 * @date 2017-07-06 09:05:06
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SystemRoleModule extends BaseModel implements Predicate<SystemRoleModule>,
		Updator<SystemRoleModule> {


    /**  */
	@NotEmpty(message = "{systemRoleModule.systemRoleId.empty}")
	@Length(max = 50, message = "{systemRoleModule.systemRoleId.illegal.length}")
	private String systemRoleId;

    /**  */
	@NotEmpty(message = "{systemRoleModule.systemModuleId.empty}")
	@Length(max = 255, message = "{systemRoleModule.systemModuleId.illegal.length}")
	private String systemModuleId;

	public SystemRoleModule(){}


	/**
	 * 获取
	 */
	public String getSystemRoleId() {
    	return systemRoleId;
    }
  	
	/**
	 * 设置
	 */
	public void setSystemRoleId(String systemRoleId) {
    	this.systemRoleId = systemRoleId;
    }

	/**
	 * 获取
	 */
	public String getSystemModuleId() {
    	return systemModuleId;
    }
  	
	/**
	 * 设置
	 */
	public void setSystemModuleId(String systemModuleId) {
    	this.systemModuleId = systemModuleId;
    }

    public String toString() {
		return new StringBuilder().append("SystemRoleModule{").
			append("systemRoleId=").append(systemRoleId).
			append(",systemModuleId=").append(systemModuleId).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * systemRoleId, systemModuleId
	 */
	public SystemRoleModule copy(){
		SystemRoleModule systemRoleModule = new SystemRoleModule();
     	systemRoleModule.systemRoleId = this.systemRoleId;
     	systemRoleModule.systemModuleId = this.systemModuleId;
		return systemRoleModule;
	}
	
	/**
	 * 比较字段：
	 * systemRoleId, systemModuleId
	 */
	@Override
	public boolean test(SystemRoleModule t) {
		if(t == null) return false;
		return (this.systemRoleId == null || this.systemRoleId.equals(t.systemRoleId))
				&& (this.systemModuleId == null || this.systemModuleId.equals(t.systemModuleId))
		;
	}
	
	@Override
	public void update(SystemRoleModule element) {
		if (element == null)
			return;
		if (this.systemRoleId != null && !this.systemRoleId.isEmpty()) {
			element.systemRoleId = this.systemRoleId;
		}
		if (this.systemModuleId != null && !this.systemModuleId.isEmpty()) {
			element.systemModuleId = this.systemModuleId;
		}
	}
}
