package com.jinpaihushi.util;

import java.util.Collection;
import java.util.function.Function;

import javax.servlet.http.HttpSession;

import org.springframework.util.CollectionUtils;

import com.jinpaihushi.model.SessionUser;

/**
 * 数据权限检查工具类
 * 
 * @author fengrz
 *
 */
public class SecurityUtils {

	/**
	 * 检查用户是否有访问对象的权限
	 * 
	 * @param hs
	 *            会话
	 * @param target
	 *            目标对象
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 */
	public static <T> void check(HttpSession hs, T target, Function<T, Integer> ownerIdGetter) {
		SessionUser user = (SessionUser) hs.getAttribute(SessionUser.SESSION_KEY);
		check(user, target, ownerIdGetter);
	}

	/**
	 * 检查用户是否有访问对象的权限
	 * 
	 * @param user
	 *            用户
	 * @param target
	 *            目标对象
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 */
	public static <T> void check(SessionUser user, T target, Function<T, Integer> ownerIdGetter) {
		if (user == null) {
			throw new SecurityException("User not login");
		}
		check(user.getUserId(), target, ownerIdGetter);
	}

	/**
	 * 检查用户是否有访问对象的权限
	 * 
	 * @param userId
	 *            用户ID
	 * @param target
	 *            目标对象
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 */
	public static <T> void check(Integer userId, T target, Function<T, Integer> ownerIdGetter) {
		if (userId == null) {
			throw new SecurityException("User not login");
		}
		if (target == null) {
			return;
		}
		Integer ownerId = ownerIdGetter.apply(target);
		if(ownerId == null){
			//ownerId为空，则认为这个对象不属于任何用户，允许任何用户访问
			return;
		}
		if (!userId.equals(ownerId)) {
			throw new SecurityException("Forbidden User#" + userId + " to access " + target
					+ ", the owner of this object is User#" + ownerId);
		}
	}

	/**
	 * 检查用户是否有访问对象的权限
	 * 
	 * @param hs
	 *            会话
	 * @param targetId
	 *            目标对象ID
	 * @param targetLoader
	 *            目标对象加载方法
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 * @return 加载的对象
	 */
	public static <T> T loadAndCheck(HttpSession hs, int targetId, Function<Integer, T> targetLoader,
			Function<T, Integer> ownerIdGetter) {
		SessionUser user = (SessionUser) hs.getAttribute(SessionUser.SESSION_KEY);
		return loadAndCheck(user, targetId, targetLoader, ownerIdGetter);
	}

	/**
	 * 检查用户是否有访问对象的权限
	 * 
	 * @param user
	 *            用户
	 * @param targetId
	 *            目标对象ID
	 * @param targetLoader
	 *            目标对象加载方法
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 * @return 加载的对象
	 */
	public static <T> T loadAndCheck(SessionUser user, int targetId, Function<Integer, T> targetLoader,
			Function<T, Integer> ownerIdGetter) {
		if (user == null) {
			throw new SecurityException("User not login");
		}
		return loadAndCheck(user.getUserId(), targetId, targetLoader, ownerIdGetter);
	}

	/**
	 * 加载对象并 检查用户是否有访问对象的权限
	 * 
	 * @param userId
	 *            用户ID
	 * @param targetId
	 *            目标对象ID
	 * @param targetLoader
	 *            目标对象加载方法
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 * @return 加载的对象
	 */
	public static <T> T loadAndCheck(Integer userId, int targetId, Function<Integer, T> targetLoader,
			Function<T, Integer> ownerIdGetter) {
		if (userId == null) {
			throw new SecurityException("User not login");
		}
		T target = targetLoader.apply(targetId);
		if (target == null) {
			return null;
		}
		Integer ownerId = ownerIdGetter.apply(target);
		if(ownerId == null){
			//ownerId为空，则认为这个对象不属于任何用户，允许任何用户访问
			return target;
		}
		if (!userId.equals(ownerId)) {
			throw new SecurityException("Forbidden User#" + userId + " to access " + target
					+ ", the owner of this object is User#" + ownerId);
		}
		return target;
	}

	/**
	 * 检查用户是否有访问集合里所有对象的权限
	 * 
	 * @param hs
	 *            会话
	 * @param targets
	 *            目标集合
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 */
	public static <T> void checkCollection(HttpSession hs, Collection<T> targets, Function<T, Integer> ownerIdGetter) {
		SessionUser user = (SessionUser) hs.getAttribute(SessionUser.SESSION_KEY);
		checkCollection(user, targets, ownerIdGetter);
	}

	/**
	 * 检查用户是否有访问集合里所有对象的权限
	 * 
	 * @param user
	 *            用户
	 * @param targets
	 *            目标集合
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 */
	public static <T> void checkCollection(SessionUser user, Collection<T> targets,
			Function<T, Integer> ownerIdGetter) {
		if (user == null) {
			throw new SecurityException("User not login");
		}
		checkCollection(user.getUserId(), targets, ownerIdGetter);
	}

	/**
	 * 检查用户是否有访问集合里所有对象的权限
	 * 
	 * @param userId
	 *            用户ID
	 * @param targets
	 *            目标集合
	 * @param ownerIdGetter
	 *            目标对象所属用户ID获取方法
	 */
	public static <T> void checkCollection(Integer userId, Collection<T> targets, Function<T, Integer> ownerIdGetter) {
		if (userId == null) {
			throw new SecurityException("User not login");
		}
		if (CollectionUtils.isEmpty(targets)) {
			return;
		}
		for (T target : targets) {
			Integer ownerId = ownerIdGetter.apply(target);
			if(ownerId == null){
				//ownerId为空，则认为这个对象不属于任何用户，允许任何用户访问
				return;
			}
			if (!userId.equals(ownerId)) {
				throw new SecurityException("Forbidden User#" + userId + " to access " + target
						+ ", the owner of this object is User#" + ownerId);
			}
		}
	}

}
