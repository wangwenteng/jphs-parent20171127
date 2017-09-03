package com.jinpaihushi.jphs.transaction.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 18:40:45
 * @version 1.0
 */
@Service("transactionService")
public class TransactionServiceImpl extends BaseServiceImpl<Transaction> implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    protected BaseDao<Transaction> getDao() {
        return transactionDao;
    }

    @Override
    public Page<Transaction> getUserInfo(Transaction transaction) {
        // TODO Auto-generated method stub
        return transactionDao.getUserInfo(transaction);
    }

    @Override
    public boolean refund(Transaction transaction) {
        // TODO Auto-generated method stub
        return transactionDao.refund(transaction);
    }

    @Override
    public Map<String, Object> incomeSummary(Map<String, Object> map) {
        return transactionDao.incomeSummary(map);
    }

    @Override
    public Map<String, Object> incomeSummaryMonth(Map<String, Object> query) {
        return transactionDao.incomeSummaryMonth(query);
    }

    @Override
    public List<Map<String, Object>> incomeBreakdownMonth(Map<String, Object> query) {
        return transactionDao.incomeBreakdownMonth(query);
    }

}