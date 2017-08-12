package com.jinpaihushi.jphs.jobtitle.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JOBTITLE_GOODS 
 * 继承自父类的字段:
 * id : 	
 * status : 状态	
 * creatorId : 创建人	
 * creatorName : 创建人姓名	
 * createTime : 创建时间	
 * 
 * @author scj
 * @date 2017-08-09 17:06:12
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JobtitleGoods extends BaseModel implements Predicate<JobtitleGoods>,
		Updator<JobtitleGoods> {


    /** 商品ID */
	@Length(max = 50, message = "{jobtitleGoods.goodsId.illegal.length}")
	private String goodsId;

    /** 职称ID */
	@Length(max = 50, message = "{jobtitleGoods.jobtitleId.illegal.length}")
	private String jobtitleId;

	public JobtitleGoods(){}

	public JobtitleGoods(String id){
		this.id = id;
	}

	/**
	 * 获取商品ID
	 */
	public String getGoodsId() {
    	return goodsId;
    }
  	
	/**
	 * 设置商品ID
	 */
	public void setGoodsId(String goodsId) {
    	this.goodsId = goodsId;
    }

	/**
	 * 获取职称ID
	 */
	public String getJobtitleId() {
    	return jobtitleId;
    }
  	
	/**
	 * 设置职称ID
	 */
	public void setJobtitleId(String jobtitleId) {
    	this.jobtitleId = jobtitleId;
    }

    public String toString() {
		return new StringBuilder().append("JobtitleGoods{").
			append("id=").append(id).
			append(",goodsId=").append(goodsId).
			append(",jobtitleId=").append(jobtitleId).
			append(",status=").append(status).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, goodsId, jobtitleId, status, 
	 * creatorId, creatorName, createTime
	 */
	public JobtitleGoods copy(){
		JobtitleGoods jobtitleGoods = new JobtitleGoods();
     	jobtitleGoods.id = this.id;
     	jobtitleGoods.goodsId = this.goodsId;
     	jobtitleGoods.jobtitleId = this.jobtitleId;
     	jobtitleGoods.status = this.status;
     	jobtitleGoods.creatorId = this.creatorId;
     	jobtitleGoods.creatorName = this.creatorName;
     	jobtitleGoods.createTime = this.createTime;
		return jobtitleGoods;
	}
	
	/**
	 * 比较字段：
	 * id, goodsId, jobtitleId, status, 
	 * creatorId, creatorName, createTime
	 */
	@Override
	public boolean test(JobtitleGoods t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.goodsId == null || this.goodsId.equals(t.goodsId))
				&& (this.jobtitleId == null || this.jobtitleId.equals(t.jobtitleId))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(JobtitleGoods element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.goodsId != null && !this.goodsId.isEmpty()) {
			element.goodsId = this.goodsId;
		}
		if (this.jobtitleId != null && !this.jobtitleId.isEmpty()) {
			element.jobtitleId = this.jobtitleId;
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
