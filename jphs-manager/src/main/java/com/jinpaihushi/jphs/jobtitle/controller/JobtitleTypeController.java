package com.jinpaihushi.jphs.jobtitle.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleTypeService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:01
 * @version 1.0
 */
@Controller
@RequestMapping(name = "职称类型管理", path = "/jobtitle/type")
public class JobtitleTypeController extends BaseController<JobtitleType> {

    @Autowired
    private JobtitleTypeService jobtitleTypeService;

    @Override
    protected BaseService<JobtitleType> getService() {
        return jobtitleTypeService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JobtitleType jobtitleType, Integer p, Integer n) {
        startPage(p, n);
        jobtitleType.setOrderby("create_time DESC");
        Page<JobtitleType> list = jobtitleTypeService.query(jobtitleType);
        PageInfos<JobtitleType> pageInfo = new PageInfos<JobtitleType>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "nurse/jobtitle/type/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JobtitleType jobtitleType = jobtitleTypeService.loadById(id);
        modelMap.put("jobtitleType", jobtitleType);
        return "nurse/jobtitle/type/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "nurse/jobtitle/type/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JobtitleType jobtitleType = jobtitleTypeService.loadById(id);
        modelMap.put("jobtitleType", jobtitleType);
        return "nurse/jobtitle/type/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JobtitleType jobtitleType) {
        SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");
        if (jobtitleType.getId() != null && !jobtitleType.getId().equals("")) {
            boolean b = jobtitleTypeService.update(jobtitleType);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jobtitle/type/err.jhtml";
            }
        }
        else {
            jobtitleType.setId(UUIDUtils.getId());
            jobtitleType.setCreateTime(new Date());
            jobtitleType.setCreatorId(systemUser.getId());
            jobtitleType.setCreatorName(systemUser.getName());
            jobtitleType.setStatus(0);
            String result = jobtitleTypeService.insert(jobtitleType);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jobtitle/type/err.jhtml";
            }
        }
        return "redirect:/jobtitle/type/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JobtitleType jobtitleType) {

        boolean b = jobtitleTypeService.update(jobtitleType);
        if (!b) {
            // 跳转到错误页
            return "redirect:/jobtitle/type/err.jhtml";
        }

        return "redirect:/jobtitle/type/index.jhtml";
    }

    @ResponseBody
    @RequestMapping(name = "验证名称", path = "/checkName.json")
    public int checkName(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JobtitleType jobtitleType) {
        int b = jobtitleTypeService.checkName(jobtitleType);
        return b;
    }

}
