package com.shuixiaofei.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shuixiaofei.entity.MenuInfo;
import com.shuixiaofei.entity.QueryModel;
import com.shuixiaofei.interceptor.Auth;
import com.shuixiaofei.service.sys.MenuService;
import com.shuixiaofei.service.sys.PowerService;
import com.shuixiaofei.service.sys.UserAccountService;
import com.shuixiaofei.utils.CommonUtils;
/**
 * 菜单管理
 * @author xu
 *
 */
@Controller
@RequestMapping("sys")
public class CoolSysCtrl {
	final Logger logger = Logger.getLogger(CoolSysCtrl.class);
	@Autowired
	private MenuService menuService;
	@Autowired
	private PowerService powerService;
	@Autowired
	private UserAccountService userAccountService;
	/**
	 * 跳转管理权限页面
	 * @param request
	 * @param session
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/index.do",method = RequestMethod.GET)
	public String managerSys(String power,HttpServletRequest request,HttpSession session){
		session.setAttribute("power", power);
		return "pages/sys/index";
		
	}
	/**
	 * get the first menu info
	 * @param request
	 * @param response
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/menu/one.do",method=RequestMethod.GET)
	public void getFristMenuList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		PrintWriter printWriter = response.getWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<MenuInfo> list = this.menuService.getMenuInfo("0");
		map.put("code", 0);
		map.put("msg", "11");
		map.put("count", list.size());
		map.put("data", list);
		printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
		printWriter.flush();
		printWriter.close();
		
	}
	/**
	 * 跳转一级菜单添加页
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value = "/menu/add/first/show.do" , method = RequestMethod.GET)
	public String getFirstMenuAdd(HttpSession session){
		session.setAttribute("m_sort", this.menuService.getMenuInfo("0").size()+1);
		return "pages/sys/menu/addFirstMenu";
	}
	/**
	 * 一级菜单添加
	 * @param m_title
	 * @param m_icon
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/menu/first/add.do",method = RequestMethod.POST)
	public String addFirstMenu( MenuInfo menuInfo ,String power ,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
			if(this.menuService.addFirstMenu(menuInfo)>0){
				request.setAttribute("add_msg", "添加成功");
			}else{
				request.setAttribute("add_msg", "网络异常");

			}
			/*power = DESUtil.decrypt(power, AppConstant.desKey, "UTF-8").trim();
			Map<String,Object> pmap = new HashMap<String, Object>();
			// 第一级菜单显示
			List<MenuInfo> firstList = this.menuService.getMenuInfo("0");
			List<MenuInfo> firstResultList = new ArrayList<MenuInfo>();
			for (MenuInfo fmodel : firstList) {
				//System.out.println("model.getCode()=" + fmodel.getCode());
				char a = power.charAt(fmodel.getM_code());
				if (a == '1') {
					List<MenuInfo> secondlist = this.menuService.getMenuInfo(fmodel.getM_id()+"");
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
			session.setAttribute("menu_list", JSON.toJSONString(pmap,SerializerFeature.DisableCircularReferenceDetect));*/
		//	return "redirect:/user/home.do"; //跳转至登录主页

