package com.jinpaihushi.jphs.goods.model;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.model.BaseModel;

@SuppressWarnings("serial")
public class Images extends BaseModel{
	/** 图片地址 */
	@Length(max = 500, message = "{serviceImages.url.illegal.length}")
	private String url;
	/** duan */
	private Integer device_type;
	/** 说明 */
	@Length(max = 255, message = "{serviceImages.remarks.illegal.length}")
	private String remarks;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDevice_type() {
		return device_type;
	}
	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
