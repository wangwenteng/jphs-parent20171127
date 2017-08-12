package com.jinpaihushi.jphs.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.model.BaseModel;

import net.sf.json.JSONObject;

/**
 * USER 
 * 继承自父类的字段:
 * id : id	
 * type : 类型（0护士，1用户）	
 * createTime : 创建时间	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author yangsong
 * @date 2017-06-27 17:15:32
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class User extends BaseModel implements Predicate<User>,
		Updator<User>,Serializable {


    /** 姓名 */
	@Length(max = 50, message = "{user.name.illegal.length}")
	private String name;

    /** 性别（0=男，1=女） */
	private Integer sex;

    /** 登录手机号（账号） */
	@Length(max = 50, message = "{user.phone.illegal.length}")
	private String phone;

    /** 密码 */
	@Length(max = 50, message = "{user.password.illegal.length}")
	private String password;

    /** 个人简介 */
	@Length(max = 65535, message = "{user.brief.illegal.length}")
	private String brief;

    /** 微信openid */
	@Length(max = 50, message = "{user.openId.illegal.length}")
	private String openId;

    /** ios=1,and=2,wx=3,4=114等(注册端) */
	private Integer device;

    /** 推荐人id */
	@Length(max = 50, message = "{user.recommendId.illegal.length}")
	private String recommendId;

	private JSONObject nurse;
	
	private User user;
	
	private Account account;
	
    /** 余额 */
	private Double balance;

    /** 积分 */
	private Integer score;
	
	private UserAddress userAddress;
	
	private Transaction transaction;
	
	private String province;
	
	private String city;
	
	private String area;
	
	private String detailaddress;
	
	private Date beginTime;
	
	private Date stopTime;
	
	private String token;
	private String headPicture;
	private String address;//区域信息
	public User(){}

	public User(String id){
		this.id = id;
	}
	
	/**
	 * 获取nurse数据
	 */
	public JSONObject getNurse() {
    	return nurse;
    }
  	
	/**
	 * 设置nurse数据
	 */
	public void setNurse(JSONObject nurse) {
    	this.nurse = nurse;
    }

	/**
	 * 获取姓名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置姓名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取性别（0=男，1=女）
	 */
	public Integer getSex() {
    	return sex;
    }
  	
	/**
	 * 设置性别（0=男，1=女）
	 */
	public void setSex(Integer sex) {
    	this.sex = sex;
    }

	/**
	 * 获取登录手机号（账号）
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置登录手机号（账号）
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
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
	 * 获取个人简介
	 */
	public String getBrief() {
    	return brief;
    }
  	
	/**
	 * 设置个人简介
	 */
	public void setBrief(String brief) {
    	this.brief = brief;
    }

	/**
	 * 获取微信openid
	 */
	public String getOpenId() {
    	return openId;
    }
  	
	/**
	 * 设置微信openid
	 */
	public void setOpenId(String openId) {
    	this.openId = openId;
    }

	/**
	 * 获取ios=1,and=2,wx=3,4=114等(注册端)
	 */
	public Integer getDevice() {
    	return device;
    }
  	
	/**
	 * 设置ios=1,and=2,wx=3,4=114等(注册端)
	 */
	public void setDevice(Integer device) {
    	this.device = device;
    }

	/**
	 * 获取推荐人id
	 */
	public String getRecommendId() {
    	return recommendId;
    }
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
	
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 设置推荐人id
	 */
	public void setRecommendId(String recommendId) {
    	this.recommendId = recommendId;
    }
	
    public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return new StringBuilder().append("User{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",name=").append(name).
			append(",sex=").append(sex).
			append(",phone=").append(phone).
			append(",password=").append(password).
			append(",brief=").append(brief).
			append(",openId=").append(openId).
			append(",device=").append(device).
			append(",recommendId=").append(recommendId).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, name, sex, 
	 * phone, password, brief, openId, 
	 * device, recommendId, createTime, status
	 */
	public User copy(){
		User user = new User();
     	user.id = this.id;
     	user.type = this.type;
     	user.name = this.name;
     	user.sex = this.sex;
     	user.phone = this.phone;
     	user.password = this.password;
     	user.brief = this.brief;
     	user.openId = this.openId;
     	user.device = this.device;
     	user.recommendId = this.recommendId;
     	user.createTime = this.createTime;
     	user.status = this.status;
		return user;
	}
	
	/**
	 * 比较字段：
	 * id, type, name, sex, 
	 * phone, password, brief, openId, 
	 * device, recommendId, createTime, status
	 */
	@Override
	public boolean test(User t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sex == null || this.sex.equals(t.sex))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.password == null || this.password.equals(t.password))
				&& (this.brief == null || this.brief.equals(t.brief))
				&& (this.openId == null || this.openId.equals(t.openId))
				&& (this.device == null || this.device.equals(t.device))
				&& (this.recommendId == null || this.recommendId.equals(t.recommendId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(User element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.sex != null) {
			element.sex = this.sex;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.password != null && !this.password.isEmpty()) {
			element.password = this.password;
		}
		if (this.brief != null && !this.brief.isEmpty()) {
			element.brief = this.brief;
		}
		if (this.openId != null && !this.openId.isEmpty()) {
			element.openId = this.openId;
		}
		if (this.device != null) {
			element.device = this.device;
		}
		if (this.recommendId != null && !this.recommendId.isEmpty()) {
			element.recommendId = this.recommendId;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
	
}
