package com.jinpaihushi.jphs.person.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.person.model.PersonGroup;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-01 14:02:33
 * @version 1.0
 */
@Repository("personGroupDao")
public interface PersonGroupDao extends BaseDao<PersonGroup> {

    List<PersonGroup> listNurse(PersonGroup query);

}
