package com.jinpaihushi.jphs.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleGoodsService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.order.service.OrderServiceService;
<<<<<<< HEAD
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseOrder")
public class NurseOrderController {

	@Autowired
	private UserService userService;
	@Autowired
	private NurseService nurseService;
	@Autowired
	private JobtitleGoodsService jobtitleGoodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderServiceService orderServiceService;
	@Autowired
	private ServiceImagesService serviceImagesService;
	
	@ResponseBody
	@RequestMapping(name="完成服务",path="/fulfilService")
	public byte[] fulfilService(HttpServletRequest req, HttpServletResponse resp,String osId, String authCode,String orderId,String orderGoodsId,User user){
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId+" token="+token);
			}
			// 查空
			if (StringUtils.isEmpty(orderId) 
					|| StringUtils.isEmpty(orderGoodsId) 
						|| StringUtils.isEmpty(user.getId())
							|| StringUtils.isEmpty(osId)
								|| StringUtils.isEmpty(user.getPassword())
									|| StringUtils.isEmpty(user.getPhone())
										|| StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}

			Order order = new Order();
			order.setAcceptUserId(user.getId());
			order.setId(orderId);
			order.setSchedule(3);
			order.setStatus(1);
			order = orderService.load(order);
			if(order == null ){
				return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
			}
			//	完成本次服务
			com.jinpaihushi.jphs.order.model.OrderService os_one = new com.jinpaihushi.jphs.order.model.OrderService();
			os_one.setStatus(1);
			os_one.setOrderId(orderId);
			os_one.setNurseId(user.getId());
			os_one.setSchedule(1);
			os_one.setId(osId);
			com.jinpaihushi.jphs.order.model.OrderService one_os = orderServiceService.load(os_one);
				if(one_os == null){
					return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
				}
			com.jinpaihushi.jphs.order.model.OrderService one_os_up = new com.jinpaihushi.jphs.order.model.OrderService();
			one_os_up.setSchedule(2);
			one_os_up.setId(osId);
			one_os_up.setStartServiceTime(new Date());
			boolean one_os_up_s = orderServiceService.update(one_os_up);
			if(one_os_up_s){
				return JSONUtil.toJSONResult(1, "订单完成", null);
			}else{
				return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
			}
			/*com.jinpaihushi.jphs.order.model.OrderService os_total = new com.jinpaihushi.jphs.order.model.OrderService();
			os_total.setOrderId(order.getId());
			os_total.setSchedule(0);
			os_total.setNurseId(user.getId());
			os_total.setStatus(1);
			List<com.jinpaihushi.jphs.order.model.OrderService> order_os_list = orderServiceService.list(os_total);
			*/
			
			
			} catch (Exception e) {
				// 记录日志-fail
				Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId, e);
			}
			
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name="护士开始服务",path="/startService")
	public byte[] startService( HttpServletRequest req, HttpServletResponse resp,String osId, String authCode,String orderId,String orderGoodsId,User user){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId+" token="+token);
			}
			// 查空
			if (StringUtils.isEmpty(orderId) 
					|| StringUtils.isEmpty(orderGoodsId) 
						|| StringUtils.isEmpty(user.getId())
							|| StringUtils.isEmpty(osId)
								|| StringUtils.isEmpty(user.getPassword())
									|| StringUtils.isEmpty(user.getPhone())
										|| StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}
			
			Order order = new Order();
			order.setAcceptUserId(user.getId());
			order.setId(orderId);
			order.setSchedule(2);
			order.setStatus(1);
			order = orderService.load(order);
			if(order == null ){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}
			Order order_start = new Order();
			order_start.setAcceptUserId(user.getId());
			order_start.setId(orderId);
			order_start.setSchedule(3);
			boolean or_start = orderService.update(order_start);
			if(!or_start){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}
			//	该订单一共几次服务
			com.jinpaihushi.jphs.order.model.OrderService os_total = new com.jinpaihushi.jphs.order.model.OrderService();
			os_total.setStatus(1);
			os_total.setOrderId(orderId);
			os_total.setNurseId(user.getId());
			os_total.setSchedule(0);
			os_total.setId(osId);
			// 查询该次订单
			com.jinpaihushi.jphs.order.model.OrderService one_os = orderServiceService.load(os_total);
			if(one_os == null){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}
			com.jinpaihushi.jphs.order.model.OrderService one_os_up = new com.jinpaihushi.jphs.order.model.OrderService();
			one_os_up.setSchedule(1);
			one_os_up.setId(osId);
			one_os_up.setStartServiceTime(new Date());
			boolean one_os_up_s = orderServiceService.update(one_os_up);
			if(!one_os_up_s){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}else{
				return JSONUtil.toJSONResult(1, "开始成功", null);
			}
			
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId, e);
		}
		
		return null;
	}
	
	/**
	 * 开始出发-马上上门
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @param user
	 * @param orderId
	 * @return
	 */
	public byte[] atOnceService(HttpServletRequest req, HttpServletResponse resp,String authCode,User user,String orderId,String osId){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone()+" token="+token);
			}
			
			// 查空
			if (StringUtils.isEmpty(user.getId())
					||StringUtils.isEmpty(user.getPhone())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getType().toString())
								|| StringUtils.isEmpty(authCode)
									|| StringUtils.isEmpty(orderId)/*
										|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			
			//			查询订单详情-
			Order order = new Order();
			order.setId(orderId);
			order.setAcceptUserId(user.getId());
//			order.setSchedule(2);
			order.setStatus(1);
			order = orderService.load(order);
			if(order == null){
				return JSONUtil.toJSONResult(0, "查询详情失败", null);
			}
			com.jinpaihushi.jphs.order.model.OrderService os = new com.jinpaihushi.jphs.order.model.OrderService();
			os.setId(osId);
			os.setOrderId(orderId);
			os.setNurseId(user.getId());
			os.setSchedule(0);
			os.setStatus(1);
			os = orderServiceService.load(os);
			if(os == null || os.getSetoutTime() == null || os.getSetoutTime().toString().equals("")){
				return JSONUtil.toJSONResult(0, "查询详情失败", null);
			}
			com.jinpaihushi.jphs.order.model.OrderService os_up = new com.jinpaihushi.jphs.order.model.OrderService();
			os_up.setId(os.getId());
			os_up.setSetoutTime(new Date());
			boolean b = orderServiceService.update(os_up);
			if(b)
				return JSONUtil.toJSONResult(1, "更新成功", "");
			else
				return JSONUtil.toJSONResult(0, "更新状态失败，请刷新重试", "");
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
	}
	
	/**
	 * 订单详情
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @param user
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="订单详情",path="/orderDetails")
	public byte[] orderDetails( HttpServletRequest req, HttpServletResponse resp,String authCode,User user,String orderId){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone()+" token="+token);
			}
			
			// 查空
			if (StringUtils.isEmpty(user.getId())
					||StringUtils.isEmpty(user.getPhone())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getType().toString())
								|| StringUtils.isEmpty(authCode)
									|| StringUtils.isEmpty(orderId)/*
										|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			//	查询订单详情-
			Order order = new Order();
			order.setId(orderId);
			order.setAcceptUserId(user.getId());
			order = orderService.nurseOrderDetails(order);
			if(order == null){
				return JSONUtil.toJSONResult(0, "查询详情失败", null);
			}
			ServiceImages serviceImages = new ServiceImages();
			serviceImages.setSourceId(order.getId());
			List<ServiceImages> serviceImagesLists = serviceImagesService.list(serviceImages);
			if(serviceImagesLists != null){
				order.setServiceOrderImages(serviceImagesLists);
			}
			
			return JSONUtil.toJSONResult(1, "查询成功", order);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name="订单列表",path = "/list.json")
	public byte[] orderList( HttpServletRequest req, HttpServletResponse resp,Integer schedule, String authCode,User user, Integer p, Integer n){
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone());
			}
			// 查空
			if (StringUtils.isEmpty(user.getId())
					||StringUtils.isEmpty(user.getPhone())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getType().toString())
							|| StringUtils.isEmpty(authCode)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			/*Order order = new Order();
			order.setSchedule(schedule);
			order.setAcceptUserId(user.getId());
			order.setStatus(1);*/
			Map<String,Object> map_o = new HashMap<String,Object>();
			map_o.put("userId", user.getId());
			map_o.put("schedule", schedule);
			PageHelper.startPage(p, n);
			List<Map<String,Object>> re_map = orderService.nurseOrderList(map_o);
			PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(re_map);
			
			return JSONUtil.toJSONResult(1, "成功", page);
