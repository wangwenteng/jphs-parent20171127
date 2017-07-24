package com.jinpaihushi.jphs.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.activity.model.Advertising;
import com.jinpaihushi.jphs.activity.service.AdvertisingService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 10:35:22
 * @version 1.0
 */
@Controller
@RequestMapping(path = "/advertising")
public class AdvertisingController extends BaseController<Advertising> {

	@Autowired
	private AdvertisingService advertisingService;

	@Override
	protected BaseService<Advertising> getService() {
		return advertisingService;
	}

	@ResponseBody
	@RequestMapping(name = "获取官网轮播图", path = "/getAdvertising.json")
	public byte[] getAdvertising() {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("advertising.getAdvertising.json" );
			}
			// 查空
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端
			
			
			Map<String, Object> map = new HashMap<>();
			map.put("type", 3);
			List<Map<String, Object>> list = advertisingService.getCarouselFigure(map);
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("advertising.getAdvertising.json" , e);

		}
		return null;
	}

}
