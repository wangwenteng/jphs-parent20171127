package com.jinpaihushi.jphs.order.controller;

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
import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.jphs.order.service.OrderOtherService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:39:09
 * @version 1.0
 */
@Controller
@RequestMapping(name = "服务订单其他", path = "/order/other")
public class OrderOtherController extends BaseController<OrderOther> {

    @Autowired
    private OrderOtherService orderOtherService;

    @Override
    protected BaseService<OrderOther> getService() {
        return orderOtherService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            OrderOther orderOther, Integer p, Integer n) {
        startPage(p, n);
        Page<OrderOther> list = orderOtherService.query(orderOther);
        PageInfos<OrderOther> pageInfo = new PageInfos<OrderOther>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "order/order/other/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        OrderOther orderOther = orderOtherService.loadById(id);
        modelMap.put("orderOther", orderOther);
        return "order/order/other/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "order/order/other/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        OrderOther orderOther = orderOtherService.loadById(id);
        modelMap.put("orderOther", orderOther);
        return "order/order/other/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            OrderOther orderOther) {

        if (orderOther.getId() != null && !orderOther.getId().equals("")) {
            boolean b = orderOtherService.update(orderOther);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/order/other/err.jhtml";
            }
        }
        else {
            orderOther.setId(UUID.randomUUID().toString());
            String result = orderOtherService.insert(orderOther);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/order/other/err.jhtml";
            }
        }
        return "redirect:/order/other/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = orderOtherService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/order/other/err.jhtml";
        }

        return "redirect:/order/other/index.jhtml";
    }

}
