package com.jinpaihushi.jphs.worktime.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseDao;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.worktime.dao.WorktimeDao;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-08-16 11:08:44
 * @version 1.0
 */
@Service("worktimeService")
@Component
@Configurable
@EnableScheduling
public class WorktimeServiceImpl extends BaseServiceImpl<Worktime> implements WorktimeService {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private PlatformTransactionManager txManager;//创建事务管理器

    @Autowired
    private WorktimeDao worktimeDao;

    @Autowired
    private NurseDao nurseDao;

    @Override
    protected BaseDao<Worktime> getDao() {
        return worktimeDao;
    }

    @Value("${WorkTimeBy0}")
    private String WorkTimeBy0;

    @Value("${WorkTimeBy1}")
    private String WorkTimeBy1;

    @Value("${WorkTimeBy2}")
    private String WorkTimeBy2;

    @Override
    public void updateUserWorkTime() {
        try {
            System.out.println("开始更新用户日程信息-------");
            // 将当前日期的数据删除（如果是每天凌晨执行）
            String yesterday = CycleTimeUtils.getPastDate(1);
            worktimeDao.deleteByCalendar(yesterday);
            // 插入7天后的数据
            String newDays = CycleTimeUtils.getFetureDate(6);
            String week = CycleTimeUtils.getWeekOfDate(sdf.parse(newDays));
            Worktime insert = new Worktime();
            insert.setId(UUIDUtils.getId());
            insert.setCalendar(newDays);
            insert.setUserid("0");
            insert.setWeek(week);
            insert.setH9(0);
            insert.setH10(0);
            insert.setH11(0);
            insert.setH12(0);
            insert.setH13(0);
            insert.setH14(0);
            insert.setH15(0);
            insert.setH16(0);
            insert.setH17(0);
            insert.setH18(0);
            insert.setH19(0);
            insert.setH20(0);
            insert.setH21(0);
            insert.setCreatetime(new Date());
            worktimeDao.insert(insert);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Worktime> findWorkTime(String userId) {
        List<Worktime> result = new ArrayList<Worktime>();
        Worktime query = new Worktime();
        query.setUserid(userId);
        query.setOrderby("calendar ASC");
        result = worktimeDao.listByQuery(query);
        return result;
    }

    @Override
    public void updateAllNurseWorkTime() {
        System.out.println("开始更新护士日程信息-------");
        List<String> list = worktimeDao.getAllNurse();
        String startDay = CycleTimeUtils.getFetureDate(7);
        String endDays = CycleTimeUtils.getFetureDate(13);
        // 新的一周的时间
        List<String> timeList = CycleTimeUtils.getDatesBetweenTwoDate(startDay, endDays);
        // 删除所有护士过期的数据
        worktimeDao.deleteExpired();
        // 给所有的护士插入新的一周数据
        List<Worktime> insert = new ArrayList<>();
        Worktime worktime = null;
        for (String string : list) {
            for (String time : timeList) {
                worktime = new Worktime();
                worktime.setId(UUIDUtils.getId());
                worktime.setCalendar(time);
                worktime.setWeek(CycleTimeUtils.getWeekOfDate(DateUtils.parse(time)));
                worktime.setUserid(string);
                worktime.setH9(1);
                worktime.setH10(1);
                worktime.setH11(1);
                worktime.setH12(1);
                worktime.setH13(1);
                worktime.setH14(1);
                worktime.setH15(1);
                worktime.setH16(1);
                worktime.setH17(1);
                worktime.setH18(1);
                worktime.setH19(1);
                worktime.setH20(1);
                worktime.setH21(1);
                worktime.setCreatetime(new Date());
                insert.add(worktime);
            }
        }
        worktimeDao.inserts(insert);
    }

    @Override
    public int insertUserWorkTime() {
        String startDay = CycleTimeUtils.getFetureDate(0);
        String endDays = CycleTimeUtils.getFetureDate(6);
        List<String> timeList = CycleTimeUtils.getDatesBetweenTwoDate(startDay, endDays);
        List<Worktime> insert = new ArrayList<>();
        Worktime worktime = null;
        for (String time : timeList) {
            worktime = new Worktime();
            worktime.setId(UUIDUtils.getId());
            worktime.setCalendar(time);
            worktime.setWeek(CycleTimeUtils.getWeekOfDate(DateUtils.parse(time)));
            worktime.setUserid("0");
            worktime.setH9(1);
            worktime.setH10(1);
            worktime.setH11(1);
            worktime.setH12(1);
            worktime.setH13(1);
            worktime.setH14(1);
            worktime.setH15(1);
            worktime.setH16(1);
            worktime.setH17(1);
            worktime.setH18(1);
            worktime.setH19(1);
            worktime.setH20(1);
            worktime.setH21(1);
            worktime.setCreatetime(new Date());
            insert.add(worktime);
        }
        return worktimeDao.inserts(insert);
    }

    @Override
    public int insertNurseWorkTime(String userId) {
        Date today = new Date();
        // 得到周一的日期
        String startDays = CycleTimeUtils.getWeekStartDay(today);

        Date startDay = DateUtils.parse(startDays);
        // 计算当前日期跟周一的日期相差的天数
        int w = CycleTimeUtils.daysOfTwo(startDay, today);
        // 获取下一周的最后一天的日期
        String endDays = CycleTimeUtils.getFetureDate(13 - w);
        // 根据开始日期和结束日期得到之间的所有日期字符串
        List<String> list = CycleTimeUtils.getDatesBetweenTwoDate(startDays, endDays);
        Worktime worktime = null;
        int i = 0;
        for (String string : list) {
            worktime = new Worktime();
            worktime.setId(UUIDUtils.getId());
            worktime.setCalendar(string);
            worktime.setWeek(CycleTimeUtils.getWeekOfDate(DateUtils.parse(string)));
            worktime.setUserid(userId);
            worktime.setH9(1);
            worktime.setH10(1);
            worktime.setH11(1);
            worktime.setH12(1);
            worktime.setH13(1);
            worktime.setH14(1);
            worktime.setH15(1);
            worktime.setH16(1);
            worktime.setH17(1);
            worktime.setH18(1);
            worktime.setH19(1);
            worktime.setH20(1);
            worktime.setH21(1);
            worktime.setCreatetime(new Date());
            i = worktimeDao.insert(worktime);
        }
        return i;
    }

    @Override
    public List<Worktime> findUserWorkTime(String productId) {
        Worktime userWorktime = null;
        List<Worktime> result = new ArrayList<>();
        // 默认提前一个小时预约
        int hour = 1;
        if (productId.equals("135")) {
            hour = Integer.valueOf(WorkTimeBy0);
        }
        if (productId.equals("133")) {
            hour = Integer.valueOf(WorkTimeBy1);
        }
        if (productId.equals("137")) {
            hour = Integer.valueOf(WorkTimeBy2);
        }
        else {
            hour = 4;
        }
        // 当前时间
        Date date = new Date();
        try {
            System.out.println("date当前时间:" + format.format(date));
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            int hour24 = ca.get(Calendar.HOUR_OF_DAY);
            //判断当前时间的小时数
            if (hour24 < 20) {
                ca.add(Calendar.HOUR_OF_DAY, hour);
                System.out.println("date当前时间+" + hour + "小时后:" + format.format(ca.getTime()));
                hour24 = ca.get(Calendar.HOUR_OF_DAY);
            }
            else {
                System.out.println("date当前时间" + format.format(ca.getTime()));
                hour24 = 24;
            }
            userWorktime = new Worktime();
            userWorktime.setCalendar(sdf.format(date));
            userWorktime.setUserid("0");
            userWorktime = worktimeDao.load(userWorktime);
            hour24 = ca.get(Calendar.HOUR_OF_DAY);
            switch (hour24) {
            case 24:
                ;
            case 23:
                ;
            case 22:
                ;
            case 21:
                userWorktime.setH21(1);
            case 20:
                userWorktime.setH20(1);
            case 19:
                userWorktime.setH19(1);
            case 18:
                userWorktime.setH18(1);
            case 17:
                userWorktime.setH17(1);
            case 16:
                userWorktime.setH16(1);
            case 15:
                userWorktime.setH15(1);
            case 14:
                userWorktime.setH14(1);
            case 13:
                userWorktime.setH13(1);
            case 12:
                userWorktime.setH12(1);
            case 11:
                userWorktime.setH11(1);
            case 10:
                userWorktime.setH10(1);
            case 9:
                userWorktime.setH9(1);
            default:
                break;
            }
            int i = worktimeDao.update(userWorktime);
            if (i > 0) {
                userWorktime = new Worktime();
                userWorktime.setUserid("0");
                userWorktime.setOrderby("W.calendar ASC");
                result = worktimeDao.list(userWorktime);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Worktime> getNurseWorktime(String userId, String productId) {
        Worktime userWorktime = null;
        List<Worktime> result = new ArrayList<>();
        // 默认提前一个小时预约
        if (null == productId)
            productId = "";
        int hour = 1;
        if (productId.equals("135")) {
            hour = Integer.valueOf(WorkTimeBy0);
        }
        if (productId.equals("133")) {
            hour = Integer.valueOf(WorkTimeBy1);
        }
        if (productId.equals("137")) {
            hour = Integer.valueOf(WorkTimeBy2);
        }
        else {
            hour = 4;
        }
        // 当前时间
        Date date = new Date();
        try {
            System.out.println("date当前时间:" + format.format(date));
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            int hour24 = ca.get(Calendar.HOUR_OF_DAY);
            //判断当前时间的小时数
            if (hour24 < 20) {
                ca.add(Calendar.HOUR_OF_DAY, hour);
                System.out.println("date当前时间+" + hour + "小时后:" + format.format(ca.getTime()));
                hour24 = ca.get(Calendar.HOUR_OF_DAY);
            }
            else {
                System.out.println("date当前时间" + format.format(ca.getTime()));
                hour24 = 24;
            }
            userWorktime = new Worktime();
            userWorktime.setCalendar(sdf.format(date));
            userWorktime.setUserid(userId);
            userWorktime = worktimeDao.load(userWorktime);
            if (userWorktime != null) {
                switch (hour24) {
                case 24:
                    ;
                case 23:
                    ;
                case 22:
                    ;
                case 21:
                    userWorktime.setH21(1);
                case 20:
                    userWorktime.setH20(1);
                case 19:
                    userWorktime.setH19(1);
                case 18:
                    userWorktime.setH18(1);
                case 17:
                    userWorktime.setH17(1);
                case 16:
                    userWorktime.setH16(1);
                case 15:
                    userWorktime.setH15(1);
                case 14:
                    userWorktime.setH14(1);
                case 13:
                    userWorktime.setH13(1);
                case 12:
                    userWorktime.setH12(1);
                case 11:
                    userWorktime.setH11(1);
                case 10:
                    userWorktime.setH10(1);
                case 9:
                    userWorktime.setH9(1);
                default:
                    break;
                }
                int i = worktimeDao.update(userWorktime);
                if (i > 0) {
                    userWorktime = new Worktime();
                    userWorktime.setUserid(userId);
                    userWorktime.setCalendar(sdf.format(date));
                    userWorktime.setOrderby("W.calendar ASC");
                    result = worktimeDao.queryByTime(userWorktime);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertAllWorkTime() {
        //事务模板
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            public String doInTransaction(final TransactionStatus status) {
                try {
                    //获取所有审核通过的护士
                    Nurse nurse = new Nurse();
                    nurse.setStatus(1);
                    List<String> list = nurseDao.getNurseIdNotHaveTime();
                    //插入护士的日程安排
                    for (String result : list) {
                        insertNurseWorkTime(result);
                    }
                    return "1";
                }
                catch (Exception e) {

                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
            }

        });

        return (int) Integer.parseInt(rs);
    }

}