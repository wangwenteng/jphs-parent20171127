package com.jinpaihushi.jphs.nurse.dao;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.model.NursePojo;

public interface NursePojoDao extends BaseDao<NursePojo> {
    /**
     * 
     * @param map
     *      lon、lat 经纬度
     *      goodsId 服务id
     *      calendar 日期
     *      h 具体的时间点
     * @return
     */
    List<Map<String, Object>> getRecommendNurse(Map<String, Object> map);
}
