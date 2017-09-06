package com.jinpaihushi.jphs.person.service;

import java.util.List;

import com.jinpaihushi.jphs.person.model.PersonGroup;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-01 14:02:33
 * @version 1.0
 */
public interface PersonGroupService extends BaseService<PersonGroup> {

    /**
     * 创建默认分组
     * @param userId
     * @return 1 创建成功  0 创建失败
     */
    int createDefaultGroup(String userId);

    /**
     * 所有分组及分组下的护士
     * @param query
     * @return
     */
    List<PersonGroup> listNurse(PersonGroup query);

}