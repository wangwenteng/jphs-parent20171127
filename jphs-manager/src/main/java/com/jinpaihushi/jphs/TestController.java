package com.jinpaihushi.jphs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.access.model.AccessLog;
import com.jinpaihushi.jphs.access.service.AccessLogService;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:41
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Test", path = "/test")
public class TestController extends BaseController<AccessLog> {

    @Autowired
    private AccessLogService accessLogService;

    @Override
    protected BaseService<AccessLog> getService() {
        return accessLogService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            AccessLog accessLog, Integer p, Integer n) {
     
        return "index";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        AccessLog accessLog = accessLogService.loadById(id);
        modelMap.put("accessLog", accessLog);
        return "access/access/log/detail/index";
    }

    @RequestMapping(name = "数据按天展示", path = "/showDataByDay.jhtml", method = RequestMethod.GET)
    public String showDataByDay(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "days", required = false) String days) {
        if (StringUtils.isEmpty(days)) {
            Calendar cl = Calendar.getInstance();
            cl.add(Calendar.DATE, -1);
            Date time = cl.getTime();
            days = new SimpleDateFormat("yyyy-MM-dd").format(time);
        }
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByDay(days);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", timeList);
        req.setAttribute("platformName", platformName);
        accessLog.remove(0);
        req.setAttribute("accessLog", accessLog);
        return "analysis.access.log.showdata.index";

    }

    @RequestMapping(name = "数据按周展示", path = "/showDataByWeek.jhtml", method = RequestMethod.GET)
    public String showDataByWeek(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "weekDays", required = false) String weekDays) {
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByWeek(weekDays);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", timeList);
        req.setAttribute("platformName", platformName);
        accessLog.remove(0);
        req.setAttribute("accessLog", accessLog);
        return "analysis.access.log.showdata.index";
    }

    @RequestMapping(name = "数据按月展示", path = "/showDataByMonth.jhtml", method = RequestMethod.GET)
    public String showDataByMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "month", required = false) String month) {
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByMonth(month);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", timeList);
        req.setAttribute("platformName", platformName);
        accessLog.remove(0);
        req.setAttribute("accessLog", accessLog);
        return "analysis.access.log.showdata.index";
    }

    @RequestMapping(name = "数据按年展示", path = "/showDataByYear.jhtml", method = RequestMethod.GET)
    public String showDataByYear(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "year", required = false) String year) {
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByYear(year);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", timeList);
        req.setAttribute("platformName", platformName);
        accessLog.remove(0);
        req.setAttribute("accessLog", accessLog);
        return "analysis.access.log.showdata.index";
    }
}
