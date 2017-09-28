package com.shuixiaofei.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shuixiaofei.entity.MenuInfo;
import com.shuixiaofei.entity.UserAccount;
import com.shuixiaofei.interceptor.Auth;
import com.shuixiaofei.lockutils.DESUtil;
import com.shuixiaofei.service.sys.MenuService;
import com.shuixiaofei.service.sys.UserAccountService;
import com.shuixiaofei.utils.AppConstant;
import com.shuixiaofei.utils.CommonUtils;
/**
 * 用户账号管理
 * @author xu
 *
 */
@Controller
@RequestMapping("account")
public class UserAccountCtrl {
	/**logger日志信息**/
	final Logger logger = Logger.getLogger(UserAccountCtrl.class);
	@Autowired
	private UserAccountService userAccountService;
	
	/**
	 * 跳转账号管理页面
	 * @return
	 */
	@Auth
	@RequestMapping(value="/show/index.do" , method = RequestMethod.GET)
	public String showUserAccountManagerPages(){
		return "pages/sys/account/index";	
	}
	/***
	 * 获取用户账号列表
	 * @param request
	 */
	@Auth("GET")
	@RequestMapping(value="/info.do" , method = RequestMethod.GET)
	public void getUserAccountListInfo(UserAccount userAccount ,HttpServletRequest request,HttpServletResponse response) {
		//request.setAttribute("data", this.userAccountService.getUserAccountListInfo(userAccount));
		try {
			PrintWriter printWriter = response.getWriter();
			Map<String,Object> map = new HashMap<String, Object>();
			List<Map<String,Object>> list = this.userAccountService.getUserAccountListInfo(userAccount);
			map.put("code", 0);
			map.put("msg", "用户数据列表");
			map.put("count", list.size());
			map.put("data", list);
			printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改用户信息
	 * @param userAccount
	 * @param request
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/info/update/show.do",method=RequestMethod.GET)
	public String  showUserInfoUpdate(UserAccount userAccount, HttpServletRequest request)throws Exception{
		request.setAttribute("user_name", java.net.URLDecoder.decode(java.net.URLDecoder.decode(userAccount.getUser_name(),"UTF-8"),"UTF-8"));
		request.setAttribute("code", userAccount.getCode() );
		request.setAttribute("is_lock",java.net.URLDecoder.decode(java.net.URLDecoder.decode(userAccount.getIs_lock(),"UTF-8"),"UTF-8"));
		System.out.println(java.net.URLDecoder.decode(java.net.URLDecoder.decode(userAccount.getIs_lock(),"UTF-8"),"UTF-8"));
		request.setAttribute("is_userful",java.net.URLDecoder.decode(java.net.URLDecoder.decode(userAccount.getIs_userful(),"UTF-8"),"UTF-8"));
		request.setAttribute("user_account", userAccount.getUser_account());	
		System.out.println(userAccount.getUser_account());
		return "pages/sys/account/updateUserInfo";
	}
	/**
	 * 更新用户信息
	 * @param userAccount
	 * @param request
	 * @param response
	 * @return
	 */
	@Auth
	@RequestMapping(value="/info/update.do", method = RequestMethod.POST)
	public String updateUserInfo(UserAccount userAccount,HttpServletRequest request,HttpServletResponse response){
		this.logger.info( CommonUtils.serialize(userAccount));
		String msg = "" ;
		if(this.userAccountService.updateUserAccountInfo(userAccount)>0){
			   msg = "修改成功";
		}else{
			   msg = "网络异常";
		}
		request.setAttribute("u_u_msg", msg);
		return "pages/sys/account/index";	
	}
	/***
	 * 添加系统用户
	 * @param userAccount
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Auth("All")
	@RequestMapping(value="/info/add/show.do",method=RequestMethod.GET)
	public String  showUserInfoInsert(UserAccount userAccount, HttpServletRequest request)throws Exception{
		return "pages/sys/account/addUserAccount";
	}
	/***
	 * 用户账号信息添加
	 * @param userAccount
	 * @param request
	 * @param response
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/info/add.do", method = RequestMethod.POST)
	public String addUserInfo(UserAccount userAccount,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//  user_name  code is_userful  is_lock  user_account  user_pwd
		userAccount.setUser_id(CommonUtils.getUUId());
		String pwd =DESUtil.encrypt(userAccount.getUser_pwd1(), AppConstant.desKey, "UTF-8").trim() ;
		userAccount.setUser_pwd(pwd);
		userAccount.setPower("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
	//	VALUES (#{user_id},'',#{user_name},#{user_account},#{user_pwd},#{power},#{code},SYSDATE(),1,0)		
		String msg = "" ;
		if(this.userAccountService.addUserAccount(userAccount)>0){
			   msg = "新增成功";
		}else{
			   msg = "网络异常";
		}
		request.setAttribute("u_u_msg", msg);
		return "pages/sys/account/index";	
	}

	/**
	 * 校验用户是否存在
	 * @param user_account
	 * @param request
	 * @param response
	 */
	@Auth("All")
	@RequestMapping(value="/user/exist.do" , method = RequestMethod.POST)
	public void getUserAccountIsExist(String uaccount ,HttpServletRequest request,HttpServletResponse response) {
		try {
			PrintWriter printWriter = response.getWriter();
			Map<String,Object> map = this.userAccountService.getAccountIsExit(uaccount);
			if(("1").equals(map.get("num")+"")){
				printWriter.print(true);
			}else{
				printWriter.print(false);
			}
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * 设置账号是否还用
	 * @param user_account
	 * @param yesorno
	 * @param request
	 * @param response
	 */
	@Auth("All")
	@RequestMapping(value="/update/useful.do",method = RequestMethod.POST)
	public void updateAccountUserfulOrnot(String user_account,String yesorno, HttpServletRequest request,HttpServletResponse response){

		try {
			PrintWriter printWriter = response.getWriter();
				
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	/**
	 * 解锁账号
	 * @param user_account
	 * @param request
	 * @param response
	 */
	@Auth("All")
	@RequestMapping(value="",method = RequestMethod.POST)
	public void updateAccountUnlock(String user_account,HttpServletRequest request,HttpServletResponse response){

		try {
			PrintWriter printWriter = response.getWriter();
			 
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
