package com.jinpaihushi.jphs.activity.controller;

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
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(name = "护士收入汇总", path = "/incomeSummary.json")
    public byte[] incomeSummary(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("transaction.incomeSummary.json,userId=" + userId);
            }
            // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //查询账户余额
            Account account = new Account();
            account.setCreatorId(userId);
            account = accountService.load(account);
            Map<String, Object> query = new HashMap<String, Object>();
            query.put("userId", userId);
            query = transactionService.incomeSummary(query);
            query.put("balance", account.getBalance().toString());
            return JSONUtil.toJSONResult(1, "操作成功！", query);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("transaction.incomeSummary.json,userId=" + userId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "护士月收入汇总", path = "/incomeSummaryMonth.json")
    public byte[] incomeSummaryMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String month) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("transaction.incomeSummaryMonth.json,userId=" + userId + " month=" + month);
            }
            // 查空
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(month)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //查询账户余额
            Map<String, Object> query = new HashMap<String, Object>();
            query.put("userId", userId);
            query.put("month", month);
            query = transactionService.incomeSummaryMonth(query);
            return JSONUtil.toJSONResult(1, "操作成功！", query);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("transaction.incomeSummaryMonth.json,userId=" + userId + " month=" + month, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "护士月收入明细", path = "/incomeBreakdownMonth.json")
    public byte[] incomeBreakdownMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String month, Integer operate, @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("transaction.incomeBreakdownMonth.json,userId=" + userId + " month=" + month
                        + " operate" + operate);
            }
            // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //判断是否存在月份不存在的时候分页
            if (StringUtils.isEmpty(month)) {
                PageHelper.startPage(p, n);
            }
            //查询账户余额
            Map<String, Object> query = new HashMap<String, Object>();
            query.put("userId", userId);
            query.put("month", month);
            if (StringUtils.isNotEmpty(month)) {
                query.put("operate", operate);
            }
            List<Map<String, Object>> list = transactionService.incomeBreakdownMonth(query);
            if (StringUtils.isEmpty(month)) {
                PageInfo<Map<String, Object>> page = new PageInfo<>(list);
                return JSONUtil.toJSONResult(1, "操作成功！", page);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error(
                    "transaction.incomeBreakdownMonth.json,userId=" + userId + " month=" + month + " operate" + operate,
                    e);
        }
        return null;
    }
}
