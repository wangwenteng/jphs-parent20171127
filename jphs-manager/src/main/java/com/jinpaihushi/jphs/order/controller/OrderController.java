package com.jinpaihushi.jphs.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.domain.Voucher;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;
import com.jinpaihushi.jphs.evaluation.service.EvaluationService;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.insurance.model.Insurance;
import com.jinpaihushi.jphs.insurance.service.InsuranceService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.jphs.order.service.OrderGoodsService;
import com.jinpaihushi.jphs.order.service.OrderOtherService;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.order.service.OrderServiceService;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.util.ExcelUtil;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Order", path = "/order")
public class OrderController extends BaseController<Order> {

	@Autowired
	private OrderService orderService;

	@Autowired
	private NurseService nurseService;

	@Autowired
	private UserService userService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private OrderOtherService orderOtherService;
	@Autowired
	private OrderServiceService orderServiceService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private InsuranceService insuranceService;
	@Autowired
	private GoodsService goodsService;

	@Override
	protected BaseService<Order> getService() {
		return orderService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Order order, Integer p, Integer n) {
		startPage(p, n);
		// Page<Order> list = orderService.query(order);
		Page<Order> list = orderService.getList(order);
		PageInfos<Order> pageInfo = new PageInfos<Order>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);

		if (order.getSchedule() == 0) {
			modelMap.put("content", "待支付");
		} else if (order.getSchedule() == 1) {
			modelMap.put("content", "待接单");
		} else if (order.getSchedule() == 2) {
			modelMap.put("content", "已结单");
		} else if (order.getSchedule() == 3) {
			modelMap.put("content", "执行中");
		} else if (order.getSchedule() == 4) {
			modelMap.put("content", "待确定");
		} else if (order.getSchedule() == 5) {
			modelMap.put("content", "已完成");
		} else if (order.getSchedule() == 6) {
			modelMap.put("content", "已取消");
		} else {
			modelMap.put("content", "申诉中");
		}

		return "order/order/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id, Nurse nurse) {
		Order order = orderService.loadById(id);
		List<Nurse> list = nurseService.getSomeNurse(nurse);

