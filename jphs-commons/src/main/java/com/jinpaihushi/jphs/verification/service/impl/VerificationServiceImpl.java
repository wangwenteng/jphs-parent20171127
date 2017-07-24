package com.jinpaihushi.jphs.verification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.verification.dao.VerificationDao;
import com.jinpaihushi.jphs.verification.model.Verification;
import com.jinpaihushi.jphs.verification.service.VerificationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-07-12 11:55:26
 * @version 1.0
 */
@Service("verificationService")
public class VerificationServiceImpl extends BaseServiceImpl<Verification> implements VerificationService{

	@Autowired
	private VerificationDao verificationDao;
	
	@Override
	protected BaseDao<Verification> getDao(){
		return verificationDao;
	}
	/**
	 * {根据手机号查询当天的发送次数}
	 * 
	 * @param phone 手机号
	 * @return 发送的次数
	 * @author: wangwt
	 */
	@Override
	public int countVerification(String phone) {
		return verificationDao.countVerification(phone);
	}
	/**
	 * {获取最后一条发送的短信}
	 * 
	 * @param phone 手机号 
	 * @return 短信内容
	 * @author: wangwt
	 */
	@Override
	public Verification getLastRecordByPhone(String phone) {
		return verificationDao.getLastRecord(phone);
	}
	
}