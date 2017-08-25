package com.jinpaihushi.jphs.access.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.access.model.AccessLog;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:41
 * @version 1.0
 */
public interface AccessLogService extends BaseService<AccessLog> {

    /**
     * {查询某一天的访问量}
     * 
     * @param days 查询的日期
     * @return
     * @author: wangwt
     */
    List<Map<String, Object>> getAmountOfAccessByDay(String days);

    /**
     * {查询某一周的访问量}
     * 
     * @param weekDays  当前周
     * @return
     * @author: wangwt
     */
    List<Map<String, Object>> getAmountOfAccessByWeek(String weekDays);

    /**
     * {查询某一个月的访问量}
     * 
     * @param month 当前月
     * @return
     * @author: wangwt
     */
    List<Map<String, Object>> getAmountOfAccessByMonth(String month);

    /**
     * {查询某一年的访问量}
     * 
     * @param year 当前年
     * @return
     * @author: wangwt
     */
    List<Map<String, Object>> getAmountOfAccessByYear(String year);

}