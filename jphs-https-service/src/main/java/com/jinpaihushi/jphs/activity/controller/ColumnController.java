package com.jinpaihushi.jphs.activity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.column.model.ColumnService;
import com.jinpaihushi.jphs.column.service.ColumnServiceService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/column")
public class ColumnController {
	@Autowired
	private ColumnServiceService columnServiceService;

	@ResponseBody
	@RequestMapping(name = "获取栏目下的服务", path = "/getColumnService.json")
	public byte[] getColumnService(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String columnId) {

		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("column.getColumnService.json,columnId=" + columnId);
			}
			// 查空
			if (StringUtils.isEmpty(columnId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			ColumnService columnService = new ColumnService();
			columnService.setColumnId(columnId);
			columnService.setStatus(1);
			List<ColumnService> list = columnServiceService.list(columnService);
			// 1.根据 name，password,type查询完整信息
			// 2.错误N种情况判断及返回前端
			// 3.信息无误，封装信息以及生成token，返回前端

			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("column.getColumnService.json,columnId=" + columnId, e);
		}
		return null;
	}
}
