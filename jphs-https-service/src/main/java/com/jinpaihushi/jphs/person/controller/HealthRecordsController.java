package com.jinpaihushi.jphs.person.controller;

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

import com.jinpaihushi.jphs.health.service.HealthLogService;
import com.jinpaihushi.jphs.tijian.service.TijianHealthLogService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * HealthRecordsController 健康档案controller
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping("/healthRecords")
public class HealthRecordsController {
    @Autowired
    private TijianHealthLogService tijianHealthLogService;

    @Autowired
    private HealthLogService healthLogService;

    @RequestMapping(path = "/getHealthLogList.json", name = "获取个人中心体检记录列表")
    @ResponseBody
    public byte[] getHealthLogList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getHealthLogList.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            List<Map<String, Object>> list = tijianHealthLogService.getHealthLog(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getHealthLogList.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getHealthLogDetail.json", name = "获取个人中心体检记录详情")
    @ResponseBody
    public byte[] getHealthLogDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String id) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getHealthLogDetail.json userId=" + userId + " id=" + id);
            }
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("id", id);
            List<Map<String, Object>> list = tijianHealthLogService.getHealthLog(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getHealthLogDetail.json userId=" + userId + " id=" + id, e);
        }
        return null;
    }

    @RequestMapping(path = "/getRecoveryList.json", name = "获取个人中心康复、陪诊记录列表")
    @ResponseBody
    public byte[] getRecoveryList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer type) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getRecoveryList.json userId" + userId + " type=" + type);
            }
            if (StringUtils.isEmpty(userId) || type == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("type", type);
            List<Map<String, Object>> list = healthLogService.getHealthLog(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getRecoveryList.json userId" + userId + " type=" + type, e);
        }
        return null;
    }

    @RequestMapping(path = "/getRecoveryDetail.json", name = "获取个人中心康复、陪诊记录详情")
    @ResponseBody
    public byte[] getRecoveryDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer type, String id) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("healthRecords.getRecoveryDetail.json userId" + userId + " type=" + type + " id" + id);
            }
            if (StringUtils.isEmpty(userId) || type == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("type", type);
            query.put("id", id);
            List<Map<String, Object>> list = healthLogService.getHealthLog(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getRecoveryDetail.json userId" + userId + " type=" + type + " id" + id,
                    e);
        }
        return null;
    }
}
