package com.jinpaihushi.jphs.price.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.price.model.PriceNurse;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-16 15:07:51
 * @version 1.0
 */
public interface PriceNurseService extends BaseService<PriceNurse> {

<<<<<<< HEAD
 
	boolean deleteByUserAndGoods(PriceNurse pn);
 
	/**
	 * 
	 * @param userId 用户id
	 * @return
	 */
	List<Map<String, Object>> getServiceItems(String userId);

	boolean updatePriceNurse(PriceNurse pn);
=======
	/**
	 * 
	 * @param userId 用户id
	 * @return
	 */
	List<Map<String, Object>> getServiceItems(String userId);

>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
	

}