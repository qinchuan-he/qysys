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
<title>计划执行列表</title>
</head>
<body>
<div><button onclick="goback()">返回</button></div>
<div style="text-align: center;">
	<table border="1px" width="80%" id="td" style="table-layout:fixed">
	<caption>执行结果</caption>
		<!-- 
		<tr style="text-align: center;" ><td colspan="6">执行结果</td></tr> -->
		<tr id="one"><td >序号</td><td>接口名字</td><td>响应结果</td><td>执行时间</td><td>计划执行次数</td><td>执行结果</td></tr>
		<c:forEach items="${list }" var="pld">
			<tr>
				<td >${pld.num }</td>
				<td><a id="${pld.apiid }" href="modifyinterselect.action?id=${pld.apiid }">${pld.inname }</a></td>
				<td style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap">${pld.responsedata }</td>
				<td>${pld.exetime }</td>
				<td>${pld.exenum }</td>
				<c:if test="${pld.result==1 }">
					<td>成功</td>
				</c:if>
				<c:if test="${pld.result!=1 }">
					<td>失败</td>
				</c:if>				
			</tr>
		</c:forEach>
		
	</table>
</div>

<script type="text/javascript">
	function goback(){
		window.history.go(-1);
	}
</script>
</body>
</html>