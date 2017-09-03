package com.jinpaihushi.wechat.timer;

import com.jinpaihushi.utils.Util;
import com.jinpaihushi.wechat.utils.WechatJStokenUtils;

public class WorkThread implements Runnable {
	private int param;
	public WorkThread(int param) {
		this.param = param;
	}
	@Override
	public void run() {
		// TODO Do something
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("我被线程池调用执行啦~！参数：" + param);
		}
		boolean falg_token = true;
		boolean token_falg = true;
		int ft = 1;
		while (falg_token) {
			boolean f_token = WechatJStokenUtils.getToken();
			token_falg = f_token;
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("获取token第-"+ft+"-次("+f_token+")");
			}
			if(f_token || ft==3){
				falg_token = false;
			}
			ft++;
		}
		if(token_falg){
			boolean falg_ticket = true;
			int tt = 1;
			while (falg_ticket) {
				boolean ti_token = WechatJStokenUtils.getTicket();
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("获取ticket第-"+tt+"-次("+ti_token+")");
				}
				if(ti_token || tt==3){
					falg_ticket = false;
				}
				tt++;
			}
		}
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("获取微信token完成");
		}
	}
	
}