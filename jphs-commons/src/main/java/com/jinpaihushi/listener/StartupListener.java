package com.jinpaihushi.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.jinpaihushi.context.SpringContext;
import com.jinpaihushi.context.SpringHelper;
import com.jinpaihushi.jphs.wechat.service.WechatService;

/**
 * 上下文初始化监听器
 * 
 * <p>
 * 注意: 系统初始化的时候进行了两次两次扫描
 * 第一次扫描除controller职位的service,dao,schedule,listener,interceptor等 第二次扫描controller
 * 第一次扫描与第二次扫描得到的ApplicationContext是不同的对象
 * 
 * <p>
 * StartupListener与SpringContextLoaderListener执行顺序: StartupListener第一遍执行
 * SpringContextLoaderListener执行 StartupListener第二遍执行
 * 
 * <p>
 * StartupListener与SpringContextLoaderListener的区别:
 * SpringContextLoaderListener执行时获取的ApplicationContext
 * 与StartupListener第一遍执行时获取的ApplicationContext是同一个对象, 都不包含Controller
 * 
 * <p>
 * ApplicationContextAware: 如果Service层实现了ApplicationContextAware接口,
 * 那么获取的ApplicationContext
 * 与StartupListener第一遍执行时获取的ApplicationContext是同一个对象,都不包含Controller
 * 
 * @author fengrz
 *
 */
@Component("startupListener")
public class StartupListener extends AbstractApplicationListener<ContextRefreshedEvent> {
	/*@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private SystemModuleService systemModuleService;
	@Autowired
	private SystemRoleModuleService systemRoleModuleService;*/
	@Autowired
	private WechatService wechatService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("===========context-refresh===============");
		SpringHelper.setContext(event.getApplicationContext());
		SpringContext.setContext(event.getApplicationContext());
		logger.info("=========================================");
		// 获取微信公众号token
		//wechatService.getTokens();
		logger.info("初始化结束");
		// 初始化之后创建超级管理员
			/*String userId = systemUserService.initSystemUser();
			if (userId.length() > 0) {
				String roleId = systemRoleService.initSystemRole(userId);
				if (roleId.length() > 0) {
					// 查询到所有角色
					List<SystemModule> list = systemModuleService.list(null);
					// 给默认角色分配所有模块
					List<SystemRoleModule> insert = new ArrayList<>();
					SystemRoleModule systemRoleModle = null;
					for (SystemModule systemModule : list) {
						systemRoleModle = new SystemRoleModule();
						systemRoleModle.setSystemRoleId(roleId);
						systemRoleModle.setSystemModuleId(systemModule.getId());
						insert.add(systemRoleModle);
					}
					systemRoleModuleService.inserts(insert);
				}
			}*/
	}

}
