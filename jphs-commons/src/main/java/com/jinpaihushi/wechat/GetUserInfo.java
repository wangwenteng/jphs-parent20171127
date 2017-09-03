package com.jinpaihushi.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.wechat.utils.WechatHttp;

import net.sf.json.JSONObject;

public class GetUserInfo {

	/**
	 * 获取到微信用户的信息
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static String getWX(String code) {
		// 读取配置文件类
		Properties pps = new Properties();
		InputStream in  = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
		try {
			pps.load(in);
		} catch (IOException e) {
		}
		String APPID = pps.getProperty("APP_ID_TEST");
		String secret = pps.getProperty("APP_SECRET_WCHAT_TEST");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + secret + "&code="
				+ code + "&grant_type=authorization_code";
		// 返回参数
		// String ret="无"; //access_token + refresh_token + openid
		String jsonResult = WechatHttp.get(url, "UTF-8");// 得到JSON字符串

		System.out.println("---------------------------------------------");
		System.out.println("jsonResult---" + jsonResult);
		System.out.println("---------------------------------------------");

		JSONObject object = JSONObject.fromObject(jsonResult);
		if (object.containsKey("errmsg")) {
			return object.toString();
		}

		String access_token = object.getString("access_token");

		String refresh_token = object.getString("refresh_token");
		String openid = object.getString("openid");
		// 获取用户信息
		String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
				+ "&lang=zh_CN";
		String userResult = WechatHttp.get(userUrl, "UTF-8");
		JSONObject userObject = JSONObject.fromObject(userResult);

		if (userObject.containsKey("errmsg")) {
			// 刷新token
			String update = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APPID
					+ "&grant_type=refresh_token&refresh_token=" + refresh_token;
			String updateResult = WechatHttp.get(update, "UTF-8"); // 执行刷新
			JSONObject updateObject = JSONObject.fromObject(updateResult);

			// 刷新后重新赋值
			access_token = updateObject.getString("access_token");
			refresh_token = updateObject.getString("refresh_token");
			openid = updateObject.getString("openid");

			// 重新提交一次
			userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
					+ "&lang=zh_CN";
			userResult = WechatHttp.get(userUrl, "UTF-8");
		}
		return userResult;
	}

}
