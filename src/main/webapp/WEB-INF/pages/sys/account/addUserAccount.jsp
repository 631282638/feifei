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
    <title>用户账号添加</title>
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
  <legend>用户账号添加</legend>
</fieldset> 
   <form class="layui-form" action="account/info/add.do" method="post">  
  <div class="layui-form-item">
    <label class="layui-form-label">用户名称</label>
    <div class="layui-input-inline">
      <input type="text" id="user_name" name="user_name" required  lay-verify="required" placeholder="请输入账号"   autocomplete="off" class="layui-input">
    </div>
  </div> 
  
  <div class="layui-form-item">
    <label class="layui-form-label">用户账号</label>
    <div class="layui-input-inline">
      <input type="text" id="user_account" name="user_account" required  lay-verify="required" placeholder="请输入用户名"   autocomplete="off" class="layui-input">
    </div>
       <div id="user_a_msg" class="layui-form-mid layui-word-aux">&nbsp;</div>
  
  </div> 
  <div class="layui-form-item">
    <label class="layui-form-label">用户密码</label>
    <div class="layui-input-inline">
      <input type="password" id="user_pwd1" name="user_pwd1" required  lay-verify="required" placeholder="请输入密码"   autocomplete="off" class="layui-input">
    </div>
  </div> 
  
  <div class="layui-form-item">
    <label class="layui-form-label">用户密码</label>
    <div class="layui-input-inline">
      <input type="password" id="user_pwd2" name="user_pwd2" required  lay-verify="required" placeholder="请输入确认密码"   autocomplete="off" class="layui-input">
    </div>
    <div id="user_p_msg" class="layui-form-mid layui-word-aux">&nbsp;</div>
  </div> 
  
  <div class="layui-form-item">
    <label class="layui-form-label">用户机构</label>
    <div class="layui-input-inline">
      <input type="text"   id="code" name="code" required  lay-verify="required"   placeholder="请输入机构号" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">暂时为维护机构树</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">账号状态</label>
    <div class="layui-input-inline">
      <select name="is_userful" lay-verify="required">       
       			<option value="1">可用</option>
       			<option value="0">不可用</option>           
      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">锁定状态</label>
    <div class="layui-input-inline">
      <select name="is_lock" lay-verify="required"> 
       			<option value="0">未锁定</option>
           		<option value="1">锁定</option>
    
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
//初始化表填准备提交
layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(formForUpdate)', function(data){
  //   layer.msg(JSON.stringify(data.field));
    });
});
// 这里校验账号是否已经存在
$("#user_account").change(function(){
	// /user/exist.do
	var uaccount = $("#user_account").val();
	  $.ajax({
			url:"account/user/exist.do",
		type:'post',
		data:{uaccount:uaccount},
		success:function(result){	
			 if(result=='true'){
				// $("#user_a_msg").val("<font color='red'>账号已存在</font>");
				 $("#user_a_msg").html("<font color='red'>账号已存在</font>");
				 $("#user_account").val("");
			   }else{
					 $("#user_a_msg").html("");
			   }
		},
		error: function(){
			alert("网络异常");
		}		
	});
			
});
// 校验两次密码是否一致
$("#user_pwd2").change(function(){
	var pwd1 = $("#user_pwd1").val();
	var pwd2 = $("#user_pwd2").val();
	if(pwd1==pwd2){
		$("#user_p_msg").html("");
	}else{
		$("#user_pwd2").val("");
		$("#user_p_msg").html("<font color='red'>两次密码不一致</font>");
		
	}
	
});


//取消添加返回账号列表
var backButtonUp = function(){
	window.location.href = "account/show/index.do";
};
</script>
</body>
 
</html>
