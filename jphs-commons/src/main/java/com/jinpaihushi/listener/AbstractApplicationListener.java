package com.jinpaihushi.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.jinpaihushi.utils.exception.ExceptionHandler;

/**
 * 监听抽象类
 * 添加了日志处理器，初始化输出
 * @author fengrz
 *
 * @param <E>
 */
public abstract class AbstractApplicationListener<E extends ApplicationEvent>
		implements ApplicationListener<E> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected ExceptionHandler exceptionHandler;

	public AbstractApplicationListener() {
		super();
		logger.debug("init : " + this.getClass().getSimpleName());
	}

}
