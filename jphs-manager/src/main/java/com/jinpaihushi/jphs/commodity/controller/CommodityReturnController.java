package com.jinpaihushi.jphs.commodity.controller;

import java.util.Map;
import java.util.UUID;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.commodity.service.CommodityReturnService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

/**
 * 
 * @author yangsong
 * @date 2017-09-27 13:52:30
 * @version 1.0
 */
@Controller
@RequestMapping(name = "CommodityReturn", path = "/commodity/return")
public class CommodityReturnController extends BaseController<CommodityReturn> {

	@Autowired
	private CommodityReturnService commodityReturnService;
	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;
	@Autowired
    private CommodityOrderService commodityOrderService;

	@Override
	protected BaseService<CommodityReturn> getService() {
		return commodityReturnService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			CommodityReturn commodityReturn, Integer p, Integer n) {
		startPage(p, n);
		Page<CommodityReturn> list = commodityReturnService.query(commodityReturn);
		PageInfos<CommodityReturn> pageInfo = new PageInfos<CommodityReturn>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "shop/commodity/return/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		CommodityReturn commodityReturn = commodityReturnService.loadById(id);
		modelMap.put("commodityReturn", commodityReturn);
		return "shop/commodity/return/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "shop/commodity/return/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		CommodityReturn commodityReturn = commodityReturnService.loadById(id);
		modelMap.put("commodityReturn", commodityReturn);
		return "shop/commodity/return/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, CommodityReturn commodityReturn) {

		if (commodityReturn.getId() != null && !commodityReturn.getId().equals("")) {
			boolean b = commodityReturnService.update(commodityReturn);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/commodity/return/err.jhtml";
			}
		} else {
			commodityReturn.setId(UUID.randomUUID().toString());
			String result = commodityReturnService.insert(commodityReturn);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/commodity/return/err.jhtml";
			}
		}
		return "redirect:/commodity/return/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = commodityReturnService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/commodity/return/err.jhtml";
		}

		return "redirect:/commodity/return/index.jhtml";
	}
	
	@RequestMapping(name = "修改", path = "/update.jhtml")
	@ResponseBody
	public JSONObject update(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, CommodityReturn commodityReturn,String coId) {

			JSONObject message = new JSONObject();
			
			boolean b = commodityReturnService.update(commodityReturn);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(coId);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

			if(!b){
				message.put("result", "0");
			}else{
				message.put("result", "1");

			if(commodityReturn.getStatus() == 2){
				CommodityOrderInfo coi = new CommodityOrderInfo();
				coi.setCommodityOrderId(coId);
				List<CommodityOrderInfo> coiList = commodityOrderInfoService.list(coi);
				int k = 0;
				for (int i = 0;i<coiList.size() ;i++ ){
					CommodityReturn cr = new CommodityReturn();
					cr.setCommodityOrderInfoId(coiList.get(i).getId());
					cr.setStatus(2);
					CommodityReturn crModel = commodityReturnService.load(cr);
					if(crModel!=null){
						k++;
					}
				}
				if(k == coiList.size()){
					CommodityOrder co = new CommodityOrder();
					co.setId(coId);
					co.setSchedule(-3);
					commodityOrderService.update(co);
				}

			}

			}
		 
		return message;
	}

}
