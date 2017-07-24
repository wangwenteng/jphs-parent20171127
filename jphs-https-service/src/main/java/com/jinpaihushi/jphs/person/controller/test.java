package com.jinpaihushi.jphs.person.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jinpaihushi.util.PropertiesUtil;


/**
 * 读取配置文件
 * @author Administrator
 *
 */
public class test {
	
	static Properties pps = new Properties();
	public static void main(String[] args) {
		try (
				InputStream fis  = PropertiesUtil.class.getResourceAsStream("/config/application.properties");
			)
		{
		pps.load(fis);
		String message=pps.getProperty("message");
		System.out.println("message="+message);
		
	} catch (IOException e) {
		e.printStackTrace();
	}}

}
