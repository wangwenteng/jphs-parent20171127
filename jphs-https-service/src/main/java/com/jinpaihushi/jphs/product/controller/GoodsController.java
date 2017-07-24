package com.jinpaihushi.jphs.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;
import com.jinpaihushi.jphs.evaluation.service.EvaluationService;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.model.GoodsDetail;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.jphs.order.service.OrderGoodsService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author scj
 * @date 2017-06-27 10:02:37
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Goods", path = "/goods")
public class GoodsController extends BaseController<Goods>{

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Override
	protected BaseService<Goods> getService() {
		// TODO Auto-generated method stub
		return null;
	}
	@ResponseBody
	@RequestMapping(name = "服务基本信息", path = "/getOneGoodsDetail.json")
	public byte[] getOneGoodsDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String siteId, String goodsId,Integer deviceType) {
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.getSiteList.json,goodsId="+goodsId+" siteId="+siteId+" deviceType="+deviceType);
			}
			// 查空
			if(StringUtils.isEmpty(goodsId)||StringUtils.isEmpty(siteId)||deviceType==null){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Map<String, Object> data = new HashMap<>();
			//商品的详情
			GoodsDetail goods = goodsService.getOneGoodsDetail(goodsId,siteId,deviceType);
			if(goods==null){
				return JSONUtil.toJSONResult(0, "此商品该站点还没有设置销售价", null);
			}
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setStatus(0);
			orderGoods.setGoodsId(goodsId);
			int orderNumber = orderGoodsService.count(orderGoods);
			goods.setOrderNumer(orderNumber);
			Integer level = evaluationService.getGoodLevel(goodsId);
			goods.setLevel(level);
			//商品的评价
			data.put("goods", goods);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", data);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("goods.getSiteList.json,goodsId="+goodsId+" siteId="+siteId+" deviceType="+deviceType, e);
			try {
				return JSONUtil.toJSONResult(0, "非法请求", null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(name = "服务评价", path = "/getGoodsEvaluation.json")
	public byte[] getGoodsEvaluation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsId,Integer p) {
		
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.getSiteList.json,goodsId="+goodsId+" p="+p);
			}
			// 查空
			if(StringUtils.isEmpty(goodsId)){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			//商品的评价
			Evaluation evaluation = new Evaluation();
			evaluation.setGoodsId(goodsId);
			evaluation.setStatus(0);
			evaluation.setOrderby("create_time desc");
			if(p==null) p=1;
			PageHelper.startPage(p, 10);
			List<Evaluation> evaluationList = evaluationService.list(evaluation);
			PageInfo<Evaluation> list = new PageInfo<>(evaluationList);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端
			
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("goods.getSiteList.json,goodsId="+goodsId+" p="+p, e);
		}
		return null;
	}


}