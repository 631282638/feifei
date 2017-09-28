package com.shuixiaofei.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shuixiaofei.interceptor.Auth;

@Controller
@RequestMapping("coolhome")
public class MisticCtrl {
	/***
	 * 默认首页
	 * @param request
	 * @param response
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/bg.do",method=RequestMethod.GET)
	public String showBgPages(HttpServletRequest request,HttpServletResponse response){
		return "pages/home/bg";
	}
	
	
	
}
