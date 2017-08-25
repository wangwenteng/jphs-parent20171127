package com.jinpaihushi.jphs.pay.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.service.OrderGoodsService;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.pay.wechatpay.WechatPay;
import com.jinpaihushi.pay.wechatpay.utils.PayCommonUtil;
import com.jinpaihushi.pay.wechatpay.utils.XMLUtil;
import com.jinpaihushi.tools.DoPostSms;
import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(path = "/getcode.json", name = "微信支付")
	@ResponseBody
	public byte[] wechatcode(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String content,String registerDevice){
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("wechat.getcode.json");
			}
			byte[] s = WechatPay.weixin_pay(content);
			return s;
		} catch (Exception e) {
			Util.failLog.error("wechat.getcode.json：e=", e);
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = "/notify.json", name = "微信支付异步回调")
	public void wechatNotify(HttpSession hs, HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException{
		  //读取参数  
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }  
        in.close();  
        inputStream.close();  
        // 记录日志-debug
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("wechat.notify.json:sb="+sb.toString());
		}
        //解析xml成map  
        Map<String, String> m = new HashMap<String, String>();  
        m = XMLUtil.doXMLParse(sb.toString());  
        // 记录日志-debug
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("wechat.notify.json: m="+JSONObject.fromObject(m).toString());
		}
        //过滤空 设置 TreeMap  
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
        Iterator it = m.keySet().iterator();  
        while (it.hasNext()) {  
            String parameter = (String) it.next();  
            String parameterValue = m.get(parameter);  
            String v = "";  
            if(null != parameterValue) {  
                v = parameterValue.trim();  
            }  
            packageParams.put(parameter, v);  
        } 
        // 记录日志-debug
 		if (Util.debugLog.isDebugEnabled()) {
 			Util.debugLog.debug("wechat.notify.json: packageParams="+JSONObject.fromObject(packageParams).toString());
 		}
        // 账号信息  
        String API_KEY =""; // key  
        
	     try {
			// 读取配置文件类
			Properties pps = new Properties();
			InputStream inKey  = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
			pps.load(inKey);
//			商户key秘钥
			API_KEY = pps.getProperty("API_KEY"); // key
		} catch (Exception e) {
		}
        
  
        //判断签名是否正确  
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams,API_KEY)) {  
            //------------------------------  
            //处理业务开始  
            //------------------------------  
            String resXml = "";  
            if("SUCCESS".equals((String)packageParams.get("result_code"))){  
                // 这里是支付成功  
                //////////执行自己的业务逻辑////////////////  
                String mch_id = (String)packageParams.get("mch_id");  
                String openid = (String)packageParams.get("openid");  
                String is_subscribe = (String)packageParams.get("is_subscribe");  
                String out_trade_no = (String)packageParams.get("out_trade_no");  
                String total_fee = (String)packageParams.get("total_fee");  
                String transaction_id = (String)packageParams.get("transaction_id");
             // 记录日志-debug
        		if (Util.debugLog.isDebugEnabled()) {
        			Util.debugLog.debug("wechat.notify.json:mch_id="+mch_id+",openid="+openid+",is_subscribe="+is_subscribe
        					+",out_trade_no="+out_trade_no
        					+",total_fee="+total_fee);
        		}
                //////////执行自己的业务逻辑////////////////  
                  
                Order order = new Order();
				order.setOrderNo(out_trade_no);
				order.setStatus(0);
				order.setSchedule(0);
				Order orders = orderService.load(order);
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("wechat.notify.json;根据微信返回transaction_id="+transaction_id+"平台内部订单号查询订单结果：orders="+JSONObject.fromObject(orders).toString());
				}
				
				if(orders!= null && !"".equals(orders)){
					
					OrderGoods orderGoods = new OrderGoods();
					orderGoods.setOrderId(orders.getId());
					orderGoods.setStatus(0);
					OrderGoods orderGoods_ny = orderGoodsService.load(orderGoods);
					// 记录日志-debug
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("wechat.notify.json;订单goods数据orderGoods_ny="+JSONObject.fromObject(orderGoods_ny).toString());
					}
					if(orderGoods_ny != null && !"".equals(orderGoods_ny)){
						int i = 1 ;
						
						try {
							String payPrice = orderGoods_ny.getPayPrice().toString();
							double total_fee_wc = Double.parseDouble(total_fee)/100;
							double total_fee_ord = Double.parseDouble(payPrice);
							/*int total_fee_wc = Integer.valueOf(total_fee)/100;
							int total_fee_ord = Integer.valueOf(payPrice);
							*/
							if(total_fee_wc == total_fee_ord){
								Transaction transaction = new Transaction();
								transaction.setId(UUID.randomUUID().toString());
								transaction.setOrderId(orders.getId());
								transaction.setAmount(total_fee_ord);
								transaction.setScore((new Double(total_fee_ord)).intValue());
								transaction.setOperate(3);
								transaction.setOperateSource(0);
								transaction.setRemark("微信回调");
								transaction.setWithdraw(0);
								transaction.setPayType(2);
								transaction.setOutTradeNo(transaction_id);
								transaction.setCreatorId(order.getCreatorId());
								transaction.setCreatorName(order.getCreatorName());
								transaction.setCreateTime(new Date());
								transaction.setStatus(1);
								// 记录日志-debug
								if (Util.debugLog.isDebugEnabled()) {
									Util.debugLog.debug("wechat.notify.json;transaction="+JSONObject.fromObject(transaction).toString());
								}
								i = transactionService.insert(transaction).length();
								if(i>0){
									Order orderUp = new Order();
									orderUp.setId(orders.getId());
									orderUp.setSchedule(1);
									boolean orderUpbool=orderService.update(orderUp);
									try {
										User user = new User();
										user.setId(orders.getCreatorId());
										user.setStatus(0);
										User orderUser = userService.load(user);
										// 记录日志-debug
										if (Util.debugLog.isDebugEnabled()) {
											Util.debugLog.debug("alipay.notify.json;订单用户信息orderUser="+JSONObject.fromObject(orderUser).toString());
										}
										if(orderUser != null){
											// 发送验证码
											DoPostSms.sendSms(orderUser.getPhone(), "【金牌护士】您的您的订单：" + out_trade_no + "下单成功。", "SMS_69155344", "{\"out_trade_no\":\"" + out_trade_no + "\"}");
										}
										
									} catch (Exception e) {
									}
									
									if(!orderUpbool){
										i=0;
									}
								}
							}else{
								i = 0;
								// 记录日志-debug
								if (Util.debugLog.isDebugEnabled()) {
									Util.debugLog.debug("wechat.notify.json;下单失败！total_fee_ali="+total_fee_wc+";total_fee_ord="+total_fee_ord);
								}
							}
						} catch (Exception e) {
							i = 0;
							// 记录日志-debug
							if (Util.debugLog.isDebugEnabled()) {
								Util.debugLog.debug("wechat.notify.json;下单失败！="+e);
							}
						}
						
						if(i<=0){
							// 记录日志-debug
							if (Util.debugLog.isDebugEnabled()) {
								Util.debugLog.debug("wechat.notify.json;下单失败！i="+i);
							}
							 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
				                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
						}else {
							// 记录日志-debug
							if (Util.debugLog.isDebugEnabled()) {
								Util.debugLog.debug("wechat.notify.json;下单成功！");
							}
							   //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
			                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
			                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
						}
					}else {
						// 记录日志-debug
						if (Util.debugLog.isDebugEnabled()) {
							Util.debugLog.debug("wechat.notify.json;下单失败！查询订单goods信息orderGoods_ny="+JSONObject.fromObject(orderGoods_ny).toString());
						}
						 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
			                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
					}
					
				}else {
					// 记录日志-debug
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("wechat.notify.json;下单失败！查询不到该订单数据-查看status状态以及schedule字段是否正确");
					}
					 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
		                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
				}
            } else {  
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            }  
            //------------------------------  
            //处理业务完毕  
            //------------------------------  
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
            out.write(resXml.getBytes());  
            out.flush();  
            out.close();  
        } else{// 记录日志-debug
     		if (Util.debugLog.isDebugEnabled()) {
     			Util.debugLog.debug("wechat.notify.json: 验证失败API_KEY="+API_KEY);
     		}
        }  
	}
	
}