			return "pages/sys/index";
	}
	
	/**
	 * 跳转一级菜单修改
	 * @param menuInfo
	 * @param session
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value = "/menu/update/first/show.do" , method = RequestMethod.GET)
	public String getFirstMenuUpdate(MenuInfo menuInfo,HttpSession session) throws Exception{
		//     	window.location.href = "sys//menu/update/first/show.do?m_id="+data.m_id+"&m_title="data.m_title+"&m_icon="+data.m_icon+"&is_userful"+data.is_userful;
		this.logger.info(CommonUtils.serialize(menuInfo));
		session.setAttribute("m_title", java.net.URLDecoder.decode(java.net.URLDecoder.decode(menuInfo.getM_title(),"UTF-8"),"UTF-8"));
		session.setAttribute("m_icon", menuInfo.getM_icon());
		session.setAttribute("is_userful", java.net.URLDecoder.decode(java.net.URLDecoder.decode(menuInfo.getIs_userful(),"UTF-8"),"UTF-8"));
		session.setAttribute("m_id", menuInfo.getM_id());
		return "pages/sys/menu/updateFirstMenu";
	}
	/**
	 * 修改一级菜单
	 * @param menuInfo
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menu/first/update.do",method = RequestMethod.POST)
	public String updateFirstMenu( MenuInfo menuInfo,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
			if(this.menuService.updateFirstMenuUnUse(menuInfo)>0){
				request.setAttribute("update_msg", "修改成功");
			}else{
				request.setAttribute("update_msg", "网络异常");

			}
			return "pages/sys/index";
	}
	
	
	
	
	/**
	 * 展示第二级菜单页
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/second/menu/show.do",method = RequestMethod.GET)
	public String showSecondMenuInfo(String p_id,HttpServletRequest request,HttpSession session) throws Exception{
		/*PrintWriter printWriter = response.getWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<MenuInfo> list = this.menuService.getMenuInfo(pid);
		map.put("code", 0);
		map.put("msg", "11");
		map.put("count", list.size());
		map.put("data", list);
		printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
		printWriter.flush();
		printWriter.close();
		request.setAttribute("secondMenu", JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));*/
		session.setAttribute("p_id", p_id);
		return "pages/sys/menu/secondMenuInfo";
	}
	/**
	 * 根据上级id获取子菜单列表
	 * @param pid
	 * @param request
	 * @param repResponse
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value ="/second/menu/query.do",method = RequestMethod.POST)
	public void getSecondMenuInfoByPid(String p_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		this.logger.info("查询二级菜单列表p_id="+p_id);
		PrintWriter printWriter = response.getWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		List<MenuInfo> list = this.menuService.getMenuInfo(p_id);
		map.put("code", 0);
		map.put("msg", "11");
		map.put("count", list.size());
		map.put("data", list);
		printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
		printWriter.flush();
		printWriter.close();
	}
	/**
	 * 跳转二级菜单添加修改页
	 * @param pid
	 * @param m_title
	 * @param url
	 * @param m_icon
	 * @param is_userful
	 * @param m_id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Auth("All")
	@RequestMapping(value="/show/second.do",method = RequestMethod.GET)
	public String showSecondAddPage(String p_id,String m_title,String url,String m_icon,String is_userful,String m_id ,HttpSession session) throws Exception{
		//this.logger.info("1=============="+java.net.URLDecoder.decode(java.net.URLDecoder.decode(m_title,"UTF-8"),"UTF-8"));
		//this.logger.info("1=============="+p_id);
		session.setAttribute("p_id", p_id);
		session.setAttribute("m_title", java.net.URLDecoder.decode(java.net.URLDecoder.decode(m_title,"UTF-8"),"UTF-8"));
		session.setAttribute("url", url);
		session.setAttribute("m_icon", m_icon);
		session.setAttribute("is_userful", java.net.URLDecoder.decode(java.net.URLDecoder.decode(is_userful,"UTF-8"),"UTF-8"));
		session.setAttribute("m_id", m_id);
		return "pages/sys/menu/updateSecondMenu";
	}
	/**
	 * 修改二级菜单
	 * @param menuInfo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Auth("All")
	@RequestMapping(value="/second/menu/update.do",method = RequestMethod.POST)
	public String updateSecondMenuInfo(MenuInfo menuInfo,HttpSession session,HttpServletRequest request) throws Exception{		
		//this.logger.info("测试数据===="+CommonUtils.serialize(menuInfo));
		int a = this.menuService.updateSecondMenuInfo(menuInfo);
		if(a>0){
			request.setAttribute("u_s_msg", "修改成功");
		}else{
			request.setAttribute("u_s_msg", "网络异常");
		}
		session.setAttribute("p_id", menuInfo.getP_id());
		return 	"pages/sys/menu/secondMenuInfo";
	}
	/**
	 * 展示二级菜单修改页
	 * @param pid
	 * @param session
	 * @return
	 */
	@Auth
	@RequestMapping(value="/show/second/add.do",method = RequestMethod.GET)
	public String showSecondMenuAdd(String p_id,HttpSession session){
		//System.out.println("====================="+p_id);
		session.setAttribute("p_id", p_id);
		return "pages/sys/menu/addSecondMenu";
	}
	/**
	 * 添加二级菜单
	 * @param menuInfo
	 * @param session
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/second/menu/add.do" ,method = RequestMethod.POST)
	public String addSecondMenuInfo(MenuInfo menuInfo,HttpSession session,HttpServletRequest request){
		this.logger.info("二级菜单添加页面======="+CommonUtils.serialize(menuInfo));
		int a = this.menuService.addSecondMenuInfo(menuInfo);
		if(a>0){
			request.setAttribute("a_s_msg", "添加成功");
		}else{
			request.setAttribute("a_s_msg", "网络异常");
		}
		session.setAttribute("pi_d", menuInfo.getP_id());
		return 	"pages/sys/menu/secondMenuInfo";
	}
	/**
	 * 更新一级菜单无用
	 * @param m_id
	 * @param request
	 * @param response
	 */