		modelMap.put("list", list);
		modelMap.put("order", order);
		return "order/order/edit";
	}

	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "order/order/edit";
	}

	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		
		
		
		//查询订单相关信息
		Order order = orderService.loadById(id);
		//查询订单创建者的信息，即user
		User user = userService.loadById(order.getCreatorId());
		//查询接单者的信息，即nurse
		User nurse = userService.loadById(order.getAcceptUserId());
		
		
		OrderGoods og = new OrderGoods();
		Evaluation evaluation = new Evaluation();
		Transaction transaction = new Transaction();
		OrderOther orderOther = new OrderOther();
		Insurance insurance = new Insurance();
		com.jinpaihushi.jphs.order.model.OrderService orderService = new com.jinpaihushi.jphs.order.model.OrderService();
		Goods goodsModel = new Goods();
		
		//查询商品盈利等信息
		og.setOrderId(order.getOrderNo());
		Page<OrderGoods> ogList = orderGoodsService.query(og);
		if (ogList.size() > 0) {
			modelMap.put("og", ogList.get(0));
			goodsModel.setId(ogList.get(0).getGoodsId());
			Goods goods = goodsService.load(goodsModel);
			modelMap.put("goods",goods);
		} else {
			modelMap.put("og", null);
			modelMap.put("goods",null);
		}


		// 查询用户积分的相关信息
		transaction.setOrderId(order.getOrderNo());
		if(user!=null){
			transaction.setCreatorId(user.getId());
		}
		Page<Transaction> transactionUserList = transactionService.query(transaction);
		if (transactionUserList.size() > 0) {
			modelMap.put("transactionUser", transactionUserList.get(0));
		} else {
			modelMap.put("transactionUser", null);
		}
		// 查询退款
		transaction.setOperate(4);

		Page<Transaction> refundList = transactionService.query(transaction);
		if (refundList.size() > 0) {
			modelMap.put("refund", refundList.get(0));
		} else {
			modelMap.put("refund", null);
		}

		// 查询护士积分的相关信息
		if(nurse != null){
			transaction.setCreatorId(nurse.getId());
		}
		transaction.setOperate(null);
		Page<Transaction> transactionNurseList = transactionService.query(transaction);

		if (transactionNurseList.size() > 0) {
			modelMap.put("transactionNurse", transactionNurseList.get(0));
		} else {
			modelMap.put("transactionNurse", null);
		}

		//查询订单的评价信息
		evaluation.setOrderId(order.getOrderNo());
		Page<Evaluation> eList = evaluationService.query(evaluation);
		if (eList.size() > 0) {
			modelMap.put("evaluation", eList.get(0));
		} else {
			modelMap.put("evaluation", null);
		}
		//查询订单的患者和时间等信息
		orderService.setOrderId(order.getOrderNo());
		Page<com.jinpaihushi.jphs.order.model.OrderService> orderServiceList = orderServiceService
				.getInfo(orderService);

		orderOther.setOrderId(order.getOrderNo());
		Page<OrderOther> orderOtherList = orderOtherService.query(orderOther);
		
		if (orderServiceList.size() > 0) {
			modelMap.put("orderService", orderServiceList.get(0));
		} else {
			modelMap.put("orderService", null);
		}
		if (orderOtherList.size() > 0) {
			modelMap.put("orderOther", orderOtherList.get(0));
		} else {
			modelMap.put("orderOtherList", null);
		}

		//查询保险
		insurance.setOrderId(order.getOrderNo());
		Page<Insurance> insuranceList = insuranceService.query(insurance);
		if (insuranceList.size() > 0) {
			modelMap.put("insurance", insuranceList.get(0));
		} else {
			modelMap.put("insurance", null);
		}
		Integer count = orderGoodsService.getOrderCount(og);

		modelMap.put("count",count);
		modelMap.put("orderServiceList", orderServiceList);
		modelMap.put("nurse", nurse);
		modelMap.put("user", user);
		modelMap.put("order", order);
		return "order/order/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Order order) {
		orderService.update(order);
		return "redirect:detail.jhtml?id="+order.getId();
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {

		boolean b = orderService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/order/err.jhtml";
		}

		return "redirect:/order/index.jhtml";
	}
	
	@RequestMapping(name = "修改患者联系方式", path = "/updatePatientName.jhtml")
	public String updatePatientName(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			com.jinpaihushi.jphs.order.model.OrderService orderServiceInfo) {

		orderServiceService.updatePatientPhone(orderServiceInfo);
		
		return "redirect:/order/detail.jhtml?id="+orderServiceInfo.getId();
	}

	@RequestMapping(name = "修改预约时间", path = "/updateAppointmentTime.jhtml")
	public String updateAppointmentTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Order order) {

		 orderService.update(order);
		
		return "redirect:/order/detail.jhtml?id="+order.getId();
	}
	
	
	@RequestMapping(name = "修改服务地址", path = "/updateDetailAddress.jhtml")
	public String updateDetailAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			OrderOther orderOther) {
		System.out.println(orderOther.getAddress());
		 orderOtherService.updateDetailAddress(orderOther);
		
		return "redirect:/order/detail.jhtml?id="+orderOther.getId();
	}
	
	@RequestMapping(name = "退款", path = "/refund.jhtml")
	public String refund(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Transaction transaction) {

		 transactionService.refund(transaction);
		
		return "redirect:/order/detail.jhtml?id="+transaction.getId();
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/edit.jhtml")
	public String edit(ModelMap modelMap) {

		return "order/order/detail/edit";
	}
	
	@RequestMapping(name = "跳转到修改页", path = "/toEdit.jhtml")
	public String toEdit(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id, Nurse nurse) {
		Order order = orderService.loadById(id);
		List<Nurse> list = nurseService.getSomeNurse(nurse);

		modelMap.put("list", list);
		modelMap.put("order", order);
		return "order/order/detail/edit";
	}
	
	
	@RequestMapping(name = "生成订单Excel", path = "/getExcel.jhtml")
	@ResponseBody
	public void getExcel(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			 Order order) {


		 Page<Order> list = orderService.getList(order);

			int count = list.size();
			Order o = new Order();
			JSONArray ja = new JSONArray();
		 
				for(int i=0;i<list.size();i++){
					
					o.setId(list.get(i).getId());
					
					o.setDiscountPrice(list.get(i).getPrice() - list.get(i).getPayPrice());
					o.setPrice(list.get(i).getPrice());
					o.setPayPrice(list.get(i).getPayPrice());
					o.setUserName(list.get(i).getUserName());
					o.setPhone(list.get(i).getPhone());
					o.setDetailAddress(list.get(i).getDetailAddress());
					o.setCreateTime(list.get(i).getCreateTime());
					ja.add(o);
				}
			 
	        
	        Map<String,String> headMap = new LinkedHashMap<String,String>();
	        headMap.put("id","订单编号");
	        headMap.put("price","订单金额");
	        headMap.put("discountPrice","优惠金额");
	        headMap.put("payPrice","实付金额");
	        headMap.put("userName","下单人姓名");
	        headMap.put("phone","电话");
			headMap.put("detailAddress","地址");
			headMap.put("createTime","下单时间");

	        String title = "订单信息";
			

         ExcelUtil.downloadExcelFile(title,headMap,ja,resp);
	}
	
	
}
