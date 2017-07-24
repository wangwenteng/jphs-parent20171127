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
import com.jinpaihushi.pay.alipay.AlipaySign;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.ObjectVerification;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping(path = "/createOrder.json", name = "用户下单", method = RequestMethod.POST)
	@ResponseBody
	public byte[] createOrder(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, OrderInfo orderInfo,
			String authCode) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("order.createOrder.json");
			}
			if(!ObjectVerification.verification(orderInfo)){
				return JSONUtil.toJSONResult(0, "请核对参数信息", null);
			}
			Map<String, Object> result = orderService.createOrder(orderInfo);
			if (result == null) {

				return JSONUtil.toJSONResult(0, "操作失败！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
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
			Map<String, Object> query = new HashMap<>();
			query.put("userId", userId);
			query.put("schedule", schedule);
			List<Map<String, Object>> result = orderService.getUserOrder(query);
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("order.getUserOrder.json userId=" + userId + " schedule=" + schedule, e);
		}
		return null;
	}

	@RequestMapping(path = "/getUserOrderDetail.json", name = "订单详情")
	@ResponseBody
	public byte[] getUserOrderDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String orderId) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("order.getUserOrderDetail.json");
			}
			if (StringUtils.isEmpty(orderId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Order result = orderService.getUserOrderDetail(orderId);
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("order.getUserOrderDetail.json", e);
		}
		return null;
	}

	@RequestMapping(path = "/orderPay.json", name = "订单支付")
	@ResponseBody
	public byte[] orderPay(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsName,
			String orderNo, String return_url, Double payParice,Integer type) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("order.getUserOrderDetail.json");
			}
			if (StringUtils.isEmpty(return_url) || StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(goodsName)
					|| payParice == null) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(type==1){
				JSONObject sParaTemp = new JSONObject();
				sParaTemp.put("_input_charset", "utf-8");
				sParaTemp.put("body", goodsName);
				sParaTemp.put("notify_url", "https://s.jinpaihushi.com/alipay/sign.json");
				sParaTemp.put("out_trade_no", orderNo);
				sParaTemp.put("payment_type", "1");
				sParaTemp.put("return_url", return_url);
				sParaTemp.put("show_url", "https://site.jinpaihushi.com");
				sParaTemp.put("subject", goodsName);
				sParaTemp.put("total_fee", payParice);

				byte[] s = AlipaySign.getAlisign(sParaTemp.toString(), "PRIVATE_KEY");
				return s;
			}
		

		} catch (Exception e) {
			Util.failLog.error("order.getUserOrderDetail.json", e);
		}
		return null;
	}
}
