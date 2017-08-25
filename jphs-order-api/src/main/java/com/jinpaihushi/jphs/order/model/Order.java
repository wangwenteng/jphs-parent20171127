package com.jinpaihushi.jphs.order.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.goods.model.Goods;
<<<<<<< HEAD
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.model.BaseModel;

/**
 * ORDER 继承自父类的字段: id : status : createTime : creatorId :
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Order extends BaseModel implements Predicate<Order>, Updator<Order> {

    /** 订单号 */
    @Length(max = 255, message = "{order.orderNo.illegal.length}")
    private String orderNo;

    /** 优惠券价格 */
    private Double voucherPrice;

    /** 优惠券id */
    @Length(max = 50, message = "{order.voucherUseId.illegal.length}")
    private String voucherUseId;

    /** 平台id */
    @Length(max = 50, message = "{order.platformId.illegal.length}")
    private String platformId;

    /** 指定人 */
    @Length(max = 50, message = "{order.expectorId.illegal.length}")
    private String expectorId;

    /** 接单人 */
    @Length(max = 50, message = "{order.acceptUserId.illegal.length}")
    private String acceptUserId;

    /** 分享人id 推荐人  */
    @Length(max = 50, message = "{order.recommendId.illegal.length}")
    private String recommendId;

    /** 预约时间 */
    private Date appointmentTime;

    /** 接单时间 */
    private Date acceptTime;

    /** 交易id */
    @Length(max = 50, message = "{order.transactionId.illegal.length}")
    private String transactionId;

    /** 进度(0:待支付,1:待接单,2:已接单,3:执行中,4:待确定,5:已完成,6:已取消,7:申诉中) */
    private Integer schedule;

    /** 下单设备/来源 pc=5 */
    private Integer device;

    /** 备注 */
    @Length(max = 500, message = "{order.remarks.illegal.length}")
    private String remarks;

    private OrderGoods orderGoods;

    private OrderService orderService;

    private User user;

    private Goods goods;

    private Transaction transaction;

    private String phone;

    private Integer type;

    private String title;

    private String nurseName;

    private String userName;

    private String nursePhone;

    private String beginTime;

    private String stopTime;

    private String url;

    private String detailAddress;

    private Integer orderServiceNumber;

    /** 销售价 */
    private Double price;

    /** 实付金额 */
    private Double payPrice;

    private Double discountPrice;

    private List<OrderService> orderServiceList;

    private OrderOther orderOther;
    
    private List<ServiceImages> serviceOrderImages;

    public Integer getOrderServiceNumber() {
        return orderServiceNumber;
    }

    public void setOrderServiceNumber(Integer orderServiceNumber) {
        this.orderServiceNumber = orderServiceNumber;
    }

    public  List<ServiceImages>  getServiceOrderImages() {
        return serviceOrderImages;
    }

    public void setServiceOrderImages( List<ServiceImages>  serviceOrderImages) {
        this.serviceOrderImages = serviceOrderImages;
    }
    
=======
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.model.BaseModel;

