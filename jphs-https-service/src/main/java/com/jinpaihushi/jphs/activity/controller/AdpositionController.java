package com.jinpaihushi.jphs.activity.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.adposition.service.AdpositionService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/adposition")
public class AdpositionController {
	@Autowired
	private AdpositionService adpositionService;

	@ResponseBody
	@RequestMapping(name = "广告位", path = "/index.json")
	public byte[] index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,Integer deviceType) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("adposition.index.json,deviceType=" + deviceType);
			}
			// 查空
			if (deviceType == null) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			// 广告位集合
			List<Map<String, Object>> adpositionList = adpositionService.getAppindex(deviceType);

			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", adpositionList);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("adposition.index.json,deviceType=" + deviceType, e);
		}
		return null;
	}
}
