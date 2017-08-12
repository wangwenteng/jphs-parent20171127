package com.jinpaihushi.jphs.product.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.sysversion.model.Sysversion;
import com.jinpaihushi.jphs.sysversion.service.SysversionService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/app")
public class AppIndexController {

	@Autowired
	private SysversionService sysversionService;

	@ResponseBody
	@RequestMapping(name = "app版本号", path = "/SysVersion.json")
	public byte[] appSysVersion(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String authCode,
			String versionNumber, Integer deviceType) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("app.SysVersion.json,deviceType=" + deviceType + " authCode=" + authCode);
			}
			// 查空
			if (deviceType == null) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Map<String, Object> re_map = new HashMap<String, Object>();

			try {
				Sysversion sysversion = sysversionService.appVersion(deviceType);
				if (sysversion != null) {
					if (versionNumber.equals(sysversion.getVersion())) {
						re_map.put("version", false);
					} else {
						re_map.put("version", true);
						re_map.put("content", sysversion);
					}
				} else {
					return JSONUtil.toJSONResult(0, "操作失败,参数错误！", "");
				}
			} catch (Exception e) {
				return JSONUtil.toJSONResult(0, "操作失败！", "");
			}
			return JSONUtil.toJSONResult(1, "操作成功！", re_map);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("app.index.json,deviceType=" + deviceType + " authCode=" + authCode, e);
		}
		return null;

	}

}
