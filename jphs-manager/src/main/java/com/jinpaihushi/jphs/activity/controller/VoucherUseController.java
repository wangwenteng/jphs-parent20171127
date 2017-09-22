package com.jinpaihushi.jphs.activity.controller;

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
 * @author zhangzd
 * @date 2017-06-26 14:48:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "用户优惠券", path = "/voucher/use")
public class VoucherUseController extends BaseController<VoucherUse> {

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
        return "voucher/voucher/use/list/index";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        VoucherUse voucherUse = voucherUseService.loadById(id);
        modelMap.put("voucherUse", voucherUse);
        return "voucher/voucher/use/detail/index";
    }

}
