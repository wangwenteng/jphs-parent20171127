﻿package com.jinpaihushi.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * <p>
 * <date>2012-03-01</date><br/>
 * 
 * </p>
 * 
 * @author LIP
 * @version 1.0.1
 */
public  class DoPostSms {

	//天下畅通
	public static String url = "http://121.52.209.124:8888/sms.aspx";
	public static String userid = "6229";
	public static String account = "a10436";
	public static String password = "456456";
	
	//阿里大于
	public static String url_="http://gw.api.taobao.com/router/rest";
	public static String appkey_="23872811";
	public static String secret_="b7d482c09e2b3b744a33c24aab97213f";
	
	public static void main(String[] args) {

		String titles="金牌护师";
		String dbname="杨松";
		String dbphone="13341181592";
		String dbnames=dbname+":"+dbphone;
		
		String orderID = "JP1234567890";
		sendSms("13341181592", "【金牌护士】您的订单"+orderID+"支付成功。关注微信公众号“金牌护师”，时时查看订单状态。","SMS_69255031","{\"titles\":\""+titles+"\",\"dbnames\":\""+dbnames+"\"}");
	}
	
	public static void sendSms(String mobile,String content,String contentCode,String param) {
		
//		 Properties pps = new Properties();
			//InputStream in = LoginService.class.getResourceAsStream("/goldenNurse.properties");
//			try {
//				pps.load(in);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//1.天下畅通平台，2.阿里大于
//			int messageId=Integer.valueOf(pps.getProperty("Message").trim());
			
			int messageId =1;
			if(messageId==1)
			{
				try {
					String send = SmsClientSend.sendSms(url,"send",userid,account,password,mobile,content);
					System.out.println(new String(send.getBytes("UTF-8")));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}else if(messageId==2)
			{
				try {
					TaobaoClient client = new DefaultTaobaoClient(url_, appkey_, secret_);
					AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
					req.setExtend("123456");
					req.setSmsType("normal");
					req.setSmsFreeSignName("金牌护士");//短信签名，传入的短信签名必须是在阿里大于“管理中心-验证码/短信通知/推广短信-配置短信签名
					//req.setSmsParamString("{\"code\":\"1234\",\"product\":\"alidayu\"}");
					req.setSmsParamString(param);
					req.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔
					req.setSmsTemplateCode(contentCode);//短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。
					AlibabaAliqinFcSmsNumSendResponse rsp;
					rsp = client.execute(req);
					System.out.println(rsp.getBody());
				} catch (ApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		

	}
	

	/**
	 * <p>
	 * <date>2012-03-01</date><br/>
	 * <span>发送信息方法1--必须传入必填内容</span><br/>
	 * <p>
	 * 其一：发送方式，默认为POST<br/>
	 * 其二：发送内容编码方式，默认为UTF-8
	 * </p>
	 * <br/>
	 * <span>发送信息最终的组合形如：http://xtx.telhk.cn:8080/sms.aspx?</span> </p>
	 * 
	 * @param url
	 *            ：必填--发送连接地址URL--比如>
	 * @param userid
	 *            ：必填--用户ID，一般为数字
	 * @param account
	 *            ：必填--用户帐号
	 * @param password
	 *            ：必填--用户密码
	 * @param mobile
	 *            ：必填--发送的手机号码，多个可以用逗号隔比如>
	 * @param content
	 *            ：必填--实际发送内容，
	 * @return 返回发送之后收到的信息
	 */
	public static String sendSms(String url, String userid, String account,
			String password, String mobile, String content) {

		return sendSms(url, userid, account, password, mobile, content, null,
				null, null, null, null, null, null, "POST", "UTF-8");
	}

	/**
	 * <p>
	 * <date>2012-03-01</date><br/>
	 * <span>发送信息方法</span><br/>
	 * <span>发送信息最终的组合形如：http://xtx.telhk.cn:8080/sms.aspx??action=send</span>
	 * </p>
	 * 
	 * @param url
	 *            ：必填--发送连接地址URL--比如>http://118.145.30.35/sms.aspx
	 * 
	 * @param userid
	 *            ：必填--用户ID，一般为数字
	 * @param account
	 *            ：必填--用户帐号
	 * @param password
	 *            ：必填--用户密码
	 * @param mobile
	 *            ：必填--发送的手机号码，多个可以用逗号隔比如>13512345678,13612345678
	 * @param content
	 *            ：必填--实际发送内容，
	 * @param action
	 *            ：选填--访问的事件，默认为send
	 * @param sendTime
	 *            ：选填--定时发送时间，不填则为立即发送，时间格式如>2011-11-11 11:11:11
	 * @param checkContent
	 *            ：选填--检查是否包含非法关键字，1--表示需要检查，0--表示不检查
	 * @param taskName
	 *            ：选填--任务名称，本次任务描述，100字内
	 * @param countNumber
	 *            ：选填--提交号码总数
	 * @param mobileNumber
	 *            ：选填--手机号码总数
	 * @param telephoneNumber
	 *            ：选填--小灵通（和）或座机总数
	 * @param sendType
	 *            ：选填--发送方式，默认为POST
	 * @param codingType
	 *            ：选填--发送内容编码方式，默认为UTF-8
	 * @return 返回发送之后收到的信息
	 */
	public static String sendSms(String url, String userid, String account,
			String password, String mobile, String content, String action,
			String sendTime, String checkContent, String taskName,
			String countNumber, String mobileNumber, String telephoneNumber,
			String sendType, String codingType) {

		return "";
	}
	
	

	public static String sendGet() {

		return "";
	}

	public static String sendPost(String sendUrl, String outEncoding) {

		String retMsg = "";
		BufferedReader reader = null;
		try {

			URL url = new URL(sendUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			// 发送域信息
			OutputStreamWriter out = new OutputStreamWriter(connection
					.getOutputStream(), outEncoding);
			out.flush();
			out.close();
			// 获取返回数据
			InputStream in = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			retMsg = buffer.toString();
		} catch (Exception e) {

			e.printStackTrace();
			retMsg = "reuid_error";
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return retMsg.trim();
	}
}
