package com.jinpaihushi.jphs.nurse.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;

/**
 * 
 * @author yangsong
 * @date 2017-08-14 10:02:02
 * @version 1.0
 */
@Repository("nurseCommodityDao")
public interface NurseCommodityDao extends BaseDao<NurseCommodity> {

	Integer getBrowser(NurseCommodity nurseCommodity);
	
	Integer getShareNumber(NurseCommodity nurseCommodity);

	boolean updateBrowser(NurseCommodity nurseCommodity);

	boolean updateShareNumber(NurseCommodity nurseCommodity);

	boolean updateCount(Map<String, Object> hashMap);
	
}
