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
    <title>修改用户信息</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css"  media="all">	
  <script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	 <style type="text/css">
		.dd_add_cs{background: white;}
		.add_panel_css{
			padding-top: 20px;
			padding-left: 10px;
		}
	</style>
</head>
  <body class="dd_add_cs">
  <div class="add_panel_css">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>用户信息修改</legend>
</fieldset> 
   <form class="layui-form" action="account/info/update.do" method="post">  
     <input type="hidden" name="user_account" value="${user_account}"/> 
  <div class="layui-form-item">
    <label class="layui-form-label">用户名称</label>
    <div class="layui-input-block">
      <input type="text" id="user_name" name="user_name" required  lay-verify="required" placeholder="请输入用户名"  value="${user_name}"  autocomplete="off" class="layui-input">
    </div>
  </div> 
  <div class="layui-form-item">
    <label class="layui-form-label">用户机构</label>
    <div class="layui-input-inline">
      <input type="text"   id="code" name="code" required  lay-verify="required" value="${code}"  placeholder="请输入机构号" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">暂时为维护机构树</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">账号状态</label>
    <div class="layui-input-block">
      <select name="is_userful" lay-verify="required">
       <%
       		 String is_useful = (String)request.getAttribute("is_userful");

       		if(is_useful.equals("可用")){
       			%> 	 
       			<option value="1">可用</option>
       			<option value="0">不可用</option>
       			<% 
       			
       		}else{
       			%>
       			<option value="0">不可用</option>
       			<option value="1">可用</option>
       			<% 
       		}
       %>
    
      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">锁定状态</label>
    <div class="layui-input-block">
      <select name="is_lock" lay-verify="required">
       <%
       		 String is_lock = (String)request.getAttribute("is_lock");

       		if(is_lock.equals("锁定")){
       			%> 	 
       			<option value="1">锁定</option>
       			<option value="0">未锁定</option>
       			<% 
       			
       		}else{
       			%>
       			<option value="0">未锁定</option>
       			<option value="1">锁定</option>
       			<% 
       		}
       %>
    
      </select>
    </div>
  </div>

  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formForUpdate">立即提交</button>
 	 <button type="button" onclick="backButtonUp();"class="layui-btn layui-btn-normal">取消</button>
    </div>
  </div>
</form>
  
  </div>
 
 
<script>
layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(formForUpdate)', function(data){
  /*   layer.msg(JSON.stringify(data.field));
    return false; */
  });
});
var backButtonUp = function(){
	window.location.href = "account/show/index.do";
};
</script>
</body>
 
</html>
