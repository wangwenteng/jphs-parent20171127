package com.jinpaihushi.jphs.nurse.service;

import java.util.HashMap;
import java.util.Map;

import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-14 10:02:02
 * @version 1.0
 */
public interface NurseCommodityService extends BaseService<NurseCommodity> {

	Integer getBrowser(NurseCommodity nurseCommodity);
	
	Integer getShareNumber(NurseCommodity nurseCommodity);
	
	boolean updateBrowser(NurseCommodity nurseCommodity);
	
	boolean updateShareNumber(NurseCommodity nurseCommodity);

	boolean updateCount(Map<String, Object> map);

}