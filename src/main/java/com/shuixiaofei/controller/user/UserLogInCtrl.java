package com.shuixiaofei.controller.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shuixiaofei.entity.MenuInfo;
import com.shuixiaofei.interceptor.Auth;
import com.shuixiaofei.interceptor.AuthInterceptor;
import com.shuixiaofei.lockutils.DESUtil;
import com.shuixiaofei.service.sys.MenuService;
import com.shuixiaofei.service.user.UserLoginService;
import com.shuixiaofei.utils.AppConstant;
/**
 * 处理用户登录
 * @author xu
 *
 */
@Controller
@RequestMapping("user")
public class UserLogInCtrl {
	@Autowired
	private UserLoginService userLoginService;	
	@Autowired
	private MenuService menuService;
	final Logger logger = Logger.getLogger(UserLogInCtrl.class);
	/**
	 * 校验用户登录信息
	 */
	@RequestMapping(value="/validate.do",method = RequestMethod.POST)
	public String validateUserLogin(String user_account,String pwd,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		 
		this.logger.info("进行判别登录状态");				
		Map<String,Object> map = this.userLoginService.getUserInfo(user_account);
		// u.user_id,u.employee_id,u.user_name,u.user_pwd,u.power,u.city_code  is_lock
		Cookie ck = new Cookie("user_account",user_account);
		//30天有效
		ck.setMaxAge(30*24*60*60);
		//设置同一服务器的所有请求都可访问该cookie
		//ck.setPath("/");	
		response.addCookie(ck);		
		if(map!=null){
			// 判别账号是否被锁住
			if(map.get("user_pwd").toString().trim().equals(DESUtil.encrypt(pwd, AppConstant.desKey, "UTF-8").trim())){
				// 校验通过登录成功，给予权限
				session.setAttribute("t_msg", null);
				session.setAttribute("user_name", map.get("user_name").toString());
			//	session.setAttribute("power", DESUtil.encrypt(map.get("power").toString(), AppConstant.desKey, "UTF-8"));
		    // 校验通过顺便把所具有的菜单放过去	
			// ---------------------------------------------------------------------------
				String power = map.get("power").toString();
				// this.logger.info("菜单"+power);
					Map<String, Object> pmap = new HashMap<String, Object>();
					if(power == null || power.equals("")){
						pmap.put("flag", 2);
						pmap.put("errorMessage", "power is null");
						pmap.put("list", new ArrayList<Map<String, Object>>());
					}else{
						// 第一级菜单显示
						List<MenuInfo> firstList = this.menuService.getMenuForUser("0");
						List<MenuInfo> firstResultList = new ArrayList<MenuInfo>();
						for (MenuInfo fmodel : firstList) {
							//System.out.println("model.getCode()=" + fmodel.getCode());
							char a = power.charAt(fmodel.getM_code());
							if (a == '1') {
								List<MenuInfo> secondlist = this.menuService.getMenuForUser(fmodel.getM_id()+"");
								List<MenuInfo> secondResultList = new ArrayList<MenuInfo>();
								for (MenuInfo smodel : secondlist) {
									char sa = power.charAt(smodel.getM_code());
									if (sa == '1') {
										secondResultList.add(smodel);
									}
								}
								fmodel.setSecondMenuListl(secondResultList);
								firstResultList.add(fmodel);
							}
						}
						pmap.put("flag", 1);
						pmap.put("list", firstResultList);
					}
				session.setAttribute("power", DESUtil.encrypt(power, AppConstant.desKey, "UTF-8").trim());
				session.setAttribute("menu_list", JSON.toJSONString(pmap,SerializerFeature.DisableCircularReferenceDetect));;	
		        session.setAttribute(AuthInterceptor.SESSION_USERID, (map.get("user_id").toString()));  
		        session.setAttribute(AuthInterceptor.SESSION_AUTHS, new HashSet<String>(Arrays.asList("All", "GET", "SAVE"))); 
		        session.setAttribute("sessionstatus", "timeout");
				return "redirect:/user/home.do"; //跳转至登录主页
			}else{
				//去查询是否多次登录，超过4次的时候锁住
				session.setAttribute("t_msg", "密码错误！");
				session.setAttribute(AuthInterceptor.SESSION_USERID, (null));  
			    session.setAttribute(AuthInterceptor.SESSION_AUTHS, null); 
			    //查询登录尝试次数如果超过四次 锁住账号
				return "index";
			}

		}else{
			//System.out.println("账号不存在");
			session.setAttribute("t_msg", "账号不存在！");
			session.setAttribute(AuthInterceptor.SESSION_USERID, (null));  
		    session.setAttribute(AuthInterceptor.SESSION_AUTHS, null); 
			return "index";
			//return "redirect:/user/home.do";

		}	
	
	}
	/**
	 * 验证通过跳转主页
	 * @return
	 */
	@Auth("All")  
	@RequestMapping(value="/home.do",method = RequestMethod.GET)
	public String showHomePage(){
	//	return "pages/home/main";

		return "pages/home/new_main";	
	}
	
	
    @RequestMapping(value="/index.do",method=RequestMethod.GET)  
    public String query(){  
		return "index";
    }  
	/**
	 * 重新加载主页
	 * @param power
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Auth("All")
	@RequestMapping(value="/reload/home.do",method = RequestMethod.GET)
	public String reloadHomePage(String power,HttpServletRequest request,HttpSession session) throws Exception{
		power = DESUtil.decrypt(power, AppConstant.desKey, "UTF-8").trim();
		Map<String,Object> pmap = new HashMap<String, Object>();
		// 第一级菜单显示
		List<MenuInfo> firstList = this.menuService.getMenuForUser("0");
		List<MenuInfo> firstResultList = new ArrayList<MenuInfo>();
		for (MenuInfo fmodel : firstList) {
			//System.out.println("model.getCode()=" + fmodel.getCode());			
			char a = power.charAt(fmodel.getM_code());
			if (a == '1') {
				List<MenuInfo> secondlist = this.menuService.getMenuForUser(fmodel.getM_id()+"");
				List<MenuInfo> secondResultList = new ArrayList<MenuInfo>();
				for (MenuInfo smodel : secondlist) {
					char sa = power.charAt(smodel.getM_code());
					if (sa == '1') {
						secondResultList.add(smodel);
					}
				}
				fmodel.setSecondMenuListl(secondResultList);
				firstResultList.add(fmodel);
			}			
		}
		pmap.put("flag", 1);
		pmap.put("list", firstResultList);
		session.setAttribute("menu_list", JSON.toJSONString(pmap,SerializerFeature.DisableCircularReferenceDetect));
		return "redirect:/user/home.do"; //跳转至登录主页

	}
	
	
	
	

}
