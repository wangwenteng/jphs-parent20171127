package com.jinpaihushi.jphs.order.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.dao.AccountDao;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.cancel.dao.CancelOrderDao;
import com.jinpaihushi.jphs.cancel.model.CancelOrder;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.insurance.dao.InsuranceDao;
import com.jinpaihushi.jphs.insurance.model.Insurance;
import com.jinpaihushi.jphs.jobtitle.dao.JobtitleGoodsDao;
import com.jinpaihushi.jphs.nurse.dao.NurseDao;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.order.dao.OrderDao;
import com.jinpaihushi.jphs.order.dao.OrderGoodsDao;
import com.jinpaihushi.jphs.order.dao.OrderOtherDao;
import com.jinpaihushi.jphs.order.dao.OrderServiceDao;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.model.OrderInfo;
import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.jphs.order.model.OrderPojo;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.price.dao.PriceDao;
import com.jinpaihushi.jphs.price.dao.PriceNurseDao;
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.model.PriceNurse;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.push.service.NurseJPushService;
import com.jinpaihushi.jphs.service.dao.ServiceImagesDao;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.dao.UserAddressDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.voucher.dao.VoucherRepertoryDao;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.jphs.worktime.dao.WorktimeDao;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private OrderGoodsDao orderGoodsDao;

    @Autowired
    private OrderOtherDao orderOtherDao;

    @Autowired
    private OrderServiceDao orderServiceDao;

    @Autowired
    private ServiceImagesDao serviceImagesDao;

    @Autowired
    private CancelOrderDao cancelOrderDao;

    @Autowired
    private InsuranceDao insuranceDao;

    @Autowired
    private VoucherUseDao voucherUseDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private PricePartDao pricePartDao;

    @Autowired
    private VoucherRepertoryDao voucherRepertoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private NurseDao nurseDao;

    @Autowired
    private JobtitleGoodsDao jobtitleGoodsDao;

    @Autowired
    private PriceDao priceDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private PriceNurseDao priceNurseDao;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private DoPostSmsService doPostSmsService;

    @Autowired
    private NurseJPushService nurseJPushService;

    @Autowired
    private WorktimeDao worktimeDao;

    //#护士接单
    @Value("${SMS_Nurse_orders}")
    private String SMS_Nurse_orders;

    //#护士接单
    @Value("${SMS_Nurse_order}")
    private String SMS_Nurse_order;

    //     #给护士派单（上门服务）
    @Value("${SMS_nurse_delivery_order}")
    private String SMS_nurse_delivery_order;

    //支付成功
    @Value("${SMS_pay_success}")
    private String SMS_pay_success;

    //通知新订单
    @Value("${SMS_notice_order}")
    private String SMS_notice_order;

    //支付成功
    @Value("${SMS_cancel_order}")
    private String SMS_cancel_order;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected BaseDao<Order> getDao() {
        return orderDao;
    }

    @Override
    public Page<Order> getList(Order order) {
        return orderDao.getList(order);
    }

    /**
     * 创建订单
     * 
     * @param order
     *            订单及相关信息
     * @param insurance
     *            保险的信息
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> createOrder(OrderInfo orderInfo) {
        Map<String, Object> result = new HashMap<>();
        // 判断OrderInfo 中的一些信息
        // 先判断是否需要上传就医证明
        Goods goods = null;
        String[] images = null;
        if (StringUtils.isEmpty(orderInfo.getGoodsId())) {
            goods = goodsDao.loadById(orderInfo.getGoodsId());
            // 如果IsProve =3 不需要上传 2需要一张 1 需要三张
            if (goods.getIsProve() != 3) {
                images = orderInfo.getImages().split(",");
                if (goods.getIsProve() == 2) {
                    if (images.length != 1)
                        return null;
                }
                if (goods.getIsProve() == 1) {
                    if (images.length < 3)
                        return null;
                }
            }
        }
        try {
            // 插入订单的基础信息
            Order order = new Order();
            BeanUtils.copyProperties(order, orderInfo);
            order.setId(UUIDUtils.getId());
            order.setOrderNo(Common.getOrderNumber());
            order.setSchedule(0);
            order.setStatus(1);
            order.setCreateTime(new Date());
            int i = orderDao.insert(order);
            if (i > 0) {
                // 添加订单的商品信息
                OrderGoods orderGoods = new OrderGoods();
                BeanUtils.copyProperties(orderGoods, orderInfo);
                orderGoods.setId(UUIDUtils.getId());
                orderGoods.setCreateTime(new Date());
                orderGoods.setOrderId(order.getId());
                orderGoods.setRemark(orderInfo.getRemarks());
                orderGoods.setStatus(1);
                i = orderGoodsDao.insert(orderGoods);
                if (i > 0) {
                    // 添加订单的其他信息
                    OrderOther orderOther = new OrderOther();
                    BeanUtils.copyProperties(orderOther, orderInfo);
                    orderOther.setId(UUIDUtils.getId());
                    orderOther.setStatus(0);
                    orderOther.setCreateTime(new Date());
                    orderOther.setOrderId(order.getId());
                    i = orderOtherDao.insert(orderOther);
                    // 插入图片
                    ServiceImages serviceImages = new ServiceImages();
                    serviceImages.setUrl(orderInfo.getOrder_img());
                    serviceImages.setId(UUID.randomUUID().toString());
                    serviceImages.setCreatorId(order.getCreatorId());
                    serviceImages.setCreatorName(order.getCreatorName());
                    serviceImages.setCreateTime(new Date());
                    serviceImages.setDevice_type(1);
                    serviceImages.setSourceId(order.getId());
                    serviceImages.setStatus(1);
                    serviceImagesDao.insert(serviceImages);
                    if (i > 0) {
                        com.jinpaihushi.jphs.order.model.OrderService orderService = null;
                        for (int j = 0; j < orderInfo.getServiceNumber(); j++) {
                            orderService = new com.jinpaihushi.jphs.order.model.OrderService();
                            BeanUtils.copyProperties(orderService, orderInfo);
                            orderService.setAppointmentTime(orderInfo.getAppointmentTime());
                            orderService.setPrice(orderInfo.getOnePrice());
                            orderService.setOrderId(order.getId());
                            orderService.setId(UUIDUtils.getId());
                            orderService.setSchedule(0);
                            orderService.setStatus(1);
                            orderServiceDao.insert(orderService);
                        }
                        // 插入相关就医证明的图片
                        if (goods.getIsProve() != 3) {
                            images = orderInfo.getImages().split(",");
                            for (String string : images) {
                                serviceImages = new ServiceImages();
                                serviceImages.setUrl(string);
                                serviceImages.setId(UUID.randomUUID().toString());
                                serviceImages.setCreatorId(order.getCreatorId());
                                serviceImages.setCreatorName(order.getCreatorName());
                                serviceImages.setCreateTime(new Date());
                                serviceImages.setDevice_type(2);
                                serviceImages.setSourceId(order.getId());
                                serviceImages.setStatus(1);
                                serviceImagesDao.insert(serviceImages);
                            }
                        }
                        // 添加保险信息
                        if (goods.getInsurance() == 1) {
                            Insurance insurance = new Insurance();
                            BeanUtils.copyProperties(insurance, orderInfo);
                            insurance.setOrderId(order.getId());
                            insurance.setId(UUIDUtils.getId());
                            insurance.setStatus(1);
                            insurance.setCreateTime(new Date());
                            insuranceDao.insert(insurance);
                        }
                        if (StringUtils.isNotEmpty(orderInfo.getVoucherUseId())) {
                            // 修改优惠券的状态
                            VoucherUse use = new VoucherUse();
                            use.setId(orderInfo.getVoucherUseId());
                            use.setStatus(1);
                            use.setUseTime(new Date());
                            voucherUseDao.update(use);
                        }
                        result.put("orderId", order.getId());
                        result.put("orderNo", order.getOrderNo());
                        result.put("payParice", orderGoods.getPayPrice());
                        result.put("goodsName", orderInfo.getTitle());
                        return result;
                    }
                }
            }
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用的个人中心的订单列表
     * 
     * @param user
     *            用户对象
     * @return
     */
    @Override
    public List<Map<String, Object>> getUserOrder(Map<String, Object> query) {
        List<Map<String, Object>> userOrder = orderDao.getUserOrder(query);
        return userOrder;
    }

    /**
     * 查询订单详情
     * 
     * @param userId
     *            用户id
     * @return
     */
    @Override
    public Order getUserOrderDetail(String orderId, Integer deviceType) {
        // 获取订单的基本信息
        Order order = new Order();
        order.setId(orderId);
        Order orderDetail = orderDao.getUserOrderDetail(order);
        // 查询订单的服务情况
        com.jinpaihushi.jphs.order.model.OrderService orderService = new com.jinpaihushi.jphs.order.model.OrderService();
        orderService.setOrderId(orderId);
        orderService.setStatus(1);
        orderService.setOrderby("schedule DESC");
        List<com.jinpaihushi.jphs.order.model.OrderService> serviceList = orderServiceDao.list(orderService);
        if (orderDetail != null)
            orderDetail.setOrderServiceList(serviceList);
        return orderDetail;
    }

    @Override
    public boolean checkPrice(String orderNo, Double payParice) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        Order orderDetail = orderDao.getUserOrderDetail(order);
        if (DoubleUtils.sub(orderDetail.getOrderGoods().getPayPrice(), payParice) == 0)
            return true;
        return false;
    }

    public List<Map<String, Object>> getUptoDataGoods(Map<String, Object> map) {
        return orderDao.getUptoDataGoods(map);
    }

    public List<Map<String, Object>> getOrderGoodsList(Map<String, Object> map) {
        List<Map<String, Object>> q_order_list = orderDao.orderNotList(map);
        if (q_order_list.size() < 10) {
            Map<String, Object> q_o_map_s = new HashMap<String, Object>();
            q_o_map_s.put("status", 1);
            q_o_map_s.put("type", 1);
            List<Map<String, Object>> q_order_list_s = orderDao.orderaccNotNullList(q_o_map_s);
            if (q_order_list_s.size() > 0) {
                int size_l = 10 - q_order_list.size();
                if (size_l > q_order_list_s.size()) {
                    q_order_list.addAll(q_order_list_s);
                }
                else {
                    q_order_list.addAll(q_order_list_s.subList(0, size_l));
                }
            }
        }

        return q_order_list;
    }

    public List<Map<String, Object>> nurseOrderList(Map<String, Object> map) {
        return orderDao.nurseOrderList(map);
    }

    public List<Map<String, Object>> orderaccNotNullList(Map<String, Object> map) {
        return orderDao.orderaccNotNullList(map);
    }

    /**
     * 订单详情
     * 
     * @param order
     * @return
     */
    public Order nurseOrderDetails(Order order) {
        return orderDao.nurseOrderDetails(order);
    }

    @Override
    public Map<String, Object> createOrderNew(OrderPojo orderInfo) {

        Map<String, Object> result = new HashMap<>();
        // 详细价格
        PricePart pricePart = pricePartDao.loadById(orderInfo.getPricePartId());

        // 用户优惠券的价格

        User user = userDao.loadById(orderInfo.getCreatorId());
        if (user == null)
            return null;
        Order order = new Order();
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            //事务模板

            public String doInTransaction(final TransactionStatus status) {
                int i = 0;
                try {

                    // 先获取商品的基本信息
                    Goods goods = goodsDao.getGoodsByPricePart(orderInfo.getPricePartId());
                    Double salePrice = 0.00;
                    Price price = priceDao.loadById(pricePart.getPriceId());
                    if (StringUtils.isNotEmpty(orderInfo.getExpectorId())) {
                        PriceNurse priceNurse = new PriceNurse();
                        priceNurse.setPricePartId(orderInfo.getPricePartId());
                        priceNurse.setGoodsId(goods.getId());
                        priceNurse.setStatus(0);
                        priceNurse.setCreatorId(orderInfo.getExpectorId());
                        PriceNurse resultPrice = priceNurseDao.load(priceNurse);
                        salePrice = resultPrice.getPrice();
                    }
                    else {
                        salePrice = pricePart.getPrice();
                    }
                    // 获取价格基本信息
                    //判断利润格式
                    Double profit = pricePart.getProfit();
                    Double nursePrice = 0.00;
                    if (profit > 1) {
                        if (salePrice > pricePart.getPrice()) {
                            nursePrice = DoubleUtils.sub(pricePart.getPrice(), pricePart.getProfit());
                            nursePrice = DoubleUtils.add(nursePrice,
                                    DoubleUtils.mul(DoubleUtils.sub(salePrice, pricePart.getPrice()), 0.75));
                        }
                        else {
                            nursePrice = DoubleUtils.sub(salePrice, pricePart.getProfit());
                        }
                    }
                    else {
                        if (salePrice > pricePart.getPrice()) {
                            nursePrice = DoubleUtils.sub(pricePart.getPrice(), DoubleUtils.mul(salePrice, profit));
                            nursePrice = DoubleUtils.add(nursePrice,
                                    DoubleUtils.mul(DoubleUtils.sub(salePrice, pricePart.getPrice()), 0.75));
                        }
                        else {
                            nursePrice = DoubleUtils.sub(salePrice, DoubleUtils.mul(salePrice, profit));
                        }
                    }
                    Double onePrice = DoubleUtils.div(nursePrice, price.getServiceNumber(), 2);
                    //判断是否有订单id
                    if (StringUtils.isNotEmpty(orderInfo.getOrderId())) {
                        //判断订单的支付状态
                        Order results = orderDao.loadById(orderInfo.getId());
                        if (null != results && results.getSchedule() == 1) {
                            return "0";
                        }
                        orderDao.deleteById(orderInfo.getOrderId());
                        orderGoodsDao.deleteByOrderId(orderInfo.getOrderId());
                        orderOtherDao.deleteByOrderId(orderInfo.getOrderId());
                        orderServiceDao.deleteByOrderId(orderInfo.getOrderId());
                    }
                    Double voucherUsePrice = 0.00;
                    if (StringUtils.isNotEmpty(orderInfo.getVoucherUseId())) {
                        String nurseId = null;
                        if (StringUtils.isNotEmpty(orderInfo.getExpectorId())) {
                            nurseId = orderInfo.getExpectorId();
                        }
                        voucherUsePrice = voucherService.getGoodsPrice(orderInfo.getVoucherUseId(),
                                orderInfo.getPricePartId(), nurseId);
                    }
                    String address = "";
                    String detailAddress = "";
                    UserAddress userAddress = null;
                    if (StringUtils.isNotEmpty(orderInfo.getAddressId())) {
                        // 获取地址
                        userAddress = userAddressDao.loadById(orderInfo.getAddressId());
                        address = userAddress.getProvince() + "," + userAddress.getCity() + "," + userAddress.getArea();
                        detailAddress = userAddress.getDetailaddress();
                    }
                    if (StringUtils.isNotEmpty(orderInfo.getHospital())) {
                        address = "";
                        detailAddress = "";
                    }
                    if (StringUtils.isNotEmpty(orderInfo.getProvince())) {
                        address = orderInfo.getProvince();
                        detailAddress = orderInfo.getAddress();
                    }

                    String[] images = null;
                    //判断就医证明
                    if (goods.getIsProve() != 3) {
                        images = orderInfo.getImages().split(",");
                        if (images.length <= 0)
                            return "2";
                    }
                    // 判断商品的信息中是否需要工具药品
                    if (goods.getDzTool() == 1) {
                        if (StringUtils.isEmpty(orderInfo.getDrug())) {
                            return "4";
                        }
                    }
                    if (goods.getHlTool() == 1) {
                        if (StringUtils.isEmpty(orderInfo.getTool())) {
                            return "4";
                        }
                    }
                    BeanUtils.copyProperties(order, orderInfo);
                    order.setId(UUIDUtils.getId());
                    order.setOrderNo(Common.getOrderNumber());
                    order.setSchedule(0);
                    order.setStatus(1);
                    order.setVoucherPrice(voucherUsePrice);
                    //如果有制定护士的话 设置接单人   接单时间 并修改护士的日程
                    if (StringUtils.isNotEmpty(orderInfo.getExpectorId())) {
                        order.setAcceptUserId(orderInfo.getExpectorId());
                        order.setAcceptTime(new Date());
                        //根据预约时间修改护士的日程     appointment_time
                        String appointmentTime = format.format(order.getAppointmentTime());
                        String days = appointmentTime.substring(0, 10);
                        String hour = appointmentTime.substring(11, 13);
                        Worktime workTime = new Worktime();
                        workTime.setCalendar(days);

                        workTime.setUserid(orderInfo.getExpectorId());
                        Worktime resultTime = worktimeDao.load(workTime);
                        boolean flag = false;
                        //判断时间 
                        switch (Integer.parseInt(hour)) {
                        case 9:
                            if (resultTime.getH9() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 10:
                            if (resultTime.getH10() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 11:
                            if (resultTime.getH11() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 12:
                            if (resultTime.getH12() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 13:
                            if (resultTime.getH13() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 14:
                            if (resultTime.getH14() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 15:
                            if (resultTime.getH15() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 16:
                            if (resultTime.getH16() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 17:
                            if (resultTime.getH17() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 18:
                            if (resultTime.getH18() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 19:
                            if (resultTime.getH19() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 20:
                            if (resultTime.getH20() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        case 21:
                            if (resultTime.getH21() == 0) {
                                flag = false;
                            }
                            else {
                                flag = true;
                            }
                            ;
                            break;
                        }
                        if (!flag) {
                            switch (Integer.parseInt(hour)) {
                            case 9:
                                workTime.setH9(2);
                                break;
                            case 10:
                                workTime.setH10(2);
                                break;
                            case 11:
                                workTime.setH11(2);
                                break;
                            case 12:
                                workTime.setH12(2);
                                break;
                            case 13:
                                workTime.setH13(2);
                                break;
                            case 14:
                                workTime.setH14(2);
                                break;
                            case 15:
                                workTime.setH15(2);
                                break;
                            case 16:
                                workTime.setH16(2);
                                break;
                            case 17:
                                workTime.setH17(2);
                                break;
                            case 18:
                                workTime.setH18(2);
                                break;
                            case 19:
                                workTime.setH19(2);
                                break;
                            case 20:
                                workTime.setH20(2);
                                break;
                            case 21:
                                workTime.setH21(2);
                                break;

                            default:
                                break;
                            }
                            worktimeDao.updateByUserId(workTime);
                        }
                        else {
                            return "3";
                        }
                    }
                    order.setCreateTime(new Date());
                    i = orderDao.insert(order);
                    if (i > 0) {
                        String nurseId = null;
                        // 计算实际需要支付价格
                        if (StringUtils.isNotEmpty(orderInfo.getExpectorId())) {
                            nurseId = orderInfo.getExpectorId();
                        }
                        Double payPrice = voucherService.getGoodsPrice(orderInfo.getVoucherUseId(),
                                orderInfo.getPricePartId(), nurseId);
                        OrderGoods orderGoods = new OrderGoods();
                        BeanUtils.copyProperties(orderGoods, orderInfo);
                        orderGoods.setId(UUIDUtils.getId());
                        orderGoods.setCreateTime(new Date());
                        orderGoods.setOrderId(order.getId());
                        orderGoods.setRemark(orderInfo.getRemarks());
                        orderGoods.setStatus(1);
                        orderGoods.setPayPrice(payPrice);
                        orderGoods.setGoodsId(goods.getId());
                        if (StringUtils.isNotEmpty(orderInfo.getExpectorId()))
                            orderGoods.setExpectorId(orderInfo.getExpectorId());
                        orderGoods.setTitle(goods.getTitle());
                        orderGoods.setPrice(salePrice);
                        orderGoods.setProfit(pricePart.getProfit());
                        i = orderGoodsDao.insert(orderGoods);
                        if (i > 0) {
                            OrderOther orderOther = new OrderOther();
                            BeanUtils.copyProperties(orderOther, orderInfo);
                            orderOther.setId(UUIDUtils.getId());
                            orderOther.setStatus(1);
                            orderOther.setCreateTime(new Date());
                            orderOther.setOrderId(order.getId());
                            orderOther.setAddress(address);
                            orderOther.setDetailAddress(detailAddress);

                            i = orderOtherDao.insert(orderOther);
                            // 判断是否需要上传就医证明
                            // 如果IsProve =3 不需要上传 2需要一张 1 需要三张

                            ServiceImages serviceImages = null;
                            if (i > 0) {
                                serviceImages = new ServiceImages();
                                // 判断下单客户端
                                if (orderInfo.getDevice() == 5) {
                                    serviceImages.setDevice_type(1);
                                }
                                else {
                                    serviceImages.setDevice_type(2);
                                }
                                serviceImages.setSourceId(goods.getId());
                                serviceImages = serviceImagesDao.load(serviceImages);
                                ServiceImages insert = new ServiceImages();
                                insert.setUrl(serviceImages.getUrl());
                                insert.setId(UUID.randomUUID().toString());
                                insert.setCreatorId(order.getCreatorId());
                                insert.setCreatorName(order.getCreatorName());
                                insert.setCreateTime(new Date());
                                insert.setType(1);
                                insert.setDevice_type(order.getDevice());
                                insert.setSourceId(order.getId());
                                insert.setStatus(1);
                                i = serviceImagesDao.insert(insert);
                            }
                            // 插入就医证明的相关信息
                            if (goods.getIsProve() != 3) {
                                images = orderInfo.getImages().split(",");
                                for (String string : images) {
                                    serviceImages = new ServiceImages();
                                    serviceImages.setUrl(string);
                                    serviceImages.setId(UUID.randomUUID().toString());
                                    serviceImages.setCreatorId(order.getCreatorId());
                                    serviceImages.setCreatorName(order.getCreatorName());
                                    serviceImages.setCreateTime(new Date());
                                    serviceImages.setType(2);
                                    serviceImages.setDevice_type(order.getDevice());
                                    serviceImages.setSourceId(order.getId());
                                    serviceImages.setStatus(1);
                                    serviceImagesDao.insert(serviceImages);
                                }
                            }
                            // 添加保险信息
                            if (goods.getInsurance() == 1) {
                                Insurance insurance = new Insurance();
                                BeanUtils.copyProperties(insurance, orderInfo);
                                insurance.setOrderId(order.getId());
                                insurance.setId(UUIDUtils.getId());
                                insurance.setStatus(1);
                                insurance.setCreateTime(new Date());
                                insuranceDao.insert(insurance);
                            }
                            if (StringUtils.isNotEmpty(orderInfo.getVoucherUseId())) {
                                // 修改优惠券的状态
                                VoucherUse use = new VoucherUse();
                                use.setId(orderInfo.getVoucherUseId());
                                use.setStatus(1);
                                use.setUseTime(new Date());
                                voucherUseDao.update(use);
                            }
                            if (i > 0) {
                                com.jinpaihushi.jphs.order.model.OrderService orderService = null;
                                for (int j = 0; j < price.getServiceNumber(); j++) {
                                    orderService = new com.jinpaihushi.jphs.order.model.OrderService();
                                    BeanUtils.copyProperties(orderService, orderInfo);
                                    orderService.setAppointmentTime(orderInfo.getAppointmentTime());
                                    orderService.setPrice(onePrice);
                                    if (StringUtils.isNotEmpty(orderInfo.getAddressId())) {
                                        orderService.setPatientName(userAddress.getName());
                                        orderService.setPatientPhone(userAddress.getPhone());
                                    }
                                    orderService.setOrderId(order.getId());
                                    orderService.setId(UUIDUtils.getId());
                                    orderService.setSchedule(0);
                                    orderService.setStatus(1);
                                    orderService.setCreateTime(new Date());
                                    if (StringUtils.isNotEmpty(orderInfo.getExpectorId()))
                                        orderService.setNurseId(orderInfo.getExpectorId());
                                    i = orderServiceDao.insert(orderService);
                                }
                            }
                            result.put("orderId", order.getId());
                            result.put("orderNo", order.getOrderNo());
                            result.put("payParice", orderGoods.getPayPrice());
                            result.put("goodsName", orderGoods.getTitle());
                            result.put("goodsId", orderGoods.getGoodsId());
                            return i + "";
                        }
                    }
                }
                catch (Exception e) {

                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
                return i + "";
            }
        });
        if (Integer.parseInt(rs) == 1) {
            //发送短信
            return result;
        }
        else if (Integer.parseInt(rs) == 2) {
            //发送短信
            result.put("msg", "请上传就医证明！");
            return result;
        }
        else if (Integer.parseInt(rs) == 3) {
            //发送短信
            result.put("msg", "护士的时间已经被预约！");
            return result;
        }
        else {
            return null;
        }
    }

    @Override
    public String cancelOrder(String orderId, User user) {

        String msg = "";
        // 根据订单id查询订单状态
        Order order = orderDao.loadById(orderId);
        // 获取订单商品信息
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(orderId);
        orderGoods = orderGoodsDao.load(orderGoods);
        // 定义退款表示，默认不需要退款
        boolean refundFlag = false;
        Double refundMoney = orderGoods.getPayPrice();
        if (order != null) {
            // 判断订单状态
            // 如果是未支付状态的可以直接取消
            if (order.getSchedule() == 0) {
                // 判断该订单是否使用优惠券
                resetVouvher(orderId, order);
                msg = "您的订单已经取消！";
            }
            //            if (StringUtils.isEmpty(order.getAcceptUserId())) {
            //                resetVouvher(orderId, order);
            //                refundFlag = true;
            //                msg = "您的订单已经取消！";
            //            }
            if (order.getSchedule() < 3 && order.getSchedule() > 0) {
                // 判断该订单是否有护士接单
                // 判断护士是否已经出发
                com.jinpaihushi.jphs.order.model.OrderService service = new com.jinpaihushi.jphs.order.model.OrderService();
                service.setOrderId(orderId);
                service.setOrderby("appointment_time DESC");
                Date serviceTime = order.getAppointmentTime();
                // 护士已经出发
                // 判断出发时间到服务时间的小时数
                /**
                 * 距离服务时间 2小时内 扣除70% 2-4小时 扣50% 4 小时以上不扣
                 */
                Double hour = DoubleUtils.div(serviceTime.getTime() - new Date().getTime(), 3600 * 1000, 2);
                if (hour > 2 && hour < 5) {
                    refundMoney = DoubleUtils.mul(refundMoney, 0.9);
                    resetVouvher(orderId, order);
                    refundFlag = true;
                    msg = "您的订单已经取消！订单取消后，退款将原路返回，预计1-5个工作日，实际时间以银行到账时间为准";
                }
                else if (1 < hour && hour < 2) {
                    refundMoney = DoubleUtils.mul(refundMoney, 0.7);
                    resetVouvher(orderId, order);
                    refundFlag = true;
                    msg = "您的订单已经取消！订单取消后，退款将原路返回，预计1-5个工作日，实际时间以银行到账时间为准";
                }
                else if (1 > hour && 0 < hour) {
                    refundMoney = DoubleUtils.mul(refundMoney, 0.4);
                    resetVouvher(orderId, order);
                    refundFlag = true;
                    msg = "您的订单已经取消！订单取消后，退款将原路返回，预计1-5个工作日，实际时间以银行到账时间为准";
                }
                else if (hour > 5) {
                    resetVouvher(orderId, order);
                    refundFlag = true;
                    msg = "您的订单已经取消！订单取消后，退款将原路返回，预计1-5个工作日，实际时间以银行到账时间为准";
                }
                else {
                    msg = "非法操作！";

                }
            }
            Map<String, Object> map = getSmsMessage(orderId);
            if (StringUtils.isNotEmpty(order.getAcceptUserId())) {
                doPostSmsService.sendSms(map.get("nursePhone").toString(), SMS_cancel_order,
                        "{\"order_no\":\"" + map.get("order_no").toString() + "\"}");
            }
            //通知客服
            doPostSmsService.sendSms("13581912414", SMS_notice_order, "{\"service_name\":\""
                    + map.get("goodsName").toString() + "\",\"order_no\":\"" + map.get("order_no").toString() + "\"}");
        }
        if (refundFlag) {
            CancelOrder cancelOrder = new CancelOrder();
            // 判断该订单是否有退款记录
            cancelOrder.setSourceId(orderId);
            cancelOrder = cancelOrderDao.load(cancelOrder);
            if (cancelOrder == null) {
                cancelOrder = new CancelOrder();
                cancelOrder.setSourceId(orderId);
                cancelOrder.setCancelTime(new Date());
                cancelOrder.setId(UUIDUtils.getId());
                cancelOrder.setType(1);
                cancelOrder.setPrice(refundMoney);
                cancelOrder.setNumber(1);
                cancelOrder.setStatus(0);
                cancelOrder.setCreatorId(user.getId());
                cancelOrder.setCreatorName(user.getName());
                cancelOrder.setCreateTime(new Date());
                cancelOrderDao.insert(cancelOrder);
            }
            else {
                msg = "非法操作！";
            }
        }
        return msg;
    }

    public boolean updateWechatOrderStutas(SortedMap<Object, Object> packageParams) {

        try {
            // 这里是支付成功
            ////////// 执行自己的业务逻辑////////////////
            String mch_id = (String) packageParams.get("mch_id");
            String openid = (String) packageParams.get("openid");
            String attach = (String) packageParams.get("attach");
            String is_subscribe = (String) packageParams.get("is_subscribe");
            String out_trade_no = (String) packageParams.get("out_trade_no");
            String total_fee = (String) packageParams.get("total_fee");
            String transaction_id = (String) packageParams.get("transaction_id");
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("wechat.updateOrderStutas:mch_id=" + mch_id + ",openid=" + openid + ",is_subscribe="
                        + is_subscribe + ",out_trade_no=" + out_trade_no + ",total_fee=" + total_fee + "-------attach="
                        + attach);
            }
            ////////// 执行自己的业务逻辑////////////////

            Order order = new Order();
            order.setOrderNo(out_trade_no);
            order.setStatus(1);
            order.setSchedule(0);
            Order orders = orderDao.load(order);
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("wechat.updateOrderStutas.;根据微信返回transaction_id=" + transaction_id
                        + "平台内部订单号查询订单结果：orders=" + JSONObject.fromObject(orders).toString());
            }

            if (orders == null || "".equals(orders)) {
                Util.debugLog.debug("wechat.updateOrderStutas.;查询失败-订单");
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("wechat.updateOrderStutas;根据微信返回transaction_id=" + transaction_id
                            + "平台内部订单号查询订单结果：orders=" + JSONObject.fromObject(orders).toString());
                }
                return false;
            }

            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orders.getId());
            orderGoods.setStatus(1);
            OrderGoods orderGoods_ny = orderGoodsDao.load(orderGoods);
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("wechat.updateOrderStutas;订单goods数据orderGoods_ny="
                        + JSONObject.fromObject(orderGoods_ny).toString());
            }
            if (orderGoods_ny == null || "".equals(orderGoods_ny)) {
                Util.debugLog.debug("wechat.updateOrderStutas.;查询失败-订单goods");
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("wechat.updateOrderStutas;订单goods数据orderGoods_ny="
                            + JSONObject.fromObject(orderGoods_ny).toString());
                }
                return false;
            }
            try {
                String payPrice = orderGoods_ny.getPayPrice().toString();
                double total_fee_wc = Double.parseDouble(total_fee) / 100;
                double total_fee_ord = Double.parseDouble(payPrice);

                if (total_fee_wc != total_fee_ord) {
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("wechat.updateOrderStutas;下单失败！total_fee_wc=" + total_fee_wc
                                + ";total_fee_ord=" + total_fee_ord);
                    }
                    return false;
                }

                String remark = "";
                if (orderGoods_ny.getTitle() != null && !orderGoods_ny.getTitle().equals("")) {
                    remark = orderGoods_ny.getTitle();
                }

                Transaction transaction = new Transaction();
                transaction.setId(UUID.randomUUID().toString());
                transaction.setOrderId(orders.getId());
                transaction.setAmount(total_fee_ord);
                transaction.setScore((new Double(total_fee_ord)).intValue());
                transaction.setOperate(3);
                transaction.setOperateSource(1);
                transaction.setRemark(remark);
                transaction.setWithdraw(0);
                transaction.setPayType(2);
                transaction.setOutTradeNo(transaction_id);
                transaction.setCreatorId(orders.getCreatorId());
                transaction.setCreatorName(orders.getCreatorName());
                transaction.setCreateTime(new Date());
                transaction.setStatus(1);
                // 记录日志-debug
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug(
                            "wechat.updateOrderStutas;transaction=" + JSONObject.fromObject(transaction).toString());
                }
                int ti = transactionDao.insert(transaction);
                if (ti <= 0) {
                    // 记录日志-debug
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("wechat.updateOrderStutas;插入数据失败transaction="
                                + JSONObject.fromObject(transaction).toString());
                    }
                    return false;
                }
                Order orderUp = new Order();
                orderUp.setId(orders.getId());
                if (orders.getAcceptUserId() != null && !"".equals(orders.getAcceptUserId())) {
                    orderUp.setSchedule(2);
                }
                else {
                    orderUp.setSchedule(1);
                }
                int orderUpbool = orderDao.update(orderUp);
                // 记录日志-debug
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("wechat.updateOrderStutas;更新订单状态=" + JSONObject.fromObject(orderUp).toString()
                            + ";orderUpbool=" + orderUpbool);
                }
                if (orderUpbool > 0) {
                    try {
                        User user = new User();
                        user.setId(orders.getCreatorId());
                        user.setStatus(1);
                        User orderUser = userDao.load(user);
                        // 记录日志-debug
                        if (Util.debugLog.isDebugEnabled()) {
                            Util.debugLog.debug("alipay.updateOrderStutas;订单用户信息orderUser="
                                    + JSONObject.fromObject(orderUser).toString());
                        }
                        if (orderUser != null) {
                            Map<String, Object> map = orderDao.getSmsMessage(orders.getId());
                            // 发送验证码
                            //通知用户下单成功
                            doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_pay_success,
                                    "{\"service_name\":\"" + map.get("goodsName").toString() + "\"}");
                            //通知客服
                            doPostSmsService.sendSms("13581912414", SMS_notice_order,
                                    "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"order_no\":\""
                                            + map.get("order_no").toString() + "\"}");
                            //判断订单有没有接单人有的话通知护士
                            if (StringUtils.isNotEmpty(orders.getAcceptUserId())) {
                                //通知用户
                                doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_Nurse_orders,
                                        "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"name\":\""
                                                + map.get("nurseName").toString() + "\",\"phone\":\""
                                                + map.get("nursePhone").toString() + "\"}");
                                //通知护士有新的订单
                                doPostSmsService.sendSms(map.get("nursePhone").toString(), SMS_nurse_delivery_order,
                                        "{\"name\":\"" + map.get("userName").toString() + "\"}");
                                //推送消息
                                nurseJPushService.jpushAlias("您有一笔新的订单待处理，请登录APP查看订单状态并及时联系客户，服务开始时做好录音准备，避免造成纠纷。",
                                        map.get("nursePhone").toString(), "0");
                            }
                            else {
                                String address = map.get("address").toString();
                                String area = "";
                                if (address.split(",")[1].equals("市辖区")) {
                                    area = address.split(",")[0].substring(0, 2);
                                }
                                else {
                                    area = address.split(",")[1].substring(0, 2);
                                }
                                nurseJPushService.jpushTag("有新的用户下单了，快去抢哦！",
                                        MD5.md5crypt(MD5.md5crypt(area)).substring(0, 8), "0");
                            }
                        }
                        return true;
                    }
                    catch (Exception e) {
                        // 记录日志-debug
                        if (Util.debugLog.isDebugEnabled()) {
                            Util.debugLog.debug("wechat.updateOrderStutas3;发送短信异常！=" + e);
                        }
                    }
                    return true;
                }
                else {
                    return false;
                }
            }
            catch (Exception e) {
                // 记录日志-debug
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("wechat.updateOrderStutas2;下单失败！=" + e);
                }
            }
        }
        catch (

        Exception e) {
            Util.failLog.error("wechatOrderServiceImpl.updateOrderStutas：1e=", e);
        }
        return false;
    }

    /**
     * 完成服务
     * @param osId
     * @param orderId
     * @param orderGoodsId
     * @param user
     * @return
     */
    public String fulfilService(String osId, String orderId, String orderGoodsId, User user) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            //事务模板
            public String doInTransaction(final TransactionStatus status) {
                try {
                    //  完成本次服务
                    com.jinpaihushi.jphs.order.model.OrderService os_one = new com.jinpaihushi.jphs.order.model.OrderService();
                    os_one.setStatus(1);
                    os_one.setOrderId(orderId);
                    os_one.setSchedule(1);
                    os_one.setId(osId);
                    com.jinpaihushi.jphs.order.model.OrderService one_os = orderServiceDao.load(os_one);
                    if (one_os == null) {
                        return "2";
                        //                      return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
                    }
                    com.jinpaihushi.jphs.order.model.OrderService one_os_up = new com.jinpaihushi.jphs.order.model.OrderService();
                    one_os_up.setSchedule(3);
                    one_os_up.setNurseId(user.getId());
                    one_os_up.setId(osId);
                    one_os_up.setEndServiceTime(new Date());
                    int one_os_up_s = orderServiceDao.update(one_os_up);

                    OrderGoods orderGoods = new OrderGoods();
                    orderGoods.setStatus(1);
                    orderGoods.setOrderId(orderId);
                    List<OrderGoods> orderGoods_list = orderGoodsDao.list(orderGoods);
                    String remark = "";
                    if (orderGoods_list != null && !orderGoods_list.equals("") && orderGoods_list.size() > 0) {
                        OrderGoods orderGoods_one = orderGoods_list.get(0);
                        if (orderGoods_one.getTitle() != null && !orderGoods_one.getTitle().equals("")) {
                            remark = orderGoods_one.getTitle();
                        }
                    }

                    if (one_os_up_s > 0) {
                        Transaction transaction = new Transaction();
                        transaction.setId(UUIDUtils.getId());
                        transaction.setOrderId(one_os.getId());
                        transaction.setAmount(one_os.getPrice());
                        transaction.setScore(Integer.valueOf(one_os.getPrice().intValue()));
                        transaction.setOperate(4);
                        transaction.setOperateSource(1);
                        transaction.setRemark(remark);
                        transaction.setWithdraw(0);
                        transaction.setCreatorId(one_os.getNurseId());
                        transaction.setCreateTime(new Date());
                        transaction.setStatus(1);
                        transaction.setType(1);
                        int to = transactionDao.insert(transaction);
                        if (to > 0) {
                            Account account = new Account();
                            account.setCreatorId(user.getId());
                            List<Account> account_list = accountDao.list(account);
                            Account account_up = new Account();
                            account_up.setId(account_list.get(0).getId());
                            account_up.setBalance(account_list.get(0).getBalance() + one_os.getPrice());

                            account_up.setScore(
                                    account_list.get(0).getScore() + Integer.valueOf(one_os.getPrice().intValue()));
                            account_up.setAvailableScore(account_list.get(0).getAvailableScore()
                                    + Integer.valueOf(one_os.getPrice().intValue()));
                            int ad = accountDao.update(account_up);
                            if (ad <= 0) {
                                String str = null;
                                System.out.println(str.toString());
                            }
                        }
                        else {
                            System.out.println(1 / 0);
                        }
                    }
                    else {
                        System.out.println(new int[] {}[0]);
                    }

                    // 多次服务列表
                    com.jinpaihushi.jphs.order.model.OrderService one_os_size = new com.jinpaihushi.jphs.order.model.OrderService();
                    one_os_size.setOrderId(orderId);
                    List<com.jinpaihushi.jphs.order.model.OrderService> one_os_size_list = orderServiceDao
                            .list(one_os_size);
                    boolean falg = false;
                    for (int a = 0; a < one_os_size_list.size(); a++) {
                        com.jinpaihushi.jphs.order.model.OrderService one_os_size_list_one = one_os_size_list.get(a);
                        if (one_os_size_list_one.getSchedule() == 3) {
                            falg = true;
                        }
                        else {
                            falg = false;
                            break;
                        }
                    }
                    if (falg) {
                        Order order_size = new Order();
                        order_size.setId(orderId);
                        order_size.setSchedule(5);
                        int od = orderDao.update(order_size);
                        if (od <= 0) {
                            System.out.println(1 / 0);
                        }
                    }

                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "4";
                }
                catch (ArithmeticException e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "3";
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "2";
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
                return "1";
            }
        });
        return rs;
    }

    /**
     * 开始服务
     */
    public String startService(String orderId, String osId, User user) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            //事务模板
            public String doInTransaction(final TransactionStatus status) {
                try {
                    Order order_start = new Order();
                    order_start.setAcceptUserId(user.getId());
                    order_start.setId(orderId);
                    order_start.setSchedule(3);
                    int or_start = orderDao.update(order_start);
                    if (or_start <= 0) {
                        String str = null;
                        System.out.println(str.toString());
                        //                      return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
                    }
                    com.jinpaihushi.jphs.order.model.OrderService os_total = new com.jinpaihushi.jphs.order.model.OrderService();
                    os_total.setStatus(1);
                    os_total.setOrderId(orderId);
                    os_total.setSchedule(0);
                    os_total.setId(osId);
                    // 查询该次订单
                    com.jinpaihushi.jphs.order.model.OrderService one_os = orderServiceDao.load(os_total);
                    if (one_os == null) {
                        System.out.println(1 / 0);
                        //                      return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
                    }
                    com.jinpaihushi.jphs.order.model.OrderService one_os_up = new com.jinpaihushi.jphs.order.model.OrderService();
                    one_os_up.setSchedule(1);
                    one_os_up.setNurseId(user.getId());
                    one_os_up.setId(osId);
                    one_os_up.setStartServiceTime(new Date());
                    int one_os_up_s = orderServiceDao.update(one_os_up);
                    if (one_os_up_s <= 0) {
                        System.out.println(new int[] {}[0]);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "4";
                }
                catch (ArithmeticException e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "3";
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "2";
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
                return "1";
            }
        });
        return rs;
    }

    /**
     * 护士抢单
     */
    public String orderGrab(String orderId, String orderGoodsId, User user) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            //事务模板

            public String doInTransaction(final TransactionStatus status) {
                try {
                    User query = new User();
                    query.setId(user.getId());
                    query.setPhone(user.getPhone());
                    query.setType(user.getType());
                    // 1.根据 name，password,type查询完整信息
                    query = userDao.queryUser(query);

                    // 2.错误     查询用户信息为空弄
                    if (query == null) {
                        return 0 + "";

                    }
                    int tid = 0; // type
                    try {
                        tid = Integer.valueOf(user.getType());
                    }
                    catch (NumberFormatException e) {
                        return "2";
                        //                          return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
                    }

                    // 3.错误     判断用户是否是护士
                    if (tid != 0) {
                        return "3";
                        //                          return JSONUtil.toJSONResult(0, "用户不是护士！", null);
                    }

                    Nurse nurse = new Nurse();
                    nurse.setCreatorId(user.getId());
                    nurse = nurseDao.load(nurse);
                    // 4.错误     用户未申请审核护士资格
                    if (nurse == null) {
                        return "4";
                        //                          return JSONUtil.toJSONResult(0, "未申请护士资格！", null);
                    }
                    // 5.错误     已封号
                    if (nurse.getActive() != 1) {
                        return "5";
                        //                          return JSONUtil.toJSONResult(0, "护士已被封号，请联系客服确认！", null);
                    }
                    // 6.错误     未通过审核
                    if (nurse.getStatus() != 1) {
                        return "6";
                        //                          return JSONUtil.toJSONResult(0, "护士未通过审核！", null);
                    }
                    Map<String, Object> jobtitleGoods = new HashMap<String, Object>();
                    jobtitleGoods.put("goodsId", orderGoodsId);
                    jobtitleGoods.put("nurseId", user.getId());
                    int hhh = jobtitleGoodsDao.getJobtitleCount(jobtitleGoods);
                    // 7.错误     护士权限不能接此类型订单
                    if (hhh <= 0) {
                        return "7";
                        //                          return JSONUtil.toJSONResult(0, "护士权限未达到！", null);
                    }
                    Goods goods_one = new Goods();
                    goods_one.setId(orderGoodsId);
                    Goods good_u = goodsDao.load(goods_one);
                    if (good_u == null || good_u.equals("")) {
                        return "11";
                    }
                    String productId = good_u.getProductId();
                    if (StringUtils.isEmpty(productId)) {
                        return "11";
                    }

                    Order orders = new Order();
                    orders.setId(orderId);
                    orders.setSchedule(1);
                    orders.setStatus(1);
                    orders = orderDao.load(orders);
                    // 8.错误     该订单已被抢走
                    if (orders == null) {
                        return "8";
                        //                          return JSONUtil.toJSONResult(0, "该订单已被抢走！", null);
                    }

                    //根据预约时间修改护士的日程     appointment_time
                    String appointmentTime = format.format(orders.getAppointmentTime());
                    String days = appointmentTime.substring(0, 10);
                    String hour = appointmentTime.substring(11, 13);
                    Worktime workTime = new Worktime();
                    workTime.setCalendar(days);
                    workTime.setUserid(user.getId());
                    Worktime resultTime = worktimeDao.load(workTime);
                    if (resultTime == null) {
                        return "18";
                    }
                    boolean flag = false;
                    //判断时间 
                    switch (Integer.parseInt(hour)) {
                    case 9:
                        if (resultTime.getH9() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 10:
                        if (resultTime.getH10() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 11:
                        if (resultTime.getH11() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 12:
                        if (resultTime.getH12() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 13:
                        if (resultTime.getH13() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 14:
                        if (resultTime.getH14() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 15:
                        if (resultTime.getH15() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 16:
                        if (resultTime.getH16() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 17:
                        if (resultTime.getH17() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 18:
                        if (resultTime.getH18() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 19:
                        if (resultTime.getH19() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 20:
                        if (resultTime.getH20() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    case 21:
                        if (resultTime.getH21() == 0) {
                            flag = false;
                        }
                        else {
                            flag = true;
                        }
                        ;
                        break;
                    }

                    if (flag) {
                        return "17";
                    }

                    if (!productId.equals("139")) {
                        OrderOther oo = new OrderOther();
                        oo.setOrderId(orderId);
                        oo.setStatus(1);
                        List<OrderOther> oo_list = orderOtherDao.list(oo);

                        if (oo_list == null || oo_list.equals("") || oo_list.size() <= 0) {
                            return "10";
                        }
                        if (oo_list.get(0).getAddress() == null || oo_list.get(0).getAddress().equals("")) {
                            return "11";
                        }

                        UserAddress ua = new UserAddress();
                        ua.setCreatorId(user.getId());
                        ua.setStatus(0);
                        //  护士地址
                        List<UserAddress> ua_list = userAddressDao.list(ua);
                        if (ua_list == null || ua_list.equals("") || ua_list.size() <= 0) {
                            return "12";
                        }
                        //  订单地址
                        String oo_addess = oo_list.get(0).getAddress();
                        String[] oo_addess_arr = oo_addess.split(",");
                        if (oo_addess_arr.length < 1) {
                            return "14";
                        }
                        if (oo_addess_arr[0] == null || oo_addess_arr[0].equals("")) {
                            return "13";
                        }
                        boolean flag_adds = true;
                        if (productId.equals("141")) {
                            String jy_s = oo_addess_arr[0];
                            for (int i = 0; i < ua_list.size(); i++) {
                                if (ua_list.get(i).getProvince() == null || ua_list.get(i).getProvince().equals("")) {
                                    continue;
                                }
                                String ua_ss_pj = ua_list.get(i).getProvince();
                                if (ua_ss_pj.equals(jy_s)) {
                                    flag_adds = false;
                                    break;
                                }
                                else if (ua_ss_pj.equals(jy_s + "市")) {
                                    flag_adds = false;
                                    break;
                                }
                                else if (ua_ss_pj.equals(jy_s + "省")) {
                                    flag_adds = false;
                                    break;
                                }
                            }
                        }
                        else {

                            if (oo_addess_arr[1] == null || oo_addess_arr[1].equals("")) {
                                return "14";
                            }
                            String oo_ss_pj = "";
                            if (oo_addess_arr[1].equals("市辖区")) {
                                oo_ss_pj = oo_addess_arr[0] + oo_addess_arr[0];
                            }
                            else {
                                oo_ss_pj = oo_addess_arr[0] + oo_addess_arr[1];
                            }
                            if (oo_ss_pj.equals("")) {
                                return "15";
                            }

                            for (int a = 0; a < ua_list.size(); a++) {
                                if (ua_list.get(a).getProvince() == null || ua_list.get(a).getProvince().equals("")) {
                                    continue;
                                }
                                if (ua_list.get(a).getCity() == null || ua_list.get(a).getCity().equals("")) {
                                    continue;
                                }
                                String ua_ss_pj = ua_list.get(a).getProvince() + ua_list.get(a).getCity();
                                if (ua_ss_pj.equals(oo_ss_pj)) {
                                    flag_adds = false;
                                    break;
                                }
                            }
                        }
                        if (flag_adds) {
                            return "16";
                        }
                    }

                    Order order = new Order();
                    order.setId(orderId);
                    order.setSchedule(1);
                    order.setStatus(1);
                    order = orderDao.load(order);
                    // 8.错误     该订单已被抢走
                    if (order == null) {
                        return "8";
                        //                          return JSONUtil.toJSONResult(0, "该订单已被抢走！", null);
                    }

                    Order order_up = new Order();
                    order_up.setId(orderId);
                    order_up.setSchedule(2);
                    order_up.setAcceptTime(new Date());
                    order_up.setAcceptUserId(user.getId());
                    int order_up_falg = orderDao.update(order_up);
                    // 9.错误     接单失败
                    if (order_up_falg <= 0) {
                        return "9";
                        //                          return JSONUtil.toJSONResult(0, "接单失败！", null);
                    }
                    if (!flag) {
                        switch (Integer.parseInt(hour)) {
                        case 9:
                            workTime.setH9(2);
                            break;
                        case 10:
                            workTime.setH10(2);
                            break;
                        case 11:
                            workTime.setH11(2);
                            break;
                        case 12:
                            workTime.setH12(2);
                            break;
                        case 13:
                            workTime.setH13(2);
                            break;
                        case 14:
                            workTime.setH14(2);
                            break;
                        case 15:
                            workTime.setH15(2);
                            break;
                        case 16:
                            workTime.setH16(2);
                            break;
                        case 17:
                            workTime.setH17(2);
                            break;
                        case 18:
                            workTime.setH18(2);
                            break;
                        case 19:
                            workTime.setH19(2);
                            break;
                        case 20:
                            workTime.setH20(2);
                            break;
                        case 21:
                            workTime.setH21(2);
                            break;

                        default:
                            break;
                        }
                        worktimeDao.updateByUserId(workTime);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
                Map<String, Object> map = getSmsMessage(orderId);
                doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_Nurse_orders,
                        "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"name\":\""
                                + map.get("nurseName").toString() + "\",\"phone\":" + map.get("nursePhone").toString()
                                + "\"}");
                //通知护士有新的订单
                doPostSmsService.sendSms(map.get("nursePhone").toString(), SMS_Nurse_order,
                        "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"order_no\":\""
                                + map.get("order_no").toString() + "\",\"time\":" + map.get("accept_time").toString()
                                + "\"}");
                //推送消息
                nurseJPushService.jpushAlias(
                        "您接到一笔" + map.get("goodsName").toString() + "订单，单号：" + map.get("order_no").toString() + "，预约时间："
                                + map.get("accept_time").toString()
                                + "。请到官网下载打印相关服务注意事项和同意书后及时联系客户，服务开始时做好录音准备，避免造成纠纷。",
                        map.get("nursePhone").toString(), "0");
                return "1";
            }
        });

        return rs;
    }

    /**
     * 余额支付
     * @return
     */
    public byte[] balancePayment(String orderId, String orderNo, Double payParice, String userId) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            //事务模板
            public String doInTransaction(final TransactionStatus status) {
                try {

                    Order order = new Order();
                    order.setOrderNo(orderNo);
                    order.setStatus(1);
                    order.setSchedule(0);
                    Order orders = orderDao.load(order);

                    if (orders == null || "".equals(orders)) {
                        status.setRollbackOnly();//回滚
                        return "5";
                    }

                    OrderGoods orderGoods = new OrderGoods();
                    orderGoods.setOrderId(orders.getId());
                    orderGoods = orderGoodsDao.load(orderGoods);
                    //判断支付金额跟订单金额
                    if (DoubleUtils.sub(orderGoods.getPayPrice(), payParice) != 0) {
                        return "2";
                    }
                    Account model = new Account();
                    model.setCreatorId(userId);
                    Account account = accountDao.load(model);
                    if (null == account || "".equals(account)) {
                        return "10";
                    }
                    if (null == account.getBalance() || "".equals(account.getBalance())) {
                        return "11";
                    }
                    /**
                     * 用于余额不足
                     */
                    if (DoubleUtils.sub(account.getBalance(), payParice) < 0) {
                        return "3";
                    }
                    /**
                     * 用户余额减去-订单金额
                     * 更新用户余额
                     */
                    Double balance = DoubleUtils.sub(account.getBalance(), payParice);
                    account.setBalance(balance);
                    int a = accountDao.update(account);
                    if (a < 1) {
                        status.setRollbackOnly();//回滚
                        return "4";
                    }

                    OrderGoods orderGoods_up = new OrderGoods();
                    orderGoods_up.setOrderId(orders.getId());
                    orderGoods_up.setStatus(1);
                    OrderGoods orderGoods_ny = orderGoodsDao.load(orderGoods_up);
                    if (orderGoods_ny == null || "".equals(orderGoods_ny)) {
                        status.setRollbackOnly();//回滚
                        return "6";
                    }

                    if (DoubleUtils.sub(orderGoods_ny.getPayPrice(), payParice) < 0) {
                        status.setRollbackOnly();//回滚
                        return "7";
                    }

                    String remark = "";
                    if (orderGoods_ny.getTitle() != null && !orderGoods_ny.getTitle().equals("")) {
                        remark = orderGoods_ny.getTitle();
                    }

                    Transaction transaction = new Transaction();
                    transaction.setId(UUID.randomUUID().toString());
                    transaction.setOrderId(orders.getId());
                    transaction.setAmount(payParice);
                    transaction.setScore((new Double(payParice)).intValue());
                    transaction.setOperate(3);
                    transaction.setOperateSource(1);
                    transaction.setRemark(remark);
                    transaction.setWithdraw(0);
                    transaction.setPayType(3);
                    transaction.setOutTradeNo(orderNo);
                    transaction.setCreatorId(orders.getCreatorId());
                    transaction.setCreatorName(orders.getCreatorName());
                    transaction.setCreateTime(new Date());
                    transaction.setStatus(1);
                    transaction.setType(1);
                    int ti = transactionDao.insert(transaction);
                    if (ti <= 0) {
                        status.setRollbackOnly();//回滚
                        return "8";
                    }
                    Order orderUp = new Order();
                    orderUp.setId(orders.getId());
                    if (orders.getAcceptUserId() != null && !"".equals(orders.getAcceptUserId())) {
                        orderUp.setSchedule(2);
                    }
                    else {
                        orderUp.setSchedule(1);
                    }
                    int orderUpbool = orderDao.update(orderUp);
                    if (orderUpbool < 1) {
                        status.setRollbackOnly();//回滚
                        return "9";
                    }
                    User user = new User();
                    user.setId(orders.getCreatorId());
                    user.setStatus(1);
                    User orderUser = userDao.load(user);
                    if (orderUser != null) {
                        // 发送验证码
                        try {
                            Map<String, Object> map = getSmsMessage(orders.getId());
                            // 发送验证码
                            //通知用户下单成功
                            doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_pay_success,
                                    "{\"service_name\":\"" + map.get("goodsName").toString() + "\"}");
                            //通知客服
                            doPostSmsService.sendSms("13581912414", SMS_notice_order,
                                    "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"order_no\":\""
                                            + map.get("order_no").toString() + "\"}");
                            //判断订单有没有接单人有的话通知护士
                            if (StringUtils.isNotEmpty(orders.getAcceptUserId())) {
                                doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_Nurse_orders,
                                        "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"name\":\""
                                                + map.get("nurseName").toString() + "\",\"phone\":\""
                                                + map.get("nursePhone").toString() + "\"}");
                                //通知护士有新的订单
                                doPostSmsService.sendSms(map.get("nursePhone").toString(), SMS_nurse_delivery_order,
                                        "{\"name\":\"" + map.get("userName").toString() + "\"}");
                                nurseJPushService.jpushAlias("您有一笔新的订单待处理，请登录APP查看订单状态并及时联系客户，服务开始时做好录音准备，避免造成纠纷。",
                                        map.get("nursePhone").toString(), "0");
                            }
                            else {
                                String address = map.get("address").toString();
                                String area = "";
                                if (address.split(",")[1].equals("市辖区")) {
                                    area = address.split(",")[0].substring(0, 2);
                                }
                                else {
                                    area = address.split(",")[1].substring(0, 2);
                                }
                                nurseJPushService.jpushTag("有新的用户下单了，快去抢哦！",
                                        MD5.md5crypt(MD5.md5crypt(area)).substring(0, 8), "0");
                            }
                        }
                        catch (Exception e) {
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
                return "1";
            }
        });
        String msg = "支付成功";
        int rsi = Integer.parseInt(rs);
        if (rsi == 0) {
            msg = "支付失败，请刷新重试";
        }
        else if (rsi == 2) {
            msg = "支付失败，订单异常";
        }
        else if (rsi == 3) {
            msg = "支付失败，余额不足";
        }
        else if (rsi == 4) {
            msg = "支付失败，订单异常";
        }
        else if (rsi == 5) {
            msg = "支付失败，订单数据异常";
        }
        else if (rsi == 6) {
            msg = "支付失败，订单数据异常-1";
        }
        else if (rsi == 7) {
            msg = "支付失败，金额异常";
        }
        else if (rsi == 8) {
            msg = "支付失败，添加消费记录异常";
        }
        else if (rsi == 9) {
            msg = "支付失败，订单状态异常";
        }
        else if (rsi == 10) {
            msg = "支付失败，用户账户异常";
        }
        else if (rsi == 11) {
            msg = "支付失败，用户账户余额异常";
        }

        try {
            return JSONUtil.toJSONResult(rsi, msg, null);
        }
        catch (IOException e) {
        }
        return null;
    }

    private void resetVouvher(String orderId, Order order) {
        if (StringUtils.isNotEmpty(order.getVoucherUseId())) {
            // 将优惠券的使用时间以及状态修改为未使用状态
            voucherRepertoryDao.updataUseTime(order.getVoucherUseId());

        }
        //如果有接单人话将接单人的时间打开
        if (StringUtils.isNotEmpty(order.getAcceptUserId())) {
            String appointmentTime = format.format(order.getAppointmentTime());
            String days = appointmentTime.substring(0, 10);
            String hour = appointmentTime.substring(11, 13);
            Worktime workTime = new Worktime();
            workTime.setCalendar(days);
            workTime.setUserid(order.getAcceptUserId());
            Worktime resultTime = worktimeDao.load(workTime);
            switch (Integer.parseInt(hour)) {
            case 9:
                resultTime.setH9(0);
                break;
            case 10:
                resultTime.setH10(0);
                break;
            case 11:
                resultTime.setH11(0);
                break;
            case 12:
                resultTime.setH12(0);
                break;
            case 13:
                resultTime.setH13(0);
                break;
            case 14:
                resultTime.setH14(0);
                break;
            case 15:
                resultTime.setH15(0);
                break;
            case 16:
                resultTime.setH16(0);
                break;
            case 17:
                resultTime.setH17(0);
                break;
            case 18:
                resultTime.setH18(0);
                break;
            case 19:
                resultTime.setH19(0);
                break;
            case 20:
                resultTime.setH20(0);
                break;
            case 21:
                resultTime.setH21(0);
                break;
            default:
                break;
            }
            worktimeDao.updateByUserId(resultTime);
        }
        // 修改子订单状态
        com.jinpaihushi.jphs.order.model.OrderService orderService = new com.jinpaihushi.jphs.order.model.OrderService();
        orderService.setOrderId(orderId);
        orderService.setCreatorId(order.getCreatorId());
        List<com.jinpaihushi.jphs.order.model.OrderService> list = orderServiceDao.list(orderService);
        for (com.jinpaihushi.jphs.order.model.OrderService orderService2 : list) {
            orderService2.setSchedule(4);
            orderServiceDao.update(orderService2);
        }
        // 修改主订单进度
        order.setSchedule(6);
        orderDao.update(order);
    }

    @Override
    public List<Order> getCompletedOrder(Map<String, Object> query) {
        return orderDao.getCompletedOrder(query);
    }

    @Override
    public List<Map<String, Object>> getOrderUnpaid() {
        return orderDao.getOrderUnpaid();
    }

    @Override
    public Map<String, Object> getSmsMessage(String id) {
        return orderDao.getSmsMessage(id);
    }

    @Override
    public List<Map<String, Object>> getNotInRank() {
        return orderDao.getNotInRank();
    }

}