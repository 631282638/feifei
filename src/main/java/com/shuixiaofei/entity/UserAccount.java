package com.shuixiaofei.entity;
/***
 * 用户账号信息表
 * @author xu
 * 2017 09 21 
 */
public class UserAccount {
	
	private String user_id; // 用户账号id
	private String employee_id; // 员工基本信息id 暂时没用
	private String user_name; // 用户名称
	private String user_account;// 用户账号
	private String user_pwd; // 用户密码
	private String user_pwd1;
	private String user_pwd2;
	private String power; //用户权限码
	private String code;//用户机构吗
	//private String create_time;//创建时间
	//private String last_update_time//上次修改时间;
	private String is_userful;//是否可用 1可用 0 不可用
	private String is_lock;//是否被锁定 1被锁住 0未被锁住
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIs_userful() {
		return is_userful;
	}
	public void setIs_userful(String is_userful) {
		this.is_userful = is_userful;
	}
	public String getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(String is_lock) {
		this.is_lock = is_lock;
	}
	public String getUser_pwd1() {
		return user_pwd1;
	}
	public void setUser_pwd1(String user_pwd1) {
		this.user_pwd1 = user_pwd1;
	}
	public String getUser_pwd2() {
		return user_pwd2;
	}
	public void setUser_pwd2(String user_pwd2) {
		this.user_pwd2 = user_pwd2;
	}
	
	

	
}
