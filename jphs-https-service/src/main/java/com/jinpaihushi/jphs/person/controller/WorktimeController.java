package com.jinpaihushi.jphs.person.controller;

import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jinpaihushi.jphs.nurse.model.NurseWorktime;
import com.jinpaihushi.jphs.nurse.service.NurseWorktimeService;
import com.jinpaihushi.jphs.user.service.UserWorktimeService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 工作时间
 * 
 * @author 王文腾
 *
 */
@Controller
@RequestMapping("/worktime")
public class WorktimeController {
	@Autowired
	private UserWorktimeService userWorktimeService;
	@Autowired
	private NurseWorktimeService nurseWorktimeService;

	/**
	 * 删除用户已过时间的记录，插入新的记录
	 * 
	 * @param hs
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(path = "/updateUserWorkTime.json", name = "更新用户上门时间")
	@ResponseBody
	public byte[] updateUserWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.findWorkTime.json ");
			}
			int i = userWorktimeService.updateUserWorkTime();
			if (i < 1) {
				return JSONUtil.toJSONResult(0, "操作失败！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", null);
		} catch (Exception e) {
			Util.failLog.error("user.findWorkTime.json", e);
		}
		return null;
	}

	@RequestMapping(path = "/updateNurseWorkTime.json", name = "更新护士空闲时间")
	@ResponseBody
	public byte[] updateNurseWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
		ServletInputStream is;
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.findWorkTime.json ");
			}
			is = req.getInputStream();
			int nRead = 1;
			int nTotalRead = 0;
			byte[] bytes = new byte[10240];
			while (nRead > 0) {
				nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
				if (nRead > 0)
					nTotalRead = nTotalRead + nRead;
			}
			String str = new String(bytes, 0, nTotalRead, "utf-8");
			is.close();
			System.out.println(str);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str);
			System.out.println(JSONObject.toJSONString(jsonObject.get("result")));
			List<NurseWorktime> list = JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("result")),
					NurseWorktime.class);
			boolean i = false;
			for (NurseWorktime nurseWorktime : list) {
				i = nurseWorktimeService.update(nurseWorktime);
			}
			if (i) {
				return JSONUtil.toJSONResult(0, "操作失败！", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", null);
		} catch (Exception e) {
			Util.failLog.error("user.findWorkTime.json", e);
		}
		return null;
	}

	@RequestMapping(path = "/findNurseWorkTime.json", name = "选择上门时间")
	@ResponseBody
	public byte[] findWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
			Boolean isNextWeek) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.findWorkTime.json userId=" + userId + " isNextWeek=" + isNextWeek);
			}
			if (StringUtils.isEmpty(userId) || isNextWeek == null) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<NurseWorktime> result1 = nurseWorktimeService.findWorkTime(userId, isNextWeek);
			//nurseWorktimeService.insertNurseWorkTime(userId);
			return JSONUtil.toJSONResult(1, "操作成功！", result1);
		} catch (Exception e) {
			Util.failLog.error("user.findWorkTime.json userId=" + userId + " isNextWeek=" + isNextWeek, e);
		}
		return null;
	}

}
