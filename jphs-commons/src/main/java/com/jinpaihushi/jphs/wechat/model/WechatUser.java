package com.jinpaihushi.jphs.wechat.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * WECHAT_USER 
 * 继承自父类的字段:
 * id : 	
 * createTime : 	
 * status : 状态	
 * 
 * @author scj
 * @date 2017-09-04 16:26:15
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WechatUser extends BaseModel implements Predicate<WechatUser>,
		Updator<WechatUser> {


    /**  */
	@Length(max = 50, message = "{wechatUser.appid.illegal.length}")
	private String appid;

    /** 用户的唯一标识 */
	@Length(max = 120, message = "{wechatUser.openid.illegal.length}")
	private String openid;

    /** 用户昵称 */
	@Length(max = 50, message = "{wechatUser.nickname.illegal.length}")
	private String nickname;

    /** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
	@Length(max = 50, message = "{wechatUser.sex.illegal.length}")
	private String sex;

    /** 用户个人资料填写的省份 */
	@Length(max = 120, message = "{wechatUser.province.illegal.length}")
	private String province;

    /** 普通用户个人资料填写的城市 */
	@Length(max = 50, message = "{wechatUser.city.illegal.length}")
	private String city;

    /** 国家，如中国为CN */
	@Length(max = 50, message = "{wechatUser.country.illegal.length}")
	private String country;

    /** 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */
	@Length(max = 500, message = "{wechatUser.headimgurl.illegal.length}")
	private String headimgurl;

    /** 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） */
	@Length(max = 500, message = "{wechatUser.privilege.illegal.length}")
	private String privilege;

    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 */
	@Length(max = 120, message = "{wechatUser.unionid.illegal.length}")
	private String unionid;

    /** 错误代码 */
	@Length(max = 200, message = "{wechatUser.errcode.illegal.length}")
	private String errcode;

    /** 错误内容 */
	@Length(max = 200, message = "{wechatUser.errmsg.illegal.length}")
	private String errmsg;

	public WechatUser(){}

	public WechatUser(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getAppid() {
    	return appid;
    }
  	
	/**
	 * 设置
	 */
	public void setAppid(String appid) {
    	this.appid = appid;
    }

	/**
	 * 获取用户的唯一标识
	 */
	public String getOpenid() {
    	return openid;
    }
  	
	/**
	 * 设置用户的唯一标识
	 */
	public void setOpenid(String openid) {
    	this.openid = openid;
    }

	/**
	 * 获取用户昵称
	 */
	public String getNickname() {
    	return nickname;
    }
  	
	/**
	 * 设置用户昵称
	 */
	public void setNickname(String nickname) {
    	this.nickname = nickname;
    }

	/**
	 * 获取用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public String getSex() {
    	return sex;
    }
  	
	/**
	 * 设置用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public void setSex(String sex) {
    	this.sex = sex;
    }

	/**
	 * 获取用户个人资料填写的省份
	 */
	public String getProvince() {
    	return province;
    }
  	
	/**
	 * 设置用户个人资料填写的省份
	 */
	public void setProvince(String province) {
    	this.province = province;
    }

	/**
	 * 获取普通用户个人资料填写的城市
	 */
	public String getCity() {
    	return city;
    }
  	
	/**
	 * 设置普通用户个人资料填写的城市
	 */
	public void setCity(String city) {
    	this.city = city;
    }

	/**
	 * 获取国家，如中国为CN
	 */
	public String getCountry() {
    	return country;
    }
  	
	/**
	 * 设置国家，如中国为CN
	 */
	public void setCountry(String country) {
    	this.country = country;
    }

	/**
	 * 获取用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	public String getHeadimgurl() {
    	return headimgurl;
    }
  	
	/**
	 * 设置用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	public void setHeadimgurl(String headimgurl) {
    	this.headimgurl = headimgurl;
    }

	/**
	 * 获取用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	 */
	public String getPrivilege() {
    	return privilege;
    }
  	
	/**
	 * 设置用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	 */
	public void setPrivilege(String privilege) {
    	this.privilege = privilege;
    }

	/**
	 * 获取只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	public String getUnionid() {
    	return unionid;
    }
  	
	/**
	 * 设置只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	public void setUnionid(String unionid) {
    	this.unionid = unionid;
    }

	/**
	 * 获取错误代码
	 */
	public String getErrcode() {
    	return errcode;
    }
  	
	/**
	 * 设置错误代码
	 */
	public void setErrcode(String errcode) {
    	this.errcode = errcode;
    }

	/**
	 * 获取错误内容
	 */
	public String getErrmsg() {
    	return errmsg;
    }
  	
	/**
	 * 设置错误内容
	 */
	public void setErrmsg(String errmsg) {
    	this.errmsg = errmsg;
    }

    public String toString() {
		return new StringBuilder().append("WechatUser{").
			append("id=").append(id).
			append(",appid=").append(appid).
			append(",openid=").append(openid).
			append(",nickname=").append(nickname).
			append(",sex=").append(sex).
			append(",province=").append(province).
			append(",city=").append(city).
			append(",country=").append(country).
			append(",headimgurl=").append(headimgurl).
			append(",privilege=").append(privilege).
			append(",unionid=").append(unionid).
			append(",errcode=").append(errcode).
			append(",errmsg=").append(errmsg).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, appid, openid, nickname, 
	 * sex, province, city, country, 
	 * headimgurl, privilege, unionid, errcode, 
	 * errmsg, createTime, status
	 */
	public WechatUser copy(){
		WechatUser wechatUser = new WechatUser();
     	wechatUser.id = this.id;
     	wechatUser.appid = this.appid;
     	wechatUser.openid = this.openid;
     	wechatUser.nickname = this.nickname;
     	wechatUser.sex = this.sex;
     	wechatUser.province = this.province;
     	wechatUser.city = this.city;
     	wechatUser.country = this.country;
     	wechatUser.headimgurl = this.headimgurl;
     	wechatUser.privilege = this.privilege;
     	wechatUser.unionid = this.unionid;
     	wechatUser.errcode = this.errcode;
     	wechatUser.errmsg = this.errmsg;
     	wechatUser.createTime = this.createTime;
     	wechatUser.status = this.status;
		return wechatUser;
	}
	
	/**
	 * 比较字段：
	 * id, appid, openid, nickname, 
	 * sex, province, city, country, 
	 * headimgurl, privilege, unionid, errcode, 
	 * errmsg, createTime, status
	 */
	@Override
	public boolean test(WechatUser t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.appid == null || this.appid.equals(t.appid))
				&& (this.openid == null || this.openid.equals(t.openid))
				&& (this.nickname == null || this.nickname.equals(t.nickname))
				&& (this.sex == null || this.sex.equals(t.sex))
				&& (this.province == null || this.province.equals(t.province))
				&& (this.city == null || this.city.equals(t.city))
				&& (this.country == null || this.country.equals(t.country))
				&& (this.headimgurl == null || this.headimgurl.equals(t.headimgurl))
				&& (this.privilege == null || this.privilege.equals(t.privilege))
				&& (this.unionid == null || this.unionid.equals(t.unionid))
				&& (this.errcode == null || this.errcode.equals(t.errcode))
				&& (this.errmsg == null || this.errmsg.equals(t.errmsg))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(WechatUser element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.appid != null && !this.appid.isEmpty()) {
			element.appid = this.appid;
		}
		if (this.openid != null && !this.openid.isEmpty()) {
			element.openid = this.openid;
		}
		if (this.nickname != null && !this.nickname.isEmpty()) {
			element.nickname = this.nickname;
		}
		if (this.sex != null && !this.sex.isEmpty()) {
			element.sex = this.sex;
		}
		if (this.province != null && !this.province.isEmpty()) {
			element.province = this.province;
		}
		if (this.city != null && !this.city.isEmpty()) {
			element.city = this.city;
		}
		if (this.country != null && !this.country.isEmpty()) {
			element.country = this.country;
		}
		if (this.headimgurl != null && !this.headimgurl.isEmpty()) {
			element.headimgurl = this.headimgurl;
		}
		if (this.privilege != null && !this.privilege.isEmpty()) {
			element.privilege = this.privilege;
		}
		if (this.unionid != null && !this.unionid.isEmpty()) {
			element.unionid = this.unionid;
		}
		if (this.errcode != null && !this.errcode.isEmpty()) {
			element.errcode = this.errcode;
		}
		if (this.errmsg != null && !this.errmsg.isEmpty()) {
			element.errmsg = this.errmsg;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
