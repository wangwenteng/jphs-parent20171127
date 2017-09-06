package com.jinpaihushi.jphs.person.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.person.model.PersonManager;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-01 14:02:34
 * @version 1.0
 */
@Repository("personManagerDao")
public interface PersonManagerDao extends BaseDao<PersonManager> {

    int updateIsRead(String userId);

    List<Map<String, Object>> getNurseList(Map<String, Object> map);

    List<PersonManager> noticeList(PersonManager manager);

    List<Map<String, Object>> searchNurse(Map<String, Object> map);

    void updateLeaderStatus(PersonManager manager);
}
