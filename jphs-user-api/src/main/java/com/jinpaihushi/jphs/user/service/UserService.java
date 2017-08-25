package com.jinpaihushi.jphs.user.service;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 17:15:32
 * @version 1.0
 */
public interface UserService extends BaseService<User> {

	User getUserDetail(String id);
	
	Page<User> userList(User user);
	/**
	 * 通过手机号密码获取用户基本信息
	 * @param user
	 * @return
	 */
	User findUser(User user);
	
	User queryUser(User user);
	String insertUser(User user);

}