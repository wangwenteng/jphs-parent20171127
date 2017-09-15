package com.jinpaihushi.jphs.wechat.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * WECTH 
 * 继承自父类的字段:
 * id : 	
 * type : 1:access_token,2:ticket_token	
 * createTime : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-09-03 09:50:11
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Wechat extends BaseModel implements Predicate<Wechat>,
		Updator<Wechat> {


    /** 公众号APPID */
	@Length(max = 80, message = "{wecth.appid.illegal.length}")
	private String appid;

    /**  */
	@Length(max = 500, message = "{wecth.accessToken.illegal.length}")
	private String accessToken;

    /**  */
	@Length(max = 500, message = "{wecth.ticket.illegal.length}")
	private String ticket;

    /**  */
	@Length(max = 100, message = "{wecth.expiresIn.illegal.length}")
	private String expiresIn;

    /**  */
	@Length(max = 50, message = "{wecth.errcode.illegal.length}")
	private String errcode;

    /**  */
	@Length(max = 65535, message = "{goods.content.illegal.length}")
	private String errmsg;

	public Wechat(){}

	public Wechat(String id){
		this.id = id;
	}

	/**
	 * 获取公众号APPID
	 */
	public String getAppid() {
    	return appid;
    }
  	
	/**
	 * 设置公众号APPID
	 */
	public void setAppid(String appid) {
    	this.appid = appid;
    }

	/**
	 * 获取
	 */
	public String getAccessToken() {
    	return accessToken;
    }
  	
	/**
	 * 设置
	 */
	public void setAccessToken(String accessToken) {
    	this.accessToken = accessToken;
    }

	/**
	 * 获取
	 */
	public String getTicket() {
    	return ticket;
    }
  	
	/**
	 * 设置
	 */
	public void setTicket(String ticket) {
    	this.ticket = ticket;
    }

	/**
	 * 获取
	 */
	public String getExpiresIn() {
    	return expiresIn;
    }
  	
	/**
	 * 设置
	 */
	public void setExpiresIn(String expiresIn) {
    	this.expiresIn = expiresIn;
    }

	/**
	 * 获取
	 */
	public String getErrcode() {
    	return errcode;
    }
  	
	/**
	 * 设置
	 */
	public void setErrcode(String errcode) {
    	this.errcode = errcode;
    }

	/**
	 * 获取
	 */
	public String getErrmsg() {
    	return errmsg;
    }
  	
	/**
	 * 设置
	 */
	public void setErrmsg(String errmsg) {
    	this.errmsg = errmsg;
    }

    public String toString() {
		return new StringBuilder().append("Wecth{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",appid=").append(appid).
			append(",accessToken=").append(accessToken).
			append(",ticket=").append(ticket).
			append(",expiresIn=").append(expiresIn).
			append(",errcode=").append(errcode).
			append(",errmsg=").append(errmsg).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, appid, accessToken, 
	 * ticket, expiresIn, errcode, errmsg, 
	 * createTime, status
	 */
	public Wechat copy(){
		Wechat wecth = new Wechat();
     	wecth.id = this.id;
     	wecth.type = this.type;
     	wecth.appid = this.appid;
     	wecth.accessToken = this.accessToken;
     	wecth.ticket = this.ticket;
     	wecth.expiresIn = this.expiresIn;
     	wecth.errcode = this.errcode;
     	wecth.errmsg = this.errmsg;
     	wecth.createTime = this.createTime;
     	wecth.status = this.status;
		return wecth;
	}
	
	/**
	 * 比较字段：
	 * id, type, appid, accessToken, 
	 * ticket, expiresIn, errcode, errmsg, 
	 * createTime, status
	 */
	@Override
	public boolean test(Wechat t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.appid == null || this.appid.equals(t.appid))
				&& (this.accessToken == null || this.accessToken.equals(t.accessToken))
				&& (this.ticket == null || this.ticket.equals(t.ticket))
				&& (this.expiresIn == null || this.expiresIn.equals(t.expiresIn))
				&& (this.errcode == null || this.errcode.equals(t.errcode))
				&& (this.errmsg == null || this.errmsg.equals(t.errmsg))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(Wechat element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.appid != null && !this.appid.isEmpty()) {
			element.appid = this.appid;
		}
		if (this.accessToken != null && !this.accessToken.isEmpty()) {
			element.accessToken = this.accessToken;
		}
		if (this.ticket != null && !this.ticket.isEmpty()) {
			element.ticket = this.ticket;
		}
		if (this.expiresIn != null && !this.expiresIn.isEmpty()) {
			element.expiresIn = this.expiresIn;
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
