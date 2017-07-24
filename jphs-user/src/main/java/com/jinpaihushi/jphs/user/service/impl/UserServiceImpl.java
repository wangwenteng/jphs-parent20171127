package com.jinpaihushi.jphs.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sequence.dao.SequenceDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 17:15:32
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private SequenceDao sequenceDao;
	@Override
	protected BaseDao<User> getDao(){
		return userDao;
	}

	@Override
	public User getUserDetail(String id) {
		// TODO Auto-generated method stub
		User user = userDao.getUserDetail(id);
		return user;
	}

	@Override
	public Page<User> userList(User user) {
		// TODO Auto-generated method stub
		return userDao.userList(user);
	}

	@Override
	public User findUser(User user) {
		// TODO Auto-generated method stub
		return userDao.findUser(user);
	}

	@Override
	public String insertUser(User user) {
		String userId = sequenceDao.getCurrentVal("user");
		user.setId(userId);
		int i = userDao.insert(user);
		if(i>0){
			return userId;
		}
		return "";
	}
	
}