package com.jinpaihushi.jphs.account.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 15:40:16
 * @version 1.0
 */
public interface AccountService extends BaseService<Account> {

    List<Map<String, Object>> getMonthList(Map<String, Object> map);

}