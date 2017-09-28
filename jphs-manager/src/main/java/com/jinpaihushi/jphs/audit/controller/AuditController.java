package com.jinpaihushi.jphs.audit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.audit.model.Audit;
import com.jinpaihushi.jphs.audit.service.AuditService;
import com.jinpaihushi.jphs.department.model.Department;
import com.jinpaihushi.jphs.department.service.DepartmentService;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

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
    private NurseJobtitleService nurseJobtitleService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    protected BaseService<Audit> getService() {
        return auditService;
    }

    @RequestMapping(name = "待审核护士列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            NurseJobtitle nurseJobtitle, Integer p, Integer n) {
        startPage(p, n);
        nurseJobtitle.setOrderby("nj.create_time DESC");
        List<NurseJobtitle> list = nurseJobtitleService.getNurseJobtitleDetail(nurseJobtitle);
        PageInfos<NurseJobtitle> pageInfo = new PageInfos<NurseJobtitle>(list, req);
        // 科室列表
        List<Department> department = departmentService.query(null);
        modelMap.put("department", department);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "nurse/audit/list";
    }

    @RequestMapping(name = "跳转到审核页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            NurseJobtitle nurseJobtitle) {
        List<Department> department = departmentService.query(null);
        modelMap.put("department", department);
        NurseJobtitle result = nurseJobtitleService.getNurseJobtitleDetails(nurseJobtitle);
        modelMap.put("nurseJobtitle", result);
        Audit audit = new Audit();
        audit.setCreatorId(result.getId());
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
            NurseJobtitle nurseJobtitle) {
        List<Department> department = departmentService.query(null);
        modelMap.put("department", department);
        NurseJobtitle result = nurseJobtitleService.getNurseJobtitleDetails(nurseJobtitle);
        modelMap.put("nurseJobtitle", result);
        Audit audit = new Audit();
        audit.setCreatorId(result.getId());
        List<Audit> list = auditService.list(audit);
        modelMap.put("audit", list);
        return "nurse/audit/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Audit audit, String nurseJobtitleId) {
        SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");
        if (audit.getAudit() == 1) {
            audit.setId(UUIDUtils.getId());
            if (StringUtils.isEmpty(audit.getRemark())) {
                audit.setRemark("审核通过");
            }
            audit.setAuditTime(new Date());
            audit.setStatus(0);
            audit.setCreateTime(new Date());
            audit.setAuditUserId(systemUser.getId());
            audit.setAuditUserName(systemUser.getName());
            int i = auditService.insertAudit(audit, nurseJobtitleId);
            if (i <= 0) {
                //更新护士状态
                // 跳转到错误页
                return "redirect:/audit/err.jhtml";
            }
        }
        else {
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
