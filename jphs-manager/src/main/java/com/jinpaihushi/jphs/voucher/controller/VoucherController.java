package com.jinpaihushi.jphs.voucher.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.site.model.Site;
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
	private VoucherUseService voucherUseService;
	@Autowired
	private ProductService productService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private PlatformService platformService;

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

		
		Page<Product> productList = productService.query(new Product());
		
		 Goods goodsModel = new Goods();
		if(voucher!=null &&voucher.getGoodsId()!=null){
			goodsModel.setProductId(voucher.getProductId());
			Page<Goods> goodsList = goodsService.query(goodsModel);
			modelMap.put("goodsList", goodsList); 
		}else{
			modelMap.put("goodsList", null);
		}
		
		
		Page<Voucher> list = voucherService.getList(voucher);
		for (int i = 0; i<list.size();i++ ){
			String productName = "";
			String goodsName = "";
			String productIds = list.get(i).getProductId();
			String[] productId = productIds.split(",");
			 
			String goodsIds = list.get(i).getGoodsId();
			 
			String[] goodsId = goodsIds.split(",");
			for(int j = 0;j<productId.length;j++){
				Product product = productService.loadById(productId[j]);
				productName += "," + product.getTitle();
			}
			for(int j = 0;j<goodsId.length;j++){
				Goods goods = goodsService.loadById(goodsId[j]);
				goodsName += "," + goods.getTitle(); 
			}
			list.get(i).setProductName(productName.substring(1));
			list.get(i).setGoodsName(goodsName.substring(1));
		}
		
		PageInfos<Voucher> pageInfo = new PageInfos<Voucher>(list, req);
		modelMap.put("list", list);
		modelMap.put("productList", productList);
		
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

		/*Page<Product> list = productService.query(new Product());
		modelMap.put("list", list);*/
		JSONObject message = new JSONObject();
		List<TreeNode> list=platformService.getGoodsList(null);
		List<Site> site = platformService.getSelectSite(null);
		message.put("treeData", list);
		modelMap.put("data", message);
		modelMap.put("site", site);
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
		
		String productName = "";
		String productIds = voucher.getProductId();
		String[] productId = productIds.split(",");
		for (int i = 0;i<productId.length ;i++ ){
			Product product = productService.loadById(productId[i]);
			productName += "," + product.getTitle();
		}
		String goodsName = "";
		String goodsIds = voucher.getGoodsId();
		String[] goodsId = goodsIds.split(",");
		for(int j = 0;j<goodsId.length;j++){
			Goods goods = goodsService.loadById(goodsId[j]);
			goodsName += "," + goods.getTitle(); 
		}


		voucher.setProductName(productName.substring(1));
		voucher.setGoodsName(goodsName.substring(1));
		
		voucherRepertory.setVoucherId(voucher.getId());
		Integer count = voucherRepertoryService.count(voucherRepertory);
		Page<VoucherRepertory> voucherRepertorylist = voucherRepertoryService.query(voucherRepertory);
		//Page<VoucherUse> list = voucherUseService.getDetailtList(voucherUse);
		List<VoucherUse> list = new ArrayList<VoucherUse>();
		for (int i = 0; i<voucherRepertorylist.size();i++ ){
			String voucherRepertoryId = voucherRepertorylist.get(i).getId();
			 
			VoucherUse VoucherUse  = voucherUseService.getVoucherUse(voucherRepertoryId);
			list.add(VoucherUse);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for (int i = 0; i<list.size();i++ ){
			System.out.println(list.get(i).toString());
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		modelMap.put("voucher", voucher);
		modelMap.put("count", count);
		modelMap.put("list", list);
		return "activity/voucher/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Voucher voucher,Integer count) {

		if(voucher.getType()!=null){
			String id = UUID.randomUUID().toString();
			SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
			voucher.setId(id);
			voucher.setCreateTime(new Date());
			voucher.setCreatorId(systemUser.getId());
			voucher.setCreatorName(systemUser.getName());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String batchNo = sdf.format(new Date());

		 
			//优惠券批次号生成规则
			if(voucher.getType() == 1){
				//现金券批次号生成规则
				voucher.setBatchNo("XJS" + batchNo);
			}else if(voucher.getType() == 2){
				//满减券批次号生成规则
				voucher.setBatchNo("MJS" + batchNo);
			}else if(voucher.getType() == 3){
				//折扣券批次号生成规则
				voucher.setBatchNo("ZKS" + batchNo);
			}
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
	
	@RequestMapping(name = "查询商品", path = "/getGoodsList.jhtml")
	@ResponseBody
	public JSONObject getGoodsList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

 
		JSONObject message = new JSONObject();
		Goods goods = new Goods();
		goods.setProductId(id);
		Page<Goods> goodsList = goodsService.query(goods);
		message.put("goodsList", goodsList); 
		 
		return message;
		 

		 
	}

}
