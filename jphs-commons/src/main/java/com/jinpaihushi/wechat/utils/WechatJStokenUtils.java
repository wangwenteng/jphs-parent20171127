package com.jinpaihushi.wechat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

public class WechatJStokenUtils {
	
	public static void main(String[] args) {
		long hqtime = 1502442186;
		System.out.println(timeStamp());
		Long s = ( timeStamp() - hqtime) / (60);
			System.out.println(s);
	}
	
	@SuppressWarnings("static-access")
	public static boolean getToken(){
		boolean falg = true;
		
		// 读取配置文件类
			Properties pps = new Properties();
			InputStream in  = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
			try {
				pps.load(in);
			} catch (IOException e) {
			}
			//	商户平台APPID
			String APPID = pps.getProperty("APP_ID_TEST");
			String APPSECRET =pps.getProperty("APP_SECRET_WCHAT_TEST");
			String token_str = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid=" + APPID + "&secret=" +APPSECRET, "");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("token_str=="+token_str);
			}
			try {
				JSONObject json_token = new JSONObject().fromObject(token_str);
				if(json_token.containsKey("access_token")){
					String token = json_token.getString("access_token");
					GetProperties.updateProperties("WCHAT_ACCESS_TOKEN", token);
					GetProperties.updateProperties("WCHAT_ACCESS_TOKEN_TIME", String.valueOf(timeStamp()));
				}else if(json_token.containsKey("errcode")){
					// {"errcode":40013,"errmsg":"invalid appid"}
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("errcode:"+json_token.getString("errcode"));
						Util.debugLog.debug("errmsg:"+json_token.getString("errmsg"));
					}
					
					falg = false;
				}
			} catch (Exception e1) {
				falg = false;
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("异常--",e1);
				}
			}
		return falg;
	}
	@SuppressWarnings("static-access")
	public static boolean getTicket(){
		boolean falg = true;
		try {
			String token = GetProperties.getWchatUrl("WCHAT_ACCESS_TOKEN");
			String ticket_str = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "access_token=" + token + "&type=jsapi", "");
			System.out.println("ticket_str------------------"+ticket_str);	
			JSONObject json_ticket = new JSONObject().fromObject(ticket_str);
				if(json_ticket.containsKey("ticket")){
					String ticket = json_ticket.getString("ticket");
					GetProperties.updateProperties("WCHAT_ACCESS_JSAPI_TICKET", ticket);
					GetProperties.updateProperties("WCHAT_ACCESS_JSAPI_TICKET_TIME", String.valueOf(timeStamp()));
				}else if(json_ticket.containsKey("errcode")){
					// {"errcode":40013,"errmsg":"invalid appid"}
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("errcode:"+json_ticket.getString("errcode"));
						Util.debugLog.debug("errmsg:"+json_ticket.getString("errmsg"));
					}
					falg = false;
				}
			} catch (Exception e1) {
				falg = false;
			}
		return falg;
	}
	
	
	 /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
	   
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static long timeStamp(){  
        long time = System.currentTimeMillis()/1000;  
        return time;  
    }  
	
	 public static Map<String, String> sign(String jsapi_ticket, String url) {
	        Map<String, String> ret = new HashMap<String, String>();
	        String nonce_str = create_nonce_str();
	        String timestamp = create_timestamp();
	        String string1;
	        String signature = "";

	        //注意这里参数名必须全部小写，且必须有序
	        string1 = "jsapi_ticket=" + jsapi_ticket +
	                  "&noncestr=" + nonce_str +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;
	        if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("string1:"+string1);
			}

	        try
	        {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        catch (UnsupportedEncodingException e)
	        {
	            e.printStackTrace();
	        }

	        ret.put("url", url);
	        ret.put("jsapi_ticket", jsapi_ticket);
	        ret.put("nonceStr", nonce_str);
	        ret.put("timestamp", timestamp);
	        ret.put("signature", signature);

	        return ret;
	    }

	    private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash)
	        {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
	    }

	    private static String create_nonce_str() {
	        return UUID.randomUUID().toString();
	    }

	    private static String create_timestamp() {
	        return Long.toString(System.currentTimeMillis() / 1000);
	    }
	
}
