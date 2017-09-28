package com.jinpaihushi.jphs.transaction.service;

import java.util.List;
import java.util.Map;

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

    String refund(Transaction transaction, String orderNo, String cancelOrderId);

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