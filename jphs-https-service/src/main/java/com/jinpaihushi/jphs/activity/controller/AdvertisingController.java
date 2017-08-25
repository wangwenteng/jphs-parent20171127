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

	/**
	 * type 1用户端 2护士端
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name = "获取轮播图", path = "/getAdvertising.json")
	public byte[] getAdvertising(Integer type) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("advertising.getAdvertising.json");
			}
			// 查空
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端
			if (type == null) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			// 轮播图集合
			Map<String, Object> map = new HashMap<String, Object>();
			if (type == 1) {
				map.put("atype", 2);
			} else if (type == 2) {
				map.put("atype", 1);
			}
			map.put("stype", 1);
			List<Advertising> advertisingList = advertisingService.getCarouselFigure(map);
			return JSONUtil.toJSONResult(1, "操作成功！", advertisingList);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("advertising.getAdvertising.json", e);

		}
		return null;
	}

}
