package com.shuixiaofei.dao;

import java.util.List;
import java.util.Map;

import com.shuixiaofei.entity.QueryModel;
import com.shuixiaofei.entity.UserAccount;

public interface AccountMapper {
	/**查询用户账号列表**/
	List<Map<String,Object>> getUserAccountListInfo(UserAccount userAccount);
	/**账号解锁**/
	int updateUserAccountUnLock(UserAccount userAccount);
	/**修改账户信息**/
	int updateUserAccountInfo(UserAccount userAccount);
	/***修改账号密码***/
	int updateUserAccountPwd(UserAccount userAccount);
	/***添加用户账号***/
	int addUserAccount(UserAccount userAccount);
	/**查询账号知否已经存在**/
	Map<String,Object> getAccountIsExit(String uaccount);
	/***修改用户权限***/
	int updateUserPower(QueryModel queryModel);
}
