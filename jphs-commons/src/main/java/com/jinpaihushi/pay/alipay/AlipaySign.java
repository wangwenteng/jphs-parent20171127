package com.jinpaihushi.pay.alipay;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.jinpaihushi.pay.alipay.sign.RSA;
import com.jinpaihushi.pay.alipay.util.AlipaySubmit;
import com.jinpaihushi.pay.utils.ReturnUtils;
import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

public  class AlipaySign {
	
	public static void main(String[] args) {
		/*String payPrice = "0.01";
		double total_fee_ali = Double.parseDouble("0.01");
		double total_fee_ord = Double.parseDouble(payPrice);
		System.out.println("total_fee_ali--"+total_fee_ali);
		System.out.println("total_fee_ord--"+total_fee_ord);*/
		JSONObject sParaTemp = new JSONObject();
			sParaTemp.put("_input_charset", "utf-8");
			sParaTemp.put("body", "goods");
			sParaTemp.put("notify_url", "https://s.jinpaihushi.com/alipay/otherNotify.json");
			sParaTemp.put("out_trade_no", "JP2017189369457");
			sParaTemp.put("payment_type", "1");
			sParaTemp.put("return_url", "site.jinpaihushi.com"); 
			sParaTemp.put("show_url", "site.jinpaihushi.com");
			sParaTemp.put("subject", "eason");
			sParaTemp.put("total_fee","0.01");
		
		byte[] s = getAlisign(sParaTemp.toString(),"PRIVATE_KEY","4");
		String t = new String(s);
		System.out.println(JSONObject.fromObject(t));
		System.out.println(JSONObject.fromObject(t).getJSONObject("result").getString("sign_from"));
	}
	
