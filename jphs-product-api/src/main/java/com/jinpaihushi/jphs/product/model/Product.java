package com.jinpaihushi.jphs.product.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.model.BaseModel;

/**
 * PRODUCT 继承自父类的字段: id : id status : 状态 createTime : 创建时间 creatorId : 创建人id
 * creatorName : 创建人姓名
 * 
 * @author scj
 * @date 2017-06-27 09:38:05
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Product extends BaseModel implements Predicate<Product>, Updator<Product> {

	/** 平台id */
	@Length(max = 50, message = "{product.platformId.illegal.length}")
	private String platformId;

	/** 产品标题 */
	@Length(max = 50, message = "{product.title.illegal.length}")
	private String title;

	/** 排序 */
	private Integer sort;

	/** 备注 */
	@Length(max = 255, message = "{product.remark.illegal.length}")
	private String remark;

	/**
	 * 获取品类图片信息
	 */
	// private ServiceImages ServiceImages;
	List<ServiceImages> serviceImagesList;
	private String url;
	List<Goods> goodsList;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<ServiceImages> getServiceImagesList() {
		return serviceImagesList;
	}

	public void setServiceImagesList(List<ServiceImages> serviceImages) {
		this.serviceImagesList = serviceImages;
	}

	public Product() {
	}

	public Product(String id) {
		this.id = id;
	}

	/**
	 * 获取平台id
	 */
	public String getPlatformId() {
		return platformId;
	}

	/**
	 * 设置平台id
	 */
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	/**
	 * 获取产品标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置产品标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		return new StringBuilder().append("Product{").append("id=").append(id).append(",platformId=").append(platformId)
				.append(",title=").append(title).append(",sort=").append(sort).append(",status=").append(status)
				.append(",remark=").append(remark).append(",createTime=").append(createTime).append(",creatorId=")
				.append(creatorId).append(",creatorName=").append(creatorName).append('}').toString();
	}

	/**
	 * 复制字段： id, platformId, title, sort, status, remark, createTime, creatorId,
	 * creatorName
	 */
	public Product copy() {
		Product product = new Product();
		product.id = this.id;
		product.platformId = this.platformId;
		product.title = this.title;
		product.sort = this.sort;
		product.status = this.status;
		product.remark = this.remark;
		product.createTime = this.createTime;
		product.creatorId = this.creatorId;
		product.creatorName = this.creatorName;
		return product;
	}

	/**
	 * 比较字段： id, platformId, title, sort, status, remark, createTime, creatorId,
	 * creatorName
	 */
	@Override
	public boolean test(Product t) {
		if (t == null)
			return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.platformId == null || this.platformId.equals(t.platformId))
				&& (this.title == null || this.title.equals(t.title)) && (this.sort == null || this.sort.equals(t.sort))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName));
	}

	@Override
	public void update(Product element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.platformId != null && !this.platformId.isEmpty()) {
			element.platformId = this.platformId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.sort != null) {
			element.sort = this.sort;
		}
		if (this.status != null) {
			element.status = this.status;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
	}
}
