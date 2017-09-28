package com.shuixiaofei.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuixiaofei.dao.UserLoginMapper;

@Service("UserLoginServiceImpl")
public class UserLoginServiceImpl implements UserLoginService{
	@Autowired
	private UserLoginMapper userLoginMapper;
	
	public Map<String, Object> getUserInfo(String user_account) {
		return this.userLoginMapper.getUserInfo(user_account);
	}
	
	
}
