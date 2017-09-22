package com.jinpaihushi.jphs.withdraw.controller;

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
import com.jinpaihushi.jphs.withdraw.model.WithdrawCash;
import com.jinpaihushi.jphs.withdraw.service.WithdrawCashService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @version 1.0
 */
@Controller
@RequestMapping(name = "提现", path = "/withdraw/cash")
public class WithdrawCashController extends BaseController<WithdrawCash> {

    @Autowired
    private WithdrawCashService withdrawCashService;

    @Override
    protected BaseService<WithdrawCash> getService() {
        return withdrawCashService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            WithdrawCash withdrawCash, Integer p, Integer n) {
        startPage(p, n);
        Page<WithdrawCash> list = withdrawCashService.query(withdrawCash);
        PageInfos<WithdrawCash> pageInfo = new PageInfos<WithdrawCash>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "user/withdraw/cash/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        WithdrawCash withdrawCash = withdrawCashService.loadById(id);
        modelMap.put("withdrawCash", withdrawCash);
        return "user/withdraw/cash/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "user/withdraw/cash/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        WithdrawCash withdrawCash = withdrawCashService.loadById(id);
        modelMap.put("withdrawCash", withdrawCash);
        return "user/withdraw/cash/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            WithdrawCash withdrawCash) {

        if (withdrawCash.getId() != null && !withdrawCash.getId().equals("")) {
            boolean b = withdrawCashService.update(withdrawCash);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/withdraw/cash/err.jhtml";
            }
        }
        else {
            withdrawCash.setId(UUID.randomUUID().toString());
            String result = withdrawCashService.insert(withdrawCash);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/withdraw/cash/err.jhtml";
            }
        }
        return "redirect:/withdraw/cash/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = withdrawCashService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/withdraw/cash/err.jhtml";
        }

        return "redirect:/withdraw/cash/index.jhtml";
    }

}
