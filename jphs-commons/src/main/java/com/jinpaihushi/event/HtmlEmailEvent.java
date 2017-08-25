package com.jinpaihushi.event;

import org.springframework.context.ApplicationEvent;

public class HtmlEmailEvent extends ApplicationEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1165814701958200023L;

	private final String title;
	
	private final String html;
	
	private final String[] receivers;
	
	public HtmlEmailEvent(Object source, String title, String html, String... receivers) {
		super(source);
		this.title = title;
		this.html = html;
		this.receivers = receivers;
	}

	public String getTitle() {
		return title;
	}

	public String getHtml() {
		return html;
	}

	public String[] getReceivers() {
		return receivers;
	}

}
