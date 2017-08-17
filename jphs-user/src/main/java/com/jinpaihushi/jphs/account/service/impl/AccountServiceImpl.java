package com.jinpaihushi.jphs.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.dao.AccountDao;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 15:40:16
 * @version 1.0
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	protected BaseDao<Account> getDao(){
		return accountDao;
	}

	@Override
	public List<Map<String, Object>> getMonthList(Map<String, Object> map) {
		List<Map<String, Object>> result=accountDao.getMonthList(map);
		return result;
	}
}