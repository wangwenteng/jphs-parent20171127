package com.jinpaihushi.jphs.audit.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.audit.model.Audit;
import com.jinpaihushi.jphs.audit.service.AuditService;
import com.jinpaihushi.jphs.department.model.Department;
import com.jinpaihushi.jphs.department.service.DepartmentService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;
import com.github.pagehelper.Page;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 08:54:36
 * @version 1.0
 */
@Controller
@RequestMapping(name = "审核管理", path = "/audit")
public class AuditController extends BaseController<Audit> {

	@Autowired
	private AuditService auditService;

	@Autowired
	private NurseService nurseService;
	@Autowired
	private DepartmentService departmentService;

	@Override
	protected BaseService<Audit> getService() {
		return auditService;
	}

	@RequestMapping(name = "待审核护士列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Nurse nurse, @RequestParam(value = "page", defaultValue = "1", required = false) Integer p,
			@RequestParam(value = "rows", defaultValue = "10", required = false) Integer n) {
		startPage(p, n);
		nurse.setStatus(0);
		List<Nurse> list = nurseService.getNurseDetail(nurse);
		PageInfos<Nurse> pageInfo = new PageInfos<Nurse>(list, req);
		// 科室列表
		List<Department> department = departmentService.query(null);
		modelMap.put("department", department);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "nurse/audit/list";
	}

	@RequestMapping(name = "跳转到审核页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Nurse nurse) {
		List<Department> department = departmentService.query(null);
		modelMap.put("department", department);
		Nurse result = nurseService.getNurseDetail(nurse).get(0);
		modelMap.put("nurse", result);
		modelMap.put("addressName", result.getAddress().split("-")[3]);
		Audit audit = new Audit();
		audit.setCreatorId(nurse.getId());
		List<Audit> list = auditService.list(audit);
		modelMap.put("audit", list);
		return "nurse/audit/edit";
	}

	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "nurse/audit/edit";
	}

	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Nurse nurse) {
		List<Department> department = departmentService.query(null);
		modelMap.put("department", department);
		Nurse result = nurseService.getNurseDetail(nurse).get(0);
		modelMap.put("nurse", result);
		modelMap.put("addressName", result.getAddress().split("-")[3]);
		// Audit audit = auditService.loadById(id);
		// modelMap.put("audit", audit);
		return "nurse/audit/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Audit audit) {
		SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");
		if (audit.getAudit() == 1) {
			audit.setId(UUIDUtils.getId());
			audit.setRemark("审核通过");
			audit.setAuditTime(new Date());
			audit.setCreateTime(new Date());
			audit.setAuditUserId(systemUser.getId());
			audit.setAuditUserName(systemUser.getName());
			String insert = auditService.insert(audit);
			if (insert.length() > 0) {
				Nurse nurse = new Nurse();
				nurse.setId(audit.getCreatorId());
				nurse.setStatus(1);
				boolean b = nurseService.update(nurse);
				if (b == false) {
					// 跳转到错误页
					return "redirect:/audit/err.jhtml";
				}
			}
		} else {
			audit.setId(UUIDUtils.getId());
			audit.setAudit(0);
			audit.setAuditTime(new Date());
			audit.setAuditUserId(systemUser.getId());
			audit.setAuditUserName(systemUser.getName());
			audit.setCreateTime(new Date());
			String result = auditService.insert(audit);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/audit/err.jhtml";
			}
		}
		return "redirect:/audit/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {

		boolean b = auditService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/audit/err.jhtml";
		}
		return "redirect:/audit/index.jhtml";
	}

}
