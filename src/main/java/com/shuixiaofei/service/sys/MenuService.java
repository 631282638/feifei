package com.shuixiaofei.service.sys;

import java.util.List;

import com.shuixiaofei.entity.MenuInfo;

public interface MenuService {
	/***获取所有的菜单列表***/
	List<MenuInfo> getAllMenuInfo();
	/**查询菜单信息给用户**/
	List<MenuInfo> getMenuForUser(String p_id);
	//查询菜单
	List<MenuInfo> getMenuInfo(String p_id);
	//添加一级菜单
	int addFirstMenu(MenuInfo menuInfo);
	//更新一级菜单不使用
	int updateFirstMenuUnUse(MenuInfo menuInfo);
	/**二级菜单修改**/
	int updateSecondMenuInfo(MenuInfo menuInfo);
	/**添加二级菜单数据**/
	int addSecondMenuInfo(MenuInfo menuInfo);
}
