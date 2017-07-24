package com.jinpaihushi.jphs.account.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.model.Account;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 15:40:16
 * @version 1.0
 */
@Repository("accountDao")
public interface AccountDao extends BaseDao<Account> {
	
	
	
}
