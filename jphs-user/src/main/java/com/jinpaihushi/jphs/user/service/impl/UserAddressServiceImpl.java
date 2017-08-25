package com.jinpaihushi.jphs.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.user.dao.UserAddressDao;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.service.UserAddressService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 15:09:14
 * @version 1.0
 */
@Service("userAddressService")
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddress> implements UserAddressService{

	@Autowired
	private UserAddressDao userAddressDao;
	
	@Override
	protected BaseDao<UserAddress> getDao(){
		return userAddressDao;
	}

	@Override
	public List<UserAddress> getReceiveAddress(UserAddress userAddress) {
		// TODO Auto-generated method stub
		return userAddressDao.getReceiveAddress(userAddress);
	}

}