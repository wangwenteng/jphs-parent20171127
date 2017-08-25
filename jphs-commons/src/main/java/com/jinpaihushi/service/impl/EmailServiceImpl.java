package com.jinpaihushi.service.impl;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jinpaihushi.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async
	@Override
	public void send(Email email){
		try {
			email.send();
			logger.debug("Send mail success:{}",email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
