package com.jinpaihushi.jphs.information.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.jinpaihushi.jphs.information.model.InformationChannel;
import com.jinpaihushi.jphs.information.service.InformationChannelService;
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
@RequestMapping(name = "资讯", path = "/information")
public class InformationController extends BaseController<Information> {

    @Autowired
    private InformationService informationService;

    @Autowired
    private InformationChannelService informationChannelService;

    @Override
    protected BaseService<Information> getService() {
        return informationService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Information information, Integer p, Integer n) {
        startPage(p, n);
        Page<Information> list = informationService.query(information);
        PageInfos<Information> pageInfo = new PageInfos<Information>(list, req);

        InformationChannel i = new InformationChannel();
        i.setStatus(0);
        List<InformationChannel> iarr = informationChannelService.list(i);
        modelMap.put("iarr", iarr);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "information/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        InformationChannel i = new InformationChannel();
        i.setStatus(1);
        List<InformationChannel> iarr = informationChannelService.list(i);
        Information information = informationService.loadById(id);
        try {

            String[] informationChannelId = information.getInformationChannelId().split(",");
            for (int a = 0; a < iarr.size(); a++) {
                for (int b = 0; b < informationChannelId.length; b++) {
                    String InformationChannel_id = iarr.get(a).getId();
                    if (InformationChannel_id.equals(informationChannelId[b])) {
                        iarr.get(a).setStatus(1);
                    }
                }
            }
        }
        catch (Exception e) {
        }

        modelMap.put("informationChannel", iarr);
        modelMap.put("information", information);
        return "information/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {
        InformationChannel i = new InformationChannel();
        i.setStatus(1);
        List<InformationChannel> iarr = informationChannelService.list(i);

        modelMap.put("informationChannel", iarr);
        return "information/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        InformationChannel i = new InformationChannel();
        i.setStatus(0);
        List<InformationChannel> iarr = informationChannelService.list(i);
        Information information = informationService.loadById(id);
        try {

            String[] informationChannelId = information.getInformationChannelId().split(",");
            for (int a = 0; a < iarr.size(); a++) {
                for (int b = 0; b < informationChannelId.length; b++) {
                    String InformationChannel_id = iarr.get(a).getId();
                    if (InformationChannel_id.equals(informationChannelId[b])) {
                        iarr.get(a).setStatus(1);
                    }
                }
            }
        }
        catch (Exception e) {
        }

        modelMap.put("informationChannel", iarr);
        modelMap.put("information", information);
        return "information/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Information information, String topTimet) {

        if (information.getId() != null && !information.getId().equals("")) {

            if (topTimet != null && !"".equals(topTimet)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    Date date = sdf.parse(topTimet);
                    information.setTopTime(date);
                }
                catch (ParseException e) {
                }
            }
            try {
                String content = information.getContent();
                String con = content.replace("＜", "<").replace("＞", ">").replace("＆quot;", "");
                information.setContent(con);
            }
            catch (Exception e) {
            }
            boolean b = informationService.update(information);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/information/err.jhtml";
            }
        }
        else {
            information.setId(UUID.randomUUID().toString());
            //	https://www.jinpaihushi.com/news/details.html?id=a11a932d-726d-4327-9ca2-0a9e29abfa7f
            if (information.getAuthor() == null || information.getAuthor().equals("")) {
                information.setAuthor("金牌护师");
            }
            if (information.getSource() == null || information.getSource().equals("")) {
                information.setSource("https://www.jinpaihushi.com/news/details.html?id=" + information.getId());
            }

            if (information.getTop() == 1) {
                information.setTopTime(new Date());
            }
            try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                information.setCreatorId(systemUser.getId());
                information.setCreatorName(systemUser.getName());
            }
            catch (Exception e) {
            }
            try {
                String content = information.getContent();
                String con = content.replace("＜", "<").replace("＞", ">").replace("＆quot;", "");
                information.setContent(con);
            }
            catch (Exception e) {
            }
            String result = informationService.insert(information);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/information/err.jhtml";
            }
        }
        return "redirect:/information/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Information information) {
        boolean b = informationService.update(information);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/information/err.jhtml";
        }

        return "redirect:/information/index.jhtml";
    }

}
