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
	body{
	background: #FAF0E6;
	}
/* 	.title_css{
		background: black;
		opacity:0.7;
		height: 27px;
		width: 100%;
	} */
 	.logo_css{
		height: 79px;
		width: 100%;
	} 
	.mainbody_css{
		position:absolute;
		top:79px;
		width: 100%;
		background-image: url('common/image/1.jpg');
		background-repeat: no-repeat;
		background-position: center center;
	}
	.login_bg_css{
		display:none;
		position:absolute;
		top:79px;
		width: 100%;
		background: grey;
		opacity:0.4;
		margin: auto;
		z-index: 1001;
	}	
	.lg_cs_bg{
		display:block;
		position:absolute;
		background: black;
		opacity:0.55; 
		width: 287px;
		height:290px;
		margin: auto;
		border:1px solid grey;
		border-radius:4px;
		z-index: 1002;
	}
	.lg_cs{
		display:block;
		position:absolute;
		/* 
		background: black;
		opacity:0.55;
		 */
		width: 287px;
		height:290px;
		margin: auto;
		border:1px solid grey;
		border-radius:4px;
		z-index: 1004;
	}
	.lg_close_cs{
		position: relative;
		top:5px;
		left:5px;
		height: 15px;
		width: 15px;
		color:white;
		text-align: center;
	}
	.lg_title_cs{
		display:block;
		position:relative;
		top:20px;
		font-size: 20;
		font-style:italic;
		color: white;
		text-align: center;
	}
	.lg_username_err{
		display:none;
		position:relative;
		top:20px;
		font-size: 12;
		color: red;
		text-align: center;
		height: 30px;
	}
	.lg_pwd_err{
		display:none;
		position:relative;
		top:20px;
		font-size: 12;
		color: red;
		text-align: center;
		height: 30px;
	}
	.lg_msg_err{
		display:none;
		position:relative;
		top:20px;
		font-size: 12;
		color: red;
		text-align: center;
		height: 30px;
	}
	.contain_css{
		position:relative;
		top:20px;
		width: 100%;
		height: 180px;
		text-align: center;
	
	}
	.username_css{
		position: relative;
		width: 220px;
		height: 40px;
		border: 0px;
		color: black;
		font-size: 16px;
		color: gray;
	}
	.pwd_css{
		position: relative;
		top:10px;
		width: 220px;
		height: 40px;
		border: 0px;
		font-size: 16px;
		color: gray;
	}
	.lg_btn_cs{
		position: relative;
		top:30px;
		width: 224px;
		height: 40px;
	}
	.lg_bottom_cs{
		position:relative;
		top:1px;
		width: 100%;
		height: 40px;
		text-align: center;
/*  		border: 1px solid yellow;	*/
		color: red;
		font-size: 12;
	}
	.lg_btn_cs:HOVER {
		 cursor: pointer;
		 
	}
	</style>
  </head>
  <body>
<!--   	<div class="title_css"></div>   -->
 	<div class="logo_css"></div>
 
  	<div class="mainbody_css" id="main_body_id">
  	
  	</div>
  	<div class="login_bg_css" align="center" >
  	</div>
  	  	  <div class="lg_cs_bg"></div>
  	
  	  <div class="lg_cs">
  	  		<div class="lg_close_cs"></div>
  	  		<div class="lg_title_cs">业务管理系统</div>
  	  		<div class="lg_username_err"></div>
  	  		<div class="lg_pwd_err">请输入密码！</div>
  	  		<div class="lg_msg_err"></div>
  	  		<div class="contain_css">
  	  			<form action="user/validate.do" method="post" id="log_form_id">
  	  			 <input type="text" name="user_account" class="username_css" placeholder="请输入账号"/>
  	  			 <input type="password" name="pwd" class="pwd_css"/>
  	  			 <input type="button" class="lg_btn_cs layui-btn layui-btn-normal" value="登录" onclick="validate()">
  	  			</form>
  	  			 
  	  		</div>
  	  		<div class="lg_bottom_cs"></div>  	  		
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
			 // window.screen.height ; 
			// document.documentElement.clientHeight;
			//  document.documentElement.clientWidth 
			$(".mainbody_css").css("height",height-99);
			$(".login_bg_css").css("height",height-99);
			$(".lg_cs").css("top",(height-79)/2-165+109);
			$(".lg_cs").css("left",width/2-163);
			
			$(".lg_cs_bg").css("top",(height-79)/2-165+109);
			$(".lg_cs_bg").css("left",width/2-163);
			//淡入背景
			$(".login_bg_css").fadeIn("slow");
			$(".username_css").val(readCookie("user_account"));		   
		  if ('${t_msg}' != null && '${t_msg}' != "") {
		  	 $(".lg_title_cs").hide();
		  	 $(".lg_pwd_err").hide();
		  	 $(".lg_username_err").hide();
		  	 $(".lg_msg_err").html('${t_msg}');
			 $(".lg_msg_err").show(); 
		}
			layer.close(index);
			  	});
		};
		
		var validate = function(){
		
  		if ($(".username_css").val().length <= 0) {
		// 	 alert("账号密码不能为空！！");
		//	$(".msg_username").html('账号不能为空！！');
			$(".lg_title_cs").hide();
			$(".lg_pwd_err").hide();
			 $(".lg_msg_err").hide();
			$(".lg_username_err").html("请输入账号！");
			$(".lg_username_err").show();
			$(".username_css").focus();
			return;
		}else{
			var reg = /^[0-9a-zA-Z]+$/;
			if(!reg.test($(".lg_username").val())){
		//	$(".msg_username").html('账号只能是字母和数子组合');
			$(".lg_title_cs").hide();
			$(".lg_pwd_err").hide();
			$(".lg_msg_err").hide();
			$(".lg_username_err").html("账号只能是字母和数子组合");
			$(".lg_username_err").show();
				return;
			}
		}
		if ($(".pwd_css").val().length <= 0) {
		//	alert("账号密码不能为空！！");
		//	$(".msg_password").html('密码不能为空！！');
			$(".lg_title_cs").hide();
			$(".lg_username_err").hide();
			$(".lg_msg_err").hide();
			$(".lg_pwd_err").show();	
			return;
		}else{	
		    var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
			$("#log_form_id").submit();
		}
  	};
  	var readCookie = function(name) {
		var cookieValue = "";
		var search = name + "=";
		if (document.cookie.length > 0) {
			offset = document.cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if (end == -1)
					end = document.cookie.length;
				cookieValue = unescape(document.cookie.substring(offset, end));
			}
		}
		return cookieValue;
	};
	if(window!=top){
	 top.location.href=location.href;
	}

	</script>

