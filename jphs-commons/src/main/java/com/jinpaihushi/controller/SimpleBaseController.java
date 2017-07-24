package com.jinpaihushi.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.jinpaihushi.config.ApplicationConfig;
import com.jinpaihushi.context.SpringHelper;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.model.SID;
import com.jinpaihushi.util.SIDEditor;
import com.jinpaihushi.utils.CustomDateEditor;
import com.jinpaihushi.utils.exception.ExceptionHandler;
import com.github.pagehelper.PageHelper;

/**
 * Controller基类，添加了日志处理器，异常处理器,格式处理器,实现了事件发表方法
 * 
 * @author fengrz
 *
 */
public abstract class SimpleBaseController implements
		ApplicationEventPublisher {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ExceptionHandler exceptionHandler;
	@Autowired
	protected ApplicationConfig applicationConfig;

	public SimpleBaseController() {
		super();
		logger.info("init:" + this.getClass().getSimpleName());
	}

	/**
	 * 添加格式处理器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initDataBinder(WebDataBinder binder) {
		binder.registerCustomEditor(SID.class, new SIDEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
		binder.setBindingErrorProcessor(new BindingErrorProcessor() {
			@Override
			public void processMissingFieldError(String missingField,
					BindingResult bindingResult) {
				logger.debug(missingField);
			}

			@Override
			public void processPropertyAccessException(
					PropertyAccessException ex, BindingResult bindingResult) {
				// logger.trace(ex);
			}
		});
	}

	/**
	 * 返回错误提示信息errorDesc
	 * 
	 * @param bindingResult
	 * @param modelMap
	 * @param errorDesc
	 */
	protected void setBindingResultError(BindingResult bindingResult,
			ModelMap modelMap, String errorDesc) {
		ObjectError objectError = new ObjectError("errorInfo", errorDesc);
		bindingResult.addError(objectError);
		modelMap.addAttribute("errors", bindingResult.getAllErrors());
	}

	@Override
	public void publishEvent(ApplicationEvent event) {
		SpringHelper.context.publishEvent(event);
	}

	@Override
	public void publishEvent(Object event) {
		SpringHelper.context.publishEvent(event);
	}

	protected void startPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(
				pageNum != null && pageNum > 0 ? pageNum : 1,
				pageSize != null && pageSize > 0 ? pageSize : applicationConfig
						.getDefaultPageSize());
	}
	
	
}
