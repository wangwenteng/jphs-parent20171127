package com.jinpaihushi.jphs.goods.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.model.PriceGrade;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.model.BaseModel;

/**
 * GOODS 继承自父类的字段: id : id(特殊,最好数字) type : 订单类型（0内部订单，1开放订单） status : 状态
 * createTime : 创建时间 creatorId : 创建人id creatorName : 创建人
 * 
 * @author scj
 * @date 2017-06-27 10:02:37
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Goods extends BaseModel implements Predicate<Goods>, Updator<Goods> {

	/** 平台id */
	@Length(max = 50, message = "{goods.productId.illegal.length}")
	private String productId;

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

	/** 体检类型id */
	@Length(max = 50, message = "{goods.tijianTypeId.illegal.length}")
	private String tijianTypeId;

	/** 体检医院id */
	@Length(max = 50, message = "{goods.tijianHospitalId.illegal.length}")
	private String tijianHospitalId;

	/** 是否需要服务工具 */
	private Integer tool;

	/** 服务类型，1-标准服务，2-等级服务 */
	private Integer gradeType;

	/** 是否需要上保险 */
	private Integer insurance;

	/** 排序 */
	private Integer sort;

	/** 是否有背景图 */
	private Integer isBg;
	/** 是否需要上传证明 1三张就医证明 2一张 3不需要 */
	private Integer isProve;

	/** 备注 */
	@Length(max = 255, message = "{goods.remark.illegal.length}")
	private String remark;

	/**
	 * 获取品类图片信息
	 */
	List<ServiceImages> serviceImagesList;
	/**
	 * 获取价格信息
	 */
	List<Price> priceList;

	/**
	 * 获取等级价格信息
	 */
	List<PriceGrade> priceGrade;
	
	List<Grade> grade;
	/**
	 * 用来存储平台但客户端的图片信息 eg：官网PC端的图片
	 */
	private ServiceImages images;

	private String url;
	
	private PricePart pricePart ;

	/**
	 * 获取职称列表
	 *//*
		 * List<Jobtitle> jobtitleList;
		 * 
		 * public List<Jobtitle> getJobtitle() { return jobtitleList; }
		 * 
		 * public void setJobtitle(List<Jobtitle> jobtitleList) {
		 * this.jobtitleList = jobtitleList; }
		 */

	public List<PriceGrade> getPriceGrade() {
		return priceGrade;
	}

	public void setPriceGrade(List<PriceGrade> priceGrade) {
		this.priceGrade = priceGrade;
	}

	/** 服务类型，1-标准服务，2-等级服务 */
	public Integer getGradeType() {
		return gradeType;
	}

	/** 服务类型，1-标准服务，2-等级服务 */
	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	public List<ServiceImages> getServiceImagesList() {
		return serviceImagesList;
	}

	public void setServiceImagesList(List<ServiceImages> serviceImages) {
		this.serviceImagesList = serviceImages;
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public void setMust(Integer must) {
		this.must = must;
	}

	public Integer getMust() {
		return must;
	}

	/**
	 * 品类
	 */
	private Product Product;

	public ServiceImages getImages() {
		return images;
	}

	public void setImages(ServiceImages images) {
		this.images = images;
	}

	public Goods() {
	}

	public Goods(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取副标题
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * 设置副标题
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * 获取文字内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置文字内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取体检类型id
	 */
	public String getTijianTypeId() {
		return tijianTypeId;
	}

	/**
	 * 设置体检类型id
	 */
	public void setTijianTypeId(String tijianTypeId) {
		this.tijianTypeId = tijianTypeId;
	}

	/**
	 * 获取体检医院id
	 */
	public String getTijianHospitalId() {
		return tijianHospitalId;
	}

	/**
	 * 设置体检医院id
	 */
	public void setTijianHospitalId(String tijianHospitalId) {
		this.tijianHospitalId = tijianHospitalId;
	}

	/**
	 * 获取是否需要服务工具
	 */
	public Integer getTool() {
		return tool;
	}

	/**
	 * 设置是否需要服务工具
	 */
	public void setTool(Integer tool) {
		this.tool = tool;
	}

	/**
	 * 获取是否需要上保险
	 */
	public Integer getInsurance() {
		return insurance;
	}

	/**
	 * 设置是否需要上保险
	 */
	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
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
	 * 获取是否有背景图标识
	 */
	public Integer getIsBg() {
		return isBg;
	}

	/**
	 * 设置是否有背景图标识
	 */
	public void setIsBg(Integer isBg) {
		this.isBg = isBg;
	}
	/**
	 * 获取是否需要上传证明 1三张就医证明 2一张 3不需要
	 */
	public Integer getIsProve() {
    	return isProve;
    }
  	
	/**
	 * 设置是否需要上传证明 1三张就医证明 2一张 3不需要
	 */
	public void setIsProve(Integer isProve) {
    	this.isProve = isProve;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	 public PricePart getPricePart() {
		return pricePart;
	}

	public void setPricePart(PricePart pricePart) {
		this.pricePart = pricePart;
	}
	
	

	public List<Grade> getGrade() {
		return grade;
	}

	public void setGrade(List<Grade> grade) {
		this.grade = grade;
	}

	public String toString() {
			return new StringBuilder().append("Goods{").
				append("id=").append(id).
				append(",productId=").append(productId).
				append(",title=").append(title).
				append(",subTitle=").append(subTitle).
				append(",content=").append(content).
				append(",tijianTypeId=").append(tijianTypeId).
				append(",tijianHospitalId=").append(tijianHospitalId).
				append(",tool=").append(tool).
				append(",insurance=").append(insurance).
				append(",must=").append(must).
				append(",type=").append(type).
				append(",gradeType=").append(gradeType).
				append(",sort=").append(sort).
				append(",isBg=").append(isBg).
				append(",isProve=").append(isProve).
				append(",remark=").append(remark).
				append(",status=").append(status).
				append(",createTime=").append(createTime).
				append(",creatorId=").append(creatorId).
				append(",creatorName=").append(creatorName).
				append('}').toString();
	    }
		
	    /**
		 * 复制字段：
		 * id, productId, title, subTitle, 
		 * content, tijianTypeId, tijianHospitalId, tool, 
		 * insurance, must, type, gradeType, 
		 * sort, isBg, isProve, remark, 
		 * status, createTime, creatorId, creatorName
		 */
		public Goods copy(){
			Goods goods = new Goods();
	     	goods.id = this.id;
	     	goods.productId = this.productId;
	     	goods.title = this.title;
	     	goods.subTitle = this.subTitle;
	     	goods.content = this.content;
	     	goods.tijianTypeId = this.tijianTypeId;
	     	goods.tijianHospitalId = this.tijianHospitalId;
	     	goods.tool = this.tool;
	     	goods.insurance = this.insurance;
	     	goods.must = this.must;
	     	goods.type = this.type;
	     	goods.gradeType = this.gradeType;
	     	goods.sort = this.sort;
	     	goods.isBg = this.isBg;
	     	goods.isProve = this.isProve;
	     	goods.remark = this.remark;
	     	goods.status = this.status;
	     	goods.createTime = this.createTime;
	     	goods.creatorId = this.creatorId;
	     	goods.creatorName = this.creatorName;
			return goods;
		}
		
		/**
		 * 比较字段：
		 * id, productId, title, subTitle, 
		 * content, tijianTypeId, tijianHospitalId, tool, 
		 * insurance, must, type, gradeType, 
		 * sort, isBg, isProve, remark, 
		 * status, createTime, creatorId, creatorName
		 */
		@Override
		public boolean test(Goods t) {
			if(t == null) return false;
			return (this.id == null || this.id.equals(t.id))
					&& (this.productId == null || this.productId.equals(t.productId))
					&& (this.title == null || this.title.equals(t.title))
					&& (this.subTitle == null || this.subTitle.equals(t.subTitle))
					&& (this.content == null || this.content.equals(t.content))
					&& (this.tijianTypeId == null || this.tijianTypeId.equals(t.tijianTypeId))
					&& (this.tijianHospitalId == null || this.tijianHospitalId.equals(t.tijianHospitalId))
					&& (this.tool == null || this.tool.equals(t.tool))
					&& (this.insurance == null || this.insurance.equals(t.insurance))
					&& (this.must == null || this.must.equals(t.must))
					&& (this.type == null || this.type.equals(t.type))
					&& (this.gradeType == null || this.gradeType.equals(t.gradeType))
					&& (this.sort == null || this.sort.equals(t.sort))
					&& (this.isBg == null || this.isBg.equals(t.isBg))
					&& (this.isProve == null || this.isProve.equals(t.isProve))
					&& (this.remark == null || this.remark.equals(t.remark))
					&& (this.status == null || this.status.equals(t.status))
					&& (this.createTime == null || this.createTime.equals(t.createTime))
					&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
					&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
			;
		}
		
		@Override
		public void update(Goods element) {
			if (element == null)
				return;
			if (this.id != null && !this.id.isEmpty()) {
				element.id = this.id;
			}
			if (this.productId != null && !this.productId.isEmpty()) {
				element.productId = this.productId;
			}
			if (this.title != null && !this.title.isEmpty()) {
				element.title = this.title;
			}
			if (this.subTitle != null && !this.subTitle.isEmpty()) {
				element.subTitle = this.subTitle;
			}
			if (this.content != null && !this.content.isEmpty()) {
				element.content = this.content;
			}
			if (this.tijianTypeId != null && !this.tijianTypeId.isEmpty()) {
				element.tijianTypeId = this.tijianTypeId;
			}
			if (this.tijianHospitalId != null && !this.tijianHospitalId.isEmpty()) {
				element.tijianHospitalId = this.tijianHospitalId;
			}
			if (this.tool != null) {
				element.tool = this.tool;
			}
			if (this.insurance != null) {
				element.insurance = this.insurance;
			}
			if (this.must != null) {
				element.must = this.must;
			}
			if (this.type != null) {
				element.type = this.type;
			}
			if (this.gradeType != null) {
				element.gradeType = this.gradeType;
			}
			if (this.sort != null) {
				element.sort = this.sort;
			}
			if (this.isBg != null) {
				element.isBg = this.isBg;
			}
			if (this.isProve != null) {
				element.isProve = this.isProve;
			}
			if (this.remark != null && !this.remark.isEmpty()) {
				element.remark = this.remark;
			}
			if (this.status != null) {
				element.status = this.status;
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
