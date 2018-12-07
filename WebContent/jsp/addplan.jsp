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
<title>新增计划</title>
</head>
<body>
<div>
	<button onclick="goback()">返回</button>
</div>
<div style="text-align: center;">
	<form action="addplan.action" method="post">
			选择测试环境：<select name="sysid">
				<c:forEach items="${list }" var="sys">
					<option value="${sys.id }">${sys.systemname }</option>
				</c:forEach>
			</select>
			计划名称：<input  name="pname" type="text">
			计划描述：<input name="des" type="text"><br>
			<input type="submit" value="保存">
	</form>
</div>

<script type="text/javascript">
	function goback(){
			window.history.go(-1);
		}
</script>
</body>
</html>