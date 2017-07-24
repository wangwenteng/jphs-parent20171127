package com.jinpaihushi.jphs.verification.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.verification.model.Verification;

/**
 * 
 * @author wangwt
 * @date 2017-07-12 11:55:26
 * @version 1.0
 */
@Repository("verificationDao")
public interface VerificationDao extends BaseDao<Verification> {
	
	int countVerification(String phone);

	Verification getLastRecord(String phone);
	
}
