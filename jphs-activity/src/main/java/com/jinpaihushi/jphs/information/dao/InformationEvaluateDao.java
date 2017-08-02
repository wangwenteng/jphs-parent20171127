package com.jinpaihushi.jphs.information.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.information.model.InformationEvaluate;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Repository("informationEvaluateDao")
public interface InformationEvaluateDao extends BaseDao<InformationEvaluate> {
	
	List<InformationEvaluate> listInfo(@Param("informationEvaluate")InformationEvaluate informationEvaluate,@Param("userId")String userId);
	
}
