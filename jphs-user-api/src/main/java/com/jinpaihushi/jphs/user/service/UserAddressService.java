package com.jinpaihushi.jphs.user.service;

import java.util.List;

import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 15:09:14
 * @version 1.0
 */
public interface UserAddressService extends BaseService<UserAddress> {

	List<UserAddress> getReceiveAddress(UserAddress userAddress);

	

}