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

import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/getUserVoucher.json", name = "获取用户可用的优惠券")
    @ResponseBody
    public byte[] getUserVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String goodsId, String pricePartId, String nurseId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("voucher.getUserVoucher.json,userId=" + userId + " goodsId=" + goodsId
                        + " pricePartId=" + pricePartId);
            }
            if (StringUtils.isEmpty(pricePartId) || StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(userId)) {
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
            List<Map<String, Object>> list = voucherService.getUservoucher(pricePartId, goodsId, userId, nurseId);
            if (list == null) {
                return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("voucher.getUserVoucher.json,userId=" + userId + " goodsId=" + goodsId + " pricePartId="
                    + pricePartId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserAllVoucher.json", name = "我的的优惠券")
    @ResponseBody
    public byte[] getUserAllVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer type, Integer status) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "voucher.getUserAllVoucher.json,userId=" + userId + " type=" + type + " status=" + status);
            }
            if (StringUtils.isEmpty(userId)) {
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
            List<Map<String, Object>> list = voucherService.getUserAllvoucher(userId, type, status);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error(
                    "voucher.getUserAllVoucher.json,userId=" + userId + " type=" + type + " status=" + status, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "计算使用优惠券之后的商品价格", path = "/getGoodsPrice.json")
    public byte[] getGoodsPrice(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String voucherUseId,
            String pricePartId, String userId, String nurseId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("计算使用优惠券之后的商品价格 ---voucher.getGoodsPrice.json,voucherUseId=" + voucherUseId
                        + " pricePartId=" + pricePartId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(voucherUseId) || StringUtils.isEmpty(pricePartId) || StringUtils.isEmpty(userId)) {
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
            // 判断该用户是否拥有此优惠券
            boolean flag = voucherService.isHaveVoucher(voucherUseId, userId);
            if (!flag) {
                return JSONUtil.toJSONResult(2, "该用户没有改优惠券", null);
            }
            // 计算使用优惠券之后的价格
            Double price = voucherService.getGoodsPrice(voucherUseId, pricePartId, nurseId);
            return JSONUtil.toJSONResult(1, "操作成功！", price);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("计算使用优惠券之后的商品价格 ---voucher.getGoodsPrice.json,voucherUseId=" + voucherUseId
                    + " pricePartId=" + pricePartId + " userId=" + userId, e);
        }
        return null;
    }
}
