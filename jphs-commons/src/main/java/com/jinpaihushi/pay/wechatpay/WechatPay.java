package com.jinpaihushi.pay.wechatpay;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.jinpaihushi.pay.utils.ReturnUtils;
import com.jinpaihushi.pay.wechatpay.utils.GetWxOrderno;
import com.jinpaihushi.pay.wechatpay.utils.HttpUtil;
import com.jinpaihushi.pay.wechatpay.utils.PayCommonUtil;
import com.jinpaihushi.pay.wechatpay.utils.RequestHandler;
import com.jinpaihushi.pay.wechatpay.utils.TenpayUtil;
import com.jinpaihushi.pay.wechatpay.utils.XMLUtil;
import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

public class WechatPay {

	public static void main(String[] args) {
		/*
		 * String total_fee ="9950"; String payPrice = "0.01"; double
		 * total_fee_wc = Double.parseDouble(total_fee)/100; double
		 * total_fee_ord = Double.parseDouble(payPrice);
		 * System.out.println("total_fee_wc---"+total_fee_wc);
		 * System.out.println("total_fee_ord---"+total_fee_ord);
		 * System.out.println(total_fee_wc == total_fee_ord);
		 * System.out.println((new Double(payPrice)).in+tValue());
		 */
		try {
			JSONObject json = new JSONObject();
			json.put("out_trade_no", "JP20193699556");
			json.put("body", "居家康复");
			json.put("price", "1");

			byte[] s = weixin_webpay(json.toString());
			String t = new String(s);
			System.out.println(JSONObject.fromObject(t).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 微信web支付
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static byte[] weixin_webpay(String content) throws Exception {

		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("微信支付参数：content=" + content);
		}

		if (content == null || "".equals(content)) {
			return ReturnUtils.jsonReturnToString("0", "content不合法", "");
		}

		String order_price = ""; // 支付金额
		String body = ""; // 商品名称
		String out_trade_no = ""; // 订单号
		String CREATE_IP = "";
		try {
			JSONObject contentjson = JSONObject.fromObject(content);
			if (!contentjson.containsKey("price") || StringUtils.isEmpty(contentjson.getString("price"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			order_price = contentjson.getString("price");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：price=" + order_price);
			}
			if (!contentjson.containsKey("body") || StringUtils.isEmpty(contentjson.getString("body"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			body = contentjson.getString("body");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：body=" + body);
			}
			if (!contentjson.containsKey("out_trade_no")
					|| StringUtils.isEmpty(contentjson.getString("out_trade_no"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			out_trade_no = contentjson.getString("out_trade_no");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：out_trade_no=" + out_trade_no);
			}
			if (!contentjson.containsKey("CREATE_IP") || StringUtils.isEmpty(contentjson.getString("CREATE_IP"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			CREATE_IP = contentjson.getString("CREATE_IP");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：CREATE_IP=" + CREATE_IP);
			}
		} catch (Exception e1) {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付异常：e1=" + e1);
			}
			return ReturnUtils.jsonReturnToString("0", "content不合法", "");
		}

		String APP_ID = "";
		String MCH_ID = "";
		String API_KEY = "";
		String NOTIFY_URL = "";
		String UFDODER_URL = "";
		String nonce_str = "";
		String trade_type = "MWEB";// web支付
		try {
			// 读取配置文件类
			Properties pps = new Properties();
			InputStream in = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
			pps.load(in);
			// 商户平台APPID
			APP_ID = pps.getProperty("APP_ID");// appid
			// 公众号key秘钥
			// String appsecret = PayConfigUtil.APP_SECRET; // appsecret
			// 商户号
			MCH_ID = pps.getProperty("MCH_ID"); //
			// 商户key秘钥
			API_KEY = pps.getProperty("API_KEY"); // key
			// 生成地址 ip
			// CREATE_IP = pps.getProperty("CREATE_IP");
			// 异步回调地址
			NOTIFY_URL = pps.getProperty("NOTIFY_URL");
			// 微信生成二维码地址
			UFDODER_URL = pps.getProperty("UFDODER_URL");

			String currTime = PayCommonUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			String strRandom = PayCommonUtil.buildRandom(4) + "";
			nonce_str = strTime + strRandom;

			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，获取账户配置信息：APP_ID=" + APP_ID + ",MCH_ID=" + MCH_ID + ",API_KEY=" + API_KEY
						+ ",CREATE_IP=" + CREATE_IP + ",CREATE_IP=" + CREATE_IP + ",NOTIFY_URL=" + NOTIFY_URL
						+ ",UFDODER_URL=" + UFDODER_URL + ",nonce_str=" + nonce_str);
			}

			if (APP_ID == null || "".equals(APP_ID) || MCH_ID == null || "".equals(MCH_ID) || API_KEY == null
					|| "".equals(API_KEY) || CREATE_IP == null || NOTIFY_URL == null || "".equals(NOTIFY_URL)
					|| UFDODER_URL == null || "".equals(UFDODER_URL)) {
				return ReturnUtils.jsonReturnToString("0", "获取配置文件信息失败！", "");
			}
		} catch (Exception e) {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，获取账户配置信息：e=" + e);
			}
			return ReturnUtils.jsonReturnToString("0", "获取账户配置信息失败！", "");
		}

		// String return_code = (String) map.get("return_code");
		// String prepay_id = (String) map.get("prepay_id");
		String signRe = "";
		try {
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			packageParams.put("appid", APP_ID);
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", order_price);
			packageParams.put("spbill_create_ip", CREATE_IP);
			packageParams.put("notify_url", NOTIFY_URL);
			packageParams.put("trade_type", trade_type);
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, API_KEY);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-sign=" + sign);
			}
			packageParams.put("sign", sign);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-packageParams=" + JSONObject.fromObject(packageParams));
			}
			String requestXML = PayCommonUtil.getRequestXml(packageParams);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-requestXML=" + requestXML);
			}
			String resXml = HttpUtil.postData(UFDODER_URL, requestXML);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-resXml=" + resXml);
			}
			Map map = XMLUtil.doXMLParse(resXml);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-map=" + JSONObject.fromObject(map));
			}
			String return_code = (String) map.get("return_code");
			String result_code = (String) map.get("result_code");
			JSONObject jsonSign = new JSONObject();
			if (return_code != null && return_code != "" && !"".equals(return_code) && result_code != null
					&& result_code != "" && !"".equals(result_code) && result_code.equals("SUCCESS")
					&& return_code.equals("SUCCESS")) {
				jsonSign.put("url", (String) map.get("mweb_url"));
				signRe = jsonSign.toString();
			} else {
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付，生成url失败");
				}
				return ReturnUtils.jsonReturnToString("0", "生成url失败！", "");
			}
		} catch (Exception e) {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，生成url失败-异常：e=" + e);
			}
			return ReturnUtils.jsonReturnToString("0", "生成url失败！", "");
		}
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("微信支付，获取支付url：signRe=" + signRe);
		}
		return ReturnUtils.jsonReturnToString("1", "成功", signRe);
	}

	/**
	 * 微信-APP支付
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access" })
	public static byte[] weixin_app_pay(String content) throws Exception {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：content=" + content);
			}

			if (content == null || "".equals(content)) {
				return ReturnUtils.jsonReturnToString("0", "content不合法", "");
			}

			String order_price = ""; // 支付金额
			String body = ""; // 商品名称
			String out_trade_no = ""; // 订单号
			String CREATE_IP = ""; // 生成订单的ip地址
			try {
				JSONObject contentjson = JSONObject.fromObject(content);
				if (!contentjson.containsKey("price") || StringUtils.isEmpty(contentjson.getString("price"))) {
					return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
				}
				order_price = contentjson.getString("price");
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付参数：price=" + order_price);
				}
				if (!contentjson.containsKey("body") || StringUtils.isEmpty(contentjson.getString("body"))) {
					return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
				}
				body = contentjson.getString("body");
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付参数：body=" + body);
				}
				if (!contentjson.containsKey("out_trade_no")
						|| StringUtils.isEmpty(contentjson.getString("out_trade_no"))) {
					return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
				}
				out_trade_no = contentjson.getString("out_trade_no");
				if (!contentjson.containsKey("CREATE_IP") || StringUtils.isEmpty(contentjson.getString("CREATE_IP"))) {
					return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
				}
				CREATE_IP = contentjson.getString("CREATE_IP");
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付参数：out_trade_no=" + out_trade_no);
				}
			} catch (Exception e1) {
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付异常：e1=" + e1);
				}
				return ReturnUtils.jsonReturnToString("0", "content不合法", "");
			}

			String APP_ID = "";
			String MCH_ID = "";
			String API_KEY = "";
			String NOTIFY_URL = "";
			String APP_SECRET = "";
			String UFDODER_URL = "";
			String nonce_str = "";
			String trade_type = "APP";// 请求二维码url
			try {
				// 读取配置文件类
				Properties pps = new Properties();
				InputStream in = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
				pps.load(in);
				// 商户平台APPID
				APP_ID = pps.getProperty("APP_ID_APP");// appid
				// 公众号key秘钥
				// String appsecret = PayConfigUtil.APP_SECRET; // appsecret
				// 商户号
				MCH_ID = pps.getProperty("MCH_ID_APP"); //
				// 商户key秘钥
				API_KEY = pps.getProperty("API_KEY_APP"); // key
				// APP_SECRET
				APP_SECRET = pps.getProperty("APP_SECRET"); // key
				// 异步回调地址
				NOTIFY_URL = pps.getProperty("NOTIFY_URL_APP");
				// 微信
				UFDODER_URL = pps.getProperty("UFDODER_URL_APP");

				String currTime = PayCommonUtil.getCurrTime();
				String strTime = currTime.substring(8, currTime.length());
				String strRandom = PayCommonUtil.buildRandom(4) + "";
				nonce_str = strTime + strRandom;

				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付，获取账户配置信息：APP_ID=" + APP_ID + ",MCH_ID=" + MCH_ID + ",API_KEY=" + API_KEY
							+ ",CREATE_IP=" + CREATE_IP + ",CREATE_IP=" + CREATE_IP + ",NOTIFY_URL=" + NOTIFY_URL
							+ ",UFDODER_URL=" + UFDODER_URL + ",nonce_str=" + nonce_str);
				}

				if (APP_ID == null || "".equals(APP_ID) || MCH_ID == null || "".equals(MCH_ID) || API_KEY == null
						|| "".equals(API_KEY) || CREATE_IP == null || "".equals(CREATE_IP) || NOTIFY_URL == null
						|| "".equals(NOTIFY_URL) || UFDODER_URL == null || "".equals(UFDODER_URL)) {
					return ReturnUtils.jsonReturnToString("0", "获取配置文件信息失败！", "");
				}
			} catch (Exception e) {
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付，获取账户配置信息：e=" + e);
				}
				return ReturnUtils.jsonReturnToString("0", "获取账户配置信息失败！", "");
			}

			try {
				SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
				packageParams.put("appid", APP_ID);
				packageParams.put("mch_id", MCH_ID);
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("body", body);
				packageParams.put("out_trade_no", out_trade_no);
				packageParams.put("total_fee", order_price);
				packageParams.put("spbill_create_ip", CREATE_IP);
				packageParams.put("notify_url", NOTIFY_URL);
				packageParams.put("trade_type", trade_type);
				RequestHandler reqHandler = new RequestHandler(null, null);
				reqHandler.init(APP_ID, APP_SECRET, API_KEY);
				String sign = reqHandler.createSign(packageParams);
				packageParams.put("sign", sign);
				String allParameters = "";
				try {
					allParameters = reqHandler.genPackage(packageParams);
				} catch (Exception e) {
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("微信支付，生成签名异常：e=" + e + ";allParameters=" + allParameters);
					}
					return ReturnUtils.jsonReturnToString("0", "生成签名失败！", "");
				}
				String prepay_id = "";// 预支付订单返回结果
				try {
					prepay_id = new GetWxOrderno().getPayNo(UFDODER_URL, XMLUtil.parseXML(packageParams));
					if (prepay_id.equals("")) {
						if (Util.debugLog.isDebugEnabled()) {
							Util.debugLog.debug("统一支付接口获取预支付订单出错：prepay_id=" + prepay_id);
						}
						return ReturnUtils.jsonReturnToString("0", "统一支付接口获取预支付订单出错1！", "");
					}
				} catch (Exception e1) {
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("统一支付接口获取预支付订单出错：prepay_id=" + prepay_id + ";e1" + e1);
					}
					return ReturnUtils.jsonReturnToString("0", "统一支付接口获取预支付订单出错2！", "");
				}
				// 获取到prepayid后对以下字段进行签名最终发送给app
				SortedMap<Object, Object> finalpackage = new TreeMap<Object, Object>();
				String timestamp = TenpayUtil.getTimeStamp();
				finalpackage.put("appid", APP_ID);
				finalpackage.put("timestamp", timestamp);
				finalpackage.put("noncestr", nonce_str);
				finalpackage.put("partnerid", MCH_ID);
				finalpackage.put("package", "Sign=WXPay");
				finalpackage.put("prepayid", prepay_id);
				String finalsign = reqHandler.createSign(finalpackage);
				Map<String, String> map = new HashMap<String, String>();// 使用Map集合
				map.put("appId", APP_ID);
				map.put("timeStamp", timestamp);
				map.put("nonceStr", nonce_str);
				map.put("package", "Sign=WXPay");
				map.put("signType", "MD5");
				map.put("total", out_trade_no);
				map.put("sign", finalsign);
				map.put("prepayid", prepay_id);
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("生成签名结果;map=" + new JSONObject().fromObject(map));
				}
				return ReturnUtils.jsonReturnToString("1", "完成！", new JSONObject().fromObject(map).toString());
			} catch (Exception e) {
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付，生成url失败-异常：e=" + e);
				}
				return ReturnUtils.jsonReturnToString("0", "生成url失败！", "");
			}
		} catch (Exception e5) {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，生成url失败-异常：e=5" + e5);
			}
		}
		return ReturnUtils.jsonReturnToString("0", "异常失败", "");
	}

	/**
	 * 微信扫码支付
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static byte[] weixin_pay(String content) throws Exception {

		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("微信支付参数：content=" + content);
		}

		if (content == null || "".equals(content)) {
			return ReturnUtils.jsonReturnToString("0", "content不合法", "");
		}

		String order_price = ""; // 支付金额
		String body = ""; // 商品名称
		String out_trade_no = ""; // 订单号
		String CREATE_IP = "";
		try {
			JSONObject contentjson = JSONObject.fromObject(content);
			if (!contentjson.containsKey("price") || StringUtils.isEmpty(contentjson.getString("price"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			order_price = contentjson.getString("price");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：price=" + order_price);
			}
			if (!contentjson.containsKey("body") || StringUtils.isEmpty(contentjson.getString("body"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			body = contentjson.getString("body");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：body=" + body);
			}
			if (!contentjson.containsKey("out_trade_no")
					|| StringUtils.isEmpty(contentjson.getString("out_trade_no"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			out_trade_no = contentjson.getString("out_trade_no");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：out_trade_no=" + out_trade_no);
			}
			if (!contentjson.containsKey("CREATE_IP") || StringUtils.isEmpty(contentjson.getString("CREATE_IP"))) {
				return ReturnUtils.jsonReturnToString("0", "参数不完整", "");
			}
			CREATE_IP = contentjson.getString("CREATE_IP");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付参数：CREATE_IP=" + CREATE_IP);
			}
		} catch (Exception e1) {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付异常：e1=" + e1);
			}
			return ReturnUtils.jsonReturnToString("0", "content不合法", "");
		}

		String APP_ID = "";
		String MCH_ID = "";
		String API_KEY = "";
		String NOTIFY_URL = "";
		String UFDODER_URL = "";
		String nonce_str = "";
		String trade_type = "NATIVE";// 请求二维码url
		try {
			// 读取配置文件类
			Properties pps = new Properties();
			InputStream in = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
			pps.load(in);
			// 商户平台APPID
			APP_ID = pps.getProperty("APP_ID");// appid
			// 公众号key秘钥
			// String appsecret = PayConfigUtil.APP_SECRET; // appsecret
			// 商户号
			MCH_ID = pps.getProperty("MCH_ID"); //
			// 商户key秘钥
			API_KEY = pps.getProperty("API_KEY"); // key
			// 生成地址 ip
			// CREATE_IP = pps.getProperty("CREATE_IP");
			// 异步回调地址
			NOTIFY_URL = pps.getProperty("NOTIFY_URL");
			// 微信生成二维码地址
			UFDODER_URL = pps.getProperty("UFDODER_URL");

			String currTime = PayCommonUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			String strRandom = PayCommonUtil.buildRandom(4) + "";
			nonce_str = strTime + strRandom;

			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，获取账户配置信息：APP_ID=" + APP_ID + ",MCH_ID=" + MCH_ID + ",API_KEY=" + API_KEY
						+ ",CREATE_IP=" + CREATE_IP + ",CREATE_IP=" + CREATE_IP + ",NOTIFY_URL=" + NOTIFY_URL
						+ ",UFDODER_URL=" + UFDODER_URL + ",nonce_str=" + nonce_str);
			}

			if (APP_ID == null || "".equals(APP_ID) || MCH_ID == null || "".equals(MCH_ID) || API_KEY == null
					|| "".equals(API_KEY) || CREATE_IP == null || NOTIFY_URL == null || "".equals(NOTIFY_URL)
					|| UFDODER_URL == null || "".equals(UFDODER_URL)) {
				return ReturnUtils.jsonReturnToString("0", "获取配置文件信息失败！", "");
			}
		} catch (Exception e) {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，获取账户配置信息：e=" + e);
			}
			return ReturnUtils.jsonReturnToString("0", "获取账户配置信息失败！", "");
		}

		// String return_code = (String) map.get("return_code");
		// String prepay_id = (String) map.get("prepay_id");
		String signRe = "";
		try {
			SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
			packageParams.put("appid", APP_ID);
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", order_price);
			packageParams.put("spbill_create_ip", CREATE_IP);
			packageParams.put("notify_url", NOTIFY_URL);
			packageParams.put("trade_type", trade_type);
			String sign = PayCommonUtil.createSign("UTF-8", packageParams, API_KEY);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-sign=" + sign);
			}
			packageParams.put("sign", sign);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-packageParams=" + JSONObject.fromObject(packageParams));
			}
			String requestXML = PayCommonUtil.getRequestXml(packageParams);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-requestXML=" + requestXML);
			}
			String resXml = HttpUtil.postData(UFDODER_URL, requestXML);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-resXml=" + resXml);
			}
			Map map = XMLUtil.doXMLParse(resXml);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，-map=" + JSONObject.fromObject(map));
			}
			String urlCode = (String) map.get("code_url");
			JSONObject jsonSign = new JSONObject();
			if (urlCode != null && urlCode != "" && !"".equals(urlCode)) {
				jsonSign.put("url", urlCode);
				signRe = jsonSign.toString();
			} else {
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("微信支付，生成url失败");
				}
				return ReturnUtils.jsonReturnToString("0", "生成url失败！", "");
			}
		} catch (Exception e) {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("微信支付，生成url失败-异常：e=" + e);
			}
			return ReturnUtils.jsonReturnToString("0", "生成url失败！", "");
		}
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("微信支付，获取支付url：signRe=" + signRe);
		}
		return ReturnUtils.jsonReturnToString("1", "成功", signRe);
	}

	/**
	 * 订单号
	 * 
	 * @return
	 */
	public static String getOutTradeNos() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		Date date = new Date();
		String key = format.format(date);

		java.util.Random r = new java.util.Random();
		key += r.nextInt(1000000);
		key = key.substring(0, 20);
		while (key.length() < 20) {
			key = key + "0";
		}
		return key;
	}

	public static String QRfromGoogle(String chl) throws Exception {
		int widhtHeight = 300;
		String EC_level = "L";
		int margin = 0;
		chl = UrlEncode(chl);
		String QRfromGoogle = "http://chart.apis.google.com/chart?chs=" + widhtHeight + "x" + widhtHeight
				+ "&cht=qr&chld=" + EC_level + "|" + margin + "&chl=" + chl;

		return QRfromGoogle;
	}

	public static String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
	}
}
