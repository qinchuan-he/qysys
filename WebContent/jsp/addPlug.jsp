<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<title>新增组件</title>
</head>
<body>
<div>
	<button id="goback">返回</button>
</div>
<div>
	<form action="addPlug.action" method="post">
		组件名称：<input name="plname" type="text">&emsp;
		描述：<input name="pldes" type="text">
		<input type="submit" value="保存">
	</form>
</div>	
	<script type="text/javascript">
		$("#goback").click(function() {
			window.history.go(-1);
		});
	</script>
</body>
</html>