package com.jinpaihushi.jphs.commodity.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.car.dao.CarDao;
import com.jinpaihushi.jphs.car.model.Car;
import com.jinpaihushi.jphs.commodity.dao.CommodityDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderInfoDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityPriceDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.jphs.nurse.service.NurseCommodityService;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.dao.UserAddressDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
@Service("commodityOrderService")
public class CommodityOrderServiceImpl extends BaseServiceImpl<CommodityOrder> implements CommodityOrderService {

    @Autowired
    private CommodityOrderDao commodityOrderDao;

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommodityOrderInfoDao commodityOrderInfoDao;

    @Autowired
    private CommodityPriceDao commodityPriceDao;

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private DoPostSmsService doPostSmsService;
    @Autowired
    private NurseCommodityService nurseCommodityService;

    @Value("${SMS_pay_success}")
    private String SMS_pay_success;

    @Override
    protected BaseDao<CommodityOrder> getDao() {
        return commodityOrderDao;
    }

    public List<HashMap<String, Object>> loadS(CommodityOrder commodityOrder) {
        return commodityOrderDao.loadS(commodityOrder);
    }

    private PlatformTransactionManager txManager;//创建事务管理器
    
    @Override
    public String createCommodityOrder(String userId, String commodityIds, String userAddressId, String cpIds,
            String guideId, Integer number, String remark, double payPrice, String code, Integer device,
            String platformId) {
        String orderNo = "";
        try {
            String[] commodityIdArr = commodityIds.split(",");
            String[] cpIdArr = cpIds.split(",");
            Set<String> set = new HashSet<String>();
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (int i = 0; i < commodityIdArr.length; i++) {
                if (!("".equals(commodityIdArr[i]))) {

                    Commodity commodity = commodityDao.loadById(commodityIdArr[i]);
                    set.add(commodity.getBusinessId());
                }

            }

            for (String businessId : set) {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < commodityIdArr.length; i++) {

                    if (!("".equals(commodityIdArr[i]))) {

                        Commodity commodity = commodityDao.loadById(commodityIdArr[i]);
                        if (commodity.getBusinessId().equals(businessId)) {
                            System.out.println(commodityIdArr[i] + "," + cpIdArr[i]);
                            list.add(commodityIdArr[i] + "," + cpIdArr[i]);

                        }
                    }
                }
                map.put(businessId, list);
            }

            Set<String> keys = map.keySet();
            Iterator<String> iterator = keys.iterator();
            // 按时间格式生成orderNo
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            orderNo = sdf.format(date);
            orderNo = "CM" + orderNo + (Math.random() * 9000 + 1000 + "").substring(0, 4);
            Integer i = 0;
            while (iterator.hasNext()) {

                String id = UUID.randomUUID().toString();

                String key = iterator.next();
                List<String> arrayList = map.get(key);
                UserAddress userAddress = userAddressDao.loadById(userAddressId);
                User user = userDao.loadById(userId);

                CommodityOrder comObj = null;

                for (String comAndCp : arrayList) {
                    String[] comAndCpArr = comAndCp.split(",");

                    Commodity commodity = commodityDao.loadById(comAndCpArr[0]);
                    i++;

                    /* Car car = null;
                    	if(number ==null){
                    		Car carModel = new Car();
                    		carModel.setCommodityId(comAndCpArr[0]);
                    		carModel.setCommodityPriceId(comAndCpArr[1]);
                    		carModel.setStatus(1);
                    		carModel.setCreatorId(userId);
                    		car = carDao.lookup(carModel);
                    	}*/

                    CommodityPrice commodityPrice = commodityPriceDao.loadById(comAndCpArr[1]);
                    CommodityOrder commodityOrder = new CommodityOrder();
                    commodityOrder.setId(id);
                    commodityOrder.setOrderNo(orderNo + "-" + i);
                    System.out.println(commodityPrice.getPrice());
                    System.out.println(number);
                    commodityOrder.setPayPrice(payPrice);
                    commodityOrder.setProtectDay(commodity.getProtectDay());
                    commodityOrder.setVoucherUseId("");
                    commodityOrder.setDevice(device);
                    commodityOrder.setPlatformId(platformId);
                    // commodityOrder.setVoucherPrice(0);
                    commodityOrder.setSchedule(0);
                    commodityOrder.setStatus(1);
                    commodityOrder.setAddress(userAddress.getProvince() + "，" + userAddress.getCity() + "，"
                            + userAddress.getArea() + "，");
                    commodityOrder.setDetailAddress(userAddress.getDetailaddress());
                    commodityOrder.setCreatorId(userId);
                    commodityOrder.setCreatorName(user.getName());
                    commodityOrder.setCreateTime(date);
                    commodityOrder.setPhone(userAddress.getPhone());
                    commodityOrder.setReceiveName(userAddress.getName());
                    int insert = commodityOrderDao.insert(commodityOrder);
                    Map<String, Object> modelMap = new HashMap<>();
                    modelMap.put("orderNo", orderNo + "-" + i);
                    comObj = commodityOrderDao.getObjectByOrder(modelMap);
                    
                    if (insert < 0) {
                        return "创建失败";
                    }
                    else {
                        break;
                    }
                }

                for (String comAndCp : arrayList) {
                    String[] comAndCpArr = comAndCp.split(",");
                    Car car = null;
                    if (number == null) {
                        Car carModel = new Car();
                        carModel.setCommodityId(comAndCpArr[0]);
                        carModel.setCommodityPriceId(comAndCpArr[1]);
                        carModel.setStatus(1);
                        carModel.setCreatorId(userId);
                        car = carDao.lookup(carModel);
                    }

                    Commodity commodity = commodityDao.loadById(comAndCpArr[0]);
                    CommodityPrice commodityPrice = commodityPriceDao.loadById(comAndCpArr[1]);
                    // 创建详细订单
                    CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();

                    commodityOrderInfo.setId(UUID.randomUUID().toString());
                    commodityOrderInfo.setCommodityOrderId(comObj.getId());
                    commodityOrderInfo.setCommodityId(comAndCpArr[0]);
                    commodityOrderInfo.setCommodityPriceId(comAndCpArr[1]);
                    double profit =0.0;
                    
                    if(commodityPrice.getProfit()>=1){
                    	profit = commodityPrice.getProfit();
                    }else{
                    	profit = (commodityPrice.getPrice() -commodityPrice.getCostPrice()) * commodityPrice.getProfit();
                    }
                    
                    commodityOrderInfo.setProfit(profit);
                    commodityOrderInfo.setTitle(commodity.getTitle());
                    commodityOrderInfo.setOldPrice(commodityPrice.getOldPrice());
                    commodityOrderInfo.setPrice(commodityPrice.getPrice());
                    commodityOrderInfo.setCommodityPriceName(commodityPrice.getName());
                    commodityOrderInfo.setCommodityModel(commodity.getModel());
                    
                   
                    if (car != null) {
                        commodityOrderInfo.setNumber(car.getNumber());
                        commodityOrderInfo.setUserId(car.getUserId());
                        commodityOrderInfo.setCode(car.getCode());
                    }else {
                        commodityOrderInfo.setNumber(number);
                        commodityOrderInfo.setUserId(guideId);
                        commodityOrderInfo.setCode(code);
                    }

                    commodityOrderInfo.setRemark(remark);
                    commodityOrderInfo.setStatus(1);
                    commodityOrderInfo.setCreatorId(userId);
                    commodityOrderInfo.setCreatorName(user.getName());
                    commodityOrderInfo.setCreateTime(date);
                    int insert2 = commodityOrderInfoDao.insert(commodityOrderInfo);

                    if (insert2 < 0) {
                        return "创建失败";
                    }

                }

            }

        }
        catch (Exception e) {
            orderNo = "";
            e.printStackTrace();
        }
        return orderNo;
    }

    @Override
    public String cancelShopOrder(String id) {

        CommodityOrder comObj = commodityOrderDao.loadById(id);

        Date date = new Date();
        comObj.setStatus(0);
        comObj.setSchedule(-1);
        comObj.setConfirmTime(date);
        int result = commodityOrderDao.update(comObj);

        CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
        commodityOrderInfo.setStatus(-1);
        commodityOrderInfo.setCommodityOrderId(id);
        result = commodityOrderInfoDao.updateByOrderNo(commodityOrderInfo);
        if (result > 0) {
            return "1";
        }
        else {
            return "0";
        }

    }

    @Override
    public List<CommodityOrder> getOrderList(String userId, String schedule) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("schedule", schedule);
        List<CommodityOrder> orderList = commodityOrderDao.getOrderList1(map);

        for (int i = 0; i < orderList.size(); i++) {

            String id = orderList.get(i).getId();

            int allNumber = commodityOrderInfoDao.getAllNumber(id);
            orderList.get(i).setCount(allNumber);

            /*List<CommodityOrderInfo> coiList = orderList.get(i).getCoiList();
            Double payment = 0.0;
            for (int j = 0; j < coiList.size(); j++) {
            	Integer number = coiList.get(j).getNumber();
            	Double price = coiList.get(j).getPrice();
            	payment += (price * number);
            }
            orderList.get(i).setPayment(payment);*/
            List<CommodityOrderInfo> setCoiList = commodityOrderInfoDao.getOrderInfo(id);

            orderList.get(i).setCoiList(setCoiList);
        }

        return orderList;
    }

    @Override
    public CommodityOrder getOrderDetail(String orderId) {

        CommodityOrder commodityOrder = commodityOrderDao.getOrderDetail(orderId);

        List<CommodityOrderInfo> coiList = commodityOrder.getCoiList();
        for (int i = 0; i < coiList.size(); i++) {
            if (coiList.get(i).getCrStatus() != null) {
                if (coiList.get(i).getCrStatus() == -1) {
                    coiList.remove(i);
                }
            }

        }

        return commodityOrder;
    }

    @Override
    public Integer updateShopOrderSchedule(CommodityOrder commodityOrder) {

        Integer result = commodityDao.updateShopOrderSchedule(commodityOrder);

        // 修改状态成功且为退货，取消订单
        if (result > 0) {
            if (commodityOrder.getSchedule() < 0) {
                CommodityOrderInfo coi = new CommodityOrderInfo();
                coi.setCommodityOrderId(commodityOrder.getId());
                coi.setStatus(-1);
                result = commodityOrderInfoDao.updateByOrderNo(coi);
            }
        }

        return result;
    }

    @Override
    public Integer updateRemindTime(CommodityOrder commodityOrder) {
        commodityOrder.setRemindTime(new Date());
        Integer result = commodityOrderDao.updateRemindTime(commodityOrder);
        return result;
    }

    @Override
    public Integer confimOrder(CommodityOrder commodityOrder) {

        Integer result = 0;
        Date date = new Date();
        commodityOrder.setTakeTime(date);
        result = commodityOrderDao.confimOrder(commodityOrder);

        return result;
    }

    @Override
    public Integer deleteOrder(CommodityOrder commodityOrder) {

        Integer result = 0;
        Date date = new Date();

        commodityOrder.setConfirmTime(date);
        result = commodityOrderDao.deleteOrder(commodityOrder);

        return result;
    }

    @Override
    public List<CommodityOrder> getListByOrderNo(String OrderNo) {

        return commodityOrderDao.getListByOrderNo(OrderNo);
    }

    @Override
    public Integer toUpdatePayPrice(String id, double payPrice) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("payPrice", payPrice);
        return commodityOrderDao.toUpdatePayPrice(map);
    }

    // 商品支付回调
    public boolean updateWechatCommodityOrderStutas(SortedMap<Object, Object> packageParams) {
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
                Util.debugLog.debug("wechat.updateWechatCommodityOrderStutas:mch_id=" + mch_id + ",openid=" + openid
                        + ",is_subscribe=" + is_subscribe + ",out_trade_no=" + out_trade_no + ",total_fee=" + total_fee
                        + "-------attach=" + attach);
            }

            CommodityOrder commodityOrder = new CommodityOrder();
            commodityOrder.setOrderNo(out_trade_no);
            commodityOrder.setStatus(1);
            commodityOrder.setSchedule(0);
            List<HashMap<String, Object>> commodityOrders_list = commodityOrderDao.loadS(commodityOrder);

            if (commodityOrders_list != null && !"".equals(commodityOrders_list) && commodityOrders_list.size() > 0) {
                // 记录日志-debug
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("updateWechatCommodityOrderStutas;根据微信返回out_trade_no=" + out_trade_no
                            + "平台内部订单号查询订单结果：orders=" + JSONArray.fromObject(commodityOrders_list).toString());
                }
                String userId = "";
                for (int a = 0; a < commodityOrders_list.size(); a++) {
                    HashMap<String, Object> map_c = commodityOrders_list.get(a);
                    // 记录日志-debug
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("updateWechatCommodityOrderStutas;" + a + "-map_c="
                                + JSONObject.fromObject(map_c).toString());
                    }
                    if (a == 0) {
                        userId = map_c.get("creator_id").toString();
                    }
                    CommodityOrder commodityOrder_up = new CommodityOrder();
                    commodityOrder_up.setPayTime(new Date());
                    commodityOrder_up.setSchedule(1);
                    commodityOrder_up.setId(map_c.get("id").toString());
                    commodityOrderDao.update(commodityOrder_up);
                    CommodityOrderInfo coi_up = new CommodityOrderInfo();
                    coi_up.setCommodityOrderId(map_c.get("id").toString());
                    CommodityOrderInfo coi = commodityOrderInfoDao.load(coi_up);
                    coi_up.setId(coi.getId());
                    coi_up.setStatus(2);
                    commodityOrderInfoDao.update(coi_up);

                    String remark = "";
                    if(coi.getTitle() != null && !coi.getTitle().equals("")){
                    	remark = coi.getTitle();
                    }
                    
                    Transaction transaction = new Transaction();
                    transaction.setId(UUID.randomUUID().toString());
                    transaction.setOrderId(map_c.get("id").toString());
                    transaction.setAmount(Double.parseDouble(map_c.get("pay_price").toString()));
                    transaction.setScore((new Double(transaction.getAmount())).intValue());
                    transaction.setOperate(3);
                    transaction.setOperateSource(2);
                    transaction.setRemark(remark);
                    transaction.setWithdraw(0);
                    transaction.setPayType(1);
                    transaction.setOutTradeNo(transaction_id);
                    transaction.setCreatorId(map_c.get("creator_id").toString());
                    transaction.setCreatorName(map_c.get("creator_name").toString());
                    transaction.setCreateTime(new Date());
                    transaction.setStatus(1);
                    transaction.setType(2);
                    // 记录日志-debug
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("updateWechatCommodityOrderStutas;transaction="
                                + JSONObject.fromObject(transaction).toString());
                    }
                    transactionDao.insert(transaction);
                }
                try {
                    if (userId != null && !userId.equals("")) {
                        User user = new User();
                        user.setId(userId);
                        User orderUser = userDao.load(user);
                        if (orderUser != null) {
                            // 记录日志-debug
                            if (Util.debugLog.isDebugEnabled()) {
                                Util.debugLog.debug("updateWechatCommodityOrderStutas;订单用户信息orderUser="
                                        + JSONObject.fromObject(orderUser).toString());
                            }
                            // 发送验证码
                            doPostSmsService.sendSms(orderUser.getPhone(), SMS_pay_success,
                                    "{\"out_trade_no\":\"" + out_trade_no + "\"}");
                        }
                    }
                }
                catch (Exception e) {
                }
                return true;
            }
            else {
                // 记录日志-debug
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("alipay.notify.json;下单失败！查询不到该订单数据-查看status状态以及schedule字段是否正确");
                }
                return false;
            }
        }
        catch (Exception e) {
            Util.failLog.error("updateWechatCommodityOrderStutas：1e=", e);
        }
        return false;
    }

    @Override
    public List<CommodityOrder> getStatusByOrderNo(String orderNo) {
        // TODO Auto-generated method stub
        return commodityOrderDao.getStatusByOrderNo(orderNo);
    }

}