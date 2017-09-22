package com.jinpaihushi.jphs.user.controller;

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
import com.jinpaihushi.jphs.user.model.Feedback;
import com.jinpaihushi.jphs.user.service.FeedbackService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author zhangzd
 * @date 2017-06-27 11:22:41
 * @version 1.0
 */
@Controller
@RequestMapping(name = "反馈", path = "/feedback")
public class FeedbackController extends BaseController<Feedback> {

    @Autowired
    private FeedbackService feedbackService;

    @Override
    protected BaseService<Feedback> getService() {
        return feedbackService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Feedback feedback, Integer p, Integer n) {
        startPage(p, n);
        Page<Feedback> list = feedbackService.query(feedback);
        PageInfos<Feedback> pageInfo = new PageInfos<Feedback>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "user/feedback/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Feedback feedback = feedbackService.loadById(id);
        modelMap.put("feedback", feedback);
        return "user/feedback/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "user/feedback/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Feedback feedback = feedbackService.loadById(id);
        modelMap.put("feedback", feedback);
        return "user/feedback/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Feedback feedback) {

        if (feedback.getId() != null && !feedback.getId().equals("")) {
            boolean b = feedbackService.update(feedback);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/feedback/err.jhtml";
            }
        }
        else {
            feedback.setId(UUID.randomUUID().toString());
            String result = feedbackService.insert(feedback);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/feedback/err.jhtml";
            }
        }
        return "redirect:/feedback/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = feedbackService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/feedback/err.jhtml";
        }

        return "redirect:/feedback/index.jhtml";
    }

}
