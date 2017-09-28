package com.shuixiaofei.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuixiaofei.dao.PowerMapper;
/**
 * 权限处理业务层00
 * @author xu
 * 20170926
 */
@Service("PowerServiceImpl")
public class PowerServiceImpl implements PowerService{
	@Autowired
	private PowerMapper powerMapper;
	
	public String getPowerByUserAccount(String user_account) {
		return this.powerMapper.getPowerByAccount(user_account);
	}
	
	
}
