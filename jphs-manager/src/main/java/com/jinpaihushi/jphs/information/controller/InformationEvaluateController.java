package com.jinpaihushi.jphs.information.controller;

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
import com.jinpaihushi.jphs.information.model.Information;
import com.jinpaihushi.jphs.information.model.InformationEvaluate;
import com.jinpaihushi.jphs.information.service.InformationEvaluateService;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Controller
@RequestMapping(name = "资讯评价", path = "/information/evaluate")
public class InformationEvaluateController extends BaseController<InformationEvaluate> {

    @Autowired
    private InformationEvaluateService informationEvaluateService;

    @Autowired
    private InformationService informationService;

    @Override
    protected BaseService<InformationEvaluate> getService() {
        return informationEvaluateService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationEvaluate informationEvaluate, Integer p, Integer n) {
        startPage(p, n);
        Page<InformationEvaluate> list = informationEvaluateService.query(informationEvaluate);
        PageInfos<InformationEvaluate> pageInfo = new PageInfos<InformationEvaluate>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "information/evaluate/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationEvaluate informationEvaluate = informationEvaluateService.loadById(id);

        Information information = new Information();
        if (informationEvaluate.getInformationId() != null && !"".equals(informationEvaluate.getInformationId())) {
            information = informationService.loadById(informationEvaluate.getInformationId());
        }
        modelMap.put("information", information);
        modelMap.put("informationEvaluate", informationEvaluate);
        return "information/evaluate/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "information/evaluate/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationEvaluate informationEvaluate = informationEvaluateService.loadById(id);
        Information information = new Information();
        if (informationEvaluate.getInformationId() != null && !"".equals(informationEvaluate.getInformationId())) {
            information = informationService.loadById(informationEvaluate.getInformationId());
        }
        modelMap.put("information", information);
        modelMap.put("informationEvaluate", informationEvaluate);
        return "information/evaluate/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationEvaluate informationEvaluate, String auditarr, String audit, String essence) {

        if (informationEvaluate.getId() != null && !informationEvaluate.getId().equals("") && !audit.equals("1")) {
            boolean b = informationEvaluateService.update(informationEvaluate);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/information/evaluate/err.jhtml";
            }
        }
        else {
            if (auditarr != null && !"".equals(auditarr) && audit != null && !"".equals(audit)) {
                String[] str_arr = auditarr.split(",");
                InformationEvaluate informationEvaluateUp = new InformationEvaluate();
                informationEvaluateUp.setStatus(Integer.valueOf(audit));
                for (int a = 0; a < str_arr.length; a++) {
                    informationEvaluateUp.setId(str_arr[a]);
                    informationEvaluateService.update(informationEvaluateUp);
                }
            }
            else if (audit != null && !"".equals(audit)) {
                if (audit.equals("1")) {
                    if (essence.equals("0")) {
                        informationEvaluate.setEssence(-1);
                    }
                    else {
                        informationEvaluate.setEssence(0);
                    }

                    informationEvaluateService.update(informationEvaluate);
                }
                return "redirect:/information/evaluate/index.jhtml";
            }
            else {

                try {
                    SystemUser user = (SystemUser) hs.getAttribute("session_user");
                    informationEvaluate.setCreatorId(user.getId());
                    informationEvaluate.setCreatorName(user.getName());
                }
                catch (Exception e) {
                }
                informationEvaluate.setId(UUID.randomUUID().toString());
                String result = informationEvaluateService.insert(informationEvaluate);
                if (result.length() <= 0) {
                    // 跳转到错误页
                    return "redirect:/information/evaluate/err.jhtml";
                }
            }
        }
        return "redirect:/information/evaluate/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = informationEvaluateService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/information/evaluate/err.jhtml";
        }

        return "redirect:/information/evaluate/index.jhtml";
    }

}
