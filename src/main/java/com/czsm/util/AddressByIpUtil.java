package com.czsm.util;

import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddressByIpUtil {

	public static void main(String[] args) {
		String addr=AddressByIpUtil.getAddrByIp("14.127.249.133");
		System.out.println(addr);
	}
	public static String getAddrByIp(String ip) {
		String resout = "";
		try {
			String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
			System.out.println(str);
			JSONObject obj = JSONObject.fromObject(str);
			JSONObject obj2 = (JSONObject) obj.get("data");
			String code =  obj.get("code")+"";
			if (code.equals("0")) {
				resout = obj2.get("country") + "," + obj2.get("area") +"," + obj2.get("region") + "," + obj2.get("city") + ","
						+ obj2.get("isp");
			} else {
				resout = "IP地址有误";
			}
		} catch (Exception e) {

			e.printStackTrace();
			resout = "获取IP地址异常：" + e.getMessage();
		}
		return resout;
	}
	public static String getJsonContent(String urlStr) {
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				return ConvertStream2Json(httpConn.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}
}