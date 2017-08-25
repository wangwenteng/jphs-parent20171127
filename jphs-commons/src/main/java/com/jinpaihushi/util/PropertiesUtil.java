package com.jinpaihushi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContext;

import com.jinpaihushi.context.SpringContext;

public abstract class PropertiesUtil {

	static Properties prop = new Properties();

	private static boolean inited = false;

	private static Properties getProperties0() {
		if (!inited) {
			try (InputStream fis  = PropertiesUtil.class.getResourceAsStream("/config/application.properties");){
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
	
	private static Properties getProperties() {
		ApplicationContext context = SpringContext.getContext();
		if (context == null) {
			return getProperties0();
		}
		return context.getBean("config", Properties.class);
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	public static String getProperty(String key, String defaultValue) {
		Properties properties = getProperties();
		return properties.getProperty(key, defaultValue);
	}

	public static Integer getInt(String key) {
		String value = getProperty(key);
		if (value == null) {
			return null;
		}
		return Integer.parseInt(value);
	}

	public static Integer getInt(String key, int defaultValue) {
		String value = getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	public static Double getDouble(String key) {
		String value = getProperty(key);
		if (value == null) {
			return null;
		}
		return Double.parseDouble(value);
	}

	public static Double getDouble(String key, double defaultValue) {
		String value = getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return Double.parseDouble(value);
	}
}
