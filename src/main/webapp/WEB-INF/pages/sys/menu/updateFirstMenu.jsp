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
    <title>二级菜单修改</title>
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
  <legend>二级菜单修改</legend>
</fieldset> 
   <form class="layui-form" action="sys/menu/first/update.do" method="post"> 
<%--      <input type="hidden" name="power" value="${power }"/> 
 --%>     <input type="hidden" name="m_id" value="${m_id}"/> 
  <div class="layui-form-item">
    <label class="layui-form-label">菜单标题</label>
    <div class="layui-input-block">
      <input type="text" id="m_title" name="m_title" required  lay-verify="required" placeholder="请输入标题"  value="${m_title}"  autocomplete="off" class="layui-input">
    </div>
  </div> 
  <div class="layui-form-item">
    <label class="layui-form-label">菜单标签</label>
    <div class="layui-input-inline">
      <input type="text"   id="m_icon" name="m_icon" required  lay-verify="required" value="${m_icon}"  placeholder="请输入图标" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">fa图标</div>
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label">菜单状态</label>
    <div class="layui-input-block">
      <select name="is_userful" lay-verify="required">
       <%
       		 String is_useful = (String)session.getAttribute("is_userful");

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
	window.location.href = "sys/index.do";
};
</script>
</body>
 
</html>
