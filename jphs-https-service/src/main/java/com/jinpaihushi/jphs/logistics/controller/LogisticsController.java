package com.jinpaihushi.jphs.logistics.controller;

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
import com.jinpaihushi.jphs.logistics.model.Logistics;
import com.jinpaihushi.jphs.logistics.service.LogisticsService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author yangsong
 * @date 2017-09-02 09:26:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Logistics", path = "/logistics")
public class LogisticsController extends BaseController<Logistics> {

	@Autowired
	private LogisticsService logisticsService;

	@Override
	protected BaseService<Logistics> getService() {
		return logisticsService;
	}

	 
	
	@RequestMapping(name = "获取商品列表", path = "/getLogisticsInfo.json")
	@ResponseBody
	public byte[] getLogisticsInfo(
			String coId) {
			 
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("logistics.getLogisticsInfo.json,coId ="+coId );
			}
			if (StringUtils.isEmpty(coId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Map<String,Object> map = logisticsService.getLogisticsInfo(coId);			 
			 
			return JSONUtil.toJSONResult(1, "操作成功！", map);
		} catch (Exception e) {
			Util.failLog.error("logistics.getLogisticsInfo.json,coId =" +coId, e);
		}
		return null;
	}


}
