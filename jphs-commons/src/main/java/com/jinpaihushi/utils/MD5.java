package com.jinpaihushi.utils;


import java.security.*;

public class MD5 {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", 
		   "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; 
	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5crypt(String input)  {
		MessageDigest md =null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArrayToHexString(md.digest(input.getBytes()));
		 
	}
	private static String byteArrayToHexString(byte[] b) { 
		StringBuffer resultSb = new StringBuffer(); 
		for (int i = 0; i < b.length; i++) { 
		    resultSb.append(byteToHexString(b[i])); 
		} 
		return resultSb.toString(); 
	} 

	/** 将一个字节转化成十六进制形式的字符串 */ 
	private static String byteToHexString(byte b) { 
		int n = b; 
		if (n < 0) 
		   n = 256 + n; 
		   int d1 = n / 16; 
		   int d2 = n % 16; 
		return hexDigits[d1] + hexDigits[d2]; 
	} 
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(md5crypt(md5crypt("4")));
	}

}

