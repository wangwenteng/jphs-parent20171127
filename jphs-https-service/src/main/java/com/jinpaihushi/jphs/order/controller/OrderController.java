package com.jinpaihushi.jphs.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderInfo;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.pay.alipay.AlipaySign;
import com.jinpaihushi.pay.wechatpay.WechatPay;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.ObjectVerification;
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
    private VoucherService voucherService;

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

            if (orderInfo == null || !ObjectVerification.verification(orderInfo)) {
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
                //验证该用户是否拥有改优惠券
                flag = voucherService.isHaveVoucher(orderInfo.getVoucherUseId(), orderInfo.getCreatorId());
                if (!flag) {
                    return JSONUtil.toJSONResult(0, "非法请求！", null);
                }
            }
            //对订单的价格进行验证
            Double price = voucherService.getGoodsPrice(orderInfo.getVoucherUseId(), orderInfo.getPricePartId());
            if (DoubleUtils.sub(price, orderInfo.getPayPrice()) != 0) {
                return JSONUtil.toJSONResult(0, "非法请求！", null);
            }
            Map<String, Object> result = orderService.createOrder(orderInfo);
            if (result == null) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("order.createOrder.json", e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserOrder.json", name = "订单列表")
    @ResponseBody
    public byte[] getUserOrder(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer schedule) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.getUserOrder.json userId=" + userId + " schedule=" + schedule);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String token = req.getHeader("token");
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
            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("schedule", schedule);
            List<Map<String, Object>> result = orderService.getUserOrder(query);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
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
                Util.debugLog.debug("order.getUserOrderDetail.json orderId=" + orderId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(orderId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String token = req.getHeader("token");
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
            }
            Order result = orderService.loadById(orderId);
            return JSONUtil.toJSONResult(1, "操作成功！", result.getSchedule());
        }
        catch (Exception e) {
            Util.failLog.error("order.getUserOrderDetail.json orderId=" + orderId + " userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/orderPay.json", name = "订单支付")
    @ResponseBody
    public byte[] orderPay(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsName,
            String orderNo, String return_url, Double payParice, Integer type, String userId) {
        try {
            if (type == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.orderPay.json goodsName=" + goodsName + " orderNo=" + orderNo
                        + " return_url=" + return_url + " payParice=" + payParice + " type=" + type);
            }
            String token = req.getHeader("token");
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
            }
            //判断支付金额
            flag = orderService.checkPrice(orderNo, payParice);
            if (!flag) {
                return JSONUtil.toJSONResult(2, "非法请求！！", null);
            }
            if (type == 1) {

                if (StringUtils.isEmpty(return_url) || StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(goodsName)
                        || payParice == null) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }

                JSONObject sParaTemp = new JSONObject();
                sParaTemp.put("_input_charset", "utf-8");
                sParaTemp.put("body", goodsName);
                sParaTemp.put("notify_url", "https://s.jinpaihushi.com/alipay/otherNotify.json");
                sParaTemp.put("out_trade_no", orderNo);
                sParaTemp.put("payment_type", "1");
                sParaTemp.put("return_url", return_url);
                sParaTemp.put("show_url", "https://site.jinpaihushi.com");
                sParaTemp.put("subject", goodsName);
                sParaTemp.put("total_fee", payParice);

                byte[] s = AlipaySign.getAlisign(sParaTemp.toString(), "PRIVATE_KEY", "1");
                return s;
            }

            if (type == 2) {

                if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(goodsName) || payParice == null) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }
                JSONObject sParaTemp = new JSONObject();
                sParaTemp.put("body", goodsName);
                sParaTemp.put("out_trade_no", orderNo);
                sParaTemp.put("price", payParice * 100);

                byte[] s = WechatPay.weixin_pay(sParaTemp.toString());
                return s;
            }

        }
        catch (Exception e) {
            Util.failLog.error("order.orderPay.json goodsName=" + goodsName + " orderNo=" + orderNo + " return_url="
                    + return_url + " payParice=" + payParice + " type=" + type, e);
        }
        return null;
    }
}
