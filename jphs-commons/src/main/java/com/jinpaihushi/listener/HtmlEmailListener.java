package com.jinpaihushi.listener;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jinpaihushi.event.HtmlEmailEvent;

/**
 * HTML邮件事件监听
 * @author fengrz
 *
 */
@Component("htmlEmailListener")
public class HtmlEmailListener extends
	AbstractApplicationListener<HtmlEmailEvent> {
	
	@Value("${email.host}")
	private String host;
	
	@Value("${email.user}")
	private String user;
	
	@Value("${email.password}")
	private String password;

	@Value("${exceptionHandler.open:false}")
	private boolean open;
	
	@Value("${exceptionHandler.receivers}")
	private String[] receivers;
	
	@Value("${exceptionHandler.tag}")
	private String tag;

	@Async
	@Override
	public void onApplicationEvent(HtmlEmailEvent event) {
		if(!open){
			logger.info("throw email : " + event.getTitle());
			return;
		}
		try {
			HtmlEmail mail = new HtmlEmail();
			mail.setHostName(host);
			mail.setAuthentication(user, password);
			mail.setCharset("UTF-8");
			mail.setFrom(user, tag);
			mail.setSubject("[" + tag + "]" + event.getTitle());
			String[] receivers = event.getReceivers();
			if(ArrayUtils.isEmpty(receivers)){
				receivers = this.receivers;
			}
			for (String receiver : receivers) {
				mail.addTo(receiver);
			}
			mail.setHtmlMsg(event.getHtml());
			mail.send();
			logger.info("send email : " + event.getTitle());
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
