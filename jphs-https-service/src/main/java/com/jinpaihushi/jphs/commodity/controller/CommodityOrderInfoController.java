package com.jinpaihushi.jphs.commodity.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
@Controller
@RequestMapping(name = "CommodityOrderInfo", path = "/commodity/order/info")
public class CommodityOrderInfoController extends BaseController<CommodityOrderInfo> {

	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;

	@Override
	protected BaseService<CommodityOrderInfo> getService() {
		return commodityOrderInfoService;
	}

	 
	@RequestMapping(name = "获取退货商品的信息", path = "/tkDetail.json")
	@ResponseBody
	public byte[] tkDetail(String coiId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.tkDetail.json,coiId =" + coiId  );
			}

			if (StringUtils.isEmpty(coiId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
		/*	if (commodityOrder.getSchdule()==0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
		*/	 
			CommodityOrderInfo coi = commodityOrderInfoService.loadById(coiId);
			
			if (coi == null ) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", coi);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.tkDetail.json,coiId =" + coiId   ,e);
		}
		return null;
	}


	@RequestMapping(name = "提交退货商品原因", path = "/tkReason.json")
	@ResponseBody
	public byte[] tkReason(CommodityOrderInfo coi,String coId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.order.info.tkReason.json,coId="+coId );
			}

			if (StringUtils.isEmpty(coId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			coi.setId(UUID.randomUUID().toString());
			String result = commodityOrderInfoService.insert(coi);
			if (result.length() <= 0) {
				// 跳转到错误页
				return JSONUtil.toJSONResult(0, "请核对参数后访问", 0);
			}
			 
			return JSONUtil.toJSONResult(1, "操作成功！", coi);
		} catch (Exception e) {
			Util.failLog.error("commodity.order.info.tkReason.json,coId="+coId,e);
		}
		return null;
	}

}
