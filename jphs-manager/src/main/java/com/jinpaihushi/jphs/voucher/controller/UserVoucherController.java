package com.jinpaihushi.jphs.voucher.controller;

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
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 16:02:31
 * @version 1.0
 */
@Controller
@RequestMapping(name = "用户的优惠券", path = "/voucher/user")
public class UserVoucherController extends BaseController<VoucherUse> {

    @Autowired
    private VoucherUseService voucherUseService;

    @Override
    protected BaseService<VoucherUse> getService() {
        return voucherUseService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            VoucherUse voucherUse, Integer p, Integer n) {
        startPage(p, n);
        //Page<VoucherUse> list = voucherUseService.query(voucherUse);
        Page<VoucherUse> list = voucherUseService.getList(voucherUse);
        PageInfos<VoucherUse> pageInfo = new PageInfos<VoucherUse>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "user/voucher/use/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        VoucherUse voucherUse = voucherUseService.loadById(id);
        modelMap.put("voucherUse", voucherUse);
        return "user/voucher/use/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "user/voucher/use/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        VoucherUse voucherUse = voucherUseService.loadById(id);
        modelMap.put("voucherUse", voucherUse);
        return "user/voucher/use/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            VoucherUse voucherUse) {

        if (voucherUse.getId() != null && !voucherUse.getId().equals("")) {
            boolean b = voucherUseService.update(voucherUse);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/voucher/use/err.jhtml";
            }
        }
        else {
            voucherUse.setId(UUID.randomUUID().toString());
            String result = voucherUseService.insert(voucherUse);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/voucher/use/err.jhtml";
            }
        }
        return "redirect:/voucher/use/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = voucherUseService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/voucher/use/err.jhtml";
        }

        return "redirect:/voucher/use/index.jhtml";
    }

}
