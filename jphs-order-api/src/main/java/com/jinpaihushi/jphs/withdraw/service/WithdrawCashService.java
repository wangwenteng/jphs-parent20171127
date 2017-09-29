package com.jinpaihushi.jphs.withdraw.service;

import com.jinpaihushi.jphs.withdraw.model.WithdrawCash;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @version 1.0
 */
public interface WithdrawCashService extends BaseService<WithdrawCash> {

    int withdrawals(WithdrawCash withdrawCash);

    WithdrawCash loadByDetail(String id);
    
}