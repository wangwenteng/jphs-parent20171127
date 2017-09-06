package com.jinpaihushi.wechat.timer;

import java.util.TimerTask;
import javax.servlet.ServletContext;

import com.jinpaihushi.utils.Util;
public class MyTimerTask extends TimerTask {
	private ServletContext context = null;
           private int  param;
	public MyTimerTask(ServletContext context) {
		this.context = context;
	}
	@Override
	public void run() {
		context.log("开始执行指定定時任务");
		// TODO 自定义
		 if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("号称每10秒钟跑一次哦！我要调用线程池去执行另外的任务");
		}
		//让线程池去跑一个任务
		PoolManager.pool.execute(new WorkThread(param++));
		if(Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("指定定時任务执行结束");
		}
	}
}