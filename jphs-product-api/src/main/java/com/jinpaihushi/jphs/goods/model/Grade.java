package com.jinpaihushi.jphs.goods.model;

import java.util.List;

import com.jinpaihushi.model.BaseModel;

/**
 * 服务详情中的服务等级对象
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class Grade extends BaseModel{
	private String name;
	private Integer grade;
	private List<GoodsPrice> goodsPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public List<GoodsPrice> getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(List<GoodsPrice> goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

}
