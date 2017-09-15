package com.jinpaihushi.jphs.withdraw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.dao.AccountDao;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.withdraw.dao.WithdrawCashDao;
import com.jinpaihushi.jphs.withdraw.model.WithdrawCash;
import com.jinpaihushi.jphs.withdraw.service.WithdrawCashService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @version 1.0
 */
@Service("withdrawCashService")
public class WithdrawCashServiceImpl extends BaseServiceImpl<WithdrawCash> implements WithdrawCashService {

    @Autowired
    private WithdrawCashDao withdrawCashDao;

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountDao accountDao;

    @Override
    protected BaseDao<WithdrawCash> getDao() {
        return withdrawCashDao;
    }

    @Override
    public int withdrawals(WithdrawCash withdrawCash) {
        //事务模板
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            public String doInTransaction(final TransactionStatus status) {
                try {
                    Double cash_withdrawal = 0.00;
                    //                    Transaction tr = new Transaction();
                    //                    tr.setWithdraw(0);
                    //                    tr.setOperate(4);
                    //                    tr.setCreatorId(withdrawCash.getCreatorId());
                    //查询该护士的可以提现的交易记录
                    List<Map<String, Object>> list = transactionDao.listWithdraw(withdrawCash.getCreatorId());
                    //判断提交的金额
                    String[] orderIds = new String[list.size()];
                    for (int k = 0; k < list.size(); k++) {
                        Object object = list.get(k).get("amount");
                        cash_withdrawal = DoubleUtils.add(cash_withdrawal, Double.parseDouble(object.toString()));
                        orderIds[k] = (String) list.get(k).get("order_id");
                    }
                    if (DoubleUtils.sub(cash_withdrawal, withdrawCash.getAmount()) < 0) {
                        return "0";
                    }
                    //插入提现记录
                    withdrawCash.setStatus(0);
                    withdrawCash.setId(UUIDUtils.getId());
                    withdrawCash.setCreateTime(new Date());
                    int i = withdrawCashDao.insert(withdrawCash);
                    //修改交易表状态
                    if (i > 0) {
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("orderId", orderIds);
                        params.put("userId", withdrawCash.getCreatorId());
                        i = transactionDao.updateWithdrawals(params);
                    }
                    //扣除余额的值
                    Account account = new Account();
                    account.setCreatorId(withdrawCash.getCreatorId());
                    account = accountDao.load(account);
                    account.setBalance(DoubleUtils.sub(account.getBalance(), withdrawCash.getAmount()));
                    accountDao.update(account);
                    //插入提现的交易记录
                    Transaction tr = new Transaction();
                    tr.setWithdraw(1);
                    tr.setOperate(1);
                    tr.setCreatorId(withdrawCash.getCreatorId());
                    tr.setCreatorName(withdrawCash.getCreatorName());
                    tr.setCreateTime(new Date());
                    tr.setOperateSource(1);
                    tr.setRemark("提现");
                    tr.setScore(0);
                    tr.setAmount(withdrawCash.getAmount());
                    tr.setId(UUIDUtils.getId());
                    tr.setStatus(0);
                    transactionDao.insert(tr);

                    return i + "";
                }
                catch (Exception e) {

                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
            }

        });
        return (int) Integer.parseInt(rs);
    }
}