/**
 * ORDER 继承自父类的字段: id : status : createTime : creatorId :
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Order extends BaseModel implements Predicate<Order>, Updator<Order> {

    /** 订单号 */
    @Length(max = 255, message = "{order.orderNo.illegal.length}")
    private String orderNo;

    /** 优惠券价格 */
    private Double voucherPrice;

    /** 优惠券id */
    @Length(max = 50, message = "{order.voucherUseId.illegal.length}")
    private String voucherUseId;

    /** 平台id */
    @Length(max = 50, message = "{order.platformId.illegal.length}")
    private String platformId;

    /** 指定人 */
    @Length(max = 50, message = "{order.expectorId.illegal.length}")
    private String expectorId;

    /** 接单人 */
    @Length(max = 50, message = "{order.acceptUserId.illegal.length}")
    private String acceptUserId;

    /** 分享人id 推荐人  */
    @Length(max = 50, message = "{order.recommendId.illegal.length}")
    private String recommendId;

    /** 预约时间 */
    private Date appointmentTime;

    /** 接单时间 */
    private Date acceptTime;

    /** 交易id */
    @Length(max = 50, message = "{order.transactionId.illegal.length}")
    private String transactionId;

    /** 进度(0:待支付,1:待接单,2:已接单,3:执行中,4:待确定,5:已完成,6:已取消,7:申诉中) */
    private Integer schedule;

    /** 下单设备/来源 pc=5 */
    private Integer device;

    /** 备注 */
    @Length(max = 500, message = "{order.remarks.illegal.length}")
    private String remarks;

    private OrderGoods orderGoods;

    private OrderService orderService;

    private User user;

    private Goods goods;

    private Transaction transaction;

    private String phone;

    private Integer type;

    private String title;

    private String nurseName;

    private String userName;

    private String nursePhone;

    private String beginTime;

    private String stopTime;

    private String url;

    private String detailAddress;

    private Integer orderServiceNumber;

    /** 销售价 */
    private Double price;

    /** 实付金额 */
    private Double payPrice;

    private Double discountPrice;

    private List<OrderService> orderServiceList;

    private OrderOther orderOther;

    public Integer getOrderServiceNumber() {
        return orderServiceNumber;
    }

    public void setOrderServiceNumber(Integer orderServiceNumber) {
        this.orderServiceNumber = orderServiceNumber;
    }

