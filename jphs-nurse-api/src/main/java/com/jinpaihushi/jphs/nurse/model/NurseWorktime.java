package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;
import java.util.Date;

/**
 * NURSE_WORKTIME 工作时间
 * 继承自父类的字段:
 * id : 	
 * 
 * @author yangsong
 * @date 2017-08-09 13:46:27
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseWorktime extends BaseModel implements Predicate<NurseWorktime>,
		Updator<NurseWorktime> {


    /**  */
	@Length(max = 50, message = "{nurseWorktime.userid.illegal.length}")
	private String userid;

    /**  */
	@Length(max = 50, message = "{nurseWorktime.calendar.illegal.length}")
	private String calendar;

    /**  */
	@Length(max = 50, message = "{nurseWorktime.week.illegal.length}")
	private String week;

    /** 改时间段是否空闲 默认0 空闲 1、已满 */
	private Integer h911=0;

    /**  */
	private Integer h1113 =0;

    /**  */
	private Integer h1315=0;

    /**  */
	private Integer h1517=0;

    /**  */
	private Integer h1719=0;

    /**  */
	private Integer h1921=0;

    /** 具体时间段 */
	@Length(max = 50, message = "{nurseWorktime.w911.illegal.length}")
	private String w911="09:00-11:00";

    /**  */
	@Length(max = 50, message = "{nurseWorktime.w1113.illegal.length}")
	private String w1113="11:00-13:00";

    /**  */
	@Length(max = 50, message = "{nurseWorktime.w1315.illegal.length}")
	private String w1315="13:00-15:00";

    /**  */
	@Length(max = 50, message = "{nurseWorktime.w1517.illegal.length}")
	private String w1517="15:00-17:00";

    /**  */
	@Length(max = 50, message = "{nurseWorktime.w1719.illegal.length}")
	private String w1719="17:00-19:00";

    /**  */
	@Length(max = 50, message = "{nurseWorktime.w1921.illegal.length}")
	private String w1921="19:00-21:00";

    /**  */
	private Date createtime;

	public NurseWorktime(){}

	public NurseWorktime(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getUserid() {
    	return userid;
    }
  	
	/**
	 * 设置
	 */
	public void setUserid(String userid) {
    	this.userid = userid;
    }

	/**
	 * 获取
	 */
	public String getCalendar() {
    	return calendar;
    }
  	
	/**
	 * 设置
	 */
	public void setCalendar(String calendar) {
    	this.calendar = calendar;
    }

	/**
	 * 获取
	 */
	public String getWeek() {
    	return week;
    }
  	
	/**
	 * 设置
	 */
	public void setWeek(String week) {
    	this.week = week;
    }

	/**
	 * 获取改时间段是否空闲 默认0 空闲 1、已满
	 */
	public Integer getH911() {
    	return h911;
    }
  	
	/**
	 * 设置改时间段是否空闲 默认0 空闲 1、已满
	 */
	public void setH911(Integer h911) {
    	this.h911 = h911;
    }

	/**
	 * 获取
	 */
	public Integer getH1113() {
    	return h1113;
    }
  	
	/**
	 * 设置
	 */
	public void setH1113(Integer h1113) {
    	this.h1113 = h1113;
    }

	/**
	 * 获取
	 */
	public Integer getH1315() {
    	return h1315;
    }
  	
	/**
	 * 设置
	 */
	public void setH1315(Integer h1315) {
    	this.h1315 = h1315;
    }

	/**
	 * 获取
	 */
	public Integer getH1517() {
    	return h1517;
    }
  	
	/**
	 * 设置
	 */
	public void setH1517(Integer h1517) {
    	this.h1517 = h1517;
    }

	/**
	 * 获取
	 */
	public Integer getH1719() {
    	return h1719;
    }
  	
	/**
	 * 设置
	 */
	public void setH1719(Integer h1719) {
    	this.h1719 = h1719;
    }

	/**
	 * 获取
	 */
	public Integer getH1921() {
    	return h1921;
    }
  	
	/**
	 * 设置
	 */
	public void setH1921(Integer h1921) {
    	this.h1921 = h1921;
    }

	/**
	 * 获取具体时间段
	 */
	public String getW911() {
    	return w911;
    }
  	
	/**
	 * 设置具体时间段
	 */
	public void setW911(String w911) {
    	this.w911 = w911;
    }

	/**
	 * 获取
	 */
	public String getW1113() {
    	return w1113;
    }
  	
	/**
	 * 设置
	 */
	public void setW1113(String w1113) {
    	this.w1113 = w1113;
    }

	/**
	 * 获取
	 */
	public String getW1315() {
    	return w1315;
    }
  	
	/**
	 * 设置
	 */
	public void setW1315(String w1315) {
    	this.w1315 = w1315;
    }

	/**
	 * 获取
	 */
	public String getW1517() {
    	return w1517;
    }
  	
	/**
	 * 设置
	 */
	public void setW1517(String w1517) {
    	this.w1517 = w1517;
    }

	/**
	 * 获取
	 */
	public String getW1719() {
    	return w1719;
    }
  	
	/**
	 * 设置
	 */
	public void setW1719(String w1719) {
    	this.w1719 = w1719;
    }

	/**
	 * 获取
	 */
	public String getW1921() {
    	return w1921;
    }
  	
	/**
	 * 设置
	 */
	public void setW1921(String w1921) {
    	this.w1921 = w1921;
    }

	/**
	 * 获取
	 */
	public Date getCreatetime() {
    	return createtime;
    }
  	
	/**
	 * 设置
	 */
	public void setCreatetime(Date createtime) {
    	this.createtime = createtime;
    }

    public String toString() {
		return new StringBuilder().append("NurseWorktime{").
			append("id=").append(id).
			append(",userid=").append(userid).
			append(",calendar=").append(calendar).
			append(",week=").append(week).
			append(",h911=").append(h911).
			append(",h1113=").append(h1113).
			append(",h1315=").append(h1315).
			append(",h1517=").append(h1517).
			append(",h1719=").append(h1719).
			append(",h1921=").append(h1921).
			append(",w911=").append(w911).
			append(",w1113=").append(w1113).
			append(",w1315=").append(w1315).
			append(",w1517=").append(w1517).
			append(",w1719=").append(w1719).
			append(",w1921=").append(w1921).
			append(",createtime=").append(createtime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, userid, calendar, week, 
	 * h911, h1113, h1315, h1517, 
	 * h1719, h1921, w911, w1113, 
	 * w1315, w1517, w1719, w1921, 
	 * createtime
	 */
	public NurseWorktime copy(){
		NurseWorktime nurseWorktime = new NurseWorktime();
     	nurseWorktime.id = this.id;
     	nurseWorktime.userid = this.userid;
     	nurseWorktime.calendar = this.calendar;
     	nurseWorktime.week = this.week;
     	nurseWorktime.h911 = this.h911;
     	nurseWorktime.h1113 = this.h1113;
     	nurseWorktime.h1315 = this.h1315;
     	nurseWorktime.h1517 = this.h1517;
     	nurseWorktime.h1719 = this.h1719;
     	nurseWorktime.h1921 = this.h1921;
     	nurseWorktime.w911 = this.w911;
     	nurseWorktime.w1113 = this.w1113;
     	nurseWorktime.w1315 = this.w1315;
     	nurseWorktime.w1517 = this.w1517;
     	nurseWorktime.w1719 = this.w1719;
     	nurseWorktime.w1921 = this.w1921;
     	nurseWorktime.createtime = this.createtime;
		return nurseWorktime;
	}
	
	/**
	 * 比较字段：
	 * id, userid, calendar, week, 
	 * h911, h1113, h1315, h1517, 
	 * h1719, h1921, w911, w1113, 
	 * w1315, w1517, w1719, w1921, 
	 * createtime
	 */
	@Override
	public boolean test(NurseWorktime t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.userid == null || this.userid.equals(t.userid))
				&& (this.calendar == null || this.calendar.equals(t.calendar))
				&& (this.week == null || this.week.equals(t.week))
				&& (this.h911 == null || this.h911.equals(t.h911))
				&& (this.h1113 == null || this.h1113.equals(t.h1113))
				&& (this.h1315 == null || this.h1315.equals(t.h1315))
				&& (this.h1517 == null || this.h1517.equals(t.h1517))
				&& (this.h1719 == null || this.h1719.equals(t.h1719))
				&& (this.h1921 == null || this.h1921.equals(t.h1921))
				&& (this.w911 == null || this.w911.equals(t.w911))
				&& (this.w1113 == null || this.w1113.equals(t.w1113))
				&& (this.w1315 == null || this.w1315.equals(t.w1315))
				&& (this.w1517 == null || this.w1517.equals(t.w1517))
				&& (this.w1719 == null || this.w1719.equals(t.w1719))
				&& (this.w1921 == null || this.w1921.equals(t.w1921))
				&& (this.createtime == null || this.createtime.equals(t.createtime))
		;
	}
	
	@Override
	public void update(NurseWorktime element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.userid != null && !this.userid.isEmpty()) {
			element.userid = this.userid;
		}
		if (this.calendar != null && !this.calendar.isEmpty()) {
			element.calendar = this.calendar;
		}
		if (this.week != null && !this.week.isEmpty()) {
			element.week = this.week;
		}
		if (this.h911 != null) {
			element.h911 = this.h911;
		}
		if (this.h1113 != null) {
			element.h1113 = this.h1113;
		}
		if (this.h1315 != null) {
			element.h1315 = this.h1315;
		}
		if (this.h1517 != null) {
			element.h1517 = this.h1517;
		}
		if (this.h1719 != null) {
			element.h1719 = this.h1719;
		}
		if (this.h1921 != null) {
			element.h1921 = this.h1921;
		}
		if (this.w911 != null && !this.w911.isEmpty()) {
			element.w911 = this.w911;
		}
		if (this.w1113 != null && !this.w1113.isEmpty()) {
			element.w1113 = this.w1113;
		}
		if (this.w1315 != null && !this.w1315.isEmpty()) {
			element.w1315 = this.w1315;
		}
		if (this.w1517 != null && !this.w1517.isEmpty()) {
			element.w1517 = this.w1517;
		}
		if (this.w1719 != null && !this.w1719.isEmpty()) {
			element.w1719 = this.w1719;
		}
		if (this.w1921 != null && !this.w1921.isEmpty()) {
			element.w1921 = this.w1921;
		}
		if (this.createtime != null) {
			element.createtime = this.createtime;
		}
	}
}
