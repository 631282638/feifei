<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/basic.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html id="emle">
  <head>
    <base href="<%=basePath%>">
    <title>一个管理系统</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=basePath%>common/css/font-awesome.min.css" rel="stylesheet">
	 <link rel="stylesheet" href="<%=basePath%>common/css/main.css" type="text/css"></link>
	 <link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	 <script type="text/javascript" src="<%=basePath%>common/js/index.js"></script>
    <style>
 
    </style>
 </head>
  <body class="ydob">
	<div class="cool_panel">
	<div class="cool_toolbar">
	<table class="cool_title">
	<tr>
	<td class="cool_logobg_cs" style="position:relative;top:-5%;" align="center">
	<table style="width:100%;">
	  <tr style="width:100%;">
	  <td style="width:100%;">
	   <div style=" display:flex;justify-content:left; align-items:center;" class="enlarger-cont">
	          <p style='overflow:hidden;color:#feeeed; font-weight:normal;font-size: 20'>xxx开发平台</p>
	  </div>
	  </td>
	  </tr>
	</table>	
	</td>
	<td class="cool_icon_css"><div class="cool_inco_ct" onclick="contentsShow(-1,-1,'posthome/bg.do')" ><i class="fa fa-bars"></i></div></td>
	<td class="cool_title_css">开发平台</td>
	<td class="cool_user_css">hi<span style="font-size:20px;color:#ef4136;border-bottom:1px dashed #f00;">&nbsp;${sessionScope.user_name}&nbsp;</span>欢迎您   <a onclick="logOff()">
	<span class="cool_user_logout">&nbsp;&nbsp;<i class='fa fa-power-off'></i> 注销</span></a></td>
	</tr>
	</table>
	</div>
	<div class="cool_left_menu" style="height:90%;overflow:auto;">
 	<ul id="accordion" class="accordion">
	</ul>
	</div>
	<div class="cool_content" style="height:90%">
		  <div id="tab1" class='content_css'>
			  <iframe src="<%=basePath%>coolhome/bg.do" name="ifrmname" id="show_contents" width="100%" height="100%"  frameborder="0"> </iframe>
		   </div>
   		
	</div>
	</div>
  </body>
  </html>
 <script type="text/javascript">
     var request_page;
	var contentsShow = function(i,j,url){
		 request_page = '<%=basePath%>'+url;
		$("#show_contents").attr("src", request_page);
		
	};
  	
  //	alert('${menu_list}')
  //	alert('${sessionScope.user_name}')
 
 	$(function(){
 	var height = document.body.clientHeight;
 	$("body").css("height",$(document).height());
 	});
	//----------------------------------------------------------------------
	  var map = JSON.parse('${menu_list}');
				     var flag = map.flag;
				    if(flag == 2){
				     // window.location.href="user/index.do";
				  //    return;
				      }else if(flag==1){
				     var menuData = map.list;
				      var htmlDom = $("#accordion");
				      var firstHtml = "";
				      var secondHtml = "";
				      if(menuData != null && menuData.length > 0){
				          for(var j=0;j<menuData.length;j++){
				           firstHtml +="<li>";
				           firstHtml +="<div class='link'><i class='fa fa-"+menuData[j].m_icon+"'></i>"+menuData[j].m_title+"<i class='fa fa-chevron-down'></i></div>";
				          //
				           if(menuData[j].secondMenuListl != null && menuData[j].secondMenuListl.length >0){
				                secondHtml = "<ul class='submenu'>";
				                for(var i=0;i<menuData[j].secondMenuListl.length;i++){
/* 					                 secondHtml+="<li id='li_"+i+"_"+j+"' class='sde'><i class='fa fa-"+menuData[j].secondMenuListl[i].m_icon+
					                 "'></i><a onclick=contentsShow("+i+","+j+",'"+menuData[j].secondMenuListl[i].m_url+"')>"+menuData[j].secondMenuListl[i].m_title+"</a></li>"; */
				                	 secondHtml+="<li id='li_"+i+"_"+j+"' class='sde'><a onclick=contentsShow("+i+","+j+",'"+menuData[j].secondMenuListl[i].m_url+"')>"+menuData[j].secondMenuListl[i].m_title+"</a></li>";
				                }
				                 secondHtml +="</ul>";
				           }
				           firstHtml += secondHtml;
				           firstHtml +="</li>";
				          }
				         
				      }
				   htmlDom.append(firstHtml);   
				     
				     }
		// zhuxiao 		     
		var logOff = function(){
			window.location.href="<%=path%>";
		};		    
				      
 </script>