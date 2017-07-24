package com.jinpaihushi.utils.exception;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.jinpaihushi.context.SpringHelper;
import com.jinpaihushi.event.HtmlEmailEvent;
import com.jinpaihushi.model.SessionUser;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.StringUtils;
import com.jinpaihushi.utils.velocity.VelocityTemplatePlugin;

@Component
public class ExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${exceptionHandler.open:false}")
	private boolean open;

	/**
	 * 异常处理方法 把异常信息与发生异常的上下文环境提取出来,发送邮件通知相关人员
	 * 
	 * @param name
	 *            异常名称/标记/标题
	 * @param e
	 *            异常对象
	 * @param req
	 *            HTTP请求
	 */
	public void handle(String name, Throwable e, HttpServletRequest req) {
		handle(name, e, req, null, null);
	}

	/**
	 * 异常处理方法 把异常信息与发生异常的上下文环境提取出来,发送邮件通知相关人员
	 * 
	 * @param name
	 *            异常名称/标记/标题
	 * @param e
	 *            异常对象
	 * @param req
	 *            HTTP请求
	 * @param modelMap
	 */
	public void handle(String name, Throwable e, HttpServletRequest req,
			ModelMap modelMap) {
		handle(name, e, req, null, modelMap);
	}

	/**
	 * 异常处理方法 把异常信息与发生异常的上下文环境提取出来,发送邮件通知相关人员
	 * 
	 * @param name
	 *            异常名称/标记/标题
	 * @param throwable
	 *            异常对象
	 * @param receivers
	 *            接收异常邮件的用户(可以多个,可无)， 默认给所有PA开发人员发邮件
	 */
	public void handle(String name, Throwable throwable) {
		if (!open) {
			return;
		}
		StringBuilder exception = new StringBuilder();
		StringUtils.toHtml(throwable, exception);

		VelocityTemplatePlugin vm = new VelocityTemplatePlugin(
				"simpleExceptionEmail");
		VelocityContext context = new VelocityContext();
		context.put("exception", exception);
		vm.setContext(context);
		vm.evaluate();
		SpringHelper.context.publishEvent(new HtmlEmailEvent(this, name,
				vm.getView()));
	}

	/**
	 * 异常处理方法<br/>
	 * 1.把异常信息与发生异常的上下文环境提取出来<br/>
	 * 2.发送邮件<br/>
	 * 3.往modelMap中放入flag:-1与exception字段<br/>
	 * 
	 * @param name
	 *            异常名称/标记/标题
	 * @param e
	 *            异常对象
	 * @param req
	 *            HTTP请求
	 * @param resp
	 *            HTTP回复
	 * @param modelMap
	 * @param receivers
	 *            接收异常邮件的用户(可以多个,可无)， 默认给所有PA开发人员发邮件
	 */
	public void handle(String name, Throwable e, HttpServletRequest req,
			HttpServletResponse resp, Map<String,Object> modelMap) {
		logger.debug("throw exception : " + e.getClass());
		if (modelMap != null) {
			modelMap.put("flag", -1);
			modelMap.put("exception", e.getClass().getSimpleName());
			if(e instanceof IllegalArgumentException){
				//非法参数异常时给客户端提供异常信息
				modelMap.put("msg", e.getMessage());
			}
		}
		if (!open) {
			return;
		}
		StringBuilder exception = new StringBuilder();
		StringBuilder request = new StringBuilder();
		StringBuilder session = new StringBuilder();
		StringBuilder model = new StringBuilder();
		StringUtils.toHtml(e, exception);
		StringUtils.toHtml(req, request);
		StringUtils.toHtml(req.getSession(), session);
		StringUtils.toHtml(modelMap, model);
		SessionUser user = (SessionUser) req.getSession().getAttribute(
				SessionUser.SESSION_KEY);

		VelocityTemplatePlugin vm = new VelocityTemplatePlugin("exceptionEmail");
		VelocityContext context = new VelocityContext();
		context.put("exceptionName", name);
		context.put("url", req.getRequestURL());
		context.put("ip", StringUtils.getRealIp(req));
		context.put("time", DateUtils.format(new Date()));
		context.put("userName", user != null ? user.getUserName() : "");
		context.put("exception", exception);
		context.put("request", request);
		context.put("session", session);
		context.put("modelMap", model);
		vm.setContext(context);
		vm.evaluate();
		
		SpringHelper.context.publishEvent(new HtmlEmailEvent(this, name,
				vm.getView()));
	}

	public void handle(String name, Exception e, HttpServletRequest req,
			HttpServletResponse resp) {
		this.handle(name, e, req, resp, null);
	}

}
