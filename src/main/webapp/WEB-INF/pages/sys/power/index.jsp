<%@ page language="java" import="java.util.*,com.shuixiaofei.entity.MenuInfo" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
  <legend><font color='green'>当前权限</font></legend>
</fieldset> 
  <table>
  		<%
  		Map<String,Object> map = (Map<String,Object>)request.getAttribute("menu_data");
			List<MenuInfo> firstList = (List<MenuInfo>)map.get("firstHtml");
  			List<MenuInfo> secondList = (List<MenuInfo>)map.get("secondHtml");
  			%>
  			<h3 style='margin-top:5px;margin-left:5px;'>一级菜单</h3><table class='layui-table'>
  			<% 
  			String trString = "";
		     int defaultCount = 6; 
		     int recordCount = firstList.size();//总记录数
		     int countLine = 0 ;//总记录行数
		     if(recordCount % defaultCount == 0){
		         countLine = recordCount / defaultCount;
		     }else{
			  double temp = Math.floor(recordCount/defaultCount);
		       countLine =  (new   Double(temp )).intValue()   + 1;
		     }
		     for(int j = 0 ;j < countLine; j++){
		    	 String firstTemp = "";
			        int begin = j*defaultCount;
			        int end = (j+1)*defaultCount;
			        for(int i= begin;i<end;i++){
				        if(i >= recordCount){
				           break;
				        }
				        MenuInfo tmenuInfo = firstList.get(i);
				        firstTemp += "<td><input type='checkbox' name='power' value='" + tmenuInfo.getM_code()+"' " + tmenuInfo.getItem() + "/>" + tmenuInfo.getM_title()+"</td>";
			        }
			        trString += "<tr>" + firstTemp + "</tr>";
			     }
	     %>
		     <%=trString %>
		     </table>
		     <h3 style='margin-top:5px;margin-left:5px;'>二级菜单</h3><table class='layui-table'>
		     <%
			 String strString = "";
		     int srecordCount = secondList.size();//总记录数
		     int scountLine = 0 ;//总记录行数
		     if(srecordCount % defaultCount == 0){
		    	 scountLine = srecordCount / defaultCount;
		     }else{
			  double temp = Math.floor(srecordCount/defaultCount);
			  scountLine =  (new   Double(temp )).intValue()   + 1;
		     }
		     for(int j = 0 ;j < scountLine; j++){
		    	 String secondTemp = "";
			        int begin = j*defaultCount;
			        int end = (j+1)*defaultCount;
			        for(int i= begin;i<end;i++){
				        if(i >= srecordCount){
				           break;
				        }
				        MenuInfo tmenuInfo = secondList.get(i);
				        secondTemp += "<td><input type='checkbox' name='power' value='" + tmenuInfo.getM_code()+"' " + tmenuInfo.getItem() + "/>" + tmenuInfo.getM_title()+"</td>";
			        }
			        strString += "<tr>" + secondTemp + "</tr>";
			     }
  			 %>
    		 <%=strString %>
		     </table>
  			<table style='width:100%;border:0px solid #000;margin-bottom:10px;margin-top:10px;'>
  			<tr><td style='border:0px solid #000;' align='center'>
  			<button id="modifyPower" class='layui-btn'><i class='layui-icon'>&#xe610;</i> 分配并修改</button></td>
  			<td style='width:20px;'></td><td align='left'><button id="cancelModifyPower"  onclick='cancelModifyPower()' class='layui-btn'>返回</button></td></tr>
  			</table>
  	
  
  </table>
  
  </div>
 
 
<script>
	// 取消权限查看
	$("#cancelModifyPower").bind("click",function(){
		 layui.use('layer', function() {
		 layer = layui.layer; //独立版的layer无需执行这一句
	 	 var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
		 window.location.href="account/show/index.do";
		 });
	});
	// 修改权限
	$("#modifyPower").bind("click",function(){
		var user_acc = '${user_acc}';
		var check_val = [] ;
		  var checkboxObj = $("input[name=power]:checked");
		    checkboxObj.each(function(index){
		       check_val.push($(this).val());
		    });
		     var codes = check_val.join(",");
		     var model = new Object();
		     model.param1 = user_acc;
		     model.param2 = codes ; 
		  layui.use('layer', function() {
				var layer = layui.layer;
					layer.confirm('权限确认要修改吗?',{offset:'100px',icon:0},function(indexBtn){
						$.ajax({
						     url:"sys/power/update.do",
						     type:"post",
						     data:model,
						     success:function(result){ 
									if(result>0){
										layer.msg("修改成功",{icon: 1,offset:'120px'});
									 	layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
										window.location.href="account/show/index.do";	
									}else{
										layer.msg("网络异常",{icon: 1,offset:'120px'});
									}
									
						     },
						     error:function(error){
						     	alert("网络异常!");
						     }
						});
					},function(index){
				//	   alert("取消修改了");
					} 
				);				 
			});
	});
	



</script>
</body>
 
</html>
