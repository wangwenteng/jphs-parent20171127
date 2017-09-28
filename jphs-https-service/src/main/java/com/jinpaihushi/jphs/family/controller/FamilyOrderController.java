package com.jinpaihushi.jphs.family.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.family.model.FamilyOrder;
import com.jinpaihushi.jphs.family.service.FamilyOrderService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 家庭护士登录判断
 * 家庭护士检查用户购买信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/familyOrder")
public class FamilyOrderController {
	
	@Autowired
	private FamilyOrderService familyOrderService;
	
	@RequestMapping(name ="用户家庭护士",path = "/gufo.json")
	@ResponseBody
	public byte[] getUserFamilyOrder(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyOrder.wechatFamily.json,userId=" + userId );
            }
         // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            FamilyOrder f = new FamilyOrder();
            f.setCreatorId(userId);
            f.setStatus(1);
            List<FamilyOrder> f_l = familyOrderService.list(f);
            if(f_l == null || f_l.size()<1){
            	return JSONUtil.toJSONResult(0, "没有购买家庭护士", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", f_l.get(0));
		}catch (Exception e){
			// 记录日志-fail
            Util.failLog.error("familyOrder.wechatFamily.json,authCode=" + authCode+" userId="+userId , e);
		}
		return null;
	}

	/**
	 * 微信端判断用户是否购买家庭护士记录
	 * @param req
	 * @param resp
	 * @param authCode
	 * @return
	 */
	@RequestMapping(name ="是否购买",path = "/wechatFamily.json")
	@ResponseBody
	public byte[] userWechatFamilyOrder(HttpServletRequest req, HttpServletResponse resp, String authCode,String openId){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyOrder.wechatFamily.json,openId=" + openId );
            }
			// 查空
            if (StringUtils.isEmpty(openId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int result = familyOrderService.userWechatFamilyOrder(openId);
    		return JSONUtil.toJSONResult(1, "操作成功！", result);
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("familyOrder.wechatFamily.json,authCode=" + authCode+" openId="+openId , e);
    	}
		return null;
	}
	

	/**
	 * 判断用户是否购买家庭护士记录
	 * @param req
	 * @param resp
	 * @param authCode
	 * @return
	 */
	@RequestMapping(name ="是否购买",path = "/userIfFamily.json")
	@ResponseBody
	public byte[] userIfFamilyOrder(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyOrder.wechatFamily.json,UserId=" + userId );
            }
			// 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int result = familyOrderService.userIdFamilyOrder(userId);
    		return JSONUtil.toJSONResult(1, "操作成功！", result);
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("familyOrder.wechatFamily.json,authCode=" + authCode+" userId="+userId , e);
    	}
		return null;
	}
	
	/**
	 * 用户输入内容
	 * @param req
	 * @param resp
	 * @param userId
	 * @param authCode
	 * @param wxNo
	 * @param promoCode	验证编号
	 * @param card
	 * @return
	 */
	@RequestMapping(name="获取家庭护士",path="/ufl.json")
	@ResponseBody
	public byte[] userFamilyLogin(HttpServletRequest req, HttpServletResponse resp, String authCode,
			String userId,String wxNo,String promoCode,String card,String name,String fmId,String openId,int payType,String code,int accessMode){
		try{
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
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyOrder.ufl.json,userId=" + userId +" wxNo="+  wxNo +" promoCode="+promoCode +
                		" card="+card);
            }
            if(StringUtils.isEmpty(String.valueOf(accessMode)) && accessMode == 2){
            	if (StringUtils.isEmpty(card) ) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }
            }
			// 查空
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(promoCode) || StringUtils.isEmpty(fmId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            
            byte[] s =familyOrderService.setOrderFamily(userId, ip, name, promoCode, wxNo, fmId, openId, payType,code,card);
            
    		return s;
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("familyOrder.ufl.json,authCode=" + userId +" wxNo="+  wxNo +" promoCode="+promoCode +
            		"  card="+card, e);
    	}
		return null;
	}
	
}
