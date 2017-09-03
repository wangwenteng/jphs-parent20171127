package com.jinpaihushi.jphs.transaction.dao;

import java.util.List;
import java.util.Map;

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

    /**
     * @param map
     *      userId 護士id
     * @return
     */
    Map<String, Object> incomeSummary(Map<String, Object> map);

    Map<String, Object> incomeSummaryMonth(Map<String, Object> query);

    /**
     * 操作明细
     * @param query
     *          userId  用户id
     *          Opera 操作 1.提现, 2.充值, 3.消费 , 4.收入, 5.系统调整
     *          month 月份
     * @return
     */
    List<Map<String, Object>> incomeBreakdownMonth(Map<String, Object> query);
}
