package com.jinpaihushi.jphs.user.service;

import com.jinpaihushi.jphs.user.model.User;

public interface TestService {
	
	/**
	 * 通过主键ID查找
	 * @param id
	 * @return
	 */
	public User selectByPrimaryKey(String id);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int insert(User user);
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int updateByPrimaryKey(User user);
	
	
}
