package com.shuixiaofei.utils;

import java.util.HashMap;
import java.util.Map;



public class JpushUtils {
		
	public static String sendJpushToParents(String aliasStr){	
		String state ="";
		try {
			String appKey ="c7633f45f4e27b662bcbecaa";
			String masterSecret = "61be3fa8ade3b30e241d0e28";
			String alert = "您有超期未归还书籍！！";
			String title = "还书提醒";
			String[] alias = aliasStr.split(",");
			//System.out.println(aliasStr);
			Map<String, Object> tempMap = new HashMap<String, Object>();
			//tempMap.put("imagepath", imagePath);
			tempMap.put("type", "3");
			tempMap.put("desc", "还书提醒 ");
			for(String a :alias){
				// 推送到特定机器
				state = JpushHttpUtil.sendPostHttps("https://api.jpush.cn/v3/push", JpushModel.getSendAndroidJson(a , alert, title,tempMap), appKey, masterSecret);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(sendJpushToParents("7878015046478336"));
		
	}
}
