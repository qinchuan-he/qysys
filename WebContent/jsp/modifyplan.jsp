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
<title>修改计划</title>
</head>
<body>
<div>
	<button onclick="goback()">返回</button>
</div><br>
<div style="text-align: center;">
<form action="modifyPlan.action" method="post">
	<table border="1px" width="80%">	
			<input name="id" type="hidden" value="${list.id }">
			<tr><td>所属环境</td><td>
				<select name="sysid">
					<option value="${list.sysid }">${list.systemname }</option>
					<c:forEach items="${syslist }" var="sys">
						<option value="${sys.id }">${sys.systemname }</option>
					</c:forEach>
				</select>			
			</td>
			<td>计划名字</td><td><input name="pname" type="text" value="${list.pname }"> </td>
			<td>计划描述</td><td><input name="des" type="text" value="${list.des }"> </td>
			</tr>
	
		
	</table><br>
	 <input type="reset" value="重置"> &emsp; <input type="submit" value="保存">
</form>	
</div>

<script type="text/javascript">
		function goback(){
				window.history.go(-1);
			}
</script>
</body>
</html>