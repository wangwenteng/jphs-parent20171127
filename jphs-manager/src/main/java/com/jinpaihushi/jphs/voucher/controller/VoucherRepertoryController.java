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
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.VoucherRepertoryService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:35:45
 * @version 1.0
 */
@Controller
@RequestMapping(name = "优惠券仓库", path = "/voucher/repertory")
public class VoucherRepertoryController extends BaseController<VoucherRepertory> {

    @Autowired
    private VoucherRepertoryService voucherRepertoryService;

    @Override
    protected BaseService<VoucherRepertory> getService() {
        return voucherRepertoryService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            VoucherRepertory voucherRepertory, Integer p, Integer n) {
        startPage(p, n);
        Page<VoucherRepertory> list = voucherRepertoryService.query(voucherRepertory);
        PageInfos<VoucherRepertory> pageInfo = new PageInfos<VoucherRepertory>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "activity/voucher/repertory/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        VoucherRepertory voucherRepertory = voucherRepertoryService.loadById(id);
        modelMap.put("voucherRepertory", voucherRepertory);
        return "activity/voucher/repertory/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "activity/voucher/repertory/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        VoucherRepertory voucherRepertory = voucherRepertoryService.loadById(id);
        modelMap.put("voucherRepertory", voucherRepertory);
        return "activity/voucher/repertory/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            VoucherRepertory voucherRepertory) {

        if (voucherRepertory.getId() != null && !voucherRepertory.getId().equals("")) {
            boolean b = voucherRepertoryService.update(voucherRepertory);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/voucher/repertory/err.jhtml";
            }
        }
        else {
            voucherRepertory.setId(UUID.randomUUID().toString());
            String result = voucherRepertoryService.insert(voucherRepertory);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/voucher/repertory/err.jhtml";
            }
        }
        return "redirect:/voucher/repertory/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = voucherRepertoryService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/voucher/repertory/err.jhtml";
        }

        return "redirect:/voucher/repertory/index.jhtml";
    }

}
