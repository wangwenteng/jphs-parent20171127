package com.jinpaihushi.jphs.verification.service;

import com.jinpaihushi.jphs.verification.model.Verification;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-07-12 11:55:26
 * @version 1.0
 */
public interface VerificationService extends BaseService<Verification> {

	/**
	 * {根据手机号查询当天的发送次数}
	 * 
	 * @param phone 手机号
	 * @return 发送的次数
	 * @author: wangwt
	 */
	int countVerification(String phone);

	/**
	 * {获取最后一天发送的短信}
	 * 
	 * @param phone
	 * @return
	 * @author: wangwt
	 */
	Verification getLastRecordByPhone(String phone);

}