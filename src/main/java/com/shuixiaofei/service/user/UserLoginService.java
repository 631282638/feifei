package com.shuixiaofei.service.user;

import java.util.Map;
/**
 * 2017 08 22
 * @author xu
 *
 */
public interface UserLoginService {
	/**
	 * for validate user login   info 	
	 * @param username
	 * @return
	 */
	Map<String,Object> getUserInfo(String user_account);
	
}
