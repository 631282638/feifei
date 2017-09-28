package com.shuixiaofei.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuixiaofei.dao.MenuMapper;
import com.shuixiaofei.entity.MenuInfo;
/**
 * 20170828
 * @author xu
 *  根据账号的获取菜单权限
 */
@Service("MenuServiceImpl")
public class MenuServiceImpl implements MenuService{
	@Autowired
	MenuMapper menuMapper;
	
	public List<MenuInfo> getAllMenuInfo() {
		return this.menuMapper.getAllMenuInfo();
	}
	public List<MenuInfo> getMenuForUser(String p_id) {
		return this.menuMapper.getMenuForUser(p_id);
	}
	public List<MenuInfo> getMenuInfo(String p_id) {

		return this.menuMapper.getMenuInfo(p_id);
	}
	public int addFirstMenu(MenuInfo menuInfo) {
		MenuInfo map = this.menuMapper.getMaxMcode();
		menuInfo.setM_code(map.getM_code());
		return this.menuMapper.addFirstMenu(menuInfo);
	}
	public int addSecondMenuInfo(MenuInfo menuInfo) {
		menuInfo.setM_code(this.menuMapper.getMaxMcode().getM_code());
		MenuInfo tMenuInfo = this.menuMapper.getMaxMSortBPid(menuInfo.getP_id()+"");
		if(tMenuInfo!=null){
			menuInfo.setM_sort(this.menuMapper.getMaxMSortBPid(menuInfo.getP_id()+"").getM_sort());
		}else{
			menuInfo.setM_sort(1);
		}
		return this.menuMapper.addSecondMenuInfo(menuInfo);
	}
	public int updateSecondMenuInfo(MenuInfo menuInfo) {
		return this.menuMapper.updateSecondMenuInfo(menuInfo);
	}
	public int updateFirstMenuUnUse(MenuInfo menuInfo) {
		return this.menuMapper.updateFirstMenuUnUse(menuInfo);
	}


}
