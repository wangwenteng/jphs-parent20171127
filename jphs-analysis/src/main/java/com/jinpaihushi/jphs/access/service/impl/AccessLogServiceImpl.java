package com.jinpaihushi.jphs.access.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.access.dao.AccessLogDao;
import com.jinpaihushi.jphs.access.dao.AccessLogSpreadDao;
import com.jinpaihushi.jphs.access.model.AccessLog;
import com.jinpaihushi.jphs.access.model.AccessLogSpread;
import com.jinpaihushi.jphs.access.service.AccessLogService;
import com.jinpaihushi.jphs.platform.dao.PlatformDao;
import com.jinpaihushi.jphs.platform.model.Platform;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.MyPredicate;

/**
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:41
 * @version 1.0
 */
@Service("accessLogService")
public class AccessLogServiceImpl extends BaseServiceImpl<AccessLog> implements AccessLogService {
    private SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private AccessLogDao accessLogDao;

    @Override
    protected BaseDao<AccessLog> getDao() {
        return accessLogDao;
    }

    @Autowired
    private AccessLogSpreadDao accessLogSpreadDao;
    
    @Autowired
    private PlatformDao platformDao;
    /**
     * {获取一天的点击量}
     * 
     * @param days 查询日期
     * @return 数据集合
     * @author: wangwt
     */
    @Override
    public List<Map<String, Object>> getAmountOfAccessByDay(String days) {
        List<AccessLogSpread> list = accessLogSpreadDao.getAmountOfAccessByDay(days);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            //定义时间的数组
            List<String> xAxis = new ArrayList<>();
            for (int i = 1; i < 25; i++) {
                if (i < 10) {
                    xAxis.add("'0" + i+"时'");
                }
                else {
                    xAxis.add("'"+i +"时'");
                }
            }
            Object[] array = xAxis.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            //获取结果中的平台集合
           /* List<AccessLogSpread> platformList = new ArrayList<>(list);
            for (int i = 0; i < platformList.size(); i++) {
                for (int j = platformList.size() - 1; j > i; j--) {
                    if (platformList.get(i).getPlatformId().equals(platformList.get(j).getPlatformId())) {
                        platformList.remove(j);
                    }
                }
            }*/
            //从平台表中获取所有可用平台信息
            Platform platform =new Platform();
            platform.setStatus(0);
            List<Platform> platformList = platformDao.list(platform);
            
            map = new HashMap<>();
            String[] names = new String[platformList.size()];
            for (int i = 0; i < platformList.size(); i++) {
                names[i] = "'" + platformList.get(i).getName() + "'";
            }
            map.put("platformName", names);
            result.add(map);
            //定义单个平台的集合
            List<AccessLogSpread> platformData = null;
            AccessLogSpread al = null;
            for (int i = 0; i < platformList.size(); i++) {
                map = new HashMap<>();
                //根据平台的信息取出集合中的结果
                platformData = new ArrayList<>();
                Predicate platPredicate = new MyPredicate("platformId", platformList.get(i).getId());
                List<AccessLogSpread> select = (List<AccessLogSpread>) CollectionUtils.select(list, platPredicate);
                for (int j = 0; j < xAxis.size(); j++) {
                    al = new AccessLogSpread();
                    for (int k = 0; k < select.size(); k++) {
                        String str = timeFormat.format(select.get(k).getStarttime()).substring(0, 2);
                        int parseInt = Integer.parseInt(str) + 1;
                        if ((Integer.parseInt(xAxis.get(j).replaceAll("[^x00-xff]*", ""))) == (parseInt)) {
                            select.get(k).setHour(xAxis.get(j));
                            platformData.add(j, select.get(k));
                            select.remove(k);
                        }
                    }
                    if (platformData.size() == j) {
                        al.setHour(xAxis.get(j));
                        al.setPv(0);
                        al.setUv(0);
                        platformData.add(j, al);
                    }

                }
                map.put(platformList.get(i).getName(), platformData);
                result.add(map);
            }

        }
        catch (Exception e) {
        }
        return result;
    }

    /**
     * {查询某一周的访问量}
     * 
     * @param weekDays 日期 2017-06-12T2016-06-18
     * @return
     * @author: wangwt
     */
    @Override
    public List<Map<String, Object>> getAmountOfAccessByWeek(String weekDays) {
        String startTime = weekDays.split("T")[0];
        String endTime = weekDays.split("T")[1];
        List<AccessLogSpread> list = accessLogSpreadDao.getAmountOfAccessByWeek(startTime, endTime);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            //获取两个日期内的所有日期
            List<String> time = CycleTimeUtils.getDatesBetweenTwoDate(startTime, endTime);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            /*List<AccessLogSpread> platformList = new ArrayList<>(list);
            for (int i = 0; i < platformList.size(); i++) {
                for (int j = platformList.size() - 1; j > i; j--) {
                    if (platformList.get(i).getPlatformId().equals(platformList.get(j).getPlatformId())) {
                        platformList.remove(j);
                    }
                }
            }*/
            //从平台表中获取所有可用平台信息
            Platform platform =new Platform();
            platform.setStatus(0);
            List<Platform> platformList = platformDao.list(platform);
            map = new HashMap<>();
            String[] names = new String[platformList.size()];
            for (int i = 0; i < platformList.size(); i++) {
                names[i] = "'" + platformList.get(i).getName() + "'";
            }
            map.put("platformName", names);
            result.add(map);
            //某个平台的数据集合
            List<AccessLogSpread> platformData = null;
            AccessLogSpread al = null;
            for (int i = 0; i < platformList.size(); i++) {
                map = new HashMap<>();
                //根据平台的信息取出集合中的结果
                platformData = new ArrayList<>();
                Predicate platPredicate = new MyPredicate("platformId", platformList.get(i).getId());
                List<AccessLogSpread> select = (List<AccessLogSpread>) CollectionUtils.select(list, platPredicate);
                for (int j = 0; j < time.size(); j++) {
                    al = new AccessLogSpread();
                    for (int k = 0; k < select.size(); k++) {
                        if ((time.get(j).replaceAll("'", "")).equals(dayFormat.format(select.get(k).getAccesstime()))) {
                            platformData.add(j, select.get(k));
                            select.remove(k);
                        }
                    }
                    if (platformData.size() == j) {
                        al.setAccesstime(dayFormat.parse(time.get(j).replaceAll("'", "")));
                        al.setPv(0);
                        al.setUv(0);
                        platformData.add(j, al);
                    }

                }
                map.put(platformList.get(i).getName(), platformData);
                result.add(map);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * {查询某一个月的访问量}
     * 
     * @param month 日期 2017-07
     * @return
     * @author: wangwt
     */
    @Override
    public List<Map<String, Object>> getAmountOfAccessByMonth(String month) {
        List<AccessLogSpread> list = accessLogSpreadDao.getAmountOfAccessByMonth(month);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(month);
            List<String> time = CycleTimeUtils.getAllDaysMonthByDate(date);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            /*List<AccessLogSpread> platformList = new ArrayList<>(list);
            for (int i = 0; i < platformList.size(); i++) {
                for (int j = platformList.size() - 1; j > i; j--) {
                    if (platformList.get(i).getPlatformId().equals(platformList.get(j).getPlatformId())) {
                        platformList.remove(j);
                    }
                }
            }
            if(platformList.size()==0){
            	platformList =new ArrayList<>(getPlatName()); 
            }*/
            //从平台表中获取所有可用平台信息
            Platform platform =new Platform();
            platform.setStatus(0);
            List<Platform> platformList = platformDao.list(platform);
            map = new HashMap<>();
            String[] names = new String[platformList.size()];
            for (int i = 0; i < platformList.size(); i++) {
                names[i] = "'" + platformList.get(i).getName() + "'";
            }
            map.put("platformName", names);
            result.add(map);
            //某个平台的数据集合
            List<AccessLogSpread> platformData = null;
            AccessLogSpread al = null;
            for (int i = 0; i < platformList.size(); i++) {
                map = new HashMap<>();
                //根据平台的信息取出集合中的结果
                platformData = new ArrayList<>();
                Predicate platPredicate = new MyPredicate("platformId", platformList.get(i).getId());
                List<AccessLogSpread> select = (List<AccessLogSpread>) CollectionUtils.select(list, platPredicate);
                for (int j = 0; j < time.size(); j++) {
                    al = new AccessLogSpread();
                    for (int k = 0; k < select.size(); k++) {
                        if ((time.get(j).replaceAll("'", "")).equals(dayFormat.format(select.get(k).getAccesstime()))) {
                            platformData.add(j, select.get(k));
                            select.remove(k);
                        }
                    }
                    if (platformData.size() == j) {
                        al.setAccesstime(dayFormat.parse(time.get(j).replaceAll("'", "")));
                        al.setPv(0);
                        al.setUv(0);
                        platformData.add(j, al);
                    }

                }
                map.put(platformList.get(i).getName(), platformData);
                result.add(map);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * {获取两个日期内的所有日期}
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author: wangwt
     */
    private List<String> getDatesBetweenTwoDate(String startTime, String endTime) {
        List<String> list = new ArrayList<>();
        try {
            Date beginDate = dayFormat.parse(startTime);
            Date endDate = dayFormat.parse(endTime);
            List<Date> lDate = new ArrayList<Date>();
            lDate.add(beginDate);
            //把开始时间加入集合 
            Calendar cal = Calendar.getInstance();
            //使用给定的 Date 设置此 Calendar 的时间 
            cal.setTime(beginDate);
            boolean bContinue = true;
            while (bContinue) {
                //根据日历的规则，为给定的日历字段添加或减去指定的时间量 
                cal.add(Calendar.DAY_OF_MONTH, 1);
                // 测试此日期是否在指定日期之后 
                if (endDate.after(cal.getTime())) {
                    lDate.add(cal.getTime());
                }
                else {
                    break;
                }
            }
            //把结束时间加入集合
            lDate.add(endDate);
            for (Date date : lDate) {
                list.add(dayFormat.format(date));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getAmountOfAccessByYear(String year) {
        List<AccessLogSpread> list = accessLogSpreadDao.getAmountOfAccessByYear(year);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            List<String> time = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                String str = "'" + year + "-";
                if (i < 10) {
                    str += "0" + i + "'";
                }
                else {
                    str += i + "'";
                }
                time.add(str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            /*List<AccessLogSpread> platformList = new ArrayList<>(list);
            for (int i = 0; i < platformList.size(); i++) {
                for (int j = platformList.size() - 1; j > i; j--) {
                    if (platformList.get(i).getPlatformId().equals(platformList.get(j).getPlatformId())) {
                        platformList.remove(j);
                    }
                }
            }
            if(platformList.size()==0){
            	platformList =new ArrayList<>(getPlatName()); 
            }*/
            //从平台表中获取所有可用平台信息
            Platform platform =new Platform();
            platform.setStatus(0);
            List<Platform> platformList = platformDao.list(platform);
            map = new HashMap<>();
            String[] names = new String[platformList.size()];
            for (int i = 0; i < platformList.size(); i++) {
                names[i] = "'" + platformList.get(i).getName() + "'";
            }
            map.put("platformName", names);
            result.add(map);
            //某个平台的数据集合
            List<AccessLogSpread> platformData = null;
            AccessLogSpread al = null;
            for (int i = 0; i < platformList.size(); i++) {
                map = new HashMap<>();
                //根据平台的信息取出集合中的结果
                platformData = new ArrayList<>();
                Predicate platPredicate = new MyPredicate("platformId", platformList.get(i).getId());
                List<AccessLogSpread> select = (List<AccessLogSpread>) CollectionUtils.select(list, platPredicate);
                for (int j = 0; j < time.size(); j++) {
                    al = new AccessLogSpread();
                    for (int k = 0; k < select.size(); k++) {
                        if ((time.get(j).replaceAll("'", "")).equals(select.get(k).getAccessMonth())) {
                            select.get(k).setAccessMonth(time.get(j).replaceAll("'", ""));
                            platformData.add(j, select.get(k));
                            select.remove(k);
                        }
                    }
                    if (platformData.size() == j) {
                        al.setAccessMonth(time.get(j).replaceAll("'", ""));
                        al.setPv(0);
                        al.setUv(0);
                        platformData.add(j, al);
                    }

                }
                map.put(platformList.get(i).getName(), platformData);
                result.add(map);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}