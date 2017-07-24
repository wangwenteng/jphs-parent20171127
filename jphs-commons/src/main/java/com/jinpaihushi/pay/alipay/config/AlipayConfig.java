package com.jinpaihushi.pay.alipay.config;

import com.jinpaihushi.pay.alipay.util.UtilDate;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088121602433527";
	
	public static String key="sazrz1ih3kegdfyd8jkp57dscy34zziy";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ3mWUISkxIpXzyb1nspEfOVkPwzOImTZuBHs1Wq/cR4dZkGwcfRucIw7E+IYHAO/YmsJJYoITjwjXOEeRi+Cy8hpN5MWdPUpQ+Is7rVU5PuNQALrJrIPeFnfq0QKBmouvuxdZ1Mhn9ey3K5hBqBGUb3ETXUQqq5dRHwKpbxehVVAgMBAAECgYBRj3ZzhpypOO2UpPp4y5IWPB+T5fFCPv1YxDCCA7zpCz/TN4ZI7LAC+rpVqqAFwfh8cIrFdR0UCE1IahkTCrdDZUiozHr1e5xoGxbnEtx4EV9R6C1kkIwcszscbuQTBxaxCRLCGnGeD+0L3qJsAsOjHhGz9bCvJ5vHiC03zy6D0QJBANCmd9lTjzRUPraYIwpxOrtw9QFdb9WY1zsTE8MwbTuH+tCY+NWD8czh7dAc0OFGFbOsBGBZx0pq1UtXLG+yLXMCQQDBu4iP2pp8EA3WYWRa9xPK3S+OFKE1P6J0HNUHJfDEH25h97EKHVHgWvxmImPPrptVsJ6gjYdxf4zdiaNXqAAXAkB/EWJb0C+eRtAKk4yDh4GlNZ2AP08kXfh12aBnds6POrjc4Mxp+/j+NXmHDIWJLA17Zn8jzok4IOfVAjnlrDVRAkEAoaKsmpEh6TafxOi3CSoyCkSmw500ry+rg8L2faNLEObEetpkjHLP+CcRhMlWi1U75q+YeY37nld0GPYYIS9a+wJBAMBJiutPyKxsH4MwExfdihSVFsvNC5/FA5lKxhvWDsfDM062J7DOsoKb6vpayVuarHEtnJk4lInEk82nbdpVk2I=";	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
//	 public void init() throws ServletException {  
//		 Properties pps = new Properties();
//			InputStream in = LoginService.class.getResourceAsStream("/alipayConfig.properties");
//			try {
//				pps.load(in);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//			partner=pps.getProperty("PARTNER");
//			key=pps.getProperty("KEY");
//			private_key=pps.getProperty("PRIVATE_KEY");
//			ali_public_key=pps.getProperty("ALI_PUBLIC_KEY");
//	        
//	    }  


	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

	// 签名方式 不需修改
	public static String sign_type2 = "MD5";
	
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_user_id = partner;


	// 支付宝公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm 
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://www.baidu.com";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://商户网址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";

	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
		
	 //退款日期 时间格式 yyyy-MM-dd HH:mm:ss
    public static String refund_date = UtilDate.getDateFormatter();
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}
