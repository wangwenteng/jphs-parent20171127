package com.jinpaihushi.jphs.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
import com.jinpaihushi.wechat.GetMenuShare;
import com.jinpaihushi.wechat.GetUserInfo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wechat")
public class WecthController {

	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(name="微信APP内获取用户信息",path = "/getUser.json")
	public byte[] getUserInfo(HttpServletRequest req, HttpServletResponse resp, String authCode,String code,String state) throws IOException{
		 try {
				String ua = ((HttpServletRequest) req).getHeader("user-agent").toLowerCase();
				if (ua.indexOf("micromessenger") <= 0) {// 是微信浏览器
					if (Util.debugLog.isDebugEnabled()) {
		                Util.debugLog.debug("wechat.getUser.json,authCode="+authCode+"-code="+code+"-state="+state+"-不在微信浏览器内");
		            }
					return JSONUtil.toJSONResult(0, "不在微信浏览器内，无法获取用户信息", null);
				}
				if (StringUtils.isEmpty(code)) {
	                return JSONUtil.toJSONResult(0, "参数不能为空", null);
	            }
				String str = GetUserInfo.getWX(code);
				JSONObject json = new JSONObject().fromObject(str);
				json.put("state", state);
				return JSONUtil.toJSONResult(1, "操作成功！", json);
			}  catch (Exception e) {
	            // 记录日志-fail
	            Util.failLog.error("wechat.getUser.json,authCode="+authCode+"-code="+code+"-state="+state, e);
	        }
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name= "" , path = "/getMenuShare.json")
	public byte[] getMenuShare(HttpServletRequest req, HttpServletResponse resp, String authCode){
		try {
			String url_p = req.getHeader("REFERER");
			if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("wechat.getMenuShare.json,authCode="+authCode+"-访问地址="+url_p);
            }
			String str = GetMenuShare.getMenuShare(url_p);
			return JSONUtil.toJSONResult(1, "操作成功！", str);
	 		} catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("wechat.getMenuShare.json,authCode="+authCode, e);
        }
		return null;
	}
	
}
