package com.jinpaihushi.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.wechat.utils.GetProperties;
import com.jinpaihushi.wechat.utils.WechatJStokenUtils;

import net.sf.json.JSONObject;

public class GetMenuShare {

	@SuppressWarnings("static-access")
	public static String getMenuShare(String url_p){
		String WCHAT_ACCESS_JSAPI_TICKET = GetProperties.getWchatUrl("WCHAT_ACCESS_JSAPI_TICKET");
		Map<String, String> ret = WechatJStokenUtils.sign(WCHAT_ACCESS_JSAPI_TICKET, url_p);
		JSONObject json_s = new JSONObject().fromObject(ret);
		// 读取配置文件类
		Properties pps = new Properties();
		InputStream in  = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
		try {
			pps.load(in);
		} catch (IOException e) {
		}
		//	商户平台APPID
		String APPID = pps.getProperty("APP_ID");
		json_s.put("appid", APPID);
		json_s.put("resultcode", 1);
		return json_s.toString();
	}
}
