package com.shuixiaofei.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shuixiaofei.entity.MenuInfo;
import com.shuixiaofei.service.sys.MenuService;

@Controller
@RequestMapping("tree")
public class TreeDemo {

	
	@Autowired
	private MenuService menuService;
	/**
	 * 测试树插件
	 * @return
	 */
	@RequestMapping(value="/demo.do" , method=RequestMethod.GET)
	public String showDemo(){
		
		return "pages/sys/account/treeDemo";
	}
	
	
	@RequestMapping(value="/data.do", method = RequestMethod.POST)
	public void getTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		List<MenuInfo> firstList = this.menuService.getMenuInfo("0");
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
		for(MenuInfo temp :firstList){
			Map<String,Object> tmap = new HashMap<String,Object>();
			tmap.put("id", temp.getM_id());
			tmap.put("text", temp.getM_title());
			List<Map<String,Object>>  smap = new ArrayList<Map<String,Object>>();
			List<MenuInfo> secondList = this.menuService.getMenuInfo(temp.getM_id()+"");
			for(MenuInfo stemp:secondList){
				  Map<String,Object> cmap = new HashMap<String, Object>();
				  cmap.put("id", stemp.getM_id());
				  cmap.put("text", stemp.getM_title());
				  smap.add(cmap);
			}
			tmap.put("children", smap);
			tempList.add(tmap);
		}
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
	
	
	

	
}
