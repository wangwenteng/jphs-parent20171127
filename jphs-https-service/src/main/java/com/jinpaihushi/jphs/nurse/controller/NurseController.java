package com.jinpaihushi.jphs.nurse.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.goods.model.Grade;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.price.service.PriceNurseService;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurse")
public class NurseController {
    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private PriceNurseService priceNurseService;

    @Autowired
    private NurseService nurseService;

    @RequestMapping(path = "/getHomepage.json", name = "用户端护士主页")
    @ResponseBody
    public byte[] getNurseList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            @RequestParam(value = "lon", defaultValue = "116.403119", required = true) String lon,
            @RequestParam(value = "lat", defaultValue = "39.914492", required = true) String lat, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.getHomepage.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (StringUtils.isEmpty(lon) || StringUtils.isEmpty(lat)) {
                lon = "116.403119";
                lat = "39.914492";
            }
            //护士基本信息
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("lon", lon);
            query.put("lat", lat);
            List<Map<String, Object>> basicInfo = nurseService.getBasicInfo(query);
            //服务项目
            List<Map<String, Object>> serviceItems = priceNurseService.getServiceItems(userId);
            //工作时间
            List<Worktime> worktime = worktimeService.getNurseWorktime(userId);
            Map<String, Object> result = new HashMap<>();
            result.put("basicInfo", basicInfo);
            result.put("serviceItems", serviceItems);
            result.put("worktime", worktime);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.getHomepage.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getRecommendNurse.json", name = "推荐的护士")
    @ResponseBody
    public byte[] getRecommendNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            @RequestParam(value = "lon", defaultValue = "116.403119", required = true) String lon,
            @RequestParam(value = "lat", defaultValue = "39.914492", required = true) String lat, String goodsId,
            String priceId, String time, @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.getRecommendNurse.json goodsId=" + goodsId + " lon=" + lon + " lat=" + lat
                        + " priceId=" + priceId + " time=" + time + " p=" + p + " n=" + n);
            }
            if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(time) || StringUtils.isEmpty(priceId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (StringUtils.isEmpty(lon) || StringUtils.isEmpty(lat)) {
                lon = "116.403119";
                lat = "39.914492";
            }
            //护士基本信息
            Map<String, Object> query = new HashMap<>();
            query.put("goodsId", goodsId);
            query.put("lon", lon);
            query.put("lat", lat);
            query.put("time", time);
            query.put("priceId", priceId);
            PageHelper.startPage(p, n);
            List<Map<String, Object>> recommendNurse = nurseService.getRecommendNurse(query);
            PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(recommendNurse);
            return JSONUtil.toJSONResult(1, "操作成功！", page);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.getRecommendNurse.json goodsId=" + goodsId + " lon=" + lon + " lat=" + lat
                    + " priceId=" + priceId + " time=" + time + " p=" + p + " n=" + n, e);
        }
        return null;
    }

    @RequestMapping(path = "/getNurseServicePrice.json", name = "获取护士的发布的服务价格列表")
    @ResponseBody
    public byte[] getNurseServicePrice(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsId,
            String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.getNurseServicePrice.json goodsId=" + goodsId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //护士基本信息
            Map<String, Object> query = new HashMap<>();
            query.put("goodsId", goodsId);
            query.put("userId", userId);
            List<Grade> servicePrice = nurseService.getNurseServicePrice(query);
            return JSONUtil.toJSONResult(1, "操作成功！", servicePrice);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.getNurseServicePrice.json goodsId=" + goodsId + " userId=" + userId, e);
        }
        return null;
    }
}
