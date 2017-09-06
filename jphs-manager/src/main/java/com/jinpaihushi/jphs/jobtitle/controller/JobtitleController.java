package com.jinpaihushi.jphs.jobtitle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleService;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleTypeService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-06-29 16:54:10
 * @version 1.0
 */
@Controller
@RequestMapping(name = "职称管理", path = "/jobtitle")
public class JobtitleController extends BaseController<Jobtitle> {

    @Autowired
    private JobtitleService jobtitleService;

    @Autowired
    private JobtitleTypeService jobtitleTypeService;

    @Override
    protected BaseService<Jobtitle> getService() {
        return jobtitleService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Jobtitle jobtitle, Integer p, Integer n) {
        startPage(p, n);
        List<JobtitleType> list = jobtitleTypeService.queryDetail(jobtitle);
        PageInfos<JobtitleType> pageInfo = new PageInfos<JobtitleType>(list, req);
        List<JobtitleType> typeList = jobtitleTypeService.query(null);
        modelMap.put("list", list);
        modelMap.put("typeList", typeList);
        modelMap.put("pageInfo", pageInfo);
        return "nurse/jobtitle/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Jobtitle jobtitle = jobtitleService.loadById(id);
        List<JobtitleType> list = jobtitleTypeService.list(null);
        modelMap.put("jobtitle", jobtitle);
        modelMap.put("list", list);
        return "nurse/jobtitle/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {
        List<JobtitleType> list = jobtitleTypeService.list(null);
        modelMap.put("list", list);
        return "nurse/jobtitle/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Jobtitle jobtitle = jobtitleService.loadById(id);
        List<JobtitleType> list = jobtitleTypeService.list(null);
        modelMap.put("list", list);
        modelMap.put("jobtitle", jobtitle);
        return "nurse/jobtitle/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Jobtitle jobtitle) {
        SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");
        if (jobtitle.getId() != null && !jobtitle.getId().equals("")) {
            boolean b = jobtitleService.update(jobtitle);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jobtitle/err.jhtml";
            }
        }
        else {
            jobtitle.setId(UUIDUtils.getId());
            jobtitle.setCreatorId(systemUser.getId());
            jobtitle.setCreatorName(systemUser.getName());
            String result = jobtitleService.insert(jobtitle);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jobtitle/err.jhtml";
            }
        }
        return "redirect:/jobtitle/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Jobtitle jobtitle) {
        boolean b = jobtitleService.update(jobtitle);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jobtitle/err.jhtml";
        }

        return "redirect:/jobtitle/index.jhtml";
    }

    @ResponseBody
    @RequestMapping(name = "验证名称", path = "/checkName.json")
    public int checkName(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Jobtitle jobtitle) {
        int b = jobtitleService.checkName(jobtitle);
        return b;
    }

}
