package com.jinpaihushi.jphs.access.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.access.model.AccessLog;

/**
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:41
 * @version 1.0
 */
@Repository("accessLogDao")
public interface AccessLogDao extends BaseDao<AccessLog> {

}
