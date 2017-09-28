package com.shuixiaofei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("init")
public class PageCtrl {

	@RequestMapping(value="/a/index.do",method=org.springframework.web.bind.annotation.RequestMethod.GET)
	public String showLogin(){
		return "pages/login/demo";
	}
	
}
