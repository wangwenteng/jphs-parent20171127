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
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.service.OrderGoodsService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:29:44
 * @version 1.0
 */
@Controller
@RequestMapping(name = "服务订单服务", path = "/order/goods")
public class OrderGoodsController extends BaseController<OrderGoods> {

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Override
    protected BaseService<OrderGoods> getService() {
        return orderGoodsService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            OrderGoods orderGoods, Integer p, Integer n) {
        startPage(p, n);
        Page<OrderGoods> list = orderGoodsService.query(orderGoods);
        PageInfos<OrderGoods> pageInfo = new PageInfos<OrderGoods>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "order/order/goods/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        OrderGoods orderGoods = orderGoodsService.loadById(id);
        modelMap.put("orderGoods", orderGoods);
        return "order/order/goods/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "order/order/goods/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        OrderGoods orderGoods = orderGoodsService.loadById(id);
        modelMap.put("orderGoods", orderGoods);
        return "order/order/goods/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            OrderGoods orderGoods) {

        if (orderGoods.getId() != null && !orderGoods.getId().equals("")) {
            boolean b = orderGoodsService.update(orderGoods);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/order/goods/err.jhtml";
            }
        }
        else {
            orderGoods.setId(UUID.randomUUID().toString());
            String result = orderGoodsService.insert(orderGoods);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/order/goods/err.jhtml";
            }
        }
        return "redirect:/order/goods/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = orderGoodsService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/order/goods/err.jhtml";
        }

        return "redirect:/order/goods/index.jhtml";
    }

}
