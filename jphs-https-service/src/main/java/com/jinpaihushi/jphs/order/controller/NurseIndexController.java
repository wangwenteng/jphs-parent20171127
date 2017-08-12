package com.jinpaihushi.jphs.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.activity.model.Advertising;
import com.jinpaihushi.jphs.activity.service.AdvertisingService;
import com.jinpaihushi.jphs.column.model.ColumnService;
import com.jinpaihushi.jphs.column.service.ColumnServiceService;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseindex")
public class NurseIndexController {

	@Autowired
	private AdvertisingService advertisingService;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private ColumnServiceService columnServiceService;
	
	/**
	 * 金牌康护-护士端首页
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="护士首页",path="/index.json")
	public byte [] getIndex(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String columnId, String authCode,User user,Integer n,Integer p){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseindex.index.json,columnId"+columnId+" user="+user.getPhone()+" authCode="+authCode+" token="+token);
			}
			// 查空
			if(StringUtils.isEmpty(columnId)
					||StringUtils.isEmpty(user.getId())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getPhone())/*
								||StringUtils.isEmpty(token)*/){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}
			

			//	功能分类
			ColumnService cs = new ColumnService();
			cs.setColumnId(columnId);
			cs.setStatus(1);
			List<ColumnService> columnServiceList = columnServiceService.list(cs);

			//	最新待服务订单
			Map<String , Object> o_map = new HashMap<String , Object>();
			o_map.put("acceptUserId", user.getId());
			o_map.put("schedule", 2);
			o_map.put("status", 1);
			o_map.put("os_schedule", 0);
			List<Map<String , Object>> order_list = orderService.getUptoDataGoods(o_map);
			
			//	抢单列表
			Map<String , Object> q_o_map = new HashMap<String , Object>();
			q_o_map.put("schedule", 1);
			q_o_map.put("status", 1);
			PageHelper.startPage(p, n);
			List<Map<String , Object>> q_order_list = orderService.getOrderGoodsList(q_o_map);
			PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(q_order_list);
			
			Map<String ,Object> re_map = new HashMap<String ,Object>();
			re_map.put("columnService", columnServiceList);
			re_map.put("myorder", order_list);
			re_map.put("order", page);
			
			return JSONUtil.toJSONResult(1, "成功", re_map);
		} catch (Exception e) {
			// 记录日志-fail
			Util.debugLog.debug("nurseindex.index.json",e);
		}
		
		return null;
	}
	
}