	/**
	 * web支付
	 * @param content			签名数据
	 * @param registerDevice	平台私钥名称
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static byte[] getAlisign(String content,String registerDevice,String endType)  {
		try{
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("参数：content="+content+";registerDevice="+registerDevice);
			}
			if(StringUtils.isEmpty(content)||
					StringUtils.isEmpty(registerDevice)||
						StringUtils.isEmpty(endType)) {
				return ReturnUtils.jsonReturnToString("0", "加密参数为空！","");
			}
			int end_type = 0;
			try{
				end_type = Integer.parseInt(endType);
			}catch (Exception e){
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("参数：endType="+endType+";类型转换异常");
				}
				return ReturnUtils.jsonReturnToString("0", "类型转换异常！","");
			}
			
			// 返回数据json字符串
			String signRe = "";
			// from表单
			String sign_from = "";
			// 私钥
			String PRIVATE_KEY = "";
			// 合作身份者ID，签约账号
			String SELLER_ID = "";
			// 	收款支付宝账号
			String PARTNER = "";
			// 生成签名的支付宝地址
			String ALI_SERVICE_URL = "";
			// 编码格式
			String INPUT_CHARSET = "";
			try {
				// 读取配置文件类
				Properties pps = new Properties();
				InputStream in  = PropertiesUtil.class.getResourceAsStream("/config/alipayConfig.properties");
				pps.load(in);
				//	平台数据key	registerDevice
				PRIVATE_KEY = pps.getProperty(registerDevice);
				INPUT_CHARSET =pps.getProperty("INPUT_CHARSET");
				PARTNER =pps.getProperty("PARTNER");
				// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
				SELLER_ID =pps.getProperty("PARTNER");
				
				//	ios	加密签名	安卓	加密签名	mobile.securitypay.pay	APP支付
				if(end_type == 1 || end_type == 2){
					ALI_SERVICE_URL =pps.getProperty("ALI_SERVICE_URL_APP");
				}else if(end_type == 4 || end_type == 6){//	114、web站支付，web支付
					ALI_SERVICE_URL =pps.getProperty("ALI_SERVICE_URL_WEB");
				}else if(end_type == 5){//	官网		pc支付
					ALI_SERVICE_URL =pps.getProperty("ALI_SERVICE_URL");
				}
				
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("平台数据：PRIVATE_KEY="+PRIVATE_KEY+";INPUT_CHARSET="+INPUT_CHARSET);
				}
			} catch (Exception e) {
				return ReturnUtils.jsonReturnToString("0", "未获取到配置文件！","");
			}	
			// 
			if(PRIVATE_KEY != null && !"".equals(PRIVATE_KEY)){
				JSONObject jsonSign = new JSONObject();
				
				try {
					JSONObject content_json = new JSONObject().fromObject(content);
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("请求数据：content_json="+content_json.toString());
					}
					//		       顺序一定不能乱
					String orderInfo = "_input_charset="+content_json.getString("_input_charset");	//	编码格式：utf-8
							orderInfo += "&body=" +  content_json.getString("body") ;				//	商品信息
							orderInfo += "&notify_url="+content_json.getString("notify_url");		//	异步回调地址
							orderInfo += "&out_trade_no=" + content_json.getString("out_trade_no");	//	订单号
							orderInfo += "&partner=" + PARTNER;										//	商户收款账号
							orderInfo += "&payment_type="+content_json.getString("payment_type");	//	支付类型1 ，无需修改
							orderInfo += "&return_url="+content_json.getString("return_url");		//	同步回调
							orderInfo += "&seller_id=" + SELLER_ID;									//	收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
							orderInfo += "&service="+ALI_SERVICE_URL;								//	生成签名支付宝url地址
							orderInfo += "&show_url="+content_json.getString("show_url");			//	商品展示页面
							orderInfo += "&subject=" + content_json.getString("subject") ;			//	商品名称
							orderInfo += "&total_fee="  + content_json.getString("total_fee");		//	金额
							
					String sign = RSA.sign(orderInfo, PRIVATE_KEY,INPUT_CHARSET);
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("签名结果：sign="+sign);
					}
					if(StringUtils.isEmpty(sign)){
						
					}
					if(end_type == 1 || end_type == 2){
						jsonSign.put("sign", sign);
						signRe = jsonSign.toString();
						return ReturnUtils.jsonReturnToString("1", "生成签名成功！",signRe);
					}
					
					Map<String, String> sParaTemp = new HashMap<String, String>();
						sParaTemp.put("_input_charset", content_json.getString("_input_charset"));
						sParaTemp.put("body", content_json.getString("body"));
						sParaTemp.put("notify_url", content_json.getString("notify_url"));
						sParaTemp.put("out_trade_no", content_json.getString("out_trade_no"));
						sParaTemp.put("partner", PARTNER);
						sParaTemp.put("payment_type", content_json.getString("payment_type"));
						sParaTemp.put("return_url", content_json.getString("return_url")); 
						sParaTemp.put("seller_id", SELLER_ID);
						sParaTemp.put("service", ALI_SERVICE_URL);
						sParaTemp.put("show_url", content_json.getString("show_url"));
						sParaTemp.put("subject", content_json.getString("subject"));
						sParaTemp.put("total_fee",content_json.getString("total_fee"));
						//	sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
						sParaTemp.put("sign", sign);
						sign_from = AlipaySubmit.buildRequest(sParaTemp,"get","ok");
				} catch (Exception e) {
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("生成from表单结果异常：sign="+e);
					}
					return ReturnUtils.jsonReturnToString("0", "生成签名异常失败！","");
				}
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("生成from表单结果：sign="+sign_from);
				}
				
				if(sign_from != null && sign_from != "" && !"".equals(sign_from)){
					
					jsonSign.put("sign_from", sign_from);
					signRe = jsonSign.toString();
				}else{
					return ReturnUtils.jsonReturnToString("0", "生成签名失败！","");
				}
			}else{
				return ReturnUtils.jsonReturnToString("0", "registerDevice参数不合法！","");
			}
			return ReturnUtils.jsonReturnToString("1", "成功",signRe);
		}catch (Exception e){
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("生成from表单结果：异常信息="+e);
			}
		}
		return ReturnUtils.jsonReturnToString("0", "生成签名异常！","");
	}
	
}
