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
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.service.UserAddressService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 15:09:14
 * @version 1.0
 */
@Controller
@RequestMapping(name = "地址管理", path = "/user/address")
public class UserAddressController extends BaseController<UserAddress> {

    @Autowired
    private UserAddressService userAddressService;

    @Override
    protected BaseService<UserAddress> getService() {
        return userAddressService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            UserAddress userAddress, Integer p, Integer n) {
        startPage(p, n);
        Page<UserAddress> list = userAddressService.query(userAddress);
        PageInfos<UserAddress> pageInfo = new PageInfos<UserAddress>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "user/user/address/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        UserAddress userAddress = userAddressService.loadById(id);
        modelMap.put("userAddress", userAddress);
        return "user/user/address/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "user/user/address/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        UserAddress userAddress = userAddressService.loadById(id);
        modelMap.put("userAddress", userAddress);
        return "user/user/address/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            UserAddress userAddress) {

        if (userAddress.getId() != null && !userAddress.getId().equals("")) {
            boolean b = userAddressService.update(userAddress);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/user/address/err.jhtml";
            }
        }
        else {
            userAddress.setId(UUID.randomUUID().toString());
            String result = userAddressService.insert(userAddress);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/user/address/err.jhtml";
            }
        }
        return "redirect:/user/address/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = userAddressService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/user/address/err.jhtml";
        }

        return "redirect:/user/address/index.jhtml";
    }

}
