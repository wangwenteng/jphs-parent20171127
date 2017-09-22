package com.jinpaihushi.jphs.jobtitle.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;

/**
 * 
 * @author wangwt
 * @date 2017-07-13 13:43:00
 * @version 1.0
 */
@Repository("jobtitleDao")
public interface JobtitleDao extends BaseDao<Jobtitle> {

    int checkName(Jobtitle jobtitle);

    List<Jobtitle> jobtitleSelectList(String type);

    List<Map<String, Object>> getBasePrice(String jobtitleTypeId);
}
