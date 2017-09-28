package com.jinpaihushi.jphs.transaction.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.dao.AccountDao;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.pay.alipay.AlipayRefund;
import com.jinpaihushi.pay.wechatpay.WeiXinPayRefund;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 18:40:45
 * @version 1.0
 */
@Service("transactionService")
public class TransactionServiceImpl extends BaseServiceImpl<Transaction> implements TransactionService {
	@Autowired
	private PlatformTransactionManager txManager;

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private AccountDao accountDao;

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
	public String refund(Transaction transaction, String totalMoney) {
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
		String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
			// 事务模板
			public String doInTransaction(final TransactionStatus status) {
				try {
					// 判断支付方式<!-- (1支付宝，2微信，3余额，4银联，5vip卡支付) -->
					// 余额支付
					if (transaction.getPayType() == 3) {
						// 插入退款信息
						transaction.setId(UUIDUtils.getId());
						transaction.setType(1);
						transaction.setCreateTime(new Date());
						transaction.setStatus(0);
						transaction.setWithdraw(0);
						// 退换余额
						Account account = new Account();
						account.setCreatorId(transaction.getCreatorId());
						Account load = accountDao.load(account);
						load.setBalance(DoubleUtils.add(load.getBalance(), transaction.getAmount()));
						accountDao.update(load);
					}
					// 微信支付
					if (transaction.getPayType() == 2) {

						WeiXinPayRefund.doPost(transaction.getOutTradeNo(), totalMoney,
								transaction.getAmount().toString());
					}
					// 支付宝支付
					if (transaction.getPayType() == 1) {
						AlipayRefund.doGet(transaction.getOutTradeNo(), transaction.getAmount().toString());
					}
					transactionDao.insert(transaction);
					return "1";
				} catch (Exception e) {

					e.printStackTrace();
					// 日志打印区
					status.setRollbackOnly();// 回滚
					return "0";
				}
			}
		});
		return rs;
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