=======
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseOrder")
public class NurseOrderController {

	@Autowired
	private UserService userService;
	@Autowired
	private NurseService nurseService;
	@Autowired
	private JobtitleGoodsService jobtitleGoodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderServiceService orderServiceService;
	
	@ResponseBody
	@RequestMapping(name="完成服务",path="/fulfilService")
	public byte[] fulfilService(HttpServletRequest req, HttpServletResponse resp,String osId, String authCode,String orderId,String orderGoodsId,User user){
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId+" token="+token);
			}
			// 查空
			if (StringUtils.isEmpty(orderId) 
					|| StringUtils.isEmpty(orderGoodsId) 
						|| StringUtils.isEmpty(user.getId())
							|| StringUtils.isEmpty(osId)
								|| StringUtils.isEmpty(user.getPassword())
									|| StringUtils.isEmpty(user.getPhone())
										|| StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}

			Order order = new Order();
			order.setAcceptUserId(user.getId());
			order.setId(orderId);
			order.setSchedule(3);
			order.setStatus(1);
			order = orderService.load(order);
			if(order == null ){
				return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
			}
			//	完成本次服务
			com.jinpaihushi.jphs.order.model.OrderService os_one = new com.jinpaihushi.jphs.order.model.OrderService();
			os_one.setStatus(1);
			os_one.setOrderId(orderId);
			os_one.setNurseId(user.getId());
			os_one.setSchedule(1);
			os_one.setId(osId);
			com.jinpaihushi.jphs.order.model.OrderService one_os = orderServiceService.load(os_one);
				if(one_os == null){
					return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
				}
			com.jinpaihushi.jphs.order.model.OrderService one_os_up = new com.jinpaihushi.jphs.order.model.OrderService();
			one_os_up.setSchedule(2);
			one_os_up.setId(osId);
			one_os_up.setStartServiceTime(new Date());
			boolean one_os_up_s = orderServiceService.update(one_os_up);
			if(one_os_up_s){
				return JSONUtil.toJSONResult(1, "订单完成", null);
			}else{
				return JSONUtil.toJSONResult(0, "订单完成失败，请刷新重试", null);
			}
			/*com.jinpaihushi.jphs.order.model.OrderService os_total = new com.jinpaihushi.jphs.order.model.OrderService();
			os_total.setOrderId(order.getId());
			os_total.setSchedule(0);
			os_total.setNurseId(user.getId());
			os_total.setStatus(1);
			List<com.jinpaihushi.jphs.order.model.OrderService> order_os_list = orderServiceService.list(os_total);
			*/
			
			
			} catch (Exception e) {
				// 记录日志-fail
				Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId, e);
			}
			
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name="护士开始服务",path="/startService")
	public byte[] startService( HttpServletRequest req, HttpServletResponse resp,String osId, String authCode,String orderId,String orderGoodsId,User user){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId+" token="+token);
			}
			// 查空
			if (StringUtils.isEmpty(orderId) 
					|| StringUtils.isEmpty(orderGoodsId) 
						|| StringUtils.isEmpty(user.getId())
							|| StringUtils.isEmpty(osId)
								|| StringUtils.isEmpty(user.getPassword())
									|| StringUtils.isEmpty(user.getPhone())
										|| StringUtils.isEmpty(token)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}
			
			Order order = new Order();
			order.setAcceptUserId(user.getId());
			order.setId(orderId);
			order.setSchedule(2);
			order.setStatus(1);
			order = orderService.load(order);
			if(order == null ){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}
			Order order_start = new Order();
			order_start.setAcceptUserId(user.getId());
			order_start.setId(orderId);
			order_start.setSchedule(3);
			boolean or_start = orderService.update(order_start);
			if(!or_start){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}
			//	该订单一共几次服务
			com.jinpaihushi.jphs.order.model.OrderService os_total = new com.jinpaihushi.jphs.order.model.OrderService();
			os_total.setStatus(1);
			os_total.setOrderId(orderId);
			os_total.setNurseId(user.getId());
			os_total.setSchedule(0);
			os_total.setId(osId);
			// 查询该次订单
			com.jinpaihushi.jphs.order.model.OrderService one_os = orderServiceService.load(os_total);
			if(one_os == null){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}
			com.jinpaihushi.jphs.order.model.OrderService one_os_up = new com.jinpaihushi.jphs.order.model.OrderService();
			one_os_up.setSchedule(1);
			one_os_up.setId(osId);
			one_os_up.setStartServiceTime(new Date());
			boolean one_os_up_s = orderServiceService.update(one_os_up);
			if(!one_os_up_s){
				return JSONUtil.toJSONResult(0, "订单执行失败，请刷新重试", null);
			}else{
				return JSONUtil.toJSONResult(1, "开始成功", null);
			}
			
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " orderId=" + orderId + " orderGoodsId=" + orderGoodsId, e);
		}
		
		return null;
	}
	
	/**
	 * 开始出发-马上上门
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @param user
	 * @param orderId
	 * @return
	 */
	public byte[] atOnceService(HttpServletRequest req, HttpServletResponse resp,String authCode,User user,String orderId,String osId){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone()+" token="+token);
			}
			
			// 查空
			if (StringUtils.isEmpty(user.getId())
					||StringUtils.isEmpty(user.getPhone())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getType().toString())
								|| StringUtils.isEmpty(authCode)
									|| StringUtils.isEmpty(orderId)/*
										|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			
			//			查询订单详情-
			Order order = new Order();
			order.setId(orderId);
			order.setAcceptUserId(user.getId());
//			order.setSchedule(2);
			order.setStatus(1);
			order = orderService.load(order);
			if(order == null){
				return JSONUtil.toJSONResult(0, "查询详情失败", null);
			}
			com.jinpaihushi.jphs.order.model.OrderService os = new com.jinpaihushi.jphs.order.model.OrderService();
			os.setId(osId);
			os.setOrderId(orderId);
			os.setNurseId(user.getId());
			os.setSchedule(0);
			os.setStatus(1);
			os = orderServiceService.load(os);
			if(os == null || os.getSetoutTime() == null || os.getSetoutTime().toString().equals("")){
				return JSONUtil.toJSONResult(0, "查询详情失败", null);
			}
			com.jinpaihushi.jphs.order.model.OrderService os_up = new com.jinpaihushi.jphs.order.model.OrderService();
			os_up.setId(os.getId());
			os_up.setSetoutTime(new Date());
			boolean b = orderServiceService.update(os_up);
			if(b)
				return JSONUtil.toJSONResult(1, "更新成功", "");
			else
				return JSONUtil.toJSONResult(0, "更新状态失败，请刷新重试", "");
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
	}
	
	/**
	 * 订单详情
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @param user
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="订单详情",path="/orderDetails")
	public byte[] orderDetails( HttpServletRequest req, HttpServletResponse resp,String authCode,User user,String orderId){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone()+" token="+token);
			}
			
			// 查空
			if (StringUtils.isEmpty(user.getId())
					||StringUtils.isEmpty(user.getPhone())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getType().toString())
								|| StringUtils.isEmpty(authCode)
									|| StringUtils.isEmpty(orderId)/*
										|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			//	查询订单详情-
			Order order = new Order();
			order.setId(orderId);
			order.setAcceptUserId(user.getId());
			order = orderService.nurseOrderDetails(order);
			if(order == null){
				return JSONUtil.toJSONResult(0, "查询详情失败", null);
			}
			return JSONUtil.toJSONResult(1, "查询成功", order);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseOrder.startService.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name="订单列表",path = "/list.json")
	public byte[] orderList( HttpServletRequest req, HttpServletResponse resp,Integer schedule, String authCode,User user, Integer p, Integer n){
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone());
			}
			// 查空
			if (StringUtils.isEmpty(user.getId())
					||StringUtils.isEmpty(user.getPhone())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getType().toString())
							|| StringUtils.isEmpty(authCode)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			/*Order order = new Order();
			order.setSchedule(schedule);
			order.setAcceptUserId(user.getId());
			order.setStatus(1);*/
			Map<String,Object> map_o = new HashMap<String,Object>();
			map_o.put("userId", user.getId());
			map_o.put("schedule", schedule);
			PageHelper.startPage(p, n);
			List<Map<String,Object>> re_map = orderService.nurseOrderList(map_o);
			PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(re_map);
			
			return JSONUtil.toJSONResult(0, "成功", page);
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
			
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseOrder.list.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
		
	}
	
	/**
	 * 护士抢单
	 * 
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode			//	验证
	 * @param orderId			//	订单ID
	 * @param orderGoodsId		//	商品ID
	 * @param user				//	护士信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="护士抢单",path="/grab.json")
	public byte[] getOrder( HttpServletRequest req, HttpServletResponse resp, String authCode,String orderId,String orderGoodsId,User user){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.grabOrder.json,user"+user.getId()+" user="+user.getPhone()+" authCode="+authCode+" user.getType()="+user.getType()+" token"+token);
			}
			
			// 查空
			if(StringUtils.isEmpty(orderId)
				||StringUtils.isEmpty(user.getId())
					||StringUtils.isEmpty(user.getPhone())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getType().toString())
								||StringUtils.isEmpty(orderGoodsId)
									||StringUtils.isEmpty(orderId)/*
								||StringUtils.isEmpty(token)*/){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			
			User user_p = new User();
			user_p.setId(user.getId());
			user_p.setPhone(user.getPhone());
			user_p.setPassword(user.getPassword());
			user_p.setType(user.getType());
			// 1.根据 name，password,type查询完整信息
			user = userService.findUser(user_p);
			
			// 2.错误		查询用户信息为空弄
			if (user == null) {
				return JSONUtil.toJSONResult(0, "用户名或密码不正确，请重新登录！", null);
			}
			
			int tid = 0; // type
			try {
				tid = Integer.valueOf(user.getType());
			} catch (NumberFormatException e) {
				return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
			}
			
			// 3.错误		判断用户是否是护士
			if (tid != 0) {
				return JSONUtil.toJSONResult(0, "用户不是护士！", null);
			}
			
			Nurse nurse = new Nurse();
			nurse.setCreatorId(user.getId());
			nurse = nurseService.load(nurse);
			// 4.错误		用户未申请审核护士资格
			if (nurse == null) {
				return JSONUtil.toJSONResult(0, "未申请护士资格！", null);
			}
			// 5.错误		已封号
			if (nurse.getActive() !=1 ) {
				return JSONUtil.toJSONResult(0, "护士已被封号，请联系客服确认！", null);
			}
			// 6.错误		未通过审核
			if (nurse.getStatus() !=1 ) {
				return JSONUtil.toJSONResult(0, "护士未通过审核！", null);
			}
			Map<String ,Object> jobtitleGoods = new HashMap<String ,Object>();
			jobtitleGoods.put("goodsId", orderGoodsId);
			jobtitleGoods.put("nurseId", user.getId());
			Integer i = jobtitleGoodsService.getJobtitleCount(jobtitleGoods);
			// 7.错误		护士权限不能接此类型订单
			if (i == 0) {
				return JSONUtil.toJSONResult(0, "护士权限未达到！", null);
			}
			Order order = new Order();
			order.setId(orderId);
			order.setSchedule(1);
			order.setStatus(1);
			order = orderService.load(order);
			// 8.错误		该订单已被抢走
			if (order == null) {
				return JSONUtil.toJSONResult(0, "该订单已被抢走！", null);
			}
			Order order_up = new Order();
			order_up.setId(orderId);
			order_up.setSchedule(2);
			order_up.setAcceptTime(new Date());
			order_up.setAcceptUserId(user.getId());
			boolean order_up_falg = orderService.update(order_up);
			// 9.错误		接单失败
			if (!order_up_falg) {
				return JSONUtil.toJSONResult(0, "接单失败！", null);
			}
			return JSONUtil.toJSONResult(1, "枪单成功！", null);
		} catch (Exception e){
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseOrder.grabOrder.json,user"+user.getId()+" user="+user.getPhone()+" authCode="+authCode+" e="+e);
			}
		}
		return null;
	}
	
}
