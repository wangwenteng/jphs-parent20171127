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
import com.jinpaihushi.jphs.information.model.InformationCollection;
import com.jinpaihushi.jphs.information.service.InformationCollectionService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Controller
@RequestMapping(name = "资讯收藏", path = "/information/collection")
public class InformationCollectionController extends BaseController<InformationCollection> {

    @Autowired
    private InformationCollectionService informationCollectionService;

    @Override
    protected BaseService<InformationCollection> getService() {
        return informationCollectionService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationCollection informationCollection, Integer p, Integer n) {
        startPage(p, n);
        Page<InformationCollection> list = informationCollectionService.query(informationCollection);
        PageInfos<InformationCollection> pageInfo = new PageInfos<InformationCollection>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "information/information/collection/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationCollection informationCollection = informationCollectionService.loadById(id);
        modelMap.put("informationCollection", informationCollection);
        return "information/information/collection/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "information/information/collection/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationCollection informationCollection = informationCollectionService.loadById(id);
        modelMap.put("informationCollection", informationCollection);
        return "information/information/collection/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationCollection informationCollection) {

        if (informationCollection.getId() != null && !informationCollection.getId().equals("")) {
            boolean b = informationCollectionService.update(informationCollection);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/information/collection/err.jhtml";
            }
        }
        else {
            informationCollection.setId(UUID.randomUUID().toString());
            String result = informationCollectionService.insert(informationCollection);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/information/collection/err.jhtml";
            }
        }
        return "redirect:/information/collection/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = informationCollectionService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/information/collection/err.jhtml";
        }

        return "redirect:/information/collection/index.jhtml";
    }

}
