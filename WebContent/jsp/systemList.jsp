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
<title>环境页面</title>
</head>
<body>
	<div>
		<div style="text-align: center;">
			输入环境名：<input id="sysname" type="text" name="name">
			<button onclick="search()">查询</button>
			<a href="addSystem.jsp"><button>新增</button></a>
			
		</div>
		<div style="text-align: center;">
			<table id="tab" border="1px" width="80%">
				<tr id="one">
					<td>环境名称</td><td>域名</td><td>更新时间</td><td>描述</td><td>操作</td>
				</tr>
				<c:forEach items="${syslist }" var="sys">
				<tr>
					<td>${sys.systemname }</td><td>${sys.urlname }</td><td>${sys.updatetime }</td><td>${sys.des }</td>
					<td><a href="modify_sys.action?id=${sys.id }">修改</a>
					&nbsp;<a href="delete_sys.action?id=${sys.id }">删除</a></td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		function search(){
		var v=$("#sysname").val();
		var jso={"name":v};
		var s=JSON.stringify(jso);
		//alert(v);
		$.ajax({
			type: "post",
			dataType: "json",
			url: "selectName.action",
			data: s,
			contentType: "application/json; charset=utf-8",
			success: function(obj){
			$("#one~tr").remove();
			//alert(obj);
			for(var i in obj){
				$("#tab").append("<tr><td>"+obj[i].systemname+"</td>"+
				"<td>"+obj[i].urlname+"</td>"+
				"<td>"+obj[i].updatetime+"</td>"+
				"<td>"+obj[i].des+"</td>"+
					"<td><a href="+"modify_sys.action?id="+obj[i].id+">修改</a>"+
					"&nbsp;"+"<a href="+"delete_sys.action?id="+obj[i].id+">删除</a></td></tr>");
				
			}	
			}			
		});
		
		
		}
		

		
	</script>
</body>
</html>