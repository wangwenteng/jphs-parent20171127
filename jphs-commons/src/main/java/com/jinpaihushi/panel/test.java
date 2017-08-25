package com.jinpaihushi.panel;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.utils.TransactionTemplateUtils;

public class test {
	private PlatformTransactionManager txManager;//创建事务管理器
	
	public int testTransaction(final String order, final String evaluation) {

		//事务模板
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
		String rs = (String)transactionTemplate.execute(new TransactionCallback<Object>() {     
			public String doInTransaction(final TransactionStatus status) {
				try {
					//功能区
					
					
				return null;
				}catch(Exception e){

					e.printStackTrace();
					//日志打印区
					status.setRollbackOnly();//回滚
					return "0";
				}
				}
			 
		});

		return (int)Integer.parseInt(rs);
	}
}
