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
    <title>一级菜单添加</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=basePath%>common/css/font-awesome.min.css" rel="stylesheet"></link>
	<link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css"></link>
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.11.1.js"></script>
	 <style type="text/css">
		.dd_add_cs{background: white;}
		.add_panel_css{
			padding-top: 20px;
			padding-left: 0px;
		}
	</style>
</head>
  <body class="dd_add_cs">
  <div class="add_panel_css">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>一级菜单添加</legend>
</fieldset> 
 <form class="layui-form" action="sys/menu/first/add.do" method="post">
  <input type="hidden" name="m_sort" value="${m_sort }"/>
  <input type="hidden" name="power" value="${power }"/>
  <div class="layui-form-item">
    <label class="layui-form-label">菜单名称</label>
    <div class="layui-input-inline">
      <input type="text" id="m_title" name="m_title" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">菜单标签</label>
    <div class="layui-input-inline">
      <input type="text" id="m_icon" name="m_icon" required  lay-verify="required" placeholder="请输入标签" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">fa标签</div>
  </div>

  <div class="layui-form-item">
    <div class="layui-input-block">
      <button  class="layui-btn" lay-submit lay-filter="forAddMenuData">立即提交</button>
<!--       <button class="layui-btn" lay-btn lay-filter="close_add_f">返回</button>
 -->    
 	 <button type="button" onclick="backButtonUp();"class="layui-btn layui-btn-normal">取消</button>
 	 <script type="text/javascript">
 	// 返回上一级
		var backButtonUp = function(){
			window.location.href = "sys/index.do?power="+'${power }';
		};
 	 </script>
 	  </div>
  </div>
 
</form>

 </div>
 
</body>
 
<script type="text/javascript">
 init();
var init = function(){
layui.use('form', function(){ 
  var form = layui.form;
  //监听提交
  form.on('submit(forAddMenuData)', function(data){
  });
});
};
</script>
</html>
