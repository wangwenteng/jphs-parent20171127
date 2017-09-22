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
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.service.UserAddressService;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 17:15:32
 * @version 1.0
 */
@Controller
@RequestMapping(name = "用户", path = "/user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private NurseImagesService nurseImagesService;

    @Autowired
    private AccountService accountService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, User user,
            Integer p, Integer n) {
        startPage(p, n);
        //只查询用户 
        user.setType(1);
        user.setStatus(0);
        Page<User> list = userService.query(user);
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i).getId();
            UserAddress userAddress = new UserAddress();
            Account account = new Account();
            account.setCreatorId(id);
            userAddress.setCreatorId(id);
            Page<UserAddress> addresslist = userAddressService.query(userAddress);
            if (addresslist.size() > 0) {
                list.get(i).setProvince(addresslist.get(0).getProvince());
                list.get(i).setCity(addresslist.get(0).getCity());
                list.get(i).setArea(addresslist.get(0).getArea());
                list.get(i).setDetailaddress(addresslist.get(0).getDetailaddress());
            }

            Page<Account> accountlist = accountService.query(account);
            if (accountlist.size() > 0) {
                list.get(i).setBalance(accountlist.get(0).getBalance());
                list.get(i).setScore(accountlist.get(0).getScore());

            }
        }

        PageInfos<User> pageInfo = new PageInfos<User>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);

        return "user/user/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        User user = userService.loadById(id);

        modelMap.put("user", user);
        return "user/user/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "user/user/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        User user = userService.getUserDetail(id);
        UserAddress userAddress = new UserAddress();
        userAddress.setCreatorId(id);
        NurseImages nurseImages = new NurseImages();
        nurseImages.setCreatorId(id);
        Page<NurseImages> query = nurseImagesService.query(nurseImages);
        if (query.size() > 0) {
            modelMap.put("images", query.get(0));
        }
        else {
            modelMap.put("images", null);
        }
        Page<UserAddress> list = userAddressService.query(userAddress);
        modelMap.put("user", user);
        modelMap.put("list", list);
        return "user/user/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            User user) {

        if (user.getId() != null && !user.getId().equals("")) {
            boolean b = userService.update(user);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/user/err.jhtml";
            }
        }
        else {
            user.setId(UUID.randomUUID().toString());
            String result = userService.insert(user);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/user/err.jhtml";
            }
        }
        return "redirect:/user/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        User user = userService.loadById(id);
        user.setStatus(-1);
        boolean b = userService.update(user);
        //boolean b = userService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/user/err.jhtml";
        }

        return "redirect:/user/index.jhtml";
    }

}
