package com.jinpaihushi.pay.alipay;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.jinpaihushi.pay.alipay.sign.RSA;
import com.jinpaihushi.pay.alipay.util.AlipaySubmit;
import com.jinpaihushi.pay.utils.ReturnUtils;
import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

public  class AlipaySignNew {
	
		/*String payPrice = "0.01";
		double total_fee_ali = Double.parseDouble("0.01");
		double total_fee_ord = Double.parseDouble(payPrice);
		System.out.println("total_fee_ali--"+total_fee_ali);
		System.out.println("total_fee_ord--"+total_fee_ord);*/
		/*JSONObject sParaTemp = new JSONObject();
			sParaTemp.put("_input_charset", "utf-8");
			sParaTemp.put("body", "goods");
			sParaTemp.put("notify_url", "https://s.jinpaihushi.com/alipay/otherNotify.json");
			sParaTemp.put("out_trade_no", "JP20171269457");
			sParaTemp.put("payment_type", "1");
			sParaTemp.put("return_url", "site.jinpaihushi.com"); 
			sParaTemp.put("show_url", "site.jinpaihushi.com");
			sParaTemp.put("subject", "eason");
			sParaTemp.put("total_fee","0.01");
		
		byte[] s = getAlisign(sParaTemp.toString(),"PRIVATE_KEY","4");
		String t = new String(s);
		System.out.println(JSONObject.fromObject(t));
		System.out.println(JSONObject.fromObject(t).getJSONObject("result").getString("sign_from"));*/
		/*String sss = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCKIOoiBxjT7gG2ct15yMzpXzQMh05lOy26Tk+yr3Vm133WPRwxFo7f6FbQHMR6GtqmG6o7WGn4H98+ikJjnDEZLScFV35dJl+yZoPHAqWHb+31HRWVj3Lx7pIJj2LdqX8LE1U13tr9vS46QmMrawzZ/GQe/xkWZJJKlGUwGp8R1QCAw2VMXQqlfpxlwm9FeW39Y9SBy/p6UGDKKrmZsG8M+njE4LG964u5Cu/VhYs4lmgFpOvmUrat+F2mcDlVJbidLjpmkkr6CwXTeRo/QqRpKsnqwb6QN1Dj/iVzrrJ60XvMP33akkjuAkXPc1zR4mzpAuh65bWjDNiBy/wt7R29AgMBAAECggEAJxcni5e8lAWQYITjXtrP+t+hiEYmjomP6GDPNz9ml0M27pF+z/mVV5KWNvFdPT0qUcYUiBT+cGT8teNE5JHIZ1qa520aatw4SvGuK3z5QXoQusSPXjMpkYQ1uhTlItwcAxNmkwdNrG+EjKJLFHeIPIyr/QWuoOK3b/nNJS7zg4T6pq7cKtM8s+c+XlXW6QdxM283BCu1uzeMVgDYIh7D4LZ4Rb2GgNIl+4lWMdqMwgOg7p9knhP45ROLWLGCQcPJcCK3dDRNJ9inDEpOM62K0kGmREKZqJyAcl0iaA6I8q8t6HfEq3C49uhkfu3a6rgLuBcRWgD5HWEHs0/l/jjzwQKBgQDmk0sYwp9pRbt+VA2gv71vdXI4Ka/k+R/CYQrqubvI8tyuyPwjrFc6ZH+uQ1UoSGfRF9wnPQuSBhErLXPKNBSLO2Jq/wnZZaqJPDOqCEa4ECKgyNIo8+TE601KnCmyLzN/ZL7tyN5lhRUoHHS5V8GBtSozs069cmqQIwmZr4AiDQKBgQCZXAXnVylsN7iYkxcwgpUb0nygQkOBjcKohTzuGOEzZPbIVqmgp/4zk2lDfKyMOnMJysMbEuqKb9SHrIAAt47gVgUuHsEc5cZFECoy3JfftQxnmutD6Lt9CeifCCXDEDHHIWcDDqIQOAEYy3ZPjFzRFfdpRwo/pF4/a9YJIg7ucQKBgFj4LNn4/xnX1mkAdeG5GpePcetJ6p0no6GVYGkCEfuP+qdW1jPNpDrb3IiZYpqQlb6QvlpFq9lEjgtjFnLHdcXjvC4dCuff2tXpbOYHG4Kad0IjOzSiJ3v0b3aCm3Qt0mktrp6CC46Qs4EuubtqqTgrd4VLgu6G36eOXX33wjFhAoGAb2jUuR0u8HkCV4Tz4b/bhtzAwMOiuuk7MqWof+f/IhEE3sFmdVnVZ659WBOoXi7QB5sPZgm17WAUa4O7ccnnt72GAd1BuTaLdPyrS0vJ4TBrlJCMvUbDfbDHF1EZ7b1EDD+N0EC/Jz0oPtUAXAXCH/rvQ8HiBh3ogfZbBUOCyWECgYAHG5IIrMEdKDwTpdladOuIggvx/V8rK/akusBSjah36aStvXvHiekA0TseXf8JlT9Shp3jtEkPt3KDnI2UCBKcyC4EyS7GZErMWxHI4IUr+m6Sdbco/qtmhKldsoFu06DsvoajJhZGENwU29XVH7sXh5SrrdOVSKUdV+HBFnaz0Q==";
		System.out.println(sss.length());
		getPay();
			System.out.println(ALIPAY_PUBLIC_KEY.length());*/
	public static String URL = "https://openapi.alipay.com/gateway.do";
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyF4gfYpNWGCbNVsIo5gBDuHT/QqA0+YbC/9oaRk3FqC9bGDBcai2znQNlQX2eNxyrKbp33x0IwRwxP3S7vxTQ9dQbkpsjK0XG+N8PAY+b2rUMdM7P241zY9jiYZu152IvIAkF5wcuBnTnQVFdEf65SI9vOHRKxGe0mq1PNzZeGKZtXWG2qsZaMCpzYPos8UmW9wcbrp+HTQaBhP6MKSK8bY6pWc5v9+tau74or5WMDSdD+IP39yDe8FSApQ5khRUVAnlk96ZnZ9kF7hoUlyBwkdUVB//fwP8A+t/JDeUJyRyplw+wDH+/Ygv15pkIVy8CFtc0/IB/aumRCE9EKt8owIDAQAB";
	public static String RSA_PRIVATE_KEY = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDIXiB9ik1YYJs1WwijmAEO4dP9CoDT5hsL/2hpGTcWoL1sYMFxqLbOdA2VBfZ43HKspunffHQjBHDE/dLu/FND11BuSmyMrRcb43w8Bj5vatQx0zs/bjXNj2OJhm7XnYi8gCQXnBy4GdOdBUV0R/rlIj284dErEZ7SarU83Nl4Ypm1dYbaqxlowKnNg+izxSZb3Bxuun4dNBoGE/owpIrxtjqlZzm/361q7viivlYwNJ0P4g/f3IN7wVIClDmSFFRUCeWT3pmdn2QXuGhSXIHCR1RUH/9/A/wD638kN5QnJHKmXD7AMf79iC/XmmQhXLwIW1zT8gH9q6ZEIT0Qq3yjAgMBAAECggEBAJK7q9uD/uRryv4l7ouvc4bshoxWtsV3S/NbVaEx4A/oIxBNQxzzSvyiLJgnx7n25tvdWIRb5ckHJXZ9041Ep82UzsgWJKxYzgWUcJSclfiOKVtbr5XiJfClw2UhV+b67PnudX/8yWNhNoFVLOndFLXcf43laYK64q6BirxJl9D6R+NPT/W4bJNBcNSiRHnbHV8YUYDLsmEtbFAKwxJTbB0yfH0SuIo+tF5jchW0lm0qf3rxvw+c8O/B/duIMnFR3Ffic8roMNQ3skNiRE2SYgwZV5JqmYcByudwV9gBPraTDm5FEXlxWHo0HnruEFlQKo/um5mHhRdIQ5O867WiItECgYEA8X+gK3WmOCHiW8FOVTE1n17YlWQeRMMh24/LATAqTWF1X7ztQWFfCi1hoSHUjD+ULCYAcM9Pc2nMvd6aIHyZ82R8fzYjaszTvRmD/LMTtUcP3pWcCapK84+dh6D4Jb7Myyortq+rwp5UqDJuRskPznNl7qsF/5CIL9dsr4nUiB8CgYEA1GY6TTCjGy83o2Qr+BZWAnKu+EFHAHRpX00O49+YmWli+RnUsWPJhKZCcHMrVRLFIrLkVLvDAgb+kOOrgchAMnpcMKq2KN8RBhTveYt4YUwe5K6ZD9d1GYQ8Y8qBFAzzbEq+/kuWtShKN5DLJXdyxKM3MLuXHDee4Eh+1Oy4Sv0CgYEAsWzXqzZLgY/7cvgJafO2drlDHDarOk+2j04MknnlimZgyo7OLKuyHJMM2Eo3kzae0PS2fCsDL0L6xkBGMjn5DWW1Rezrhmqn4ZVecAjNIPDtitWWB0/SszC/RscPqQOOCHGaHiTgZyLWBaDPfjf34fDMYdXKmWGBfMyjsC/uMncCgYEA05og3UPmSy/oQzyJ3XgLKhctH6CQ5vATBmHTwsvA0J3Af6DvV+atHh0xNaxXcELp6R7GAXCp37wuIxTeicaBxohGjimUnvVUlXWH+tQrEuvuHy+ex7yEKe6m51gY7mvUm39/9gJCW6qO/fqfmGz/Bs4ALunEqtTy0EkJKoHHPNUCgYEAt1G/mAIBkdGjplePhTmvgYdKhvVTgp9MjHxgvKjSB5Kj+EUD4sXIyLpK57EH9HzPtU1lisU1AZTKYpXM3QrOGvdwUlhv3iyqcnS3bsySEatUSgwa04Fvx45rhEMWeXKdboamYAOvRCufT1xbfUBnEj4iwCthZcQx4mbOqYof7aA=";
	
