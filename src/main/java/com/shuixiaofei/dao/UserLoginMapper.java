package com.shuixiaofei.dao;

import java.util.Map;
/**
 * 2017 08 22 
 * @author xu
 * for  user validate
 */
public interface UserLoginMapper {
		
		Map<String,Object> getUserInfo(String user_account);
	
		Map<String,Object> getTryedNum(String user_account);
		
		Map<String,Object> updateTryedNumToZero(String user_account);
}
