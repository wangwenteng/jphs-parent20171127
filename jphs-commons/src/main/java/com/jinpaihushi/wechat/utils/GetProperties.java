package com.jinpaihushi.wechat.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class GetProperties {

	/*
	 * public static String getUrl(String param){ Properties prop = new
	 * Properties(); String url_str = ""; try {
	 * prop.load(GetProperties.class.getClassLoader().getResourceAsStream(
	 * "url.properties")); } catch (IOException e) { e.printStackTrace(); }
	 * url_str = prop.getProperty(param); return url_str; }
	 */

	public static String getWchatUrl(String param) {
		Properties prop = new Properties();
		String url_str = "";
		try {
			prop.load(GetProperties.class.getClassLoader().getResourceAsStream("config/wchattoken.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		url_str = prop.getProperty(param);
		return url_str;
	}

	public static void main(String[] args) {
		System.out.println(getWchatUrl("WCHAT_ACCESS_JSAPI_TICKET"));
		// updateProperties("WCHAT_ACCESS_JSAPI_TICKET","1111111111111111");
	}

	/**
	 * 更新properties文件的键值对 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 * 
	 * @param keyname
	 *            键名
	 * @param keyvalue
	 *            键值
	 */
	public static void updateProperties(String keyname, String keyvalue) {
		String filePaths = getPath(GetProperties.class) + "config/wchattoken.properties";

		System.out.println("filePaths-----" + filePaths);
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filePaths));
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePaths);
			prop.setProperty(keyname, keyvalue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "Update '" + keyname + "' value");
		} catch (IOException e) {
			System.err.println("属性文件更新错误");
		}
	}

	/**
	 * 得到某一个类的路径
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String getPath(Class name) {
		String strResult = null;
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			strResult = name.getResource("/").toString().replace("file:/", "").replace("%20", " ");
		} else {
			strResult = name.getResource("/").toString().replace("file:", "").replace("%20", " ");
		}
		return strResult;
	}
}
