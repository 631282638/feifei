package com.shuixiaofei.dao;

import java.util.List;

import com.shuixiaofei.entity.MenuInfo;


public interface MenuMapper {
	/**获取所有的菜单列表***/
	List<MenuInfo> getAllMenuInfo();
	/**获取菜单给用户**/
	List<MenuInfo> getMenuForUser(String p_id);
	//获取菜单信息
	List<MenuInfo> getMenuInfo(String  p_id);
	//获取最大的mcode
	MenuInfo getMaxMcode();
	//添加一级菜单
	int addFirstMenu(MenuInfo menuInfo);
	//修改一级菜单不使用
	int updateFirstMenuUnUse(MenuInfo menuInfo);
	/**二级菜单修改**/
	int updateSecondMenuInfo(MenuInfo menuInfo);
	/**添加二级菜单数据**/
	int addSecondMenuInfo(MenuInfo menuInfo);
	/**获取二级菜单最大排序**/
	MenuInfo getMaxMSortBPid(String p_id);
}
