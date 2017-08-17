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
import com.jinpaihushi.jphs.goods.model.Grade;
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
public class GoodsController extends BaseController<Goods> {

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
	public byte[] getOneGoodsDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String siteId,
			String goodsId, Integer deviceType) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.getOneGoodsDetail.json,goodsId=" + goodsId + " siteId=" + siteId
						+ " deviceType=" + deviceType);
			}
			// 查空
			if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(siteId) || deviceType == null) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Map<String, Object> data = new HashMap<>();
			// 商品的详情
			GoodsDetail goods = goodsService.getOneGoodsDetail(goodsId, siteId, deviceType);
			if (goods == null) {
				return JSONUtil.toJSONResult(0, "此商品该站点还没有设置销售价", null);
			}
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setStatus(0);
			orderGoods.setGoodsId(goodsId);
			int orderNumber = orderGoodsService.count(orderGoods);
			goods.setOrderNumer(orderNumber);
			Integer level = evaluationService.getGoodLevel(goodsId);
			goods.setLevel(level);
			// 商品的评价
			data.put("goods", goods);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", data);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("goods.getOneGoodsDetail.json,goodsId=" + goodsId + " siteId=" + siteId + " deviceType="
					+ deviceType, e);
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
	public byte[] getGoodsEvaluation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsId,
			Integer p) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.getGoodsEvaluation.json,goodsId=" + goodsId + " p=" + p);
			}
			// 查空
			if (StringUtils.isEmpty(goodsId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			// 商品的评价
			Evaluation evaluation = new Evaluation();
			evaluation.setGoodsId(goodsId);
			evaluation.setStatus(0);
			evaluation.setOrderby("create_time desc");
			if (p == null)
				p = 1;
			PageHelper.startPage(p, 10);
			List<Evaluation> evaluationList = evaluationService.listInfo(evaluation);
			PageInfo<Evaluation> list = new PageInfo<>(evaluationList);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("goods.getGoodsEvaluation.json,goodsId=" + goodsId + " p=" + p, e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(name = "服务价格", path = "/getGoodsAllPrice.json")
	public byte[] getGoodsAllPrice(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsId,
			String siteId) {

		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.getGoodsAllPrice.json,goodsId=" + goodsId + " siteId=" + siteId);
			}
			// 查空
			if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(siteId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Grade> goods = goodsService.getGoodsAllPrice(goodsId, siteId);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", goods);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("goods.getGoodsAllPrice.json,goodsId=" + goodsId + " siteId=" + siteId, e);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(name = "热门服务", path = "/hostService.json")
	public byte[] hostService(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, Integer deviceType,
			String columnId, String siteId) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.hostService.json,deviceType=" + deviceType + " siteId=" + siteId + " columnId="
						+ columnId);
			}
			// 查空
			if (StringUtils.isEmpty(columnId) || StringUtils.isEmpty(siteId) || deviceType == null) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}

			// 热门服务
			Map<String, Object> map_columnId = new HashMap<String, Object>();
			map_columnId.put("columnId", columnId);
			map_columnId.put("siteId", siteId);
			map_columnId.put("deviceType", deviceType);
			List<Map<String, Object>> columnGoodsList = goodsService.getColumnGoods(map_columnId);

			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", columnGoodsList);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error(
					"goods.hostService.json,deviceType=" + deviceType + " siteId=" + siteId + " columnId=" + columnId, e);
		}
		return null;
	}


	@ResponseBody
	@RequestMapping(name = "服务评价", path = "/getAllGoods.json")
	public byte[] getAllGoods(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,Goods goods) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.getAllGoods.json");
			}

			if (StringUtils.isEmpty(goods.getCreatorId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
			List<Goods> goodsList = goodsService.getAllGoods(goods);

			return JSONUtil.toJSONResult(1, "操作成功！", goodsList);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("goods.getAllGoods.json " , e);
		}
		return null;
	}


	
	@ResponseBody
	@RequestMapping(name = "服务评价", path = "/getMyServices.json")
	public byte[] getMyServices(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String creatorId) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("goods.getMyServices.json creatorId = "+ creatorId);
			}
			if (StringUtils.isEmpty(creatorId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
			List<Goods> goodsList = goodsService.getMyService(creatorId);

			return JSONUtil.toJSONResult(1, "操作成功！", goodsList);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("goods.getMyServices.json creatorId = "+creatorId , e);
		}
		return null;
	}



	
}
