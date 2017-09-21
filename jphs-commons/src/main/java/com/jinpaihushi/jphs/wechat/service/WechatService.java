package com.jinpaihushi.jphs.wechat.service;

import com.jinpaihushi.jphs.wechat.model.Wechat;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-09-03 09:50:11
 * @version 1.0
 */
public interface WechatService extends BaseService<Wechat> {

	Wechat getUnusedAccessToken(Integer type);

	String getMenuShare(String url_p);
	
	String getWXUserInfo(String code);
	
	void getTokens();
	
	int getUserWecthIfFollow(String openId);
}