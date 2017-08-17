package com.jinpaihushi.jphs.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.model.Nurse;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 10:05:23
 * @version 1.0
 */
@Repository("nurseDao")
public interface NurseDao extends BaseDao<Nurse> {

	Page<Nurse> getNurseDetail(@Param("nurse")Nurse nurse);
	
	List<Map<String, Object>> queryNurseOrder(Nurse nurse);
	List<Map<String, Object>> queryNurseIncome(Nurse nurse);

	List<Nurse> getSomeNurse(Nurse nurse);

	List<Map<String, Object>> getBasicInfo(Map<String, Object> map);
	
}
