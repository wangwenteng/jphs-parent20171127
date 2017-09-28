package com.jinpaihushi.jphs.worktime.dao;

import java.util.List;
import java.util.Map;

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

    /**
     * 清空所有的日程
     */
    int deleteAll();

    int updateByUserId(Worktime userWorktime);
    /**
     * 修改护士的日程
     * Calendar 时间
     * h_k 时间段
     * h_v 时间段的值
     * userid 用户id
     * @param map
     * @return
     */
    int editDateByUserId(Map<String,Object> map);
    List<Worktime> listByQuery(Worktime query);

}
