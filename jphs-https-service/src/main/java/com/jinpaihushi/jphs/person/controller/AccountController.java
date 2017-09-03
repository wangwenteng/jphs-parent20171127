package com.jinpaihushi.jphs.person.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.service.OrderGoodsService;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @RequestMapping(path = "/getUserAccount.json", name = "个人账户")
    @ResponseBody
    public byte[] getUserAccount(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("account.getUserAccount.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //			String token = req.getHeader("token");
            //			if (StringUtils.isEmpty(token)) {
            //				return JSONUtil.toJSONResult(3, "非法请求", null);
            //			}
            //			User user = (User) req.getSession().getAttribute("user");
            //			if (user == null)
            //				user = userService.loadById(userId);
            //			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //			if (!flag) {
            //				// 身份认证失败,返回错误信息
            //				return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //			}
            Account account = new Account();
            account.setCreatorId(userId);
            Account result = accountService.load(account);
            if (result == null) {
                return JSONUtil.toJSONResult(0, "操作失败", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("account.getUserAccount.json userId=" + userId, e);
        }
        return null;
    }

    /**
     * @param hs
     * @param req
     * @param resp
     * @param userId 查询用户id
     * @param operate 操作(1.提现, 2.充值, 3.消费 , 4.收入, 5.系统调整)
     * @return
     */
    @RequestMapping(path = "/getMonthList.json", name = "本年的交易记录的月份列表")
    @ResponseBody
    public byte[] getMonthList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer operate) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("account.getMonthList.json userId=" + userId + " operate=" + operate);
            }
            if (StringUtils.isEmpty(userId) || operate == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //			String token = req.getHeader("token");
            //			if (StringUtils.isEmpty(token)) {
            //				return JSONUtil.toJSONResult(3, "非法请求", null);
            //			}
            //			User user = (User) req.getSession().getAttribute("user");
            //			if (user == null)
            //				user = userService.loadById(userId);
            //			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //			if (!flag) {
            //				// 身份认证失败,返回错误信息
            //				return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //			}
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("operate", operate);
            List<Map<String, Object>> result = accountService.getMonthList(query);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("account.getMonthList.json userId=" + userId + " operate=" + operate, e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserBalance.json", name = "用户余额")
    @ResponseBody
    public byte[] getUserBalance(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer operate, String month) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "account.getTranByMonth.json userId=" + userId + " operate=" + operate + " month=" + month);
            }
            if (StringUtils.isEmpty(userId) || operate == null || StringUtils.isEmpty(month)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //			String token = req.getHeader("token");
            //			if (StringUtils.isEmpty(token)) {
            //				return JSONUtil.toJSONResult(3, "非法请求", null);
            //			}
            //			User user = (User) req.getSession().getAttribute("user");
            //			if (user == null)
            //				user = userService.loadById(userId);
            //			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //			if (!flag) {
            //				// 身份认证失败,返回错误信息
            //				return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //			}
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("operate", operate);
            query.put("month", month);
            List<Map<String, Object>> result = accountService.getMonthList(query);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error(
                    "account.getTranByMonth.json userId=" + userId + " operate=" + operate + " month=" + month, e);
        }
        return null;
    }

    //获取账户余额
    @RequestMapping(path = "/getTranByMonth.json", name = "得到指定月的交易记录")
    @ResponseBody
    public byte[] getTranByMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer operate, String month) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "account.getTranByMonth.json userId=" + userId + " operate=" + operate + " month=" + month);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //          String token = req.getHeader("token");
            //          if (StringUtils.isEmpty(token)) {
            //              return JSONUtil.toJSONResult(3, "非法请求", null);
            //          }
            //          User user = (User) req.getSession().getAttribute("user");
            //          if (user == null)
            //              user = userService.loadById(userId);
            //          boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //          if (!flag) {
            //              // 身份认证失败,返回错误信息
            //              return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //          }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("operate", operate);
            query.put("month", month);
            List<Map<String, Object>> result = accountService.getMonthList(query);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error(
                    "account.getTranByMonth.json userId=" + userId + " operate=" + operate + " month=" + month, e);
        }
        return null;
    } //获取账户余额

    @RequestMapping(path = "/balancePayment.json", name = "余额支付")
    @ResponseBody
    public byte[] balancePayment(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String orderId,
            Double payParice, String userId, String orderNo) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("account.balancePayment.json orderId=" + orderId + " payParice=" + payParice
                        + "userId=" + userId + " orderNo=" + orderNo);
            }
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(orderId) || payParice == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //          String token = req.getHeader("token");
            //          if (StringUtils.isEmpty(token)) {
            //              return JSONUtil.toJSONResult(3, "非法请求", null);
            //          }
            //          User user = (User) req.getSession().getAttribute("user");
            //          if (user == null)
            //              user = userService.loadById(userId);
            //          boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //          if (!flag) {
            //              // 身份认证失败,返回错误信息
            //              return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //          }
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orderId);
            orderGoods = orderGoodsService.load(orderGoods);
            //判断支付金额跟订单金额
            if (DoubleUtils.sub(orderGoods.getPayPrice(), payParice) != 0) {

                return JSONUtil.toJSONResult(0, "非法请求", null);
            }
            Account model = new Account();
            model.setCreatorId(userId);
            Account account = accountService.load(model);
            if (DoubleUtils.sub(account.getBalance(), payParice) < 0) {
                return JSONUtil.toJSONResult(0, "余额不足，请充值后再操作！", null);
            }
            Double balance = DoubleUtils.sub(account.getBalance(), payParice);
            account.setBalance(balance);
            accountService.update(account);
            Order order = new Order();
            order.setId(orderId);
            order.setSchedule(1);
            orderService.update(order);
            return JSONUtil.toJSONResult(1, "支付成功！", null);
        }
        catch (Exception e) {
            Util.failLog.error("account.balancePayment.json orderId=" + orderId + " payParice=" + payParice + "userId="
                    + userId + " orderNo=" + orderNo, e);
        }
        return null;
    }
}
