package com.jinpaihushi.jphs.person.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.person.model.PersonManager;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-01 14:02:34
 * @version 1.0
 */
public interface PersonManagerService extends BaseService<PersonManager> {

    /**
     * 将改护士的未读信息修改为已读
     * @param userId 护士id
     * @return int 1 修改成功 0 修改失败
     */
    int updateIsRead(String userId);

    /**
     * @param map 
     *          personGroupId 分组id  
     *          search 为搜索添加的护士
     * @return
     */
    List<Map<String, Object>> getNurseList(Map<String, Object> map);

    List<PersonManager> noticeList(PersonManager manager);

    List<Map<String, Object>> searchNurse(Map<String, Object> query);

    void updateLeaderStatus(PersonManager manager);

}