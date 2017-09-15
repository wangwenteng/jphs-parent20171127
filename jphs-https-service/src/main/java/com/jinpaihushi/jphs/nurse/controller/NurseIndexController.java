package com.jinpaihushi.jphs.nurse.controller;

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
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.activity.model.Advertising;
import com.jinpaihushi.jphs.activity.service.AdvertisingService;
import com.jinpaihushi.jphs.column.model.ColumnService;
import com.jinpaihushi.jphs.column.service.ColumnServiceService;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseindex")
public class NurseIndexController extends BaseController<Nurse> {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ColumnServiceService columnServiceService;
	
	@Autowired
	private AdvertisingService advertisingService;
	
	@Autowired
	private InformationService informationService;
	
	@Override
	protected BaseService<Nurse> getService() {
	    // TODO Auto-generated method stub
	    return null;
	}
	
	/**
	 * 金牌康护-护士端首页-抢单列表
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="护士首页",path="/spearorder.json")
	public byte[] spearorder( HttpServletRequest req, HttpServletResponse resp,String authCode,User user,Integer p,Integer n){

		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseindex.neworder.json,user="+user.getPhone()+" authCode="+authCode+" token="+token);
			}
			// 查空
			if(StringUtils.isEmpty(user.getId())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getPhone())/*
								||StringUtils.isEmpty(token)*/){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}

			//	抢单列表
			Map<String , Object> q_o_map = new HashMap<String , Object>();
			q_o_map.put("schedule", 1);
			q_o_map.put("status", 1);
			PageHelper.startPage(p, n);
			List<Map<String , Object>> q_order_list = orderService.getOrderGoodsList(q_o_map);
			PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(q_order_list);
			
			return JSONUtil.toJSONResult(1, "成功", page);
		} catch (Exception e) {
			// 记录日志-fail
			Util.debugLog.debug("nurseindex.neworder.json",e);
		}
		return null;
	}
	
	/**
	 * 金牌康护-护士端首页-最新带服务
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="护士首页",path="/neworder.json")
	public byte[] neworder( HttpServletRequest req, HttpServletResponse resp,String authCode,User user){

		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseindex.neworder.json, user="+user.getPhone()+" authCode="+authCode+" token="+token);
			}
			// 查空
			if(StringUtils.isEmpty(user.getId())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getPhone())/*
								||StringUtils.isEmpty(token)*/){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}
			//	最新待服务订单
			Map<String , Object> o_map = new HashMap<String , Object>();
			o_map.put("acceptUserId", user.getId());
			o_map.put("schedule", 2);
			o_map.put("status", 1);
			o_map.put("os_schedule", 0);
			List<Map<String , Object>> order_list = orderService.getUptoDataGoods(o_map);
			
			return JSONUtil.toJSONResult(1, "成功", order_list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.debugLog.debug("nurseindex.neworder.json",e);
		}
		return null;
	}
	
	/**
	 * 金牌康护-护士端首页-功能按钮
	 * @param hs
	 * @param req
	 * @param resp
	 * @param authCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="护士首页",path="/column.json")
	public byte[] column( HttpServletRequest req, HttpServletResponse resp,String columnId, String authCode){

		try{
			/*String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}*/
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseindex.column.json,columnId"+columnId+" authCode="+authCode);
			}
			// 查空
			if(StringUtils.isEmpty(columnId)){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/

			//	功能分类
			ColumnService cs = new ColumnService();
			cs.setColumnId(columnId);
			cs.setStatus(1);
			List<ColumnService> columnServiceList = columnServiceService.list(cs);
			
			return JSONUtil.toJSONResult(1, "成功", columnServiceList);
		} catch (Exception e) {
			// 记录日志-fail
			Util.debugLog.debug("nurseindex.column.json",e);
		}
		return null;
	}
	
    /**
     * 金牌康护-护士端首页
     * @param hs
     * @param req
     * @param resp
     * @param authCode
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "护士首页", path = "/index.json")
    public byte[] getIndex(HttpServletRequest req, HttpServletResponse resp, String columnId,
            String authCode, User user, Integer n, Integer p, String channelId) {

        try {
            String token = "";
           /* try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }*/
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseindex.index.json,columnId" + columnId + " user=" + user.getPhone()
                        + " authCode=" + authCode + " token=" + token);
            }
            // 查空
            if (StringUtils.isEmpty(columnId) || StringUtils.isEmpty(user.getId())
                    || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getPhone())/*
                                                           ||StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }/*
            if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/

            //	轮播图
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("atype", 1);
            map.put("stype", 1);
            List<Advertising> advertisingList = advertisingService.getCarouselFigure(map);

            //	功能分类
            ColumnService cs = new ColumnService();
            cs.setColumnId(columnId);
            cs.setStatus(1);
            List<ColumnService> columnServiceList = columnServiceService.list(cs);

            //	最新待服务订单
            Map<String, Object> o_map = new HashMap<String, Object>();
            o_map.put("acceptUserId", user.getId());
            o_map.put("schedule", 2);
            o_map.put("status", 1);
            o_map.put("os_schedule", 0);
            List<Map<String, Object>> order_list = orderService.getUptoDataGoods(o_map);

            //	抢单列表
            Map<String, Object> q_o_map = new HashMap<String, Object>();
            q_o_map.put("schedule", 1);
            q_o_map.put("status", 1);
            q_o_map.put("type", 1);
            List<Map<String, Object>> q_order_list = orderService.getOrderGoodsList(q_o_map);
            //   资讯集合
            Map<String, Object> query = new HashMap<>();
            query.put("channelId", channelId);
            query.put("top", 1);
            query.put("num", 5);
            List<Map<String, Object>> informationList = informationService.listapp(query);
            Map<String, Object> re_map = new HashMap<String, Object>();
            re_map.put("advertising", advertisingList);
            re_map.put("columnService", columnServiceList);
            re_map.put("myorder", order_list);
            re_map.put("information", informationList); //  资讯
            re_map.put("order", q_order_list);

            return JSONUtil.toJSONResult(1, "成功", re_map);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.debugLog.debug("nurseindex.index.json", e);
        }

        return null;
    }

}
