package com.jinpaihushi.jphs.activity.controller;

import java.util.Date;
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
import com.jinpaihushi.jphs.information.model.Information;
import com.jinpaihushi.jphs.information.model.InformationEvaluate;
import com.jinpaihushi.jphs.information.service.InformationEvaluateService;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/information")
public class InformationController {
	@Autowired
	private InformationService informationService;
	@Autowired
	private InformationEvaluateService informationEvaluateService;

	@ResponseBody
	@RequestMapping(name = "首页资讯列表", path = "/getHomeInformation.json")
	public byte[] getHomeInformation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
			String channelId) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("information.getHomeInformation.json,channelId=" + channelId);
			}
			// 查空
			if (StringUtils.isEmpty(channelId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}

			Map<String, Object> list = informationService.getHomeInformation(channelId);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("information.getHomeInformation.json,channelId=" + channelId, e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(name = "资讯列表", path = "/getInformationList.json")
	public byte[] getInformationList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String channelId,
			Integer page) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("information.getInformationList.json,channelId=" + channelId + " page=" + page);
			}
			// 查空
			if (StringUtils.isEmpty(channelId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Map<String, Object> list = informationService.getInformationList(channelId, page);
			if (list.size() <= 0) {
				return JSONUtil.toJSONResult(1, "暂无数据", null);
			}
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("information.getInformationList.json,channelId=" + channelId + " page=" + page, e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(name = "资讯详情", path = "/getInformationDetail.json")
	public byte[] getInformationDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String id) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("information.getInformationDetail.json,id=" + id);
			}
			// 查空
			if (StringUtils.isEmpty(id)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Information result = informationService.getInformationDetail(id);

			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("information.getInformationDetail.json,id=" + id, e);
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(name = "资讯评价", path = "/getInformationEvaluate.json")
	public byte[] getInformationEvaluate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String id,Integer p,String userId) {
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("information.getInformationEvaluate.json,id=" + id+" p="+p+" userId="+userId);
			}
			// 查空
			if (StringUtils.isEmpty(id)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			InformationEvaluate query = new InformationEvaluate();
			query.setInformationId(id);
			query.setStatus(1);
			query.setOrderby("create_time DESC");
			if(p==null) p=1;
			PageHelper.startPage(p,5);
			List<InformationEvaluate> list = informationEvaluateService.listInfo(query,userId);
			PageInfo<InformationEvaluate> page = new PageInfo<>(list);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端
			
			return JSONUtil.toJSONResult(1, "操作成功！", page);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("information.getInformationEvaluate.json,id=" + id+" p="+p+" userId="+userId, e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(name = "发表评论", path = "/sendEvaluate.json")
	public byte[] sendEvaluate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
			InformationEvaluate evaluate) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("information.sendEvaluate.json");
			}
			evaluate.setId(UUIDUtils.getId());
			evaluate.setCreateTime(new Date());
			evaluate.setStatus(0);
			String result = informationEvaluateService.insert(evaluate);

			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("information.sendEvaluate.json", e);
		}
		return null;
	}
}
