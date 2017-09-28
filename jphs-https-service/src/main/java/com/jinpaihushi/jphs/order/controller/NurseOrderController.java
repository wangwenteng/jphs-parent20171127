package com.jinpaihushi.jphs.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.order.service.OrderServiceService;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseOrder")
public class NurseOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderServiceService orderServiceService;

    @Autowired
    private ServiceImagesService serviceImagesService;

    @Autowired
    private DoPostSmsService doPostSmsService;

    //马上出门
    @Value("${SMS_nurse_atOnceService}")
    private String SMS_nurse_atOnceService;

    //开始服务
    @Value("${SMS_start_server}")
    private String SMS_start_server;

    //结束服务
    @Value("${SMS_end_server}")
    private String SMS_end_server;

    //结束服务多次
    @Value("${SMS_end_many_server}")
    private String SMS_end_many_server;

    //结束服务通知客服
    @Value("${SMS_notice_order_over}")
    private String SMS_notice_order_over;

    @ResponseBody
    @RequestMapping(name = "完成服务", path = "/fulfilService.json")
    public byte[] fulfilService(HttpServletRequest req, HttpServletResponse resp, String osId, String authCode,
            String orderId, String orderGoodsId, User user) {
        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.fulfilService.json,authCode=" + authCode + " orderId=" + orderId
                        + " orderGoodsId=" + orderGoodsId + " token=" + token);
            }
            // 查空
            if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(osId)
                    || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getPhone())
            /*|| StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            } /*
              if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
              return JSONUtil.toJSONResult(0, "token验证失败", null);
              }*/

            Order order = new Order();
            order.setAcceptUserId(user.getId());
            order.setId(orderId);
            order.setSchedule(3);
            order.setStatus(1);
            order = orderService.load(order);
            if (order == null) {
                return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
            }

            String rs = orderService.fulfilService(osId, orderId, orderGoodsId, user);
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.fulfilService.json,rs=" + rs);
            }
            //			查询订单详情-
            Order re_order = new Order();
            re_order.setId(orderId);
            re_order.setAcceptUserId(user.getId());
            re_order = orderService.nurseOrderDetails(re_order);

            ServiceImages serviceImages = new ServiceImages();
            serviceImages.setSourceId(re_order.getId());
            List<ServiceImages> serviceImagesLists = serviceImagesService.list(serviceImages);
            if (serviceImagesLists != null) {
                re_order.setServiceOrderImages(serviceImagesLists);
            }

            int a = 1;
            String msg = "操作成功！确认订单完成";
            if (!rs.equals("1")) {
                a = 0;
                msg = "完成失败，请刷新重试!";
            }
            Map<String, Object> map = orderService.getSmsMessage(orderId);
            //判断是不是多次服务
            int totalNumber = Integer.parseInt(map.get("totalNumber").toString());
            int successNumber = Integer.parseInt(map.get("successNumber").toString());
            if (totalNumber > 1 && totalNumber != successNumber) {
                doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_end_many_server,
                        "{\"service_name\":\"" + map.get("goodsName").toString() + "\",\"name\":\""
                                + map.get("nurseName").toString() + "\",\"number\":\""
                                + map.get("successNumber").toString() + "\"}");
            }
            if (totalNumber == successNumber) {
                doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_end_server, "{\"service_name\":\""
                        + map.get("goodsName").toString() + "\",\"name\":\"" + map.get("nurseName").toString() + "\"}");
                //通知客服
                doPostSmsService.sendSms("13581912414", SMS_notice_order_over,
                        "{\"order_no\":\"" + map.get("order_no").toString() + "\",\"service_name\":\""
                                + map.get("goodsName").toString() + "\"}");
            }
            return JSONUtil.toJSONResult(a, msg, re_order);

        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseOrder.fulfilService.json,authCode=" + authCode + " orderId=" + orderId
                    + " orderGoodsId=" + orderGoodsId, e);
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(name = "护士开始服务", path = "/startService.json")
    public byte[] startService(HttpServletRequest req, HttpServletResponse resp, String osId, String authCode,
            String orderId, String orderGoodsId, User user) {

        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId
                        + " orderGoodsId=" + orderGoodsId + " token=" + token);
            }
            // 查空
            if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(osId)
                    || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getPhone())
            /*|| StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            } /*
              if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
              return JSONUtil.toJSONResult(0, "token验证失败", null);
              }*/

            Order order = new Order();
            order.setAcceptUserId(user.getId());
            order.setId(orderId);
            order.setStatus(1);
            order = orderService.load(order);
            if (order == null) {
                return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
            }

            String rs = orderService.startService(orderId, osId, user);

            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.startService.json,rs=" + rs);
            }
            //			查询订单详情-
            Order re_order = new Order();
            re_order.setId(orderId);
            re_order.setAcceptUserId(user.getId());
            re_order = orderService.nurseOrderDetails(re_order);

            ServiceImages serviceImages = new ServiceImages();
            serviceImages.setSourceId(re_order.getId());
            serviceImages.setType(2);
            List<ServiceImages> serviceImagesLists = serviceImagesService.list(serviceImages);
            if (serviceImagesLists != null) {
                re_order.setServiceOrderImages(serviceImagesLists);
            }
            int a = 1;
            String msg = "服务状态更新成功！";
            if (!rs.equals("1")) {
                a = 0;
                msg = "订单执行失败，请刷新重试!";
            }
            //给用户发送短信
            Map<String, Object> map = orderService.getSmsMessage(orderId);
            doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_start_server, "{\"service_name\":\""
                    + map.get("goodsName").toString() + "\",\"name\":\"" + map.get("nurseName").toString() + "\"}");
            return JSONUtil.toJSONResult(a, msg, re_order);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId
                    + " orderGoodsId=" + orderGoodsId, e);
        }

        return null;
    }

    /**
     * 开始出发-马上上门
     * @param hs
     * @param req
     * @param resp
     * @param authCode
     * @param user
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "护士马上出门", path = "/atOnceService.json")
    public byte[] atOnceService(HttpServletRequest req, HttpServletResponse resp, String authCode, User user,
            String orderId, String osId) {

        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.atOnceService.json,authCode=" + authCode + " user=" + user.getId()
                        + " user=" + user.getPhone() + " token=" + token);
            }

            // 查空
            if (StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getPhone())
                    || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getType().toString())
                    || StringUtils.isEmpty(authCode) || StringUtils.isEmpty(orderId)/*
                                                                                    || StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*
            if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/

            //			查询订单详情-
            Order order = new Order();
            order.setId(orderId);
            order.setAcceptUserId(user.getId());
            //			order.setSchedule(2);
            order.setStatus(1);
            order = orderService.load(order);
            if (order == null) {
                return JSONUtil.toJSONResult(0, "查询详情失败", null);
            }
            com.jinpaihushi.jphs.order.model.OrderService os = new com.jinpaihushi.jphs.order.model.OrderService();
            os.setId(osId);
            os.setOrderId(orderId);
            os.setSchedule(0);
            os.setStatus(1);
            os = orderServiceService.load(os);
            if (os == null) {
                return JSONUtil.toJSONResult(0, "查询详情失败", null);
            }
            com.jinpaihushi.jphs.order.model.OrderService os_up = new com.jinpaihushi.jphs.order.model.OrderService();
            os_up.setId(os.getId());
            os_up.setSetoutTime(new Date());
            boolean b = orderServiceService.update(os_up);

            //			查询订单详情-
            Order re_order = new Order();
            re_order.setId(orderId);
            re_order.setAcceptUserId(user.getId());
            re_order = orderService.nurseOrderDetails(re_order);

            ServiceImages serviceImages = new ServiceImages();
            serviceImages.setSourceId(re_order.getId());
            serviceImages.setType(2);
            List<ServiceImages> serviceImagesLists = serviceImagesService.list(serviceImages);
            if (serviceImagesLists != null) {
                re_order.setServiceOrderImages(serviceImagesLists);
            }

            if (b) {
                Map<String, Object> map = orderService.getSmsMessage(orderId);
                doPostSmsService.sendSms(map.get("userPhone").toString(), SMS_nurse_atOnceService,
                        "{\"name\":\"" + map.get("nurseName").toString() + "\",\"phone\":\""
                                + map.get("nursePhone").toString() + "\"}");

                return JSONUtil.toJSONResult(1, "操作成功！请准时到达服务地点，服务开始前请点击“开始服务”更新服务状态哦~", re_order);
            }
            else {
                return JSONUtil.toJSONResult(0, "更新状态失败，请刷新重试", re_order);
            }
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseOrder.atOnceService.json,authCode=" + authCode + " user=" + user.getId() + " user="
                    + user.getPhone(), e);
        }

        return null;
    }

    /**
     * 订单详情
     * @param hs
     * @param req
     * @param resp
     * @param authCode
     * @param user
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "订单详情", path = "/orderDetails")
    public byte[] orderDetails(HttpServletRequest req, HttpServletResponse resp, String authCode, User user,
            String orderId) {

        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.orderDetails.json,authCode=" + authCode + " user=" + user.getId()
                        + " user=" + user.getPhone() + " token=" + token);
            }

            // 查空
            if (StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getPhone())
                    || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getType().toString())
                    || StringUtils.isEmpty(authCode) || StringUtils.isEmpty(orderId)/*
                                                                                    || StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            //	查询订单详情-
            Order order = new Order();
            order.setId(orderId);
            order.setAcceptUserId(user.getId());
            order = orderService.nurseOrderDetails(order);
            if (order == null) {
                Order orders = new Order();
                orders.setId(orderId);
                order = orderService.nurseOrderDetails(orders);
                if (order == null) {
                    return JSONUtil.toJSONResult(0, "查询详情失败", null);
                }
            }
            ServiceImages serviceImages = new ServiceImages();
            serviceImages.setSourceId(order.getId());
            serviceImages.setType(2);
            List<ServiceImages> serviceImagesLists = serviceImagesService.list(serviceImages);
            if (serviceImagesLists != null) {
                order.setServiceOrderImages(serviceImagesLists);
            }

            return JSONUtil.toJSONResult(1, "查询成功", order);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseOrder.orderDetails.json,authCode=" + authCode + " user=" + user.getId() + " user="
                    + user.getPhone(), e);
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(name = "订单列表", path = "/list.json")
    public byte[] orderList(HttpServletRequest req, HttpServletResponse resp, Integer schedule, String authCode,
            User user, Integer p, Integer n) {
        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user="
                        + user.getPhone());
            }
            // 查空
            if (StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getPhone())
                    || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getType().toString())
                    || StringUtils.isEmpty(authCode)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            /*Order order = new Order();
            order.setSchedule(schedule);
            order.setAcceptUserId(user.getId());
            order.setStatus(1);*/
            Map<String, Object> map_o = new HashMap<String, Object>();
            map_o.put("userId", user.getId());
            map_o.put("schedule", schedule);
            PageHelper.startPage(p, n);
            List<Map<String, Object>> re_map = orderService.nurseOrderList(map_o);
            PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(re_map);

            return JSONUtil.toJSONResult(1, "成功", page);

        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error(
                    "nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),
                    e);
        }

        return null;

    }

    /**
     * 护士抢单
     * 
     * @param hs
     * @param req
     * @param resp
     * @param authCode			//	验证
     * @param orderId			//	订单ID
     * @param orderGoodsId		//	商品ID
     * @param user				//	护士信息
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "护士抢单", path = "/grab.json")
    public byte[] orderGrab(HttpServletRequest req, HttpServletResponse resp, String authCode, String orderId,
            String orderGoodsId, User user) {

        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.grab.json,user" + user.getId() + " user=" + user.getPhone()
                        + " authCode=" + authCode + " user.getType()=" + user.getType() + " token" + token);
            }

            // 查空
            if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(user.getId())
                    || StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getType().toString()) || StringUtils.isEmpty(orderGoodsId)
                    || StringUtils.isEmpty(orderId)/*
                                                   ||StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            String rs = orderService.orderGrab(orderId, orderGoodsId, user);
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.grab.json,rs=" + rs);
            }
            int a = 1;
            String msg = "抢单成功";
            if (rs.equals("0")) {
                a = 0;
                msg = "查询用户信息为空！";
            }
            else if (rs.equals("2")) {
                a = 0;
                msg = "字符串转整形失败";
            }
            else if (rs.equals("3")) {
                a = 0;
                msg = "用户不是护士！";
            }
            else if (rs.equals("4")) {
                a = 0;
                msg = "未申请护士资格！";
            }
            else if (rs.equals("5")) {
                a = 0;
                msg = "护士已被封号，请联系客服确认！";
            }
            else if (rs.equals("6")) {
                a = 0;
                msg = "护士未通过审核！";
            }
            else if (rs.equals("7")) {
                a = 0;
                msg = "您的认证服务资质接取不了专业技能范围外的订单~如有任何疑问可拨打客服电话进行反馈咨询！";
            }
            else if (rs.equals("8")) {
                a = 0;
                msg = "该订单已被抢走！";
            }
            else if (rs.equals("9")) {
                a = 0;
                msg = "接单失败！";
            }
            else if (rs.equals("10")) {
                a = 0;
                msg = "订单异常(订单地址不完整)！";
            }
            else if (rs.equals("11")) {
                a = 0;
                msg = "订单异常(订单地址不完整)！";
            }
            else if (rs.equals("12")) {
                a = 0;
                msg = "您还未设置出门地址，请完善出门地址！";
            }
            else if (rs.equals("13")) {
                a = 0;
                msg = "订单异常(地址不完整-省)！";
            }
            else if (rs.equals("14")) {
                a = 0;
                msg = "订单异常(地址不完整-市)！";
            }
            else if (rs.equals("15")) {
                a = 0;
                msg = "订单异常(地址不完整-省,市)！";
            }
            else if (rs.equals("16")) {
                a = 0;
                msg = "和您的出门地址不匹配，请选择就近订单！";
            }else if (rs.equals("17")) {
                a = 0;
                msg = "该时间段您的预约已满。";
            }else if (rs.equals("17")) {
                a = 0;
                msg = "您还未设置空闲时间。";
            }

            return JSONUtil.toJSONResult(a, msg, null);
        }
        catch (Exception e) {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseOrder.grab.json,user" + user.getId() + " user=" + user.getPhone()
                        + " authCode=" + authCode + " e=", e);
            }
        }
        return null;
    }

}
