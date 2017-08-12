package com.jinpaihushi.utils;

import java.lang.reflect.Field;

/**
 * 对象字段为空验证
 * 
 * @author admin
 *
 */
public class ObjectVerification {
	/**
	 * 判断对象的必填字段是否为空
	 * 
	 * @param object
	 *            对象
	 * @return true or false
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean verification(Object object) throws Exception {
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String name = field.getName();
			if (name.equals("id")||name.equals("status") || name.equals("createTime"))
				continue;
			if (field.get(object) == null) {
				System.out.println(name);
				return false;
			}
		}

		return true;
	}
}
