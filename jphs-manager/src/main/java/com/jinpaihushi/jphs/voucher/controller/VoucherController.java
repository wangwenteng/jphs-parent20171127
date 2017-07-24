package com.jinpaihushi.jphs.voucher.controller;

import java.util.Date;
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
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.VoucherRepertoryService;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
@Controller
@RequestMapping(name = "优惠券", path = "/voucher")
public class VoucherController extends BaseController<Voucher> {

	@Autowired
	private VoucherService voucherService;
	@Autowired
	private VoucherRepertoryService voucherRepertoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private VoucherUseService voucherUseService;

	@Override
	protected BaseService<Voucher> getService() {
		return voucherService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Voucher voucher, Integer p, Integer n) {
		startPage(p, n);
		//Page<Voucher> list = voucherService.query(voucher);
		Page<Voucher> list = voucherService.getList(voucher);
		PageInfos<Voucher> pageInfo = new PageInfos<Voucher>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "activity/voucher/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Voucher voucher = voucherService.loadById(id);
		modelMap.put("voucher", voucher);
		return "activity/voucher/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		Page<Product> list = productService.query(new Product());
		modelMap.put("list", list);
		return "activity/voucher/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Voucher voucherModel = new Voucher();
		VoucherRepertory voucherRepertory = new VoucherRepertory();
		VoucherUse voucherUse = new VoucherUse();
		voucherModel.setId(id);
		Voucher voucher = voucherService.getList(voucherModel).get(0);
		voucherRepertory.setVoucherId(voucher.getId());
		Integer count = voucherRepertoryService.count(voucherRepertory);
		voucherUse.setVoucherRepertoryId(id);
		Page<VoucherUse> list = voucherUseService.getDetailtList(voucherUse);
		modelMap.put("voucher", voucher);
		modelMap.put("count", count);
		modelMap.put("list", list);
		return "activity/voucher/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Voucher voucher,Integer count) {

		if(voucher.getBatchNo()!=null &&voucher.getBatchNo().length() > 0){
			String id = UUID.randomUUID().toString();
			//SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
			voucher.setId(id);
			voucher.setCreateTime(new Date());
			//voucher.setCreatorId(systemUser.getId());
			//voucher.setCreatorName(systemUser.getName());
			String result = voucherService.insert(voucher);
			
			VoucherRepertory voucherRepertory = new VoucherRepertory(); 
				for (int i = 0; i < count; i++) {
					voucherRepertory.setId(UUID.randomUUID().toString());
					voucherRepertory.setVoucherId(id);
					voucherRepertory.setNo(voucher.getBatchNo()+i);
					voucherRepertory.setAmount(voucher.getAmount());
					voucherRepertory.setConditionAmount(voucher.getConditionAmount());
					voucherRepertory.setDiscountAmount(voucher.getDiscountAmount());
					voucherRepertoryService.insert(voucherRepertory);
				}
			
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/voucher/err.jhtml";
			}
		}
		return "redirect:/voucher/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = voucherService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/voucher/err.jhtml";
		}

		return "redirect:/voucher/index.jhtml";
	}
	
	

}
