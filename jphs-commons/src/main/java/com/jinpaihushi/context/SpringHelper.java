package com.jinpaihushi.context;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * HISTORY @ Copyright: Copyright (c) 2002 @ Company:TMG
 * @version:1.0
 * @date 2008/04/09
 * @author wei.li
 * @description: Spring bean工厂接口
 * 
 */
public class SpringHelper implements ApplicationContextAware {

	public static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext appcontext)
			throws BeansException {
		// TODO Auto-generated method stub
		SpringHelper.context = appcontext;
	}

	/**
	 * 通过制定的名称获得Bean对象
	 * 
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	public synchronized static <T> T getBean(String name) {
		if (context == null) {
			 initApplicationContext();
		}
		return (T) context.getBean(name);
	}

	public synchronized static void initWeb(ServletContext sc) {
		context = WebApplicationContextUtils.getWebApplicationContext(sc);
	}

	/**
	 * 通过class类型获得Bean对象
	 * 
	 * @param clazz
	 */
	public static <T> T getBean(Class<T> clzz) {
		return context.getBean(clzz);
	}

	public static void setContext(ApplicationContext applicationContext){
		context = applicationContext;
	}
	/**
	 * 手动初始化spring方法
	 */
	public static void initApplicationContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"classpath*:**//applicationContext*.xml");
		}
	}

}
