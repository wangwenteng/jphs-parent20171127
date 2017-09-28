package com.jinpaihushi.jphs.pay.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.family.service.FamilyOrderService;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.pay.wechatpay.WechatPay;
import com.jinpaihushi.pay.wechatpay.utils.PayCommonUtil;
import com.jinpaihushi.pay.wechatpay.utils.XMLUtil;
import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CommodityOrderService commodityOrderService;
	
	@Autowired
	private FamilyOrderService familyOrderService;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
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
            	boolean pay_status = false;
            	String attach = (String)packageParams.get("attach"); 
            	JSONObject attach_json = new JSONObject().fromObject(attach);
            	if(attach_json.containsKey("payType")){
            		int payType = attach_json.getInt("payType");
            		//	服务
            		if(payType == 1){
            			pay_status = orderService.updateWechatOrderStutas(packageParams);
            		}
            		if(payType == 2){
            			pay_status = commodityOrderService.updateWechatCommodityOrderStutas(packageParams);
            		}
            		if(payType == 3){
            			pay_status = familyOrderService.updateWechatfamilyOrderStutas(packageParams);
            		}
            		
            		// 记录日志-debug
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("wechat.notify：pay_status=" +pay_status);
                    }
            		
            		if(pay_status){
            			  //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
		                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
		                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
            		}else{
            			 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                                 + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            		}
            	}else{
            		 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                             + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            	}
            	
            } else {  
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            }  
            
         // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("wechat.notify：resXml=" +resXml);
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
