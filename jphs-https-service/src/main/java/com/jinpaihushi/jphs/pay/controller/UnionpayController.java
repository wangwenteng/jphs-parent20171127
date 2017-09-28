package com.jinpaihushi.jphs.pay.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.service.OrderGoodsService;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.push.service.NurseJPushService;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.pay.unionpay.Form_6_2_FrontConsume;
import com.jinpaihushi.pay.unionpay.sdk.AcpService;
import com.jinpaihushi.pay.unionpay.sdk.LogUtil;
import com.jinpaihushi.pay.unionpay.sdk.SDKConfig;
import com.jinpaihushi.pay.unionpay.sdk.SDKConstants;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/unionpay")
public class UnionpayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoPostSmsService doPostSmsService;

    @Autowired
    private NurseJPushService nurseJPushService;

    //#护士接单
    @Value("${SMS_Nurse_orders}")
    private String SMS_Nurse_orders;

    //    #给护士派单（上门服务）
    @Value("${SMS_nurse_delivery_order}")
    private String SMS_nurse_delivery_order;

    //支付成功
    @Value("${SMS_pay_success}")
    private String SMS_pay_success;

    //通知新订单
    @Value("${SMS_notice_order}")
    private String SMS_notice_order;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "/sign.json", name = "银联+支付")
    @ResponseBody
    public byte[] unionpaySign(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String url,
            String price, String orderNumber) {
        byte[] html_from = Form_6_2_FrontConsume.getHtml(url, price, orderNumber);
        if (Util.debugLog.isDebugEnabled()) {
            Util.debugLog.debug("银联支付 - from表单：html_from=" + html_from);
        }
        return html_from;
    }

    @SuppressWarnings("static-access")
    @RequestMapping(path = "/unionpayNotify.json", name = "银联支付异步回调")
    @Transactional
    public void unionpayNotify(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SDKConfig.getConfig().loadPropertiesFromSrc();
        req.setCharacterEncoding("ISO-8859-1");
        String encoding = req.getParameter(SDKConstants.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);
        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                value = new String(value.getBytes("ISO-8859-1"), encoding);
                valideData.put(key, value);
            }
        }

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            //验签失败，需解决验签问题
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("unionpay.notify.json;验证签名结果[失败].");
            }
        }
        else {
            LogUtil.writeLog("验证签名结果[成功].");
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderId = valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode = valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("unionpay.notify.json;orderId=" + orderId);
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("unionpay.notify.json;respCode=" + respCode);
            }
            Order order = new Order();
            order.setOrderNo(orderId);
            order.setStatus(0);
            order.setSchedule(0);
            Order orders = orderService.load(order);
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("unionpay.notify.json;orders=" + new net.sf.json.JSONObject().fromObject(orders));
            }
            if (orders != null && !"".equals(orders)) {
                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setOrderId(orders.getId());
                orderGoods.setStatus(0);
                OrderGoods orderGoods_ny = orderGoodsService.load(orderGoods);
                // 记录日志-debug
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("alipay.notify.json;订单goods数据orderGoods_ny="
                            + JSONObject.fromObject(orderGoods_ny).toString());
                }

                if (orderGoods_ny != null && !"".equals(orderGoods_ny)) {
                    double payPrice = orderGoods_ny.getPayPrice();
                    Transaction transaction = new Transaction();
                    transaction.setId(UUID.randomUUID().toString());
                    transaction.setOrderId(orders.getId());
                    transaction.setAmount(payPrice);
                    transaction.setScore((new Double(payPrice)).intValue());
                    transaction.setOperate(3);
                    transaction.setOperateSource(0);
                    transaction.setRemark("支付宝回调");
                    transaction.setWithdraw(0);
                    transaction.setPayType(1);
                    transaction.setOutTradeNo(orderId);
                    transaction.setCreatorId(order.getCreatorId());
                    transaction.setCreatorName(order.getCreatorName());
                    transaction.setCreateTime(new Date());
                    transaction.setStatus(1);
                    // 记录日志-debug
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug(
                                "alipay.notify.json;transaction=" + JSONObject.fromObject(transaction).toString());
                    }
                    int i = transactionService.insert(transaction).length();
                    if (i > 0) {
                        Order orderUp = new Order();
                        orderUp.setId(orders.getId());
                        orderUp.setSchedule(1);
                        boolean orderUpbool = orderService.update(orderUp);
                        try {
                            User user = new User();
                            user.setId(orders.getCreatorId());
                            user.setStatus(0);
                            User orderUser = userService.load(user);
                            // 记录日志-debug
                            if (Util.debugLog.isDebugEnabled()) {
                                Util.debugLog.debug("alipay.notify.json;订单用户信息orderUser="
                                        + JSONObject.fromObject(orderUser).toString());
                            }
                            if (orderUser != null) {
                                // 发送验证码
                                Map<String, Object> map = orderService.getSmsMessage(orders.getId());
                                // 发送验证码
                                //通知用户下单成功
                                doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_pay_success,
                                        "{\"service_name\":\"" + map.get("goodsName").toString() + "\"}");
                                //通知客服
                                doPostSmsService.sendSms("13581912414", SMS_notice_order,
                                        "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"order_no\":\""
                                                + map.get("order_no").toString() + "\"}");
                                //判断订单有没有接单人有的话通知护士
                                if (StringUtils.isNotEmpty(map.get("nursePhone").toString())) {
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
                                    nurseJPushService.jpushTag("", MD5.md5crypt(MD5.md5crypt(area)).substring(0, 8),
                                            "0");
                                }
                            }

                        }
                        catch (Exception e) {
                        }
                        if (!orderUpbool) {
                            i = 0;
                        }
                    }
                }
                else {
                    // 记录日志-debug
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("unionpay.notify.json;下单失败orders=" + orderGoods_ny);
                    }
                }
            }
            else {
                // 记录日志-debug
                if (Util.debugLog.isDebugEnabled()) {
                    Util.debugLog.debug("unionpay.notify.json;下单失败orders=" + orders);
                }
            }
        }
        // 记录日志-debug
        if (Util.debugLog.isDebugEnabled()) {
            Util.debugLog.debug("unionpay.notify.json;BackRcvResponse接收后台通知结束");
        }
        //返回给银联服务器http 200  状态码
        resp.getWriter().print("ok");
    }

    /**
     * 获取请求参数中所有的信息
     * 
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                //System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }

}
