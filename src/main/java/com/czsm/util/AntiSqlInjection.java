package com.czsm.util;

/**
 * 防SQL注入工具类 把SQL关键字替换为空字符串
 * 
 * @author zhao
 * @since 2015.7.23
 */
public class AntiSqlInjection {

	public final static String regex = "'|%|--|and|or|not|use|insert|delete|update|select|count|group|union"
			+ "|create|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|source|sql";

	/**
	 * 把SQL关键字替换为空字符串
	 * 
	 * @param param
	 * @return
	 */
	public static String filter(String param) {
		if (param == null) {
			return param;
		}
		return param.replaceAll("(?i)" + regex, " "); // (?i)不区分大小写替换
	}
}