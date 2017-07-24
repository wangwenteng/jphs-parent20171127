package com.jinpaihushi.service;

import org.apache.commons.mail.Email;

public interface EmailService {

	/**
	 * 使用系统邮箱发送邮件
	 * @param email 邮件
	 */
	void send(Email email);
	
}
