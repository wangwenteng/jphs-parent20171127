package com.jinpaihushi.jphs.transaction.service;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 18:40:45
 * @version 1.0
 */
public interface TransactionService extends BaseService<Transaction> {

	Page<Transaction> getUserInfo(Transaction transaction);

	boolean refund(Transaction transaction);

	

}