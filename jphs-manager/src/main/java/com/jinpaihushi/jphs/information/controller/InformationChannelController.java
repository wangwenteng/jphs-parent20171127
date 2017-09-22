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
import com.jinpaihushi.jphs.information.model.InformationChannel;
import com.jinpaihushi.jphs.information.service.InformationChannelService;
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
@RequestMapping(name = "资讯频道", path = "/information/channel")
public class InformationChannelController extends BaseController<InformationChannel> {

    @Autowired
    private InformationChannelService informationChannelService;

    @Override
    protected BaseService<InformationChannel> getService() {
        return informationChannelService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationChannel informationChannel, Integer p, Integer n) {
        startPage(p, n);
        Page<InformationChannel> list = informationChannelService.query(informationChannel);
        PageInfos<InformationChannel> pageInfo = new PageInfos<InformationChannel>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "information/channel/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationChannel informationChannel = informationChannelService.loadById(id);
        modelMap.put("informationChannel", informationChannel);
        return "information/channel/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "information/channel/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationChannel informationChannel = informationChannelService.loadById(id);
        modelMap.put("informationChannel", informationChannel);
        return "information/channel/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationChannel informationChannel) {

        if (informationChannel.getId() != null && !informationChannel.getId().equals("")) {
            boolean b = informationChannelService.update(informationChannel);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/information/channel/err.jhtml";
            }
        }
        else {
            try {
                SystemUser user = (SystemUser) hs.getAttribute("session_user");
                informationChannel.setCreatorId(user.getId());
                informationChannel.setCreatorName(user.getName());
            }
            catch (Exception e) {
            }
            informationChannel.setId(UUID.randomUUID().toString());
            String result = informationChannelService.insert(informationChannel);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/information/channel/err.jhtml";
            }
        }
        return "redirect:/information/channel/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            InformationChannel informationChannel) {
        boolean b = informationChannelService.update(informationChannel);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/information/channel/err.jhtml";
        }

        return "redirect:/information/channel/index.jhtml";
    }

}
