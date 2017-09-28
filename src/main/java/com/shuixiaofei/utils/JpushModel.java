package com.shuixiaofei.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JpushModel {
		
	
	/**
	 *  推送模板
	 *  @param plagForm 推送平台
	 * @param alias 别名
	 * @param alert 内容
	 * @param title 标题
	 * @return
	 */
	public static  String  getSendAndroidJson(String alias,String  alert,String title,Map<String,Object> iamgeMap){
		Map<String,Object>  map = new LinkedHashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add("android");
		list.add("ios");
		map.put("platform", list);
		Map<String,Object> audienceMap = new HashMap<String, Object>();
		audienceMap.put("alias",alias.split(","));  // 学生编号+ 家长id
		map.put("audience", audienceMap);
		Map<String,Object> androidMap = new HashMap<String, Object>();
		androidMap.put("alert", alert);
		androidMap.put("title", title);
		Map<String,Object> iosMap = new HashMap<String, Object>();
		iosMap.put("alert", alert);
		iosMap.put("badge", "+1");
		iosMap.put("sound", "");	
		iosMap.put("extras", JSON.toJSON(iamgeMap));
		Map<String,Object> options = new HashMap<String, Object>();
	//	options.put("time_to_live", "60");
		options.put("apns_production", false);		
		Map<String,Object> noficationMapAndroid = new HashMap<String, Object>();		
		noficationMapAndroid.put("android", androidMap);
		noficationMapAndroid.put("ios", iosMap);
		map.put("notification", noficationMapAndroid);	
		map.put("options", options);

		return JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 *  平台消息推送，推送给所有家长，所有老师，所有园长
	 *  @param plagForm 推送平台
	 * @param alias 别名
	 * @param alert 内容
	 * @param title 标题
	 * @return
	 */
	public static  String  getSendMessageJson(String alert,String title){
		Map<String,Object>  map = new LinkedHashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add("android");
		list.add("ios");
		map.put("platform", list);
		map.put("audience", "all");
		Map<String,Object> androidMap = new HashMap<String, Object>();
		androidMap.put("alert", alert);
		androidMap.put("title", title);
		Map<String,Object> extrasMap = new HashMap<String, Object>();
		extrasMap.put("type", "3");
		extrasMap.put("desc", "平台推送消息");
		androidMap.put("extras", JSON.toJSON(extrasMap));
		Map<String,Object> iosMap = new HashMap<String, Object>();
		iosMap.put("alert", alert);
		iosMap.put("badge", "+1");
		iosMap.put("sound", "");
		iosMap.put("extras", JSON.toJSON(extrasMap));
		Map<String,Object> options = new HashMap<String, Object>();
	//	options.put("time_to_live", "60");
		options.put("apns_production", false);
		
		Map<String,Object> noficationMapAndroid = new HashMap<String, Object>();
		
		noficationMapAndroid.put("android", androidMap);
		noficationMapAndroid.put("ios", iosMap);
		map.put("notification", noficationMapAndroid);	
		map.put("options", options);

		return JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);
	}
}
