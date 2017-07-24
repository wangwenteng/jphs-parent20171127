package com.jinpaihushi.jphs.system.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SYSTEM_ROLE 系统角色表
 * 继承自父类的字段:
 * id : 	
 * status : 角色状态	
 * creatorId : 创建人id	
 * creatorName : 	
 * createTime : 	
 * 
 * @author wangwt
 * @date 2017-07-07 17:56:34
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SystemRole extends BaseModel implements Predicate<SystemRole>,
		Updator<SystemRole> {


    /** 角色名称 */
	@NotEmpty(message = "{systemRole.name.empty}")
	@Length(max = 50, message = "{systemRole.name.illegal.length}")
	private String name;

    /** 角色描述 */
	@Length(max = 200, message = "{systemRole.describe.illegal.length}")
	private String describe;
	private Boolean checked = false;
	private List<SystemModule> moduleList;
	public SystemRole(){}

	public SystemRole(String id){
		this.id = id;
	}

	/**
	 * 获取角色名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置角色名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取角色描述
	 */
	public String getDescribe() {
    	return describe;
    }
  	
	/**
	 * 设置角色描述
	 */
	public void setDescribe(String describe) {
    	this.describe = describe;
    }
	
    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<SystemModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<SystemModule> moduleList) {
		this.moduleList = moduleList;
	}

	public String toString() {
		return new StringBuilder().append("SystemRole{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",describe=").append(describe).
			append(",status=").append(status).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, describe, status, 
	 * creatorId, creatorName, createTime
	 */
	public SystemRole copy(){
		SystemRole systemRole = new SystemRole();
     	systemRole.id = this.id;
     	systemRole.name = this.name;
     	systemRole.describe = this.describe;
     	systemRole.status = this.status;
     	systemRole.creatorId = this.creatorId;
     	systemRole.creatorName = this.creatorName;
     	systemRole.createTime = this.createTime;
		return systemRole;
	}
	
	/**
	 * 比较字段：
	 * id, name, describe, status, 
	 * creatorId, creatorName, createTime
	 */
	@Override
	public boolean test(SystemRole t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.describe == null || this.describe.equals(t.describe))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(SystemRole element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.describe != null && !this.describe.isEmpty()) {
			element.describe = this.describe;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
	}
}
