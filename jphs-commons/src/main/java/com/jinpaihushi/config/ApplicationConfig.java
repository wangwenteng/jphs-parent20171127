package com.jinpaihushi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {
	
	@Value("${application.name:}")
	private String name;
	
	@Value("${application.host:}")
	private String host;
	
	@Value("${application.home:}")
	private String home;
	
	@Value("${application.page.size:10}")
	private int defaultPageSize;
	
	@Value("${application.debug:false}")
	private boolean debug;

	public String getName() {
		return name;
	}

	public String getHost() {
		return host;
	}

	public int getDefaultPageSize() {
		return defaultPageSize;
	}

	public boolean isDebug() {
		return debug;
	}

	public String getHome() {
		return home;
	}

}
