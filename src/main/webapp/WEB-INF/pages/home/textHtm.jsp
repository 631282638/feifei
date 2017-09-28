<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'textHtm.jsp' starting page</title>
   
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="<%=basePath%>common/css/bootstrap.css" rel="stylesheet">
	
	<link href="<%=basePath%>common/css/font-awesome.min.css" rel="stylesheet">
	
	<style type="text/css">
	.aa{
		background-color: #2E8B57;
	}
	.bb{
		background-color: #cb4a5d;
	}
	</style>
  </head>
  
  <body>
    sss
    <div>
    <i class="icon camera-retro"></i> icon-camera-retro
        <i class="fa fa-bars"></i>aa
    
    </div>
    <div class="aa">dsddssdds</div>
    <div class="bb">gggggg</div>
    ss
      <button type="button" onclick="test();">返回</button>
    
  </body>
</html>
<script type="text/javascript">
var backButton = function(){
alert("11")
	window.location.href = "sys/index.do";
};

function test(){
	alert("11")
}

</script>