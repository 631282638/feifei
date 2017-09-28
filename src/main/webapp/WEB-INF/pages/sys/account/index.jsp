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
    <title>综合业务管理系统</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	<link rel="stylesheet" href="<%=basePath%>common/eazyui/themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="<%=basePath%>common/eazyui/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath%>common/eazyui/jquery.min.js"></script>
  	<script type="text/javascript" src="<%=basePath%>common/eazyui/jquery.easyui.min.js"></script>
 <style>
</style>
 </head>
<body>
 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>系统用户管理</legend>
</fieldset> 
<!--   <button class="layui-btn layui-btn-normal" onclick="addMenu();">增加</button>
</div>  --> 
<div class="layui-btn-group btnaccount">
  <button class="layui-btn layui-btn-small" id="add_user">
    <i class="layui-icon">&#xe654;</i>添加
  </button>
  <button class="layui-btn layui-btn-small" data-type="getCheckData" >
    <i class="layui-icon">&#xe642;</i>修改
  </button>
<!--   <button class="layui-btn layui-btn-small">
    <i class="layui-icon">&#xe640;</i>删除
  </button> -->

</div>
 <script type="text/html" id="barDid">
  <a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="editpower">权限修改</a>

</script>
<!--  <a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="onordown">禁用/启用</a>
  <a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="release">解锁</a>  -->

<table class="layui-table" lay-data="{ url:'account/info.do', page:false, id:'user_id'}" lay-filter="user_id">
  <thead>
<!--   u.user_id,u.user_name,u.user_account,u.power,u.`code`,u.is_userful,u.is_lock
 -->    <tr>
      <th lay-data="{checkbox:true,align:'center'}"></th>
<!--       <th lay-data="{field:'user_id',width:150, display:'none'}">id</th>
 -->      <th lay-data="{field:'user_name',width:150  }">用户名称</th>
      <th lay-data="{field:'user_account', width:150}">用户账号</th>
      <th lay-data="{field:'is_userful',width:100,}">是否可用</th>
      <th lay-data="{field:'is_lock',width:100}">是否锁定</th>
      <th lay-data="{fixed: 'right',width:200, align:'center', toolbar: '#barDid'}">权限管理</th>
    </tr>
  </thead>
</table>
     
    
    
  </body>
  </html>
 <script type="text/javascript">	
$(function(){
	 layui.use('layer', function(){
			  var layer = layui.layer;
			  if ('${add_msg}' != null && '${add_msg}' != "") {
				  layer.alert('${add_msg}');
			 }
			 if ('${u_u_msg}' != null && '${u_u_msg}' != "") {
				  layer.alert('${u_u_msg}');
			 }
	 });  
});
 
 



layui.use('table', function(){
  var table = layui.table;

  
  //监听工具条
  table.on('tool(user_id)', function(obj){
    var data = obj.data;
   if(obj.event === 'editpower'){
	  //  window.location.href = "sys/show/power/index.do?user_acc="+data.user_account;
    	//  layer.alert('编辑行：<br>'+ JSON.stringify(data))
    	modifyPower(data.user_account);
    }
  });
  
  // 权限修改
  var modifyPower = function(user_account){
	  var htmlStr = " <ul id='tt_tree' class='easyui-tree' checkbox='true'  ></ul></br>";
		var buttonStr = "<table style='width:50%;border:0px solid #000;margin-bottom:10px;margin-top:10px;align:center;margin:auto;' >"+
		"<tr><td style='border:0px solid #000;' align='center'> "+
		"<button  id='modify_power_i' class='layui-btn layui-btn-normal layui-btn-small'>确认分配</button></td><td align='center'><button  id='cancel_modify_i' class='layui-btn layui-btn-normal layui-btn-small'>取消</button></td></tr></table>";
		
	     var layer = layui.layer;
           layer.open({
	            title:'修改权限',
			    type: 1,
			    icon: 2,
			    offset: '20px',
			    area: ['230px', '300px'],
			    content:   htmlStr + buttonStr
			  });
           
           $('#tt_tree').tree({
        	    url:'sys/tree/data.do?user_acc='+user_account
        	}); 
           // 修改权限哟呼呼
           document.getElementById("modify_power_i").onclick=function(){ 
    		var check_val = [] ;
   		    var row = $('#tt_tree').tree('getChecked');
   		 /* 		 row.each(function(index){
    		       check_val.push($(this).id);
    		    }); */
   		   for(var i=0;i<row.length;i++){
			//	alert(JSON.stringify(row)); 
			var cid = row[i].id;
			var candd = cid.split("-");
   			check_val.push(candd[1]);
   		   }	 
    		     var codes = check_val.join(",");    		     
    		     var model = new Object();
    		     model.param1 = user_account;
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
        	   
        	   
        	   
           };
           // 取消绑定
           document.getElementById("cancel_modify_i").onclick=function(){
        	   layui.use('layer', function() {
        			 var layer = layui.layer;
        			layer.closeAll(); 
        		});      
           };
           
  };
 // 监听选择复选框事件
  var $ = layui.$, active = {
    getCheckData: function(){
      //监听操作的是哪个表格
      var checkStatus = table.checkStatus('user_id')
      ,data = checkStatus.data;
      //  data.length  长度
      //  layer.msg(checkStatus.isAll ? '全选': '未全选')
	 if(data.length>1||data.length<1){
	      layer.alert("请选择一条进行修改！");
		 return;
	 }else{		   
		 window.location.href = "account/info/update/show.do?user_account="+data[0].user_account+
		    			"&user_name="+encodeURI(encodeURI(data[0].user_name))+"&is_userful="+encodeURI(encodeURI(data[0].is_userful))+"&is_lock="+encodeURI(encodeURI(data[0].is_lock))
		    			+"&code="+data[0].code;
		 
	 }
    }
    /* ,isAll: function(){
      var checkStatus = table.checkStatus('menu_id');
    }
    ,reload: function(){
      var demoReload = $('#demoReload');
      
      ins1.reload({
        where: {
          key: {
            id: demoReload.val()
          }
        }
      });
    }
    ,parseTable: function(){
      table.init('parse-table-demo');
    } */
  };
  
  $('.btnaccount .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  }); 
  
  
  

});

  
  $("#add_user").bind("click",function(){
	  var layer = layui.layer; //独立版的layer无需执行这一句
	  var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
	  window.location.href = "account/info/add/show.do";
	  
  });
  

  
  
  

 </script>