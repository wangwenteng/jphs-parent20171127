package com.jinpaihushi.jphs.sysversion.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sysversion.model.Sysversion;

/**
 * 
 * @author scj
 * @date 2017-08-08 10:46:13
 * @version 1.0
 */
@Repository("sysversionDao")
public interface SysversionDao extends BaseDao<Sysversion> {
	
	Sysversion appVersion(@Param("type")Integer type);
	
}
