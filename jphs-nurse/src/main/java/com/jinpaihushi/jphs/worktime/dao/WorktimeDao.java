package com.jinpaihushi.jphs.worktime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.worktime.model.Worktime;

/**
 * 
 * @author wangwt
 * @date 2017-08-16 11:08:43
 * @version 1.0
 */
@Repository("worktimeDao")
public interface WorktimeDao extends BaseDao<Worktime> {

    int deleteByCalendar(String yesterday);

    /**
     * 获取所有的护士
     * @return
     */
    List<String> getAllNurse();

    /**
     * 删除护士已过的日程
     * @return
     */
    int deleteExpired();

    List<Worktime> queryByTime(Worktime userWorktime);

}
