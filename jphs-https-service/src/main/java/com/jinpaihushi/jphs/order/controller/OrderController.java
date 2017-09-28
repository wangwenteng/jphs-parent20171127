package com.jinpaihushi.jphs.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderInfo;
import com.jinpaihushi.jphs.order.model.OrderPojo;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.pay.alipay.AlipaySign;
import com.jinpaihushi.pay.wechatpay.WechatPay;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoucherUseService voucherUseService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private CommodityOrderService commodityOrderService;

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(path = "/createOrder.json", name = "用户下单", method = RequestMethod.POST)
    @ResponseBody
    public byte[] createOrder(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, OrderInfo orderInfo,
            String authCode) {
        try {
            boolean flag = false;
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.createOrder.json");
            }

            if (orderInfo == null /*|| !ObjectVerification.verification(orderInfo)*/) {
                return JSONUtil.toJSONResult(0, "请核对参数信息", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(orderInfo.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //对订单信息进行验证
            //首先判断优惠券的信息 如果用户使用优惠券
            if (StringUtils.isNotEmpty(orderInfo.getVoucherUseId())) {
                //判断优惠券是否有效
                VoucherUse voucherUse = voucherUseService.loadById(orderInfo.getVoucherUseId());
                if (voucherUse.getStatus() == 1) {
                    return JSONUtil.toJSONResult(0, "非法请求！", null);
                }
                //验证该用户是否拥有改优惠券
                flag = voucherService.isHaveVoucher(orderInfo.getVoucherUseId(), orderInfo.getCreatorId());
                if (!flag) {
                    return JSONUtil.toJSONResult(0, "非法请求！", null);
                }
            }
            //对订单的价格进行验证
            String nurseId = null;
            if (StringUtils.isNotEmpty(orderInfo.getExpectorId())) {
                nurseId = orderInfo.getExpectorId();
            }
            Double price = voucherService.getGoodsPrice(orderInfo.getVoucherUseId(), orderInfo.getPricePartId(),
                    nurseId);
            if (DoubleUtils.sub(price, orderInfo.getPayPrice()) != 0) {
                return JSONUtil.toJSONResult(0, "非法请求！", null);
            }
            Map<String, Object> result = orderService.createOrder(orderInfo);
            if (result == null) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "" + "成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("order.createOrder.json", e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserOrder.json", name = "订单列表")
    @ResponseBody
    public byte[] getUserOrder(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer schedule, @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.getUserOrder.json userId=" + userId + " schedule=" + schedule);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String token = req.getHeader("token");
            //            if (StringUtils.isEmpty(token)) {
            //                return JSONUtil.toJSONResult(3, "非法请求", null);
            //            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //            if (!flag) {
            //                // 身份认证失败,返回错误信息
            //                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("schedule", schedule);
            PageHelper.startPage(p, n);
            List<Map<String, Object>> list = orderService.getUserOrder(query);
            PageInfo<Map<String, Object>> result = new PageInfo<Map<String, Object>>(list);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (

        Exception e) {
            Util.failLog.error("order.getUserOrder.json userId=" + userId + " schedule=" + schedule, e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserOrderDetail.json", name = "订单详情")
    @ResponseBody
    public byte[] getUserOrderDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String orderId,
            String userId, Integer deviceType) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.getUserOrderDetail.json orderId=" + orderId + " deviceType=" + deviceType
                        + " userId=" + userId);
            }
            if (StringUtils.isEmpty(orderId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            Order result = orderService.getUserOrderDetail(orderId, deviceType);
            if (result == null) {
                return JSONUtil.toJSONResult(0, "非法请求", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("order.getUserOrderDetail.json orderId=" + orderId + " deviceType=" + deviceType
                    + " userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserOrderStatus.json", name = "订单状态")
    @ResponseBody
    public byte[] getUserOrderStatus(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String orderId,
            String userId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.getUserOrderStatus.json orderId=" + orderId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(orderId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String token = req.getHeader("token");
            /* if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            Order result = orderService.loadById(orderId);
            if (result == null) {
                return JSONUtil.toJSONResult(0, "订单不存在", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", result.getSchedule());
        }
        catch (Exception e) {
            Util.failLog.error("order.getUserOrderStatus.json orderId=" + orderId + " userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/cancelOrder.json", name = "取消订单")
    @ResponseBody
    public byte[] cancelOrder(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String orderId,
            String userId, String remark) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.cancelOrder.json orderId=" + orderId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String token = req.getHeader("token");
            //            if (StringUtils.isEmpty(token)) {
            //                return JSONUtil.toJSONResult(3, "非法请求", null);
            //            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(userId);
            //            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //            if (!flag) {
            //                // 身份认证失败,返回错误信息
            //                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //            }
            String result = orderService.cancelOrder(orderId, user);
            //需要发短信
            //需要极光推送
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("order.cancelOrder.json orderId=" + orderId + " userId=" + userId, e);
        }
        return null;
    }

    /**
     * @param hs
     * @param req
     * @param resp
     * @param goodsName 商品名称
     * @param orderNo 订单编号
     * @param return_url 支付成功回调地址
     * @param payParice 支付金额
     * @param type 支付平台 微信扫码2 支付宝1 3 微信web支付  4 微信公众号 
     * @param userId 用户id
     * @param show_url 商品展示页
     * @param source 支付类型 
     * @param serviceType 服务是1 商品是2
     * @return
     */
    @RequestMapping(path = "/orderPay.json", name = "订单支付")
    @ResponseBody
    public byte[] orderPay(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsName,
            String orderNo, String orderId, String return_url, Double payParice, Integer type, String userId,
            String show_url, @RequestParam(name = "source", defaultValue = "5", required = true) Integer source,
            Integer serviceType, String openid) {
        try {
            String ip = req.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = req.getRemoteAddr();
            }
            logger.info("ip地址：" + ip);
            if (StringUtils.isEmpty(payParice.toString()) || StringUtils.isEmpty(orderNo) || type == null
                    || StringUtils.isEmpty(return_url) || StringUtils.isEmpty(userId) || StringUtils.isEmpty(return_url)
                    || StringUtils.isEmpty(show_url) || serviceType == null || payParice == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.orderPay.json goodsName=" + goodsName + " orderNo=" + orderNo
                        + " return_url=" + return_url + " show_url=" + show_url + " payParice=" + payParice + " type="
                        + type + " source=" + source + " serviceType=" + serviceType);
            }
            String token = req.getHeader("token");
            //            if (StringUtils.isEmpty(token)) {
            //                return JSONUtil.toJSONResult(3, "非法请求", null);
            //            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(userId);
            //            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            boolean flag = true;
            //            if (!flag) {
            //                // 身份认证失败,返回错误信息
            //                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //            }
            //判断支付金额
            String notify_url = "";
            if (serviceType == 1) {
                flag = orderService.checkPrice(orderNo, payParice);
                notify_url = "https://s.jinpaihushi.com/alipay/otherNotify.json";
            }
            else {
                notify_url = "https://s.jinpaihushi.com/alipay/otherNotifyGoods.json";
            }
            if (!flag) {
                return JSONUtil.toJSONResult(2, "非法请求！！", null);
            }
            //	支付宝支付
            if (type == 1) {
                Util.debugLog.debug("order.createOrder.json  支付宝支付");
                if (StringUtils.isEmpty(return_url) || StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(goodsName)
                        || payParice == null) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }

                JSONObject sParaTemp = new JSONObject();
                sParaTemp.put("_input_charset", "utf-8");
                sParaTemp.put("body", goodsName);
                sParaTemp.put("notify_url", notify_url);
                sParaTemp.put("out_trade_no", orderNo);
                sParaTemp.put("payment_type", "1");
                sParaTemp.put("return_url", return_url);
                sParaTemp.put("show_url", show_url);
                sParaTemp.put("subject", goodsName);
                sParaTemp.put("total_fee", payParice);
                sParaTemp.put("serviceType", serviceType);

                byte[] s = AlipaySign.getAlisign(sParaTemp.toString(), "PRIVATE_KEY", source.toString());
                return s;
            }
            //	微信扫码支付
            if (type == 2) {
                Util.debugLog.debug("order.createOrder.json  weixin_pay");
                if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(goodsName) || payParice == null) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }
                String remoteAddr = req.getRemoteAddr();
                System.out.println(remoteAddr);
                JSONObject sParaTemp = new JSONObject();
                sParaTemp.put("body", goodsName);
                sParaTemp.put("out_trade_no", orderNo);
                sParaTemp.put("price", payParice * 100);
                sParaTemp.put("CREATE_IP", ip);
                sParaTemp.put("serviceType", serviceType);
                byte[] s = WechatPay.weixin_pay(sParaTemp.toString());
                return s;
            }
            //	微信web支付
            if (type == 3) {
                Util.debugLog.debug("order.createOrder.json  weixin_webpay");
                if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(goodsName) || payParice == null) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }
                String remoteAddr = req.getRemoteAddr();
                System.out.println(remoteAddr);
                JSONObject sParaTemp = new JSONObject();
                sParaTemp.put("body", goodsName);
                sParaTemp.put("out_trade_no", orderNo);
                sParaTemp.put("price", payParice * 100);
                sParaTemp.put("CREATE_IP", ip);
                sParaTemp.put("serviceType", serviceType);
                byte[] s = WechatPay.weixin_webpay(sParaTemp.toString());
                return s;
            }
            //	微信公众号支付
            if (type == 4) {
                Util.debugLog.debug("order.createOrder.json  weixin_webpay");
                if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(openid) || StringUtils.isEmpty(goodsName)
                        || payParice == null) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }
                String remoteAddr = req.getRemoteAddr();
                System.out.println(remoteAddr);
                JSONObject sParaTemp = new JSONObject();
                sParaTemp.put("body", goodsName);
                sParaTemp.put("out_trade_no", orderNo);
                sParaTemp.put("price", payParice * 100);
                sParaTemp.put("CREATE_IP", ip);
                sParaTemp.put("serviceType", serviceType);
                sParaTemp.put("OPENID", openid);
                byte[] s = WechatPay.weixin_wechatpay(sParaTemp.toString());
                return s;
            }
            //	余额支付
            if (type == 5) {
                Util.debugLog.debug("order.createOrder.json  balance_pay");
                if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(goodsName) || payParice == null) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }
                if (serviceType == 1) {
                    byte[] rs = orderService.balancePayment(orderId, orderNo, payParice, userId);
                    return rs;
                }
                if (serviceType == 2) {
                    byte[] rs = commodityOrderService.balancePayment(orderId, orderNo, payParice, userId);
                    return rs;
                }
            }

        }
        catch (Exception e) {
            Util.failLog.error("order.orderPay.json goodsName=" + goodsName + " orderNo=" + orderNo + " return_url="
                    + return_url + " payParice=" + payParice + " type=" + type + " source=" + source, e);
        }
        return null;
    }

    @RequestMapping(path = "/createOrderNew.json", name = "用户下单", method = RequestMethod.POST)
    @ResponseBody
    public byte[] createOrderNew(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, OrderPojo orderInfo,
            String authCode) {
        try {
            boolean flag = false;
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.createOrder.json");
            }

            if (orderInfo == null) {
                return JSONUtil.toJSONResult(0, "请核对参数信息", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(orderInfo.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //对订单信息进行验证
            //首先判断优惠券的信息 如果用户使用优惠券
            if (StringUtils.isNotEmpty(orderInfo.getVoucherUseId())) {
                //判断优惠券是否有效
                VoucherUse voucherUse = voucherUseService.loadById(orderInfo.getVoucherUseId());
                if (voucherUse.getStatus() == 1) {
                    return JSONUtil.toJSONResult(0, "非法请求！", null);
                }
                //验证该用户是否拥有改优惠券
                flag = voucherService.isHaveVoucher(orderInfo.getVoucherUseId(), orderInfo.getCreatorId());
                if (!flag) {
                    return JSONUtil.toJSONResult(0, "非法请求！", null);
                }
            }
            Map<String, Object> result = orderService.createOrderNew(orderInfo);
            if (result == null) {
                return JSONUtil.toJSONResult(0, "网络异常！", null);
            }
            if (null != result.get("msg") && StringUtils.isNotEmpty(result.get("msg").toString())) {
                return JSONUtil.toJSONResult(0, result.get("msg").toString(), null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("order.createOrder.json", e);
        }
        return null;
    }
}
