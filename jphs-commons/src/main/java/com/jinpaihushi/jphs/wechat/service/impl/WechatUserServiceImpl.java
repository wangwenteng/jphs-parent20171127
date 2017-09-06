package com.jinpaihushi.jphs.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.wechat.dao.WechatUserDao;
import com.jinpaihushi.jphs.wechat.model.WechatUser;
import com.jinpaihushi.jphs.wechat.service.WechatUserService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-04 16:26:15
 * @version 1.0
 */
@Service("wechatUserService")
public class WechatUserServiceImpl extends BaseServiceImpl<WechatUser> implements WechatUserService{

	@Autowired
	private WechatUserDao wechatUserDao;
	
	@Override
	protected BaseDao<WechatUser> getDao(){
		return wechatUserDao;
	}

}