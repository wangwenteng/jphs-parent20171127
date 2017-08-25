package com.jinpaihushi.jphs.tijian.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.tijian.model.TijianHospital;

/**
 * 
 * @author scj
 * @date 2017-08-08 15:34:53
 * @version 1.0
 */
@Repository("tijianHospitalDao")
public interface TijianHospitalDao extends BaseDao<TijianHospital> {
	
	List<Map<String,Object>> getAllHospital(String platformId);
	
}
