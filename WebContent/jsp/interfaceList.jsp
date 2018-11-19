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
<title>接口页面</title>
</head>
<body>
	<div style="text-align: center;">
		输入接口名称<input><input type="button" value="查询">&nbsp;&emsp;<a href="addinterface.action"><button>新增</button></a>
	</div>
	<div>
		<table border="1px" width="80%">
			<tr id="one">
				<td>接口名称</td><td>URL</td><td>请求方式</td><td>描述</td><td>操作</td>
			</tr>
			<c:forEach items="${list }" var="inter">
				<tr>
				<td>${inter.inname }</td><td>${inter.url }</td><td>${inter.method }</td><td>${inter.des }</td>
					<td><a href="modifyinterselect.action?id=${inter.id }">修改</a>&emsp;<a href="deleteinter.action?id=${inter.id }">删除</a>
						&emsp;<a href="testinter.action?id=${inter.id }">执行</a>
					</td>					
				<tr>
			</c:forEach>
		</table>
		
	</div>
</body>
</html>