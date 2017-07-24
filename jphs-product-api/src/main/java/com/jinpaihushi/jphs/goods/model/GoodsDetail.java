package com.jinpaihushi.jphs.goods.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.model.BaseModel;

@SuppressWarnings("serial")
public class GoodsDetail extends BaseModel {
	/** 平台id */
	@Length(max = 50, message = "{goods.productId.illegal.length}")
	private String productId;
	/** 平台name */
	@Length(max = 50, message = "{goods.productId.illegal.length}")
	private String productName;

	/** 标题 */
	@Length(max = 100, message = "{goods.title.illegal.length}")
	private String title;

	/** 标题 */
	private Integer must;

	/** 副标题 */
	@Length(max = 100, message = "{goods.subTitle.illegal.length}")
	private String subTitle;

	/** 文字内容 */
	@Length(max = 65535, message = "{goods.content.illegal.length}")
	private String content;
	/** 是否需要服务工具 */
	private Integer tool;

	/** 服务类型，1-标准服务，2-等级服务 */
	private Integer gradeType;

	/** 是否需要上保险 */
	private Integer insurance;

	/** 备注 */
	@Length(max = 255, message = "{goods.remark.illegal.length}")
	private String remark;
	
	/**
	 * 用来存储平台但客户端的图片信息
	 * eg：官网PC端的图片  
	 */
	private Images images;
	
	private List<Grade> grade;
	/**
	 * 订单数量
	 */
	private Integer orderNumer =0;
	private Integer level;
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getMust() {
		return must;
	}

	public void setMust(Integer must) {
		this.must = must;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTool() {
		return tool;
	}

	public void setTool(Integer tool) {
		this.tool = tool;
	}

	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public List<Grade> getGrade() {
		return grade;
	}

	public void setGrade(List<Grade> grade) {
		this.grade = grade;
	}

	public Integer getOrderNumer() {
		return orderNumer;
	}

	public void setOrderNumer(Integer orderNumer) {
		this.orderNumer = orderNumer;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
		
}