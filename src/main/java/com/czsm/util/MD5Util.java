package com.czsm.util;

import java.security.MessageDigest;

/**
 * MD5加密帮助类
 * 
 * @author Mac(刘平) 20180804
 *
 */
public class MD5Util {
	/**
	 * 加密方法
	 * 
	 * @param str 原文
	 * @return 密文
	 */

	public static String EncoderByMd5(String key) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = key.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 密文校验
	 * 
	 * @param newpasswd 明文
	 * @param oldpasswd 密文
	 * @return
	 */
	public static boolean checkpassword(String newpasswd, String oldpasswd) {
		if (EncoderByMd5(newpasswd).equals(oldpasswd))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.EncoderByMd5("1234"));
	}
}