>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
    public OrderOther getOrderOther() {
        return orderOther;
    }

    public void setOrderOther(OrderOther orderOther) {
        this.orderOther = orderOther;
    }

    public Order() {
    }

    public Order(String id) {
        this.id = id;
    }

    /**
     * 获取订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    /**
     * 设置订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取优惠券价格
     */
    public Double getVoucherPrice() {
        return voucherPrice;
    }

    /**
     * 设置优惠券价格
     */
    public void setVoucherPrice(Double voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    /**
     * 获取优惠券id
     */
    public String getVoucherUseId() {
        return voucherUseId;
    }

    /**
     * 设置优惠券id
     */
    public void setVoucherUseId(String voucherUseId) {
        this.voucherUseId = voucherUseId;
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
     * 获取指定人
     */
    public String getExpectorId() {
        return expectorId;
    }

    /**
     * 设置指定人
     */
    public void setExpectorId(String expectorId) {
        this.expectorId = expectorId;
    }

    /**
     * 获取接单人
     */
    public String getAcceptUserId() {
        return acceptUserId;
    }

    /**
     * 设置接单人
     */
    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    /**
     * 获取分享人id 推荐人 
     */
    public String getRecommendId() {
        return recommendId;
    }

    /**
     * 设置分享人id 推荐人 
     */
    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    /**
     * 获取预约时间
     */
    public Date getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * 设置预约时间
     */
    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * 获取接单时间
     */
    public Date getAcceptTime() {
        return acceptTime;
    }

    /**
     * 设置接单时间
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * 获取交易id
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 设置交易id
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 获取进度
     */
    public Integer getSchedule() {
        return schedule;
    }

    /**
     * 设置进度
     */
    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    /**
     * 获取下单设备/来源
     */
    public Integer getDevice() {
        return device;
    }

    /**
     * 设置下单设备/来源
     */
    public void setDevice(Integer device) {
        this.device = device;
    }

    /**
     * 获取备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public OrderGoods getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(OrderGoods orderGoods) {
        this.orderGoods = orderGoods;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNursePhone() {
        return nursePhone;
    }

    public void setNursePhone(String nursePhone) {
        this.nursePhone = nursePhone;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public List<OrderService> getOrderServiceList() {
        return orderServiceList;
    }

    public void setOrderServiceList(List<OrderService> orderServiceList) {
        this.orderServiceList = orderServiceList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String toString() {
        return new StringBuilder().append("Order{").append("id=").append(id).append(",orderNo=").append(orderNo)
                .append(",voucherPrice=").append(voucherPrice).append(",voucherUseId=").append(voucherUseId)
                .append(",platformId=").append(platformId).append(",expectorId=").append(expectorId)
                .append(",acceptUserId=").append(acceptUserId).append(",recommendId=").append(recommendId)
                .append(",appointmentTime=").append(appointmentTime).append(",acceptTime=").append(acceptTime)
                .append(",transactionId=").append(transactionId).append(",schedule=").append(schedule)
                .append(",device=").append(device).append(",remarks=").append(remarks).append(",status=").append(status)
                .append(",createTime=").append(createTime).append(",creatorId=").append(creatorId).append('}')
                .toString();
    }

    /**
     * 复制字段：
     * id, orderNo, voucherPrice, voucherUseId, 
     * platformId, expectorId, acceptUserId, recommendId, 
     * appointmentTime, acceptTime, transactionId, schedule, 
     * device, remarks, status, createTime, 
     * creatorId
     */
    public Order copy() {
        Order order = new Order();
        order.id = this.id;
        order.orderNo = this.orderNo;
        order.voucherPrice = this.voucherPrice;
        order.voucherUseId = this.voucherUseId;
        order.platformId = this.platformId;
        order.expectorId = this.expectorId;
        order.acceptUserId = this.acceptUserId;
        order.recommendId = this.recommendId;
        order.appointmentTime = this.appointmentTime;
        order.acceptTime = this.acceptTime;
        order.transactionId = this.transactionId;
        order.schedule = this.schedule;
        order.device = this.device;
        order.remarks = this.remarks;
        order.status = this.status;
        order.createTime = this.createTime;
        order.creatorId = this.creatorId;
        return order;
    }

    /**
     * 比较字段：
     * id, orderNo, voucherPrice, voucherUseId, 
     * platformId, expectorId, acceptUserId, recommendId, 
     * appointmentTime, acceptTime, transactionId, schedule, 
     * device, remarks, status, createTime, 
     * creatorId
     */
    @Override
    public boolean test(Order t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.orderNo == null || this.orderNo.equals(t.orderNo))
                && (this.voucherPrice == null || this.voucherPrice.equals(t.voucherPrice))
                && (this.voucherUseId == null || this.voucherUseId.equals(t.voucherUseId))
                && (this.platformId == null || this.platformId.equals(t.platformId))
                && (this.expectorId == null || this.expectorId.equals(t.expectorId))
                && (this.acceptUserId == null || this.acceptUserId.equals(t.acceptUserId))
                && (this.recommendId == null || this.recommendId.equals(t.recommendId))
                && (this.appointmentTime == null || this.appointmentTime.equals(t.appointmentTime))
                && (this.acceptTime == null || this.acceptTime.equals(t.acceptTime))
                && (this.transactionId == null || this.transactionId.equals(t.transactionId))
                && (this.schedule == null || this.schedule.equals(t.schedule))
                && (this.device == null || this.device.equals(t.device))
                && (this.remarks == null || this.remarks.equals(t.remarks))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId));
    }

    @Override
    public void update(Order element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.orderNo != null && !this.orderNo.isEmpty()) {
            element.orderNo = this.orderNo;
        }
        if (this.voucherPrice != null) {
            element.voucherPrice = this.voucherPrice;
        }
        if (this.voucherUseId != null && !this.voucherUseId.isEmpty()) {
            element.voucherUseId = this.voucherUseId;
        }
        if (this.platformId != null && !this.platformId.isEmpty()) {
            element.platformId = this.platformId;
        }
        if (this.expectorId != null && !this.expectorId.isEmpty()) {
            element.expectorId = this.expectorId;
        }
        if (this.acceptUserId != null && !this.acceptUserId.isEmpty()) {
            element.acceptUserId = this.acceptUserId;
        }
        if (this.recommendId != null && !this.recommendId.isEmpty()) {
            element.recommendId = this.recommendId;
        }
        if (this.appointmentTime != null) {
            element.appointmentTime = this.appointmentTime;
        }
        if (this.acceptTime != null) {
            element.acceptTime = this.acceptTime;
        }
        if (this.transactionId != null && !this.transactionId.isEmpty()) {
            element.transactionId = this.transactionId;
        }
        if (this.schedule != null) {
            element.schedule = this.schedule;
        }
        if (this.device != null) {
            element.device = this.device;
        }
        if (this.remarks != null && !this.remarks.isEmpty()) {
            element.remarks = this.remarks;
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
    }
}
