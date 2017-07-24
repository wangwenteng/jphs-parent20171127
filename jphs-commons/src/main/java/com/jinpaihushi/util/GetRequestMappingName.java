package com.jinpaihushi.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * (获取controller中requestMapping的value值)
 * 
 * @ClassName: GetRequestMappingName
 * @Description: TODO
 * @author 王文腾
 * @date 2017年2月24日 下午1:54:42
 */
public class GetRequestMappingName {

	/**
	 * @Title: getControllerName
	 * @Description: 获取controller的requestMapping值
	 * @param clazz
	 * @return 设定文件
	 * @return String 返回类型
	 */
	public static String getControllerName(Class<?> clazz) {
		// 获取controller中path
		String controllerName = null;
		// 判断controller是否有requestMapping注释
		boolean controller = clazz.isAnnotationPresent(RequestMapping.class);
		if (controller) {
			RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
			// 获取controller中requestMapping的value
			controllerName = requestMapping.path()[0];
			controllerName += "-" + requestMapping.name();
		}
		return controllerName;
	}

	/**
	 * @Title: getMethodName
	 * @Description: 通过反射得到方法中requestMapping的value值
	 * @param clazz
	 * @return 设定文件
	 * @return List<String> 返回类型
	 */
	public static List<String> getMethodName(Class<?> clazz) {
		clazz.getName();
		List<String> list = new ArrayList<>();
		// 获取controller中path
		String controllerName = null;
		String controllerPath = null;
		boolean controller = clazz.isAnnotationPresent(RequestMapping.class);
		if (controller) {
			RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
			// 获取controller中requestMapping的value
			controllerPath = requestMapping.path()[0];
			controllerName = requestMapping.name();
		}
		list.add(controllerPath + "TT" + controllerName);
		// 得到方法
		Method[] methods = clazz.getDeclaredMethods();
		String methodName = null;
		String methodPath = null;
		for (Method method : methods) {
			// 判断是方法否存在requestMapping注释
			boolean present = method.isAnnotationPresent(RequestMapping.class);
			if (present) {
				RequestMapping annotation = method.getAnnotation(RequestMapping.class);
				methodPath = controllerPath + annotation.path()[0];
				if (methodPath.endsWith("/index.jhtml")) {
					methodName = controllerName;

				} else {
					methodName = annotation.name() ;
				}
				list.add(methodPath + "TT" + methodName);
			}
		}
		return list;
	}
}
