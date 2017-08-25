package com.jinpaihushi.jphs.order.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.insurance.dao.InsuranceDao;
import com.jinpaihushi.jphs.insurance.model.Insurance;
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
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.service.dao.ServiceImagesDao;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.user.dao.UserAddressDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.voucher.dao.VoucherRepertoryDao;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.UUIDUtils;

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
    private OrderGoodsDao orderGoodsDao;

    @Autowired
    private OrderOtherDao orderOtherDao;

    @Autowired
    private OrderServiceDao orderServiceDao;

    @Autowired
    private ServiceImagesDao serviceImagesDao;

    @Autowired
    private InsuranceDao insuranceDao;

    @Autowired
    private VoucherUseDao voucherUseDao;

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private PricePartDao pricePartDao;

    @Autowired
    private VoucherRepertoryDao voucherRepertoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PriceDao priceDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private VoucherService voucherService;

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
        //判断OrderInfo 中的一些信息
        //先判断是否需要上传就医证明
        Goods goods = null;
        String[] images = null;
        if (StringUtils.isEmpty(orderInfo.getGoodsId())) {
            goods = goodsDao.loadById(orderInfo.getGoodsId());
            //如果IsProve =3 不需要上传 2需要一张 1 需要三张
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
                        //插入相关就医证明的图片
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
        orderService.setStatus(0);
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
        if (orderDetail.getOrderGoods().getPayPrice() == payParice)
            return true;
        return false;
    }

    public List<Map<String, Object>> getUptoDataGoods(Map<String, Object> map) {
        return orderDao.getUptoDataGoods(map);
    }

    public List<Map<String, Object>> getOrderGoodsList(Map<String, Object> map) {
        return orderDao.orderNotList(map);
    }

    public List<Map<String, Object>> nurseOrderList(Map<String, Object> map) {
        return orderDao.nurseOrderList(map);
    }

    /**
     * 订单详情
     * @param order
     * @return
     */
    public Order nurseOrderDetails(Order order) {
        return orderDao.nurseOrderDetails(order);
    }

    @Override
    public Map<String, Object> createOrderNew(OrderPojo orderInfo) {
        Map<String, Object> result = new HashMap<>();
        //获取地址
        UserAddress userAddress = userAddressDao.loadById(orderInfo.getAddressId());
        String address = userAddress.getProvince() + "," + userAddress.getCity() + "," + userAddress.getArea();
        //详细价格
        PricePart pricePart = pricePartDao.loadById(orderInfo.getPricePartId());
        //获取价格基本信息
        Price price = priceDao.loadById(pricePart.getPriceId());
        Double onePrice = DoubleUtils.div(pricePart.getPrice(), price.getServiceNumber(), 2);
        //用户优惠券的价格
        VoucherUse voucherUse = voucherUseDao.loadById(orderInfo.getVoucherUseId());
        //先获取商品的基本信息
        Goods goods = goodsDao.getGoodsByPricePart(orderInfo.getPricePartId());
        Order order = new Order();
        String[] images = null;
        try {
            BeanUtils.copyProperties(order, orderInfo);
            order.setId(UUIDUtils.getId());
            order.setOrderNo(Common.getOrderNumber());
            order.setSchedule(0);
            order.setStatus(1);
            order.setVoucherPrice(voucherUse.getAmount());
            if (StringUtils.isNotEmpty(orderInfo.getExpectorId()))
                order.setAcceptUserId(orderInfo.getExpectorId());
            order.setCreateTime(new Date());
            int i = orderDao.insert(order);
            if (i > 0) {
                String nurseId = null;
                //计算实际需要支付价格
                if (StringUtils.isNotEmpty(orderInfo.getExpectorId())) {
                    nurseId = orderInfo.getExpectorId();
                }
                Double payPrice = voucherService.getGoodsPrice(orderInfo.getVoucherUseId(), orderInfo.getPricePartId(),
                        nurseId);
                OrderGoods orderGoods = new OrderGoods();
                BeanUtils.copyProperties(orderGoods, orderInfo);
                orderGoods.setId(UUIDUtils.getId());
                orderGoods.setCreateTime(new Date());
                orderGoods.setOrderId(order.getId());
                orderGoods.setRemark(orderInfo.getRemarks());
                orderGoods.setStatus(1);
                orderGoods.setPayPrice(payPrice);
                orderGoods.setGoodsId(goods.getId());
                orderGoods.setTitle(goods.getTitle());
                orderGoods.setPrice(pricePart.getPrice());
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
                    orderOther.setDetailAddress(userAddress.getDetailaddress());
                    //判断商品的信息中是否需要工具药品
                    if (goods.getTool() == 1) {
                        orderOther.setDrug("是");
                        orderOther.setTool("是");
                    }
                    else {
                        orderOther.setDrug("否");
                        orderOther.setTool("否");
                    }
                    i = orderOtherDao.insert(orderOther);
                    //判断是否需要上传就医证明
                    //如果IsProve =3 不需要上传 2需要一张 1 需要三张
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
                    ServiceImages serviceImages = null;
                    if (i > 0) {
                        serviceImages = new ServiceImages();
                        serviceImages.setUrl(goods.getUrl());
                        serviceImages.setId(UUID.randomUUID().toString());
                        serviceImages.setCreatorId(order.getCreatorId());
                        serviceImages.setCreatorName(order.getCreatorName());
                        serviceImages.setCreateTime(new Date());
                        serviceImages.setDevice_type(1);
                        serviceImages.setSourceId(order.getId());
                        serviceImages.setStatus(1);
                        i = serviceImagesDao.insert(serviceImages);
                    }
                    //插入就医证明的相关信息
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
                    if (i > 0) {
                        com.jinpaihushi.jphs.order.model.OrderService orderService = null;
                        for (int j = 0; j < price.getServiceNumber(); j++) {
                            orderService = new com.jinpaihushi.jphs.order.model.OrderService();
                            BeanUtils.copyProperties(orderService, orderInfo);
                            orderService.setAppointmentTime(orderInfo.getAppointmentTime());
                            orderService.setPrice(onePrice);
                            orderService.setOrderId(order.getId());
                            orderService.setId(UUIDUtils.getId());
                            orderService.setSchedule(0);
                            orderService.setStatus(1);
                            i = orderServiceDao.insert(orderService);
                        }
                    }
                    result.put("orderId", order.getId());
                    result.put("orderNo", order.getOrderNo());
                    result.put("payParice", orderGoods.getPayPrice());
                    result.put("goodsName", orderGoods.getTitle());
                    result.put("goodsId", orderGoods.getGoodsId());
                    return result;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> cancelOrder(String orderId) {
        Map<String, Object> result = new HashMap<>();
        //根据订单id查询订单状态
        Order order = orderDao.loadById(orderId);
        if (order != null) {
            //判断订单状态
            //如果是未支付状态的可以直接取消
            if (order.getSchedule() == 0) {
                //判断该订单是否使用优惠券
                if (StringUtils.isNotEmpty(order.getVoucherUseId())) {
                    //将优惠券的使用时间以及状态修改为未使用状态
                    voucherRepertoryDao.updataUseTime(order.getVoucherUseId());
                    //修改子订单状态
                    com.jinpaihushi.jphs.order.model.OrderService orderService = new com.jinpaihushi.jphs.order.model.OrderService();
                    orderService.setOrderId(orderId);
                    orderService.setCreatorId(order.getCreatorId());
                    List<com.jinpaihushi.jphs.order.model.OrderService> list = orderServiceDao.list(orderService);
                    for (com.jinpaihushi.jphs.order.model.OrderService orderService2 : list) {
                        orderService2.setSchedule(4);
                        orderServiceDao.update(orderService2);
                    }
                }
                //判断该订单是否有护士接单
                if (StringUtils.isNotEmpty(order.getAcceptUserId())) {
                    //获取护士的信息
                    User user = userDao.loadById(order.getAcceptUserId());
                    result.put("phone", user.getPhone());
                }
            }
        }
        return null;
    }

=======
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.insurance.dao.InsuranceDao;
import com.jinpaihushi.jphs.insurance.model.Insurance;
import com.jinpaihushi.jphs.order.dao.OrderDao;
import com.jinpaihushi.jphs.order.dao.OrderGoodsDao;
import com.jinpaihushi.jphs.order.dao.OrderOtherDao;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.model.OrderInfo;
import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.order.service.OrderServiceService;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.UUIDUtils;

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
	private OrderGoodsDao orderGoodsDao;
	@Autowired
	private OrderOtherDao orderOtherDao;
	@Autowired
	private OrderServiceService orderServiceService;
	@Autowired
	private ServiceImagesService serviceImagesService;
	@Autowired
	private InsuranceDao insuranceDao;
	@Autowired
	private VoucherUseDao voucherUseDao;
	@Autowired
	private GoodsDao goodsDao;

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
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> createOrder(OrderInfo orderInfo) {
		Map<String, Object> result = new HashMap<>();
		try {
			// 插入订单的基础信息
			Order order = new Order();
			BeanUtils.copyProperties(order, orderInfo);
			order.setId(UUIDUtils.getId());
			order.setOrderNo(Common.getOrderNumber());
			order.setSchedule(0);
			order.setStatus(0);
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
				orderGoods.setStatus(0);
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
					// 暂时写死，后期下单的时候带过来
					serviceImages.setDevice_type(1);
					serviceImages.setSourceId(order.getId());
					serviceImagesService.insert(serviceImages);
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
							orderService.setStatus(0);
							orderServiceService.insert(orderService);
						}
						// 添加保险信息
						Goods goods = goodsDao.loadById(orderInfo.getGoodsId());
						if (goods.getInsurance() == 1) {
							Insurance insurance = new Insurance();
							BeanUtils.copyProperties(insurance, orderInfo);
							insurance.setOrderId(order.getId());
							insurance.setId(UUIDUtils.getId());
							insurance.setStatus(0);
							insurance.setCreateTime(new Date());
							insuranceDao.insert(insurance);
						}
						if(StringUtils.isNotEmpty(orderInfo.getVoucherUseId())){
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
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
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
		orderService.setStatus(0);
		List<com.jinpaihushi.jphs.order.model.OrderService> serviceList = orderServiceService.list(orderService);
		if (orderDetail != null)
			orderDetail.setOrderServiceList(serviceList);
		return orderDetail;
	}

	@Override
	public boolean checkPrice(String orderNo, Double payParice) {
		Order order = new Order();
		order.setOrderNo(orderNo);
		Order orderDetail = orderDao.getUserOrderDetail(order);
		if (orderDetail.getOrderGoods().getPayPrice() == payParice)
			return true;
		return false;
	}

	
	public List<Map<String , Object>> getUptoDataGoods(Map<String,Object> map){
		return orderDao.getUptoDataGoods(map);
	}
	
	public List<Map<String , Object>> getOrderGoodsList(Map<String,Object> map){
		return orderDao.orderNotList(map);
	}
	
	public List<Map<String , Object>> nurseOrderList(Map<String,Object> map){
		return orderDao.nurseOrderList(map);
	}
	
	/**
	 * 订单详情
	 * @param order
	 * @return
	 */
	public Order nurseOrderDetails(Order order){
		return orderDao.nurseOrderDetails(order);
	}
	
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}