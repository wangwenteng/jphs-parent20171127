package com.jinpaihushi.jphs.access.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import com.jinpaihushi.jphs.access.model.AccessLogSpread;
import com.jinpaihushi.jphs.access.service.AccessLogService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.CycleTimeUtils;

/**
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:41
 * @version 1.0
 */
@Controller
@RequestMapping(name = "访问量统计", path = "/access/log")
@SuppressWarnings("unchecked")
public class AccessLogController extends BaseController<AccessLog> {

	@Autowired
    private AccessLogService accessLogService;
    @Override
    protected BaseService<AccessLog> getService() {
        return accessLogService;
    }
    
   
	@RequestMapping(name = "首页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            AccessLog accessLog, Integer p, Integer n) {
        /*startPage(p, n);
        Page<AccessLog> list = accessLogService.query(accessLog);
        PageInfos<AccessLog> pageInfo = new PageInfos<AccessLog>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);*/
        return "access/access/log/list/index";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        AccessLog accessLog = accessLogService.loadById(id);
        modelMap.put("accessLog", accessLog);
        return "access/access/log/detail/index";
    }

    @RequestMapping(name = "日访问量统计", path = "/showDataByDay.jhtml", method = RequestMethod.GET)
    public String showDataByDay(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "days", required = false) String days) {
    	boolean flag=false;
        if (StringUtils.isEmpty(days)) {
            Calendar cl = Calendar.getInstance();
            cl.add(Calendar.DATE, -1);
            Date time = cl.getTime();
            days = new SimpleDateFormat("yyyy-MM-dd").format(time);
            flag =true;
        }
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByDay(days);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", Arrays.toString(timeList));
        req.setAttribute("platformName", Arrays.toString(platformName));
        accessLog.remove(0);
    	String[] totalPv = new String [accessLog.size()];
    	String[] totalUv = new String [accessLog.size()];
        for (int i = 0; i < accessLog.size(); i++) {
			List<AccessLogSpread> list = (List<AccessLogSpread>) accessLog.get(i)
                    .get(platformName[i].replaceAll("'", ""));
            int[] pvNum = new int[list.size()];
            int[] uvNum = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                pvNum[j] = list.get(j).getPv();
                uvNum[j] = list.get(j).getUv();
            }
            totalPv[i] = Arrays.toString(pvNum);
            totalUv[i] = Arrays.toString(uvNum);
        }
        req.setAttribute("totalPv", Arrays.toString(totalPv));
        req.setAttribute("totalUv", Arrays.toString(totalUv));
        req.setAttribute("days","'"+ days+"'");
        if(!flag){
        	return "analysis.access.log.showdata.day.empty";
        }
        return "analysis.access.log.showdata.day";

    }

    @RequestMapping(name = "周访问量统计", path = "/showDataByWeek.jhtml", method = RequestMethod.GET)
    public String showDataByWeek(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "weekDays", required = false) String weekDays) {
    	boolean flag=false;
    	if(StringUtils.isEmpty(weekDays)){
    		weekDays =CycleTimeUtils.getWeekStartAndEnd();
    		flag =true;
    	}
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByWeek(weekDays);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", Arrays.toString(timeList));
        req.setAttribute("platformName", Arrays.toString(platformName));
        accessLog.remove(0);
    	String[] totalPv = new String [accessLog.size()];
    	String[] totalUv = new String [accessLog.size()];
        for (int i = 0; i < accessLog.size(); i++) {
            List<AccessLogSpread> list = (List<AccessLogSpread>) accessLog.get(i)
                    .get(platformName[i].replaceAll("'", ""));
            int[] pvNum = new int[list.size()];
            int[] uvNum = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                pvNum[j] = list.get(j).getPv();
                uvNum[j] = list.get(j).getUv();
            }
            totalPv[i] = Arrays.toString(pvNum);
            totalUv[i] = Arrays.toString(uvNum);
        }
        req.setAttribute("totalPv", Arrays.toString(totalPv));
        req.setAttribute("totalUv", Arrays.toString(totalUv));
        if(!flag){
        	return "analysis.access.log.showdata.week.empty";
        }
        return "analysis.access.log.showdata.week";
    }

    @RequestMapping(name = "月访问量统计", path = "/showDataByMonth.jhtml", method = RequestMethod.GET)
    public String showDataByMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "month", required = false) String month) {
    	boolean flag =false;
    	if(StringUtils.isEmpty(month)){
    		month = new SimpleDateFormat("yyyy-MM").format(new Date());
    		flag =true;
    	}
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByMonth(month);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", Arrays.toString(timeList));
        req.setAttribute("platformName", Arrays.toString(platformName));
        accessLog.remove(0);
    	String[] totalPv = new String [accessLog.size()];
    	String[] totalUv = new String [accessLog.size()];
        for (int i = 0; i < accessLog.size(); i++) {
            List<AccessLogSpread> list = (List<AccessLogSpread>) accessLog.get(i)
                    .get(platformName[i].replaceAll("'", ""));
            int[] pvNum = new int[list.size()];
            int[] uvNum = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                pvNum[j] = list.get(j).getPv();
                uvNum[j] = list.get(j).getUv();
            }
            totalPv[i] = Arrays.toString(pvNum);
            totalUv[i] = Arrays.toString(uvNum);
        }
        req.setAttribute("totalPv", Arrays.toString(totalPv));
        req.setAttribute("totalUv", Arrays.toString(totalUv));
        if(!flag){
        	return "analysis.access.log.showdata.month.empty";
        }
        return "analysis.access.log.showdata.month";
    }

    @RequestMapping(name = "月访问量统计", path = "/showDataByYear.jhtml", method = RequestMethod.GET)
    public String showDataByYear(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "year", required = false) String year) {
    	boolean flag =false;
    	if(StringUtils.isEmpty(year)){
    		year = new SimpleDateFormat("yyyy").format(new Date());
    		flag =true;
    	}
        List<Map<String, Object>> accessLog = accessLogService.getAmountOfAccessByYear(year);
        //获取时间列表
        Object[] timeList = (Object[]) accessLog.get(0).get("time");
        //获取平台的名称
        String[] platformName = (String[]) accessLog.get(1).get("platformName");
        platformName.toString();
        accessLog.remove(0);
        req.setAttribute("timeList", Arrays.toString(timeList));
        req.setAttribute("platformName", Arrays.toString(platformName));
        accessLog.remove(0);
    	String[] totalPv = new String [accessLog.size()];
    	String[] totalUv = new String [accessLog.size()];
        for (int i = 0; i < accessLog.size(); i++) {
            List<AccessLogSpread> list = (List<AccessLogSpread>) accessLog.get(i)
                    .get(platformName[i].replaceAll("'", ""));
            int[] pvNum = new int[list.size()];
            int[] uvNum = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                pvNum[j] = list.get(j).getPv();
                uvNum[j] = list.get(j).getUv();
            }
            totalPv[i] = Arrays.toString(pvNum);
            totalUv[i] = Arrays.toString(uvNum);
        }
        req.setAttribute("totalPv", Arrays.toString(totalPv));
        req.setAttribute("totalUv", Arrays.toString(totalUv));
        if(!flag){
        	return "analysis.access.log.showdata.year.empty";
        }
        return "analysis.access.log.showdata.year";
    }
}
