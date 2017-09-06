package com.jinpaihushi.jphs.person.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.person.dao.PersonManagerDao;
import com.jinpaihushi.jphs.person.model.PersonManager;
import com.jinpaihushi.jphs.person.service.PersonManagerService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-01 14:02:34
 * @version 1.0
 */
@Service("personManagerService")
public class PersonManagerServiceImpl extends BaseServiceImpl<PersonManager> implements PersonManagerService {

    @Autowired
    private PersonManagerDao personManagerDao;

    @Override
    protected BaseDao<PersonManager> getDao() {
        return personManagerDao;
    }

    @Override
    public int updateIsRead(String userId) {
        return personManagerDao.updateIsRead(userId);
    }

    @Override
    public List<Map<String, Object>> getNurseList(Map<String, Object> map) {

        return personManagerDao.getNurseList(map);
    }

    @Override
    public List<PersonManager> noticeList(PersonManager manager) {
        return personManagerDao.noticeList(manager);
    }

    @Override
    public List<Map<String, Object>> searchNurse(Map<String, Object> query) {
        return personManagerDao.searchNurse(query);
    }

    @Override
    public void updateLeaderStatus(PersonManager manager) {
        personManagerDao.updateLeaderStatus(manager);
    }

}