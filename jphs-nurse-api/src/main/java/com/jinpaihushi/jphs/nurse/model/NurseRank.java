package com.jinpaihushi.jphs.nurse.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * NURSE_RANK 
 * 继承自父类的字段:
 * id : 	
 * type : 类型 0 系统分析 1、默认 2、手动	
 * status : 	
 * createTime : 	
 * updateTime : 	
 * 
 * @author wangwenteng
 * @date 2017-09-20 09:27:07
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NurseRank extends BaseModel implements Predicate<NurseRank>, Updator<NurseRank> {

    /**  */
    @Length(max = 50, message = "{nurseRank.userId.illegal.length}")
    private String userId;

    /** 排名 */
    private Integer ranking;

    /** 基础接单次数 */
    private Integer baseServerNumber;

    /** 真实接单次数 */
    private Integer realServerNumer;

    /** 好评率 */
    private Double favorableRate;

    /**  */
    private Integer degreeHeat;

    public NurseRank() {
    }

    public NurseRank(String id) {
        this.id = id;
    }

    /**
     * 获取
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取排名
     */
    public Integer getRanking() {
        return ranking;
    }

    /**
     * 设置排名
     */
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    /**
     * 获取基础接单次数
     */
    public Integer getBaseServerNumber() {
        return baseServerNumber;
    }

    /**
     * 设置基础接单次数
     */
    public void setBaseServerNumber(Integer baseServerNumber) {
        this.baseServerNumber = baseServerNumber;
    }

    /**
     * 获取真实接单次数
     */
    public Integer getRealServerNumer() {
        return realServerNumer;
    }

    /**
     * 设置真实接单次数
     */
    public void setRealServerNumer(Integer realServerNumer) {
        this.realServerNumer = realServerNumer;
    }

    /**
     * 获取好评率
     */
    public Double getFavorableRate() {
        return favorableRate;
    }

    /**
     * 设置好评率
     */
    public void setFavorableRate(Double favorableRate) {
        this.favorableRate = favorableRate;
    }

    /**
     * 获取
     */
    public Integer getDegreeHeat() {
        return degreeHeat;
    }

    /**
     * 设置
     */
    public void setDegreeHeat(Integer degreeHeat) {
        this.degreeHeat = degreeHeat;
    }

    public String toString() {
        return new StringBuilder().append("NurseRank{").append("id=").append(id).append(",userId=").append(userId)
                .append(",ranking=").append(ranking).append(",type=").append(type).append(",baseServerNumber=")
                .append(baseServerNumber).append(",realServerNumer=").append(realServerNumer).append(",favorableRate=")
                .append(favorableRate).append(",degreeHeat=").append(degreeHeat).append(",status=").append(status)
                .append(",createTime=").append(createTime).append(",updateTime=").append(updateTime).append('}')
                .toString();
    }

    /**
     * 复制字段：
     * id, userId, ranking, type, 
     * baseServerNumber, realServerNumer, favorableRate, degreeHeat, 
     * status, createTime, updateTime
     */
    public NurseRank copy() {
        NurseRank nurseRank = new NurseRank();
        nurseRank.id = this.id;
        nurseRank.userId = this.userId;
        nurseRank.ranking = this.ranking;
        nurseRank.type = this.type;
        nurseRank.baseServerNumber = this.baseServerNumber;
        nurseRank.realServerNumer = this.realServerNumer;
        nurseRank.favorableRate = this.favorableRate;
        nurseRank.degreeHeat = this.degreeHeat;
        nurseRank.status = this.status;
        nurseRank.createTime = this.createTime;
        nurseRank.updateTime = this.updateTime;
        return nurseRank;
    }

    /**
     * 比较字段：
     * id, userId, ranking, type, 
     * baseServerNumber, realServerNumer, favorableRate, degreeHeat, 
     * status, createTime, updateTime
     */
    @Override
    public boolean test(NurseRank t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.userId == null || this.userId.equals(t.userId))
                && (this.ranking == null || this.ranking.equals(t.ranking))
                && (this.type == null || this.type.equals(t.type))
                && (this.baseServerNumber == null || this.baseServerNumber.equals(t.baseServerNumber))
                && (this.realServerNumer == null || this.realServerNumer.equals(t.realServerNumer))
                && (this.favorableRate == null || this.favorableRate.equals(t.favorableRate))
                && (this.degreeHeat == null || this.degreeHeat.equals(t.degreeHeat))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.updateTime == null || this.updateTime.equals(t.updateTime));
    }

    @Override
    public void update(NurseRank element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.userId != null && !this.userId.isEmpty()) {
            element.userId = this.userId;
        }
        if (this.ranking != null) {
            element.ranking = this.ranking;
        }
        if (this.type != null) {
            element.type = this.type;
        }
        if (this.baseServerNumber != null) {
            element.baseServerNumber = this.baseServerNumber;
        }
        if (this.realServerNumer != null) {
            element.realServerNumer = this.realServerNumer;
        }
        if (this.favorableRate != null) {
            element.favorableRate = this.favorableRate;
        }
        if (this.degreeHeat != null) {
            element.degreeHeat = this.degreeHeat;
        }
        if (this.status != null) {
            element.status = this.status;
        }
        if (this.createTime != null) {
            element.createTime = this.createTime;
        }
        if (this.updateTime != null) {
            element.updateTime = this.updateTime;
        }
    }
}
