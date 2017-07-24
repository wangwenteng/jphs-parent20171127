package com.jinpaihushi.jphs.product.controller;

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
import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-06-27 09:38:05
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Product", path = "/product")
public class ProductController extends BaseController<Product> {

	@Autowired
	private ProductService productService;

	@Override
	protected BaseService<Product> getService() {
		return productService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Product product, Integer p, Integer n) {
		startPage(p, n);
		product.setStatus(0);
		Page<Product> list = productService.query(product);
		PageInfos<Product> pageInfo = new PageInfos<Product>(list, req);
		System.out.println("pageInfo---pp--"+pageInfo);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "product/product/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Product product = productService.getProductDetail(id);
		List<ServiceImages> serviceImage = product.getServiceImagesList();
		ServiceImages pc_image = new ServiceImages();
		ServiceImages web_image = new ServiceImages();
		ServiceImages qt_image = new ServiceImages();
		for(int a=0;a<serviceImage.size();a++){
			if(serviceImage.get(a).getDevice_type() == 1){
				pc_image = serviceImage.get(a);
			}else if(serviceImage.get(a).getDevice_type() == 2){
				web_image = serviceImage.get(a);
			}else{
				qt_image = serviceImage.get(a);
			}
		}
		modelMap.put("pc_image", pc_image);
		modelMap.put("wap_image", web_image);
		modelMap.put("qt_image", qt_image);
		modelMap.put("product", product);
		return "product/product/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "product/product/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Product product = productService.getProductDetail(id);
		List<ServiceImages> serviceImage = product.getServiceImagesList();
		ServiceImages pc_image = new ServiceImages();
		ServiceImages web_image = new ServiceImages();
		ServiceImages qt_image = new ServiceImages();
		for(int a=0;a<serviceImage.size();a++){
			if(serviceImage.get(a).getDevice_type() == 1){
				pc_image = serviceImage.get(a);
			}else if(serviceImage.get(a).getDevice_type() == 2){
				web_image = serviceImage.get(a);
			}else{
				qt_image = serviceImage.get(a);
			}
		}
		modelMap.put("pc_image", pc_image);
		modelMap.put("web_image", web_image);
		modelMap.put("qt_image", qt_image);
		modelMap.put("product", product);
		return "product/product/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs,User user, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Product product,ImageType imageType) {
//		user.setId(hs.getAttribute(""));
		if (product.getId() != null && !product.getId().equals("")) {
			boolean b = productService.update(product,imageType);
			
			if (b == false) {
				// 跳转到错误页
				return "redirect:/product/err.jhtml";
			}
		} else {
			try {
				SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
				product.setCreatorId(systemUser.getId());
				product.setCreatorName(systemUser.getName());
			} catch (Exception e) {
			}
			boolean result = productService.insert(product,imageType);
			
			if (result == false) {
				// 跳转到错误页
				return "redirect:/product/err.jhtml";
			}
		}
		return "redirect:/product/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Product product) {
		product.setStatus(-1);
		boolean b = productService.update(product);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/product/err.jhtml";
		}

		return "redirect:/product/index.jhtml";
	}
}