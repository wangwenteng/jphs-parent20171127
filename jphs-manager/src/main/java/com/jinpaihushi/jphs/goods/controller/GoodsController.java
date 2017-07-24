package com.jinpaihushi.jphs.goods.controller;

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
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.goods.model.ListPrice;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.price.model.Jobtitle;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-06-27 10:02:37
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Goods", path = "/goods")
public class GoodsController extends BaseController<Goods> {

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ProductService productService;

	@Override
	protected BaseService<Goods> getService() {
		return goodsService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Goods goods, Integer p, Integer n) {
		Product pro = new Product();
		pro.setStatus(0);
		Page<Product> product_list = productService.query(pro);
		goods.setStatus(0);
		startPage(p, n);
		Page<Product> list = goodsService.getProductGoodsListDetail(goods);
		PageInfos<Product> pageInfo = new PageInfos<Product>(list, req);
		System.out.println("pageInfo---"+pageInfo);
		modelMap.put("product_list", product_list);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "product/goods/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id,Product p) {
		Goods goods = goodsService.getGoodsDetail(id);
		List<ServiceImages> serviceImage = goods.getServiceImagesList();
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
		
		/*String content = goods.getContent();
		String con = content.replace("<", "");
		*/
		modelMap.put("pc_image", pc_image);
		modelMap.put("wap_image", web_image);
		modelMap.put("qt_image", qt_image);
		Product product_f = new Product();
		product_f.setStatus(0);
		Page<Product> product_list = productService.query(product_f);
		modelMap.put("product_list", product_list);
		modelMap.put("goods", goods);
		return "product/goods/edit";
	}
	
	@RequestMapping(name = "选择职称权限",path = "/jobtitlePriceList.jhtml")
	public String getJobtitle(ModelMap modelMap,String id,String arr){
		
		List<Jobtitle> jobtitlePriceList=goodsService.getJobtitle();
		if(arr!=null && !"".equals(arr)){
			String [] aptitudeIdArr = arr.split(",");
			for(int a=0;a<aptitudeIdArr.length;a++){
				for(int b=0;b<jobtitlePriceList.size();b++){
					if(jobtitlePriceList.get(b).getId().equals(aptitudeIdArr[a])){
						jobtitlePriceList.remove(b);
					}
				}
			}
			
		}
		List<Jobtitle> jobtitleList=goodsService.getJobtitle();
		modelMap.put("jobtitleArr", arr);
		modelMap.put("priceID", id);
		modelMap.put("jobtitleList", jobtitleList);
		modelMap.put("jobtitlePriceList", jobtitlePriceList);
		return "product/goods/jobtitle";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap,Product product) {
		/**
		 * 查询品类
		 * */
		product.setStatus(0);
		Page<Product> product_list = productService.query(product);
		modelMap.put("product_list", product_list);
		
		return "product/goods/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id,Product p) {
		Goods goods = goodsService.getGoodsDetail(id);
		try {
			List<ServiceImages> serviceImage = goods.getServiceImagesList();
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
		} catch (Exception e) {
		}
		modelMap.put("goods", goods);
		return "product/goods/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Goods goods,ListPrice priceGrade,ImageType imageType) {
		if (goods.getId() != null && !goods.getId().equals("")) {
			boolean b = goodsService.updateGoods(goods,priceGrade,imageType);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/goods/err.jhtml";
			}
		} else {
			try {
				SystemUser user = (SystemUser) hs.getAttribute("session_user");
				goods.setCreatorId(user.getId());
				goods.setCreatorName(user.getName());
			} catch (Exception e) {
			}
			int result = goodsService.insertGoodsAndImg(goods,imageType,priceGrade);
			if (result <= 0) {
				// 跳转到错误页
				return "redirect:/goods/err.jhtml";
			}
		}
		return "redirect:/goods/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id ,Goods goods) {

		goods.setStatus(-1);
		boolean b = goodsService.update(goods);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/goods/err.jhtml";
		}

		return "redirect:/goods/index.jhtml";
	}
	
	

}
