package com.jinpaihushi.jphs.user.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jinpaihushi.jphs.user.dao.UserMapper;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Resource
	UserMapper userMapper;
	
	@Override
	public User selectByPrimaryKey(String id) {
		
		User user = userMapper.selectByPrimaryKey(id);		
		
		return user;
	}

	@Override
	public int insert(User user) {
		
		user.setId(UUID.randomUUID().toString());
		System.out.println("user.getId() = "+user.getId());
		int result = userMapper.insert(user);
		
		return result;
	}

	@Override
	public int updateByPrimaryKey(User user) {
		
		return userMapper.updateByPrimaryKey(user);
	}

}
