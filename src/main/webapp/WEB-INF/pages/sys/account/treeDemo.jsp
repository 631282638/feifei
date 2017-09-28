<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>tree demo</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css"  media="all">	
  <script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
    <link rel="stylesheet" href="<%=basePath%>common/eazyui/themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="<%=basePath%>common/eazyui/themes/icon.css" type="text/css"></link>
    
	<script type="text/javascript" src="<%=basePath%>common/eazyui/jquery.min.js"></script>
  	<script type="text/javascript" src="<%=basePath%>common/eazyui/jquery.easyui.min.js"></script>
  	
  	
<script type="text/javascript">		

	/* $(function(){
		$('#t1t').tree({
	    	onClick: function(node){
	    		alert(node.text);  // alert node text property when clicked
	    	}
	    }); 
		
	})
	
	  
	     */
	     layui.use('form', function(){
	    	  var form = layui.form;
	    	  //监听提交
	    	  form.on('submit(formForUpdate)', function(data){
	    	  	//   layer.msg(JSON.stringify(data.field));
	    	  	 	alert(JSON.stringify(data.field))
	    	    });
	    	});
	function test(){
		 var row = $('#t1t').tree('getChecked');
	     if (row){
	    	 alert(row.length)
	    	alert(JSON.stringify(row));

	     }
		
	}
	    
	</script> 
</head>
<body>
<h2>Basic Tree</h2>
	<p>Click the arrow on the left to expand or collapse nodes.</p>
	<div style="margin:20px 0;"></div>
	
	<div class="easyui-panel" style="padding:5px">
	  
		<ul id="t1t" name="treeda" class="easyui-tree" checkbox="true" url='tree/data.do'  >
		
		</ul>
		<button onclick="test()">sss</button>
    
		
	</div>
  </body>
</html>
