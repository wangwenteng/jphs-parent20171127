package com.jinpaihushi.jphs.pay.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.jinpaihushi.pay.alipay.AlipaySign;
import com.jinpaihushi.pay.alipay.util.AlipayNotify;
import com.jinpaihushi.tools.DoPostSms;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/alipay")
public class AlipaySignController {
	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(path = "/sign.json", name = "阿里支付")
	@ResponseBody
	public byte[] alipaySign(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String content,String registerDevice){
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("alipay.sign.json");
			}
			byte[] s = AlipaySign.getAlisign(content, registerDevice);
			return s;
		} catch (Exception e) {
			Util.failLog.error("alipay.sign.json", e);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/otherNotify.json", name = "阿里支付异步回调")
	@Transactional
	public byte[] alipayNotify(HttpSession hs, HttpServletRequest request, HttpServletResponse response) throws IOException{
				//获取支付宝POST过来反馈信息
				Map<String,String> params = new HashMap<String,String>();
				Map requestParams = request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i]
								: valueStr + values[i] + ",";
					}
					//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
					//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
					params.put(name, valueStr);
				}
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("alipay.notify.json;支付宝回调返回数据：params="+params.toString());
				}
				
				//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
				//商户订单号
				System.out.println("支付宝返回数据:"+params);
				//交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
				// 自己生成的订单号
				String no=new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
				// 支付宝交易号
				String trade_no=new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
				// 金额
				String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
				System.out.println("out_trade_no="+no);
				System.out.println("trade_no="+trade_no);
				
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("alipay.notify.json;支付宝回调返回数据：交易状态trade_status="+params);
					Util.debugLog.debug("alipay.notify.json;支付宝回调返回数据：本平台内部订单号no="+no);
					Util.debugLog.debug("alipay.notify.json;支付宝回调返回数据：支付宝交易号trade_no="+trade_no);
					Util.debugLog.debug("alipay.notify.json;支付宝回调返回数据：支付金额total_fee="+total_fee);
				}
				//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
				if(AlipayNotify.verify(params)){//验证成功
					//////////////////////////////////////////////////////////////////////////////////////////
					//请在这里加上商户的业务逻辑程序代码
					//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					System.out.println("验证成功！");
					if(trade_status.equals("TRADE_FINISHED")){
						//判断该笔订单是否在商户网站中已经做过处理
							//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
							//如果有做过处理，不执行商户的业务程序
						// 记录日志-debug
						if (Util.debugLog.isDebugEnabled()) {
							Util.debugLog.debug("alipay.notify.json;支付宝回调返回数据：trade_status：TRADE_FINISHED");
						}
						//注意：
						//该种交易状态只在两种情况下出现
						//1、开通了普通即时到账，买家付款成功后。trade_close 
						//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
						response.getWriter().write("success");
					}else if (trade_status.equals("TRADE_SUCCESS")){
						//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
						//注意：
						//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
						//第一步：获取下单对应的字段
						//服务器下单
						try {
							Order order = new Order();
							order.setOrderNo(no);
							order.setStatus(0);
							order.setSchedule(0);
							Order orders = orderService.load(order);
							
							// 记录日志-debug
							if (Util.debugLog.isDebugEnabled()) {
								Util.debugLog.debug("alipay.notify.json;根据支付宝返回no="+no+"平台内部订单号查询订单结果：orders="+JSONObject.fromObject(orders).toString());
							}
							
							if(orders!= null && !"".equals(orders)){
								
								OrderGoods orderGoods = new OrderGoods();
								orderGoods.setOrderId(orders.getId());
								orderGoods.setStatus(0);
								OrderGoods orderGoods_ny = orderGoodsService.load(orderGoods);
								// 记录日志-debug
								if (Util.debugLog.isDebugEnabled()) {
									Util.debugLog.debug("alipay.notify.json;订单goods数据orderGoods_ny="+JSONObject.fromObject(orderGoods_ny).toString());
								}
								if(orderGoods_ny != null && !"".equals(orderGoods_ny)){
									int i = 1 ;
									
									try {
										double payPrice = orderGoods_ny.getPayPrice();
										double total_fee_ali = Double.parseDouble(total_fee);
//										double total_fee_ord = Double.parseDouble(payPrice);
										// 记录日志-debug
										if (Util.debugLog.isDebugEnabled()) {
											Util.debugLog.debug("alipay.notify.json;=order_goods查询的价格-"+payPrice);
										}
										// 记录日志-debug
										if (Util.debugLog.isDebugEnabled()) {
											Util.debugLog.debug("alipay.notify.json;=阿里返回的价格-"+total_fee_ali);
										}
										
										if(total_fee_ali == payPrice){
											// 记录日志-debug
											if (Util.debugLog.isDebugEnabled()) {
												Util.debugLog.debug("alipay.notify.json;=两个价格比对-"+(total_fee_ali == payPrice));
											}
											Transaction transaction = new Transaction();
											transaction.setId(UUID.randomUUID().toString());
											transaction.setOrderId(orders.getId());
											transaction.setAmount(total_fee_ali);
											transaction.setScore((new Double(total_fee_ali)).intValue());
											transaction.setOperate(3);
											transaction.setOperateSource(0);
											transaction.setRemark("支付宝回调");
											transaction.setWithdraw(0);
											transaction.setPayType(1);
											transaction.setOutTradeNo(trade_no);
											transaction.setCreatorId("");
											transaction.setCreatorName("系统");
											transaction.setCreateTime(new Date());
											transaction.setStatus(1);
											// 记录日志-debug
											if (Util.debugLog.isDebugEnabled()) {
												Util.debugLog.debug("alipay.notify.json;transaction="+JSONObject.fromObject(transaction).toString());
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
														DoPostSms.sendSms(orderUser.getPhone(), "【金牌护士】您的您的订单：" + no + "下单成功。", "SMS_69155344", "{\"out_trade_no\":\"" + no + "\"}");
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
												Util.debugLog.debug("alipay.notify.json;下单失败！total_fee_ali="+total_fee_ali+";payPrice="+payPrice);
											}
										}
									} catch (Exception e) {
										i = 0;
										// 记录日志-debug
										if (Util.debugLog.isDebugEnabled()) {
											Util.debugLog.debug("alipay.notify.json;下单失败！="+e);
										}
									}
									
									if(i<=0){
										System.out.println("下单失败！");	//请不要修改或删除
										// 记录日志-debug
										if (Util.debugLog.isDebugEnabled()) {
											Util.debugLog.debug("alipay.notify.json;下单失败！i="+i);
										}
									}else {
										// 记录日志-debug
										if (Util.debugLog.isDebugEnabled()) {
											Util.debugLog.debug("alipay.notify.json;下单成功！");
										}
										System.out.println("下单成功！");	//请不要修改或删除
									}
								}else {
									// 记录日志-debug
									if (Util.debugLog.isDebugEnabled()) {
										Util.debugLog.debug("alipay.notify.json;下单失败！查询订单goods信息orderGoods_ny="+JSONObject.fromObject(orderGoods_ny).toString());
									}
									System.out.println("下单失败！");	//请不要修改或删除
								}
								
							}else {
								// 记录日志-debug
								if (Util.debugLog.isDebugEnabled()) {
									Util.debugLog.debug("alipay.notify.json;下单失败！查询不到该订单数据-查看status状态以及schedule字段是否正确");
								}
								System.out.println("下单失败！查询不到该订单数据");	//请不要修改或删除
							}
								
							response.getWriter().write("success");
						} catch (Exception e) {
							// 记录日志-debug
							if (Util.debugLog.isDebugEnabled()) {
								Util.debugLog.debug("alipay.notify.json;下单失败！异常"+e);
							}
						}
					}else {
						// 记录日志-debug
						if (Util.debugLog.isDebugEnabled()) {
							Util.debugLog.debug("alipay.notify.json;下单失败！异常-trade_status="+trade_status);
						}
						response.getWriter().write("success");
					}
					//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					//////////////////////////////////////////////////////////////////////////////////////////
				}else{//验证失败
					// 记录日志-debug
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("alipay.notify.json;验证失败！异常-params="+params.toString());
					}
					System.out.println("fail");
					response.getWriter().write("fail");
				}
		return null;
	}
	
	/**
	 * 
	 * @param hs
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/withNotify.json", name = "阿里支付同步回调")
	@ResponseBody
	public void withNotify(HttpSession hs, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			System.out.println("requestParams:"+requestParams.toString());
			String name = (String) iter.next();
			//	continue;
			//	pAction 参数是内部系统的参数要去掉。不能传给params检验
			String[] values = (String[]) requestParams.get(name);
			String valueStr = ""; 
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			/*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
			params.put(name, valueStr);
		}
		// 记录日志-debug
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("alipay.withNotify.json；-params="+params.toString());
		}
		
		String out_trade_no = request.getParameter("out_trade_no");
		//支付宝交易号
		String trade_no = request.getParameter("trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		
		// 记录日志-debug
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("alipay.withNotify.json；-out_trade_no="+out_trade_no+";trade_no="+trade_no+";trade_status"+trade_status);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//TRADE_SUCCESS
		//计算得出通知验证结果
		if(!params.isEmpty()){
			boolean verify_result = AlipayNotify.verify(params);
			if (verify_result) {//验证成功
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("alipay.withNotify.json；-verify_result="+verify_result);
				}
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					// 记录日志-debug
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("alipay.withNotify.json；-验证成功trade_status="+trade_status.toString());
					}
				}
				System.out.println("验证成功<br />");
			}else{
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("alipay.withNotify.json；验证失败-verify_result="+verify_result);
				}
			}
		}else{
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("alipay.withNotify.json；验证失败");
			}
		}
	}
}