	public static void getPay() throws UnsupportedEncodingException {
		// 商户订单号，商户网站订单系统中唯一订单号，必填
	    String out_trade_no ="JP142424378945";
		// 订单名称，必填
	    String subject =  new String("ds".getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(subject);
	    // 付款金额，必填
	    String total_amount="12";
	    // 商品描述，可空
	    String body = new String("dfdsgss".getBytes("ISO-8859-1"),"UTF-8");
//	    Math.random().toString(36).substr(2, 8);
	    // 超时时间 可空
	    String timeout_express="2m";
	    // 销售产品码 必填
	    String product_code="FDSAASD";
	    /**********************/
	    // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
	    //调用RSA签名方式
	    AlipayClient client = new DefaultAlipayClient(URL, "2016011201087224", RSA_PRIVATE_KEY,"json", "UTF-8", ALIPAY_PUBLIC_KEY,"RSA2");
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    // 封装请求支付信息
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(out_trade_no);
	    model.setSubject(subject);
	    model.setTotalAmount(total_amount);
	    model.setBody(body);
	    model.setTimeoutExpress(timeout_express);
	    model.setProductCode(product_code);
	    JSONObject passbackParams = new JSONObject();
	    passbackParams.put("userId", "123");
	    passbackParams.put("type", "345");
	    passbackParams.put("ex", "123124");
	    passbackParams.put("name", "123124");
	    model.setPassbackParams(passbackParams.toString());
	    
	    alipay_request.setBizModel(model);
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl("https://www.jinpaihushi.com");
	    // 设置同步地址
	    alipay_request.setReturnUrl("https://www.jinpaihushi.com");   
	    
	    // form表单生产
	    String form = "";
//		try {
			// 调用SDK生成表单
			try {
				form = client.pageExecute(alipay_request).getBody();
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(form);
		/*	response.setContentType("text/html;charset=" + AlipayConfig.CHARSET); 
		    response.getWriter().write(form);//直接将完整的表单html输出到页面 
		    response.getWriter().flush(); 
		    response.getWriter().close();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}
	/*if(content_json.containsKey("total_amount")){
		return ReturnUtils.jsonReturnToString("0", "加密参数有空！","total_amount");
	}
	if(content_json.containsKey("subject")){
		return ReturnUtils.jsonReturnToString("0", "加密参数有空！","subject");
	}
	if(content_json.containsKey("out_trade_no")){
		return ReturnUtils.jsonReturnToString("0", "加密参数有空！","out_trade_no");
	}
	if(content_json.containsKey("body")){
		return ReturnUtils.jsonReturnToString("0", "加密参数有空！","body");
	}
	if(content_json.containsKey("product_code")){
		return ReturnUtils.jsonReturnToString("0", "加密参数有空！","product_code");
	}
	if(content_json.containsKey("notify_url")){
		return ReturnUtils.jsonReturnToString("0", "加密参数有空！","notify_url");
	}
	if(content_json.containsKey("return_url")){
		return ReturnUtils.jsonReturnToString("0", "加密参数有空！","return_url");
	}*/
	
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("total_amount", 1);
		obj.put("subject", "电风扇");
		obj.put("out_trade_no", "JP489451455");
		obj.put("body", "阿斯顿");
		obj.put("product_code", "DTCVSAAWE");
		obj.put("notify_url", "https://www.jinpaihushi.com");
		obj.put("return_url", "https://www.jinpaihushi.com");
		JSONObject passbackParams = new JSONObject();
		passbackParams.put("sda", "12");
		passbackParams.put("fsd", "123");
		passbackParams.put("vxc", "1234");
		obj.put("passbackParams",passbackParams);
		
		byte [] s= getNewAlisign(obj.toString(),"12");
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
	public static byte[] getNewAlisign(String content,String endType)  {
		try{
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("参数：content="+content+";");
			}
			if(StringUtils.isEmpty(content)||
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
				return ReturnUtils.jsonReturnToString("0", "类型转换异常！", endType);
			}
			
			// from表单
			String sign_from = "";
			// 私钥
			String NEW_PRIVATE_KEY = "";
			// 公钥
			String NEW_ALIPAY_PUBLIC_KEY = "";
			// 合作身份者ID，签约账号
			String ALIPAY_APPID = "";
			// 生成签名的支付宝地址
			String NEW_ALIPAY_URL = "";
			// 编码格式
			String INPUT_CHARSET = "UTF-8";
			try {
				// 读取配置文件类
				Properties pps = new Properties();
				InputStream in  = PropertiesUtil.class.getResourceAsStream("/config/alipayConfig.properties");
				pps.load(in);
				//	平台数据key	registerDevice
				// 私钥
				NEW_PRIVATE_KEY = pps.getProperty("NEW_ALIPAY_RSA_PRIVATE_KEY");
				// 公钥
				NEW_ALIPAY_PUBLIC_KEY =pps.getProperty("NEW_ALIPAY_PUBLIC_KEY");
				// APPID
				ALIPAY_APPID =pps.getProperty("NEW_ALIPAY_APPID");
				
				NEW_ALIPAY_URL =pps.getProperty("NEW_ALIPAY_URL");
				
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("平台数据：ALIPAY_APPID="+ALIPAY_APPID+"; INPUT_CHARSET="+INPUT_CHARSET+"; NEW_ALIPAY_PUBLIC_KEY="+NEW_ALIPAY_PUBLIC_KEY
							+"; NEW_ALIPAY_URL="+NEW_ALIPAY_URL
							+"; NEW_PRIVATE_KEY="+NEW_PRIVATE_KEY);
				}
			} catch (Exception e) {
				  Util.failLog.error("alipay.sign.json", e);
				return ReturnUtils.jsonReturnToString("0", "未获取到配置文件！","");
			}	
			// 
			if(NEW_PRIVATE_KEY == null || "".equals(NEW_PRIVATE_KEY)){
				return ReturnUtils.jsonReturnToString("0", "registerDevice参数不合法！","");
			}
			
				try {
					JSONObject content_json = new JSONObject().fromObject(content);
					System.out.println(content_json.toString());
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("请求数据：content_json="+content_json.toString());
					}
					if(!content_json.containsKey("total_amount")){
						return ReturnUtils.jsonReturnToString("0", "加密参数有空！","total_amount");
					}
					if(!content_json.containsKey("subject")){
						return ReturnUtils.jsonReturnToString("0", "加密参数有空！","subject");
					}
					if(!content_json.containsKey("out_trade_no")){
						return ReturnUtils.jsonReturnToString("0", "加密参数有空！","out_trade_no");
					}
					if(!content_json.containsKey("body")){
						return ReturnUtils.jsonReturnToString("0", "加密参数有空！","body");
					}
					if(!content_json.containsKey("product_code")){
						return ReturnUtils.jsonReturnToString("0", "加密参数有空！","product_code");
					}
					if(!content_json.containsKey("notify_url")){
						return ReturnUtils.jsonReturnToString("0", "加密参数有空！","notify_url");
					}
					if(!content_json.containsKey("return_url")){
						return ReturnUtils.jsonReturnToString("0", "加密参数有空！","return_url");
					}
					
					/**********************/
				    // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
				    //调用RSA签名方式
				    AlipayClient client = new DefaultAlipayClient(NEW_ALIPAY_URL, ALIPAY_APPID, NEW_PRIVATE_KEY,"json", INPUT_CHARSET, NEW_ALIPAY_PUBLIC_KEY,"RSA2");
				    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
				    // 封装请求支付信息
				    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
				    model.setOutTradeNo(content_json.getString("out_trade_no"));
				    model.setSubject(content_json.getString("subject"));
				    model.setTotalAmount(content_json.getString("total_amount"));
				    model.setBody(content_json.getString("body"));
				    model.setProductCode(content_json.getString("product_code"));
				    if(content_json.containsKey("passbackParams")){
				    	model.setPassbackParams(content_json.getString("passbackParams"));
				    }
				    alipay_request.setBizModel(model);
				    // 设置异步通知地址
				    alipay_request.setNotifyUrl(content_json.getString("notify_url"));
				    // 设置同步地址
				    alipay_request.setReturnUrl(content_json.getString("return_url"));   
					
				    sign_from = client.pageExecute(alipay_request).getBody();
				    
				} catch (Exception e) {
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("生成from表单结果异常：sign="+e);
					}
					return ReturnUtils.jsonReturnToString("0", "生成签名异常失败！","");
				}
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("生成from表单结果：sign="+sign_from);
				}
				if(sign_from == null || sign_from == "" || "".equals(sign_from)){
					return ReturnUtils.jsonReturnToString("0", "生成签名失败！","");
				}
				JSONObject jsonSign = new JSONObject();
				jsonSign.put("sign_from", sign_from);
			return ReturnUtils.jsonReturnToString("1", "成功",jsonSign.toString());
		}catch (Exception e){
			Util.failLog.error("alipay.sign.json", e);
		}
		return ReturnUtils.jsonReturnToString("0", "生成签名异常！","");
	}
	
}
