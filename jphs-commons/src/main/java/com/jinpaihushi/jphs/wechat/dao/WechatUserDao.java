package com.jinpaihushi.jphs.wechat.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.wechat.model.WechatUser;

/**
 * 
 * @author scj
 * @date 2017-09-04 16:26:15
 * @version 1.0
 */
@Repository("wechatUserDao")
public interface WechatUserDao extends BaseDao<WechatUser> {
	
	
	
}
