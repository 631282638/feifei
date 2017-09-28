<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引入样式-->
<link href="<%=request.getContextPath()%>/common/css/common.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/jquery-1.11.1.min.js"></script>
<!-- Scroll bar for browser  -->
<script
	src="<%=request.getContextPath()%>/common/js/jquery.nicescroll.min.js"></script>
<!-- for drag elements -->	
<script src="<%=request.getContextPath()%>/common/js/draggabilly.pkgd.min.js"></script>
<!-- about Enlargement of the picture -->
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/basic/js/prefixfree.min.js"></script> --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/json2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/json_parse.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/json_parse_state.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/jquery.base64.js"></script>
	
<script type="text/javascript">

	$(function() {
	
	$(document).ajaxComplete(function(event, xhr, settings) { 
	if(xhr.getResponseHeader("sessionstatus")=="timeout"){ 
	
			alert("会话过期，请重新登陆!");
			window.location.href="<%=request.getContextPath()%>";
	
	} 
	}); 
	
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				closeShowWarnMessage();
			}
		}
	});

	function sleep(numberMillis) {
		var now = new Date();
		var exitTime = now.getTime() + numberMillis;
		while (true) {
			now = new Date();
			if (now.getTime() > exitTime)
				return;
		}
	};
	
</script>