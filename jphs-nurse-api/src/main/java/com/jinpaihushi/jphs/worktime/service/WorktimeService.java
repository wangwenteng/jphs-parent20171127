package com.jinpaihushi.jphs.worktime.service;

import java.util.List;

import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-08-16 11:08:44
 * @version 1.0
 */
public interface WorktimeService extends BaseService<Worktime> {
    /**
     * 更新用户选择的上门时间
     * 
     * @return 1 成功 0 失败
     */
    void updateUserWorkTime();

    /**
     * 护士的日程安排
     * @param userId 护士id
     * @param isNextWeek 是否是下周
     * @return
     */
    List<Worktime> findWorkTime(String userId);

    /**
     * 更新护士的日程时间
     */
    void updateAllNurseWorkTime();

    int insertNurseWorkTime(String userId);

    /**
     * 插入用户的日程安排
     * @return
     */
    int insertUserWorkTime();

    /**
     * 获取用户的工作时间
     * @param productId
     * @return
     */
    List<Worktime> findUserWorkTime(String productId);

    /**
     * 护士主页的可约时间
     * @param userId
     * @return
     */
    List<Worktime> getNurseWorktime(String userId, String productId);

    int insertAllWorkTime();

}