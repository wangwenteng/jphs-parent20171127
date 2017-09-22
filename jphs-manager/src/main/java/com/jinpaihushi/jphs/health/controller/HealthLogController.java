package com.jinpaihushi.jphs.health.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.health.model.HealthLog;
import com.jinpaihushi.jphs.health.service.HealthLogService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 11:18:42
 * @version 1.0
 */
@Controller
@RequestMapping(name = "健康", path = "/health/log")
public class HealthLogController extends BaseController<HealthLog> {

    @Autowired
    private HealthLogService healthLogService;

    @Override
    protected BaseService<HealthLog> getService() {
        return healthLogService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            HealthLog healthLog, Integer p, Integer n) {
        startPage(p, n);
        Page<HealthLog> list = healthLogService.query(healthLog);
        PageInfos<HealthLog> pageInfo = new PageInfos<HealthLog>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "user/health/log/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        HealthLog healthLog = healthLogService.loadById(id);
        modelMap.put("healthLog", healthLog);
        return "user/health/log/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "user/health/log/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        HealthLog healthLog = healthLogService.loadById(id);
        modelMap.put("healthLog", healthLog);
        return "user/health/log/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            HealthLog healthLog) {

        if (healthLog.getId() != null && !healthLog.getId().equals("")) {
            boolean b = healthLogService.update(healthLog);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/health/log/err.jhtml";
            }
        }
        else {
            healthLog.setId(UUID.randomUUID().toString());
            String result = healthLogService.insert(healthLog);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/health/log/err.jhtml";
            }
        }
        return "redirect:/health/log/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = healthLogService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/health/log/err.jhtml";
        }

        return "redirect:/health/log/index.jhtml";
    }

}
