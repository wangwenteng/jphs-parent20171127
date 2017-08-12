package com.jinpaihushi.jphs.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.user.model.UserAddress;

/**
 * 
 * @author yangsong
 * @date 2017-07-03 15:09:14
 * @version 1.0
 */
@Repository("userAddressDao")
public interface UserAddressDao extends BaseDao<UserAddress> {

	List<UserAddress> getReceiveAddress(UserAddress userAddress);
	
	
	
}
