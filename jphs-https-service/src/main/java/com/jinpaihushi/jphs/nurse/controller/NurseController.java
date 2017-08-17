package com.jinpaihushi.jphs.nurse.controller;

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

import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.price.service.PriceNurseService;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurse")
public class NurseController {
	@Autowired
	private WorktimeService worktimeService;
	@Autowired
	private PriceNurseService priceNurseService;
	@Autowired
	private NurseService nurseService;
	@RequestMapping(path = "/getHomepage.json", name = "用户端护士主页")
	@ResponseBody
	public byte[] getNurseList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String lon,String lat,String userId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurse.getHomepage.json userId=" + userId);
			}
			if (StringUtils.isEmpty(lon)||StringUtils.isEmpty(lat)||StringUtils.isEmpty(userId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			//护士基本信息
			Map<String, Object> query = new HashMap<>();
			query.put("userId", userId);
			query.put("lon", lon);
			query.put("lat", lat);
			List<Map<String, Object>> basicInfo= nurseService.getBasicInfo(query);
			//服务项目
			List<Map<String, Object>> serviceItems=priceNurseService.getServiceItems(userId);
			//工作时间
			List<Worktime> worktime=worktimeService.getNurseWorktime(userId);
			Map<String, Object> result = new HashMap<>();
			result.put("basicInfo", basicInfo);
			result.put("serviceItems", serviceItems);
			result.put("worktime", worktime);
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("nurse.getHomepage.json userId=" + userId , e);
		}
		return null;
	}
}
