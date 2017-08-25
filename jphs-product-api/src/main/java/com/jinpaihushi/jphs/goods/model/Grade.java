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
	private String siUrl;
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

	public String getSiUrl() {
		return siUrl;
	}

	public void setSiUrl(String siUrl) {
		this.siUrl = siUrl;
	}

	
	public static void main(String[] args) {
		
		String a1 = "22,333";
		String a2 = "22";
		
		String[] split = a1.split(",");
		String[] split2 = a2.split(",");
		System.out.println(split.length);
		System.out.println(split2.length);
	}
	
}
