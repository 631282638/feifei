<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/common/basic.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>登录页面</title>
     <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.11.1.js"></script>
	<style type="text/css">
	*{margin: 0px;}
	 .panel{
	  width:100%;
	  height:100%;

	    
	 }
	 .panel img{
	  width:100%;
	  height:100%;
	 
	 }
	</style>
  </head>
  <body>
  <div class="panel">
  <img alt="" src="common/image/yz_login_02.jpg">
  </div>

  </body>
</html>
	<script type="text/javascript">	
	 $(document).ready(function(){
		$(window).resize(function() {
			init();
			});
		});
	$(function() {
		init();
	});
	
	var init = function(){
	layui.use('layer', function(){ //独立版的layer无需执行这一句
 	 var $ = layui.jquery,
 	 layer = layui.layer; //独立版的layer无需执行这一句
 	 var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
			var height = window.innerHeight;
			var width =  window.innerWidth; 
	//		$(".mainbody_css").css("height",height-99);
	//		$(".login_bg_css").css("height",height-99);	   
			layer.close(index);
			  	});
	};
	
	if(window!=top){
	 top.location.href=location.href;
	}

	</script>

