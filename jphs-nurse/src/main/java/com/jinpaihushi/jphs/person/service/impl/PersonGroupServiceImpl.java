package com.jinpaihushi.jphs.person.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.person.dao.PersonGroupDao;
import com.jinpaihushi.jphs.person.model.PersonGroup;
import com.jinpaihushi.jphs.person.service.PersonGroupService;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-01 14:02:33
 * @version 1.0
 */
@Service("personGroupService")
public class PersonGroupServiceImpl extends BaseServiceImpl<PersonGroup> implements PersonGroupService {

    @Autowired
    private PersonGroupDao personGroupDao;

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<PersonGroup> getDao() {
        return personGroupDao;
    }

    @Override
    public int createDefaultGroup(String userId) {
        PersonGroup personGroup = new PersonGroup();
        personGroup.setName("我的护士");
        personGroup.setIsDefault(1);
        personGroup.setCreatorId(userId);
        User user = userDao.loadById(userId);
        personGroup.setCreatorName(user.getName());
        personGroup.setCreateTime(new Date());
        personGroup.setStatus(0);
        personGroup.setId(UUIDUtils.getId());
        int i = personGroupDao.insert(personGroup);
        return i;
    }

    @Override
    public List<PersonGroup> listNurse(PersonGroup query) {
        return personGroupDao.listNurse(query);
    }

}