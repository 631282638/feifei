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
    <title>二级菜单信息</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=basePath%>common/css/font-awesome.min.css" rel="stylesheet"></link>
	<link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.11.1.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/jquery.base64.js"></script>
	 <style type="text/css">	
	 .add_panel_css{
			padding-top: 20px;
			padding-left: 0px;
		}
	.btn_pos{
		padding-left: 0px;
		}
	</style>
</head>
  <body class="dd_add_cs">
<!--  begin ------------------------------------------------------------------------  -->	 
<!-- <div class="layui-btn-group btn_pos" >
  <button class="layui-btn layui-btn-normal" id="backToOne">返回上一级</button>
</div>  -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
	  <legend>二级菜单数据列表</legend>
	</fieldset> 
	<script type="text/html" id="barDid">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="editInfo">编辑</a>
  <a class="layui-btn layui-btn-mini" lay-event="toup">上移</a>
  <a class="layui-btn layui-btn-mini" lay-event="todown">下移</a>
</script>
 <button class="layui-btn" id="addSecondMenu">添加二级菜单</button>
 <button class="layui-btn layui-btn-normal" id="backToOne">返回上一级</button>
  
<table class="layui-table" id="second_menu_data_griad"  lay-filter="second_menu_data_griad" >
    
</table>
                     
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
$(function(){
	layui.use('layer', function(){
		  var layer = layui.layer;
			init();

		});              
});
var init = function (){
	  if ('${u_s_msg}' != null && '${u_s_msg}' != "") {
		  layer.alert('${u_s_msg}');
	  }
	  if ('${a_s_msg}' != null && '${a_s_msg}' != "") {
		  layer.alert('${a_s_msg}');
	  }
	var p_id = '${p_id}';
	var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
	layui.use('table', function(){
		  var table = layui.table;	  
			table.render({ //其它参数在此省略
			  elem: '#second_menu_data_griad',
			  url: 'sys/second/menu/query.do',
			  where: {p_id:p_id}, //如果无需传递额外参数，可不加该参数
			  method: 'post', //如果无需自定义HTTP类型，可不加该参数
			  page: false
		    ,height: 450
		    ,even: true,
		    
		    cols: [[ //标题栏
		      // {checkbox: true, LAY_CHECKED: true} ,//默认全选
		      // 	 m.,m.p_id,m.,m.,m.m_sort,m.,m.,CASE m.is_userful WHEN 1 then '可用' ELSE '不可用' end as is_userful

		      {field: 'm_id', title: 'ID', width: 100, sort: true}
		      ,{field: 'm_title', title: '标题', width: 130}
		      ,{field: 'm_url', title: '访问路径', width: 265}
		      ,{field: 'm_icon', title: '图标', width: 150}
		      ,{field: 'is_userful', title: '状态', width: 150}
		      ,{field: 'm_code', title: '权限码', width: 90}
		      ,{width:190,  toolbar: '#barDid',title:'操作',}
		      

		    ]] , 
			   //如果无需自定义请求参数，可不加该参数
		/* 	  response: {
			   statusName: 'status' //数据状态的字段名称，默认：code
			  ,statusCode: 200 //成功的状态码，默认：0
			  ,msgName: 'hint' //状态信息的字段名称，默认：msg
			  ,countName: 'total' //数据总数的字段名称，默认：count
			  ,dataName: 'rows' //数据列表的字段名称，默认：data
			  } //如果无需自定义数据响应名称，可不加该参数 */
			  done: function(res){
			  layer.close(index);
		    //如果是异步请求数据方式，res即为你接口返回的信息。
		    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
		 		 }
			}); 
			
		 table.on('tool(second_menu_data_griad)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'editInfo'){
			     //  layer.msg('ID：'+ data.m_title + ' 的查看操作');
			     layer = layui.layer; //独立版的layer无需执行这一句
 				 var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
			     window.location = "sys/show/second.do?m_title="+encodeURI(encodeURI(data.m_title))+"&url="+data.m_url+"&m_icon="+data.m_icon+"&is_userful="+encodeURI(encodeURI(data.is_userful))+"&m_id="+data.m_id+"&p_id="+'${p_id}';
			    } else if(obj.event === 'del'){
			      layer.confirm('真的删除行么', function(index){
			      
			        /* obj.del();
			        layer.close(index);
			         */
			        updateMenuUnUseful(data.m_id);
			      });
			    } else if(obj.event === 'edit'){
			      layer.alert('编辑行：<br>'+ JSON.stringify(data));
			      
			    }
			  });
			
		});

};

var editInfo = function(){
	
	
	
}
// onclick="backToOne();"   返回一级菜单管理页面
$("#backToOne").bind('click',function(){
	 layer = layui.layer; //独立版的layer无需执行这一句
 	 var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
	 window.location.href = "sys/index.do";
});

$("#addSecondMenu").bind('click',function(){
	 layer = layui.layer; //独立版的layer无需执行这一句
 	 var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
	 window.location.href = "sys//show/second/add.do?p_id="+'${p_id}';
});

</script>
	 
	 
	 
	 
	 
	 
<!--  end --------------------------------------------------------------------------  -->
    
 
</body>
 


</html>
