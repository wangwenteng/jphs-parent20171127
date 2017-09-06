package com.jinpaihushi.jphs.wechat.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.wechat.model.Wechat;

/**
 * 
 * @author scj
 * @date 2017-09-03 09:50:11
 * @version 1.0
 */
@Repository("wecthDao")
public interface WechatDao extends BaseDao<Wechat> {
	//	   getUnusedAccessToken
	Wechat getUnusedAccessToken(Integer type);
	
}
