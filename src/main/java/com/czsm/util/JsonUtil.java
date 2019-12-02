package com.czsm.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	@SuppressWarnings("unchecked")
	public static <T> T jsonStrToObj(String josnStr,T t){
	     JSONObject jsonObj=JSONObject.fromObject(josnStr);
		 T obj =  (T) JSONObject.toBean(jsonObj, t.getClass());
		 return obj;
	}
	@SuppressWarnings("unchecked")
	public static <T> T jsonObjToObj(JSONObject jsonObj,T t){
		 T obj =  (T) JSONObject.toBean(jsonObj, t.getClass());
		 return obj;
	}
	
	

	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonArrayToObjList(String jsonArrayStr ,T t){
		List<T> list=new ArrayList<T>();
		JSONArray jsonList=JSONArray.fromObject(jsonArrayStr);
		for (int i=0;i<jsonList.size();i++) {
			JSONObject object=jsonList.getJSONObject(i);
			T t1=(T) JSONObject.toBean(object,t.getClass());
			list.add(t1);
		}
		 return list;
	}
}
