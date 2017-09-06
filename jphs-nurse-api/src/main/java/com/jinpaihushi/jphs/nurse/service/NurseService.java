package com.jinpaihushi.jphs.nurse.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.goods.model.Grade;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author fengrz
 * @date 2017-06-09 08:51:43
 * @version 1.0
 */
public interface NurseService extends BaseService<Nurse> {

    Page<Nurse> getNurseDetail(Nurse nurse);

    String insertNurse(Nurse nurse);

    String updateNurse(Nurse nurse, List<JobtitleType> jobtitleType);

    List<Map<String, Object>> queryOrder(Nurse nurse);

    List<Map<String, Object>> queryIncome(Nurse nurse);

    List<Nurse> getSomeNurse(Nurse nurse);

    /**
     * 获取护士的职称
     * @param nurse
     * @return
     */
    List<JobtitleType> getNurseJobtitle(Nurse nurse);

    /**
     * 护士的基本信息
     * @param userId 护士id
     * @param lon 经度
     * @param lat 纬度
     * @return
     */
    List<Map<String, Object>> getBasicInfo(Map<String, Object> map);

    /**
     * 
     * @param map
     *      lon、lat 经纬度
     *      goodsId 服务id
     *      calendar 日期
     *      h 具体的时间点
     * @return
     */
    List<Map<String, Object>> getRecommendNurse(Map<String, Object> query);

    /**
     * 获取护士的发布的服务的价格列表
     * @param query
     * @return
     */
    List<Grade> getNurseServicePrice(Map<String, Object> query);

}