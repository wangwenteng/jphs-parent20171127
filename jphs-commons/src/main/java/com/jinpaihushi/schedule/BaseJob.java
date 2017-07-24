package com.jinpaihushi.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jinpaihushi.utils.exception.ExceptionHandler;

/**
 * 定时任务基类
 * 
 * @author fengrz
 *
 */
public abstract class BaseJob {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ExceptionHandler exceptionHandler;
	
	/**
	 * 定时任务总开关 switcher=false表示关闭了所有定时任务，即使某些定时任务isOpen=true也不能允许
	 * switcher=true表示总开关打开了，这如果某个定时任务isOpen=true表示打开了定时任务，isOpen=false表示关闭了定时任务
	 */
	@Value("${schedule.switcher:true}")
	private boolean switcher;

	/**
	 * 判断这个定时任务是否开启的标志
	 */
	private boolean isOpen;

	public BaseJob() {
		super();
		logger.debug("init : " + this.getClass().getSimpleName());
	}

	/**
	 * 带参构造方法，实现类可以覆盖这个构造方法，使用注解注入定时任务开关 <code>
	 * 示例代码<br/>@Autowired<br/>
	 * public SomeJob(@Value("${schedule.someJob:false}")boolean isOpen){<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;super(isOpen);<br/>
	 * }<br/>
	 * <code/>
	 * 
	 * @param isOpen
	 *            true表示开启定时任务，false表示关闭定时任务
	 */
	public BaseJob(boolean isOpen) {
		super();
		this.isOpen = isOpen;
		logger.debug("init : " + this.getClass().getSimpleName());
	}

	/**
	 * 设置本定时任务是否开启法，实现类可以覆盖这个方法，使用注解注入定时任务开关 <code>
	 * 示例代码<br/>@Autowired<br/>
	 * public void setIsOpen(@Value("${schedule.someJob:false}")boolean isOpen){<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;super.setIsOpen(isOpen);<br/>
	 * }<br/>
	 * <code/>
	 * 
	 * @param isOpen
	 */
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * 判断这个定时任务是否开启，定时任务开启了表示定时条件满足后运行相应业务逻辑， 而关闭后不执行定时任务或执行定时任务但是不运行相应业务逻辑
	 * 
	 * @return true表示已开启，false表示未开启
	 */
	public boolean isOpen() {
		return switcher && isOpen;
	}
}
