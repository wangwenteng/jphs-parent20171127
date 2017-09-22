package com.jinpaihushi.jphs.commodity.controller;

import java.util.UUID;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.alibaba.fastjson.JSONObject;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.logistics.model.Logistics;
import com.jinpaihushi.jphs.logistics.service.LogisticsService;
import com.jinpaihushi.jphs.commodity.model.CommodityLogistics;
import com.jinpaihushi.jphs.commodity.service.CommodityLogisticsService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-09-21 09:17:25
 * @version 1.0
 */
@Controller
@RequestMapping(name = "商品订单", path = "/commodity/order")
public class CommodityOrderController extends BaseController<CommodityOrder> {

    @Autowired
    private CommodityOrderService commodityOrderService;
	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private CommodityLogisticsService commodityLogisticsService;

    @Override
    protected BaseService<CommodityOrder> getService() {
        return commodityOrderService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            CommodityOrder commodityOrder, Integer p, Integer n) {
        startPage(p, n);
        //Page<CommodityOrder> list = commodityOrderService.query(commodityOrder);
		
        Page<CommodityOrder> list = commodityOrderService.getList(commodityOrder);
        PageInfos<CommodityOrder> pageInfo = new PageInfos<CommodityOrder>(list, req);
		Logistics l = new Logistics();
		System.out.println(list.size());
		List<Logistics> lList = logisticsService.list(l);
        modelMap.put("list", list);
		modelMap.put("lList", lList);
        modelMap.put("pageInfo", pageInfo);
        return "order/commodity/order/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        CommodityOrder commodityOrder = commodityOrderService.loadById(id);

        modelMap.put("commodityOrder", commodityOrder);
		
        return "order/commodity/order/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "order/commodity/order/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        CommodityOrder commodityOrder = commodityOrderService.loadById(id);
		List<CommodityOrderInfo> coiList= commodityOrderInfoService.getList(commodityOrder.getId());
		String remark = "";
		if(coiList.size()>0){
			remark = coiList.get(0).getRemark();
		}
		User user = userService.loadById(commodityOrder.getCreatorId());
        modelMap.put("commodityOrder", commodityOrder);
		modelMap.put("coiList", coiList);
		modelMap.put("user", user);
		modelMap.put("remark", remark);
        return "order/commodity/order/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            CommodityOrder commodityOrder) {

        if (commodityOrder.getId() != null && !commodityOrder.getId().equals("")) {
            boolean b = commodityOrderService.update(commodityOrder);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/commodity/order/err.jhtml";
            }
        }
        else {
            commodityOrder.setId(UUID.randomUUID().toString());
            String result = commodityOrderService.insert(commodityOrder);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/commodity/order/err.jhtml";
            }
        }
        return "redirect:/commodity/order/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = commodityOrderService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/commodity/order/err.jhtml";
        }

        return "redirect:/commodity/order/index.jhtml";
    }


	@RequestMapping(name = "添加或修改数据", path = "/addLogistics.jhtml")
	@ResponseBody
	public JSONObject addLogistics(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, CommodityLogistics commodityLogistics) {
			 JSONObject message = new JSONObject();
			

			commodityLogistics.setType(1);
			commodityLogistics.setStatus(1);
			String no = commodityLogistics.getNo();
			commodityLogistics.setNo("");
			CommodityLogistics cl =	commodityLogisticsService.load(commodityLogistics);
			if(cl == null){
				commodityLogistics.setId(UUID.randomUUID().toString());
				commodityLogistics.setNo(no);
				String result = commodityLogisticsService.insert(commodityLogistics);
				if(result.length() >0){
					CommodityOrder commodityOrder = new CommodityOrder();
					commodityOrder.setId(commodityLogistics.getCommodityOrderId());
					commodityOrder.setSchedule(2);
					boolean b = commodityOrderService.update(commodityOrder);
					if(!b){
						message.put("result", "0");
					}else{
						message.put("result", "1");
					}
				}
			}else{
				message.put("result", "0");
			}
		 return message;
	}

}
