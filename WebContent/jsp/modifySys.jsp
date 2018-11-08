<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

<title>新增环境</title>
</head>
<body>
	<button onclick="goback()">返回</button>
	<form action="modifysys.action" method="post" style="text-align: center;">
		<c:forEach items="${list }" var="sys">
			<input type="hidden" name="id" value="${sys.id }" >
			环境名称：<input type="text" name="sysname" value="${sys.systemname }"/>
			域名：<input type="text" name="url" value="${sys.urlname }"/>
			描述：<input type="text" name="des" value="${sys.des }"/><br>
			</c:forEach>
			<input type="reset" value="重置"> <input type="submit" value="保存">
	</form>
<script type="text/javascript">
	function goback(){
		window.history.go(-1);
	}
</script>
</body>
</html>