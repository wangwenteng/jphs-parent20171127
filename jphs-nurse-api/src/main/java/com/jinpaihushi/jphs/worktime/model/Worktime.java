package com.jinpaihushi.jphs.worktime.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * WORKTIME 
 * 继承自父类的字段:
 * id : 	
 * 
 * @author wangwt
 * @date 2017-08-16 11:08:44
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Worktime extends BaseModel implements Predicate<Worktime>, Updator<Worktime> {

    /** 护士的userId  如果为0时是用户信息 */
    @Length(max = 50, message = "{worktime.userid.illegal.length}")
    private String userid;

    /**  */
    @Length(max = 50, message = "{worktime.calendar.illegal.length}")
    private String calendar;

    /**  */
    @Length(max = 50, message = "{worktime.week.illegal.length}")
    private String week;

    /** 0=空闲 1=已满 2=已接单 */
    private Integer h9;

    /**  */
    private Integer h10;

    /**  */
    private Integer h11;

    /**  */
    private Integer h12;

    /**  */
    private Integer h13;

    /**  */
    private Integer h14;

    /**  */
    private Integer h15;

    /**  */
    private Integer h16;

    /**  */
    private Integer h17;

    /**  */
    private Integer h18;

    /**  */
    private Integer h19;

    /**  */
    private Integer h20;

    /**  */
    private Integer h21;

    /**  */
    @Length(max = 50, message = "{worktime.w9.illegal.length}")
    private String w9 = "09:00";

    /**  */
    @Length(max = 50, message = "{worktime.w10.illegal.length}")
    private String w10 = "10:00";

    /**  */
    @Length(max = 50, message = "{worktime.w11.illegal.length}")
    private String w11 = "11:00";

    /**  */
    @Length(max = 50, message = "{worktime.w12.illegal.length}")
    private String w12 = "12:00";

    /**  */
    @Length(max = 50, message = "{worktime.w13.illegal.length}")
    private String w13 = "13:00";

    /**  */
    @Length(max = 50, message = "{worktime.w14.illegal.length}")
    private String w14 = "14:00";

    /**  */
    @Length(max = 50, message = "{worktime.w15.illegal.length}")
    private String w15 = "15:00";

    /**  */
    @Length(max = 50, message = "{worktime.w16.illegal.length}")
    private String w16 = "16:00";

    /**  */
    @Length(max = 50, message = "{worktime.w17.illegal.length}")
    private String w17 = "17:00";

    /**  */
    @Length(max = 50, message = "{worktime.w18.illegal.length}")
    private String w18 = "18:00";

    /**  */
    @Length(max = 50, message = "{worktime.w19.illegal.length}")
    private String w19 = "19:00";

    /**  */
    @Length(max = 50, message = "{worktime.w20.illegal.length}")
    private String w20 = "20:00";

    /**  */
    @Length(max = 50, message = "{worktime.w21.illegal.length}")
    private String w21 = "21:00";

    /**  */
    private Date createtime;

    public Worktime() {
    }

    public Worktime(String id) {
        this.id = id;
    }

    /**
     * 获取护士的userId  如果为0时是用户信息
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置护士的userId  如果为0时是用户信息
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
     * 获取0=空闲 1=已满 2=已接单
     */
    public Integer getH9() {
        return h9;
    }

    /**
     * 设置0=空闲 1=已满 2=已接单
     */
    public void setH9(Integer h9) {
        this.h9 = h9;
    }

    /**
     * 获取
     */
    public Integer getH10() {
        return h10;
    }

    /**
     * 设置
     */
    public void setH10(Integer h10) {
        this.h10 = h10;
    }

    /**
     * 获取
     */
    public Integer getH11() {
        return h11;
    }

    /**
     * 设置
     */
    public void setH11(Integer h11) {
        this.h11 = h11;
    }

    /**
     * 获取
     */
    public Integer getH12() {
        return h12;
    }

    /**
     * 设置
     */
    public void setH12(Integer h12) {
        this.h12 = h12;
    }

    /**
     * 获取
     */
    public Integer getH13() {
        return h13;
    }

    /**
     * 设置
     */
    public void setH13(Integer h13) {
        this.h13 = h13;
    }

    /**
     * 获取
     */
    public Integer getH14() {
        return h14;
    }

    /**
     * 设置
     */
    public void setH14(Integer h14) {
        this.h14 = h14;
    }

    /**
     * 获取
     */
    public Integer getH15() {
        return h15;
    }

    /**
     * 设置
     */
    public void setH15(Integer h15) {
        this.h15 = h15;
    }

    /**
     * 获取
     */
    public Integer getH16() {
        return h16;
    }

    /**
     * 设置
     */
    public void setH16(Integer h16) {
        this.h16 = h16;
    }

    /**
     * 获取
     */
    public Integer getH17() {
        return h17;
    }

    /**
     * 设置
     */
    public void setH17(Integer h17) {
        this.h17 = h17;
    }

    /**
     * 获取
     */
    public Integer getH18() {
        return h18;
    }

    /**
     * 设置
     */
    public void setH18(Integer h18) {
        this.h18 = h18;
    }

    /**
     * 获取
     */
    public Integer getH19() {
        return h19;
    }

    /**
     * 设置
     */
    public void setH19(Integer h19) {
        this.h19 = h19;
    }

    /**
     * 获取
     */
    public Integer getH20() {
        return h20;
    }

    /**
     * 设置
     */
    public void setH20(Integer h20) {
        this.h20 = h20;
    }

    /**
     * 获取
     */
    public Integer getH21() {
        return h21;
    }

    /**
     * 设置
     */
    public void setH21(Integer h21) {
        this.h21 = h21;
    }

    /**
     * 获取
     */
    public String getW9() {
        return w9;
    }

    /**
     * 设置
     */
    public void setW9(String w9) {
        this.w9 = w9;
    }

    /**
     * 获取
     */
    public String getW10() {
        return w10;
    }

    /**
     * 设置
     */
    public void setW10(String w10) {
        this.w10 = w10;
    }

    /**
     * 获取
     */
    public String getW11() {
        return w11;
    }

    /**
     * 设置
     */
    public void setW11(String w11) {
        this.w11 = w11;
    }

    /**
     * 获取
     */
    public String getW12() {
        return w12;
    }

    /**
     * 设置
     */
    public void setW12(String w12) {
        this.w12 = w12;
    }

    /**
     * 获取
     */
    public String getW13() {
        return w13;
    }

    /**
     * 设置
     */
    public void setW13(String w13) {
        this.w13 = w13;
    }

    /**
     * 获取
     */
    public String getW14() {
        return w14;
    }

    /**
     * 设置
     */
    public void setW14(String w14) {
        this.w14 = w14;
    }

    /**
     * 获取
     */
    public String getW15() {
        return w15;
    }

    /**
     * 设置
     */
    public void setW15(String w15) {
        this.w15 = w15;
    }

    /**
     * 获取
     */
    public String getW16() {
        return w16;
    }

    /**
     * 设置
     */
    public void setW16(String w16) {
        this.w16 = w16;
    }

    /**
     * 获取
     */
    public String getW17() {
        return w17;
    }

    /**
     * 设置
     */
    public void setW17(String w17) {
        this.w17 = w17;
    }

    /**
     * 获取
     */
    public String getW18() {
        return w18;
    }

    /**
     * 设置
     */
    public void setW18(String w18) {
        this.w18 = w18;
    }

    /**
     * 获取
     */
    public String getW19() {
        return w19;
    }

    /**
     * 设置
     */
    public void setW19(String w19) {
        this.w19 = w19;
    }

    /**
     * 获取
     */
    public String getW20() {
        return w20;
    }

    /**
     * 设置
     */
    public void setW20(String w20) {
        this.w20 = w20;
    }

    /**
     * 获取
     */
    public String getW21() {
        return w21;
    }

    /**
     * 设置
     */
    public void setW21(String w21) {
        this.w21 = w21;
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
        return new StringBuilder().append("Worktime{").append("id=").append(id).append(",userid=").append(userid)
                .append(",calendar=").append(calendar).append(",week=").append(week).append(",h9=").append(h9)
                .append(",h10=").append(h10).append(",h11=").append(h11).append(",h12=").append(h12).append(",h13=")
                .append(h13).append(",h14=").append(h14).append(",h15=").append(h15).append(",h16=").append(h16)
                .append(",h17=").append(h17).append(",h18=").append(h18).append(",h19=").append(h19).append(",h20=")
                .append(h20).append(",h21=").append(h21).append(",w9=").append(w9).append(",w10=").append(w10)
                .append(",w11=").append(w11).append(",w12=").append(w12).append(",w13=").append(w13).append(",w14=")
                .append(w14).append(",w15=").append(w15).append(",w16=").append(w16).append(",w17=").append(w17)
                .append(",w18=").append(w18).append(",w19=").append(w19).append(",w20=").append(w20).append(",w21=")
                .append(w21).append(",createtime=").append(createtime).append('}').toString();
    }

    /**
     * 复制字段：
     * id, userid, calendar, week, 
     * h9, h10, h11, h12, 
     * h13, h14, h15, h16, 
     * h17, h18, h19, h20, 
     * h21, w9, w10, w11, 
     * w12, w13, w14, w15, 
     * w16, w17, w18, w19, 
     * w20, w21, createtime
     */
    public Worktime copy() {
        Worktime worktime = new Worktime();
        worktime.id = this.id;
        worktime.userid = this.userid;
        worktime.calendar = this.calendar;
        worktime.week = this.week;
        worktime.h9 = this.h9;
        worktime.h10 = this.h10;
        worktime.h11 = this.h11;
        worktime.h12 = this.h12;
        worktime.h13 = this.h13;
        worktime.h14 = this.h14;
        worktime.h15 = this.h15;
        worktime.h16 = this.h16;
        worktime.h17 = this.h17;
        worktime.h18 = this.h18;
        worktime.h19 = this.h19;
        worktime.h20 = this.h20;
        worktime.h21 = this.h21;
        worktime.w9 = this.w9;
        worktime.w10 = this.w10;
        worktime.w11 = this.w11;
        worktime.w12 = this.w12;
        worktime.w13 = this.w13;
        worktime.w14 = this.w14;
        worktime.w15 = this.w15;
        worktime.w16 = this.w16;
        worktime.w17 = this.w17;
        worktime.w18 = this.w18;
        worktime.w19 = this.w19;
        worktime.w20 = this.w20;
        worktime.w21 = this.w21;
        worktime.createtime = this.createtime;
        return worktime;
    }

    /**
     * 比较字段：
     * id, userid, calendar, week, 
     * h9, h10, h11, h12, 
     * h13, h14, h15, h16, 
     * h17, h18, h19, h20, 
     * h21, w9, w10, w11, 
     * w12, w13, w14, w15, 
     * w16, w17, w18, w19, 
     * w20, w21, createtime
     */
    @Override
    public boolean test(Worktime t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.userid == null || this.userid.equals(t.userid))
                && (this.calendar == null || this.calendar.equals(t.calendar))
                && (this.week == null || this.week.equals(t.week)) && (this.h9 == null || this.h9.equals(t.h9))
                && (this.h10 == null || this.h10.equals(t.h10)) && (this.h11 == null || this.h11.equals(t.h11))
                && (this.h12 == null || this.h12.equals(t.h12)) && (this.h13 == null || this.h13.equals(t.h13))
                && (this.h14 == null || this.h14.equals(t.h14)) && (this.h15 == null || this.h15.equals(t.h15))
                && (this.h16 == null || this.h16.equals(t.h16)) && (this.h17 == null || this.h17.equals(t.h17))
                && (this.h18 == null || this.h18.equals(t.h18)) && (this.h19 == null || this.h19.equals(t.h19))
                && (this.h20 == null || this.h20.equals(t.h20)) && (this.h21 == null || this.h21.equals(t.h21))
                && (this.w9 == null || this.w9.equals(t.w9)) && (this.w10 == null || this.w10.equals(t.w10))
                && (this.w11 == null || this.w11.equals(t.w11)) && (this.w12 == null || this.w12.equals(t.w12))
                && (this.w13 == null || this.w13.equals(t.w13)) && (this.w14 == null || this.w14.equals(t.w14))
                && (this.w15 == null || this.w15.equals(t.w15)) && (this.w16 == null || this.w16.equals(t.w16))
                && (this.w17 == null || this.w17.equals(t.w17)) && (this.w18 == null || this.w18.equals(t.w18))
                && (this.w19 == null || this.w19.equals(t.w19)) && (this.w20 == null || this.w20.equals(t.w20))
                && (this.w21 == null || this.w21.equals(t.w21))
                && (this.createtime == null || this.createtime.equals(t.createtime));
    }

    @Override
    public void update(Worktime element) {
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
        if (this.h9 != null) {
            element.h9 = this.h9;
        }
        if (this.h10 != null) {
            element.h10 = this.h10;
        }
        if (this.h11 != null) {
            element.h11 = this.h11;
        }
        if (this.h12 != null) {
            element.h12 = this.h12;
        }
        if (this.h13 != null) {
            element.h13 = this.h13;
        }
        if (this.h14 != null) {
            element.h14 = this.h14;
        }
        if (this.h15 != null) {
            element.h15 = this.h15;
        }
        if (this.h16 != null) {
            element.h16 = this.h16;
        }
        if (this.h17 != null) {
            element.h17 = this.h17;
        }
        if (this.h18 != null) {
            element.h18 = this.h18;
        }
        if (this.h19 != null) {
            element.h19 = this.h19;
        }
        if (this.h20 != null) {
            element.h20 = this.h20;
        }
        if (this.h21 != null) {
            element.h21 = this.h21;
        }
        if (this.w9 != null && !this.w9.isEmpty()) {
            element.w9 = this.w9;
        }
        if (this.w10 != null && !this.w10.isEmpty()) {
            element.w10 = this.w10;
        }
        if (this.w11 != null && !this.w11.isEmpty()) {
            element.w11 = this.w11;
        }
        if (this.w12 != null && !this.w12.isEmpty()) {
            element.w12 = this.w12;
        }
        if (this.w13 != null && !this.w13.isEmpty()) {
            element.w13 = this.w13;
        }
        if (this.w14 != null && !this.w14.isEmpty()) {
            element.w14 = this.w14;
        }
        if (this.w15 != null && !this.w15.isEmpty()) {
            element.w15 = this.w15;
        }
        if (this.w16 != null && !this.w16.isEmpty()) {
            element.w16 = this.w16;
        }
        if (this.w17 != null && !this.w17.isEmpty()) {
            element.w17 = this.w17;
        }
        if (this.w18 != null && !this.w18.isEmpty()) {
            element.w18 = this.w18;
        }
        if (this.w19 != null && !this.w19.isEmpty()) {
            element.w19 = this.w19;
        }
        if (this.w20 != null && !this.w20.isEmpty()) {
            element.w20 = this.w20;
        }
        if (this.w21 != null && !this.w21.isEmpty()) {
            element.w21 = this.w21;
        }
        if (this.createtime != null) {
            element.createtime = this.createtime;
        }
    }
}
