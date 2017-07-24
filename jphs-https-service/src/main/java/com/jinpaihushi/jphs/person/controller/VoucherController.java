package com.jinpaihushi.jphs.person.controller;

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

import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
	@Autowired
	private VoucherService voucherService;

	@RequestMapping(path = "/getUserVoucher.json", name = "获取用户可用的优惠券")
	@ResponseBody
	public byte[] getUserVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
			String goodsId, String pricePartId) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("voucher.getUserVoucher.json,userId=" + userId + " goodsId=" + goodsId
						+ " pricePartId=" + pricePartId);
			}
			if (StringUtils.isEmpty(pricePartId) || StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Voucher> list = voucherService.getUservoucher(pricePartId, goodsId, userId);
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("voucher.getUserVoucher.json,userId=" + userId + " goodsId=" + goodsId + " pricePartId="
					+ pricePartId, e);
		}
		return null;
	}

	@RequestMapping(path = "/getUserAllVoucher.json", name = "我的的优惠券")
	@ResponseBody
	public byte[] getUserAllVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,Integer type) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("voucher.getUserVoucher.json,userId=" + userId+" type="+type );
			}
			if ( StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Map<String,Object>> list = voucherService.getUserAllvoucher( userId,type);
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("voucher.getUserVoucher.json,userId=" + userId +" type="+type, e);
		}
		return null;
	}
}
