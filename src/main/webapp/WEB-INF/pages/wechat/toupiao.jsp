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

	</style>
  </head>
  <body>

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
	}

	</script>

