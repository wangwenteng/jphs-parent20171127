package com.jinpaihushi.jphs.transaction.dao;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 18:40:45
 * @version 1.0
 */
@Repository("transactionDao")
public interface TransactionDao extends BaseDao<Transaction> {
	
	
	Page<Transaction> getUserInfo(Transaction transaction);

	boolean refund(Transaction transaction);
}
