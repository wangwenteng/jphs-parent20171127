package com.jinpaihushi.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JSONUtil {
	private static JSONObject jsonObject;
	static {
		if (jsonObject == null) {
			jsonObject = new JSONObject();
		}
	}

	public JSONUtil() {

	}

	public static byte[] toJSONResult(int res, String msg, Object o)
			throws IOException {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("resultcode", res); // 返回是否成功 1-成功 0-失败
		m.put("msg", msg); // 返回说明
		m.put("result", o); // 具体返回内容
		
		jsonObject = JSONObject.fromObject(m);
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug(jsonObject);
		}

		byte[] bs = jsonObject.toString().getBytes("UTF-8");
		return bs;
	}
}
