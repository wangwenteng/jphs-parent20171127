package com.jinpaihushi.model;

import java.util.Set;

/**
 * 用户接口
 * @author fengrz
 *
 */
@SuppressWarnings("serial")
public abstract class SessionUser extends BaseModel{
	
	/**
	 * 用户session的键值，使用方可以根据实际需求更改
	 */
	public static String SESSION_KEY = "sessionUser";
	
	/**
	 * 获取用户名
	 * @return
	 */
	public abstract String getName();

	/**
	 * 获取用户ID
	 * @return
	 */
	public abstract Integer getUserId();
	
	/**
	 * 获取用户名
	 * @return
	 */
	public abstract String getUserName();
	
	/**
	 * 获取用户类型
	 * @return
	 */
	public abstract int getUserType();
	
	/**
	 * 获取用户角色集合
	 * @return
	 */
	public abstract Set<String> getRoles();
	
}
