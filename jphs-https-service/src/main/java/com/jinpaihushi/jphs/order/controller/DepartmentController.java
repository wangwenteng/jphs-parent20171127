package com.jinpaihushi.jphs.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.department.model.Department;
import com.jinpaihushi.jphs.department.service.DepartmentService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@RequestMapping(path = "/getDeptAll.json", name = "获取所有科室列表")
	@ResponseBody
	public byte[] getDeptAll(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("user.getSessionUser.json");
			}
			Department dept = new Department();
			dept.setStatus(0);
			List<Department> list = departmentService.list(dept);
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("user.getSessionUser.json", e);
		}
		return null;
	}
}
