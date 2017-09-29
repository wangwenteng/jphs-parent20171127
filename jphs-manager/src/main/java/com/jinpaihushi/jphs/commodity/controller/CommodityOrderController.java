package com.jinpaihushi.jphs.commodity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.commodity.model.CommodityLogistics;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.jphs.commodity.service.CommodityLogisticsService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.commodity.service.CommodityReturnService;
import com.jinpaihushi.jphs.logistics.model.Logistics;
import com.jinpaihushi.jphs.logistics.service.LogisticsService;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.logistics.KdniaoTrackQueryAPI;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	@Autowired
	private CommodityReturnService commodityReturnService;
	@Autowired
	private TransactionService transactionService;



    @Override
    protected BaseService<CommodityOrder> getService() {
        return commodityOrderService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            CommodityOrder commodityOrder, Integer p, Integer n) {
       
        //Page<CommodityOrder> list = commodityOrderService.query(commodityOrder);
		
		System.out.println(StringUtils.isEmpty(commodityOrder.getTitle()));
			
		CommodityOrderInfo coi = new CommodityOrderInfo();
		List<CommodityOrderInfo> coiList = new ArrayList<CommodityOrderInfo>();
	
		if(!(StringUtils.isEmpty(commodityOrder.getTitle()))){
			coi.setTitle(commodityOrder.getTitle());
			coiList = commodityOrderInfoService.list(coi);
			String ids = "";
			for (int i = 0;i<coiList.size() ;i++ ){
				ids +=",'"+coiList.get(i).getCommodityOrderId()+"'";
			}
			commodityOrder.setId(ids.substring(1));
		}
		startPage(p, n);
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
            String id) throws Exception {
        CommodityOrder commodityOrder = commodityOrderService.loadById(id);
		List<CommodityOrderInfo> coiList= commodityOrderInfoService.getList(commodityOrder.getId());
		for (int i = 0; i<coiList.size();i++ ){
			CommodityReturn commodityReturn = new CommodityReturn();
			commodityReturn.setCommodityOrderInfoId(coiList.get(i).getId());
			
			CommodityReturn cr = commodityReturnService.getNotStatus(commodityReturn);
			if(cr!=null){
				coiList.get(i).setCrReason(cr.getReason());
				coiList.get(i).setCrStatus(cr.getStatus());
				coiList.get(i).setCrId(cr.getId());
			}
		}
		String remark = "";
		if(coiList.size()>0){
			remark = coiList.get(0).getRemark();
		}
		User user = userService.loadById(commodityOrder.getCreatorId());
		 
		CommodityLogistics cl =  commodityLogisticsService.getInfo(id);


		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();

		if(cl!=null){
		String result = api.getOrderTracesByJson(cl.getCode(), cl.getNo());
			
		JSONArray arry = JSONObject.fromObject(result).getJSONArray("Traces");
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < arry.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String AcceptStation = arry.getJSONObject(i).getString("AcceptStation") ;
				String AcceptTime = arry.getJSONObject(i).getString("AcceptTime") ;
				map.put("AcceptTime", AcceptTime);
				map.put("AcceptStation", AcceptStation);
				list.add(map);
			}
			modelMap.put("LogisticsList", list);
		}

		Transaction transaction = new Transaction();
		transaction.setOrderId(id);
		Transaction t = transactionService.load(transaction);
        modelMap.put("commodityOrder", commodityOrder);
		modelMap.put("coiList", coiList);
		modelMap.put("user", user);
		modelMap.put("remark", remark);
		modelMap.put("cl", cl);
		modelMap.put("transaction", t);
		

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


	@RequestMapping(name = "添加物流信息", path = "/addLogistics.jhtml")
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
					commodityOrder.setSendTime(new Date());
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
