package com.jinpaihushi.wechat.timer;

import java.util.Timer;
import java.util.concurrent.Executors;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jinpaihushi.utils.Util;
public class StartupListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		sce.getServletContext().log("定时器销毁");
		System.out.println("定时器销毁");
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if(Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("启动线程池");
		}
		// Start a thread pool to deal with different task;
		PoolManager.pool = Executors.newFixedThreadPool(10);
		if(Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("启动定时器");
		}
		//Create a Daemon timer thread
		Timer timer=new Timer(true);
		// 每隔10秒钟执行任务
		timer.schedule(new MyTimerTask(sce.getServletContext()), 0,10 * 1000); 
		if(Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("已添加任务表");
		}
	}
}