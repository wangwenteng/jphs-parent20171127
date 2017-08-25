package com.jinpaihushi.jphs.person.controller;

import java.util.List;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 工作时间
 * 
 * @author 王文腾
 *
 */
@Controller
@RequestMapping("/worktime")
public class WorktimeController {
    @Autowired
    private WorktimeService worktimeService;

    /**
     * 删除用户已过时间的记录，插入新的记录
     * 
     * @param hs
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(path = "/updateUserWorkTime.json", name = "更新用户上门时间")
    @ResponseBody
    public byte[] updateUserWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("worktime.updateUserWorkTime.json ");
            }
            worktimeService.updateUserWorkTime();
            return JSONUtil.toJSONResult(1, "操作成功！", null);
        }
        catch (Exception e) {
            Util.failLog.error("worktime.updateUserWorkTime.json", e);
        }
        return null;
    }

    @RequestMapping(path = "/updateNurseWorkTime.json", name = "更新护士空闲时间")
    @ResponseBody
    public byte[] updateNurseWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            Worktime worktime) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("worktime.updateNurseWorkTime.json ");
            }
            boolean b = worktimeService.update(worktime);
            Worktime loadById = worktimeService.loadById(worktime.getId());
            List<Worktime> result1 = worktimeService.findWorkTime(loadById.getUserid());
            return JSONUtil.toJSONResult(1, "操作成功！", result1);
        }
        catch (Exception e) {
            Util.failLog.error("worktime.updateNurseWorkTime.json", e);
        }
        return null;
    }

    @RequestMapping(path = "/findNurseWorkTime.json", name = "护士日程安排")
    @ResponseBody
    public byte[] findWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("worktime.findNurseWorkTime.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            List<Worktime> result1 = worktimeService.findWorkTime(userId);
            return JSONUtil.toJSONResult(1, "操作成功！", result1);
        }
        catch (Exception e) {
            Util.failLog.error("worktime.findNurseWorkTime.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/insertNurseWorkTime.json", name = "初始化护士的时间")
    @ResponseBody
    public byte[] insertNurseWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("worktime.findNurseWorkTime.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int result1 = worktimeService.insertNurseWorkTime(userId);
            return JSONUtil.toJSONResult(1, "操作成功！", result1);
        }
        catch (Exception e) {
            Util.failLog.error("worktime.findNurseWorkTime.json userId=" + userId, e);
        }
        return null;
    }
=======
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 工作时间
 * 
 * @author 王文腾
 *
 */
@Controller
@RequestMapping("/worktime")
public class WorktimeController {
    @Autowired
    private WorktimeService worktimeService;

    /**
     * 删除用户已过时间的记录，插入新的记录
     * 
     * @param hs
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(path = "/updateUserWorkTime.json", name = "更新用户上门时间")
    @ResponseBody
    public byte[] updateUserWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("worktime.updateUserWorkTime.json ");
            }
            worktimeService.updateUserWorkTime();
            return JSONUtil.toJSONResult(1, "操作成功！", null);
        }
        catch (Exception e) {
            Util.failLog.error("worktime.updateUserWorkTime.json", e);
        }
        return null;
    }

    @RequestMapping(path = "/updateNurseWorkTime.json", name = "更新护士空闲时间")
    @ResponseBody
    public byte[] updateNurseWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        ServletInputStream is;
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("worktime.updateNurseWorkTime.json ");
            }
            is = req.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[10240];
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0)
                    nTotalRead = nTotalRead + nRead;
            }
            String str = new String(bytes, 0, nTotalRead, "utf-8");
            is.close();
            System.out.println(str);
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str);
            System.out.println(JSONObject.toJSONString(jsonObject.get("result")));
            List<Worktime> list = JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("result")),
                    Worktime.class);
            boolean i = false;
            for (Worktime nurseWorktime : list) {
                i = worktimeService.update(nurseWorktime);
            }
            if (i) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", null);
        }
        catch (Exception e) {
            Util.failLog.error("worktime.updateNurseWorkTime.json", e);
        }
        return null;
    }

    @RequestMapping(path = "/findNurseWorkTime.json", name = "护士日程安排")
    @ResponseBody
    public byte[] findWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Boolean isNextWeek) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("worktime.findNurseWorkTime.json userId=" + userId + " isNextWeek=" + isNextWeek);
            }
            if (StringUtils.isEmpty(userId) || isNextWeek == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            List<Worktime> result1 = worktimeService.findWorkTime(userId, isNextWeek);
            return JSONUtil.toJSONResult(1, "操作成功！", result1);
        }
        catch (Exception e) {
            Util.failLog.error("worktime.findNurseWorkTime.json userId=" + userId + " isNextWeek=" + isNextWeek, e);
        }
        return null;
    }

>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
}
