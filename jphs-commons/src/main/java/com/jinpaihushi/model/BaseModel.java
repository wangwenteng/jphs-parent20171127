package com.jinpaihushi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author fengrz
 */
public abstract class BaseModel implements Serializable {

	protected static final Logger logger = LoggerFactory.getLogger(BaseModel.class);

	private static final long serialVersionUID = 1L;

	public static final Integer STATUS_DISABLED = -1;

	public static final Integer STATUS_DEFAULT = 0;

	public static final Integer TYPE_DEFAULT = 0;

	/**
	 * 对象ID
	 */
	protected String id;
	
	/**
	 * 创建时间
	 */
	protected Date createTime;

	/**
	 * 更新时间
	 */
	protected Date updateTime;

	/**
	 * 状态
	 */
	protected Integer status;

	/**
	 * 类型
	 */
	protected Integer type;

	/**
	 * 创建人ID
	 */
	protected String creatorId;

	/**
	 * 创建人名称
	 */
	protected String creatorName;

	/**
	 * 用作排序的字符串，不参与序列化
	 */
	private transient String orderby;

	/**
	 * 作为查询条件的ID序列，不参与序列化
	 */
	private transient String ids;
	
	private String paySign;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@JsonIgnore
	public String getOrderby() {
		return orderby;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public void setOrderby(String orderby) {
		if (orderby == null) {
			return;
		}
		if (!orderby.matches("((\\w+\\.)?\\w+\\s+(ASC|DESC|asc|desc)?\\s*,\\s*)*(\\w+\\.)?\\w+\\s+(ASC|DESC|asc|desc)?")) {
			throw new IllegalArgumentException("orderby:" + orderby);
		}
		this.orderby = orderby;
	}

	@JsonIgnore
	public String getIds() {
		return ids;
	}
	
	public String refreshPaySign() {
		paySign = RandomStringUtils.randomAlphanumeric(8);
		return paySign;
	}
	
	public String getPaySign() {
		return paySign;
	}

	public void setIds(Integer... ids) {
		if (ids == null || ids.length == 0) {
			return;
		}
		this.ids = StringUtils.join(ids, ",");
	}

	public void setIds(List<Integer> ids) {
		if (ids == null || ids.isEmpty()) {
			return;
		}
		this.ids = StringUtils.join(ids, ",");
	}

	public void setIds(String ids) {
		if (ids == null) {
			return;
		}
		if (!ids.matches("(\\d+,)*\\d+")) {
			throw new IllegalArgumentException("ids:" + ids);
		}
		this.ids = ids;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseModel other = (BaseModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
