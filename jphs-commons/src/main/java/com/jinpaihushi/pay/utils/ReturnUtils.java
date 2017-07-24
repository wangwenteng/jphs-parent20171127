package com.jinpaihushi.pay.utils;

import java.io.UnsupportedEncodingException;

import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

public class ReturnUtils {

	/**
	 * 支付-结果返回
	 * @param resultcode
	 * @param msg
	 * @param result
	 * @return
	 */
	public static byte[] jsonReturnToString(String resultcode,String msg,String result){
		JSONObject json = new JSONObject();
		json.put("resultcode", resultcode);
		json.put("msg", msg);
		json.put("result", result);
		
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug(json);
		}
		try {
			byte[] bs = json.toString().getBytes("UTF-8");
			return bs;
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
	
}
