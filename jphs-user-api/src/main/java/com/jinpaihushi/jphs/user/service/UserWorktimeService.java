package com.jinpaihushi.jphs.user.service;

import java.util.List;

import com.jinpaihushi.jphs.user.model.UserWorktime;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 16:35:43
 * @version 1.0
 */
public interface UserWorktimeService extends BaseService<UserWorktime> {

	int updateUserWorkTime();

	List<UserWorktime> findWorkTime(String productId);

	

}