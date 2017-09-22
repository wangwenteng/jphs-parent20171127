package com.jinpaihushi.jphs.family.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
			String userId,String wxNo,String promoCode,String card){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyOrder.ufl.json,userId=" + userId +" wxNo="+  wxNo +" promoCode="+promoCode +
                		" card="+card);
            }
			// 查空
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(promoCode)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            
            
            
    		return JSONUtil.toJSONResult(1, "操作成功！", null);
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("familyOrder.ufl.json,authCode=" + userId +" wxNo="+  wxNo +" promoCode="+promoCode +
            		" card="+card, e);
    	}
		
		
		return null;
	}
	
}
