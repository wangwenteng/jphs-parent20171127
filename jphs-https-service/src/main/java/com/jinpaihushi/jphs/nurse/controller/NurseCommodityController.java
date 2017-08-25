package com.jinpaihushi.jphs.nurse.controller;

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
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.jphs.nurse.service.NurseCommodityService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author yangsong
 * @date 2017-08-14 10:02:02
 * @version 1.0
 */
@Controller
@RequestMapping(name = "NurseCommodity", path = "/nurse/commodity")
public class NurseCommodityController extends BaseController<NurseCommodity> {

	@Autowired
	private NurseCommodityService nurseCommodityService;

	@Override
	protected BaseService<NurseCommodity> getService() {
		return nurseCommodityService;
	}

	@RequestMapping(name = "创建商品订单", path = "/updateBrowser.json")
	@ResponseBody
	public byte[] updateBrowser(
			NurseCommodity nurseCommodity) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.updateBrowser.json,userId =" + nurseCommodity.getCreatorId() + ",commodityId = "+nurseCommodity.getCommodityId());
			} 
		 
			if (StringUtils.isEmpty(nurseCommodity.getCreatorId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(nurseCommodity.getCommodityId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 			 
			boolean result =  nurseCommodityService.updateBrowser(nurseCommodity);
			
			if (!result) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.updateBrowser.json,userId =" + nurseCommodity.getCreatorId() + ",commodityId = "+nurseCommodity.getCommodityId() , e);
		}
		return null;
	}
	

	@RequestMapping(name = "创建商品订单", path = "/updateShareNumber.json")
	@ResponseBody
	public byte[] updateShareNumber(
			NurseCommodity nurseCommodity) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodityOrder.updateShareNumber.json,userId =" + nurseCommodity.getCreatorId() + ",commodityId = "+nurseCommodity.getCommodityId());
			} 
		 
			if (StringUtils.isEmpty(nurseCommodity.getCreatorId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(nurseCommodity.getCommodityId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 			 
			boolean result =  nurseCommodityService.updateShareNumber(nurseCommodity);
			
			if (!result) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", result);
		} catch (Exception e) {
			Util.failLog.error("commodityOrder.updateShareNumber.json,userId =" + nurseCommodity.getCreatorId() + ",commodityId = "+nurseCommodity.getCommodityId() , e);
		}
		return null;
	}

}
