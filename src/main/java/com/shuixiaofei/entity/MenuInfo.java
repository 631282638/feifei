package com.shuixiaofei.entity;

import java.util.List;

/**
 * 菜单实体类
 * @author xu
 *
 */
public class MenuInfo {
/*
	m_id	int	11	0	0	-1	0	0	0		0					-1	0
	p_id	int	11	0	-1	0	0	0	0	0	0	上级id				0	0
	m_title	varchar	50	0	-1	0	0	0	0		0	菜单标题	utf8	utf8_general_ci		0	0
	m_url	varchar	50	0	-1	0	0	0	0		0	菜单路径	utf8	utf8_general_ci		0	0
	m_sort	int	11	0	-1	0	0	0	0		0	排序规则				0	0
	m_icon	varchar	20	0	-1	0	0	0	0		0	标题图标	utf8	utf8_general_ci		0	0
	m_code	int	11	0	-1	0	0	0	0		0					0	0
	create_time	datetime	0	0	-1	0	0	0	0		0	创建时间				0	0
	update_time	timestamp	0	0	-1	0	0	0	0		-1	最近更新时间				0	0
	is_userful	int	1	0	-1	0	0	0	0	1	0	是否显示 1 显示 ，0 隐藏				0	0*/

	private int m_id;
	private int p_id;
	private String m_title;
	private String m_url;
	private int m_sort;
	private String m_icon;
	private int m_code;
	private String is_userful;
	private String item; // 是否具有权限
	private List<MenuInfo> secondMenuListl;
	
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	public String getM_url() {
		return m_url;
	}
	public void setM_url(String m_url) {
		this.m_url = m_url;
	}
	
	public int getM_sort() {
		return m_sort;
	}
	public void setM_sort(int m_sort) {
		this.m_sort = m_sort;
	}
	public String getM_icon() {
		return m_icon;
	}
	public void setM_icon(String m_icon) {
		this.m_icon = m_icon;
	}
	public int getM_code() {
		return m_code;
	}
	public void setM_code(int m_code) {
		this.m_code = m_code;
	}
	public List<MenuInfo> getSecondMenuListl() {
		return secondMenuListl;
	}
	public void setSecondMenuListl(List<MenuInfo> secondMenuListl) {
		this.secondMenuListl = secondMenuListl;
	}
	public String getIs_userful() {
		return is_userful;
	}
	public void setIs_userful(String is_userful) {
		this.is_userful = is_userful;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	
	
}
