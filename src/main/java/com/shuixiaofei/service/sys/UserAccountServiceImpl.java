package com.shuixiaofei.service.sys;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuixiaofei.dao.AccountMapper;
import com.shuixiaofei.entity.QueryModel;
import com.shuixiaofei.entity.UserAccount;

@Service("UserAccountServiceImpl")
public class UserAccountServiceImpl implements UserAccountService{
	@Autowired
	private AccountMapper accountMapper;
	public int addUserAccount(UserAccount userAccount) {
		return this.accountMapper.addUserAccount(userAccount);
	}
	public java.util.List<java.util.Map<String,Object>> getUserAccountListInfo(UserAccount userAccount) {	
		return this.accountMapper.getUserAccountListInfo(userAccount);
	};
	public int updateUserAccountInfo(UserAccount userAccount) {
		return this.accountMapper.updateUserAccountInfo(userAccount);
	}
	public int updateUserAccountPwd(UserAccount userAccount) {
		return this.accountMapper.updateUserAccountPwd(userAccount);
	}
	public int updateUserAccountUnLock(UserAccount userAccount) {
		return this.accountMapper.updateUserAccountUnLock(userAccount);
	}
	public Map<String, Object> getAccountIsExit(String uaccount) {
		return this.accountMapper.getAccountIsExit(uaccount);
	}
	public int updateUserPower(QueryModel queryModel) {
		return this.accountMapper.updateUserPower(queryModel);
	}
}
