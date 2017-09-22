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
import com.jinpaihushi.jphs.information.model.InformationLikes;
import com.jinpaihushi.jphs.information.service.InformationLikesService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Controller
@RequestMapping(name = "资讯点赞", path = "/information/likes")
public class InformationLikesController extends BaseController<InformationLikes> {

    @Autowired
    private InformationLikesService informationLikesService;

    @Override
    protected BaseService<InformationLikes> getService() {
        return informationLikesService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationLikes informationLikes, Integer p, Integer n) {
        startPage(p, n);
        Page<InformationLikes> list = informationLikesService.query(informationLikes);
        PageInfos<InformationLikes> pageInfo = new PageInfos<InformationLikes>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "information/information/likes/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationLikes informationLikes = informationLikesService.loadById(id);
        modelMap.put("informationLikes", informationLikes);
        return "information/information/likes/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "information/information/likes/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationLikes informationLikes = informationLikesService.loadById(id);
        modelMap.put("informationLikes", informationLikes);
        return "information/information/likes/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationLikes informationLikes) {

        if (informationLikes.getId() != null && !informationLikes.getId().equals("")) {
            boolean b = informationLikesService.update(informationLikes);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/information/likes/err.jhtml";
            }
        }
        else {
            informationLikes.setId(UUID.randomUUID().toString());
            String result = informationLikesService.insert(informationLikes);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/information/likes/err.jhtml";
            }
        }
        return "redirect:/information/likes/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = informationLikesService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/information/likes/err.jhtml";
        }

        return "redirect:/information/likes/index.jhtml";
    }

}
