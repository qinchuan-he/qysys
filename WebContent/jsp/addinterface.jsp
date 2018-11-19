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
<title>新增接口页面</title>
</head>
<body>
<button onclick="goback()">返回</button>
<div style="text-align: center">
<form action="addinter.action" method="post">
	<div>
	请选择环境：
	<select name="sysid">
		<option value="0">请选择环境</option>
		<c:forEach items="${syslist }" var="sys">
			<option value="${sys.id }">${sys.systemname }</option>
		</c:forEach>
	</select>
	选择请求方式：
	<select name="method1">
		<option value="post">post</option>
		<option value="get">get</option>
	</select>
	输入接口名字<input type="text" name="name">
	<input type="submit" value="保存"><br>
	请求地址：<input type="text" name="url">&emsp;
	 描述：<input type="text" name="des"><br>
	<textarea name="param" rows="20" cols="80" placeholder="输入参数"></textarea><br>
	响应断言:<input type="text" name="check">
	</div>
	
</form>
</div>
<script type="text/javascript">
	function goback(){
		window.history.go(-1);
	}
</script>
</body>
</html>