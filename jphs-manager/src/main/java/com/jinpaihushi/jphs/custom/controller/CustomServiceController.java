package com.jinpaihushi.jphs.custom.controller;

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
import com.jinpaihushi.jphs.custom.model.CustomService;
import com.jinpaihushi.jphs.custom.service.CustomServiceService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-13 14:40:35
 * @version 1.0
 */
@Controller
@RequestMapping(name = "定制服务", path = "/custom/service")
public class CustomServiceController extends BaseController<CustomService> {

    @Autowired
    private CustomServiceService customServiceService;

    @Override
    protected BaseService<CustomService> getService() {
        return customServiceService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            CustomService customService, Integer p, Integer n) {
        startPage(p, n);
        //Page<CustomService> list = customServiceService.query(customService);
        Page<CustomService> list = customServiceService.getList(customService);
        PageInfos<CustomService> pageInfo = new PageInfos<CustomService>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "order/custom/service/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        CustomService customService = customServiceService.loadById(id);
        modelMap.put("customService", customService);
        return "order/custom/service/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "order/custom/service/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        CustomService customService = customServiceService.loadById(id);
        modelMap.put("customService", customService);
        return "order/custom/service/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            CustomService customService) {

        if (customService.getId() != null && !customService.getId().equals("")) {
            boolean b = customServiceService.update(customService);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/custom/service/err.jhtml";
            }
        }
        else {
            customService.setId(UUID.randomUUID().toString());
            String result = customServiceService.insert(customService);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/custom/service/err.jhtml";
            }
        }
        return "redirect:/custom/service/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = customServiceService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/custom/service/err.jhtml";
        }

        return "redirect:/custom/service/index.jhtml";
    }

}
