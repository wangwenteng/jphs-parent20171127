package com.jinpaihushi.jphs.site.controller;

import java.util.List;

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
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.site.model.ProductList;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.jphs.site.service.SiteService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 站点-
 * @author scjun
 * @date 2017-06-27 08:49:57
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Site", path = "/site")
public class SiteController extends BaseController<Site> {

	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ProductService productService;

	@Override
	protected BaseService<Site> getService() {
		return siteService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Site site, Integer p, Integer n) {
		startPage(p, n);
		site.setStatus(0);
		Page<Site> list = siteService.query(site);
		PageInfos<Site> pageInfo = new PageInfos<Site>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "product/site/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Site site = siteService.loadById(id);
		List<Product> listProduct = productService.getProductGoodsDetail(id);
		modelMap.put("listProduct", listProduct);
		modelMap.put("site", site);
		return "product/site/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap,Product product) {
		List<Product> listProduct = productService.getProductGoodsDetail("0");
		modelMap.put("listProduct", listProduct);
		return "product/site/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Site site = siteService.getSiteDetail(id);
		List<Product> listProduct = productService.getProductGoodsDetail(id);
		modelMap.put("listProduct", listProduct);
		modelMap.put("site", site);
		return "product/site/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Site site,ProductList productList) {

		if (site.getId() != null && !site.getId().equals("")) {
			boolean b = siteService.update(site,productList);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/site/err.jhtml";
			}
		} else {
			try {
				SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
				site.setCreatorId(systemUser.getId());
				site.setCreatorName(systemUser.getName());
			} catch (Exception e) {
			}
			boolean result = siteService.insert(site,productList);
			if (result == false) {
				// 跳转到错误页
				return "redirect:/site/err.jhtml";
			}
		}
		return "redirect:/site/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Site site) {

		site.setStatus(-1);
		boolean b = siteService.update(site);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/site/err.jhtml";
		}

		return "redirect:/site/index.jhtml";
	}
	
	

}