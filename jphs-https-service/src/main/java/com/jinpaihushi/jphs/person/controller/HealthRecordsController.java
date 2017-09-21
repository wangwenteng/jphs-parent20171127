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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.peizhen.model.PeizhenPharmacyRemind;
import com.jinpaihushi.jphs.peizhen.service.PeizhenPharmacyRemindService;
import com.jinpaihushi.jphs.peizhen.service.PeizhenRecordService;
import com.jinpaihushi.jphs.recovered.service.RecoveredRecordService;
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
    private RecoveredRecordService recoveredRecordService;

    @Autowired
    private PeizhenRecordService peizhenRecordService;

    @Autowired
    private PeizhenPharmacyRemindService peizhenPharmacyRemindService;

    @RequestMapping(path = "/getHealthLogList.json", name = "获取个人中心体检记录列表")
    @ResponseBody
    public byte[] getHealthLogList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getHealthLogList.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            PageHelper.startPage(p, n);
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            List<Map<String, Object>> list = tijianHealthLogService.getHealthLog(query);
            PageInfo<Map<String, Object>> page = new PageInfo<>(list);
            return JSONUtil.toJSONResult(1, "操作成功！", page);
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
            return JSONUtil.toJSONResult(1, "操作成功！", list.get(0));
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getHealthLogDetail.json userId=" + userId + " id=" + id, e);
        }
        return null;
    }

    @RequestMapping(path = "/getRecoveryList.json", name = "获取个人中心康复记录列表")
    @ResponseBody
    public byte[] getRecoveryList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getRecoveryList.json userId" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            PageHelper.startPage(p, n);
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            List<Map<String, Object>> list = recoveredRecordService.getHealthLog(query);
            PageInfo<Map<String, Object>> page = new PageInfo<>(list);
            return JSONUtil.toJSONResult(1, "操作成功！", page);
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getRecoveryList.json userId" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getRecoveryDetail.json", name = "获取个人中心康复记录详情")
    @ResponseBody
    public byte[] getRecoveryDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String id) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getRecoveryDetail.json userId" + userId + " id" + id);
            }
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("id", id);
            List<Map<String, Object>> list = recoveredRecordService.getHealthLog(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list.get(0));
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getRecoveryDetail.json userId" + userId + " id" + id, e);
        }
        return null;
    }

    @RequestMapping(path = "/getDiagnosisList.json", name = "获取个人中心陪诊记录列表")
    @ResponseBody
    public byte[] getDiagnosisList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getDiagnosisList.json userId" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            PageHelper.startPage(p, n);
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            List<Map<String, Object>> list = peizhenRecordService.getHealthLog(query);
            PageInfo<Map<String, Object>> page = new PageInfo<>(list);
            return JSONUtil.toJSONResult(1, "操作成功！", page);
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getDiagnosisList.json userId" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getDiagnosisDetail.json", name = "获取个人中心陪诊记录详情")
    @ResponseBody
    public byte[] getDiagnosisDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String id) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("healthRecords.getDiagnosisDetail.json userId" + userId + " id" + id);
            }
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("id", id);
            List<Map<String, Object>> list = peizhenRecordService.getHealthLog(query);
            Map<String, Object> map = list.get(0);
            PeizhenPharmacyRemind peizhenPharmacyRemind = new PeizhenPharmacyRemind();
            peizhenPharmacyRemind.setPeizhenRecordId(id);
            List<PeizhenPharmacyRemind> list2 = peizhenPharmacyRemindService.list(peizhenPharmacyRemind);
            map.put("peizhenPharmacyRemind", list2);
            return JSONUtil.toJSONResult(1, "操作成功！", map);
        }
        catch (Exception e) {
            Util.failLog.error("healthRecords.getDiagnosisDetail.json userId" + userId + " id" + id, e);
        }
        return null;
    }
}
