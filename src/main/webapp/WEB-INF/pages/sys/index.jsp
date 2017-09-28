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
	<link href="<%=basePath%>common/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
 <style>

.tbo{
height:90%;
overflow-y:auto;
background:white;

}
.demo-class{
	background-color: red;
}

</style>
 </head>
  <body class="tbo layui-anim layui-anim-upbit ">
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li  class="layui-this">一级菜单管理</li>
    <li>权限分配</li>
  </ul>
    <div class="layui-tab-content" style="height: 100px;">
    <!-- the first menu  -->
    <div class="layui-tab-item layui-show">
       <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>系统菜单管理</legend>
</fieldset> 
 
<!-- <div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div>  -->
<div class="layui-btn-group">
  <button class="layui-btn layui-btn-normal" onclick="addMenu();">增加</button>
  <!-- <button class="layui-btn">编辑</button>
  <button class="layui-btn">删除</button> -->
</div>  
 <script type="text/html" id="barDid">
  <a class="layui-btn layui-btn-mini" lay-event="detail">子菜单</a>
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-mini" lay-event="del">删除</a>
</script>
<table class="layui-table" lay-data="{ url:'sys/menu/one.do', page:false, id:'menu_id'}" lay-filter="menu_id">
  <thead>
<!--    m.m_id,m.p_id,m.m_title,m.m_url,m.m_sort,m.m_icon,m.m_code 
 -->    <tr>
<!--       <th lay-data="{checkbox:true,width:80, fixed: true}"></th>
 -->  <th lay-data="{field:'m_id',width:50,  fixed: true}">id</th>
      <th lay-data="{field:'m_title',width:120 }">菜单名称</th>
      <th lay-data="{field:'m_sort', width:50}">排序</th>
      <th lay-data="{field:'m_icon',width:150}">标签</th>
      <th lay-data="{field:'m_code',width:100}">权限码</th>
      <th lay-data="{field:'is_userful',width:80}">状态</th>
      <th lay-data="{fixed: 'right',width:200, align:'center', toolbar: '#barDid'}">操作</th>
    </tr>
  </thead>
</table>
     
    
    </div>
    <!-- wait for use  -->
    <div class="layui-tab-item">
  
     <!-- 
<table class="layui-table" lay-data="{height:415, url:'test/data.do', page:true, id:'test'}" lay-filter="test">
  <thead>
    <tr>
      <th lay-data="{field:'id' sort: true}">ID</th>
      <th lay-data="{field:'username' }">用户名</th>
      <th lay-data="{field:'sex',  sort: true}">性别</th>
      <th lay-data="{field:'city' }">城市</th>
      <th lay-data="{field:'sign' }">签名</th>
      <th lay-data="{field:'experience',  sort: true}">积分</th>
      <th lay-data="{field:'score',  sort: true}">评分</th>
      <th lay-data="{field:'classify', }">职业</th>
      <th lay-data="{field:'wealth',  sort: true}">财富</th>
    </tr>
  </thead>
</table>
     -->
    
    
    </div>
  </div>
</div>      
  </body>
  </html>
 <script type="text/javascript">	
 layui.use('layer', function(){
		  var layer = layui.layer;
		  if ('${add_msg}' != null && '${add_msg}' != "") {
			  layer.alert('${add_msg}');
		 }
		 if ('${update_msg}' != null && '${update_msg}' != "") {
			  layer.alert('${update_msg}');
		 }

		});  
 
 //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
 layui.use('element', function(){
  var element = layui.element;
  });
  

layui.use('table', function(){
  var table = layui.table;
 
/*   //监听表格复选框选择
  table.on('checkbox(test1)', function(obj){
    console.log(obj)
  }); */
  
  //监听单元格编辑
 /*  table.on('edit(test3)', function(obj){
    var value = obj.value //得到修改后的值
    ,data = obj.data //得到所在行所有键值
    ,field = obj.field; //得到字段
    
    data[field] = value; //更新缓存中的值
    
    layer.msg(value);
  }); */
  
  //监听工具条
  table.on('tool(menu_id)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
      //  layer.msg('ID：'+ data.m_id + ' 的查看操作');
    	window.location.href = "sys/second/menu/show.do?p_id="+data.m_id;
    } else if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
      
        /* obj.del();
        layer.close(index);
         */
        updateMenuUnUseful(data.m_id);
      });
    } else if(obj.event === 'edit'){
    	window.location.href = "sys/menu/update/first/show.do?m_id="+data.m_id+
    			"&m_title="+encodeURI(encodeURI(data.m_title))+"&m_icon="+data.m_icon+"&is_userful="+encodeURI(encodeURI(data.is_userful));
    	//  layer.alert('编辑行：<br>'+ JSON.stringify(data))
    }
  });
  
 /*  //监听单元格事件
  table.on('tool(demoEvent)', function(obj){
    var data = obj.data;
    if(obj.event === 'setSign'){
      layer.prompt({
        formType: 2
        ,title: '修改 ID 为 ['+ data.id +'] 的用户签名'
        ,value: data.sign
      }, function(value, index){
        layer.close(index);
        
        //这里一般是发送修改的Ajax请求
        
        //同步更新表格和缓存对应的值
        obj.update({
          sign: value
        });
      });
    }
  }); */
  
  
  var $ = layui.$, active = {
    getCheckData: function(){
      var checkStatus = table.checkStatus('menu_id')
      ,data = checkStatus.data;
      layer.alert(JSON.stringify(data));
    }
    ,getCheckLength: function(){
      var checkStatus = table.checkStatus('menu_id')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个');
    }
    ,isAll: function(){
      var checkStatus = table.checkStatus('menu_id');
      layer.msg(checkStatus.isAll ? '全选': '未全选')
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
    }
  };
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  }); 
});

  // 添加菜单
  var addMenu = function(){
		 layer = layui.layer; //独立版的layer无需执行这一句
	 	 var index = layer.load(0,{shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
	 	 window.location.href = "sys/menu/add/first/show.do?power="+'${power}';
  
/*  layer.open({
  btnAlign: 'c',
 skin: 'demo-class',
  title:'一级菜单添加',
  type: 2, 
  content: ['http://localhost:8080/LinYiPost/sys/menu/add/first/show.do','no'] 
  ,area: ['500px', '600px'] ,//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
  btn: ['继续弹出', '全部关闭'] //只是为了演示
        ,yes: function(){
          $(that).click(); 
        }
        ,btn2: function(){
          layer.closeAll();
        }
	});  */
  };
  var updateMenuUnUseful = function(m_id){
   $.ajax({
			url:"/update/first/unuse.do",
			type:'post',
			data:{m_id:m_mid},
			success:function(result){
			      if(result != null && result != ""){
			        if(result==1){
			           var obj = JSON.parse(result);
			         alert(obj.mm);
			        }else{
			         alert(失败);
			        }
			      
			     
			      }
			},
			error: function(error){
			    alert("异常");
			}
		});
  
  }
  

 </script>