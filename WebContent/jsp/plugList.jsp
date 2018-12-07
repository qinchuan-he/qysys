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
<title>组件列表</title>
</head>
<body>
	<div>
		<button onclick="goback()">返回</button>
	</div>
	<div>
		输入组件名称：<input id="plugName" type="text" name="plname">&emsp;<button onclick="searchName()">查询</button>&emsp;
		<a href="addPlug.jsp"><button>新增</button></a>
	</div>
	<div>
		<table id="tab" border="1px" width="80%">
			<tr id="one">
				<td>名字</td><td>描述</td><td>更新时间</td><td>操作</td>
			</tr>
			<c:forEach items="${list }" var="plug">
				<tr><td>${plug.plname }</td><td>${plug.pldes }</td><td>${plug.updatetime }</td>
				<td>	
					<a href="describeplug.action?id=${plug.id }">详情</a>
					<a href="modifyselectplug.action?id=${plug.id }">修改</a>
					<a href="deleteplug.action?id=${plug.id }">删除</a>
				</td></tr>
			</c:forEach>
		</table>
	</div>
	
	<script type="text/javascript">
		function goback(){
			window.history.go(-1);
		}
		
		function searchName(){
			var data=$("#plugName").val();
			$("#one~tr").remove();
			var s={"name":data};
			$.ajax({
				url: "searchPlugName.action?name="+data,
				type: "get",
				dataType: "json",				
				//data: {"name":data},
				success:function(plug){
					for(var i in plug){
						$("#tab").append("<tr><td>"+plug[i].plname+"</td><td>"+plug[i].pldes+"</td><td>"+plug[i].updatetime+"</td><td>"
						+"<a href="+"describeplug.action?id="+plug[i].id+">详情</a>"
						+"<a href="+"modifyselectplug.action?id="+plug[i].id+">修改</a>"
						+"<a href="+"deleteplug.action?id="+plug[i].id+">删除</a>"
						+"</td></tr>");
					}
				}
			});
		}
		
	</script>
</body>
</html>