/*	@Auth("All")
	@RequestMapping(value="/update/first/unuse.do",method = RequestMethod.POST)
	public void updateFirstMenuUnuse(String m_id,HttpServletRequest request ,HttpServletResponse response){
		System.out.println("11111111111111");
	}	
	*/
	/***
	 * 展示用户权限页面
	 * @param request
	 * @param response
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/show/power/index", method = RequestMethod.GET)
	public String showPowerManager(String user_acc,HttpServletRequest request,HttpServletResponse response){
		this.logger.info("这是一个测试案例-------"+user_acc);

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String power = this.powerService.getPowerByUserAccount(user_acc);
			List<Integer> codeList= new ArrayList<Integer>();
			if (StringUtils.isNotBlank(power)) {
				char[] charArray = power.toCharArray();
				for (int i = 0; i < charArray.length; i++) {
					if (String.valueOf(charArray[i]).equals("1")) {
						codeList.add(i);
				     }
			     }
			}
			String thePowerHad = StringUtils.join(codeList, ",");
			List<MenuInfo> firstList = new ArrayList<MenuInfo>();
			List<MenuInfo> secondList = new ArrayList<MenuInfo>();
			// 查询所有的菜单
			List<MenuInfo> list = this.menuService.getAllMenuInfo();
			for(MenuInfo menuInfo:list){
				if(thePowerHad.contains(menuInfo.getM_code()+"")){
					menuInfo.setItem("checked");
				}else{
					menuInfo.setItem("");
				}
				if("0".equals(menuInfo.getP_id()+"")){
					firstList.add(menuInfo);
				}else{
					secondList.add(menuInfo);
				}
			}
			map.put("firstHtml", firstList);
			map.put("secondHtml", secondList);
			request.setAttribute("menu_data", map);
			request.setAttribute("user_acc", user_acc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/sys/power/index";
	}
	/**
	 * 获取用户权限树
	 * @param user_acc
	 * @param request
	 * @param response
	 */
	@Auth("All")
	@RequestMapping(value="/tree/data.do", method=RequestMethod.POST)
	public void getPowerTreeData(String user_acc,HttpServletRequest request,HttpServletResponse response)throws Exception{
		// 查询用的权限码
		String power = this.powerService.getPowerByUserAccount(user_acc);
		List<Integer> codeList= new ArrayList<Integer>();
		if (StringUtils.isNotBlank(power)) {
			char[] charArray = power.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				if (String.valueOf(charArray[i]).equals("1")) {
					codeList.add(i);
			     }
		     }
		}
		String thePowerHad = StringUtils.join(codeList, ","); // 该用户已经具有的权限
		System.out.println(thePowerHad);
		// 查询一级菜单
		List<MenuInfo> firstList = this.menuService.getMenuForUser("0");
		// 返回的权限集合列表
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
		for(MenuInfo temp :firstList){
			Map<String,Object> tmap = new HashMap<String,Object>();
			tmap.put("id", temp.getM_id()+"-"+temp.getM_code());
			tmap.put("text", temp.getM_title());
			if(thePowerHad.contains(temp.getM_code()+"")){
				tmap.put("checked", true);
			}
			List<Map<String,Object>>  smap = new ArrayList<Map<String,Object>>();
			List<MenuInfo> secondList = this.menuService.getMenuForUser(temp.getM_id()+"");
			for(MenuInfo stemp:secondList){
				  Map<String,Object> cmap = new HashMap<String, Object>();
				  cmap.put("id", stemp.getM_id()+"-"+stemp.getM_code());
				  cmap.put("text", stemp.getM_title());
				  if(thePowerHad.contains(stemp.getM_code()+"")){
					  cmap.put("checked", true);
					}			
				  smap.add(cmap);
			}
			tmap.put("children", smap);
			tempList.add(tmap);
		}
		System.out.println(JSON.toJSONString(tempList,SerializerFeature.DisableCircularReferenceDetect));
		PrintWriter printWriter = response.getWriter();
		printWriter.print(JSON.toJSONString(tempList,SerializerFeature.DisableCircularReferenceDetect));
		printWriter.flush();
		printWriter.close();
	/*	"id":1,
		"text":"Foods",
		"children":[{
			"id":2,
			"text":"Fruits",  
			"state":"closed",
			"children":[{
				"text":"apple",
				"checked":true
			},{
				"text":"orange"
			}]
		*/
	
		
		
	}
	
	/***
	 * 修改用户权限
	 * @param user_account
	 * @param codes
	 * @param response
	 * @param request
	 */
	@Auth("All")
	@RequestMapping(value="/power/update.do",method = RequestMethod.POST)
	public void  updateUserPower(QueryModel model,HttpServletResponse response,HttpServletRequest request){
		 try {
			this.logger.info("=========="+CommonUtils.serialize(model));
			 String gotPower = model.getParam2().toString();
			 String powerStr = "";
			 for(int i=0 ;i<256;i++){
				 if(gotPower.contains(i+"")){
					 powerStr = powerStr + "1";
				 }else{
					 powerStr = powerStr + "0"; 
				 }
			 }
			 model.setParam2(powerStr);
			 int a = this.userAccountService.updateUserPower(model);
			 PrintWriter printWriter = response.getWriter();
			 printWriter.print(JSON.toJSONString(a,SerializerFeature.DisableCircularReferenceDetect));
			 printWriter.flush();
			 printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
